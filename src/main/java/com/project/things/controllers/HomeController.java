package com.project.things.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.things.services.Logic;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.h2.util.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class HomeController {

    Logic logic = new Logic();

    @RequestMapping(value = "/dispersion/{since}", method = RequestMethod.GET)
    public Double getDisperion(@PathVariable(value = "since")String sinceDate) throws ParseException, org.apache.tomcat.util.json.ParseException {
        List<BigDecimal> usdHistoryValues = logic.getUSDHistoryValues(sinceDate);
        double[] tgByValues = logic.getTgByValues(usdHistoryValues);
        double dispersion = logic.getVariance(tgByValues);
        System.out.println("request");
        return dispersion;
    }

    @RequestMapping(value = "/rate/{date}", method = RequestMethod.GET)
    public BigDecimal getRate(@PathVariable(value = "date")String date) throws ParseException, org.apache.tomcat.util.json.ParseException {
        return logic.getUSDValueByDate(date);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() throws IOException {
        return null;
    }

}
