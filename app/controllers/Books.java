package controllers;

import java.io.File;

import models.Book;
import play.modules.paginate.ModelPaginator;
import play.mvc.Controller;
import controllers.utils.Constants;

public class Books extends Controller {

	public static void books(String searchKey) {
		ModelPaginator<Book> paginatorBooks = null;
		
		if(searchKey!=null && !searchKey.isEmpty()){
			paginatorBooks = new ModelPaginator(Book.class, "LOWER(title) LIKE '%"+ searchKey.toLowerCase() +"%'");
		}else{
			paginatorBooks = new ModelPaginator(Book.class).orderBy("title desc");
		}
		
		paginatorBooks.setPageSize(Constants.BOOKS_PER_PAGE);
		render(paginatorBooks);
	}
	
	public static void book(long id){
		Book bookItem = Book.find("byId", id).first();

		bookItem.getUsers().size();
		render(bookItem);
	}
}
