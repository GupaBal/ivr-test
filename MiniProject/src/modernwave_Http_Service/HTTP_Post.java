package modernwave_Http_Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;


public class HTTP_Post {
	String url;
	String type;

	public HTTP_Post(String url, String type) {
		this.url = url;
		this.type = type;
	}

	@FunctionalInterface
	public interface RequestFunction {
		Map<String, Map<String, String>> request() throws Exception;
	}

	public Map<String, Map<String, String>> request(RequestFunction a) throws Exception {
		System.out.println("[HTTP_Post] Log.info >>> return a.request();  a.request() :" + a.request());
		return a.request();
	}

	public Map<String, Map<String, String>> onRequestCustomer_Confirm(Map<String, String> param) throws Exception {
		
		try {
			return this.request(() -> {
				String requestStr = StringToHTTPString.toRequestOrderConfirmURL(param);
				return getresponseByHTTPS("checkArsOrder.jsp", requestStr);
			});
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public Map<String, Map<String, String>> onRequestOrder_Payment(Map<String, String> param) throws Exception {
		
		try {
			return this.request(() -> {
				String requestStr = StringToHTTPString.toRequestOrderConfirmURL(param);
				return getresponseByHTTPS("processArsPayment.jsp", requestStr);
			});
		} catch (Exception e) {
			throw e;
		}
		
	}

	private Map<String, Map<String, String>> getresponseByHTTPS(String requestUrl, String requestStr) throws IOException, NoSuchAlgorithmException, KeyManagementException {

		HttpsURLConnection connection = null;
		OutputStream os = null;
		
		String response = "";
		URL searchUrl = new URL(url + requestUrl);
		
		System.out.println("[HTTP_Post] Log.info requestUrl :"+url+requestUrl);
		System.out.println("[HTTP_Post] Log.info requestStr :"+requestStr);
		
		connection = (HttpsURLConnection) searchUrl.openConnection();	// Http통신을 위한 connection 객체 생성 
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; text/html; charset=euc-kr");
		connection.setRequestProperty("User-Agent", "java-client");
		
		connection.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				return false;
			}
		});
		
		// SSL setting 
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null,new TrustManager[] {new javax.net.ssl.X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		}},null);
		
		connection.setSSLSocketFactory(context.getSocketFactory());
		connection.setDoOutput(true);
		os = connection.getOutputStream();
		os.write(requestStr.getBytes("euc-kr"));
		os.flush();
		os.close();

		
		
		// 결과값 수신 
		Map<String, String> headermap = new HashMap<String, String>();
		Map<String, String> bodymap = new HashMap<String, String>();
		Map<String, Map<String, String>> responsemap = new HashMap<String, Map<String, String>>();
		InputStreamReader in = new InputStreamReader(connection.getInputStream(), "euc-kr");
		BufferedReader br = new BufferedReader(in);
		String strLine;
		while((strLine = br.readLine()) != null) {
			response = response.concat(strLine);
		}
		
		// 결과값 출력 
		response = response.replaceAll("\r", "");
		response = response.replaceAll("\n", "");
		
		Pattern p = Pattern.compile("([^=|]+)\\=([^|]+)");
		Matcher m = p.matcher(response);
		
		while(m.find()) {
			bodymap.put(m.group(1), m.group(2));
		}
		
		responsemap.put("HEADER", headermap);
		responsemap.put("BODY", bodymap);
		
		return responsemap;
	}

	

}
