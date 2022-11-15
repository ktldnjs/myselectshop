package com.siwon.myselectshop.controller;

import com.siwon.myselectshop.models.ItemDto;
import com.siwon.myselectshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // JSON으로 응답함을 선언
@RequiredArgsConstructor // final로 선언된 클래스를 자동으로 생성
public class SearchRequestController {
    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> Search(String query){
        String result = naverShopSearch.search(query);
        return naverShopSearch.fromJSONtoItems(result);
    }

}
