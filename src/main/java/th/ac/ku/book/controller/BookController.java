package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
