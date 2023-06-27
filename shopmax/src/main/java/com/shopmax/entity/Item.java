package com.shopmax.entity;

import java.time.LocalDateTime;

import com.shopmax.constant.ItemSellStatus;

import jakarta.persistence.*; //임포트 많을 때 같은 애들 *로 묶어서 정리~
import lombok.*;

@Entity //엔티티 클래스로 정의. 꼭 있어야 함. 
@Table(name="item") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Item {
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
	@Column(nullable = false)
	private String itemDetail; //상품상세설명
	
	@Enumerated(EnumType.STRING) //enum의 이름을 DB에 저장
	private ItemSellStatus itemSellStatus; //판매상태(sell,sold out)
	
	private LocalDateTime regTime; //등록시간
	private LocalDateTime updateTime; //수정시간
}
