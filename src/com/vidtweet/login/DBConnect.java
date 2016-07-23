package com.vidtweet.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	public Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String DB_END_POINT = "vidtweetinst.cxdz5wnqcr4a.us-east-1.rds.amazonaws.com";
	private final String DB_USER_NAME = "admin";
	private final String DB_PWD = "admin112";
	private final String DB_NAME = "mydb";
	private final int DB_PORT = 3306;
	
	
	public void close_conn()
	{
		try 
		{
			connect.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public Statement getNewStatement()
	{
		try 
		{
			//System.out.println("This is working 1....");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//System.out.println("This is working 2....");
			connect = DriverManager
					.getConnection("jdbc:mysql://"+DB_END_POINT+":"+DB_PORT+"/"+DB_NAME,DB_USER_NAME,DB_PWD);
			//System.out.println("This is working 3....");
			statement = connect.createStatement();
//			
			
			return statement;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet run_select_query_rs(Statement st,String query)
	{
		try
		{
			getNewStatement(	);
			return st.executeQuery(query);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {

			}
		}
		
	}

}
