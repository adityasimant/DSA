package LLD.BookManagementSystem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

class Library {
    List<Book> bookList;
    List<User> currentUserList;

    Library(){
        bookList = new ArrayList<>();
    }

    public void viewAllBooks(UserRole userRole){
        if (userRole.equals(UserRole.ADMIN)){
            for(Book currentBook : bookList){
                System.out.println(currentBook.toString());
            }
        }else{
            System.out.println("Permission Denied!");
        }
    }

    public void addBook(Book book){
        bookList.add(book);
    }

    public void removeBook(Book book){
        for (int i=0; i<bookList.size(); i++){
            if (bookList.get(i).getBookName() == book.getBookName()){
                bookList.remove(i);
            }
        }
    }

    public void createUser(User user){
        currentUserList.add(user);
    }

    public void returnBook(UUID userId, Book book){
        for (int i=0; i<currentUserList.size(); i++){
            if (currentUserList.get(i).getUserId() == userId){
                currentUserList.get(i).removeBook(book);
                addBook(book);
            }
        }
    }


    public void borrowBook(UUID userId, Book book){
        for (int i=0; i<currentUserList.size(); i++){
            if (currentUserList.get(i).getUserId() == userId){
                currentUserList.get(i).returnBook(book);
                removeBook(book);
            }
        }
    }

    public List<Book> getUserBooks(UUID userId){
        for (int i=0; i<currentUserList.size(); i++){
            if (currentUserList.get(i).getUserId() == userId){
                return  currentUserList.get(i).getBooks();
            }
        }
        System.out.println("No books found!");
        return Collections.emptyList();
    }
}
