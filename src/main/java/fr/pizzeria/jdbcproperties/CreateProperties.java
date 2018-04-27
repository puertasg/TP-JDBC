package fr.pizzeria.jdbcproperties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class CreateProperties {
	public static void main(String[] args) {

		Properties prop = new Properties();
		OutputStream output = null;

		try {
			
			output = new FileOutputStream("config_jdbc.properties");

			prop.setProperty("url", "jdbc:mysql://127.0.0.1/pizzadb");
			prop.setProperty("user", "root");
			prop.setProperty("password", "");

			prop.store(output, null);
			
			output.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
}