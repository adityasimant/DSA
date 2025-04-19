package LLD.BookManagementSystem2;

import java.util.List;
import java.util.UUID;

enum UserRole{
    ADMIN,
    USER
}

public class User {
    private String name;
    private UUID id;
    private List<Book> userBookList;
    private boolean isAuthenticated;
    private UserRole userRole;

    User(UUID id, String name, boolean isAdmin){
        this.name = name;
        this.id = id;
        this.userRole = isAdmin? UserRole.ADMIN : UserRole.USER;
    }

    public UUID getUserId(){
        return id;
    }

    public void authenticateUser(){
        this.isAuthenticated = true;
    }

    public String getUserName(){
        return this.name;
    }

    public boolean isUserAuthenticated(){
        return this.isAuthenticated;
    }

    public List<Book> getBooks(){
        return userBookList;
    }
    public void removeBook(Book book){

        for (int i = 0; i < userBookList.size(); i++) {
            if (userBookList.get(i).getBookName().equals(book.getBookName().toLowerCase())){
                userBookList.remove(i);
                System.out.println("Book returned!");
                return;
            }
        }

        System.out.println("No book found!");
    }

    public void borrowBook(Book book){
        userBookList.add(book);
    }

    public Book returnBook(Book book){
        for (int i=0; i<userBookList.size(); i++){
            if (book.getBookName().equals(userBookList.get(i))){
                Book bookToReturn = userBookList.get(i);
                userBookList.remove(i);
                return bookToReturn;
            }
        }
        return null;
    }
}
