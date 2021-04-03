package com.project.things.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.h2.util.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Logic {
    private Date getDateByFormat(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = format.parse(date);

        return formatDate;

    }

    private String getStringByDate(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private List<Date> getDatesToNowDate(String sinceDate) throws ParseException {
        List<Date> dates = new ArrayList<>();
        Date nowDate = new Date();
        Date tmpDate = getDateByFormat(sinceDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tmpDate);

        while (tmpDate.before(nowDate)){
            dates.add(tmpDate);
            calendar.add(Calendar.DATE, 1);
            tmpDate = calendar.getTime();
        }
        return dates;
    }

    private LinkedHashMap<String, Object> getHttpGETRequest(String URL){
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final HttpUriRequest httpGet = new HttpGet(URL);
        try{
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            final HttpEntity entity1 = response1.getEntity();
            String result = EntityUtils.toString(entity1);
            return  getValueFromJson(result);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }
    private BigDecimal getUSDValue(LinkedHashMap<String, Object> request) throws org.apache.tomcat.util.json.ParseException {
        var objects = (LinkedHashMap<String, Object>)request.get("rates");
        return (BigDecimal) objects.get("USD");

    }

    private LinkedHashMap<String, Object> getValueFromJson(String input) throws org.apache.tomcat.util.json.ParseException {
        JSONParser parser = new JSONParser(input);
        return  parser.parseObject();
    }

    public BigDecimal getUSDValueByDate(String date) throws ParseException, org.apache.tomcat.util.json.ParseException {
        LinkedHashMap<String, Object> httpGETRequest = getHttpGETRequest(String.format("http://api.exchangeratesapi.io/v1/%s?access_key=c3eeb24b3445c308f65427d6599f24ec", date));
        return getUSDValue(httpGETRequest);
    }

    public List<BigDecimal> getUSDHistoryValues(String since) throws ParseException, org.apache.tomcat.util.json.ParseException {
        List<Date> dates = getDatesToNowDate(since);
        List<BigDecimal> usdValues = new ArrayList<>();
        for (var date: dates) {
            usdValues.add(getUSDValueByDate(getStringByDate(date)));
        }
        return usdValues;
    }

    public double[] getTgByValues(List<BigDecimal> values){
        double[] tgValues = new double[values.size()];
        for (int i=0; i<values.size(); i++){
            tgValues[i] = Math.pow(Math.tan(values.get(i).doubleValue()), 7.5);
        }
        return  tgValues;
    }

    public double getVariance(double[] values){
        StatisticsLogic statisticsLogic = new StatisticsLogic(values);
        return statisticsLogic.getVariance();
    }
}
