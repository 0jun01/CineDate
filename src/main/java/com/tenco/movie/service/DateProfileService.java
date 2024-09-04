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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.tenco.movie.dto.DateProfileSignUp;
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
	public int createdProfile(User principal, DateProfileSignUp signUp) {
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
			throw new DataDeliveryException("회원가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
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
			throw new DataDeliveryException("파일 크기는 20MB 이상 클 수 없습니다", HttpStatus.BAD_REQUEST);
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
                throw new DataDeliveryException("업로드 디렉토리를 생성할 수 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
		

		try {
			mFile.transferTo(destination);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			throw new DataDeliveryException("파일 업로드 중에 오류가 발생 했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
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
