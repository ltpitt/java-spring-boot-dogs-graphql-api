package it.davidenastri.dogsgraphql.repository;


import it.davidenastri.dogsgraphql.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}