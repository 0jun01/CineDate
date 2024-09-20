package com.tenco.movie.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tenco.movie.dto.UserItemInventory;
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

	// 유저 인벤토리 업데이트
	void updateUserInventory(@Param("principalId") int principalId, @Param("itemId") int itemId,
			@Param("quantity") int quantity);

	List<UserItemInventory> findInventoryByUserId(int principal);

	// 현재 보유중인 아이템 개수 가져오기
	int countItem(@Param("principal") int principal, @Param("itemId") int itemId);

	// 아이템 보유개수 차감하기!!!!!!!!!!!!!
	void updateUsedItem(@Param("principal") int principal, @Param("itemId") int itemId);

	// 슈퍼리스트에 올리기!!!!!!!!!!!!!
	void updateSuperList(int principal);
}
