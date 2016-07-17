package cn.yangbolin.mock.powermock;


import cn.yangbolin.mock.code.MockClazz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { MockClazz.class })
public class MockTest {

    @Test
    public void testMockStatic() {
        // 静态方法的mock
        PowerMockito.mockStatic(MockClazz.class);
        PowerMockito.when(MockClazz.sleep("A")).thenReturn("B");
        System.out.println(MockClazz.sleep("A"));
        PowerMockito.verifyStatic();
    }

    @Test
    public void testMockPrivate() throws  Exception {
        // 私有方法的mock,getEatInfo=>eat,eat是私有方法
        MockClazz mockClazz = PowerMockito.mock(MockClazz.class);
        PowerMockito.when(mockClazz, "eat", "A").thenReturn("mock");
        PowerMockito.doCallRealMethod().when(mockClazz).getEatInfo("A");
        System.out.println(mockClazz.getEatInfo("A"));
    }

    @Test
    public void testMockFinal() {
        // final方法的mock
        MockClazz mockClazz = PowerMockito.mock(MockClazz.class);
        PowerMockito.when(mockClazz.create("A")).thenReturn("B");
        System.out.println(mockClazz.create("A"));
    }
}
