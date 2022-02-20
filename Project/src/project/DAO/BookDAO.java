package project.DAO;

import java.util.List;

import project.entity.Book;

public interface BookDAO {
	public List<Book> getAll();
	public boolean add(Book book);
	public boolean update(Book book,String id);
	public boolean delete(String id);
	public Book getById(String id);
	public List<Book> getByCategoryId(String categoryId);
	public List<Book> orderByPrice(float min, float max);
	public List<Book> orderByTitle();
	public List<Book> getByAuthorAndPrice(String author, float price);
	public boolean deletebyCategoryId(String categoryId);
}
