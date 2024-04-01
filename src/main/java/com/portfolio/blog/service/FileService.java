package com.portfolio.blog.service;

import com.portfolio.blog.entity.File;
import com.portfolio.blog.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public Long save(MultipartFile file) throws IOException {

        String fullPath = "D:/files/";
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1); ;
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        log.info("file.getOriginalFilename = {}", file.getOriginalFilename());
        log.info("fullPath = {}", fullPath);
        log.info("type = {}", type);
        log.info("fileName = {}", fileName);

        java.io.File saveFile = new java.io.File(fullPath, fileName);
        file.transferTo(saveFile);

        File newFile = File.builder()
                .fileName(fileName)
                .originFileName(file.getOriginalFilename())
                .filePath(fullPath)
                .fileType(type)
                .fileSize(file.getSize())
                .build();

        return fileRepository.save(newFile).getId();
    }

    @Transactional(readOnly = true)
    public File findById(Long id){
        return fileRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 파일입니다."));
    }

}
