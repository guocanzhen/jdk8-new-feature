package com.guocz.streamapi;

import com.guocz.methodref.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author guocz
 * @date 2022/7/28 9:49
 *
 * 测试Stream的终止操作
 */
public class StreamAPITest3 {

    public static void main(String[] args) {
        test04();
    }

    /**
     * 1-匹配与查找1
     */
    static void test01(){
        List<Employee> employees = EmployeeData.getEmployees();

        // allMatch(Predicate p)——检查是否匹配所有元素。
        // 练习：是否所有的员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        // anyMatch(Predicate p)——检查是否至少匹配一个元素。
        // 练习：是否存在员工的工资大于 8000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 8000);
        System.out.println(anyMatch);

        // noneMatch(Predicate p)——检查是否没有匹配的元素。
        // 练习：是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);

        // findFirst——返回第一个元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);

        // findAny——返回当前流中的任意元素
        Optional <Employee> employee1 = employees.stream().findAny();
        System.out.println(employee1);
    }

    /**
     * 2-匹配与查找2
     */
    static void test02(){
        List <Employee> employees = EmployeeData.getEmployees();
        // count-返回流中元素的总个数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);
        // max(Comparator c)——返回流中最大值
        // 练习：返回最高的工资：
        Optional <Double> maxSalary = employees.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println(maxSalary);

        // min(Comparator c)——返回流中最小值
        // 练习：返回最低工资的员工
        Optional <Employee> employee = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(employee);
        System.out.println();
        // forEach(Consumer c)——内部迭代
        employees.stream().forEach(System.out::println);
        System.out.println();

        //使用集合的遍历操作
        employees.forEach(System.out::println);

    }

    /**
     * 3-归约
     */
    static void test03(){
        // reduce(T identity, BinaryOperator)——可以将流中元素反复结合起来，得到一个值。返回 T
        // 练习1：计算1-10的自然数的和
        List <Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, Integer::sum);//实际上就是 传入俩值得到结果，再作为新值继续往后加
        System.out.println(sum);
        // reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        // 练习2：计算公司所有员工工资的总和
        List <Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        // Optional <Double> sumMoney = salaryStream.reduce(Double::sum);
        Optional <Double> sumMoney = salaryStream.reduce((d1,d2)-> Double.sum(d1,d2));
        System.out.println(sumMoney.get());

    }

    /**
     * 4-收集
     */
    static void test04(){
        // collect(Collector c)——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
        // 练习1：查找工资大于6000的员工，结果返回为一个List或Set
        List <Employee> employees = EmployeeData.getEmployees();
        List <Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);
        System.out.println();

        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
        System.out.println();

        List<Employee> list = employees.parallelStream().filter(employee -> employee.getSalary() > 6000).collect(Collectors.toList());
        list.parallelStream().forEach(System.out::println);
    }


}
