package com.vidtweet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchUser
 */
public class SearchUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUser() {
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
		PrintWriter pwr=response.getWriter();
		String username=(String) request.getParameter("Name");
		System.out.println("Name is: " + username );
		ResultSet res=null;
		String userName=(String) request.getAttribute("varName");
		request.setAttribute("varName", userName);
		HttpSession session= request.getSession(false);
		String loggedInUser=(String) session.getAttribute("user");
		
		String userSearchQuery="select a.uname from auth a where a.uname like "+"'%" + username + "%' and a.uname!=" +"'"+ loggedInUser +"' and a.uname not in (select f.friend from friendship f where f.user ='"+loggedInUser+"')";
		DBConnect conn=new DBConnect();
        Statement statement=conn.getNewStatement();
        try {
			res=statement.executeQuery(userSearchQuery);
			request.setAttribute("availableUsers",res);
			getServletContext().getRequestDispatcher("/SearchResults.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
