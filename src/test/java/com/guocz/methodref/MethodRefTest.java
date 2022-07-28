package com.guocz.methodref;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author guocz
 * @date 2022/7/27 13:54
 *
 * * 方法引用的使用
 *  *
 *  * 1.使用情境：当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！
 *  *
 *  * 2.方法引用，本质上就是Lambda表达式，而Lambda表达式作为函数式接口的实例。所以
 *  *   方法引用，也是函数式接口的实例。
 *  *
 *  * 3. 使用格式：  类(或对象) :: 方法名
 *  *
 *  * 4. 具体分为如下的三种情况：
 *  *    情况1     对象 :: 非静态方法
 *  *    情况2     类 :: 静态方法
 *  *
 *  *    情况3     类 :: 非静态方法
 *  *
 *  * 5. 方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的
 *  *    形参列表和返回值类型相同！（针对于情况1和情况2）
 */
public class MethodRefTest {

    public static void main(String[] args) {
        test5();
    }

    /**
     * 情况一：对象 :: 实例方法
     * Consumer中的void accept(T t)
     * PrintStream中的void println(T t)
     */
    static void test1() {
        Consumer<String> consumer1 = str-> System.out.println(str);
        consumer1.accept("北京");

        System.out.println("*******************");
        PrintStream ps = System.out;
        Consumer<String> consumer2 = ps::println;
        consumer2.accept("北京");
    }

    /**
     * Supplier中的T get()
     * Employee中的String getName()
     */
    static void test2() {
        Employee emp = new Employee(1001, "Tom", 23, 5600);
        Supplier<String> sup1 = ()->emp.getName();
        System.out.println(sup1.get());

        System.out.println("*********************");
        Supplier<Double> sup2 = emp::getSalary;
        System.out.println(sup2.get());
    }

    /**
     * 情况二：类 :: 静态方法
     * Comparator中的int compare(T t1,T t2)
     * Integer中的int compare(T t1,T t2)
     */
    static void test3() {
        Comparator<Integer> com1 = (t1, t2)->Integer.compare(t1,t2);
        System.out.println(com1.compare(10,54));

        System.out.println("*********************");
        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(54,13));
    }

    /**
     * Function中的R apply(T t)
     * Math中的Long round(Double d)
     */
    static void test4() {
        Function<Double,Long> func = new Function <Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(func.apply(10.1));
        System.out.println("*******************");

        Function<Double,Long> func2 = a->Math.round(a);
        System.out.println(func2.apply(10.5));

        System.out.println("*******************");
        Function<Double,Long> func3 = Math::round;
        System.out.println(func3.apply(10.8));

    }

    /**
     * 情况三：类 :: 实例方法
     * Comparator中的int comapre(T t1,T t2)
     * String中的int t1.compareTo(t2)
     */
    static void test5() {
        Comparator<String> com1 = (s1,s2)->s1.compareTo(s2);
        System.out.println(com1.compare("abc","abd"));

        System.out.println("*******************");

        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("abd","abm"));//第一个参数当成函数的调用者
    }

    /**
     * BiPredicate中的boolean test(T t1, T t2);
     * String中的boolean t1.equals(t2)
     */
    public void test6() {
        BiPredicate<String,String> pre1 = (s1, s2)->s1.equals(s2);
        System.out.println(pre1.test("abc","abc"));

        System.out.println("************************");

        BiPredicate<String,String> pre2 = String::equals;
        System.out.println(pre2.test("abc","abd"));

    }

    /**
     * Function中的R apply(T t)
     * Employee中的String getName();
     */
    public void test7() {
        Employee employee = new Employee(1001, "张三", 20, 6600);
        Function<Employee,String> fun1 = e -> e.getName();
        System.out.println(fun1.apply(employee));

        System.out.println("***************");
        Function<Employee,String> fun2 = Employee::getName;
        System.out.println(fun2.apply(employee));//传入的参数employee当成方法调用者
    }

}
