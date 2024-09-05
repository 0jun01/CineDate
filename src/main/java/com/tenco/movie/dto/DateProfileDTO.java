package com.tenco.movie.dto;

import org.springframework.web.multipart.MultipartFile;

import com.tenco.movie.repository.model.DateProfile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString 
public class DateProfileDTO {
    private String nickName;
    private String introduce;
    private MultipartFile mFileOne;
    private String OneOriginFileName;
    private String OneUproadFileName;
    private MultipartFile mFileTwo;
    private String TwoOriginFileName;
    private String TwoUproadFileName;
    private MultipartFile mFile3;
    private String thirdOriginFileName;
    private MultipartFile mFile4;
    private String fourthOriginFileName;
    private MultipartFile mFile5;
    private String fifthOriginFileName;


    // third_origin_file_name fourth_origin_file_name first_upload_file_name

    public DateProfile toProfile(int PrinpalId) {


        return DateProfile.builder()
                .userId(PrinpalId)
                .nickName(nickName)
                .introduce(introduce)
                .firstOriginFileName(OneOriginFileName)
                .firstUploadFileName(OneUproadFileName)
                .secondOriginFileName(TwoOriginFileName)
                .secondUploadFileName(TwoUproadFileName)
                .thirdOriginFileName(thirdOriginFileName)
                .fourthOriginFileName(fourthOriginFileName)
                .fifthOriginFileName(fifthOriginFileName)
                .build();

    }
   
}