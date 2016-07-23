package com.vidtweet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
		ResultSet res_max=null;
		ResultSet res_pass = null;
		String password=null;
		
		int count=0;
		HttpSession session=request.getSession(false);
		String userName=(String) session.getAttribute("user");
		
		DBConnect conn=new DBConnect();
        Statement statement=conn.getNewStatement();	
        
        String fetchMaxConvId="select max(conv_id) from conversation";
        
        	try
        	{
        		res_max=statement.executeQuery(fetchMaxConvId);
                while(res_max.next())
                {
                	count=res_max.getInt(1);
                }
                count++;
                String convName=(String)request.getParameter("buttonclicked")+"_"+userName;
                String insertConversationQuery="insert into conversation values ("+count+","+"'"+convName+"'"+","+ "CURRENT_TIMESTAMP)";
                statement.executeUpdate(insertConversationQuery);
                
                
                String insertFriendshipQuery="insert into friendship values (" + "'"+ request.getParameter("buttonclicked")+ "','"+userName+"')"; 
                statement.executeUpdate(insertFriendshipQuery);
                
                String insertFriendshipQuery1="insert into friendship values ('"+userName+"','"+ request.getParameter("buttonclicked")+"')"; 
                statement.executeUpdate(insertFriendshipQuery1);
                
                String insertHasaQuery="insert into hasa values ('"+userName+"',"+ count+")";
                statement.executeUpdate(insertHasaQuery);
                
                String insertHasaQuery1="insert into hasa values ('"+ request.getParameter("buttonclicked")+"',"+ count+")";
                statement.executeUpdate(insertHasaQuery1);
                
                String getPasswordQuery = "select a.passwd from auth a where a.uname='" + userName+"'";
                res_pass = statement.executeQuery(getPasswordQuery);
                while( res_pass.next() )
                {
                	password=res_pass.getString(1);
                }
                
			} 
        	catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//PrintWriter pwr=response.getWriter();
		//pwr.print("Hello world " + request.getParameter("buttonclicked") + count);
        	request.setAttribute("unamez", userName);
            request.setAttribute("passz", password);
            request.getRequestDispatcher("Login").forward(request,response);	 
	}

}
