package br.com.conteinerControle.movimentacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.conteinerControle.base.BaseController;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController extends BaseController<Movimentacao, MovimentacaoRepository, MovimentacaoService>{
  
  @Autowired
  private MovimentacaoRepository movimentacaoRepository;

  @Autowired
  private MovimentacaoService movimentacaoService;

  @PostMapping(value = "/salvarMovimentacoes")
  public ResponseEntity<List<Movimentacao>> saveMovimentacoes(@RequestBody List<Movimentacao> movimentacoes){
    List<Movimentacao> result = movimentacaoService.salvarMovimentacoes(movimentacoes);
    if(!result.isEmpty()){
      return ResponseEntity.status(HttpStatus.CREATED).body(result);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping(value = "/listarMovimentacoesPorClienteETipoMovimentacao")
  public List<Movimentacao> listarMovimentacoesAgrupadasPorClienteETipoMovimentacao(){
    return movimentacaoRepository.listarMovimentacoesPorClientesETipoMovimentacao();
  } 

  @GetMapping(value = "/listarMovimentacoesPorCliente")
  public List<Movimentacao> listarMovimentacoesPorCliente(@RequestParam("cod") Long codCliente){
    return movimentacaoRepository.listarMovimentacoesPorClientes(codCliente);
  }

}
