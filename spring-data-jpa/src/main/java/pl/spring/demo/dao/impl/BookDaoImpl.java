package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableBookId;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookDaoImpl implements BookDao {

    private final Set<BookTo> ALL_BOOKS = new HashSet<>();

    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookTo> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookTo> findBookByTitle(String title) {
    	List<BookTo> books = new ArrayList<BookTo>();
    	for (BookTo book : ALL_BOOKS) {
    		if (book.findByTitle(title)) {
    			books.add(book);
    		}
        }
    	return books;
    }
	

	@Override
    public List<BookTo> findBooksByAuthor(String author) {
		List<BookTo> books = new ArrayList<BookTo>();
    	for (BookTo book : ALL_BOOKS) {
    		if (book.findByAuthor(author)) {
    			books.add(book);
    		}
        }
    	return books;
    }
    
    @Override
    @NullableId
    @NullableBookId
    public BookTo save(BookTo book) {
        ALL_BOOKS.add(book);
        return book;
    }

    private void addTestBooks() {
        ALL_BOOKS.add(new BookTo(1L, "Romeo i Julia", "Wiliam", "Szekspir"));
        ALL_BOOKS.add(new BookTo(2L, "Opium w rosole", "Hanna", "Ożogowska"));
        ALL_BOOKS.add(new BookTo(3L, "Przygody Odyseusza", "Jan", "Parandowski"));
        ALL_BOOKS.add(new BookTo(4L, "Awantura w Niekłaju", "Edmund", "Niziurski"));
        ALL_BOOKS.add(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew", "Nienacki"));
        ALL_BOOKS.add(new BookTo(6L, "Zemsta", "Aleksander", "Fredro"));
    }
  }
