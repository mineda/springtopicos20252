package br.gov.sp.fatec.springtopicos20252.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    @Value("${api.key:default_secret_key}")
    private String KEY;

    public String generateToken(Authentication usuario) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Login usuarioSemSenha = new Login();
        usuarioSemSenha.setUsername(usuario.getName());
        if (!usuario.getAuthorities().isEmpty()) {
            usuarioSemSenha.setAuth(usuario.getAuthorities().iterator().next().getAuthority());
        }
        String usuarioJson = mapper.writeValueAsString(usuarioSemSenha);
        Date agora = new Date();
        Long hora = 1000L * 60L * 60L; // Uma hora
        return Jwts.builder()
            .claim("userDetails", usuarioJson)
            .setIssuer("br.gov.sp.fatec")
            .setSubject(usuario.getName())
            .setExpiration(new Date(agora.getTime() + hora))
            .signWith(Keys.hmacShaKeyFor(KEY.getBytes()), SignatureAlgorithm.HS256).compact();
    }

    public Authentication parseToken(String token) 
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).build()
            .parseClaimsJws(token).getBody().get("userDetails", String.class);
        Login usuario = mapper.readValue(credentialsJson, Login.class);
        UserDetails userDetails = User.builder().username(usuario.getUsername()).password("secret")
            .authorities(usuario.getAuth()).build();
        return new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword(),
            userDetails.getAuthorities());
    }

}
