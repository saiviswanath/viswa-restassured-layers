package com.gorest.app.flows;

import java.util.List;

import com.framework.core.api.restclient.ResponseBodyParser;
import com.framework.core.api.restclient.ResponseFetcher;
import com.framework.core.asserts.Asserts;
import com.gorest.app.pojo.UserPOJO;

import io.restassured.http.Headers;

public class GoRestValidation {

    public static void validateConTentType(ResponseFetcher response){
        Asserts.assertEquals(response.getHeader("Content-Type"),"application/json; charset=utf-8","Validate Content-Type");
    }

    public static void validatePaginationHeaders(ResponseFetcher response){
        Headers responseHeaders = response.getHeaders();
        Asserts.assertEquals(response.getHeader("x-pagination-page"),"1","Validate Response Header - x-pagination-page");
        Asserts.assertTrue(responseHeaders.hasHeaderWithName("x-pagination-page"),"Validate header x-pagination-page exist");
        Asserts.assertTrue(responseHeaders.hasHeaderWithName("x-pagination-limit"),"Validate header x-pagination-limit exist");
        Asserts.assertTrue(responseHeaders.hasHeaderWithName("x-pagination-total"),"Validate header x-pagination-total exist");
        Asserts.assertTrue(responseHeaders.hasHeaderWithName("x-pagination-pages"),"Validate header x-pagination-total exist");
    }
    public static void validatePaginationHeadersNotPresent(ResponseFetcher response){
        Headers responseHeaders = response.getHeaders();
        Asserts.assertFalse(responseHeaders.hasHeaderWithName("x-pagination-page"),"Validate header x-pagination-page is not present");
        Asserts.assertFalse(responseHeaders.hasHeaderWithName("x-pagination-limit"),"Validate header x-pagination-limit is not present");
        Asserts.assertFalse(responseHeaders.hasHeaderWithName("x-pagination-total"),"Validate header x-pagination-total is not present");
        Asserts.assertFalse(responseHeaders.hasHeaderWithName("x-pagination-pages"),"Validate header x-pagination-pages is not present");
    }
    public static void validateUserFieldsResponseBody(ResponseBodyParser responseBodyParser){
        Asserts.assertTrue(responseBodyParser.get("id").toString().length() > 0,"Validate response body has id field");
        Asserts.assertTrue(responseBodyParser.get("name").toString().length() > 0,"Validate response body has name field");
        Asserts.assertTrue(responseBodyParser.get("email").toString().length() > 0,"Validate response body has email field");
        Asserts.assertTrue(responseBodyParser.get("gender").toString().length() > 0,"Validate response body has gender field");
        Asserts.assertTrue(responseBodyParser.get("status").toString().length() > 0,"Validate response body has status");
    }
    
    public static void validateUserFieldsResponseBodyClazz(List<UserPOJO> resp){
        Asserts.assertTrue(resp.size() > 0,"Validate response body array size");
        Asserts.assertTrue(resp.get(0).getName().length() > 0,"Validate response body has name field");
        Asserts.assertTrue(resp.get(0).getEmail().length() > 0,"Validate response body has email field");
        Asserts.assertTrue(resp.get(0).getGender().length() > 0,"Validate response body has gender field");
        Asserts.assertTrue(resp.get(0).getStatus().length() > 0,"Validate response body has status");
    }
}
