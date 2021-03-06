package hh.springproject.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.springproject.bookstore.domain.Book;
import hh.springproject.bookstore.domain.BookRepository;
import hh.springproject.bookstore.domain.Category;
import hh.springproject.bookstore.domain.CategoryRepository;
import hh.springproject.bookstore.domain.User;
import hh.springproject.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	
	//test data for H2
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("Create users");
			// user/salasana, admin/sanasala
			userRepository.save(new User("user", "$2a$10$nBgjJ9qQB2poMNZkY2D5HuAscR7wTmOVtJvMb7Dn.r2JNGyCZNvEG", "USER"));
			userRepository.save(new User("admin", "$2a$10$rK1ortSlb6ompzrcsDTSaOG0DPxPm3scube7fFNiXYN3w9IYX08TG", "ADMIN"));
			
			
			log.info("Save some categories");
			categoryRepository.save(new Category("Music"));
			categoryRepository.save(new Category("Poetry"));
			categoryRepository.save(new Category("History"));
			
			log.info("Save some sample books");
			bookRepository.save(new Book("Book title 1", "Author 1", 2010, "ISBN 1", 15.99, categoryRepository.findByName("Music")));
			bookRepository.save(new Book("Book title 2", "Author 2", 2012, "ISBN 2", 99.90, categoryRepository.findByName("History")));
			
			
			
			
		log.info("Fetch all the categories");	
		for (Category category : categoryRepository.findAll()) {
			log.info(category.toString());
		}
		
			
		log.info("Fetch all the books");	
		for (Book book : bookRepository.findAll()) {
			log.info(book.toString());
		}
		};
	}
}
