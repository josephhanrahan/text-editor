package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

public class UserBag implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TreeMap<String, User> userBag;
	

	public UserBag() {
		userBag = new TreeMap<String, User>();
	}

	public TreeMap<String, User> getUserBag() {
		return userBag;
	}

	public void setUserBag(TreeMap<String, User> userBag) {
		this.userBag = userBag;
	}
	
	public User searchByUsername(User user) {
		User index = userBag.get(user.getUsername());
		return index;
	}
	
	public void insert(User user) {
		userBag.put(user.getUsername(), user);
	}
	

}
