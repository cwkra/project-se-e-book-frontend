package th.ac.ku.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.book.dto.BookDto;
import th.ac.ku.book.security.JwtAccessTokenService;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtAccessTokenService tokenService;

    public List<BookDto> getBooks() {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        HttpEntity entity = new HttpEntity(headers);

        String url = "http://localhost:8090/book";

        ResponseEntity<BookDto[]> response =
                restTemplate.exchange(url, HttpMethod.GET,
                        entity, BookDto[].class);

        BookDto[] books = response.getBody();
        return Arrays.asList(books);
    }

    public BookDto addBook(BookDto book) {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(book,headers);

        String url = "http://localhost:8090/book";

        ResponseEntity<BookDto> response =
                restTemplate.exchange(url, HttpMethod.POST,
                        entity, BookDto.class);

        return response.getBody();
    }

    public BookDto updateBook(BookDto book) {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(book,headers);

        String url = "http://localhost:8090/book";

        ResponseEntity<BookDto> response =
                restTemplate.exchange(url, HttpMethod.PUT, entity, BookDto.class);
        System.out.println(response.getBody().toString());
        return response.getBody();
    }

    public BookDto deleteBook(BookDto book) {
        String token = tokenService.requestAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + token);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity entity = new HttpEntity(book,headers);

        String url = "http://localhost:8090/book/" + book.getId();

        ResponseEntity<BookDto> response =
                restTemplate.exchange(url, HttpMethod.DELETE, entity, BookDto.class);

        return response.getBody();
    }
}
