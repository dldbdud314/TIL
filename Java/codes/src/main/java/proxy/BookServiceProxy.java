package proxy;

public class BookServiceProxy implements BookService{

    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("------log start------");
        bookService.rent(book);
        System.out.println("------log end------");
    }

    @Override
    public String returnBook() {
        return bookService.returnBook();
    }
}
