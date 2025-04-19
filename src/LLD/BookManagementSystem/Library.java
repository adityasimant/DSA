package LLD.BookManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> bookList;

    private int MAX_CAPACITY;

    public Library(int maxCapacity){
        this.MAX_CAPACITY = maxCapacity;
        bookList = new ArrayList<>();
    }

    public void addBook(Book book){
        if (bookList.size() == MAX_CAPACITY){
            throw new IllegalStateException("The Library is at max capacity!");
        }
        bookList.add(book);
    }

    public void removeBook(String book){
        if (bookList.size() == 0){
            throw new IllegalStateException("The Library is empty!");
        }
        int index = searchBook(book);
        if (index!=-1){
            bookList.remove(index);
            System.out.println("Successfully removed the book "+book);
        }else{
            System.out.println("Couldn't find the book to delete!");
        }
    }

    public int searchBook(String book){
        if (bookList.isEmpty()){
            throw new IllegalStateException("The Library is empty!");
        }
        for (int i=0; i<bookList.size(); i++){
            Book currentBook = bookList.get(i);
            if (currentBook.getBookName().equals(book)){
                return i;
            }
        }
        return -1;
    }

    public void listBooks(){
        if (bookList.isEmpty()){
            System.out.println("The Library is empty!");
        }else{
            for (int i=0; i<bookList.size(); i++){
                Book currentBook = bookList.get(i);
                System.out.println("Book Name: " + currentBook.getBookName());
                System.out.println();
            }
        }
    }
}
