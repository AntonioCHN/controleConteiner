package br.com.conteinerControle.movimentacao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.conteinerControle.base.BaseRepository;

public interface MovimentacaoRepository extends BaseRepository<Movimentacao>{

  @Query(value = "select m from Movimentacao m "
                + "inner join Conteiner c on c.cod = m.conteiner.cod "
                + "inner join Cliente cl on cl.cod = c.cliente.cod "
                + "group by m.cod, c.cliente.name, m.tipo "
                + "order by c.cliente.name asc "
                )                        
  public List<Movimentacao> listarMovimentacoesPorClientesETipoMovimentacao();

  @Query(value = "select m from Movimentacao m "
                + "inner join Conteiner c on c.cod = m.conteiner.cod "
                + "inner join Cliente cl on cl.cod = c.cliente.cod "
                + "where c.cliente.cod =:cod "
                + "group by m.cod, c.cliente.name, m.tipo ")
  public List<Movimentacao> listarMovimentacoesPorClientes(@Param("cod") Long cod);              
}
