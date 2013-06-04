package controllers;

import java.util.List;

import controllers.utils.Constants;

import models.Book;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;

public class Books extends Controller {

	public static void books() {
		List<Book> booksList = Book.find("order by title").fetch();
		ValuePaginator paginatorBooks = new ValuePaginator(booksList);
		paginatorBooks.setPageSize(Constants.books_per_page);
		render(paginatorBooks);
	}
}
