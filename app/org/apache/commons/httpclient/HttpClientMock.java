package org.apache.commons.httpclient;

import java.io.ByteArrayInputStream;

public class HttpClientMock extends HttpClient {

	public HttpClientMock() {
	}
	
	@Override
	public int executeMethod(HttpMethod method) {
		try {
			((HttpMethodBase) method).setResponseStream(new ByteArrayInputStream("505".getBytes("UTF-8")));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 200;
	}
}
