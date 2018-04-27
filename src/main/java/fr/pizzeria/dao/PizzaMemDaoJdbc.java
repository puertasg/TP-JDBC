package fr.pizzeria.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Pizza;

public class PizzaMemDaoJdbc implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaMemDaoJdbc.class);

	private Connection connection;
	private Statement statement;

	public PizzaMemDaoJdbc() {

		Properties prop = new Properties();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			InputStream input = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config_jdbc.properties");
			prop.load(input);
			input.close();

			this.connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("password"));
			this.connection.setAutoCommit(false);
			this.statement = connection.createStatement();
		} catch (SQLException | ClassNotFoundException | IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {

		List<Pizza> listePizza = new ArrayList<>();
		ResultSet results = null;

		try {
			results = this.statement.executeQuery("SELECT * FROM pizzas");

			while (results.next()) {

				String code = results.getString("CODE");
				String libelle = results.getString("LIBELLE");
				double prix = results.getDouble("PRIX");

				listePizza.add(new Pizza(code, libelle, prix));
			}

		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue");
			e.printStackTrace();
		} finally {
			this.closeResults(results);
		}

		return listePizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		PreparedStatement insertPizzaSt = null;

		try {
			insertPizzaSt = this.connection.prepareStatement("INSERT INTO pizzas(CODE,LIBELLE,PRIX) VALUES(?,?,?)");
			insertPizzaSt.setString(1, pizza.getCode());
			insertPizzaSt.setString(2, pizza.getLibelle());
			insertPizzaSt.setDouble(3, pizza.getPrix());

			insertPizzaSt.executeUpdate();

		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue lors de l'insertion pizza");
			e.printStackTrace();
		} finally {
			this.closePreparedStatement(insertPizzaSt);
		}

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		PreparedStatement updatePizzaSt = null;
		try {
			updatePizzaSt = this.connection
					.prepareStatement("UPDATE pizzas SET CODE=?, LIBELLE=?, PRIX=? WHERE CODE=?");
			updatePizzaSt.setString(1, pizza.getCode());
			updatePizzaSt.setString(2, pizza.getLibelle());
			updatePizzaSt.setDouble(3, pizza.getPrix());
			updatePizzaSt.setString(4, codePizza);

			updatePizzaSt.executeUpdate();

			updatePizzaSt.close();
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue lors de l'update pizza");
			e.printStackTrace();
		} finally {
			this.closePreparedStatement(updatePizzaSt);
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		PreparedStatement deletePizzaSt = null;

		try {
			deletePizzaSt = this.connection.prepareStatement("DELETE FROM pizzas WHERE CODE=?");
			deletePizzaSt.setString(1, codePizza);

			deletePizzaSt.executeUpdate();

			deletePizzaSt.close();
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue lors de la supression pizza");
			e.printStackTrace();
		} finally {
			this.closePreparedStatement(deletePizzaSt);
		}

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		Pizza pi = null;
		ResultSet results = null;
		try {
			PreparedStatement selectPizzaSt = this.connection.prepareStatement("SELECT * FROM pizzas WHERE CODE=?");
			selectPizzaSt.setString(1, codePizza);

			results = selectPizzaSt.executeQuery();

			if (results.isBeforeFirst()) {
				results.next();

				String code = results.getString("CODE");
				String libelle = results.getString("LIBELLE");
				double prix = results.getDouble("PRIX");

				pi = new Pizza(code, libelle, prix);
			}

		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue lors de la recherche pizza par code");
			e.printStackTrace();
		} finally {
			this.closeResults(results);
		}

		return pi;
	}

	@Override
	public boolean pizzaExists(String codePizza) {

		boolean found = false;
		ResultSet results = null;

		try {
			PreparedStatement selectPizzaSt = this.connection.prepareStatement("SELECT * FROM pizzas WHERE CODE=?");
			selectPizzaSt.setString(1, codePizza);

			results = selectPizzaSt.executeQuery();

			found = results.first();

		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue lors de la recherche pizza");
			e.printStackTrace();
		} finally {
			this.closeResults(results);
		}

		return found;
	}

	public void commit() {
		try {
			this.connection.commit();
		} catch (SQLException e) {
			this.rollback();
			e.printStackTrace();
		}
	}
	
	public void rollback()
	{
		try {
			this.connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			this.statement.close();
			this.connection.close();
		} catch (SQLException e) {
			LOG.error("Une erreur est survenue lors de la femreture de la connection");
			e.printStackTrace();
		}
	}

	public void closeResults(ResultSet r) {
		if (r != null) {
			try {
				r.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void closePreparedStatement(Statement s) {
		if (s != null) {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
