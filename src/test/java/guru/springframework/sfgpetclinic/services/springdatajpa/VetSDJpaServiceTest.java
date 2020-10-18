package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.repositories.CrudRepository;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepositoryMock;

    @InjectMocks
    VetSDJpaService vetSDJpaServiceMock;

    @BeforeEach
    void setUp() {
        System.out.println("VetSDJpaServiceTest");
    }

    @Test
    void deleteById() {
        long id = 2L;
        vetSDJpaServiceMock.deleteById(id); // test service.deleteById
        verify(vetRepositoryMock).deleteById(id); // verify that repository.deleteById is called
    }
}