package com.mall.malladmin.test;

public class Instance {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, CloneNotSupportedException {
        Class<?> aClass = Class.forName("com.mall.malladmin.test.Person");
        Person person = (Person) aClass.newInstance();
        System.out.println(person.toString());
        Person p1 = new Person();
        p1.setAge(11);
        p1.setName("测试");
        p1.setProvince("222");
        Person p2 = p1.clone();
        System.out.println(p2.toString());

    }
}
