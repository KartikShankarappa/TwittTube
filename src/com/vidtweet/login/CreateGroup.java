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
 * Servlet implementation class CreateGroup
 */
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGroup() {
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
		//PrintWriter pwr=response.getWriter();
		//pwr.print("Hello world");
		ResultSet res_pass = null;
		String password=null;
		HttpSession session=request.getSession(false);
		String userName=(String) session.getAttribute("user");
		
		ResultSet res_max=null;
		int count=0;
		
		DBConnect conn=new DBConnect();
		Statement statement=conn.getNewStatement();
		String fetchMaxConvId="select max(conv_id) from conversation";
		String convName=request.getParameter("groupName");
		System.out.println("Group Name: "+convName);
		
		try
    	{
    		res_max=statement.executeQuery(fetchMaxConvId);
            while(res_max.next())
            {
            	count=res_max.getInt(1);
            }
            count++;
            String insertConversationQuery="insert into conversation values ("+count+","+"'"+convName+"'"+","+ "CURRENT_TIMESTAMP)";
            statement.executeUpdate(insertConversationQuery);
    	}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] checkboxNamesList= request.getParameterValues("searcheduser");
		for (int i = 0; i < checkboxNamesList.length; i++) {

		    String myCheckBoxValue=request.getParameter(checkboxNamesList[i]);

		    // if null, it means checkbox is not in request, so unchecked 
		    if (myCheckBoxValue != null)
		    {
		    	
		    }
		        //pwr.print(checkboxNamesList[i] + "=unchecked");

		    // if is there, it means checkbox checked
		    else
		    {
		        
		    	String insertHasaQuery="insert into hasa values ('"+(String)checkboxNamesList[i]+"',"+ count+")";
		    	try {
					statement.executeUpdate(insertHasaQuery);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }   
		}
		String insertHasaQuery="insert into hasa values ('"+userName+"',"+ count+")";
		try {
			statement.executeUpdate(insertHasaQuery);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		pwr.flush();
		String getPasswordQuery = "select a.passwd from auth a where a.uname='" + userName+"'";
        try {
			res_pass = statement.executeQuery(getPasswordQuery);
			 while( res_pass.next() )
		        {
		        	password=res_pass.getString(1);
		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        request.setAttribute("unamez", userName);
        request.setAttribute("passz", password);
        request.getRequestDispatcher("Login").forward(request,response);
		
	}

}
