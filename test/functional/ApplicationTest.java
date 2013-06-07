package functional;

import java.io.File;

import models.Book;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Response;
import play.mvc.results.RenderBinary;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.executeSQL("ALTER TABLE Book ALTER COLUMN id RESTART WITH 1");
	}

	@Test
	public void testThatIndexPageWorks() {
		Response response = GET("/");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);
	}

	@Test
	public void testThatAboutPageWorks() {
		Response response = GET("/about");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);
	}

	@Test
	public void testRenderImage() {
		Response response = GET("/image?imageName=book1_front_page.jpeg&imageBig=1");
		assertIsOk(response);
		assertContentType("image/jpeg", response);

//		File imageFile = (File) renderArgs("imageFile");
//		assertNotNull(imageFile);
//		assertEquals("/home/olga/Development/books_front_page/book1_front_page.jpeg", imageFile.getAbsolutePath());
	}
}