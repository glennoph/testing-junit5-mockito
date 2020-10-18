package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

public class MockAnnotationTest {
    @Mock
    Map<String, Object> mapMock;

    @BeforeEach
    void before() {
        MockitoAnnotations.initMocks(this);
        System.out.println("MockAnnotationTest");
    }

    @Test
    void testMock() {
        mapMock.put("keyvalue", "111");
        System.out.println(mapMock.size());
    }
}
