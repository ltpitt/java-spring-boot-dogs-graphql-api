import it.davidenastri.dogsgraphql.entity.Dog;
import it.davidenastri.dogsgraphql.exception.DogNotFoundException;
import it.davidenastri.dogsgraphql.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QueryTest {

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private Query query;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllDogs_ShouldReturnAllDogs() {
        // Arrange
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        when(dogRepository.findAll()).thenReturn(Arrays.asList(dog1, dog2));

        // Act
        Iterable<Dog> dogs = query.findAllDogs();

        // Assert
        assertNotNull(dogs);
        assertTrue(dogs.iterator().hasNext());
        assertEquals(2, ((Collection<?>) dogs).size());
        verify(dogRepository, times(1)).findAll();
    }

    @Test
    void findDogById_ShouldReturnDog_WhenDogExists() {
        // Arrange
        Dog dog = new Dog();
        when(dogRepository.findById(1L)).thenReturn(Optional.of(dog));

        // Act
        Dog foundDog = query.findDogById(1L);

        // Assert
        assertNotNull(foundDog);
        assertEquals(dog, foundDog);
        verify(dogRepository, times(1)).findById(1L);
    }

    @Test
    void findDogById_ShouldThrowException_WhenDogDoesNotExist() {
        // Arrange
        when(dogRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        DogNotFoundException exception = assertThrows(DogNotFoundException.class, () -> query.findDogById(1L));
        assertEquals("Dog Not Found", exception.getMessage());
        verify(dogRepository, times(1)).findById(1L);
    }
}
