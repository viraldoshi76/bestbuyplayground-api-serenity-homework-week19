package com.bestbuyapiplayground.model.bestbuyinfo;

import com.bestbuyapiplayground.model.constants.EndPoints;
import com.bestbuyapiplayground.model.model.StoresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoresSteps {

    @Step("Creating new Store with name: {0},type: {1},address: {2},address2: {3},city: {4},state: {5},zip: {6},lat: {7},lng: {8},hours: {9}")
    public ValidatableResponse createStore(String name,String type,String address,String address2,String city,String state,String zip,double lat,double lng,String hours){

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

        return (ValidatableResponse) SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(storesPojo)
                .when()
                .post(EndPoints.CREATE_NEW_STORE)
                .then();

    }
}
