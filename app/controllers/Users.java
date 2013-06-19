package controllers;

import models.User;
import play.modules.paginate.ModelPaginator;
import play.mvc.Controller;
import play.mvc.Util;
import play.mvc.With;
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
}
