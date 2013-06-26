package controllers.utils;

import play.Play;

public class Constants {

	public static final String IMAGE_PATH = Play.configuration.getProperty("images.home.folder.path");
	public static final int BOOKS_PER_PAGE = Integer.parseInt(Play.configuration.getProperty("paginate.book.items.amount"));
	public static final int USERS_PER_PAGE = Integer.parseInt(Play.configuration.getProperty("paginate.user.items.amount"));
	public static final String ALLOWED_DOMAIN = Play.configuration.getProperty("allowed.domain");;
}
