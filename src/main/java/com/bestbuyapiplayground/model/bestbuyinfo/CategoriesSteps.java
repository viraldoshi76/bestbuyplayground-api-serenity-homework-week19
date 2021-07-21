package com.bestbuyapiplayground.model.bestbuyinfo;

import com.bestbuyapiplayground.model.constants.EndPoints;
import com.bestbuyapiplayground.model.model.CategoriesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CategoriesSteps {

    @Step("Creating new category with name: {0}")
    public ValidatableResponse createCategory(String name){

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);


        return (ValidatableResponse) SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .post(EndPoints.CREATE_NEW_CATEGORIES)
                .then();

    }

}
