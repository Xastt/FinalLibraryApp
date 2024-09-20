package xast.FinalLibraryApp.models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "Enter your name!")
    @Size(min = 1, max = 60, message = "Your name should be between 10 and 60!")
    private String fullName;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Your data born should be more than 1900!")
    private int data_born;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {
    }

    public Person(String fullName, int data_born) {
        this.fullName = fullName;
        this.data_born = data_born;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public int getData_born(){
        return data_born;
    }
    public void setData_born(int data_born){
        this.data_born = data_born;
    }

    public List<Book> getBooks(){
        return books;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }
}

