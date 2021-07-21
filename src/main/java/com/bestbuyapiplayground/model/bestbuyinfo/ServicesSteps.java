package com.bestbuyapiplayground.model.bestbuyinfo;

import com.bestbuyapiplayground.model.constants.EndPoints;
import com.bestbuyapiplayground.model.model.ServicesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ServicesSteps {

    @Step("Creating new services with name: {0}")
    public ValidatableResponse createService(String name){

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return (ValidatableResponse) SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post(EndPoints.CREATE_NEW_SERVICE);
    }
}
