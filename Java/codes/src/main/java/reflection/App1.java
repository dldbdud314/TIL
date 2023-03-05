package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App1 {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("reflection.Book");
        Constructor<?> constructor = bookClass.getConstructor(String.class, String.class, String.class);
        Book myBook = (Book) constructor.newInstance("myBook1", "myBook2", "myBook3");
        System.out.println(myBook);

        Field a = Book.class.getDeclaredField("A");
        System.out.println(a.get(null));
        a.set(null, "AAAAAAA");
        System.out.println(a.get(null));

        Field b = Book.class.getDeclaredField("B");
        b.setAccessible(true);
        System.out.println(b.get(null));

        Method f = Book.class.getDeclaredMethod("f");
        f.setAccessible(true);
        f.invoke(myBook);

        Method sum = Book.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) sum.invoke(myBook, 1, 2);
        System.out.println(invoke);
    }
}
