package com.vidtweet.login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class test 
{

	public static void main(String[] args) 
	{
		ResultSet rs = null;
		PreparedStatement pStatement=null;
		DBConnect conn=new DBConnect();
        Statement statement=conn.getNewStatement();
        Integer conv_id = 2;
        String testQuery="insert into conversation (conv_name,last_mod_date) values (?,?)";
        try {
			pStatement=conn.connect.prepareStatement(testQuery,Statement.RETURN_GENERATED_KEYS);
			pStatement.setString(1, "conv5");
	        pStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
	        pStatement.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        String selectObjectQuery = "select a.uname,temp_tab.oname, temp_tab.oid, temp_tab.odate from auth a "
				+ " inner join addedby ab on a.uname=ab.uname inner join ("
				+ " select o.obj_name as oname, o.obj_id as oid, o.obj_create_date as odate"
				+ " from 	object_tbl o inner join belongsto b on b.obj_id=o.obj_id "
				+ "inner join conversation c on c.conv_id=b.conv_id "
				+ "where c.conv_id=" +conv_id +") as temp_tab on temp_tab.oid=ab.obj_id order by temp_tab.odate";
		System.out.println(selectObjectQuery);
		try 
		{
			rs = (ResultSet)statement.executeQuery(selectObjectQuery);
			while(rs.next())
			{
				System.out.println(rs.getString(3));
			}
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
