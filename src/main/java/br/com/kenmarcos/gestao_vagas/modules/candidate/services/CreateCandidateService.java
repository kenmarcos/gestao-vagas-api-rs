package br.com.kenmarcos.gestao_vagas.modules.candidate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.gestao_vagas.exceptions.CandidateFoundException;
import br.com.kenmarcos.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.kenmarcos.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateService {

  @Autowired
  private CandidateRepository candidateRepository;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((candidate) -> {
          throw new CandidateFoundException();
        });

    return candidateRepository.save(candidateEntity);

  }
}
