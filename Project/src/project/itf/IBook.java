package project.itf;

import java.util.List;
import java.util.Locale;

import project.entity.Book;
import project.entity.Category;

public interface IBook {
	public void inputData(List<Book> listbook, List<Category> listcategory, Locale locale);
	public void displayData();
	public void updateData(List<Book> listbook, List<Category> listcategory, Book book, Locale locale);
	public boolean checkTitle(List<Book> listbook, String title);
	public boolean checkCatId(List<Category> listcategory, String categoryId);
	public boolean checkId(List<Book> listbook, String id2);
}
