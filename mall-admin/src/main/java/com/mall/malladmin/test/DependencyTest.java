package com.mall.malladmin.test;

public class DependencyTest {
    /**
     * 菜刀
     */
    static class Kinfe {
        public static void cutting(String name) {
            System.out.println("切" + name);
        }
    }

    /**
     *  厨师
     */
    class Chef {
        public void cutting(Kinfe kinfe, String vegetables) {
            kinfe.cutting(vegetables);
        }
    }

    class Test{
        public void say(){

        }
    }
    public static void main(String[] args) {
        DependencyTest dependencyTest = new DependencyTest();
        Chef chef = dependencyTest.new Chef();
        Kinfe kinfe = new Kinfe();

        chef.cutting(kinfe,"carrot");


    }

}
