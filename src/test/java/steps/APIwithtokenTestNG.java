package steps;


import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.*;

public class APIwithtokenTestNG {

	Playwright playwright;
	APIRequest request;
	APIRequestContext requestContext;
	
	static String emailId;
	
	
	@BeforeTest
	public void setup() {
	playwright = Playwright.create();
	request = playwright.request();
	requestContext = request.newContext();
	}
	
	public void tearDown() 
	{ 
		playwright.close(); 
	}

	public static String getRandomEmail(){
	    emailId = "testpwautomation" + System.currentTimeMillis() + "@gmail.com";
	    return emailId;
	}

	@Test
	public void getTokenTest() throws IOException {

	//String json:
		String reqTokenJsonBody = "{\n" +
			    "    \"username\" : \"admin\",\n" +
			    "    \"password\" : \"password123\"\n" +
			"}";
	
	
	//POST Call: create a token
	APIResponse apiPostTokenResponse = requestContext.post("https://restful-booker.herokuapp.com/auth",
	    RequestOptions.create()
	        .setHeader( "Content-Type", "application/json")
	        .setData(reqTokenJsonBody));

	System.out.println(apiPostTokenResponse.status());
	Assert.assertEquals(apiPostTokenResponse.status(), 200);
	Assert.assertEquals(apiPostTokenResponse.statusText(), "OK");

	System.out.println(apiPostTokenResponse.text());

	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode postJsonResponse = objectMapper.readTree(apiPostTokenResponse.body());
	System.out.println(postJsonResponse.toPrettyString());

	//capture token from the post json response
	String userId = postJsonResponse.get("token").asText();
	System.out.println("user id : " + userId);
}
}
