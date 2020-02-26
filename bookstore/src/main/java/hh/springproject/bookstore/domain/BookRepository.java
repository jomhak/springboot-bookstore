package hh.springproject.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>{

	
	public Book findByAuthor(String author);
}
