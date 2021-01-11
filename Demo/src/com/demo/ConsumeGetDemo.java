package com.demo;


import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ConsumeGetDemo {


    public static void main(String[] args)throws Exception {
        System.out.println("start.....");
        var url = "http://10.47.1.55/postal_codes/v3/700034/seller_pincode_serviceabilities";
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
        getCsvFile(response);
    }

    public static void getCsvFile(HttpResponse response){
        System.out.println("start..json to csv");
        if(response != null){
            String resp = response.body().toString();
            try {
                File file = new File("JSONFile.csv");
                String docs= resp.replaceAll("[^a-zA-Z0-9,:]", " ");
                System.out.println(" docs :: "+docs);
                FileUtils.writeStringToFile(file, docs);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
