package com.example.clickhouseDemo.service;

import com.example.clickhouseDemo.dto.RestaurantDto;
import com.example.clickhouseDemo.entity.Restaurant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClickhouseService {
    private final ObjectMapper objectMapper;
    private final Producer producer;

    public ClickhouseService(ObjectMapper objectMapper, Producer producer) {
        this.objectMapper = objectMapper;
        this.producer = producer;
    }

    public ResponseEntity<String> processApiData() {
        try {
            String response = ApiData();
            JsonNode items = parseJson(response).path("data");
            for (JsonNode item : items) {
                RestaurantDto restaurantDto = objectMapper.treeToValue(item, RestaurantDto.class);
                if(StringUtils.hasText(restaurantDto.getMenu())) {
                    String[] menu = parsingMenu(restaurantDto.getMenu());
                    restaurantDto.setMenu_array(menu);
                }
                Restaurant restaurant = restaurantDto.convertToEntity();
                producer.send(restaurant);
            }
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    public String ApiData() throws IOException {
        HttpURLConnection urlConnection = null;
        try {
            StringBuilder urlBuilder = new StringBuilder("https://www.daegufood.go.kr/kor/api/tasty.html");
            urlBuilder.append("?").append(URLEncoder.encode("mode", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("json", StandardCharsets.UTF_8));
            urlBuilder.append("&").append(URLEncoder.encode("addr", StandardCharsets.UTF_8)).append("=").append(URLEncoder.encode("중구", StandardCharsets.UTF_8));
            URL url = new URL(urlBuilder.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            try (InputStream stream = getNetworkConnection(urlConnection)) {
                return readStreamToString(stream);
            }
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    private InputStream getNetworkConnection(HttpURLConnection urlConnection) throws IOException {
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);
        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + urlConnection.getResponseCode());
        }
        return urlConnection.getInputStream();
    }

    private String readStreamToString(InputStream stream) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        String readLine;
        while ((readLine = br.readLine()) != null) {
            result.append(readLine).append("\n\r");
        }
        br.close();
        return result.toString();
    }

    public JsonNode parseJson(String response) throws IOException {
        return objectMapper.readTree(response);
    }

    public String[] parsingMenu(String menu) {
        Pattern pattern = Pattern.compile("\\[.*?\\]"); //일부 메뉴에 []설명 있는것 제거
        Matcher matcher = pattern.matcher(menu);
        String replaceMenu = matcher.replaceAll("").trim();
        return Arrays.stream(replaceMenu.split("\\u003Cbr /\\u003E")).map(String::trim).filter(StringUtils::hasText).toArray(String[]::new);
    }
}
