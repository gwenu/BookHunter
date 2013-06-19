package controllers;

import java.io.File;

import models.User;

import org.junit.Before;

import play.mvc.Controller;
import controllers.Secure.Security;
import controllers.utils.Constants;

public class Application extends Controller {
	
	public static void index() {
		String user = Security.connected();
        render(user);
	}

	public static void about() {
		render();
	}

	public static void image(String imageName, boolean imageBig) {
		File imageFile = imageBig 
				       ? new File(Constants.IMAGE_PATH + "big_" + imageName) 
		               : new File(Constants.IMAGE_PATH + imageName);
		renderBinary(imageFile);
	}
}
