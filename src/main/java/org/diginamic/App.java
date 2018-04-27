package org.diginamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdd_mysql","root","");
			Statement statement = connection.createStatement();
		} catch (SQLException e) {
			
		}
        
        
    }
}
