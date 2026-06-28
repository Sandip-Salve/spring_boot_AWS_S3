package com.app.fileuploadanddownload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {

    String uploadFile(MultipartFile multipartFile) throws IOException;

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);
}
