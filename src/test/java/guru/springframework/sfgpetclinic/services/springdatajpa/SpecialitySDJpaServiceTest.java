package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

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
        Speciality speciality = new Speciality();
        service.delete(speciality);
        // either verify is ok, prob should not have both
        verify(specialtyRepositoryMock).delete(speciality); // arg match speciality obj - more specific
        verify(specialtyRepositoryMock).delete(any(Speciality.class)); // arg match any Speciality
    }

    @Test
    void findById() {
        Speciality speciality = new Speciality();
        long id = 11L;
        when(specialtyRepositoryMock.findById(id)).thenReturn(Optional.of(speciality));
        Speciality foundSpecialty = service.findById(id);
        assertNotNull(foundSpecialty);
        verify(specialtyRepositoryMock).findById(id);
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("test exception")).when(specialtyRepositoryMock).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepositoryMock.delete(new Speciality()));
        verify(specialtyRepositoryMock).delete(any());
    }

    @Test
    void testWillThrow() {
        willThrow(new RuntimeException("test ex")).given(specialtyRepositoryMock).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepositoryMock.delete(new Speciality()));
        verify(specialtyRepositoryMock).delete(any());
    }

}