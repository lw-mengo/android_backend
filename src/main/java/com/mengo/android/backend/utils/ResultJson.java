package com.mengo.android.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultJson {
    Map<String, String> hashMap = new LinkedHashMap<>();
    String result;
    ObjectMapper mapper = new ObjectMapper();

    public String success() {
        hashMap.put("status", "success");
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String success(String info) {
        hashMap.put("status", "success");
        hashMap.put("data", info);
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void error() {
        //TODO
    }

    public String error(String reason) {
        hashMap.put("status", "error");
        hashMap.put("reason", reason);
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
