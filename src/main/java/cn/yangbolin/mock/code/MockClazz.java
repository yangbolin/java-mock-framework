package cn.yangbolin.mock.code;

public class MockClazz {

    public String  run(String name) {
        System.out.println(name);
        return name + " begin run...";
    }

    public static String sleep (String name) {
        System.out.println(name);
        return name + " begin sleep...";
    }

    private String eat(String name) {
        System.out.println(name);
        return name + " begin eat...";
    }

    public String getEatInfo(String name) {
        return eat(name);
    }

    public final String create(String name) {
        return name;
    }
}
