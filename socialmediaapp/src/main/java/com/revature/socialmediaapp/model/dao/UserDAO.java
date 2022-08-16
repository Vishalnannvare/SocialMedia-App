package com.revature.socialmediaapp.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.socialmediaapp.model.user.Userutility;

public interface UserDAO
{

	ArrayList<Userutility> viewAllUser();

	List<Userutility> View(String emailogin) throws Exception;

	List<Userutility> searchprofile(String name) throws Exception;

	void createpost(String message, String emailogin) throws Exception;

	void update(String emailogin) throws Exception;

	void delete(String emailogin) throws SQLException;

	void getTimeline(String emailogin);

	void seeOthersPost(String emailogin);
	
}