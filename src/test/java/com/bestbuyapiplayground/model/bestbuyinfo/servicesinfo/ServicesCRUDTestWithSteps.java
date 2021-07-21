package com.bestbuyapiplayground.model.bestbuyinfo.servicesinfo;

import com.bestbuyapiplayground.model.bestbuyinfo.CategoriesSteps;
import com.bestbuyapiplayground.model.bestbuyinfo.ServicesSteps;
import com.bestbuyapiplayground.model.constants.EndPoints;
import com.bestbuyapiplayground.model.constants.Path;
import com.bestbuyapiplayground.model.model.CategoriesPojo;
import com.bestbuyapiplayground.model.model.ServicesPojo;
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
public class ServicesCRUDTestWithSteps extends TestBase {

    static String name = "javaandcsharp" + TestUtils.getRandomValue();
    static String servicesId;

    @Steps
    ServicesSteps servicesSteps;

    @Title("creating and verify the category data to an application")
    @Test
    public void test001(){

        HashMap<String,Object> pvalue = servicesSteps.createService(name)
                .log().all().statusCode(201).extract().response().body().jsonPath().get();

        System.out.println(pvalue);
        assertThat(pvalue,hasValue(name));
        servicesId = (String) pvalue.get("id");
        System.out.println(servicesId);
    }

    @Title("Update and verify the data in category application")
    @Test
    public void test002(){

        name = name + "_Updated";

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("servicesId",servicesId)
                .body(servicesPojo)
                .when()
                .put(EndPoints.UPDATE_SERVICE_BY_ID)
                .then()
                .log().all().statusCode(200);

        HashMap<String,Object> pvalue =
                SerenityRest.rest().given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_SERVICES)
                        .then()
                        .log().all().statusCode(200)
                        .extract().response().body().jsonPath().get();
        assertThat(pvalue,hasValue(name));
    }

    @Title("Deleting and verify the product is deleted")
    @Test
    public void test003(){
        SerenityRest.rest().given().log().all()
                .pathParam("servicesId",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then()
                .statusCode(200);

        SerenityRest.rest().given().log().all()
                .pathParam("servicesId",servicesId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then()
                .statusCode(404);
    }
}
