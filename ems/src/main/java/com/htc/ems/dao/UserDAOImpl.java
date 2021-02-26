package com.htc.ems.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.htc.ems.model.User;

public class UserDAOImpl implements IUserDAO {
	
	Connection con=null;
	
	public UserDAOImpl() {
		super();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	public static Connection getJDBCConnection()
	{
		Connection gen=null;
		 InputStream input = null;
		try {
			input = new FileInputStream("E:\\Selenium\\ems\\src\\main\\resources\\dbconnection.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 Properties dbprop=new Properties();
		 try {
			dbprop.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			gen=DriverManager.getConnection(dbprop.getProperty("DB_DRIVER")+dbprop.getProperty("DB_URL"),dbprop.getProperty("DB_USERNAME"),dbprop.getProperty("DB_PASSWORD"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		System.out.println(gen.toString());
		return gen;
		
	}
	@Override
	public User getUser(int accno) {
		con=getJDBCConnection();
		User usr=null;
		try {
			PreparedStatement st=con.prepareStatement("SELECT id, name, email, country, password\r\n" + 
					"  FROM public.users WHERE id=?;" );
			st.setInt(1, accno);
			
			ResultSet rs=st.executeQuery();
			while(rs.next())
			{
				usr=new User();
				usr.setId(rs.getInt("id"));
				usr.setName(rs.getString("name"));
				usr.setEmail(rs.getString("email"));
				usr.setCountry(rs.getString("country"));
				usr.setPassword(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				return usr;
	}

	@Override
	public boolean deleteUser(int idt) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public boolean createUSer(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateemail(int uid, String email) {
		// TODO Auto-generated method stub
		boolean status=false;
		con=getJDBCConnection();
		try {
			PreparedStatement st=con.prepareStatement("UPDATE users SET email=? WHERE id=?;" );
			st.setInt(2, uid);
			st.setString(1,email);
			
			
			int noOfRowsAffected=st.executeUpdate();
			
			if(noOfRowsAffected==1)
			{
				status=true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

}
