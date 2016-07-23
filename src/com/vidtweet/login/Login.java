package com.vidtweet.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ResultSet res = null;
		ResultSet res1= null;
		int count=0;
		String url=null;
		String user_name=null;
		String userConvQueryString=null;
		String username = request.getParameter("Name");
        String password = request.getParameter("Pass");
        if(!(username != null && password!=null) )
        {
        	username = (String)request.getAttribute("unamez");
        	password = (String)request.getAttribute("passz");
        }
        response.setContentType("text/html");
        
        DBConnect conn=new DBConnect();
        Statement statement=conn.getNewStatement();
        String userCountQuery="SELECT count(a.uname), a.uname from auth a where uname='"+username+"' and passwd='"+password+"'"+"group by a.uname";

        try 
        {
        	if(statement!=null)
        	{
        		res = statement.executeQuery(userCountQuery);
        		while(res.next())
        		{
        			count=res.getInt(1);
        			user_name = res.getString(2);
        			userConvQueryString ="select c.conv_name, c.conv_id from conversation c "
        		        		+ "inner join hasa h on h.conv_id=c.conv_id inner join auth a on a.uname=h.uname "
        		        		+ "where a.uname="+"\""+user_name+"\" order by c.last_mod_date DESC";
        			System.out.println("Query: " + userConvQueryString);
        		}
        		
        		res1 = statement.executeQuery(userConvQueryString);
        		System.out.println("query 2 executed");
        		if ( count  > 0 )
        		{
        			res1 = statement.executeQuery(userConvQueryString);
            		System.out.println("query 2 executed");
        			
        			HttpSession session= request.getSession(true);
        			session.setAttribute("user", user_name);
        			
        			request.setAttribute("varName", user_name);
        			request.setAttribute("conv_list", res1);
        			getServletContext().getRequestDispatcher("/LoginSuccesful_1.jsp").forward(request, response);
        			
        		}
        		else
        		{
        			response.sendRedirect("LoginFailure.jsp");
        			return;
        		}
			}
        	else
        	{
        		response.getWriter().println("<html><body><Marquee>Null Value found!!!</marquee></body></html>");
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        finally
        {
        	try {
				if (res != null) {
					res.close();
				}

				if (res1 != null) {
					res1.close();
				}

				if (statement != null) {
					statement.close();
				}
				if (conn.connect != null) {
					conn.close_conn();
				}
			} catch (Exception e) {

			}
        }
        
	}

}
