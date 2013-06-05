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

	public static void search(String searchKey) {

		Books.books(searchKey);
	}
	
	public static void image(String imageName) {
		File file = new File(Constants.IMAGE_PATH + imageName);
		renderBinary(file);
	}
}