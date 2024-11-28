package br.com.kenmarcos.gestao_vagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenmarcos.gestao_vagas.modules.company.entities.JobEntity;
import br.com.kenmarcos.gestao_vagas.modules.company.services.CreateJobService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
  @Autowired
  private CreateJobService createJobService;

  @PostMapping("/")
  public JobEntity create(@Valid @RequestBody JobEntity jobEntity) {
    JobEntity result = createJobService.execute(jobEntity);
    return result;
  }
}
