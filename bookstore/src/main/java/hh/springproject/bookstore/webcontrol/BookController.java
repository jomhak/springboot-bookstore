package hh.springproject.bookstore.webcontrol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.springproject.bookstore.domain.Book;
import hh.springproject.bookstore.domain.BookRepository;
import hh.springproject.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// Index
	@GetMapping("/")
	public String showIndex() {
		return "index";
	}
	
	// Add book
	@GetMapping("/addbook")
	public String showAddbook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook";
	}
	
	// Save book
	@PostMapping("/savebook")
	public String saveBook(Book book) {
		bookRepository.save(book);
		return "redirect:booklist";
	}

	// List all books
	@GetMapping("/booklist")
	public String getAllBooks(Model model) {
		// List books from database
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}
	
	// Delete specific book
	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	// Edit specific book
	@GetMapping("/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", bookRepository.findById(bookId));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}
	
	// Rest methods
	
	//Rest: all books
	@GetMapping("/books")
	public @ResponseBody List<Book> jsonBook() {
		return (List<Book>) bookRepository.findAll();
	}
	
	//Rest: specific book
	@GetMapping("/books/{id}")
	public @ResponseBody Optional<Book> jsonBookById(@PathVariable("id") Long bookId) {
		return bookRepository.findById(bookId);
	}
	
}
