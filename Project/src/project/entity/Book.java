package project.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;

import project.itf.IBook;

public class Book implements IBook, Serializable{
	@Override
	public int hashCode() {
		return Objects.hash(author, categoryId, description, id, price, status, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(categoryId, other.categoryId)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price) && status == other.status
				&& Objects.equals(title, other.title);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	private String id;
	private String title;
	private boolean status;
	private float price;
	private String description;
	private String author;
	private String categoryId;
	
	public Book(String id, String title, boolean status, float price, String description, String author,
			String categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.price = price;
		this.description = description;
		this.author = author;
		this.categoryId = categoryId;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void inputData(List<Book> listbook, List<Category> listcategory, Locale locale) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ResourceBundle bund = ResourceBundle.getBundle("project.languages.lang", locale);
		System.out.println(bund.getString("Nhapvaoid")+bund.getString("4kytubatdaubangC"));
		do {
			id = sc.nextLine();
			if(id.length()!=4) {
				System.out.println(bund.getString("Idphaigom4kytu"));
			}
			else if(id.startsWith("C")==false) {
				System.out.println(bund.getString("IdphaibatdaubangchuC"));
			}
			else if(this.checkId(listbook,id)) {
				System.out.println(bund.getString("Idkhongduoctrung"));
			}
			else {
				break;
			}
		} while (true);
		System.out.println(bund.getString("Nhapvaotuadesach")+"("+bund.getString("650Kytu")+")");
		do {
			title = sc.nextLine();
			if(title.length()<6 && title.length()>50) {
				System.out.println(bund.getString("Tuadelonhon6nhohon50"));
			}
			else if(this.checkTitle(listbook, title)) {
				System.out.println(bund.getString("Tuadekhongduoctrung"));
			}
			else {
				break;
			}
		} while (true);
		System.out.println(bund.getString("Nhapvaostatus"));
		status = Boolean.parseBoolean(sc.nextLine());
		System.out.println(bund.getString("Nhapvaogiatien"));
		do {
			price=Float.parseFloat(sc.nextLine());
			if(price<=0) {
				System.out.println(bund.getString("Giasachlonhon0"));
			}
			else {
				break;
			}
		} while(true);
		System.out.println(bund.getString("Nhapvaomota"));
		description = sc.nextLine();
		System.out.println(bund.getString("Nhapvaotentacgia"));
		do {
			author=sc.nextLine();
			if(author.equals("")) {
				System.out.println(bund.getString("Tentacgiakhongduocdetrong"));
			}
			else {
				break;
			}
		} while(true);
		System.out.println(bund.getString("Nhapvaoiddanhmuc"));
		do {
			try {
				categoryId = sc.nextLine();
				if(this.checkCatId(listcategory, categoryId)==false) {
					System.out.println(bund.getString("Danhmuckhongtontai"));
				}
				else if(categoryId==null) {
					System.out.println(bund.getString("Danhmuckhongduocdetrong"));
				}
				else {
					break;
				}
			} catch (Exception e) {
				break;
			}
		} while (true);
	}
	@Override
	public void displayData() {
		// TODO Auto-generated method stub
		System.out.printf("%S  ||\t%-25S ||\t%-10S ||\t%-10.0f ||\t%-20S ||\t%-20S ||\t%S\n",id,title,status?"còn":"hết",price,description,author,categoryId);

	}
	@Override
	public boolean checkTitle(List<Book> listbook, String title) {
		boolean check = false;
		for (Book bk : listbook) {
			if(bk.title.equals(title)) {
				check = true;
			}
		}
		
		return check;
	}
	
	@Override
	public boolean checkCatId(List<Category> listcategory, String categoryId) {
		boolean check = false;
		for (Category cat : listcategory) {
			if(cat.getId().equals(categoryId)) {
				check = true;
			}
		}
		return check;
	}
	@Override
	public void updateData(List<Book> listbook, List<Category> listcategory, Book book, Locale locale) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ResourceBundle bund = ResourceBundle.getBundle("project.languages.lang", locale);
		int lc;
		do {
			System.out.println("*************"+bund.getString("Danhsachthongtinsach")+"**************");
			System.out.println("1. "+bund.getString("Tensach"+": ")+book.getTitle());
			System.out.println("2. "+bund.getString("Trangthai"+": ")+book.isStatus());
			System.out.println("3. "+bund.getString("Giatien"+": ")+book.getPrice());
			System.out.println("4. "+bund.getString("Mota"+": ")+book.getDescription());
			System.out.println("5. "+bund.getString("Tentacgia"+": ")+book.getAuthor());
			System.out.println("6. "+bund.getString("Madanhmuc"+": ")+book.getCategoryId());
			System.out.println("0. "+bund.getString("Thoat"));
			System.out.println(bund.getString("Suluachoncuaban"));
			lc = Integer.parseInt(sc.nextLine());
			switch (lc) {
			case 0:
				break;
			case 1:
				System.out.println(bund.getString("Nhapvaotuadesach")+"("+bund.getString("650Kytu")+")");
				do {
					title = sc.nextLine();
					if(title.length()<6 && title.length()>50) {
						System.out.println(bund.getString("Tuadelonhon6nhohon50"));
					}
					else if(this.checkTitle(listbook, title)) {
						System.out.println(bund.getString("Tuadekhongduoctrung"));
					}
					else {
						break;
					}
				} while (true);
				break;
			case 2:
				System.out.println(bund.getString("Nhapvaostatus"));
				status = Boolean.parseBoolean(sc.nextLine());
				break;
			case 3:
				System.out.println(bund.getString("Nhapvaogiatien"));
				do {
					price=Float.parseFloat(sc.nextLine());
					if(price<=0) {
						System.out.println(bund.getString("Giasachlonhon0"));
					}
					else {
						break;
					}
				} while(true);
				break;
			case 4:
				System.out.println(bund.getString("Nhapvaomota"));
				description = sc.nextLine();
				break;
			case 5:
				System.out.println(bund.getString("Nhapvaotentacgia"));
				do {
					author=sc.nextLine();
					if(author.equals("")) {
						System.out.println(bund.getString("Tentacgiakhongduocdetrong"));
					}
					else {
						break;
					}
				} while(true);
				break;
			case 6:
				System.out.println(bund.getString("Nhapvaoiddanhmuc"));
				do {
					try {
						categoryId = sc.nextLine();
						if(this.checkCatId(listcategory, categoryId)==false) {
							System.out.println(bund.getString("Danhmuckhongtontai"));
						}
						else if(categoryId==null) {
							System.out.println(bund.getString("Danhmuckhongduocdetrong"));
						}
						else {
							break;
						}
					} catch (Exception e) {
						break;
					}
				} while (true);
				break;
			default:
				System.out.println(bund.getString("Vuilongnhaplai"));
				break;
			}
		}while(lc!=0);
	}
	@Override
	public boolean checkId(List<Book> listbook, String id2) {
		// TODO Auto-generated method stub
			boolean check = false;
			for (Book bk : listbook) {
				if(bk.getId().equals(id2)) {
					check = true;
				}
			}
			return check;
	}
	
}
