package proxy;

import static net.bytebuddy.matcher.ElementMatchers.named;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


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
                                if (method.getName().equals("rent")) {
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

    @Test
    @DisplayName("CGLIB로 클래스 기반의 프록시 만들기")
    public void cglib() {
        MethodInterceptor handler = new MethodInterceptor() {
            DefaultBookService realService = new DefaultBookService();  // 구체클래스로 바꿔도 동작

            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy)
                    throws Throwable {
                if (method.getName().equals("rent")) {
                    System.out.println("******log start******");
                    Object invoke = method.invoke(realService, args);
                    System.out.println("******log end******");

                    return invoke;
                }

                return method.invoke(realService, args);
            }
        };
        BookService proxyService = (BookService) Enhancer.create(BookService.class, handler);
        Book book = new Book();
        book.setTitle("real log printed");
        proxyService.rent(book);
    }

    @Test
    @DisplayName("바이트버디 활용(바이트코드 조작) 프록시 만들기")
    public void byteBuddy() throws Exception {
        Class<? extends BookService> proxyClass = new ByteBuddy().subclass(BookService.class)
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    DefaultBookService realService = new DefaultBookService();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("//////log start//////");
                        Object invoke = method.invoke(realService, args);
                        System.out.println("//////log end//////");
                        return invoke;
                    }
                }))
                .make().load(BookService.class.getClassLoader()).getLoaded();

        BookService proxyService = proxyClass.getConstructor(null).newInstance();
        Book book = new Book();
        book.setTitle("real log printed");
        proxyService.rent(book);
    }

}