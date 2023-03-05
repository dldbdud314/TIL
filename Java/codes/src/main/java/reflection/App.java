package reflection;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<Book> aClass0 = Book.class;  // 1. 타입을 통할 때

        Arrays.stream(aClass0.getFields())
                .forEach(System.out::println);  // public 필드만
        Arrays.stream(aClass0.getDeclaredFields())
                .forEach(System.out::println);  // 접근 지시자 상관없이 모두 가져온다
        Arrays.stream(aClass0.getDeclaredConstructors())
                .forEach(System.out::println);

        Book book = new Book();
        Class<? extends Book> aClass1 = book.getClass();  // 2. 인스턴스를 통할 때
        Arrays.stream(aClass1.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true);  // 접근 지시자 무시
                System.out.printf("%s %s\n", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        Arrays.stream(aClass1.getDeclaredFields()).forEach(f -> {
            int modifiers = f.getModifiers();

            System.out.println(f);
            System.out.println(Modifier.isPrivate(modifiers));
            System.out.println(Modifier.isStatic(modifiers));
        });

        Class<?> aClass2 = Class.forName("reflection.Book");  // 3. 정보 없음 --> FQCN 활용
        Arrays.stream(aClass2.getMethods()).forEach(System.out::println);
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);

        Arrays.stream(Book.class.getDeclaredFields()).forEach(f -> {
            Arrays.stream(f.getAnnotations()).forEach(a -> {
                if (a instanceof MyAnnotation) {
                    MyAnnotation myAnnotation = (MyAnnotation) a;
                    System.out.println(myAnnotation.name());
                    System.out.println(myAnnotation.number());
                }
            });
        });

    }
}
