package LLD.BookManagementSystem2;


import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class libraryMain {

    static Library library = new Library();
    static UUID currentUserId;
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Welcome to library Management II");
        boolean breakLoop = false;
        int choice;
        boolean isRegistered = false;

        while (breakLoop){
            System.out.println("Please choose an option from the following:");
            System.out.println("1 -> Register User" +
                    "2 -> borrow a book"+
                    "3 -> return a book"+
                    "4 -> view all books"+
                    "5 -> exit");
            choice = sc.nextInt();

            switch (choice){
                case 1 :
                    if (isRegistered) {
                        System.out.println("you're already registered!");
                    }else{
                        currentUserId = UUID.randomUUID();
                        registerUser(currentUserId);
                    }

                case 2 :
                    if (currentUserId != null){
                        borrowBook(currentUserId);
                    }else{
                        System.out.println("Please register before borrowing.");
                    }

                case 3 :
                    if (currentUserId != null){
                        returnBook(currentUserId);
                    }else{
                        System.out.println("Please register before using the app.");
                    }

                case 4 :
                    if (currentUserId != null){
                        viewAllBooks(currentUserId);
                    }else{
                        System.out.println("Please register before using the app.");
                    }

                case 5 :
                    System.out.println("Thanks for using the app!");
                    breakLoop = true;
                    break;
            }
        }
    }

    public  static  void registerUser(UUID userId){
        System.out.println("Please enter your name");
        String name = sc.nextLine();
        System.out.println("Are you admin?");
        boolean isAdmin = sc.hasNext();
        User user = new User(userId, name, isAdmin);
        library.createUser(user);
    }

    public  static  void borrowBook(UUID userId){

    }

    public  static  void returnBook(UUID userId){
        List<Book> userBooks = library.getUserBooks(userId);
        if (userBooks.isEmpty()) System.out.println("You dont have any books");
        else {
            System.out.println("Choose which book to return, please type the name");
            System.out.println(userBooks.toString());
            String book = sc.nextLine();
//            library.returnBook(currentUserId, book);
        }
    }

    public  static  void viewAllBooks(UUID userId){

    }
}
