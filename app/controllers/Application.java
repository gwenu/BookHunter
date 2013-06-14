package controllers;

import java.io.File;

import play.mvc.Controller;
import controllers.utils.Constants;

public class Application extends Controller {

	public static void index() {
		render();
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
