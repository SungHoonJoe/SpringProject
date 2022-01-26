package com.honsta.project.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honsta.project.common.EncryptUtils;
import com.honsta.project.user.dao.UserDAO;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(String loginId,String password,String name,String email) {
		
		String encPassword = EncryptUtils.md5(password);
		
		return  userDAO.insertUser(loginId, encPassword, name, email);
	}
	
	

}
