package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.repository.model.ConItems;

@Mapper
public interface StoreRepository {

	// 모든 아이템 리스트 가져오기
	List<ConItems> viewItems();

	// 현재 보유중인 콘 가져오기
	int getConCount(int userId);

	int insertConHistory(@Param("userId") int id, @Param("wConCount") int wConCount, @Param("conAmount") int amount,
			@Param("itemId") int itemId);

	void updateProfileCon(@Param("userId") int id, @Param("amount") int amount);
}
