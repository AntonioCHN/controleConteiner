package br.com.conteinerControle.movimentacao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conteinerControle.base.BaseService;
import br.com.conteinerControle.resumo.MovimentacaoResumida;

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

  public Map<Object, Object> listarMovimentacoesAgrupadasPorClienteETipoMovimentacao(){

    List<MovimentacaoResumida> movimentacoes = movimentacaoRepository.listarMovimentacoesPorClientesETipoMovimentacao();


    Map<Object, Object> mapa = movimentacoes.stream()
    .collect(Collectors.toMap(x -> x.getCod(), x -> x));  
    
    
    Optional<Long> totalImportacao = Optional.empty();
    Optional<Long> totalExportacao = Optional.empty();
  

    totalImportacao = Optional.of(movimentacoes.stream().mapToLong(m -> m.getTotalImportacao()).sum());
    totalExportacao = Optional.of(movimentacoes.stream().mapToLong(m -> m.getTotalExportacao()).sum());

    
    Map<Object, Object> mapa1 = new HashMap<>();
    if ((totalImportacao.isPresent()) && (totalExportacao.isPresent())){
      mapa1.put("Total de Importação", totalImportacao);
      mapa1.put("Total de Exportação", totalExportacao);
    }

    Map<Object, Object> finalMap = new LinkedHashMap<>(mapa);
      finalMap.putAll(mapa1);

    return finalMap;
  }
}
