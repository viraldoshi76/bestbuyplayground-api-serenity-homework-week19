package com.bestbuyapiplayground.model.bestbuyinfo;

import com.bestbuyapiplayground.model.constants.EndPoints;
import com.bestbuyapiplayground.model.model.ProductsPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductsSteps {

    @Step("Creating Products with name: {0}, type: {1}, price: {2},shipping: {3},upc: {4},description: {5},manufacturer: {6},model: {7},url: {8},image: {9}")
    public ValidatableResponse createProduct(String name,String type,double price,int shipping,String upc,String description,String manufacturer,String model,String url,String image){

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);

        return (ValidatableResponse) SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(productsPojo)
                .when()
                .post(EndPoints.CREATE_NEW_PRODUCT)
                .then();
    }
}
