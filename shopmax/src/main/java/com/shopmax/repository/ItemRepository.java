package com.shopmax.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.shopmax.constant.ItemSellStatus;
import com.shopmax.entity.Item;
								//<해당 레파지토리에서 사용할 entity, entity클래스의 기본키 타입.>
public interface ItemRepository extends JpaRepository<Item,Long>{
	//select*from item where item_nm= ? 과 의미가 같음. 
	List<Item> findByItemNm(String itemNm); 
	
	// select *from item where item_nm = ? and item_sell_status = ?
	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);
	
	//퀴즈 1-7
	List<Item> findByPriceBetween(int price1, int price2);
	// selete * from item where price between ?1 and ?2;
	
	List<Item> findByRegTimeAfter(LocalDateTime regTime);

	List<Item> findByItemSellStatusNotNull();
	
	List<Item> findByItemDetailLike(String itemDetail);
	
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	List<Item> findByPriceLessThanOrderByPriceDesc(int price);
	
	//JPQL(non native 쿼리) > 컬럼명, 테이블명은 entity class 기준으로 작성한다. (여기서는 Item.java)
	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); //()안의 값 > 윗줄에서 :itemDetail
	
	//JPQL(native 쿼리) > h2 데이터 베이스를 기준으로 한 쿼리문 작성. 
	@Query(value="select*from item where item_detail like %:itemDetail% order by price desc",nativeQuery = true)
	List<Item>findByItemDetailByNative(@Param("itemDetail")String itemDetail);
	
	@Query("select i from Item i where i.price >= :price order by i.price desc")
	List<Item> findByItemDetailAfter(@Param("price") int price);
	
	@Query("select i from Item i where i.price >= :price order by i.price desc")
	List<Item> findByItemNm(@Param("price") int price);
	
	// itemNm이 “테스트 상품1” 이고 ItemSellStatus가 Sell인 레코드를 구하는 @Query 어노테이션을 작성하시오.
	@Query("select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :itemSellStatus")
	List<Item> findByItemNmAndItemSellStatus1(@Param("itemNm") String itemNm, @Param("itemSellStatus") ItemSellStatus itemSellStatus);
	
}
