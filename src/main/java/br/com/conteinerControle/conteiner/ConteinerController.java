package br.com.conteinerControle.conteiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.conteinerControle.base.BaseController;

@RestController
@RequestMapping("/api/conteiners")
public class ConteinerController extends BaseController<Conteiner, ConteinerRepository, ConteinerService> {

  @Autowired
  private ConteinerService conteinerService;
  
  @PostMapping(value = "/saveConteiner")
  public void saveConteiner(@RequestBody Conteiner conteiner){
    conteinerService.saveConteiner(conteiner);
  }
}