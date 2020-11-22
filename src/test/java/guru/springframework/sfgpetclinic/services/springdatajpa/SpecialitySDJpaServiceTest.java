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
import static org.mockito.BDDMockito.given;
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
        doThrow(new RuntimeException("test exception"))
                .when(specialtyRepositoryMock).delete(any());
        assertThrows(RuntimeException.class,
                () -> service.delete(new Speciality())); // run service.delete()
        verify(specialtyRepositoryMock).delete(any());
    }

    @Test
    void testDoThrowTryCatch() {
        doThrow(new RuntimeException("test exception"))
                .when(specialtyRepositoryMock).delete(any());
        try {
            service.delete(new Speciality()); // run service.delete()
            fail("did not throw exception");
        } catch(RuntimeException ex) {
            System.out.println(ex);
        }
        verify(specialtyRepositoryMock).delete(any());
    }

    @Test
    void testWillThrow() {
        willThrow(new RuntimeException("test exception"))
                .given(specialtyRepositoryMock).delete(any());
        assertThrows(RuntimeException.class,
                () -> service.delete(new Speciality())); // run service.delete()
        verify(specialtyRepositoryMock).delete(any());
    }

    @Test
    void testSaveLambda() {
        final String MATCH_PATTERN = "match";
        Speciality specialityTest = new Speciality();
        specialityTest.setDescription(MATCH_PATTERN);

        final long ID = 1234L;
        Speciality specialitySaved = new Speciality();
        specialitySaved.setId(ID);

        // argThat the description is equal to the match pattern
        given(specialtyRepositoryMock.save(
                argThat(arg ->
                        arg.getDescription().equals(MATCH_PATTERN))
        )).willReturn(specialitySaved);

        //when
        Speciality specialityReturned = service.save(specialityTest);

        //then
        verify(specialtyRepositoryMock).save(any());
        assertNotNull(specialityReturned);
        assertEquals(ID, specialityReturned.getId());
        System.out.println("specialityReturned.getId="+specialityReturned.getId());
    }

}