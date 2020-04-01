package hh.springproject.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>{

	
	public List<Book> findByAuthor(String author);
}
