package com.project.things.services;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Logic {
    private Date getDateByFormat(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date formatDate = format.parse(date);

        return formatDate;

    }

    private String getStringByDate(Date date){
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
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

    private LinkedHashMap<String, Object> getHttpGETRequestByJson(String URL){
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

    public Element getHttpGETRequestByXML(String URL){
        final CloseableHttpClient httpclient = HttpClients.createDefault();
        final HttpUriRequest httpGet = new HttpGet(URL);
        try{
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            final HttpEntity entity1 = response1.getEntity();
            String result = EntityUtils.toString(entity1);
            return  getValueFromXML(result);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

    private BigDecimal getUSDValueFromJson(LinkedHashMap<String, Object> request) throws org.apache.tomcat.util.json.ParseException {
        var objects = (LinkedHashMap<String, Object>)request.get("rates");
        return (BigDecimal) objects.get("USD");

    }

    private BigDecimal getUSDValueFromXML(Element request) throws org.apache.tomcat.util.json.ParseException {
        BigDecimal value = new BigDecimal(request.getChildNodes().item(4).getChildNodes().item(4).getChildNodes().item(0).getNodeValue().replace(",", "."));
        return value;
    }

    private LinkedHashMap<String, Object> getValueFromJson(String input) throws org.apache.tomcat.util.json.ParseException {
        JSONParser parser = new JSONParser(input);
        return  parser.parseObject();
    }

    private Element getValueFromXML(String input) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(input)));
        Element rootElement = document.getDocumentElement();
        return rootElement;
    }

    public BigDecimal getUSDValueByDateFromJson(String date) throws ParseException, org.apache.tomcat.util.json.ParseException {
        LinkedHashMap<String, Object> httpGETRequest = getHttpGETRequestByJson(String.format("http://www.cbr.ru/scripts/XML_daily.asp?date_req=%s?", date));
        return getUSDValueFromJson(httpGETRequest);
    }

    public BigDecimal getUSDValueByDateFromXML(String date) throws ParseException, org.apache.tomcat.util.json.ParseException {
        Element httpGETRequest = getHttpGETRequestByXML(String.format("http://www.cbr.ru/scripts/XML_daily.asp?date_req=%s", date.replace("-", "/")));
        return getUSDValueFromXML(httpGETRequest);
    }

    public List<BigDecimal> getUSDHistoryValuesByJson(String since) throws ParseException, org.apache.tomcat.util.json.ParseException {
        List<Date> dates = getDatesToNowDate(since);
        List<BigDecimal> usdValues = new ArrayList<>();
        for (var date: dates) {
            usdValues.add(getUSDValueByDateFromJson(getStringByDate(date)));
        }
        return usdValues;
    }

    public List<BigDecimal> getUSDHistoryValuesByXML(String since) throws ParseException, org.apache.tomcat.util.json.ParseException {
        List<Date> dates = getDatesToNowDate(since);
        List<BigDecimal> usdValues = new ArrayList<>();
        for (var date: dates) {
            usdValues.add(getUSDValueByDateFromXML(getStringByDate(date)));
        }
        return usdValues;
    }

    public double[] getTgByValues(List<BigDecimal> values){
        /*for (int i = 0; i< values.size(); i++){
            if (BigDecimal.isNaN(values[i]))
        }*/
        List<Double> tmp = new ArrayList<>();
        Double tmpValue = 0.0;
        for (int i=0; i<values.size(); i++){
            tmpValue = Math.pow(Math.tan(values.get(i).doubleValue()), 7.5);
            if (!Double.isNaN(tmpValue)){
                tmp.add(tmpValue);
            }
        }
        double[] tgValues = new double[tmp.size()];

        for (int i=0;i<tmp.size(); i++){
            tgValues[i] = tmp.get(i);
        }
        return  tgValues;
    }

    public double getVariance(double[] values){
        StatisticsLogic statisticsLogic = new StatisticsLogic(values);
        return statisticsLogic.getVariance();
    }
}
