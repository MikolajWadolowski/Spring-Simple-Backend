package Spring.test.edu.dtos;

import Spring.test.edu.models.Book;

public class RentBookResponse {
    private Book book;
    private boolean success;
    private String errorMessage;


    public RentBookResponse(Book book, boolean success, String errorMessage) {
        this.book = book;
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static RentBookResponse buildFailure(String errorMessage) {
        return new RentBookResponse(null, false, errorMessage);
    }

    public static RentBookResponse buildSuccess(Book book) {
        return new RentBookResponse(book, true, null);
    }
}
