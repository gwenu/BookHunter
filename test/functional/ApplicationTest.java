package functional;

import org.junit.Before;
import org.junit.Test;

import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
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
		Response response = GET("/image?imageName=test1.jpeg&imageBig=1");
		assertIsOk(response);
		assertContentType("image/jpeg", response);
	}
}