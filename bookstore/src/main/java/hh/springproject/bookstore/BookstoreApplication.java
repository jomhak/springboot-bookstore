package hh.springproject.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.springproject.bookstore.domain.Book;
import hh.springproject.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	
	//testidata h2-tietokantaa varten
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("Save some sample books");
			bookRepository.save(new Book("Book title 1", "Author 1", 2010, "ISBN 1", 15.99));
			bookRepository.save(new Book("Book title 2", "Author 2", 2012, "ISBN 2", 99.90));
			
			
		log.info("Fetch all the books");	
		for (Book book : bookRepository.findAll()) {
			log.info(book.toString());
		}
		};
	}
}
