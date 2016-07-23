package com.vidtweet.login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=request.getParameter("Name");
		String password=request.getParameter("Pass");
		int count=0;
		ResultSet res=null;
		String userValidationQuery= "select count(a.uname) from auth a where a.uname=" + "'" + username + "'";
		DBConnect conn=new DBConnect();
		Statement statement=conn.getNewStatement();
		try {
			res=statement.executeQuery(userValidationQuery);
			while(res.next())
    		{
    			count=res.getInt(1);
    		}
			
			if ( count > 0 )
			{
				getServletContext().getRequestDispatcher("/LoginFailure.jsp").forward(request, response);
			}
			else
			{
				String insertQuery="insert into auth values (" + "'" + username + "'" + "," + "'" + password + "'" + ")";
				System.out.println("insert " + insertQuery);
				statement.executeUpdate(insertQuery);
			}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			
				try 
				{
					if (res != null) {
						res.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (conn.connect != null) {
						conn.close_conn();
					}
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
		}
		
		HttpSession session=request.getSession(true);
		session.setAttribute("user", username);
		getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		
	}

}
