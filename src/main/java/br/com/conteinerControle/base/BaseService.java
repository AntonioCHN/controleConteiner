package br.com.conteinerControle.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

//Service tem como parâmetro uma entidade, que extende de BaseEntity, e também, tem como parâmetro um repositório, que extende de BaseRepository  

public class BaseService<ENTITY extends BaseEntity, REPOSITORY extends BaseRepository<ENTITY>>  {
  
  //referencia de um rpositório para obtenção dos metodos de acesso ao banco  
  @Autowired
  private REPOSITORY repo;

  @Transactional
  //metodo que salva um registro na entidade 
  public Optional<ENTITY> salvar(ENTITY entity){
    ENTITY n = repo.save(entity);
    return Optional.of(n);
  } 

  //atualiza um registro na entidade
  @Transactional 
  public Optional<ENTITY> update(ENTITY entity){
    if (repo.existsById(entity.getCod())){
      salvar(entity);
      return Optional.of(entity);
    } else {
      return Optional.empty();
    }
  }

  // metodo que retorna uma runtimeException caso o registro que desejo alterar não esteja presente na tabela
  public ENTITY callUpdate(ENTITY entity){
    return update(entity).orElseThrow(() -> new RuntimeException("Registro não encontrado " + entity.getCod()));
  }

  //deleta registro da entidade 
  @Transactional
  public void delete(Long id){
    if(repo.existsById(id)){
      repo.deleteById(id);
    }
  }

}
