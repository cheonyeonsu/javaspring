package com.shopmax.entity;

import java.time.LocalDateTime;

import com.shopmax.constant.ItemSellStatus;
import com.shopmax.dto.ItemFormDto;
import com.shopmax.exception.OutOfStockException;

import jakarta.persistence.*; //임포트 많을 때 같은 애들 *로 묶어서 정리~
import lombok.*;

@Entity //엔티티 클래스로 정의. 꼭 있어야 함. 
@Table(name="item") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Item extends BaseEntity {
	@Id
	@Column(name="item_id") //테이블로 생성될 때 컬럼이름을 지정해준다. 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	//값 어떻게 생성할건지 니가 정해.> 기본키 자동으로 지정해주는 전략 사용. 
	private Long id; //상품 코드[프라이머리키]
	
	@Column(nullable = false,length = 50) //not null여부, 컬럼 크기 지정. 
	private String itemNm; //상품명
	
	@Column(nullable = false)
	private int price; //가격
	
	@Column(nullable = false)
	private int stockNumber; //재고수량
	
	@Lob
	@Column(nullable = false, columnDefinition = "longtext")
	private String itemDetail; //상품상세설명
	
	@Enumerated(EnumType.STRING) //enum의 이름을 DB에 저장
	private ItemSellStatus itemSellStatus; //판매상태(sell,sold out)
	
	//item 엔티티 수정
	public void updateItem(ItemFormDto itemFormDto) {
		this.itemNm = itemFormDto.getItemNm();	
		this.price = itemFormDto.getPrice();
		this.stockNumber = itemFormDto.getStockNumber();
		this.itemDetail = itemFormDto.getItemDetail();
		this.itemSellStatus = itemFormDto.getItemSellStatus();
		
	}
	
	//재고를 감소시킨다.
	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber - stockNumber; //남은 재고 수량
		
		if(restStock < 0) {
			throw new OutOfStockException ("상품의 재고가 부족합니다." + "현재 재고수량: " + this.stockNumber);
		}
		this.stockNumber = restStock; //남은 재고수량 반영
	}
	
}
