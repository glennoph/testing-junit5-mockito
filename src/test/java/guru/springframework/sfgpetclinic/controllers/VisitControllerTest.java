package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitServiceMock;

    @Mock
    PetService petServiceMock;

    @InjectMocks
    VisitController visitControllerMock;

    @BeforeEach
    void setUp() {
        System.out.println("VisitControllerTest");
    }

    @Test
    void loadPetWithVisitMock() {
        //given
        final long ID = 12345L;
        Pet pet = new Pet(ID);
        Map<String, Object> model = new HashMap<>();
        given(petServiceMock.findById(anyLong())).willReturn(pet);

        //when
        Visit visit = visitControllerMock.loadPetWithVisit(123L, model);

        //then
        assertNotNull(visit);
        assertNotNull(visit.getPet());
        System.out.println("pet id="+visit.getPet().getId());
        assertEquals(ID, visit.getPet().getId());
    }


    @Spy
    PetMapService petMapServiceSpy;


    @Test
    void loadPetWithVisitSpy() {
        //given
        final long ID = 123456L;
        Pet pet = new Pet(ID);
        petMapServiceSpy.save(pet); // save the pet
        Map<String, Object> model = new HashMap<>();

        // call real method findById
        given(petMapServiceSpy.findById(anyLong())).willCallRealMethod();

        //when
        Visit visit = visitControllerMock.loadPetWithVisit(ID, model);

        //then
        assertNotNull(visit);
        assertNotNull(visit.getPet());
        assertEquals(Optional.of(ID), java.util.Optional.ofNullable(visit.getPet().getId()));
    }
}