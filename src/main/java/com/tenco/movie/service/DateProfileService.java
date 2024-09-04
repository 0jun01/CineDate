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
	 * 마이페이지 업데이트
	 * @author 성후
	 * @return
	 */
	@Transactional
	public void updateProfile(String newNickName, String newIntroduce,
	        MultipartFile newFirstFile, MultipartFile newSecondFile,
	        MultipartFile newThirdFile, MultipartFile newFourthFile,
	        MultipartFile newFifthFile, MultipartFile newFirstUploadFile,
	        MultipartFile newSecondUploadFile, int newUserId) throws IOException {

	    // 파일 저장 및 파일 이름 가져오기
	    String newFirstFileName = saveFile(newFirstFile);
	    String newSecondFileName = saveFile(newSecondFile);
	    String newThirdFileName = saveFile(newThirdFile);
	    String newFourthFileName = saveFile(newFourthFile);
	    String newFifthFileName = saveFile(newFifthFile);
	    String newFirstUploadFileName = saveFile(newFirstUploadFile);
	    String newSecondUploadFileName = saveFile(newSecondUploadFile);

	    // 프로필 업데이트
	    DateProfile dateProfile = DateProfile.builder()
	            .nickName(newNickName)
	            .introduce(newIntroduce)
	            .firstOriginFileName(newFirstFileName)
	            .secondOriginFileName(newSecondFileName)
	            .thirdOriginFileName(newThirdFileName)
	            .fourthOriginFileName(newFourthFileName)
	            .fifthOriginFileName(newFifthFileName)
	            .firstUploadFileName(newFirstUploadFileName)
	            .secondUploadFileName(newSecondUploadFileName)
	            .userId(newUserId)
	            .build();

	    profileRepository.updateProfile(dateProfile);
	}

	private String saveFile(MultipartFile file) throws IOException {
	    if (file.isEmpty()) return "";

	    // 파일 확장자 추출
	    String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
	    
	    // UUID와 파일 확장자를 결합하여 새로운 파일 이름 생성
	    String fileName = UUID.randomUUID().toString() + "_" + fileExtension;

	    // 업로드 디렉토리 경로 설정
	    File targetFile = new File(uploadDir + File.separator + fileName);
	    
	    // 업로드 디렉토리가 존재하지 않으면 생성
	    File uploadDirFile = new File(uploadDir);
	   
	    if (!uploadDirFile.exists()) {
	        uploadDirFile.mkdirs();
	    }

	    // 파일을 지정된 위치에 저장
	    file.transferTo(targetFile);
	    
	    return fileName; // UUID가 포함된 파일 이름 반환
	}

	
	
	public List<DateProfile> searchPartner(int principalId, String principalGender){
		List<DateProfile> partnerList = null;
		
		partnerList = profileRepository.searchPartner(principalId, principalGender);
		
		return partnerList;
	}
	
}
