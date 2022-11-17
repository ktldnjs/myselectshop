package com.siwon.myselectshop.utils;

import com.siwon.myselectshop.models.ItemDto;
import com.siwon.myselectshop.models.Product;
import com.siwon.myselectshop.models.ProductRepository;
import com.siwon.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor // final 멤버변수를 자동으로 생성.
@Component // 스프링이 자동으로 생성해서 실행해야 하니까. // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가함.

public class Scheduler {
    private final NaverShopSearch naverShopSearch;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException {
        System.out.println("가격 업데이트 실행");
        // 저장된 모든 관심상품을 조회.
        List<Product> productList = productRepository.findAll();
        for (int i = 0; i<productList.size(); i++){
            // 1초에 한 상품 조회(naver 제한)
            TimeUnit.SECONDS.sleep(1);
            // i 번째 관심상품을 꺼낸다.
            Product product = productList.get(i);
            // i 번째 관심상품의 제목으로 검색.
            String title = product.getTitle();
            String result = naverShopSearch.search(title);
            // i 번째 관심상품의 검색결과 목록에서 첫번째 결과를 꺼냄.
            List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(result);
            ItemDto itemDto = itemDtoList.get(0);
            // i 번째 관심 상품 정보를 업데이트
            Long id = product.getId();
            productService.updateBySearch(id, itemDto);
        }
    }


}
