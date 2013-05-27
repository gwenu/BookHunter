package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
//    	Post frontPost = Post.find("order by date desc").first();
//        List<Post> olderPosts = Post.find(
//            "order by date desc"
//        ).from(1).fetch(10);
//        render(frontPost, olderPosts);
    }
    
    public static void about() {
    	render();
    }
    
    public static void peoples() {
    	List<User> users = User.find("order by username").fetch();
    	
    	render(users);
    }
}