package com.controle.base;
//um Controller tem como parâmetro uma entidade que extende de BaseEntity, um repositório que extende de BaseRepository, e referencia uma entidade e um Service que extende de BaseService, e referencia uma entidade e um repositório

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class BaseController <ENTITY extends BaseEntity, REPOSITORY extends BaseRepository<ENTITY>, SERVICE extends BaseService<ENTITY, REPOSITORY>> {

  @Autowired
  private SERVICE service;

  @Autowired
  private REPOSITORY repo;

   //annotation @PostMapping que marca este metodo como um metodo http de post, ou seja, que ira fazer um POST na tabela, ira iserir um novo registro na tabel
  //reponseEntity me apresenta metodos para a manipulação das requisçoes http de uma forma que otimiza nosso codigo, me dando acesso também aos codigos de status para enviar se uma requisição teve sucesso ou não
  @PostMapping
  public ResponseEntity<String> salvar(@RequestBody ENTITY entity) {
    Optional<ENTITY> result = service.salvar(entity);
    if (result.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(result.get().getCod().toString());
    } else {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  }

   //anota o metodo como um metodo http de get, que retorna informaçoes
  //metodo esta retornando os resgistros da entidade referenciada em forma de lista
  @GetMapping
  public List<ENTITY> findAll(){
    return repo.findAll();
  }

   //anota o metodo como um metodo http de put, que envia uma requisição que altera um registro na tabela 
  @PutMapping
  public ResponseEntity<ENTITY> update(@RequestBody ENTITY entity){
    ENTITY entityUpdate = service.callUpdate(entity);
    return ResponseEntity.ok().body(entityUpdate);
  }

   //anota o metodo como um metodo http de delte, que deleta um registro da tabela
  @DeleteMapping  
  public ResponseEntity<Void> delete(@RequestParam("cod") Long cod) {
    service.delete(cod);
    return ResponseEntity.noContent().build(); // retorna nada, uma entidade sem body
  }

}
