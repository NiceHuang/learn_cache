import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viruser on 2018/9/1.
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(7);
        list.add(1, "1");
        System.out.print(1);
    }

    public static class A {
        public void a() {
            System.out.println("a s");
            this.b();
            System.out.println("a e");
        }

        public void b() {
            System.out.println("b s");
            System.out.println("b e");
        }
    }

    public static class C {
        public A a;
        public void a() {
            System.out.println("ca s");
            a.a();
            System.out.println("ca e");
        }

        public void b() {
            System.out.println("cb s");
            a.b();
            System.out.println("cb e");
        }
    }
}

