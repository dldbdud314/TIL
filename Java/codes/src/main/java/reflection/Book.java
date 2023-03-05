package reflection;

@MyAnnotation(name = "joy12", number = 3)
public class Book {
    public static String A = "BOOK1";

    private static String B = "BOOK12";

    @MyAnnotation(name = "aaa1", number = 4)
    private static final String c = "BOOK2";

    @MyAnnotation(name = "aaa2", number = 3)
    private String a = "a";

    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    public void g() {
        System.out.println("G");
    }

    public int sum(int a, int b) {
        return a + b;
    }
}
