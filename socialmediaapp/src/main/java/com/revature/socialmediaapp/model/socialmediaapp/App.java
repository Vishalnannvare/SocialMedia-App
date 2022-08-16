package com.revature.socialmediaapp.model.socialmediaapp;
import java.util.*;

import com.revature.socialmediaapp.model.dao.UserDAO;
import com.revature.socialmediaapp.model.dao.UserDAOImpl;
import com.revature.socialmediaapp.model.user.Userutility;


/**
 * Hello world!
 *
 */
public class App 
{
	private static final String String = null;
	
	public static void printDetails(Userutility u)
	{
		int id = u.getId();
		String name = u.getName();
		String address = u.getAddress();
		int age = u.getAge();
		String gender = u.getGender();
		System.out.println(id+" "+name+" "+" "+address+" "+age+"  "+gender+" ");
	}

	    public static void main( String[] args )
	    {
	    	//intital condition for executing 
	    	String ans = "yes";
//for keeping user on main page
	   	 while(ans.equalsIgnoreCase("yes"))
	   	 {
	   		 //log menu 
		   	  while(ans.equalsIgnoreCase("yes")) 
		   	  {
		   		try
			    {
			    	Scanner sc = new Scanner(System.in);
			    	System.out.println("-----------Welcome to Social Media App-----------------");
			    	System.out.println();
			    	System.out.println("    1.Sign up      " + " "+ "    2.Login up        ");
			    	System.out.println();
			    	System.out.println("----------------------------------------------------");

			    	
//			    	System.out.println("\n enter your choice" + "  \n 1.Register user  "+ "\n2.Login user");
			    	int choise = sc.nextInt();
			    	boolean flag = true;
			    	String emailogin = null;
					String passwordlogin = null;
					
	//getting choise to sign up and login in	
					
				    	switch(choise)
				    	{
				    	case 1:
				    		System.out.println("Enter user id");
				    		int id = sc.nextInt();
				    		System.out.println("Enter user name");
				    		String name = sc.next();
				    		System.out.println("Enter user address");
				    		String address = sc.next();
				    		System.out.println("Enter user age");
				    		int age = sc.nextInt();
				    		System.out.println("Enter user email");
				    		String email = sc.next();
				    		System.out.println("Enter user gender");
				    		String gender = sc.next();
				    		System.out.println("Enter user password");
				    		String passward = sc.next();    		
				    		Userutility user = new Userutility();
				    		user.setId(id);
				    		user.setAddress(address);
				    		user.setAge(age);
				    		user.setEmail(email);
				    		user.setGender(gender);
				    		user.setName(name);
				    		user.setPassword(passward);
				    		
				    		UserDAOImpl.signUp(user);
				    		System.out.println();
					    	System.out.println("----------------------------------------------------");
				    		System.out.println("Profile created");
				    		System.out.println();
					    	System.out.println("----------------------------------------------------");
				    		System.out.println("login profile");
					    	System.out.println("----------------------------------------------------");

				    	
				    	case 2:
				    		boolean choise1 = true;
				    		boolean choise2 = true;
				    		 emailogin = null;
				    		 passwordlogin = null;
				    		
				    		while(choise1)
				    		{
				    			System.out.println("Enter email address");
				    			emailogin = sc.next();
				    			choise1 = UserDAOImpl.loginEmailCheckIsAvailable(emailogin);
				    			
				    		}
				    		
				    		while(choise2)
				    		{
				    			System.out.println("enter password");
				    			passwordlogin = sc.next();
				    			choise2 = UserDAOImpl.logiEmailPasswordCheckIsAvailable(emailogin,passwordlogin);
				    		}
				    		
				    		System.out.println("login Sucessfully");
				    		System.out.println();
					    	System.out.println("----------------------------------------------------");
					    	System.out.println();

				    		// after login user see menu 
				    		
				    		String option;
				    		String opton1;
				    		
							do {
				        		Scanner Sc = new Scanner(System.in);
				        		System.out.println("---------------menu-------------------");
				        		System.out.println(
				        				"\n 1. View all profiles"
				        				+"\n 2. Update profile"
				        				+ "\n 3. Delete profile"
				        				+ "\n 4. View my profile"
				        				+"\n 5. Search profile"
				        				+ "\n 6. Create Post"
				        				+ "\n 7. Show timeline"
				        				+"\n 8. See post created by other user"
				        				+ "\n 9. Log out");
				        		System.out.println();
				        		System.out.println("Enter your option");
						    	System.out.println("----------------------------------------------------");

				        		int option1 = sc.nextInt();
				        		UserDAO dao = new UserDAOImpl();
				        		
				        		switch(option1)
				        		{
					        		case 1: //show all users
					        				System.out.println("::::Listing all the User::::");
					        				ArrayList<Userutility> uList = dao.viewAllUser();
					        				for(Userutility userutility : uList)
					        				{
					        					printDetails(userutility);
					        				}
					        				break;
					        				
					        		case 2: // update
					        				dao.update(emailogin);
					        				break;
					        				
					        		case 3: //delete 
					        				dao.delete(emailogin);
					        				App.main(null);
					        				break;

					        		case 4: //view my profile
					        			
					        			List<Userutility> list = dao.View(emailogin);
					        			
					        			for(Userutility u : list)
					        			{
					        				printDetails(u);
					        			}
					        			
					        			break;
					        			
					        		case 5: //search profile
					        			System.out.println("Enter name you are searching");
					        			name = sc.next();
					        			List<Userutility> List = dao.searchprofile(name);
					        			for(Userutility u : List)
					        			{
					        				printDetails(u);
					        			}
					        							        			
					        			break;
					        			
					        		case 6: //create post
					        			System.out.println("ENter the post message: ");
					        			sc.nextLine();
					        			String message = sc.nextLine();
					        			dao.createpost(message,emailogin);
					        			break;
					        			
					        		case 7://Show Timeline
					        			dao.getTimeline(emailogin);
					        			break;
					        			
					        		case 8://see post created by other user
					        			dao.seeOthersPost(emailogin);
					        			break;
					        			
					        		case 9://logout
					        			flag = false;
					        			break;
					        		
					        		
					        			
				        		}
				        		
				        		if(flag == false)
				        		{
				        			break;
				        		}
			        		System.out.println("Do you want  to menu continue with loggers menu(yes/no)");
			        		opton1 = sc.next();
							
			        	}while(opton1.equalsIgnoreCase("yes"));
							
							System.out.println("Going back to main menu");
				    	}	
				    }
			   	 catch(Exception e)
			    {
			    	System.out.println(e);
			    }
	   		 
	   		System.out.println("Do you want to continue with main menu(yes/no): '");
	   		ans = new Scanner(System.in).next();
	   	 }
	    }
	   }
	 } 	

