/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ringkasan.klasifikasiretorik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mochamadtry
 */
public class AksesDatabase {
    private static Connection connect = null;
    private static Statement state = null;

	public AksesDatabase() {
            final String driver = "com.mysql.jdbc.Driver";
            final String url = "jdbc:mysql://localhost:3306/notulenbaru";

            final String username = "root";
            final String password = "password";


            try {
                    Class.forName(driver);
                    connect = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
            }
			

        }

	public ResultSet selectquery(String query) {
		ResultSet rs = null;

		try {
			state = connect.createStatement();
			rs = state.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public boolean updatequery(String query) {
		try {
			state = connect.createStatement();
			state.execute(query);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
}
