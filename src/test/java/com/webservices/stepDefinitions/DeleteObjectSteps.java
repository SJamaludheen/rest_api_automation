package com.webservices.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import utils.ResponseContainer;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.is;
import static com.webservices.model.Setup.requestSpec;
import static httpfactory.URLFactory.*;
import static io.restassured.RestAssured.given;
import static utils.Config.schemaFile;
import static utils.ObjectId.getObjectId;
import static utils.RandomGen.randomNumberString;

public class DeleteObjectSteps {
    private static final String SCHEMA = schemaFile(DeleteObjectSteps.class.getSimpleName());
    private ValidatableResponse deleteObjectResponse;
    String randomNumber;

    @When("^a request to delete the item is made$")
    public void aDeleteRequestIsMade() {
        deleteObjectResponse = given()
                .spec(requestSpec)
                .when()
                .delete(deleteObject(getObjectId()))
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(SCHEMA));
        ResponseContainer.getInstance().setDataMap("DELETE_RESPONSE", deleteObjectResponse);
    }

    @When("^a request to delete an invalid item is made$")
    public void anInvalidItemToBeDeleted() {
        randomNumber = randomNumberString(1000, 2000);
        deleteObjectResponse = given()
                .spec(requestSpec)
                .when()
                .delete(deleteObject(randomNumber))
                .then()
                .statusCode(404);
        ResponseContainer.getInstance().setDataMap("DELETE_RESPONSE", deleteObjectResponse);
    }

    @And("^an error message \"(.*)\" is returned from the DELETE endpoint$")
    public void anErrorMessageIsReturnedFromTheDeleteEndpoint(String errorMessage) {
        errorMessage = errorMessage.replace("#", "= " + randomNumber);
        deleteObjectResponse.body("error", is(errorMessage));
    }

    @Then("^a (\\d+) response code is returned from the DELETE endpoint$")
    public void aResponseCodeIsReturnedFromTheDeleteEndpoint(int statusCode) {
        deleteObjectResponse.statusCode(statusCode);
    }

    @And("^a message \"(.*)\" is returned from the DELETE endpoint$")
    public void aMessageIsReturnedFromTheDeleteEndpoint(String message) {
        message = message.replace("#", "= " + getObjectId());
        deleteObjectResponse.body("message", is(message));
    }
}