package functional;

import org.junit.*;

import play.modules.paginate.ValuePaginator;
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
	public void verifyUsersPagination(){
		Response response = GET("/users");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);
		
		ValuePaginator usersPaginator = (ValuePaginator) renderArgs("paginatorUsers");
		assertNotNull(usersPaginator);
		assertEquals(3, usersPaginator.size());
		assertEquals(2, usersPaginator.getPageCount());
		assertEquals(2, usersPaginator.getPageSize());
	}
}
