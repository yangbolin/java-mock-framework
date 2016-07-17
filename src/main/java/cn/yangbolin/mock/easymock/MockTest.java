package cn.yangbolin.mock.easymock;


import cn.yangbolin.mock.code.MockClazz;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class MockTest {
    @Test
    public void test() {
        // �� ����mock����
        MockClazz mockClazz = EasyMock.createMock(MockClazz.class);
        // �� ¼��mock�����Ԥ����Ϊ�����
        EasyMock.expect(mockClazz.run(EasyMock.anyString())).andReturn("mocked string for run");
        // �� ��mock�����л�������״̬
        EasyMock.replay(mockClazz);
        // �� ����mock���󷽷����в���
        String actualString = mockClazz.run("name");
        Assert.assertEquals("mocked string for run", actualString);
        // �� ��mock�������Ϊ������֤,��֤mock�Ķ����Ƿ���¼�Ƶ���Ϊ����
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
        // ��֧��private ����
    }

    @Test
    public void testStatic() {
        // ��֧��static������mock
    }
}
