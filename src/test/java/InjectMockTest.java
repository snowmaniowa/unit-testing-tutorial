import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class InjectMockTest {
    @Mock
    private Map<String, String> wordMap;
    @InjectMocks
    private Dictionary dictionary = new Dictionary();

    @Test
    public void test() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");
        assertEquals("aMeaning", dictionary.getMeaning("aWord"));
    }
}
