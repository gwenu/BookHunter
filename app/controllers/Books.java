package controllers;

import java.util.List;

import controllers.utils.Constants;

import models.Book;
import play.Play;
import play.modules.paginate.ValuePaginator;
import play.mvc.Controller;

public class Books extends Controller {

	public static void books(String searchKey) {
		List<Book> booksList = null;
		
		if(searchKey!=null && !searchKey.isEmpty()){
			booksList = Book.find("byTitleLike", "%"+ searchKey +"%").fetch();
		}else{
			booksList = Book.find("order by title").fetch();
		}
		
		ValuePaginator paginatorBooks = new ValuePaginator(booksList);
		paginatorBooks.setPageSize(Constants.BOOKS_PER_PAGE);
		
		render(paginatorBooks);
	}
	
	public static void book(String bookID){
		Long bookId = Long.parseLong(bookID);
		Book bookItem = null;
		
		if(bookID!=null){
			bookItem = Book.find("byId", bookId).first();
		}else{
			
		}
		
		render(bookItem);
	}
	
	public static void bookSearch(){
		
	}
}
