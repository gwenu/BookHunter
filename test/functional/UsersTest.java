package functional;

import models.User;

import org.junit.Before;
import org.junit.Test;

import play.modules.paginate.ModelPaginator;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;

public class UsersTest extends FunctionalTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("/mock/data/data.yml");
	}

	@Test
	public void verifyUsersPagination() {
		Response response = GET("/users");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);

		// Test retrieving users list and properties of pagination
		ModelPaginator<User> usersPaginator = (ModelPaginator<User>) renderArgs("paginatorUsers");
		assertNotNull(usersPaginator);
		assertEquals(4, usersPaginator.size());
		assertEquals(2, usersPaginator.getPageCount());
		assertEquals(2, usersPaginator.getPageSize());

		// Test order by username asc
		assertEquals("User1", usersPaginator.get(0).getUsername());
		assertEquals("User2", usersPaginator.get(1).getUsername());
		assertEquals("User3", usersPaginator.get(2).getUsername());
		assertEquals("User4", usersPaginator.get(3).getUsername());
	}

	@Test
	public void testRenderRegistrationPage() {
		Response response = GET("/registration");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);
	}

	@Test
	public void testRenderLoginPage() {
		Response response = GET("/login");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);
	}
}
