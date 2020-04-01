package hh.springproject.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.springproject.bookstore.domain.Book;
import hh.springproject.bookstore.domain.BookRepository;
import hh.springproject.bookstore.domain.Category;
import hh.springproject.bookstore.domain.CategoryRepository;
import hh.springproject.bookstore.domain.User;
import hh.springproject.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	@Autowired
	private BookRepository bookrepository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	//Book tests
	@Test
	public void findBookAuthor() {
		List<Book> books = bookrepository.findByAuthor("Author 1");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("Book title 1");
	}
	
	@Test
	public void createNewBook() {
		Book book = new Book("Cool title", "Cooler author", 2002, "ISBNhere", 20.34, categoryrepository.findByName("History"));
		bookrepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewBook() {
		Book book = new Book("Deletable book", "Deletable author", 2007, "Deletable ISBN", 85.35, categoryrepository.findByName("History"));
		bookrepository.save(book);
		assertThat(book.getId()).isNotNull();
		long bookCount = bookrepository.count();
		bookrepository.deleteById(book.getId());
		assertThat(bookrepository.count()).isEqualTo(bookCount - 1);
	}
	
	
	
	//Category tests
	@Test
	public void findMusicCategory() {
		List<Category> categories = (List<Category>) categoryrepository.findAll();
		assertThat(categories).hasSize(3);
		assertThat(categories.get(0).getName()).isEqualTo("Music");
	}
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Drama");
		categoryrepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	
	@Test
	public void deleteNewCategory() {
		Category category = new Category("Delete category");
		categoryrepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
		long categoryCount = categoryrepository.count();
		categoryrepository.deleteById(category.getCategoryId());
		assertThat(categoryrepository.count()).isEqualTo(categoryCount - 1);
	}
	
	
	
	// User tests
	@Test
	public void findUserByUsername() {
		List<User> users = (List<User>) userrepository.findAll();
		assertThat(users).hasSize(2);
		assertThat(users.get(1).getUsername()).isEqualTo("admin");
	}
	
	@Test
	public void createNewUser() {
		User user = new User("pekka", "$2a$10$nBgjJ9qQB2poMNZkY2D5HuAscR7wTmOVtJvMb7Dn.r2JNGyCZNvEG", "USER");
		userrepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteNewUser() {
		User user = new User("deleteme", "$2a$10$nBgjJ9qQB2poMNZkY2D5HuAscR7wTmOVtJvMb7Dn.r2JNGyCZNvEG", "USER");
		userrepository.save(user);
		assertThat(user.getId()).isNotNull();
		long userCount = userrepository.count();
		userrepository.deleteById(user.getId());
		assertThat(userrepository.count()).isEqualTo(userCount - 1);
	}
	
	
	
}
