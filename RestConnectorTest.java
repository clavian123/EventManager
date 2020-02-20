package com.pikachugo.RestManager.connector;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pikachugo.RestManager.util.ConstructDataAsliRI;
import com.pikachugo.RestManager.util.ConstructDataDUKCAPIL;
import com.pikachugo.RestManager.util.ConstructDataLiveCheck;
import com.pikachugo.RestManager.util.ConstructDataNegativeList;
import com.pikachugo.RestManager.util.MySSLSocketFactory;

public class RestConnectorTest {
	private static Logger log = Logger.getLogger(RestConnector.class);
	public static final String URI_NEGATIVE_LIST = "http://10.32.1.17/PegaAPI/api/ws/NegativeList";
	public static final String URI_VERIFY_SELFIE = "https://10.32.1.202/labs/sb/kyc-service/asliri-selfie-verification";
	public static final String URI_DUKCAPIL_GET_NIK = "http://10.22.11.37:8080/umg/getNIK";
	public static final String URI_LIVENESS_CHECK = "https://img.smma.co.id:8002/detect";
	public static final String URI_Gian = "https://sandbox.banksinarmas.com/labs/sb/alfacart/auth";
	
	public static ResponseEntity sendRequest(Map dataMap, String uri, HttpMethod requestMethod) throws Exception {
		
		HttpEntity requestEntity = null;
		ResponseEntity<Map> responseEntity = null;
		String requestBody=null;
		Map requestMap = new HashMap();
		
		//Configure rest template
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		String timeoutString = "30000"; //Hard code 30000 for development testing only
		requestFactory.setHttpClient(getNewHttpClient());
		log.debug("timeoutString :"+timeoutString);
		requestFactory.setReadTimeout(30000);
	    requestFactory.setConnectTimeout(30000);
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		try {
		    //Set body (Re-mapping request dataMap here)
			requestMap = constructRequestMap(uri, dataMap);
			log.debug("requestMap : "+requestMap);
			
			//Set http entity
			requestEntity = setEntityRequest(requestMap, uri);
			log.debug("cek jar : "+requestEntity.getBody());
			log.debug("cek jar : "+requestEntity.getHeaders());
		    
		    //Execute
		    try {
		    	log.debug("Request data : " + requestBody);
		    	responseEntity = restTemplate.exchange(uri, requestMethod, requestEntity, Map.class);
		    	log.debug("Response data : "+responseEntity.getBody());
			} catch (Exception e) {
		    	log.error("Response data Exception : "+e.getMessage(), e);
		    	throw new Exception(e.getMessage());
			}
		} catch (Exception e) {
			log.error("Fail send request with error message : " + e.getMessage(), e);
			throw new Exception(e.getMessage());
		}		
		log.debug("berhasil sampe return");
		return responseEntity;
	}
	
	public static Map constructRequestMap(String uri, Map dataMap) {
		Map returnMap = new HashMap();
		if(URI_NEGATIVE_LIST.equals(uri)) {
			returnMap = ConstructDataNegativeList.constructRequestDataNegativeList(dataMap);
		}else if(URI_DUKCAPIL_GET_NIK.equals(uri)) {
			returnMap = ConstructDataDUKCAPIL.constructRequestDataDUKCAPIL(dataMap);
		}else if(URI_VERIFY_SELFIE.equals(uri)) {
			returnMap = ConstructDataAsliRI.constructRequestDataAsliRI(dataMap);
		}else if(URI_LIVENESS_CHECK.equals(uri)) {
			returnMap = ConstructDataLiveCheck.constructRequestDataLiveCheck(dataMap);
		} else {
			returnMap.put("partnerCode", "SM02");
		}
		return returnMap;
	}
	
	/**
	 * 
	 * @param uri Third party API URI
	 * @param httpType (1,2)
	 * @see <b>httpType</b> documentation :
	 * <br>1: Application-json 
	 * <br>2: Multipart form data
	 * @return httpType value as a String
	 */
	public static String checkEntityType(String uri, String httpType) {
		if(URI_LIVENESS_CHECK.equals(uri)) {
			httpType = "2";
			return httpType;
		}else {
			return httpType;
		}
	}
	
	public static HttpHeaders checkUriForHeaders(String uri) {
		HttpHeaders returnMap = new HttpHeaders();
		if(URI_NEGATIVE_LIST.equals(uri)) {
			returnMap = ConstructDataNegativeList.constructRequestHeaderNegativeList(uri);
		}else if(URI_DUKCAPIL_GET_NIK.equals(uri)) {
			returnMap = ConstructDataDUKCAPIL.constructRequestHeaderDUKCAPIL(uri);
		}else if(URI_VERIFY_SELFIE.equals(uri)) {
			returnMap = ConstructDataAsliRI.constructRequestHeaderAsliRI(uri);
		}else if(URI_LIVENESS_CHECK.equals(uri)) {
			returnMap = ConstructDataLiveCheck.constructRequestHeaderLiveCheck(uri);
		}else {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("x-ibm-client-id", "66112868-48b0-4a36-ada4-efff65accc40"); //masih ditanyakan ke umg ini maksudnya apa
			requestHeaders.add("Content-Type", "application/json");
			requestHeaders.add("x-alfa-requestTime", "202001081005018");
			requestHeaders.add("x-alfa-requestId", "BSIM-123");
			requestHeaders.add("x-api-key", "bte0J9PwV213n38xt4gEV2vET0K1KPHkfJo3t588");
			return requestHeaders;
		}
		return returnMap;
	}
	
	public static HttpEntity setEntityRequest(Map dataMap, String uri) {
		HttpEntity requestEntity = null;
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders = checkUriForHeaders(uri);
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(dataMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return requestEntity = new HttpEntity(jsonString, requestHeaders);
	}
	
	public static void testAPIAsliRI() {
		Map dataMap = new HashMap();
		dataMap.put("nik", "");
		dataMap.put("selfie_photo", "");
		try {
			ResponseEntity responseEntity = sendRequest(dataMap, URI_VERIFY_SELFIE, HttpMethod.POST);
			log.debug("responseEntity : "+responseEntity.getBody());
		} catch (Exception e) {
			log.debug("Fail verify selfie with error message : "+e.getMessage());
		}
	}
	
	public static void testAPINegativeList() {
		Map dataMap = new HashMap();
		dataMap.put("NIK", "3320015510720001");
		try {
			ResponseEntity responseEntity = sendRequest(dataMap, URI_NEGATIVE_LIST, HttpMethod.POST);
			log.debug("responseEntity : "+responseEntity.getBody());
		} catch (Exception e) {
			log.debug("Fail verify selfie with error message : "+e.getMessage());
		}
	}
	
	public static void testAPILivenessCheck() {
		Map dataMap = new HashMap();
		String photo ="";
		dataMap.put("img", photo);
		try {
			ResponseEntity responseEntity = sendRequest(dataMap, URI_LIVENESS_CHECK, HttpMethod.POST);
			log.debug("responseEntity : "+responseEntity.getBody());
		} catch (Exception e) {
			log.debug("Fail verify selfie with error message : "+e.getMessage());
		}
	}
	
	public static void main(String [] args) {
//		testAPINegativeList();
		//testAPIAsliRI();
		try {
			ResponseEntity responseEntity = sendRequest(new HashMap(), URI_Gian, HttpMethod.POST);
			log.debug("responseEntity : "+responseEntity.getBody());
		} catch (Exception e) {
			log.debug("Fail verify selfie with error message : "+e.getMessage());
		}
	}
	
	public static HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
