package com.example.ipfsservice.services;


import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String saveFile(String filePath);
    String saveFile(MultipartFile file);

    byte[] loadFile(String hash);
}