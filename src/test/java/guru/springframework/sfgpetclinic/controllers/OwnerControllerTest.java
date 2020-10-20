package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerServiceMock;

    @InjectMocks
    OwnerController ownerControllerMock;

    @BeforeEach
    void setUp() {
        System.out.println("OwnerControllerTest");
    }

    @Test
    void processCreationForm_hasErrors() {
        BindingResult resultMock = mock(BindingResult.class);
        when(resultMock.hasErrors()).thenReturn(true);
        Owner ownerMock = mock(Owner.class);
        String form = ownerControllerMock.processCreationForm(ownerMock, resultMock);
        assertEquals(form, "owners/createOrUpdateOwnerForm");
        verify(resultMock).hasErrors();
    }


    @Test
    void processCreationForm_noErrors() {
        BindingResult resultMock = mock(BindingResult.class);
        when(resultMock.hasErrors()).thenReturn(false);
        Owner ownerMock = mock(Owner.class);
        when(ownerServiceMock.save(any())).thenReturn(ownerMock);
        when(ownerMock.getId()).thenReturn(5L);
        String form = ownerControllerMock.processCreationForm(ownerMock, resultMock);
        assertEquals(form, "redirect:/owners/5");
        verify(resultMock).hasErrors();
        verify(ownerServiceMock).save(any());
        verify(ownerMock).getId();
    }
}