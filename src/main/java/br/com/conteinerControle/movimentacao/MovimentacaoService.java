package br.com.conteinerControle.movimentacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conteinerControle.base.BaseService;

@Service
public class MovimentacaoService extends BaseService<Movimentacao, MovimentacaoRepository> {
  
  @Autowired
  private MovimentacaoRepository movimentacaoRepository;

  public List<Movimentacao> salvarMovimentacoes(List<Movimentacao> movimentacoes){
    if(!movimentacoes.isEmpty()){
      return movimentacaoRepository.saveAll(movimentacoes);
    } else {
      throw new RuntimeException("Não existem dados a serem processados");
    }

    /* */
  }
}
