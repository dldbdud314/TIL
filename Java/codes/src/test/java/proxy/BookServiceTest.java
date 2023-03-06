package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BookServiceTest {

    @Test
    @DisplayName("직접 구현한 프록시 패턴 활용")
    public void pureProxy() {
        BookService bookService = new BookServiceProxy(new DefaultBookService());
        Book book = new Book();
        book.setTitle("real log printed");
        bookService.rent(book);
    }

    @Test
    @DisplayName("다이나믹 프록시 활용")
    public void dynamicProxy() {
        BookService proxyService = (BookService) Proxy
                .newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
                        new InvocationHandler() {
                            BookService realService = new DefaultBookService();

                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                if (method.getName().equals("rent")){
                                    System.out.println("------log start------");
                                    Object invoke = method.invoke(realService, args);
                                    System.out.println("------log end------");

                                    return invoke;
                                }

                                return method.invoke(realService, args);
                            }
                        });
        System.out.println(proxyService.returnBook());

        Book book = new Book();
        book.setTitle("real log printed");
        proxyService.rent(book);
    }

}