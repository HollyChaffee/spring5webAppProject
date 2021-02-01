package com.hollychaffee.spring5webapp.controllers;

import com.hollychaffee.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // Tells Spring Framework that our intent is to make this into a Spring MVC controller
public class BookController {

    private final BookRepository bookRepository; // Inject a book repository

    public BookController(BookRepository bookRepository) {  // Constructor-Spring managed component.
        this.bookRepository = bookRepository;               // When Spring instantiates this it will inject an instance of the book repository into the controller.
    }

    @RequestMapping("/books")  // When we do an action against the URL of books this method will be invoked by the Spring MVC Framework.
    public String getBooks(Model model) {  // Gives us the Model object. This is what is going to get returned to the view. The view will get a copy of the model.

        model.addAttribute("books", bookRepository.findAll());  // At runtime, when Spring gets a request to the URL/books it will execute the getBooks
        // method and provide that method a model object. Will give us a list of books. Model gets returned back to the view layer with the
        // attribute "books" and a list of books. We then will be able to utilize that value inside the view layer to apply the necessary view to be returned back to the client.
        return "books";
    }
}
