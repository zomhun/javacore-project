package project.entity;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import project.itf.ICategory;

public class Category implements ICategory{
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	private String id;
	private String name;
	private boolean status;
	private String parentId;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String id, String name, boolean status, String parentId) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.parentId = parentId;
	}
	@Override
	public void inputData(List<Category> listcategory,Locale locale) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ResourceBundle bund = ResourceBundle.getBundle("project.languages.lang", locale);
		System.out.println(bund.getString("Nhapvaoid"));
		do {
			id = sc.nextLine();
			if(Integer.parseInt(id)<=0) {
				System.out.println(bund.getString("Idphailonhon0"));
			}
			else if(this.checkId(listcategory,id)) {
				System.out.println(bund.getString("Idkhongduoctrung"));
			}
			else {
				break;
			}
		} while (true);
		System.out.println(bund.getString("Nhapvaoten"));
		do {
			name = sc.nextLine();
			if(name.equals("")) {
				System.out.println(bund.getString("Tenkhongduocdetrong"));
			}
			else if(this.checkName(listcategory, name)) {
				System.out.println(bund.getString("Tenkhongduoctrung"));
			}
			else {
				break;
			}
		} while (true);
		System.out.println(bund.getString("Nhapvaostatus"));
		status = Boolean.parseBoolean(sc.nextLine());
		System.out.println(bund.getString("Nhapvaomadanhmuccha"));
		do {
			try {
				parentId = sc.nextLine();
				if(this.checkId(listcategory, parentId) || parentId.equals("0")) {
					break;
				}
				else if(parentId.equals("")){
					parentId = "0";
					break;
				}	else {
					System.out.println(bund.getString("Danhmucchakhongtontai"));
				}
			} catch (Exception e) {
				
				break;
			}
		} while (true);
	}
	@Override
	public void displayData() {
		// TODO Auto-generated method stub
		System.out.printf("%S  ||\t%-20S ||\t%-10B||\t%S\n",id,name,status,parentId);

	}
	@Override
	public boolean checkName(List<Category> listcategory, String name) {
		boolean check = false;
		for (Category cat : listcategory) {
			if(cat.name.equals(name)) {
				check = true;
			}
		}
		
		return check;
	}
	@Override
	public boolean checkId(List<Category> listcategory, String id2) {
		boolean check = false;
		for (Category cat : listcategory) {
			if(cat.id.equals(id2)) {
				check = true;
			}
		}
		return check;
	}
}
