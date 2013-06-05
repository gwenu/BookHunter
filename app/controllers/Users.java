package controllers;

import java.util.List;

import controllers.utils.Constants;

import models.User;
import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;

public class Users extends Controller {

	public static void users() {
		List<User> users = User.find("order by username").fetch();
		ValuePaginator paginatorUsers = new ValuePaginator(users);
		paginatorUsers.setPageSize(Constants.USERS_PER_PAGE);
		render(paginatorUsers);
	}

	public static void registration() {
		render();
	}

	public static void login() {
		render();
	}
}
