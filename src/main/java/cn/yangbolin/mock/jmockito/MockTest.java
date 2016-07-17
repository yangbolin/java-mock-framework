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
    public void testExpectations() { // 全局mock抛异常
        new Expectations() {
            {
                mockClazz.getEatInfo("A");
                returns("B");
            }
        };

        // 被mock的方法,返回mock后的值
        System.out.println(mockClazz.getEatInfo("A"));
        // 没有被mock的方法,mockit.internal.UnexpectedInvocation,jmocit对run没有进行mock
        System.out.println(mockClazz.run("A"));
    }

    @Test
    public void testNonExpectations() { //全局mock返回缺省值

        new NonStrictExpectations() {
            {
                mockClazz.getEatInfo("A");
                returns("B");
            }
        };

        // 被mock的方法,返回mock后的值
        System.out.println(mockClazz.getEatInfo("A"));
        // 没有被mock的方法,返回默认值,jmockit对run方法也进行了mock
        System.out.println(mockClazz.run("A"));
    }

    @Test
    public void testMockStatic() {
        // mock静态方法
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
        // mock静态方法
        final MockClazz obj = new MockClazz();
        new NonStrictExpectations(obj) {
            {
                // 私有方法mock
                this.invoke(obj, "eat", "A");
                returns("B");
            }
        };
        System.out.println(obj.getEatInfo("A")); // 私有方法被mock了
        System.out.println(obj.run("A")); // run方法不会被mock,走真实逻辑
    }
}
