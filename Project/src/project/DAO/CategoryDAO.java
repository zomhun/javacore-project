package project.DAO;

import java.util.List;

import project.entity.Category;

public interface CategoryDAO {
	public List<Category> getAll();
	public boolean add(Category category);
	public boolean delete(String id);
	public Category getById(String id);
	public Category getByName(String name);
}
