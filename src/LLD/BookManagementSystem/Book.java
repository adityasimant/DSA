package LLD.BookManagementSystem;



public class Book {
    private String bookName;
    private String author;
    private String bookGenre;
    private int totalPages;
    private float bookCost;

    Book(String bookName, String bookGenre, String author, int totalPages, float bookCost){
        this.bookCost = bookCost;
        this.bookName = bookName;
        this.author = author;
        this.bookGenre = bookGenre;
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }



    public float getBookCost(){
        return bookCost;
    }

    public String getBookName(){
        return this.bookName;
    }
}
