package br.com.kenmarcos.gestao_vagas.modules.company.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenmarcos.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.kenmarcos.gestao_vagas.modules.company.services.AuthCompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyService authCompanyService;

  @PostMapping("/companies")
  public ResponseEntity<Object> create(@RequestBody AuthCompanyDTO authCompanyDTO) {
    try {
      var result = authCompanyService.execute(authCompanyDTO);

      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }

}
