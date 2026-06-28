package com.app.fileuploadanddownload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class FileServiceImpl implements IFileService{

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        s3Client.putObject(
                PutObjectRequest.builder().bucket(bucketName).key(multipartFile.getOriginalFilename()).build(),
                RequestBody.fromBytes(multipartFile.getBytes())
        );
        return "File uploaded successfully.";
    }

    @Override
    public byte[] downloadFile(String fileName) {
        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(GetObjectRequest.builder().bucket(bucketName).key(fileName).build());
        return objectBytes.asByteArray();
    }

    @Override
    public String deleteFile(String fileName) {
        s3Client.deleteObject(
                DeleteObjectRequest.builder().bucket(bucketName).key(fileName).build()
        );
        return "Deleted successfully.";
    }
}
