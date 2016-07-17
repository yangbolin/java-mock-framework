package cn.yangbolin.mock.mockito;

import mockit.Mock;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cn.yangbolin.mock.code.MockClazz;

public class MockTest {

    @Test
    public void test() {
        // �� ����mock����
        MockClazz mockClazz = Mockito.mock(MockClazz.class);
        // �� ¼��mock����
        Mockito.when(mockClazz.run("A")).thenReturn("B");
        // �� ִ�е�Ԫ����
        String actual = mockClazz.run("A");
        Assert.assertEquals(actual,"B");
        // �� У��mock�������Ϊ�Ƿ���mockִ��
        Mockito.verify(mockClazz).run("A");

        String eatInfo = mockClazz.getEatInfo("aa");
        System.out.println(eatInfo);
    }

    @Test
    public void testCallRealMethod() {
        MockClazz mockClazz = Mockito.mock(MockClazz.class);
        Mockito.doCallRealMethod().when(mockClazz).run("A");
        String actual = mockClazz.run("A");
        System.out.println(actual); // A begin run... ԭ��ִ��

        System.out.println(mockClazz.run("B")); // null ����Ĭ��ֵ

    }

    @Test
    public void testSpy() {
        MockClazz mockClazz = Mockito.spy(new MockClazz()); // ע��������Ҫnewһ��
        Mockito.when(mockClazz.run("A")).thenReturn("B");

        String actual = mockClazz.run("C");
        System.out.println(actual); // ���[C begin run...],ԭ���������mock�߼�
    }

    @Test
    public void testSpy2() {
        MockClazz mockClazz = Mockito.spy(new MockClazz());
        Mockito.when(mockClazz.run("A")).thenReturn("B");
        System.out.println(mockClazz.run("A")); // ʵ��ִ��run�Ĵ���,ֻ���޸ķ���ֵ�������A,�ٷ���B)
    }

    @Test
    public void testSpy3(){
        MockClazz mockClazz = Mockito.spy(new MockClazz());
        Mockito.doReturn("B").when(mockClazz).run("C");
        System.out.println(mockClazz.run("C")); // ������ִ��run�Ĵ���,ֱ�ӷ���
    }
}
