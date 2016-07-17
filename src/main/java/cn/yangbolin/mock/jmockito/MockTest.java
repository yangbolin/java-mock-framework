package cn.yangbolin.mock.jmockito;

import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.yangbolin.mock.code.MockClazz;

@RunWith(JMockit.class)
public class MockTest {

    @Mocked
    MockClazz mockClazz = new MockClazz();

    @Test
    public void testExpectations() { // ȫ��mock���쳣
        new Expectations() {
            {
                mockClazz.getEatInfo("A");
                returns("B");
            }
        };

        // ��mock�ķ���,����mock���ֵ
        System.out.println(mockClazz.getEatInfo("A"));
        // û�б�mock�ķ���,mockit.internal.UnexpectedInvocation,jmocit��runû�н���mock
        System.out.println(mockClazz.run("A"));
    }

    @Test
    public void testNonExpectations() { //ȫ��mock����ȱʡֵ

        new NonStrictExpectations() {
            {
                mockClazz.getEatInfo("A");
                returns("B");
            }
        };

        // ��mock�ķ���,����mock���ֵ
        System.out.println(mockClazz.getEatInfo("A"));
        // û�б�mock�ķ���,����Ĭ��ֵ,jmockit��run����Ҳ������mock
        System.out.println(mockClazz.run("A"));
    }

    @Test
    public void testMockStatic() {
        // mock��̬����
        new NonStrictExpectations() {
            {
                MockClazz.sleep("A");
                result = "B";
            }
        };
        System.out.println(MockClazz.sleep("A"));
    }

    @Test
    public void testMockPrivate() {
        // mock��̬����
        final MockClazz obj = new MockClazz();
        new NonStrictExpectations(obj) {
            {
                // ˽�з���mock
                this.invoke(obj, "eat", "A");
                returns("B");
            }
        };
        System.out.println(obj.getEatInfo("A")); // ˽�з�����mock��
        System.out.println(obj.run("A")); // run�������ᱻmock,����ʵ�߼�
    }
}
