package br.com.kenmarcos.gestao_vagas.modules.candidate.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.kenmarcos.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.kenmarcos.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.kenmarcos.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateService {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO)
      throws AuthenticationException {
    var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Username/password incorrect");
        });

    var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create().withIssuer("kenmarcos")
        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .withSubject(candidate.getId().toString())
        .withClaim("roles", Arrays.asList("candidate"))
        .sign(algorithm);

    var authCandidateResponse = AuthCandidateResponseDTO.builder().access_token(token).build();

    return authCandidateResponse;
  }
}
