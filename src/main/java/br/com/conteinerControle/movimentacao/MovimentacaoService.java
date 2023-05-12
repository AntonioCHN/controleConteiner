package br.com.conteinerControle.movimentacao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conteinerControle.Resumo.MovimentacaoResumida;
import br.com.conteinerControle.base.BaseService;

@Service
public class MovimentacaoService extends BaseService<Movimentacao, MovimentacaoRepository> {

  @Autowired
  private MovimentacaoRepository movimentacaoRepository;

  public Optional<Movimentacao> salvarMovimentacao(Movimentacao movimentacao){ 
    validarDataHora(movimentacao);
    Movimentacao nova = movimentacaoRepository.save(movimentacao);
    return Optional.of(nova);
  }


  public void validarDataHora(Movimentacao movimentacao){
    LocalDateTime agora = LocalDateTime.now();
    LocalDateTime datahorai = movimentacao.getDatahorai();
    LocalDateTime datahoraf = movimentacao.getDatahoraf();

    if((datahorai.isBefore(agora)) || (datahoraf.isBefore(agora))) {
      throw new RuntimeException("Data e hora inicial ou final estão menor que a atual do sistema!");
    } else if (datahoraf.isBefore(datahorai)){
      throw new RuntimeException("Data e hora final menor que a inicial");
    }
  }

  /*vamos implementar agora o metodo que vai acessar a nossa query nativa para trazer o relatório das movimentações agrupadas por cliente e com o total de importação e exportação.
  Utilizando a estrutura Map para mapearmos a forma que os dados deve ser acessados no banco de dados e apresentados para o cliente*/

  public Map<String, Object> listarMovimentacoesAgrupadasPorClienteETipoMovimentacao(){
    
    Optional<Long> totalImportacao = Optional.empty();
    Optional<Long> totalExportacao = Optional.empty();

    List<MovimentacaoResumida> movimentacoes = movimentacaoRepository.listarMovimentacoesPorClientesETipoMovimentacao();

    Map<String, Object> mapa = new HashMap<>();
    for (MovimentacaoResumida mov : movimentacoes){
      mapa.put(mov.getCod().toString(), mov);
    }

    totalImportacao = Optional.of(movimentacoes.stream().mapToLong(p -> p.getTotalImportacao()).sum());
    totalExportacao = Optional.of(movimentacoes.stream().mapToLong(p -> p.getTotalExportacao()).sum());

    if ((totalImportacao.isPresent()) && (totalExportacao.isPresent())){
      mapa.put("Total de Importação", totalImportacao);
      mapa.put("Total de Exportação", totalExportacao);
    }

    return mapa;

  }


}
