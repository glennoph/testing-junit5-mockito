package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repositoryMock;

    @InjectMocks
    VisitSDJpaService serviceMock;

    @BeforeEach
    void setUp() {
        System.out.println("VisitSDJpaServiceTest");
    }

    @Test
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(visit);
        when(repositoryMock.findAll()).thenReturn(visitSet);
        Set<Visit> foundVisits = serviceMock.findAll();
        assertNotNull(foundVisits);
        assertEquals(foundVisits, visitSet);
        verify(repositoryMock).findAll();
    }

    @Test
    void findById() {
        Visit visit = new Visit();
        Long id = 123L;
        when(repositoryMock.findById(id)).thenReturn(java.util.Optional.of(visit));
        serviceMock.findById(id);
        verify(repositoryMock).findById(id);
    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(repositoryMock.save(visit)).thenReturn(visit);
        Visit savedVisit = serviceMock.save(visit);
        assertNotNull(savedVisit);
        assertEquals(savedVisit, visit);
        verify(repositoryMock).save(visit);
    }

    @Test
    void delete() {
        Visit visit = new Visit();
        serviceMock.delete(visit);
        verify(repositoryMock).delete(visit);
    }

    @Test
    void deleteById() {
        Visit visit = new Visit();
        Long id = 123L;
        serviceMock.deleteById(id);
        verify(repositoryMock).deleteById(id);
    }
}