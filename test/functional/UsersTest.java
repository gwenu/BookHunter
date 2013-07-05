package functional;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import models.User;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import controllers.Secure;

import play.modules.paginate.ModelPaginator;
import play.mvc.Http.Request;
import play.mvc.Http.Response;
import play.mvc.Scope;
import play.mvc.Scope.Session;
import play.test.Fixtures;
import play.test.FunctionalTest;
import play.utils.Utils.Maps;

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
	public void renderRegistrationPage() {
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
	
	@Test
	public void renderProfilePage() throws Throwable{
		Fixtures.loadModels("/mock/data/userlogin.yml");
		
		GET("/login");
		controllers.UserLogin.authenticate("User_login1","pass1");

		Response response = GET("/profile?username=User_login1");
		assertIsOk(response);
		
		User sessionUser = (User)renderArgs("user");
		assertNotNull(sessionUser);
		assertEquals("Name1", sessionUser.getFirstName());
		assertEquals("Last1", sessionUser.getLastName());
		assertEquals("User_login1", sessionUser.getUsername());
		assertEquals("Inf1", sessionUser.getUserInf());
		assertEquals("userpic1.jpg", sessionUser.getImageName());
		assertEquals(2, sessionUser.getReadBooks().size());
		assertEquals("Title_book1", sessionUser.getReadBooks().get(0).getTitle());
		assertEquals("AuthorName", sessionUser.getReadBooks().get(0).getAuthor().getFullName());
		assertEquals("Description_book1", sessionUser.getReadBooks().get(0).getDescription());
		assertEquals("1book_front_page.jpeg", sessionUser.getReadBooks().get(0).getImageName());
		assertEquals("url_test1", sessionUser.getReadBooks().get(0).getBookUrl());
		assertEquals("Title_book2", sessionUser.getReadBooks().get(1).getTitle());
		assertEquals("AuthorName", sessionUser.getReadBooks().get(1).getAuthor().getFullName());
		assertEquals("Description_book2", sessionUser.getReadBooks().get(1).getDescription());
		assertEquals("2book_front_page.jpeg", sessionUser.getReadBooks().get(1).getImageName());
		assertEquals("url_test2", sessionUser.getReadBooks().get(1).getBookUrl());
	}
	
	@Test
	public void saveUserAfterRegistration(){
		Request request = newRequest();
		request.url = "/saveUser";

		Map<String, String> paramMap = new HashMap();
		paramMap.put("username", "test_registration");
		paramMap.put("password", "12345");
		paramMap.put("confirm_pwd", "12345");
		paramMap.put("first_name", "Name_r");
		paramMap.put("last_name", "Surname_r");
		
		Map<String, File> fileMap = new HashMap<String, File>();
		fileMap.put("imageFile", new File("test/mock/data/image.jpg"));
		
		Response response = POST("/saveUser", paramMap, fileMap);
		assertStatus(302, response);
	}
}