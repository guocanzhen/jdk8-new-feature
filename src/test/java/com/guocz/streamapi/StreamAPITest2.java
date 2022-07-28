package com.guocz.streamapi;

import com.guocz.methodref.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author guocz
 * @date 2022/7/27 15:58
 *
 * 测试Stream的中间操作
 */
public class StreamAPITest2 {

    public static void main(String[] args) {
        test04();
    }

    /**
     * 筛选与切片
     */
    static void test01(){
        List<Employee> list = EmployeeData.getEmployees();
        Stream<Employee> stream = list.stream();
        //filter(Predicate p)——接收 Lambda ， 从流中排除某些元素。
        //练习：查询员工表中薪资大于7000的员工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        // limit(n)-截断流，使其元素
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();

        // skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();

        // distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",41,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",42,8000));
        list.add(new Employee(1010,"刘强东",40,8000));

        list.stream().distinct().forEach(System.out::println);
    }


    /**
     * 2-映射
     */
    static void test02(){
        // map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List <String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);

        // 练习1：获取员工姓名长度大于3的员工的姓名。
        List <Employee> employees = EmployeeData.getEmployees();
        Stream <String> nameStream = employees.stream().map(Employee::getName);
        nameStream.filter(name->name.length() > 3).forEach(System.out::println);
        System.out.println();

        // 练习2.map下面使用fromStringToStream
        //list.stream()会生成一个stream<string>,但是里面的每一个string 又会通过fromStringToStream再生成stream，最终的结果就是steam<stream>
        Stream <Stream <Character>> streamStream = list.stream().map(StreamAPITest2::fromStringToStream);
        streamStream.forEach(s->s.forEach(System.out::println));//遍历时候需要双层for循环
        System.out.println();

        //练习3 flatMap下面使用fromStringToStream
        // flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        Stream <Character> characterStream = list.stream().flatMap(StreamAPITest2::fromStringToStream);
        characterStream.forEach(System.out::println);

        //练习2和练习3中，map和flatMap的区别：
        // map: 类似于add(),如果新增加的元素是一个stream的话，则是stream里面又有一个stream，即Stream<Stream>
        // faltMap: 类似于addAll(),如果新增加的是一个stream，则会把stream拆散，加入到大的stream中，即Stream
    }

    //将字符串中的多个字符构成的集合转换成对应的Strean的实例
    static Stream<Character> fromStringToStream(String str){
        List <Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }


    static void test3(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        list1.add(list2);// 如果添加的项是一个集合，则把该集合当成一个元素              [1, 2, 3, [4, 5, 6]]
        list1.addAll(list2);//如果添加的项是一个集合，则把集合拆分后再加入到list1中    [1, 2, 3, 4, 5, 6]
        System.out.println(list1);

    }

    /**
     * 3-排序
     */
    static void test04(){
        // sorted()——自然排序
        List <Integer> list = Arrays.asList(12, 43, 65, 34, 89, -5, 26);
        list.stream().sorted().forEach(System.out::println);

        //抛异常，原因:Employee没有实现Comparable接口
//         List <Employee> employees = EmployeeData.getEmployees();
//         employees.stream().sorted().forEach(System.out::println);

        // sorted(Comparator com)——定制排序
        List <Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1,e2)->{
            //按照年龄升序，工资降序排列
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            return ageValue != 0 ?ageValue : -Double.compare(e1.getSalary(),e2.getSalary());
        }).forEach(System.out::println);

    }
}
