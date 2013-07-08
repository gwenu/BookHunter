package guice;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.mockito.Mockito;

import play.Play;

import com.google.inject.AbstractModule;

public class BookHunterGuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		if("test".equals(Play.id)){
			HttpClient client = Mockito.mock(HttpClient.class);
			
			try {
				when(client.executeMethod(any(GetMethod.class))).thenReturn(200);
			} catch (Exception e) {e.printStackTrace();}
			
			bind(HttpClient.class).toInstance(client);
		}else{
			bind(HttpClient.class);
		}
	}
}
