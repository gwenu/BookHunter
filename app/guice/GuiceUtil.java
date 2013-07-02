package guice;

import org.apache.commons.httpclient.HttpClient;

import play.modules.guice.GuiceSupport;
import com.google.inject.*;

public class GuiceUtil extends GuiceSupport {
	
    protected Injector configure() {
        return
        Guice.createInjector(
        		new BookHunterGuiceModule()
        );
     }
}
