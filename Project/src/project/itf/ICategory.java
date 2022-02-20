package project.itf;

import java.util.List;
import java.util.Locale;

import project.entity.Category;

public interface ICategory {
public void inputData(List<Category> listcategory, Locale locale);
public void displayData();
public boolean checkId(List<Category> listcategory, String id2);
public boolean checkName(List<Category> listcategory, String name);
}
