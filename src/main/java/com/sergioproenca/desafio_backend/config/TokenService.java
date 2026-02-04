package com.sergioproenca.desafio_backend.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // Chave secreta para assinatura do token
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String gerarToken() {
        Date hoje = new Date();
        // Requisito (b): 5 minutos = 300.000 milissegundos
        Date dataExpiracao = new Date(hoje.getTime() + 300000);

        return Jwts.builder()
                .setIssuer("Desafio Backend")
                .setSubject("Candidato Senior")
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(key)
                .compact();
    }
}