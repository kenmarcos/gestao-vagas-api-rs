package br.com.kenmarcos.gestao_vagas.exceptions;

public class CandidateFoundException extends RuntimeException {
  public CandidateFoundException() {
    super("Candidato já existe");
  }
}