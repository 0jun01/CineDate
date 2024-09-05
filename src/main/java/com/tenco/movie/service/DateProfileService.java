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
import com.tenco.movie.handler.exception.DataDeliveryException;
import com.tenco.movie.repository.interfaces.ProfileRepository;
import com.tenco.movie.repository.model.DateProfile;
import com.tenco.movie.repository.model.User;
import com.tenco.movie.utils.Define;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DateProfileService {

	@Autowired
	private final ProfileRepository profileRepository;
	
	

	@Value("${file.upload-dir}")
	private String uploadDir;

	public DateProfile searchProfile(int principalID) {

		DateProfile profile = profileRepository.searchProfile(principalID);

		return profile;
	}
	
	/**
	 * @author 병호  파일 업로드 구현
	 * @param principal
	 * @param signUp
	 * @return
	 */
	@Transactional
	public int createdProfile(User principal, DateProfileDTO signUp) {
		int result = 0;
		
		if(signUp.getMFileOne() != null && !signUp.getMFileOne().isEmpty()) {
			// 파일 업로드 로직 구현
			String[] fileNames = uploadFile(signUp.getMFileOne());
			signUp.setOneOriginFileName(fileNames[0]);
			signUp.setOneUproadFileName(fileNames[1]);
		}
		if(signUp.getMFileTwo() != null && !signUp.getMFileTwo().isEmpty()) {
			// 파일 업로드 로직 구현
			String[] fileNames = uploadFile(signUp.getMFileTwo());
			signUp.setTwoOriginFileName(fileNames[0]);
			signUp.setTwoUproadFileName(fileNames[1]);
		}
		result = profileRepository.createdProfile(signUp.toProfile(principal.getId()));
		
		if(result != 1) {
			throw new DataDeliveryException(Define.FAIL_TO_CREATE_USER, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return result;
	}

	/**
	 * @author 병호  파일 이름변화 
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
	 * @author 성후
	 * @return
	 */
	@Transactional
	public void updateProfile(DateProfileDTO update, int principalId) throws IOException {
		
		DateProfile profile = searchProfile(principalId);
		System.out.println("=======================================================================");
		System.out.println(profile);
		System.out.println("=======================================================================");
		update.setOneOriginFileName(profile.getFirstOriginFileName());
	    update.setOneUproadFileName(profile.getFirstUploadFileName());
	    update.setTwoOriginFileName(profile.getSecondOriginFileName());
	    update.setTwoUproadFileName(profile.getSecondUploadFileName());

	    // 파일 업로드 및 파일 이름 처리
	    if (update.getMFileOne() != null && !update.getMFileOne().isEmpty()) {
	        String[] fileNames = uploadFile(update.getMFileOne());
	        update.setOneOriginFileName(fileNames[0]);
	        update.setOneUproadFileName(fileNames[1]);
	    }

	    if (update.getMFileTwo() != null && !update.getMFileTwo().isEmpty()) {
	        String[] fileNames = uploadFile(update.getMFileTwo());
	        update.setTwoOriginFileName(fileNames[0]);
	        update.setTwoUproadFileName(fileNames[1]);
	    }

	    if (update.getMFile3() != null && !update.getMFile3().isEmpty()) {
	        String[] fileNames = uploadFile(update.getMFile3());
	        update.setThirdOriginFileName(fileNames[1]); // 원본 파일 이름
	    }

	    if (update.getMFile4() != null && !update.getMFile4().isEmpty()) {
	        String[] fileNames = uploadFile(update.getMFile4());
	        update.setFourthOriginFileName(fileNames[1]);
	    }

	    if (update.getMFile5() != null && !update.getMFile5().isEmpty()) {
	        String[] fileNames = uploadFile(update.getMFile5());
	        update.setFifthOriginFileName(fileNames[1]);
	    }

	    System.out.println("=======================================================================");
	    System.out.println("update  :" + update);
	    System.out.println("=======================================================================");

	    // 프로필 업데이트
	    profileRepository.updateProfile(update.toProfile(principalId));
	}

	
	/**
	 * 전체목록
	 * @param principalId
	 * @param principalGender
	 * @return
	 */
	public List<DateProfile> searchPartner(int principalId, String principalGender){
		List<DateProfile> partnerList = null;
		
		partnerList = profileRepository.searchPartner(principalId, principalGender);
		
		return partnerList;
	}
	
	
	public DateProfile detailPartner(int userId,int id) {
		
		
		DateProfile profile =  profileRepository.detailPartner(userId, id);
		
		return profile;
	}
	
}