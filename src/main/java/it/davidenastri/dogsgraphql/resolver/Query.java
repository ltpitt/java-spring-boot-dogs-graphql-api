package it.davidenastri.dogsgraphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import it.davidenastri.dogsgraphql.entity.Dog;
import it.davidenastri.dogsgraphql.exception.DogNotFoundException;
import it.davidenastri.dogsgraphql.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if (optionalDog.isPresent()) {
            return optionalDog.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }
    }
}