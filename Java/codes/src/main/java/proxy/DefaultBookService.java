package proxy;

public class DefaultBookService implements BookService{

    Book book = new Book();

    @Override
    public void rent(Book book) {
        System.out.println("this book = " + book.getTitle());
    }

    @Override
    public String returnBook() {
        return book.getTitle();
    }
}
