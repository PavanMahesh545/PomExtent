package com.htc.ems.dao;

import com.htc.ems.model.User;

public interface IUserDAO {
	public User getUser(int accno);//Read
	public boolean deleteUser(int idt);
	public boolean updateemail(int uid,String email);
	public boolean createUSer(User user);
	
}
