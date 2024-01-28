package org.example;

import static io.restassured.RestAssured.*;   // IMP step
import static org.hamcrest.Matchers.*;   //IMP Step  equalTo("APP")

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.example.Files.Payload;
import org.testng.reporters.XMLConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Basics {

    // Before that You need to have a 'API contract'
    public static void main(String[] args) throws IOException {

        //Validate that AddPlace API is working correctly

        // Given - all input details,
        // When - submit   with HTTP method and Resource - IMP
        // Then - validate response


        //RequestSpecBuilder

       RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
                .addQueryParam("key","qaclick123").build();

        //Convert json file into string
        //convert data into Byte -> then Byte to string

        //given us rest assured static package

       String response =  given().spec(req)
                .body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Abhishek Mishra\\Desktop\\addPlace.json")))).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .extract().response().asString();


        System.out.println(response);

        JsonPath js = new JsonPath(response);// for parsing json and take input as string

        System.out.println(js.get("place_id"));

        String placeID = js.get("place_id");


      given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\"81 winter walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").when().put("maps/api/place/update/json")
                .then().log().all().assertThat().body("msg",equalTo("Address successfully updated"));

      // Get API


        String getResponse = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID).when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println(getResponse);
        System.out.println(getResponse);
        System.out.println(getResponse);
        System.out.println(getResponse);    
        System.out.println("Change Added");
        System.out.println("Again change by Abhi");


        // This is now up to date code.


        // Add place --> Update place ---> Get updated place and verify with old place







    }
}
