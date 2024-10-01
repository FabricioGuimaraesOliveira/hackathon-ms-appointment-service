package com.fiap.hackathon.appointmentservice.usecases.agendamento;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class AuthorizationUseCase {
    private Algorithm algorithm;
    private JWTVerifier verifier;

    public AuthorizationUseCase() {
        this.algorithm = Algorithm.HMAC256("fiap");
        this.verifier = JWT.require(algorithm).build();
    }


    public boolean validarToken(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public String obterCpfDoToken(String token) {
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getClaim("cpf").asString();
    }
}
