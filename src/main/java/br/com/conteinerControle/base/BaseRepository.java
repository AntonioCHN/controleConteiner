package br.com.conteinerControle.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

 //Annotation serve para "remover o repositório", ele não será reconhecido como um repositório propriamente dito, já que não temos nenuhma tabela 'Base' no banco de dados.
@NoRepositoryBean
public interface BaseRepository<ENTITY> extends JpaRepository<ENTITY, Long> {
  
}
