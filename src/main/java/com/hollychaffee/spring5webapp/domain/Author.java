package com.hollychaffee.spring5webapp.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity  // Annotates the class and makes the POJO into an official JPA (Java Persistence API) entity class
public class Author {

    @Id  // Annotation used to specify the primary key of the entity. In ORM (Object Relational Mapping), every object needs to have a unique identifier.
    @GeneratedValue(strategy = GenerationType.AUTO)  // Annotation used to specify how the primary key should be generated. Underlying database provides the generation.
    private Long id;  // The database will be assigning the id
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")  //Many to many relationship to books, mapped by authors
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {  // Implements a functional toString method that will show the properties of the object if we need to use a toString method
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {  // Setting up id property to determine equality
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return id != null ? id.equals(author.id) : author.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

