package models.test;

import java.util.List;

import models.Author;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class AuthorCRUDTests extends UnitTest {

	@Before
	public void setup(){
		Fixtures.deleteAllModels();
		Fixtures.loadModels("/mock/data/data.yml");
	}
	
	@Test
	public void createAndReadAuthor(){
		new Author("TestAuthor1","info1").save();
		
		List<Author> createdAuthor = Author.find("byFullName", "TestAuthor1").fetch();
		
		assertNotNull(createdAuthor);
		assertEquals(1, createdAuthor.size());
		assertEquals("TestAuthor1", createdAuthor.get(0).getFullName());
		assertEquals("info1", createdAuthor.get(0).getInfo());
	}
	
	@Test
	public void updateAuthor(){
		// Retrieve Author by FullName
		Author author = Author.find("byFullName", "First").first();
		assertNotNull(author);
		assertEquals("InfAboutAuthor1", author.getInfo());
		
		// Update author's info and save
		author.setInfo("update test");
		author.save();
		
		// Retrieve Author by FullName and check that obj was updated 
		Author updatedAuthor = Author.find("byFullName", "First").first();
		assertNotNull(updatedAuthor);
		assertEquals("update test", updatedAuthor.getInfo());
	}
}
