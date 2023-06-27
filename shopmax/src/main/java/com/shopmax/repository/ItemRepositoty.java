package com.shopmax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmax.entity.Item;
								//<해당 레파지토리에서 사용할 entity, entity클래스의 기본키 타입.>
public interface ItemRepository extends JpaRepository<Item,Long> {

}
