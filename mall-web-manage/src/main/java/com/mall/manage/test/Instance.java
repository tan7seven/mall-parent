package com.mall.manage.test;

public class Instance {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, CloneNotSupportedException {
        Class<?> aClass = Class.forName("com.mall.manage.test.Person");
        Person person = (Person) aClass.newInstance();
        System.out.println(person.toString());
        Person p1 = new Person();
        p1.setAge(11);
        p1.setProvince("222");
        Person p2 = p1.clone();
        System.out.println(p2.toString());
        TestEnum test = TestEnum.sss;
        TestEnum test2 = TestEnum.sss;
        System.out.println(test ==test2);
    }
}
