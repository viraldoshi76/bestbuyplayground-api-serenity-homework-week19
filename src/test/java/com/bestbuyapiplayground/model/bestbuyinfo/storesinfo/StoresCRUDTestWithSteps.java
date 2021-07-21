package com.bestbuyapiplayground.model.bestbuyinfo.storesinfo;

import com.bestbuyapiplayground.model.bestbuyinfo.StoresSteps;
import com.bestbuyapiplayground.model.constants.EndPoints;
import com.bestbuyapiplayground.model.constants.Path;
import com.bestbuyapiplayground.model.model.StoresPojo;
import com.bestbuyapiplayground.model.testbase.TestBase;
import com.bestbuyapiplayground.model.utils.TestUtils;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
public class StoresCRUDTestWithSteps extends TestBase {

    static String name = "Innerpartical" + TestUtils.getRandomValue();
    static String type = "Range"+ TestUtils.getRandomValue();
    static String address = TestUtils.getRandomValue()+ " 16th Road F";
    static String address2 = "";
    static String city = "Innerpartical" + TestUtils.getRandomValue();
    static String state = "TU"+TestUtils.getRandomValue();
    static String zip = "63" + TestUtils.getRandomIntegerValue();
    static double lat = 56.93 + TestUtils.getRandomIntegerValue();
    static double lng = -87.53 + TestUtils.getRandomIntegerValue();
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    @Steps
    StoresSteps storesSteps;

    @Title("creating and verify the data added to store application")
    @Test
    public void test001(){
        HashMap<String,Object> pvalue = storesSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours)
                .log().all().statusCode(201).extract().response().body().jsonPath().get();
        System.out.println(pvalue);
        assertThat(pvalue,hasValue(name));
        storeId = (int) pvalue.get("id");
        System.out.println(storeId);

    }

    @Title("Update and verify the data in store application")
    @Test
    public void test002(){
        name = name + "_Updated";

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("storeId",storeId)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)
                .then().log().all().statusCode(200);

        HashMap<String,Object> pvalue =
                SerenityRest.rest().given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_STORES)
                        .then()
                        .statusCode(200)
                        .extract().response().body().jsonPath().get();
        assertThat(pvalue,hasValue(name));
    }

    @Title("Deleting and verify the product is deleted")
    @Test
    public void test003(){
        SerenityRest.rest().given().log().all()
                .pathParam("storeId",storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then()
                .statusCode(200);

        SerenityRest.rest().given().log().all()
                .pathParam("storeId",storeId)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(404);
    }
}
