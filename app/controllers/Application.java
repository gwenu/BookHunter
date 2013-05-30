package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	render();
    }
    
    public static void about() {
    	render();
    }
    
    public static void users() {
    	List<User> users = User.find("order by username").fetch();
    	
    	render(users);
    }
    
    public static void posts() {
    	render();
    }
    
    public static void registration() {
    	render();
    }
    
    public static void login() {
    	render();
    }
    
    public static void books() {
    	List<Book> booksList = Book.findAll();
    	render(booksList);
    }
    
    public static void search(){
    	
    }
}