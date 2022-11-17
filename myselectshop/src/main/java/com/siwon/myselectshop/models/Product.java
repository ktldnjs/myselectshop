package com.siwon.myselectshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자 만들어줌.
@Entity // 테이블이 자동 생성되도록.
@Getter // private 로 해줬으니까 Getter 설정.
public class Product extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice; // 최저가

    @Column(nullable = false)
    private int myprice;

    public Product(ProductRequestDto productRequestDto) {
        this.title = productRequestDto.getTitle();
        this.link = productRequestDto.getLink();
        this.image = productRequestDto.getImage();
        this.lprice = productRequestDto.getLprice();
        this.myprice = 0;
    }

    public void update(ProductMypriceRequestDto productMypriceRequestDto){
        this.myprice = productMypriceRequestDto.getMyprice();
    }

    public void updateByItemDto(ItemDto itemDto){
        this.lprice = itemDto.getLprice();
    }



}
