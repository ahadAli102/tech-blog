package com.ahad.service;

import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.Part;

import com.ahad.dao.UserDao;
import com.ahad.entity.Image;
import com.ahad.entity.User;
import com.ahad.util.ServiceProvider;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Override
	public int addUser(User user) {
		if (userDao == null)
			userDao = ServiceProvider.getUserDao();

		if (user.getCondition() != null && user.getCondition().equals("accepted")) {
			if (user.getName() != null && !user.getName().isEmpty() && user.getPassword() != null
					&& !user.getPassword().isEmpty() && user.getRePassword() != null
					&& !user.getRePassword().isEmpty()) {
				if (user.getPassword().equals(user.getRePassword())) {
					return userDao.addUser(user);
				} else {
					throw new RuntimeException("Passwords didn't matched");
				}
			} else {
				throw new RuntimeException("All form filed not filled");
			}
		} else {
			throw new RuntimeException("Sorry you don't have checked condition");
		}

	}

	@Override
	public User getUser(String email, String password) {
		if (userDao == null)
			userDao = ServiceProvider.getUserDao();
		if (email != null && !email.isBlank() && !email.isEmpty()) {
			if (password != null && !password.isBlank() && !password.isEmpty()) {
				System.out.println("service get user porsess");
				return userDao.getUser(email, password);
			} else {
				throw new RuntimeException("service get user password is empty");
			}
		} else {
			throw new RuntimeException("service get user email is empty");
		}

	}

	@Override
	public int insertProfileImage(Part part, String email) {
		if (userDao == null)
			userDao = ServiceProvider.getUserDao();

		try {
			String fileName = part.getSubmittedFileName();
			String type = part.getContentType();
			if (part != null && !fileName.isEmpty()) {
				InputStream is = part.getInputStream();
				byte[] image = is.readAllBytes();
				System.out.println("Name is :" + fileName + " size is : " + image.length);
				return userDao.insertProfileImage(image, fileName, type, email);

			} else {
				throw new RuntimeException("Please select a file");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Image getProfileImage(String email) {
		if (userDao == null)
			userDao = ServiceProvider.getUserDao();
		if (email != null) {
			Image image = userDao.getImage(email);
			if (image != null) {
				String textImage = Base64.getEncoder().encodeToString(image.getByteImage());
				image.setTextImage(textImage);
				return image;
			} else {
				throw new RuntimeException("Image not fownd for the user " + email);
			}
		} else {
			throw new RuntimeException("User email is empty");
		}
	}

	@Override
	public void rateAuthor(String authorEmail, String raterEmail, int rating) {
		if (userDao == null)
			userDao = ServiceProvider.getUserDao();
		System.out.println("User service : "+authorEmail+" "+raterEmail+" "+rating);
		if(userDao.rateAuthor(authorEmail, raterEmail, rating) == -1)
			throw new RuntimeException("You have already ratted this author");
	}

}
