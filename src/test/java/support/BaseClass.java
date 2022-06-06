package support;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import io.restassured.RestAssured.*;

public class BaseClass {

    @BeforeClass
    public static void setUp(){
        System.out.println("entro before class");
        //RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        //RestAssured.port = "";
        RestAssured.basePath = "https://jsonplaceholder.typicode.com";
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

}
