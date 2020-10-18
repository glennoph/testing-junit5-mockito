package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepositoryMock;

    @InjectMocks
    SpecialitySDJpaService service;

    @BeforeEach
    void setUp() {
        System.out.println("SpecialitySDJpaServiceTest");
    }


    @Test
    void deleteById() {
        System.out.println("deleteById");
        service.deleteById(1L);
        // verify that the method was called with parm
        verify(specialtyRepositoryMock).deleteById(1L);
    }

    @Test
    void delete() {
        System.out.println("delete");
        service.delete(new Speciality());
    }
}