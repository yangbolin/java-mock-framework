package cn.yangbolin.mock.easymock;


import cn.yangbolin.mock.code.MockClazz;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class MockTest {
    @Test
    public void test() {
        // ① 创建mock对象
        MockClazz mockClazz = EasyMock.createMock(MockClazz.class);
        // ② 录制mock对象的预期行为和输出
        EasyMock.expect(mockClazz.run(EasyMock.anyString())).andReturn("mocked string for run");
        // ③ 将mock对象切换到播放状态
        EasyMock.replay(mockClazz);
        // ④ 调用mock对象方法进行测试
        String actualString = mockClazz.run("name");
        Assert.assertEquals("mocked string for run", actualString);
        // ⑤ 对mock对象的行为进行验证,验证mock的对象是否按照录制的行为发生
        EasyMock.verify(mockClazz);

        String eatInfo = mockClazz.getEatInfo("aa");
        System.out.println(eatInfo);
    }

    @Test
    public void testFinal() {
        MockClazz mockClazz = EasyMock.createMock(MockClazz.class);
        EasyMock.expect(mockClazz.create("A")).andReturn("B");
        EasyMock.replay(mockClazz);
        System.out.println(mockClazz.create("A"));
    }

    @Test
    public void testPrivate() {
        MockClazz mockClazz = EasyMock.createMock(MockClazz.class);
        // 不支持private 方法
    }

    @Test
    public void testStatic() {
        // 不支持static方法的mock
    }
}
