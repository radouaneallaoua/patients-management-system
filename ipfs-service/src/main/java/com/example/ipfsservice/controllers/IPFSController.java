package com.example.ipfsservice.controllers;


import com.example.ipfsservice.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
public class IPFSController {


    @Autowired
    private FileService ipfsService;

    @GetMapping(value = "")
    public String saveText(@RequestParam("filepath") String filepath) {
        return ipfsService.saveFile(filepath);
    }


    @PostMapping(value = "upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        return ipfsService.saveFile(file);
    }

    @GetMapping(value = "file/{hash}")
    public ResponseEntity<byte[]> getFile(@PathVariable("hash") String hash) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", MediaType.ALL_VALUE);
        byte[] bytes = ipfsService.loadFile(hash);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);

    }

}