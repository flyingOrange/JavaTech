package java8newFeatures.methodReference;

import java.util.function.Supplier;

import org.junit.Test;

//构造器引用同方法引用类似，同样作用于函数式接口
//构造器引用的语法为 ClassName::new

//构造器参数列表要与接口中抽象方法的参数列表一致
public class ConstructorReference {

    /*
     * 1.首先我们要只是Supplier接口的抽象方法定义为： T get(); 我们代码
     * Supplier<Student>此时已经泛型T传入Student类型 相当于： Student get();
     * //函数式接口中是无参，返回值为Student 2.在看我们的Student类的定义中一个无参构成器 Student(){}
     * //构造器无参，返回Student类型（严格意义上两个构成函数没有返回值，我们先这样理解）
     * 3.此时，符合我们使用构造方法引用的前提： 构造器参数列表要与接口中抽象方法的参数列表一致！ 所以：可以使用构造引用。
     */
    @Test
    public void test() {

        // 传统Lambda方式
        Supplier<Student> studentSupplier = () -> new Student();
        Student student = studentSupplier.get();
        System.out.println(student);// Student{name='null', age=0}

        // 构造器引用
        studentSupplier = Student::new;
        student = studentSupplier.get();
        System.out.println(student);// Student{name='null', age=0}

    }

}
