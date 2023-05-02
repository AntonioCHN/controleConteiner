package br.com.conteinerControle.movimentacao;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.conteinerControle.base.BaseEntity;
import br.com.conteinerControle.conteiner.Conteiner;
import br.com.conteinerControle.enums.TipoMovimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;


@Entity
@Table
public class Movimentacao extends BaseEntity {

  @OneToOne
  @JoinColumn(name = "conteiner_cod")
  private Conteiner conteiner;

  @NotNull(message = "Todos os campos devem ser preenchidos!")
  @Enumerated(EnumType.STRING)
  private TipoMovimentacao tipo;

  @NotNull(message = "Todos os campos devem ser preenchidos!")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHoraInicio;

  @NotNull(message = "Todos os campos devem ser preenchidos!")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHoraFim;


  public Movimentacao() {
  }


  public Conteiner getConteiner() {
    return conteiner;
  }

  public void setConteiner(Conteiner conteiner) {
    this.conteiner = conteiner;
  }

  public TipoMovimentacao getTipo() {
    return tipo;
  }

  public void setTipo(TipoMovimentacao tipo) {
    this.tipo = tipo;
  }

  public Date getDataHoraInicio() {
    return dataHoraInicio;
  }

  public void setDataHoraInicio(Date dataHoraInicio) {
    this.dataHoraInicio = dataHoraInicio;
  }

  public Date getDataHoraFim() {
    return dataHoraFim;
  }

  public void setDataHoraFim(Date dataHoraFim) {
    this.dataHoraFim = dataHoraFim;
  }

}
