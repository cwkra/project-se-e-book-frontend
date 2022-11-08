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

    @PostMapping("/change")
    public String updateBook(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("price") double price,
                             @RequestParam("category") String category,
                             @RequestParam("author") String author) {
//        if (result.hasErrors()) {
//            return "book";
//        }
        System.out.println("test");
        BookDto bookDto = new BookDto();
        bookDto.setId(id);
        bookDto.setName(name);
        bookDto.setPrice(price);
        bookDto.setCategory(category);
        bookDto.setAuthor(author);
        System.out.println(bookDto.toString());

        service.updateBook(bookDto);

        return "redirect:/book";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") Integer id,
                             @RequestParam("name") String name,
                             @RequestParam("price") double price,
                             @RequestParam("category") String category,
                             @RequestParam("author") String author) {
//        if (result.hasErrors()) {
//            return "book";
//        }
        BookDto bookDto = new BookDto();
        bookDto.setId(id);
        bookDto.setName(name);
        bookDto.setPrice(price);
        bookDto.setCategory(category);
        bookDto.setAuthor(author);
        service.deleteBook(bookDto);

        return "redirect:/book";
    }

    @PostMapping
    public String buyBook(@Valid BookDto bookDto,
                          BindingResult result,
                          Model model) {
        return "redirect:/";
    }
}
