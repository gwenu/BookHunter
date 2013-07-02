package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.inject.Inject;

import models.Author;
import models.Book;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import play.modules.paginate.ModelPaginator;
import play.mvc.Controller;
import play.mvc.Util;
import controllers.utils.Constants;

public class Books extends Controller {

	@Inject
	static HttpClient client;

	public static void books(String searchKey) {
		ModelPaginator<Book> paginatorBooks = null;

		if (searchKey != null && !searchKey.isEmpty()) {
			paginatorBooks = new ModelPaginator(Book.class, "LOWER(title) LIKE '%" + searchKey.toLowerCase() + "%'");
		} else {
			paginatorBooks = new ModelPaginator(Book.class);
		}

		paginatorBooks.setPageSize(Constants.BOOKS_PER_PAGE);
		render(paginatorBooks);
	}

	public static void book(long id) {
		Book bookItem = Book.find("byId", id).first();

		bookItem.getUsers().size();
		render(bookItem);
	}

	public static void add() {
		render();
	}

	public static void proxy(final String resourceLink) throws HttpException, IOException {
		renderHtml(getHttpResponse(resourceLink));
	}
	
	public static String getHttpResponse(String resourceLink) throws HttpException, IOException {
		GetMethod get = new GetMethod(resourceLink);
		int status = client.executeMethod(get);

		if (status != HttpStatus.SC_OK) {
			error(404, "Dump!");
		}
	
		return get.getResponseBodyAsString();
	}

	public static void saveBook(String preview_title, String preview_author, String preview_description, String preview_url, String preview_img_src) {
		Author author = new Author(preview_author, " ").save();
		Book newBook = new Book(author, preview_title, preview_description, preview_url, null).save();
		newBook.setImageName(newBook.getId() + "book_front_page.jpg");
		newBook.save();

		saveImageFromUrl(preview_img_src, newBook.getImageName());

		book(newBook.getId());
	}

	@Util
	private static void saveImageFromUrl(String imageUrl, String imagename) {
		try {
			URL url = new URL(imageUrl);
			BufferedImage image = ImageIO.read(url);
			File imagePath = new File(Constants.IMAGE_PATH + imagename);
			File bigImagePath = new File(Constants.IMAGE_PATH + "big_" + imagename);
			ImageIO.write(image, "jpg", imagePath);
			ImageIO.write(image, "jpg", bigImagePath);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
