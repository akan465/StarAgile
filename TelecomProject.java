package Api_Methods;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.meta.When;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TelecomProject {
	
	String tokenValue;
	String email;
	String loginToken;
	String id;
	
	@Test
	public void addnewUser() {
		Response res = given().header("Content-Type","application/json").body("{ \r\n"
						+ "\"firstName\": \"Test\", \r\n"
						+ "\"lastName\": \"User\", \r\n"
						+ "\"email\": \"abcd"+System.currentTimeMillis()+"@gmael.com\", \r\n"
						+ "\"password\": \"myPassword\" \r\n"
						+ "}").when().post("https://thinking-tester-contact-list.herokuapp.com/users");
		res.then().log().body();
		
		tokenValue = res.jsonPath().getString("token");
		System.out.println("UserToken" +tokenValue);
		System.out.println("New User created with status code "+res.statusCode());
		AssertJUnit.assertEquals(res.getStatusCode(),201); 

		
	}
	@Test(dependsOnMethods = "addnewUser")
	public void getUser() {
		Response res = given().header("Content-Type","application/json").header("Authorization","Bearer "+tokenValue)
				.when().get("https://thinking-tester-contact-list.herokuapp.com/users/me");
		res.then().log().body();
		System.out.println("Got User "+res.statusCode());
		Assert.assertEquals(res.getStatusCode(),200); 

				
	}
	
	@Test(dependsOnMethods = "addnewUser")
	public void updateUser() {
		Response res = given().header("Content-Type","application/json").header("Authorization","Bearer " +tokenValue)
				.body("{ \r\n"
						+ "\"firstName\": \"Updated\", \r\n"
						+ "\"lastName\": \"Username\", \r\n"
						+ "\"email\": \"jhj"+System.currentTimeMillis()+"@gmmael.com\", \r\n"
						+ "\"password\": \"myNewPassword\" \r\n"
						+ "} ").when().patch("https://thinking-tester-contact-list.herokuapp.com/users/me");
		res.then().log().body();
		
		tokenValue = res.jsonPath().getString("token");
		System.out.println("UserToken" +tokenValue);
		System.out.println("Firstname of user updated "+res.statusCode());
		Assert.assertEquals(res.getStatusCode(),200); 

		
	}
	
	@Test
	public void LoginUser() {
        email = "abcd" + System.currentTimeMillis() + "@gmael.com";
        
        String body = "{\n" +
                "  \"firstName\": \"Test\",\n" +
                "  \"lastName\": \"User\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"myNewPassword\"\n" +
                "}";
        Response res = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("https://thinking-tester-contact-list.herokuapp.com/users");

        res.then().log().body();
        Assert.assertEquals(res.statusCode(), 201, "User Loggeed in");
    
		loginToken = res.jsonPath().getString("token");
		System.out.println("Response Body: " + res.getBody().asString());
		System.out.println("User is logged in" +res.statusCode());//200
	}
	
	@Test(dependsOnMethods = "LoginUser")
	public void addContact() {
		email = "abcd" + System.currentTimeMillis() + "@gmael.com";
		Response res = given().header("Content-Type","application/json").header("Authorization","Bearer "+loginToken)
				.body("{ \r\n"
						+ "\"firstName\": \"Dilip\", \r\n"
						+ "\"lastName\": \"Donehill\", \r\n"
						+ "\"birthdate\": \"1970-01-01\", \r\n"
						+ "\"email\": \""+email+ "\", \r\n"
						+ "\"phone\": \"8005555555\", \r\n"
						+ "\"street1\": \"1 Main St.\", \r\n"
						+ "\"street2\": \"Apartment A\", \r\n"
						+ "\"city\": \"Anytown\", \r\n"
						+ "\"stateProvince\": \"KS\", \r\n"
						+ "\"postalCode\": \"12345\", \r\n"
						+ "\"country\": \"USA\" \r\n"
						+ "}").when().post("https://thinking-tester-contact-list.herokuapp.com/contacts");
				res.then().log().body();
		
		res.jsonPath().getString("token");
		id=res.jsonPath().getString("_id");
		System.out.println("Contact Added: "+res.statusCode());
		AssertJUnit.assertEquals(res.getStatusCode(),201); 

	}
	
	@Test(dependsOnMethods = "LoginUser")
	public void GetContactList() {
		Response res = given().header("Content-Type","application/json").header("Authorization", "Bearer "+loginToken)
				.when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
		
		List<String> contactIds = res.jsonPath().getList("contacts._id");
	    System.out.println("List of Contact IDs: " + contactIds);

		AssertJUnit.assertEquals(res.getStatusCode(),200);
		System.out.println("Status code is : " +res.statusCode());
		res.then().log().body();

	}
	
	@Test(dependsOnMethods = "LoginUser")
	public void GetContact() throws InterruptedException {
		Response res =given().header("Content-Type","application/json").header("Authorization", "Bearer "+loginToken)
			.when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
		System.out.println("Details of Contact are :" );
		Thread.sleep(2000);
		res.then().log().body();
		AssertJUnit.assertEquals(res.getStatusCode(),200); 

		
	}
	
	@Test(dependsOnMethods = "addContact")
	public void updateContact() {
		String conmail = "Duphus"+System.currentTimeMillis()+"@gmael.com";
		
		Response res = given().header("Content-Type","application/json").header("Authorization","Bearer "+loginToken)
				.body("{ \r\n"
						+ "\"firstName\": \"Amy\", \r\n"
						+ "\"lastName\": \"Hangs\", \r\n"
						+ "\"birthdate\": \"1970-01-01\", \r\n"
						+ "\"email\": \""+conmail+ "\", \r\n"
						+ "\"phone\": \"8005555555\", \r\n"
						+ "\"street1\": \"1 Main St.\", \r\n"
						+ "\"street2\": \"Apartment A\", \r\n"
						+ "\"city\": \"Anytown\", \r\n"
						+ "\"stateProvince\": \"KS\", \r\n"
						+ "\"postalCode\": \"12345\", \r\n"
						+ "\"country\": \"USA\" \r\n"
						+ "}").when().put("https://thinking-tester-contact-list.herokuapp.com/contacts/"+id);
				res.then().log().body();
		
		tokenValue = res.jsonPath().getString("token");
		System.out.println(res.getBody());
		AssertJUnit.assertEquals(res.getStatusCode(),200); 

			
	}
	
	@Test(dependsOnMethods = "addContact")
	public void updateConName() {
		
		Response res = given().header("Content-Type","application/json").header("Authorization","Bearer " +loginToken)
				.body("{ \n"
						+ "\"firstName\": \"Aditya\" \n"
						+ "\"lastName\": \"Hangs\" \r\n" ).when().patch("https://thinking-tester-contact-list.herokuapp.com/contacts/"+id);
		res.then().log().body();
		//tokenValue = res.jsonPath().getString("token");
		System.out.println(res.statusCode());
	}
	
	@Test
	public void LogoutUser() {
		Response res = given().header("Content-Type", "application/json").header("Authorization","Bearer "+loginToken)
				.when().post("https://thinking-tester-contact-list.herokuapp.com/users/logout");
		res.then().log().body();
		tokenValue = res.jsonPath().getString("token");

	}
}