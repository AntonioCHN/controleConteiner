package br.com.conteinerControle.cliente;

import br.com.conteinerControle.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

//entidade cliente que extende de baseEntity
@Entity
@Table
public class Cliente extends BaseEntity{
  
  @NotNull(message = "Campo nome deve ser preenchido!")
  private String nome;

  public Cliente() {
  }

  public String getName() {
    return nome;
  }

  public void setName(String nome) {
    this.nome = nome;
  }

  
}
