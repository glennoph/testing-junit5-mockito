package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class MockInlineTest {

    @Test
    void testInlineMock() {
        System.out.println( getClass().getName());
        Map mapMock = mock(Map.class); // create inline mock of Map

        assertEquals(mapMock.size(), 0);
    }
}
