package com.app.fileuploadanddownload.controller;

import com.app.fileuploadanddownload.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FIleController {

    @Autowired
    private IFileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        String msg = fileService.uploadFile(file);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName){
        byte[] fileBytes = fileService.downloadFile(fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(fileBytes,httpHeaders,HttpStatus.OK);
    }

    @GetMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName){
        String msg = fileService.deleteFile(fileName);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
