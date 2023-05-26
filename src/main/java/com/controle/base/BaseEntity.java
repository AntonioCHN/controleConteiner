package com.controle.base;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

//Annotation que mapeia uma superclasse
@MappedSuperclass
public class BaseEntity {
  

  @Id //mapeia a váriavel como ID da entidade
  @GeneratedValue(strategy = GenerationType.IDENTITY) //informa que a gereção do id deve ser auto incrementada assim que um novo registro é inserido na tabela do banco de dados 
  private Long cod;

  public BaseEntity(){

  }

  public Long getCod(){
    return cod;
  }

  //cada código gerado é encapsulado em um hashSet, para uma melhor eficiencia na hora de realizarmos uma busca pelo cod
  @Override
  public int hashCode() {
    return cod.hashCode();
  }


  //método que compara o cod que estamos buscando, com os cod já salvos na nossa entidade, atuando em conjunto com nosso metodo hashCode()
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseEntity other = (BaseEntity) obj;
    if (cod == null) {
      if (other.cod != null)
        return false;
    } else if (!cod.equals(other.cod))
      return false;
    return true;
  }

  
}
