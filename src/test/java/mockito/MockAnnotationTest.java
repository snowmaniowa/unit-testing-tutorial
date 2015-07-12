package mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;
import org.mockito.internal.verification.AtMost;
import org.mockito.internal.verification.Only;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
import static org.mockito.Matchers.same;

@RunWith(MockitoJUnitRunner.class)
public class MockAnnotationTest {

    @Mock
    private List<String> mockedList;

    @Test
    public void testNumberOfCalls() {
        mockedList.add("value");

        Mockito.verify(mockedList).add("value");
        Mockito.verify(mockedList, new AtLeast(1)).add("value");
        Mockito.verify(mockedList, new AtMost(1)).add("value");
        Mockito.verify(mockedList, new Only()).add("value");
        Mockito.verify(mockedList, new Times(1)).add("value");
        assertEquals(0, mockedList.size());
    }

    @Test
    public void testStubMethod() {
        Mockito.when(mockedList.size()).thenReturn(100);
        assertEquals(100, mockedList.size());
    }

    @Test
    public void testTypeMatchers() {
        final List<String> stringList = new ArrayList<>();
        stringList.add("value");
        mockedList.addAll(stringList);
        mockedList.add("anotherValue");

        // All possible matchers
        // http://docs.mockito.googlecode.com/hg/latest/org/mockito/Matchers.html
        Mockito.verify(mockedList).addAll(anyCollection());
        Mockito.verify(mockedList).addAll(anyCollectionOf(String.class));
        Mockito.verify(mockedList).add(anyString());
        Mockito.verify(mockedList).add(notNull(String.class));
        Mockito.verify(mockedList).add(same("anotherValue"));
    }
}
