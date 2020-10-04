package br.com.daione.pavan.repository;

import br.com.daione.pavan.entity.TypeEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends ReactiveMongoRepository<TypeEntity, String> {

}
