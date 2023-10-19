package com.example.demo.functions;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;


public class ResponseHandler extends ResponseEntity {


    public ResponseHandler(Object object, int httpStatus, String Error_message) {
        super(ObjectTemplate(object, Error_message), HttpStatusCode.valueOf(httpStatus));
    }
    

    public ResponseHandler(int httpStatus, String Error_message) {
        super(ObjectTemplate(null, Error_message) ,HttpStatusCode.valueOf(httpStatus));
    }
    

    public ResponseHandler(Object object, int httpStatus, String titleHeader,String valueHeader, String Error_message) {
        super(ObjectTemplate(object, Error_message), ResponseHandler.build_MultiValueMap(titleHeader, valueHeader) , HttpStatusCode.valueOf(httpStatus));
    }
    

    public ResponseHandler(Object object, int httpStatus, Map<String, String> map, String Error_message) {
        super(ObjectTemplate(object, Error_message), ResponseHandler.buildSome_MultiValueMap(map) , HttpStatusCode.valueOf(httpStatus));
    }
    

    public static MultiValueMap build_MultiValueMap(String titleHeader,String valueHeader){
        MultiValueMap
        multiValueMap = new HttpHeaders();
        multiValueMap.set(titleHeader, valueHeader);
        return multiValueMap;
    }


    public static MultiValueMap buildSome_MultiValueMap(Map<String, String > map){
        MultiValueMap multiValueMap = new HttpHeaders();
        for (String key : map.keySet()) {
            multiValueMap.set(key, map.get(key));
        }
        return multiValueMap;
    }


    public static Map ObjectTemplate (Object data, String Error_message){
        Map map = new HashMap<>();
        map.put("data", data);
        map.put("error_message", Error_message);
        return map;
    }


}
