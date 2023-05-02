package br.com.conteinerControle.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conteinerControle.base.BaseController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController extends BaseController<Cliente, ClienteRepository, ClienteService>{
  
  @Autowired
  private ClienteService clienteService;

  @PostMapping(value = "/salvarCliente")
  public void salvarCliente(@RequestBody Cliente clinte){
    clienteService.salvarCliente(clinte);
  }
}