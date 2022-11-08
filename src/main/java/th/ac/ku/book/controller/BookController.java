package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.book.dto.BookDto;
import th.ac.ku.book.service.BookService;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", service.getBooks());
        return "book";
    }

    @GetMapping("/add")
    public String getBookForm(BookDto bookDto) {
        return "book-add";
    }

    @PostMapping("/add")
    public String addBook(@Valid BookDto bookDto,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            return "book-add";
        }

        service.addBook(bookDto);
        return "redirect:/book";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable("id") Integer id,
                             @Valid BookDto bookDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "book";
        }

        return "book";
    }

    @PostMapping
    public String deleteBook(@Valid BookDto bookDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "book";
        }

        return "book";
    }

    public String buyBook(@Valid BookDto bookDto,
                          BindingResult result,
                          Model model) {
        return "book";
    }
}
