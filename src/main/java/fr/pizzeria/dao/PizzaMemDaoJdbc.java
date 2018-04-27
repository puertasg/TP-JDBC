package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Pizza;

public class PizzaMemDaoJdbc implements IPizzaDao {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaMemDaoJdbc.class);

	private Connection connection;
	private Statement statement;

	public PizzaMemDaoJdbc() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/pizzadb", "root", "");
			this.statement = connection.createStatement();
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue ");
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			LOG.error("Echec du chargement du driver");
			e1.printStackTrace();
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {

		List<Pizza> listePizza = new ArrayList<>();

		try {
			ResultSet results = this.statement.executeQuery("SELECT * FROM pizzas");

			while (results.next()) {

				String code = results.getString("CODE");
				String libelle = results.getString("LIBELLE");
				double prix = results.getDouble("PRIX");

				listePizza.add(new Pizza(code, libelle, prix));
			}
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue ");
			e.printStackTrace();
		}

		return listePizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		try {
			PreparedStatement insertPizzaSt = this.connection
					.prepareStatement("INSERT INTO pizzas(CODE,LIBELLE,PRIX) VALUES(?,?,?)");
			insertPizzaSt.setString(1, pizza.getCode());
			insertPizzaSt.setString(2, pizza.getLibelle());
			insertPizzaSt.setDouble(3, pizza.getPrix());

			insertPizzaSt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue ");
			e.printStackTrace();
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement updatePizzaSt = this.connection
					.prepareStatement("UPDATE pizzas SET CODE=? LIBELLE=? PRIX=? WHERE CODE=?");
			updatePizzaSt.setString(1, codePizza);
			updatePizzaSt.setString(2, pizza.getLibelle());
			updatePizzaSt.setDouble(3, pizza.getPrix());
			updatePizzaSt.setString(4, pizza.getCode());

			updatePizzaSt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue ");
			e.printStackTrace();
		}
	}

	@Override
	public void deletePizza(String codePizza) {
		try {
			PreparedStatement deletePizzaSt = this.connection.prepareStatement("DELETE FROM pizzas WHERE CODE=?");
			deletePizzaSt.setString(1, codePizza);

			deletePizzaSt.executeUpdate();
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue ");
			e.printStackTrace();
		}

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {

		Pizza pi = null;
		try {
			PreparedStatement selectPizzaSt = this.connection.prepareStatement("SELECT * FROM pizzas WHERE CODE=?");
			selectPizzaSt.setString(1, codePizza);

			ResultSet results = selectPizzaSt.executeQuery();

			if (!results.isBeforeFirst()) {
				results.next();

				String code = results.getString("CODE");
				String libelle = results.getString("LIBELLE");
				double prix = results.getDouble("PRIX");

				pi = new Pizza(code, libelle, prix);
			}
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue ");
			e.printStackTrace();
		}
		return pi;
	}

	@Override
	public boolean pizzaExists(String codePizza) {

		boolean found = false;

		try {
			PreparedStatement selectPizzaSt = this.connection.prepareStatement("SELECT * FROM pizzas WHERE CODE=?");
			selectPizzaSt.setString(1, codePizza);

			ResultSet results = selectPizzaSt.executeQuery();

			if (!results.isBeforeFirst()) {
				found = true;
			}
		} catch (SQLException e) {
			LOG.error("Une erreur SQL est survenue ");
			e.printStackTrace();
		}

		return found;
	}

}
