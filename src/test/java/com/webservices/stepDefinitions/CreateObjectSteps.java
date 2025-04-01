package com.webservices.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import com.webservices.requests.RequestBuilder;
import jsonfactory.createObject.CreateObject;
import utils.ResponseContainer;

import java.io.IOException;

import static com.webservices.model.Setup.requestSpec;
import static httpfactory.URLFactory.createObject;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
import static utils.Config.schemaFile;
import static utils.ObjectId.setObjectId;

public class CreateObjectSteps {
    private RequestBuilder requestBuilder = new RequestBuilder();
    private static final String SCHEMA = schemaFile(CreateObjectSteps.class.getSimpleName());
    private CreateObject createObjectRequest = new CreateObject();
    private ValidatableResponse createObjectResponse;


    @When("^a request is made to add item \"(.*)\" with CPU model as \"(.*)\" and of price (\\d+.\\d+)$")
    public void aSpecificItemIsRequestedToBeCreated(String item, String cpuModel, Double price) throws IOException {
        createObjectRequest = requestBuilder.createObjectRequest();
        setRequestData(item, cpuModel, price);
        createObjectResponse = given()
                .spec(requestSpec)
                .body(requestBuilder.objToJsonString(createObjectRequest))
                .when()
                .post(createObject())
                .then()
                .statusCode(200);
        ResponseContainer.getInstance().setDataMap("CREATE_OBJECT_RESPONSE", createObjectResponse);

        // setting the 'id' so that it can be used later in Delete or Get scenarios
        setObjectId(createObjectResponse.extract().response().getBody().path("id"));
    }

    @When("^a request to add an item is made$")
    public void aCreateRequestIsMade() throws IOException {
        createObjectRequest = requestBuilder.createObjectRequest();
        createObjectResponse = given()
                .spec(requestSpec)
                .body(requestBuilder.objToJsonString(createObjectRequest))
                .when()
                .post(createObject())
                .then()
                .statusCode(200);
        ResponseContainer.getInstance().setDataMap("CREATE_OBJECT_RESPONSE", createObjectResponse);

        // setting the 'id' so that it can be used later in Delete or Get scenarios
        setObjectId(createObjectResponse.extract().response().getBody().path("id"));
    }

    @And("^item name is (.*)$")
    public void itemNameMatches(String item) {
        createObjectResponse.body("name", equalTo(item));
    }

    @And("^is a (.*) CPU model$")
    public void isACPUModel(String cpuModel) {
        createObjectResponse.body("data['CPU model']", equalTo(cpuModel));
    }

    @And("^has a price of (\\d+.\\d+)$")
    public void hasAPriceOf(Double price) {
        createObjectResponse.body("data.price", equalTo(price.floatValue()));
    }

    @Then("^a (\\d+) response code is returned from the POST endpoint$")
    public void aResponseCodeIsReturnedFromThePOSTEndpoint(int statusCode) {
        createObjectResponse.statusCode(statusCode);
    }

    public void setRequestData(String item, String cpuModel, Double price) {
        createObjectRequest.setName(item);
        createObjectRequest.getData().setCPUModel(cpuModel);
        createObjectRequest.getData().setPrice(price);
    }

    @Then("^the item is created$")
    public void anItemIsCreated() {
        createObjectResponse.body(matchesJsonSchema(SCHEMA));
        itemNameMatches(createObjectRequest.getName());
        isACPUModel(createObjectRequest.getData().getCPUModel());
        hasAPriceOf(createObjectRequest.getData().getPrice());
    }

    @Then("^item \"(.*)\" with CPU model as \"(.*)\" and of price (\\d+.\\d+) is created$")
    public void anItemIsCreated(String item, String cpuModel, Double price) {
        createObjectResponse.body(matchesJsonSchema(SCHEMA));
        itemNameMatches(item);
        isACPUModel(cpuModel);
        hasAPriceOf(price);
    }
}