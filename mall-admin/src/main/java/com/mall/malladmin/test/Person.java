package com.mall.malladmin.test;

public class Person implements Cloneable{
    private String name;
    private int age;
    private String province;
    public Person() {
    }
    public Person(String name, int age, String province) {
        this.name = name;
        this.age = age;
        this.province = province;
    }
    public Person clone() throws CloneNotSupportedException {
        return (Person) super.clone();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", province='" + province + '\'' +
                '}';
    }
}
