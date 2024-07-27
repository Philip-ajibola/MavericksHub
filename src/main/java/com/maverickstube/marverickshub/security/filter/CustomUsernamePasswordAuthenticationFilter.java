package com.maverickstube.marverickshub.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maverickstube.marverickshub.dtos.request.LoginRequest;
import com.maverickstube.marverickshub.dtos.response.BaseResponse;
import com.maverickstube.marverickshub.dtos.response.LoginResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private  final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            InputStream requestBody = request.getInputStream();
            LoginRequest loginRequest = objectMapper.readValue(requestBody, LoginRequest.class);
            Authentication authenticationResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            return authenticationResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = generateAccessToken(authResult);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setMessage("SuccessFul Authentication ");
        BaseResponse<LoginResponse> authResponse = new BaseResponse<>();
        authResponse.setData(loginResponse);
        authResponse.setStatus(true);
        authResponse.setStatusCode(HttpStatus.OK.value());

        response.getOutputStream().write(objectMapper.writeValueAsBytes(authResponse));
        response.flushBuffer();
        chain.doFilter(request,response);
    }
    private static String generateAccessToken(Authentication authResult) {
      return JWT.create()
                .withIssuer("mavericks_hub")
                        .withArrayClaim("roles",getClaimsFrom(authResult.getAuthorities()))
                                .withExpiresAt(Instant.now().plusSeconds(24*60*60))
                                        .sign(Algorithm.HMAC512("secret"));

    }

    private static String[] getClaimsFrom(Collection<? extends GrantedAuthority> authorities){
        return authorities.stream().map((grantedAuthority)-> grantedAuthority.getAuthority()).toArray(String[]::new);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage(exception.getMessage());
        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        baseResponse.setStatus(false);
        baseResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        response.getOutputStream().write(objectMapper.writeValueAsBytes(baseResponse));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.flushBuffer();
    }
}
