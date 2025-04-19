package LLD.BookManagementSystem;

import java.util.*;

public class LibraryMain {

    static Scanner sc = new Scanner(System.in);
    static Library library;

    public static void main(String[] args) {
        System.out.println("Welcome to book management system!");
        System.out.println("What is the maximum capacity of library?");
        int librarySize = sc.nextInt();
        library  = new Library(librarySize);
        boolean exit = false;

        while (true){
            System.out.println("What operation would you like to perform?");
            System.out.println("1 -> Add a book");
            System.out.println("2 -> remove a book");
            System.out.println("3 -> search a book");
            System.out.println("4 -> list all books");
            System.out.println("5 -> exit");

            int choice = sc.nextInt();

            switch (choice){
                case 1 :
                    addBookMain();
                    break;
                case 2 :
                    removeBookMain();
                    break;
                case 3 :
                    searchBookMain();
                    break;
                case 4 :
                    listAllBooksMain();
                    break;
                case 5 :
                    exit = true;
                    System.out.println("Thank you for using :)");
                    break;
                default:
                    System.out.println("Invalid input! please make sure you choose only the provided options...");
                    break;
            }
            if (exit) break;
        }
    }

    public static void addBookMain(){
        System.out.println("What is the book name?");
        String name = sc.next();
        System.out.println("What is total number of pages?");
        int totalPages = sc.nextInt();
        System.out.println("What genre?");
        String genre = sc.next();
        System.out.println("What is the book cost?");
        float cost = sc.nextFloat();
        System.out.println("author name?");
        String author = sc.next();

        Book book = new Book(name, genre, author, totalPages, cost);
        library.addBook(book);
        System.out.println("Book added!");
    }

    public static void removeBookMain(){
        System.out.println("What is the book name?");
        String name = sc.next();
        library.removeBook(name);
    }

    public static void searchBookMain(){
        System.out.println("What is the book name?");
        String name = sc.next();
        int status = library.searchBook(name);
        if (status == -1){
            System.out.println("Couldn't find the book");
        }else{
            System.out.println("Found the book at "+status+1+" position");
        }
    }

    public static void listAllBooksMain(){
        library.listBooks();
    }
}
