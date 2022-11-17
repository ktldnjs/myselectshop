package com.siwon.myselectshop.service;

import com.siwon.myselectshop.models.ItemDto;
import com.siwon.myselectshop.models.Product;
import com.siwon.myselectshop.models.ProductMypriceRequestDto;
import com.siwon.myselectshop.models.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional // 업데이트가 DB 관련 작업이다. DB 정보를 업데이트 해줘야한다.
    public Long update(Long id, ProductMypriceRequestDto productMypriceRequestDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        product.update(productMypriceRequestDto);
        return id;
    }

    @Transactional // DB가 업데이트 돼야한다.
    public Long updateBySearch(Long id, ItemDto itemDto){
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("아이디가 존재하지 않습니다.") // 아무거나 상관없음.
        );
        product.updateByItemDto(itemDto);
        return id;
    }
}
