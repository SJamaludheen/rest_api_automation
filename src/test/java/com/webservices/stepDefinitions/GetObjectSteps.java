package com.webservices.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import utils.ResponseContainer;

import static com.webservices.model.Setup.requestSpec;
import static httpfactory.URLFactory.getObject;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static utils.Config.schemaFile;
import static utils.ObjectId.getObjectId;
import static utils.RandomGen.randomNumberString;

public class GetObjectSteps {
    private static final String SCHEMA = schemaFile(GetObjectSteps.class.getSimpleName());
    private ValidatableResponse getObjectResponse;
    String randomNumber;

    @When("^a request to retrieve the item is made$")
    public void aRequestToRetrieveTheItemIsMade() {
        getObjectResponse = given()
                .spec(requestSpec)
                .when()
                .get(getObject(getObjectId())) // objectId is set from the create object test step
                .then();
        ResponseContainer.getInstance().setDataMap("GET_OBJECT_RESPONSE", getObjectResponse);
    }

    @When("^a request to retrieve an invalid item is made$")
    public void aRequestToRetrieveAnInvalidItemIsMade() {
        randomNumber = randomNumberString(1000, 2000);
        getObjectResponse = given()
                .spec(requestSpec)
                .when()
                .get(getObject(randomNumber))
                .then();
        ResponseContainer.getInstance().setDataMap("GET_OBJECT_RESPONSE", getObjectResponse);
    }

    @Then("^a (\\d+) response code is returned from the GET endpoint$")
    public void aResponseCodeIsReturnedFromTheGETEndpoint(int statusCode) {
        getObjectResponse.statusCode(statusCode);
    }

    @And("^an error message \"(.*)\" is returned from the GET endpoint$")
    public void anErrorMessageIsReturnedFromTheGETEndpoint(String errorMessage) {
        errorMessage = errorMessage.replace(" #", "=" + getObjectId());
        getObjectResponse.body("error", is(errorMessage));
    }

    @And("^the details \"(.*)\", CPU model \"(.*)\" and price (\\d+.\\d+) match$")
    public void theDetailsCPUModelAndPricePriceMatch(String item, String cpuModel, Double price) {
        getObjectResponse.body(matchesJsonSchema(SCHEMA));
        getObjectResponse.body("name", equalTo(item));
        getObjectResponse.body("id", equalTo(getObjectId()));
        getObjectResponse.body("data['CPU model']", equalTo(cpuModel));
        getObjectResponse.body("data.price", equalTo(price.floatValue()));
    }
}