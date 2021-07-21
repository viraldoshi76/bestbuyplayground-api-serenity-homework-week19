package com.bestbuyapiplayground.model.testbase;

import com.bestbuyapiplayground.model.constants.Path;
import com.bestbuyapiplayground.model.utils.PropertyReader;
import io.restassured.RestAssured;
import jnr.ffi.annotations.In;
import org.junit.BeforeClass;

import java.util.Properties;

public class TestBase {

    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init(){
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));

    }
}
