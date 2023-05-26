package com.controle.resumo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.controle.enums.Categoria;
import com.controle.enums.Status;
import com.controle.enums.TipoConteiner;
import com.controle.enums.TipoMovimentacao;

@Component
public interface MovimentacaoResumida {

  @JsonIgnore
  Long getCod();

  String getNome();

  Categoria getCategoria();

  Status getStatus();

  TipoConteiner getTipoc();

  String getNumero();

  TipoMovimentacao getTipom();

  LocalDateTime getDatahorai();

  LocalDateTime getDatahoraf();
  
  @JsonIgnore
  Long getTotalImportacao();

  @JsonIgnore
  Long getTotalExportacao();
}