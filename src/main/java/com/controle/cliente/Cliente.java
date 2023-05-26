package com.controle.cliente;

import com.controle.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//entidade cliente que extende de baseEntity
@Entity
@Table
public class Cliente extends BaseEntity{
  
  private String nome;

  public Cliente() {
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  
}
