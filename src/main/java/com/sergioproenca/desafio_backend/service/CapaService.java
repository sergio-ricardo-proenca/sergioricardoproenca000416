package com.sergioproenca.desafio_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class CapaService {

    @Autowired
    private S3Client s3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;

    public String enviarCapa(MultipartFile arquivo) throws IOException {
        String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(nomeArquivo)
                .contentType(arquivo.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, 
                RequestBody.fromInputStream(arquivo.getInputStream(), arquivo.getSize()));

        return nomeArquivo;
    }
}