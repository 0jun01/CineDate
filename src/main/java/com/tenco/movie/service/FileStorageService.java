package com.tenco.movie.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tenco.movie.utils.Define;

/**
 * 파일저장
 * @author 성후
 */
@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("upload-dir"); // 파일 업로드 디렉토리 설정

    public void store(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException(Define.FAILED_PROCESSING);
        }
        Path destinationFile = this.rootLocation.resolve(
                Paths.get(file.getOriginalFilename()))
                .normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
            // This is a security check
            throw new RuntimeException(
            		Define.FAILED_PROCESSING);
        }
        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile);
        }
    }
}