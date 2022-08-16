package com.revature.socialmediaapp.model.dao;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.revature.socialmediaapp.model.socialmediaapp.App;
import com.revature.socialmediaapp.model.user.Userutility;
import com.revature.socialmediaapp.model.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO
{   
	//declare global variable 
	static Connection con;
	static PreparedStatement ps;
	static Statement st;
	static Scanner sc = new Scanner(System.in);
	static PreparedStatement stm;

	//signup user 
	
	public static void signUp(Userutility user) 
	{
		try
		{
		    con = ConnectionUtil.getconnection();//getting connection
		    ps = con.prepareStatement("Insert into userinformation values(?,?,?,?,?,?,?)");
			ps.setInt(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3,user.getAddress());
			ps.setInt(4, user.getAge());
			ps.setString(5,user.getEmail());
			ps.setString(6,user.getGender());
			ps.setString(7, user.getPassword());
			
			if(ps.executeUpdate()==1)
				System.out.println("Inserted Successsfully");
		}
		catch (Exception e) 
		{
		e.printStackTrace();	
		}
		
		
	}
	
	//checklogin

	public static boolean loginEmailCheckIsAvailable(String emaillogin)
	{
		try 
		{
			 con = ConnectionUtil.getconnection();
			 st = con.createStatement();
			String querycheck = "select email from userinformation where email ='"+emaillogin+"'";
			ResultSet rs = st.executeQuery(querycheck);
			if(rs.next())
			{
				
				return false;
				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Wrong email");
		return true;
	}

	public static boolean logiEmailPasswordCheckIsAvailable(String emaillogin, String passwordlogin) {
		try 
		{
			 con = ConnectionUtil.getconnection();
			 st = con.createStatement();
			String querycheck = "select email,password from userinformation where email = '"+emaillogin+"' and password = '"+passwordlogin+"'";
			ResultSet rs = st.executeQuery(querycheck);
			if(rs.next())
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Wrong password");
		return true;
	}

	//view all user
	
	public ArrayList<Userutility> viewAllUser() {
		ArrayList<Userutility> sList = new ArrayList<Userutility>();
		try 
		{
			 con = ConnectionUtil.getconnection();
			 st = con.createStatement();
			ResultSet rs = st.executeQuery("select userid,name,address,age,gender from userinformation");
			while(rs.next())
			{
				Userutility s = new Userutility();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				int age = rs.getInt(4);
				String gender = rs.getString(5);
				
			
				
				s.setId(id);
				s.setName(name);
				s.setAddress(address);
				s.setAge(age);
				s.setGender(gender);
				sList.add(s);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sList;
	}

	//update user
	
	public void update(String email) throws Exception
	{
		int choice;
		con = ConnectionUtil.getconnection();
		
		//getting choise
		
		System.out.println("menu: 1.update name\n2.update password\n3.update age\n4. update address\n5.update gender");
		choice = sc.nextInt();
		
		switch(choice)
		{
		
		//update username
		
			case 1:	System.out.println("Enter the new username: ");
					String tempuser = sc.next();
					
					PreparedStatement stm = con.prepareStatement("update userinformation set name = ? where email = ?");
					stm.setString(1,tempuser);
					stm.setString(2, email);
					stm.executeUpdate();
					System.out.println("The name was updated successfully");
					break;
					
					//update password
					
			
			case 2: System.out.println("Enter the new password: ");
					String temppass = sc.next();
			
					stm = con.prepareStatement("update userinformation set password = ? where email = ?");
					stm.setString(1,temppass);
					stm.setString(2, email);
					stm.executeUpdate();
					System.out.println("The password was updated successfully");
					break;
				
					//update age
					
			case 3:System.out.println("Enter the age: ");
			       int tempage = sc.nextInt();
			       
			       stm = con.prepareStatement("update userinformation set age = ? where email = ?");
			       stm.setInt(1, tempage);
			       stm.setString(2, email);
			       stm.executeUpdate();
			       System.out.println("The age was updated sucessfully");
			       break;
			       
			       //update address
			       
			case 4:System.out.println("Enter address: ");
			       String tempaddress = sc.next();
			       
				       stm = con.prepareStatement("update userinformation set address = ? where email = ?");
				       stm.setString(1,tempaddress);
					   stm.setString(2, email);
					   stm.executeUpdate();
					   System.out.println("The address  was updated successfully");
					   break;
					   
					   ///update gender
					   
			case 5:System.out.println("Enter gender: ");
			       String tempgender = sc.next();
			       
			       stm = con.prepareStatement("update userinformation set gender = ? where email = ?");
			       stm.setString(1, tempgender);
			       stm.setString(2, email);
			       stm.executeUpdate();
			       System.out.println("The gander was updated successfully");
			       break;
			       

		}
	}
	
	//delete user 
	
	public void delete(String email) throws SQLException
	{
		
		stm = con.prepareStatement("DELETE FROM Userinformation where email = '"+email+"'");
		
		if(stm.executeUpdate()==1)
		{
		System.out.println("The record was successfully deleted.");
		}
	}
	
	//view user
	
	public ArrayList<Userutility> View(String emailogin) throws Exception
	
	{
		ArrayList<Userutility> sList = new ArrayList<Userutility>();
		
			 con = ConnectionUtil.getconnection();
			 ps = con.prepareStatement("select userid,name,address,age,gender from userinformation where email=?");
			 ps.setString(1,emailogin);
			ResultSet rs = ps.executeQuery();
			 
			 
			while(rs.next())
			{
				Userutility s = new Userutility();
				int id = rs.getInt(1);
				String name1 = rs.getString(2);
				String address = rs.getString(3);
				int age = rs.getInt(4);
				String gender = rs.getString(5);
				
			
				
				s.setId(id);
				s.setName(name1);
				s.setAddress(address);
				s.setAge(age);
				s.setGender(gender);
				sList.add(s);
			}
		
		return sList;

		
	}
	
	//search profile
	
	public ArrayList<Userutility> searchprofile(String name) throws Exception 
	{	
		ArrayList<Userutility> sList = new ArrayList<Userutility>();
		
		con = ConnectionUtil.getconnection();
	    ps = con.prepareStatement("select userid,name, address,age,gender from userinformation where name like ?");
	    ps.setString(1, name+"%");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			Userutility s = new Userutility();
			int id = rs.getInt(1);
			String name1 = rs.getString(2);
			String address = rs.getString(3);
			int age = rs.getInt(4);
			String gender = rs.getString(5);
			
		
			
			s.setId(id);
			s.setName(name1);
			s.setAddress(address);
			s.setAge(age);
			s.setGender(gender);
			sList.add(s);
		}
		

		return sList;
		
	}
	
	//create post
	
	public void createpost(String msg , String mail) throws Exception
	{
		
		ps = con.prepareStatement("insert into user_post(user_message,user_email,post_date) values(?,?, current_timestamp())");
		ps.setString(1, msg);
		ps.setString(2, mail);
	
		if(ps.executeUpdate()==1)
		{
			System.out.println("\nPost created Sucessfully");
		}

	}
	
	static void PrintPost(Userutility uu)
	{
		String msg = uu.getUser_message();
		String date = uu.getPost_date();
		String time = uu.getPost_time();
		
		
		System.out.println("\nMessage : "+msg+"\nDate :"+date+"\nTime : "+time+"\n");
		
		
	}

	//timeline
	
	public void getTimeline(String emailogin) 
	{
		try
		{
			con = ConnectionUtil.getconnection();
			ps = con.prepareStatement("select user_email,user_message,post_date from user_post where user_email = ? ");
			ps.setString(1, emailogin);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println();
				String mail = rs.getString(1);
				System.out.println("Mail-Id: "+mail);
				String post = rs.getString(2);
				System.out.println("Post : "+post);
				String date = rs.getString(3);
				System.out.println("Date and Time : "+date);
				System.out.println();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	//see post user
	
	public void seeOthersPost(String emailogin)
	{	
		try
		{
			con = ConnectionUtil.getconnection();
			ps = con.prepareStatement("select u.name,p.user_email,p.user_message,p.post_date from userinformation as u inner join  user_post as p on u.email = p.user_email");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println();
				String name = rs.getString(1);
				System.out.println("Name : "+name);
				String mail = rs.getString(2);
				System.out.println("Mail-Id: "+mail);
				String post = rs.getString(3);
				System.out.println("Post : "+post);
				String date = rs.getString(4);
				System.out.println("Date and Time : "+date);
				System.out.println();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
}
