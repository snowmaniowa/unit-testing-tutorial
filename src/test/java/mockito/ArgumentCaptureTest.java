package mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptureTest {
    @Mock
    private List<String> mockedList;
    @Captor
    private ArgumentCaptor<String> argCaptor;

    @Test
    public void test() {
        mockedList.add("one");
        Mockito.verify(mockedList).add(argCaptor.capture());
        assertEquals("one", argCaptor.getValue());
        final List<String> allValues = argCaptor.getAllValues();
        assertEquals(1, allValues.size());
    }
}
