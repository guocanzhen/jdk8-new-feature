package com.guocz.streamapi;

import com.guocz.methodref.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author guocz
 * @date 2022/7/27 15:27
 *
 * * 1. Stream关注的是对数据的运算，与CPU打交道
 *  *    集合关注的是数据的存储，与内存打交道
 *  *
 *  * 2.
 *  * ①Stream 自己不会存储元素。
 *  * ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 *  * ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行
 *  *
 *  * 3.Stream 执行流程
 *  * ① Stream的实例化
 *  * ② 一系列的中间操作（过滤、映射、...)
 *  * ③ 终止操作
 *  *
 *  * 4.说明：
 *  * 4.1 一个中间操作链，对数据源的数据进行处理
 *  * 4.2 一旦执行终止操作，就执行中间操作链，并产生结果。之后，不会再被使用
 *  *
 *  *
 *  *  测试Stream的实例化
 */
public class StreamAPITest {

    public static void main(String[] args) {
        test04();
    }

    /**
     * 创建Stream方式一：通过集合
     */
    static void test01(){
        List<Employee> employees = EmployeeData.getEmployees();
        // default Stream<E> stream() : 返回一个顺序流
        Stream<Employee> stream = employees.stream();

        // default Stream<E> parallelStream() : 返回一个并行流
        Stream <Employee> parallelStream = employees.parallelStream();
    }

    /**
     * 创建Stream方式二：通过数组
     */
    static void test02(){
        int[] arr = new int[]{1,2,3,4,5};
        //调用Arrays类的static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream = Arrays.stream(arr);

        Employee employee1 = new Employee(1001, "刘备", 25, 15000);
        Employee employee2 = new Employee(1002, "关羽", 31, 12000);
        Employee[] arr2 = {employee1, employee2};
        Stream <Employee> stream1 = Arrays.stream(arr2);
    }

    /**
     * 创建Stream方式三：通过Stream的of()
     */
    static void test03(){
        Stream <Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    /**
     * 创建Stream 方式四：创建无限流
     */
    static void test04(){
        //迭代
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前10个偶数
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);

        //生成
        // public static<T> Stream<T> generate(Supplier<T> s)
        //生成10个随机数
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

}
