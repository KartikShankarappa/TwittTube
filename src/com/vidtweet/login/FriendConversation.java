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
 * Servlet implementation class FriendConversation
 */
public class FriendConversation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendConversation() {
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
		int conv_id=0;
		String selectObjectQuery = "";
		PrintWriter pwr=response.getWriter();
		pwr.print("Hello world" + request.getParameter("buttonclicked"));
		String conv_id_str = (String)request.getParameter("buttonclicked");
		if(conv_id_str != null)
		{
			conv_id = Integer.parseInt(conv_id_str);
		}
		else
		{
			conv_id = (int)request.getAttribute("qwerty");
		}
		DBConnect conn=new DBConnect();
        Statement statement=conn.getNewStatement();
        
        HttpSession session=request.getSession(false);
        session.setAttribute("conv_id", conv_id);
		selectObjectQuery = "select a.uname,temp_tab.oname, temp_tab.oid, temp_tab.odate from auth a "
				+ " inner join addedby ab on a.uname=ab.uname inner join ("
				+ " select o.obj_name as oname, o.obj_id as oid, o.obj_create_date as odate"
				+ " from 	object_tbl o inner join belongsto b on b.obj_id=o.obj_id "
				+ "inner join conversation c on c.conv_id=b.conv_id "
				+ "where c.conv_id=" +conv_id +") as temp_tab on temp_tab.oid=ab.obj_id order by temp_tab.odate DESC";
		try 
		{
			res = (ResultSet)statement.executeQuery(selectObjectQuery);
			request.setAttribute("objectres", res);
			request.setAttribute("conv_name", "");
			getServletContext().getRequestDispatcher("/VideoConversation.jsp").forward(request, response);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
        {
        	try {
				if (res != null) {
					res.close();
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
