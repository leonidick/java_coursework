package ioconsole;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class HttpRequester {
    final static String url = "http://localhost:8080";
    final static String twarehouse = "warehouse";
    final static String tsale = "sale";
    final static String texpenseItem = "expense_item";
    final static String tcharge = "charge";

    final static String requestType_getAll = "get";
    final static String requestType_getById = "geti";
    final static String requestType_getByName = "getn";
    final static String requestType_getWhereQuantityMore = "getwqm";
    final static String requestType_getWhereQuantityLess = "getwql";
    final static String requestType_getWhereSaleQuantityMore = "getwsqm";
    final static String requestType_getWhereSaleQuantityLess = "getwsql";
    final static String requestType_getWhereAmountMore = "getwam";
    final static String requestType_getWhereAmountLess = "getwal";
    final static String requestType_add = "add";

    final static String requestType_getWhereNameStarted = "getwns";
    final static String requestType_getWhereChargeAmountMore = "getwcam";
    final static String requestType_getWhereChargeAmountLess = "getwcal";

    final static String requestType_deleteAll = "delete";
    final static String requestType_deleteById = "dbi";
    final static String requestType_deleteByName = "dbn";

    static RestTemplate restTemplate = new RestTemplate();

    static String token;

    public static void makeAuth(String login, String password) {
        try {
            String jsonString = "{\n" +
                    "    \"username\": \"" + login + "\",\n" +
                    "    \"password\": \"" + password + "\"\n" +
                    "}";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(jsonString, httpHeaders);

            ResponseEntity responseEntity = restTemplate.postForEntity(url + "/auth/login", request, String.class);

            JSONObject jsonObject = new JSONObject(responseEntity.getBody().toString());
            token = jsonObject.getString("token");

            System.out.println(responseEntity.getBody());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void makeRequest(String requestTable, String requestType, String frequest, String json_body) {
        try {
            String request = url + "/" + requestTable + "/" + frequest;
            switch (requestType) {
                case requestType_add -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    if (token != null && !token.isEmpty()) {
                        httpHeaders.setBearerAuth(token);
                    }

                    HttpEntity<String> rqst = new HttpEntity<>(json_body, httpHeaders);

                    ResponseEntity responseEntity = restTemplate.postForEntity(request, rqst, String.class);
                }
                case
                        requestType_getAll,
                                requestType_getById,
                                requestType_getByName,
                                requestType_getWhereQuantityMore,
                                requestType_getWhereQuantityLess,
                                requestType_getWhereSaleQuantityMore,
                                requestType_getWhereSaleQuantityLess,
                                requestType_getWhereChargeAmountLess,
                                requestType_getWhereChargeAmountMore,
                                requestType_getWhereAmountLess,
                                requestType_getWhereAmountMore,
                                requestType_getWhereNameStarted
                        -> {
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(request, String.class);

                    JSONArray jsonArray = new JSONArray(responseEntity.getBody());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        System.out.println(jsonArray.getJSONObject(i));
                    }
                }
                case
                        requestType_deleteAll,
                                requestType_deleteById,
                                requestType_deleteByName
                        -> {
                    HttpHeaders httpHeaders = new HttpHeaders();

                    if (token != null && !token.isEmpty()) {
                        httpHeaders.setBearerAuth(token);
                    }
                    HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
                    restTemplate.exchange(request, HttpMethod.DELETE, httpEntity, String.class);
                }

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
