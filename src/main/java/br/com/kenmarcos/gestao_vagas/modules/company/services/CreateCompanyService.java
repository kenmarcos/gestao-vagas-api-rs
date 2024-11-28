package br.com.kenmarcos.gestao_vagas.modules.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenmarcos.gestao_vagas.exceptions.CompanyFoundException;
import br.com.kenmarcos.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.kenmarcos.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyService {
  @Autowired
  private CompanyRepository companyRepository;

  public CompanyEntity execute(CompanyEntity companyEntity) {
    companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
        .ifPresent((company) -> {
          throw new CompanyFoundException();
        });

    return companyRepository.save(companyEntity);

  }
}
