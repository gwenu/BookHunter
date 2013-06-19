package controllers;

import controllers.Secure.Security;
import models.User;

public class UserLogin extends Secure.Security {

	static boolean authenticate(String username, String password) {
		return User.connect(username, password) != null;
	}
	
	static void onDisconnected() {
	    Application.index();
	}
}
