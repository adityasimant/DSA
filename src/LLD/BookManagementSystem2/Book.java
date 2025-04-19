package LLD.BookManagementSystem2;

import java.util.UUID;

public class Book {

    private UUID id;
    private String name;
    private String author;

    Book(String name, String author){
        this.id = UUID.randomUUID();
        this.name = name;
        this.author = author;
    }

    public String getBookName(){
        return  this.name;
    }

    @Override
    public String toString(){
        return "Id: "+this.id+", book Name: "+this.name+", Author: "+this.author;
    }

    public String getBookId(){
        return this.id.toString();
    }

    public String getAuthor(){
        return this.author;
    }

}

