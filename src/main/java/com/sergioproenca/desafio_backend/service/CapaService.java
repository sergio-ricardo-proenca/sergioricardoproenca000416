package com.sergioproenca.desafio_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;

@Service
public class CapaService {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner; // Necessário para gerar links expiráveis

    @Value("${s3.bucket-name}")
    private String bucketName;

    // Injeção via construtor: Melhor prática Sênior
    public CapaService(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    /**
     * Sobe o arquivo para o MinIO e retorna o NOME do arquivo para salvar no banco.
     */
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

    /**
     * Gera uma URL pré-assinada que expira em 30 minutos.
     * Requisito (i) do edital.
     */
    public String gerarLinkTemporario(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.isEmpty()) {
            return null;
        }

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(nomeArquivo)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(30)) // Tempo exigido no edital
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url().toString();
    }
}