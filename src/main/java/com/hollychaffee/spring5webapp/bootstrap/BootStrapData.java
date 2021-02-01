package com.hollychaffee.spring5webapp.bootstrap;

import com.hollychaffee.spring5webapp.domain.Author;
import com.hollychaffee.spring5webapp.domain.Book;
import com.hollychaffee.spring5webapp.domain.Publisher;
import com.hollychaffee.spring5webapp.repositories.AuthorRepository;
import com.hollychaffee.spring5webapp.repositories.BookRepository;
import com.hollychaffee.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component  // Annotation-Spring Framework will autodetect the class as a Spring managed component for dependency injection
public class BootStrapData implements CommandLineRunner {  //CommandLineRunner is an interface. Spring will look for instances of this type,
                                                           // when they are found, will be run-implements run method

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;  // Adds in new property

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        // Spring will do dependency injection into the constructor for an instance of the author repository, the book repository, and the publisher repository
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;  // Modified the constructor to tell Spring Framework that we want an instance of the publisher
        // repository injected into this class using dependency injection
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher publisher = new Publisher();  // Created a new publisher object
        publisher.setName("SFG Publishing");
        publisher.setCity("St. Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);  // Saves the publisher object to the repository

        System.out.println("Publisher Count: " + publisherRepository.count());  // Gives an output of the count

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);  // Get the book and add the title
        ddd.getAuthors().add(eric);  // Get the author and add Eric as an author of the book

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        authorRepository.save(eric);  // Spring managed component has two properties (authorRepository and bookRepository) initialized inside the constructor as final variables
        bookRepository.save(ddd);  // Saves these into the h2 database. Tells the Spring Framework that it must inject an instance of these repositories
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");  // Adding another author
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");  // Adding another book
        rod.getBooks().add(noEJB);  // Get the book, add the title
        noEJB.getAuthors().add(rod);  // Get the author, add the author

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);  // Spring data JPA is utilizing Hibernate to save these to an in-memory h2 database.
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + publisher.getBooks().size());
    }
}

// Hibernate is generating the SQL (Structured Query Language), DDL (Data Definition Language) statements to go out and
// create the database tables based on our JPA (Java Persistence API) definitions.

// Our Entities are being persisted to an in-memory h2 database.

// By looking at the logs, we can see that Hibernate is initializing the database and generating the SQL statements
// based on the Entities we have created. When we populate the Entities and save them, Hibernate is creating the SQL
// statements to insert the data for us.