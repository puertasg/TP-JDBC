package org.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/pizzadb", "root", "");
			Statement statement = connection.createStatement();

			String sqlCreate = "CREATE TABLE pizzas " 
					+ "(id INTEGER not NULL, " 
					+ " CODE VARCHAR(3), "
					+ " LIBELLE VARCHAR(255), " 
					+ " PRIX DOUBLE, " 
					+ " PRIMARY KEY ( id ))";

			statement.executeQuery(sqlCreate);
		} catch (SQLException e) {
			System.out.println("Echec de l'éxécution de la requete SQL");
		} catch (ClassNotFoundException e1) {
			System.out.println("Echec du chargement du driver");
			e1.printStackTrace();
		}

	}
}
