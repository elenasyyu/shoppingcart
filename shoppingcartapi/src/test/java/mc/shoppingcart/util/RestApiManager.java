package mc.shoppingcart.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

public class RestApiManager {
	private static final TrustManager[] getDisableTrustManager() {
		return new TrustManager[] {
			new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
				
				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			}
		};
	}
	public static final Integer sendRestApiCall(CookieManager objCookieManager,
			Supplier<Boolean> objDisableSSL,
			String restURL,
			HttpMethod method,
			Consumer<HttpURLConnection> objRequestHeader,
			Supplier<String> objRequestBody,
			Consumer<List<String>> objResponse,
			Consumer<CookieManager> objResponseCookie) {
		Integer errorCode = 0;
		
		try {
			if (objDisableSSL.get()) {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null,  getDisableTrustManager(), new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			}
			
			//Set up Cookie Manager first...
			CookieHandler.setDefault(objCookieManager);
			
			// Open Http URL Connection
			URL url = new URL(restURL);
			HttpURLConnection objConnection = (HttpURLConnection) url.openConnection();
			
			// Set timeout..
			objConnection.setConnectTimeout(5000);	// TODO...
			
			// Indicate to recdeive response back
			objConnection.setDoInput(true);
			
			// Set method
			objConnection.setRequestMethod(method.toString());
			
			// Set Request Header
			objRequestHeader.accept(objConnection);
			
			// Set Request Body
			String requestBody = objRequestBody.get();
			if (!StringUtils.isEmpty(requestBody)) {
				objConnection.setDoOutput(true);
				OutputStream os = objConnection.getOutputStream();
				os.write(requestBody.getBytes("utf-8"));
			}
			
			// Check Send REST API response
			if (objConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return objConnection.getResponseCode();
			}
			
			// Process Response
			BufferedReader br = new BufferedReader(new InputStreamReader((objConnection.getInputStream())));
			List<String> outputList = new ArrayList<String>();
			String output;
			while ((output = br.readLine()) != null) {
				outputList.add(output);
			}
			br.close();
			objResponse.accept(outputList);
			
			// Process Cookie
			List<HttpCookie> cookies = objCookieManager.getCookieStore().getCookies();
			for (HttpCookie cookie : cookies) {
			}
			objResponseCookie.accept(objCookieManager);
			
			objConnection.disconnect();
			
			return HttpURLConnection.HTTP_OK;
		} catch (MalformedURLException e) {
			
		} catch (IOException e) {
			
		} catch (Exception e) {
			
		} finally {
			
		}
		
		return 0;
	}
}
