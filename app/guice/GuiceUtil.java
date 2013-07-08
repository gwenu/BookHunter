package guice;

import play.modules.guice.GuiceSupport;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceUtil extends GuiceSupport {

	protected Injector configure() {
		return Guice.createInjector(new BookHunterGuiceModule());
	}
}
