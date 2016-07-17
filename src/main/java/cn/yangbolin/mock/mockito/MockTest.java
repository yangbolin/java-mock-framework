package cn.yangbolin.mock.mockito;

import mockit.Mock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cn.yangbolin.mock.code.MockClazz;

public class MockTest {

    @Test
    public void test() {
        // ① 创建mock对象
        MockClazz mockClazz = Mockito.mock(MockClazz.class);
        // ② 录制mock代码
        Mockito.when(mockClazz.run("A")).thenReturn("B");
        // ③ 执行单元测试
        String actual = mockClazz.run("A");
        Assert.assertEquals(actual,"B");
        // ④ 校验mock对象的行为是否按照mock执行
        Mockito.verify(mockClazz).run("A");

        String eatInfo = mockClazz.getEatInfo("aa");
        System.out.println(eatInfo);
    }

    @Test
    public void testCallRealMethod() {
        MockClazz mockClazz = Mockito.mock(MockClazz.class);
        Mockito.doCallRealMethod().when(mockClazz).run("A");
        String actual = mockClazz.run("A");
        System.out.println(actual); // A begin run... 原样执行

        System.out.println(mockClazz.run("B")); // null 返回默认值

    }

    @Test
    public void testSpy() {
        MockClazz mockClazz = Mockito.spy(new MockClazz()); // 注意这里需要new一个
        Mockito.when(mockClazz.run("A")).thenReturn("B");

        String actual = mockClazz.run("C");
        System.out.println(actual); // 输出[C begin run...],原样输出忽略mock逻辑
    }

    @Test
    public void testSpy2() {
        MockClazz mockClazz = Mockito.spy(new MockClazz());
        Mockito.when(mockClazz.run("A")).thenReturn("B");
        System.out.println(mockClazz.run("A")); // 实际执行run的代码,只是修改返回值（先输出A,再返回B)
    }

    @Test
    public void testSpy3(){
        MockClazz mockClazz = Mockito.spy(new MockClazz());
        Mockito.doReturn("B").when(mockClazz).run("C");
        System.out.println(mockClazz.run("C")); // 根本不执行run的代码,直接返回
    }
}
