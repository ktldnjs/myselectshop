package com.siwon.myselectshop.utils;

import com.siwon.myselectshop.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "4mmKW0gcmBrhbbI7_nTd");
        headers.add("X-Naver-Client-Secret", "Onhs6izL4k");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public static void main(String[] args) {
        NaverShopSearch naverShopSearch = new NaverShopSearch();
        String result = naverShopSearch.search("아이맥");
        JSONObject rjson = new JSONObject(result);
        // System.out.println(rjson);
        JSONArray items = rjson.getJSONArray("items"); // rjson 에서 JSONArray를 꺼내겠다는 뜻. JSON으로 이루어진 배열을.(items가 []리스트형태로 되어있음)
        // 키 값은 items라고 적혀있으니 items라 적는것.
        // 변수명(items)의 형태는 JSONArray임.

        List<ItemDto> itemDtoList = new ArrayList<>();
        for (int i=0; i<items.length(); i++){
            JSONObject itemJson = items.getJSONObject(i);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;
    }
}

