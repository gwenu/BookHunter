package controllers;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.User;
import play.data.validation.Error;
import play.libs.Files;
import play.modules.paginate.ModelPaginator;
import play.mvc.Controller;
import play.mvc.Util;
import controllers.utils.Constants;

public class Users extends Controller {

	public static void users() {
		ModelPaginator<User> paginatorUsers = new ModelPaginator(User.class).orderBy("username asc");
		paginatorUsers.setPageSize(Constants.USERS_PER_PAGE);
		render(paginatorUsers);
	}

	public static void registration() {
		render();
	}
	
	public static void profile(String username){
		User user = User.find("byUsername", username).first();
		render(user);
	}

	public static void saveUser(String username, String password, String first_name, String last_name, String confirm_pwd, File user_image) {
		formValidation(username, password, first_name, last_name, confirm_pwd);
		User newUser = new User(username, password, first_name, last_name, "User inf", "").save();
		newUser.setImageName("userpic" + newUser.getId()+".jpg");
		newUser.save();
		saveUserImage(newUser, user_image);
		
	    try {
	        Secure.authenticate(username, password, false);
	    } catch(Throwable t) {
	        t.printStackTrace();
	    }
	}
	
	@Util
	private static void formValidation(String username, String password, String first_name, String last_name, String confirm_pwd) {

		validation.required(first_name);
		validation.required(last_name);
		validation.required(username);
		validation.required(password);
		validation.required(confirm_pwd);
		validation.equals(confirm_pwd, password);

		if (isCharactersOrDigits(username)) {
			validation.addError("username",
					"Please use only characters and digits!");
		}

		if (User.find("byUsername", username).first() != null) {
			validation.addError("username", "User already exist!");
		}

		validation.minSize(username, 5);
		validation.minSize(password, 4);

		if (validation.hasErrors()) {
			for (Error error : validation.errors()) {
				params.flash();
				validation.keep();
			}
			registration();
		}
	}

	@Util
	private static boolean isCharactersOrDigits(String strValue) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]$");
		Matcher matcher = pattern.matcher(strValue);

		return matcher.matches();
	}
	
	@Util
	private static void saveUserImage(User user, File userImage){
		File imagePath = new File(Constants.IMAGE_PATH + user.getImageName());
		
		if (userImage != null) {
			Files.copy(userImage, imagePath);
		} else {
			File noImagePath = new File("public/images/noUserImage.jpg");
			Files.copy(noImagePath, imagePath);
		}
	}
}
