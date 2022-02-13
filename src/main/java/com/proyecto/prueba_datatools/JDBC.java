package com.proyecto.prueba_datatools;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
	Connection connection = null;
	
	public JDBC() {
		
		/* JDBC Connection */
		String url = "jdbc:mysql://localhost:3306/prueba_datatools";
		String user = "root";
		String pass = "Root123*";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
