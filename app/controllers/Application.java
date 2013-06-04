package controllers;

import play.*;
import play.mvc.*;

import java.io.File;
import java.util.*;

import controllers.utils.Constants;

import models.*;

public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void about() {
		render();
	}

	public static void posts() {
		render();
	}

	public static void search() {

	}
	
	public static void image(String imageName) {
		File file = new File(Constants.imagesPath + imageName);
		renderBinary(file);
	}
}