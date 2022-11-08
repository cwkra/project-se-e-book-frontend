package th.ac.ku.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.book.service.BookService;

@Controller
public class HomeController {

    @Autowired
    private BookService service;

    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("books", service.getBooks());
        return "home";
    }
}
