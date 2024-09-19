package com.tenco.movie.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tenco.movie.dto.DateProfileDTO;
import com.tenco.movie.dto.profileDetailDTO;
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.ProfileRepository;
import com.tenco.movie.repository.interfaces.StoreRepository;
import com.tenco.movie.repository.model.ConItems;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DateProfileService {

	@Autowired
	private final ProfileRepository profileRepository;
	@Autowired
	private final StoreRepository storeRepository;

	@Value("${file.upload-dir}")
	private String uploadDir;

	public DateProfile searchProfile(int principalID) {

		DateProfile profile = profileRepository.searchProfile(principalID);

		return profile;
	}

	/**
	 * @author 병호 파일 업로드 구현
	 * @param principal
	 * @param signUp
	 * @return
	 */
	@Transactional
	public int createdProfile(User principal, DateProfileDTO signUp) {
		int result = 0;

		if (signUp.getMFileOne() != null && !signUp.getMFileOne().isEmpty()) {
			// 파일 업로드 로직 구현
			String[] fileNames = uploadFile(signUp.getMFileOne());
			signUp.setOneOriginFileName(fileNames[0]);
			signUp.setOneUproadFileName(fileNames[1]);
		}
		if (signUp.getMFileTwo() != null && !signUp.getMFileTwo().isEmpty()) {
			// 파일 업로드 로직 구현
			String[] fileNames = uploadFile(signUp.getMFileTwo());
			signUp.setTwoOriginFileName(fileNames[0]);
			signUp.setTwoUproadFileName(fileNames[1]);
		}

		if (signUp.getMFile3() != null && !signUp.getMFile3().isEmpty()) {
			String[] fileNames = uploadFile(signUp.getMFile3());
			signUp.setThirdOriginFileName(fileNames[1]);
		}
		if (signUp.getMFile4() != null && !signUp.getMFile4().isEmpty()) {
			String[] fileNames = uploadFile(signUp.getMFile4());
			signUp.setFourthOriginFileName(fileNames[1]);
		}
		if (signUp.getMFile5() != null && !signUp.getMFile5().isEmpty()) {
			String[] fileNames = uploadFile(signUp.getMFile5());
			signUp.setFifthOriginFileName(fileNames[1]);
		}

		result = profileRepository.createdProfile(signUp.toProfile(principal.getId()));

		if (result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	/**
	 * @author 병호 파일 이름변화
	 * @param mFile
	 * @return
	 */
	private String[] uploadFile(MultipartFile mFile) {

		// 크기
		if (mFile.getSize() > Define.MAX_FILE_SIZE) {
			throw new DataDeliveryException(Define.FILE_SIZE_EXCEEDED, HttpStatus.BAD_REQUEST);
		}
		String saveDirectory = uploadDir;

		String uploadFileName = UUID.randomUUID() + "_" + mFile.getOriginalFilename();

		String uploadPath = saveDirectory + File.separator + uploadFileName;

		File destination = new File(uploadPath);

		Path uploadPath1 = Paths.get(uploadDir);
		if (!Files.exists(uploadPath1)) {
			try {
				Files.createDirectories(uploadPath1);
			} catch (IOException e) {
				throw new DataDeliveryException(Define.UPLOAD_DIR_CREATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		try {
			mFile.transferTo(destination);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			throw new DataDeliveryException(Define.FILE_UPLOAD_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new String[] { mFile.getOriginalFilename(), uploadFileName };
	}

	/**
	 * @author 병호 닉네임 중복검사
	 * @param nickname
	 * @return
	 */
	public DateProfile searchUsername(String nickname) {

		DateProfile profile = profileRepository.searchNickName(nickname);

		return profile;
	}

	/**
	 * 마이페이지 업데이트
	 * 
	 * @author 성후
	 * @return
	 */
	@Transactional
	public void updateProfile(DateProfileDTO uptate, int principalId) throws IOException {

		DateProfile profile = searchProfile(principalId);
		System.out.println("=======================================================================");
		System.out.println(profile);
		System.out.println("=======================================================================");
		uptate.setOneOriginFileName(profile.getFirstOriginFileName());
		uptate.setOneUproadFileName(profile.getFirstUploadFileName());
		uptate.setTwoOriginFileName(profile.getSeocndOriginFileName());
		uptate.setTwoUproadFileName(profile.getSecondUploadFileName());
		// 파일 저장 및 파일 이름 가져오기

		if (uptate.getMFileOne() != null && !uptate.getMFileOne().isEmpty()) {
			// 파일 업로드 로직 구현
			String[] fileNames = uploadFile(uptate.getMFileOne());
			uptate.setOneOriginFileName(fileNames[0]);
			uptate.setOneUproadFileName(fileNames[1]);
		}

		if (uptate.getMFileTwo() != null && !uptate.getMFileTwo().isEmpty()) {
			// 파일 업로드 로직 구현
			String[] fileNames = uploadFile(uptate.getMFileTwo());
			uptate.setTwoOriginFileName(fileNames[0]);
			uptate.setTwoUproadFileName(fileNames[1]);
		}

		if (uptate.getMFile3() != null && !uptate.getMFile3().isEmpty()) {
			String[] fileNames = uploadFile(uptate.getMFile3());
			uptate.setThirdOriginFileName(fileNames[1]);
		}
		if (uptate.getMFile4() != null && !uptate.getMFile4().isEmpty()) {
			String[] fileNames = uploadFile(uptate.getMFile4());
			uptate.setFourthOriginFileName(fileNames[1]);
		}
		if (uptate.getMFile5() != null && !uptate.getMFile5().isEmpty()) {
			String[] fileNames = uploadFile(uptate.getMFile5());
			uptate.setFifthOriginFileName(fileNames[1]);
		}

		profileRepository.updateProfile(uptate.toProfile(principalId));
	}

	/**
	 * 전체목록
	 * 
	 * @param principalId
	 * @param principalGender
	 * @return
	 */
	public List<DateProfile> searchPartner(int principalId, String principalGender) {
		List<DateProfile> partnerList = null;

		partnerList = profileRepository.searchPartner(principalId, principalGender);

		return partnerList;
	}

	/**
	 * 상세보기
	 * 
	 * @param userId
	 * @param id
	 * @return
	 */
	public DateProfile detailPartner(int userId, int id) {

		DateProfile profile = profileRepository.detailPartner(userId, id);

		return profile;
	}

	/**
	 * 슈퍼 파트너 리스트 / 상단 고정 3개
	 * 
	 * @param principalId
	 * @param principalGender
	 * @return
	 * @author 형정
	 */
	public List<DateProfile> superPartner(int principalId, String principalGender) {

		List<DateProfile> superPartnerList = null;

		superPartnerList = profileRepository.superPartner(principalId, principalGender);

		return superPartnerList;

	}

	public List<ConItems> viewStoreList() {
		List<ConItems> itemList = null;
		try {
			itemList = storeRepository.viewItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}

	public int getCurrentCon(int userId) {
		int con = storeRepository.getConCount(userId);
		return con;
	}

	/**
	 * 콘 사용시 히스토리 업데이트 그리고 프로필 콘 개수 업데이트
	 * 
	 * @param userId
	 * @param price
	 * @param amount
	 * @param itemId
	 * @author 변영준
	 */
	@Transactional
	public int insertConHistory(int userId, int price, int amount, int itemId) {
		int result = 0;
		try {

			result = storeRepository.insertConHistory(userId, price, amount, itemId);
			storeRepository.updateProfileCon(userId, amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Transactional
	public int createdProfileDetail(profileDetailDTO detailDTO) {
		return profileRepository.createdProfileDetail(detailDTO);
	}
	
	public profileDetailDTO detailPartnerDetail(int id) {
		return profileRepository.detailPartnerDetail(id);
	}
	
	
}
