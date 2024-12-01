package br.com.kenmarcos.gestao_vagas.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.gestao_vagas.exceptions.CompanyFoundException;
import br.com.kenmarcos.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.kenmarcos.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((candidate) -> {
          throw new CompanyFoundException();
        });

    var passwordEncoded = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(passwordEncoded);

    return candidateRepository.save(candidateEntity);

  }
}
