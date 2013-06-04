package functional;

import java.util.List;

import models.Book;

import org.junit.*;

import play.modules.paginate.ValuePaginator;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.FunctionalTest;
import play.test.UnitTest;

public class BooksTest extends FunctionalTest {

	@Before
	public void setup() {
		Fixtures.deleteAllModels();
		Fixtures.loadModels("/mock/data/data.yml");
	}

	@Test
	public void retrieveBooksList() {
		Response response = GET("/books");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);

		ValuePaginator booksPaginator = (ValuePaginator) renderArgs("paginatorBooks");
		assertNotNull(booksPaginator);
		assertEquals(8, booksPaginator.size());
		assertEquals(2, booksPaginator.getPageCount());
		assertEquals(4, booksPaginator.getPageSize());
	}
}
