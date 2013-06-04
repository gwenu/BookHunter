package controllers.utils;

import play.Play;

public class Constants {

	public static final String imagesPath = Play.configuration.getProperty("images.home.folder.path");
	public static final int books_per_page = Integer.parseInt(Play.configuration.getProperty("paginate.book.items.amount"));
	public static final int users_per_page = Integer.parseInt(Play.configuration.getProperty("paginate.user.items.amount"));
}
