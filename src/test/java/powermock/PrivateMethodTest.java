package powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.notNull;
import static org.mockito.Matchers.isNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DataService.class)
public class PrivateMethodTest {

    @Test
    public void test() throws Exception {
        final DataService realDataService = new DataService();
        final DataService spiedDataService = PowerMockito.spy(realDataService);
        PowerMockito.doReturn(true).when(spiedDataService, "modifyData", anyString(), notNull(Object.class));
        PowerMockito.doReturn(false).when(spiedDataService, "modifyData", anyString(), isNull());

        assertTrue(spiedDataService.replaceData("1231412311", new byte[]{1, 1, 0, 1}));
        assertFalse(spiedDataService.deleteData("1231412311"));

        PowerMockito.verifyPrivate(spiedDataService, times(2)).invoke("modifyData", anyString(), anyObject());
    }
}
