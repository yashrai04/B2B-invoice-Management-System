package com.highradius.connection;
import java.sql.*;
//import java.util.*;
public class DataConnector{
	public static void main(String args[])
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/h2h","root","Rohan@123");
			Statement statement=con.createStatement();
			ResultSet resultset=statement.executeQuery("select * from h2h_oap");
			
			while(resultset.next())
			{
			   System.out.println(resultset.getInt(1)+" "+resultset.getString(2)+" "+resultset.getString(3)+" "+resultset.getString(4)+"  "+resultset.getString(5)
			   +" "+resultset.getDouble(6)+"   "+resultset.getString(7)+"  "+resultset.getInt(8)+"  "+resultset.getString(9)+"  "+resultset.getString(10)
			   +"  "+resultset.getString(11)+"  "+resultset.getInt(12)+" "+resultset.getDouble(13)+"  "+resultset.getString(14)+"  "+resultset.getString(15)
			   +"  "+resultset.getString(16)+"  "+resultset.getInt(17)+"  "+resultset.getDouble(18));
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
}
   
}
