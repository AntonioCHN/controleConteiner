package br.com.conteinerControle.movimentacao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.conteinerControle.base.BaseRepository;
import br.com.conteinerControle.resumo.MovimentacaoResumida;

public interface MovimentacaoRepository extends BaseRepository<Movimentacao>{

  //Feita em query nativa
  @Query(value = "select m.cod, cl.nome, c.numero, c.tipoc, c.status, "
                + "c.categoria, m.tipom, m.datahorai, m.datahoraf,"
                + " (select COUNT(*) from conteiner co where co.cod = m.conteiner_cod and co.categoria = 'Importação') as totalImportacao, "
                + " (select COUNT(*) from conteiner co where co.cod = m.conteiner_cod and co.categoria = 'Exportação') as totalExportacao "
                + "from movimentacao m "
                + "inner join conteiner c on c.cod = m.conteiner_cod "
                + "inner join cliente cl on cl.cod = c.cliente_cod "
                + "group by m.cod, cl.nome, m.tipom ", nativeQuery = true)                        
  public List<MovimentacaoResumida> listarMovimentacoesPorClientesETipoMovimentacao();


  //feito em JPQL 
  @Query(value = "select m from Movimentacao m "
                + "inner join Conteiner c on c.cod = m.conteiner.cod "
                + "inner join Cliente cl on cl.cod = c.cliente.cod "
                + "where c.cliente.cod =:cod "
                + "group by m.cod, c.cliente.nome, m.tipom ")
  public List<Movimentacao> listarMovimentacoesPorClientes(@Param("cod") Long cod);              
}
