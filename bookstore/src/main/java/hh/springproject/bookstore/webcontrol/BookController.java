package hh.springproject.bookstore.webcontrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.springproject.bookstore.domain.Book;
import hh.springproject.bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@GetMapping("/addbook")
	public String showAddbook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@PostMapping("/savebook")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

	@GetMapping("/booklist")
	public String getAllBooks(Model model) {
		// List books from database
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}
	
	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping("/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		return "editbook";
	}
	
}
