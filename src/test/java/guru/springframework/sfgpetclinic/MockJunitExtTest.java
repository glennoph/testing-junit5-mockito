package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MockJunitExtTest {

    @Mock
    Map<String, Object> mapMock;

    @BeforeEach
    void before() {
        MockitoAnnotations.initMocks(this);
        System.out.println("MockJunitExtTest");
    }

    @Test
    void testMock() {
        mapMock.put("keyvalue", "111");
        System.out.println(mapMock.size());
    }

}
