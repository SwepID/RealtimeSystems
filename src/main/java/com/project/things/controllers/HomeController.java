package com.project.things.controllers;

import com.project.things.services.Logic;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@RestController
public class HomeController {

    Logic logic = new Logic();

    @RequestMapping(value = "/dispersion/{since}", method = RequestMethod.GET)
    public Double getDispersion(@PathVariable(value = "since")String sinceDate) throws ParseException, org.apache.tomcat.util.json.ParseException {
        List<BigDecimal> usdHistoryValues = logic.getUSDHistoryValuesByXML(sinceDate);
        double[] tgByValues = logic.getTgByValues(usdHistoryValues);
        double dispersion = logic.getVariance(tgByValues);
        return dispersion;
    }

    @RequestMapping(value = "/rate/{date}", method = RequestMethod.GET)
    public BigDecimal getRate(@PathVariable(value = "date")String date) throws ParseException, org.apache.tomcat.util.json.ParseException {

        return logic.getUSDValueByDateFromXML(date);
    }

}
