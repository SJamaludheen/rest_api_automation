package com.webservices.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import utils.ResponseContainer;

import java.util.List;

import static com.webservices.model.Setup.requestSpec;
import static httpfactory.URLFactory.listAllObjects;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static utils.Config.schemaFile;

public class ListAllObjectsSteps {
    private static final String SCHEMA = schemaFile(ListAllObjectsSteps.class.getSimpleName());
    private ValidatableResponse listAllObjectsResponse;


    @When("^a request to retrieve all items is made$")
    public void aRequestToRetrieveAllItemsIsMade() {
        listAllObjectsResponse = given()
                .spec(requestSpec)
                .when()
                .get(listAllObjects())
                .then()
                .statusCode(200);
        ResponseContainer.getInstance().setDataMap("LIST_ALL_OBJECTS_RESPONSE", listAllObjectsResponse);
    }

    @Then("all items are listed")
    public void allItemsAreListed() {
        listAllObjectsResponse.body(matchesJsonSchema(SCHEMA));
        List<Object> itemsList = listAllObjectsResponse.extract().jsonPath().getList("$");
        if (itemsList != null && !itemsList.isEmpty()) {
            System.out.println("Response is a list of items.");
        } else {
            Assert.fail("Response is not a list or is empty.");
        }
    }

    @And("^a (\\d+) response code is returned from the list endpoint$")
    public void aResponseCodeIsReturnedFromTheListEndpoint(int statusCode) {
        listAllObjectsResponse.statusCode(statusCode);
    }
}