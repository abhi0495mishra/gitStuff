package org.example;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;   // IMP step
import static org.hamcrest.Matchers.*;   //IMP Step  equalTo("APP")

import io.restassured.path.json.JsonPath;
import org.example.Files.Payload;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicJson {

    //Add the book in the Library
    @Test(dataProvider = "getData")
    public void addBook(String aisle, String isbn){

        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type","text/plain")
                .body(Payload.addBook(aisle,isbn)).when().post("Library/Addbook.php")
                .then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(response);
        String id = js.get("ID");
        System.out.println(id);

    }







    @DataProvider
    public Object[] getData(){

        return new Object[][] {{"2926","bcd"},{"2345","qwe"}};
    }





}
