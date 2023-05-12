package br.com.conteinerControle.resumo;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.conteinerControle.enums.Categoria;
import br.com.conteinerControle.enums.Status;
import br.com.conteinerControle.enums.TipoConteiner;
import br.com.conteinerControle.enums.TipoMovimentacao;

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