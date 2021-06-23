
//https://github.com/automationrohit25/SeleniumRestTests
//https://github.com/automationrohit25/SeleniumRestTests.git
package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class CovidApiTrackerTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://api.covid19india.org";
		
		String response= given().log().all()
					       .when().get("/data.json")
						      .then().assertThat().statusCode(200)
							     .extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		
		int count = js.getInt("cases_time_series.size()");
		System.out.println(count);
		
		long sum = 0;
		
		for(int i = 0; i <count; i++){
			long totalconfirmed = js.getLong("cases_time_series["+ i + "].totalconfirmed");
			sum = sum + totalconfirmed;
		}
		System.out.println("Total number of confirmed cases " + sum);

	}

}
