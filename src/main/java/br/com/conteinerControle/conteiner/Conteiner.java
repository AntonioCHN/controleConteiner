package br.com.conteinerControle.conteiner;


import br.com.conteinerControle.base.BaseEntity;
import br.com.conteinerControle.cliente.Cliente;
import br.com.conteinerControle.enums.Categoria;
import br.com.conteinerControle.enums.Status;
import br.com.conteinerControle.enums.TipoConteiner;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table
public class Conteiner extends BaseEntity{

  @OneToOne
  @JoinColumn(name = "cliente_cod")
  private Cliente cliente;

  @Pattern(regexp = "[CONT]{4}[0-9]{7}", message = "Campo deve ser preenchido com 4 letras iniciais (CONT), seguidas de 7 números!")
  private String numero;

  @Enumerated(EnumType.STRING) //Anotação para o registro ser salvo como uma string no banco
  private TipoConteiner tipoc;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Enumerated(EnumType.STRING)
  private Categoria categoria;

  public Conteiner() {

  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public TipoConteiner getTipoc() {
    return tipoc;
  }

  public void setTipo(TipoConteiner tipoc) {
    this.tipoc = tipoc;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }
  
}
