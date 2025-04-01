package com.webservices.requests;

import com.google.gson.Gson;
import jsonfactory.createObject.CreateObject;

import java.io.FileReader;
import java.io.IOException;

public class RequestBuilder {
    public CreateObject createObjectRequest() throws IOException {
        Gson gson = new Gson();
        //JSON to Java object, reading it from a file.
        return gson.fromJson(new FileReader(System.getProperty("user.dir") + "/src/test/resources/requestpayloads/CreateObject.json"), CreateObject.class);
    }

    public String objToJsonString(Object obj) {
        Gson gson = new Gson();
        //Java object to JSON
        return gson.toJson(obj);
    }
}