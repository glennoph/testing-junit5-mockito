package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    final String fname1 = "fname";
    final String lname1 = "lname";
    final String srchExpected1 = "%"+ lname1 +"%";
    final Owner owner1 = new Owner(11L, fname1, lname1);

    final String lname2NF = "not-found";
    final String srchExpected2NF = "%"+ lname2NF +"%";
    final Owner owner2NF = new Owner(22L, fname1, lname2NF);

    final String lname3Mult = "mult-found";
    final String srchExpected3Mult = "%"+ lname3Mult +"%";
    final Owner owner3Mult1 = new Owner(331L, fname1, lname3Mult);
    final Owner owner3Mult2 = new Owner(332L, "fname2", lname3Mult);

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

    @Captor
    ArgumentCaptor<String> argumentCaptor;
    @Mock
    BindingResult bindingResultMock;
    @Mock
    Model modelMock;

    void setupAnswer() {
        given(ownerServiceMock.findAllByLastNameLike(argumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> ownerList = new ArrayList<>();

                    String srchArg = invocation.getArgument(0);

                    if (srchArg.equals(srchExpected1)) {
                        ownerList.add(owner1);
                        return ownerList;
                    } else if (srchArg.equals(srchExpected2NF)) {
                        // do not add owner to list
                        return  ownerList;
                    } else if (srchArg.equals(srchExpected3Mult)) {
                        ownerList.add(owner3Mult1);
                        ownerList.add(owner3Mult2);
                        return  ownerList;
                    }

                    throw new RuntimeException("setupAnswer: invalid argument");
                } );
    }

    @Test
    void processFindFormWildcardArgCapture() {
        //given
        setupAnswer();

        //when
        String viewRet = ownerControllerMock.processFindForm(owner1, bindingResultMock, modelMock);

        //then
        assertEquals(srchExpected1, argumentCaptor.getValue()); // check search arg
        assertEquals("redirect:/owners/11", viewRet); // check view returned
    }

    @Test
    void processFindFormWildcardArgCaptureNotFound() {
        //given
        setupAnswer();

        //when
        String viewRet = ownerControllerMock.processFindForm(owner2NF, bindingResultMock, modelMock);

        //then
        assertEquals(srchExpected2NF, argumentCaptor.getValue()); // check search arg
        assertEquals("owners/findOwners", viewRet); // check view returned
    }

    @Test
    void processFindFormWildcardArgCaptureMultFound() {
        //given
        setupAnswer();

        //when
        String viewRet = ownerControllerMock.processFindForm(owner3Mult1, bindingResultMock, modelMock);

        //then
        assertEquals(srchExpected3Mult, argumentCaptor.getValue()); // check search arg
        assertEquals("owners/ownersList", viewRet); // check view returned
    }


}