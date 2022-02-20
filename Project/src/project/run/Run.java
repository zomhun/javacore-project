package project.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import project.DAO.BookDAOimp;
import project.DAO.CategoryDAOimp;
import project.entity.Book;
import project.entity.Category;



public class Run{
//	public static void createFile(String path, Locale locale) {
//		ResourceBundle bund = ResourceBundle.getBundle("project.languages.lang", locale);
//		File file = new File(path);
//		if(file.exists()) {
//			System.err.println(bund.getString("Filedatontai"));
//		} else {
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println(bund.getString("Taofilethanhcong")+file.getAbsolutePath());
//		}
//
//	}
	public static void writeObject(String path, List<Book> list1, Locale locale) {
//		createFile("C:\\Users\\datpt\\eclipse-workspace\\Project\\book.txt", locale);
		ResourceBundle bund = ResourceBundle.getBundle("project.languages.lang", locale);
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			ObjectOutputStream stream = new ObjectOutputStream(outputStream);
			stream.writeObject(list1);
			System.out.println(bund.getString("Xuatfilethanhcong"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<Book> readObject(String path){
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(path);
			ObjectInputStream stream = new ObjectInputStream(fileInputStream);
			List<Book> data = (List<Book>) stream.readObject();
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		CategoryDAOimp cateImp = new CategoryDAOimp();
		Category category = new Category();
		List<Category> listcategory = cateImp.getAll();
		BookDAOimp bookImp = new BookDAOimp();
		Book book = new Book();
		List<Book> listbook = bookImp.getAll();
		Scanner sc = new Scanner(System.in);
		Locale locale = Locale.getDefault();

		System.out.println("lang 1.EN-2.VN-");
		int luaChon = Integer.parseInt(sc.nextLine());
		if(luaChon == 1) {
			locale = new Locale("en","US");

		} else {
			locale = new Locale("vi","VN");

		}
		ResourceBundle bund = ResourceBundle.getBundle("project.languages.lang", locale);
		theLabel:do{
			System.out.println("*****************Menu******************");
			System.out.println("1." + bund.getString("Quanlydanhmuc"));
			System.out.println("2." + bund.getString("Quanlysach"));
			System.out.println("3." + bund.getString("Thoat"));
			System.out.println(bund.getString("Suluachoncuaban"));
			try {   
			    String str = sc.nextLine();  
			    int lc=  Integer.parseInt(str);
				switch (lc) {
				case 1:
					theLabel1: do{
						System.out.println("*****************" + bund.getString("Quanlydanhmuc") + "******************");
						System.out.println("1." + bund.getString("Danhsachdanhmuc"));
						System.out.println("2." + bund.getString("Themdanhmuc"));
						System.out.println("3." + bund.getString("Xoadanhmuc"));
						System.out.println("4." + bund.getString("Timkiemdanhmuc"));
						System.out.println("5." + bund.getString("Quaylai"));
						System.out.println(bund.getString("Suluachoncuaban"));
						try {   
						    str = sc.nextLine();  
						    lc=  Integer.parseInt(str);
							switch (lc) {
							case 1:
								do {
									System.out.println("*****************"+ bund.getString("Danhsachdanhmuc")+"******************");
									System.out.println("1." + bund.getString("Danhsachcaydanhmuc"));
									System.out.println("2." + bund.getString("Thongtinchitietdanhmuc"));
									System.out.println("3." + bund.getString("Quaylai"));
									System.out.println(bund.getString("Suluachoncuaban"));
									try {   
									    str = sc.nextLine();  
									    lc=  Integer.parseInt(str);
										switch (lc) {
										case 1:
											System.out.println(bund.getString("Danhsachcaydanhmuc"));
											int stt1 = 1;
											for (Category cate : listcategory) {
												if(cate.getParentId().equals("0")) {
													System.out.printf("%d.%s\n",stt1,cate.getName());
													int stt2 = 1;
													for(Category cate2 : listcategory) {
														if(cate.getId().equals(cate2.getParentId())) {
															System.out.printf("\t%d.%d.%s\n",stt1,stt2,cate2.getName());
															int stt3 = 1;
															for (Category cate3 : listcategory) {
																if(cate2.getId().equals(cate3.getParentId())) {
																	System.out.printf("\t\t%d.%d.%d.%s\n",stt1,stt2,stt3,cate3.getName());
																	stt3++;
																}
															}
															stt2++;
														}
													}
													stt1++;
												}
											}
											break;
										case 2:
											System.out.println(bund.getString("Thongtinchitietdanhmuc"));
											System.out.println(bund.getString("Nhapvaoiddanhmuc"));
											String id = sc.nextLine();
											Category cate1 = cateImp.getById(id);
											if(cate1.getId() != null) {
												System.out.printf("id ||\t%-20S ||\t%-10S||\t" +bund.getString("Madanhmuc") + "\n",bund.getString("Tendanhmuc"),bund.getString("Trangthai"));
												cate1.displayData();}
											else {
												System.out.println("không có danh mục id ="+id);
											}
											break;
										case 3:
											continue theLabel1;
										default:
											System.out.println(bund.getString("Vuilongnhaplai"));
											break;
										}
									}
									catch(Exception e) {  
									     System.out.println("Vui lòng nhập số");               
								    }
								}while(true);
							case 2:
								String cont;
								do {
									Category cat = new Category();
									cat.inputData(listcategory,locale);
									listcategory.add(cat);
									System.out.println(bund.getString("Bancomuontieptuc")+"(y/n)");
									cont = sc.nextLine();
									if(cateImp.add(cat)) {
										System.out.println(bund.getString("Themmoithanhcong"));
										listcategory = cateImp.getAll();
									}else {
										System.out.println(bund.getString("Themmoithatbai"));
									}
								}while(cont.equals("y"));
								break;
							case 3:
								System.out.println(bund.getString("Nhapidmuonxoa"));
								String idDelete = sc.nextLine();
								Boolean child = false;
								List<Category> listcate = new ArrayList<Category>();
								List<Book> list = new ArrayList<Book>();
								for (Category cate : listcategory) {
									if(cate.getParentId().equals("0")) {
										if(cate.getId().equals(idDelete)) {
											for(Category cate2 : listcategory) {
												if(cate.getId().equals(cate2.getParentId())) {
													for (Category cate3 : listcategory) {
														if(cate2.getId().equals(cate3.getParentId())) {
															listcate.add(cate3);
															child = true;
														}
													}
												}
											}
										}
										else {
											for(Category cate2 : listcategory) {
												if(cate.getId().equals(cate2.getParentId())) {
													if(cate2.getId().equals(idDelete)) {
														for (Category cate3 : listcategory) {
															if(cate2.getId().equals(cate3.getParentId())) {
																listcate.add(cate3);
																child = true;
															}
														}
													}
													else {
														for (Category cate3 : listcategory) {
															if(cate2.getId().equals(cate3.getParentId())) {
																if(cate3.getId().equals(idDelete)) {
																	listcate.add(cate3);
																	child = false;
																}
															}
														}
													}
												}
											}
										}
									}
								}
								for (Category ct2 : listcate) {
									list.addAll(bookImp.getByCategoryId(ct2.getId()));
								}
								if(child) {
									System.out.println(bund.getString("Danhmucdangchuadanhmuccon"));
								}
								else if(list.isEmpty()){
									if(cateImp.delete(idDelete)) {
										System.out.println(bund.getString("Xoathanhcong"));
										listcategory = cateImp.getAll();
									}
									else {
										System.err.println(bund.getString("Xoathatbai"));
									}
								}
								else {
									System.out.println(bund.getString("Danhmucdangchuadulieubancomuonxoakhong"));
									String conf = sc.nextLine();
									if(conf.equalsIgnoreCase("y")) {
										cateImp.delete(idDelete);
										bookImp.deletebyCategoryId(idDelete);
										if(cateImp.delete(idDelete)) {
											System.out.println(bund.getString("Xoathanhcong"));
											listcategory = cateImp.getAll();
										}
										else {
											System.err.println(bund.getString("Xoathatbai"));
										}
									}
								}
								
								break;
							case 4:
								System.out.println(bund.getString("Nhaptendanhmucmuontim"));
								String nameSearch = sc.nextLine();
								Category cat = cateImp.getByName(nameSearch);
								System.out.printf("id ||\t%-20S ||\t%-10S||\t" +bund.getString("Madanhmuc") + "\n",bund.getString("Tendanhmuc"),bund.getString("Trangthai"));
								if(cat!=null) {
									cat.displayData();
								} else {
									System.out.println(bund.getString("Tendanhmuckhongtontai"));
								}
								break;
							case 5:
								continue theLabel;
							}
						}
						catch(Exception e) {  
							System.out.println("Vui lòng nhập số");               
					    }
					}while(true);
				case 2:
					theLabel2: do {
						System.out.println("*****************" + bund.getString("Quanlysach") + "******************");
						System.out.println("1. " + bund.getString("Themmoi"));
						System.out.println("2. " + bund.getString("Hienthithongtin"));
						System.out.println("3. " + bund.getString("SapXep"));
						System.out.println("4. " + bund.getString("Capnhatthongtin"));
						System.out.println("5. " + bund.getString("Quaylai"));
						System.out.println(bund.getString("Suluachoncuaban"));
						try {   
						    str = sc.nextLine();  
						    lc=  Integer.parseInt(str);
							switch(lc) {
							case 1:
								String cont;
								do {
									Book bk = new Book();
									bk.inputData(listbook,listcategory,locale);
									listbook.add(bk);
									System.out.println(bund.getString("Bancomuontieptuc")+"(y/n)");
									cont = sc.nextLine();
									if(bookImp.add(bk)) {
										System.out.println(bund.getString("Themmoithanhcong"));
										listbook = bookImp.getAll();
									} else {
										System.out.println(bund.getString("Themmoithatbai"));
									}
								}while(cont.equals("y"));
								break;
							case 2:
								do {
									System.out.println("*****************" + bund.getString("Thongtin") + "******************");
									System.out.println("1. " + bund.getString("Hienthisachtheodanhmuc"));
									System.out.println("2. " + bund.getString("Hienthichitietsach"));
									System.out.println("3. "  + bund.getString("Quaylai"));
									System.out.println(bund.getString("Suluachoncuaban"));
									try {   
									    str = sc.nextLine();  
									    lc=  Integer.parseInt(str);
										switch(lc) {
										case 1:
											List<Category> listcate = new ArrayList<Category>();
											List<Book> list = new ArrayList<Book>();
											System.out.println(bund.getString("Thongtinsachtheodanhmuc"));
											System.out.println(bund.getString("Danhsachcaydanhmuc"));
											for (Category cate : listcategory) {
												if(cate.getParentId().equals("0")) {
													System.out.printf("%s.%s\n",cate.getId(),cate.getName());
													for(Category cate2 : listcategory) {
														if(cate.getId().equals(cate2.getParentId())) {
															System.out.printf("\t%s.%s\n",cate2.getId(),cate2.getName());
															for (Category cate3 : listcategory) {
																if(cate2.getId().equals(cate3.getParentId())) {
																	System.out.printf("\t\t%s.%s\n",cate3.getId(),cate3.getName());
																}
															}
														}
													}
												}
											}
											System.out.println(bund.getString("Nhapiddanhmucmuontim"));
											String ctId = sc.nextLine();
											for (Category cate : listcategory) {
												if(cate.getParentId().equals("0")) {
													if(cate.getId().equals(ctId)) {
														for(Category cate2 : listcategory) {
															if(cate.getId().equals(cate2.getParentId())) {
																for (Category cate3 : listcategory) {
																	if(cate2.getId().equals(cate3.getParentId())) {
																		listcate.add(cate3);
																	}
																}
															}
														}
													}
													else {
														for(Category cate2 : listcategory) {
															if(cate.getId().equals(cate2.getParentId())) {
																if(cate2.getId().equals(ctId)) {
																	for (Category cate3 : listcategory) {
																		if(cate2.getId().equals(cate3.getParentId())) {
																			listcate.add(cate3);
																		}
																	}
																}
																else {
																	for (Category cate3 : listcategory) {
																		if(cate2.getId().equals(cate3.getParentId())) {
																			if(cate3.getId().equals(ctId)) {
																				listcate.add(cate3);
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
											for (Category ct2 : listcate) {
												list.addAll(bookImp.getByCategoryId(ct2.getId()));
											}
											System.out.printf("id    ||\t%-25S ||\t%-10S ||\t%-10S ||\t%-20S ||\t%-20S ||\t" + bund.getString("Madanhmuc") + "\n",bund.getString("Tensach"),bund.getString("Trangthai"),bund.getString("Giatien"),bund.getString("Mota"),bund.getString("Tentacgia"));
											for (Book book1 : list) {
												book1.displayData();
											}
											break;
										case 2:
											System.out.println(bund.getString("Nhapvaotentacgia"));
											String tg = sc.nextLine();
											System.out.println(bund.getString("Nhapvaogianhonhat"));
											Float gm = Float.parseFloat(sc.nextLine());
											System.out.println(bund.getString("Hienthichitietsach"));
											List<Book> list1 = bookImp.getByAuthorAndPrice(tg,gm);
											if(list1!=null) {
												System.out.printf("id    ||\t%-25S ||\t%-10S ||\t%-10S ||\t%-20S ||\t%-20S ||\t" + bund.getString("Madanhmuc") + "\n",bund.getString("Tensach"),bund.getString("Trangthai"),bund.getString("Giatien"),bund.getString("Mota"),bund.getString("Tentacgia"));
												for (Book book1 : list1) {
													book1.displayData();
												}
												System.out.println(bund.getString("Bancomuonxuatfilekhong")+"(y/n)");
												String in = sc.nextLine();
												if(in.equalsIgnoreCase("y")) {
													writeObject("C:\\Users\\datpt\\eclipse-workspace\\Project\\book.txt", list1, locale);
													//										List<Book> dataFromFile = readObject("C:\\Users\\datpt\\eclipse-workspace\\Project\\book.txt");
													//										for (Book book1 : dataFromFile) {
													//											book1.displayData();
													//										}
												}
											}else {
												System.out.println(bund.getString("Khongtimthaysach"));
											}
											break;
										case 3:
											continue theLabel2;
										}
									}
									catch(Exception e) {  
									     System.out.println("Vui lòng nhập số");               
								    }
								}while(true);
							case 3:
								do {
									System.out.println("*****************" + bund.getString("Sapxep") + "******************");
									System.out.println("1. " + bund.getString("Sapxepsanphamtheokhoanggia"));
									System.out.println("2. " + bund.getString("Sapxepsanphamtheotentangdan"));
									System.out.println("3. " + bund.getString("Quaylai"));
									System.out.println(bund.getString("Suluachoncuaban"));
									try {   
									    str = sc.nextLine();  
									    lc=  Integer.parseInt(str);
										switch(lc) {
										case 1:
											Float priceMin, priceMax;
											System.out.println(bund.getString("Sapxepsanphamtheokhoanggia"));
											System.out.println(bund.getString("Nhapvaogianhonhat"));
											priceMin = Float.parseFloat(sc.nextLine());
											do {
												System.out.println(bund.getString("Nhapvaogialonnhat"));
												priceMax = Float.parseFloat(sc.nextLine());
												if(priceMax<priceMin) {
													System.out.println(bund.getString("Gianhapvaophailonhongianhonhat"));
												} else if(priceMax==null) {
													System.out.println(bund.getString("Khongduocdetrong"));
												}
												else {
													break;
												}
											}while(true);
											System.out.println(bund.getString("Sapxepsanphamtheokhoanggia"));
											List<Book> list = bookImp.orderByPrice(priceMin,priceMax);
											System.out.printf("id ||\t%-25S ||\t%-10S ||\t%-10S ||\t%-20S ||\t%-20S ||\t" + bund.getString("Madanhmuc") + "\n",bund.getString("Tensach"),bund.getString("Trangthai"),bund.getString("Giatien"),bund.getString("Mota"),bund.getString("Tentacgia"));
											for (Book book1 : list) {
												book1.displayData();
											}
											break;
										case 2:
											System.out.println(bund.getString("Sapxepsanphamtheotentangdan"));
											List<Book> list1 = bookImp.orderByTitle();
											System.out.printf("id ||\t%-25S ||\t%-10S ||\t%-10S ||\t%-20S ||\t%-20S ||\t" + bund.getString("Madanhmuc") + "\n",bund.getString("Tensach"),bund.getString("Trangthai"),bund.getString("Giatien"),bund.getString("Mota"),bund.getString("Tentacgia"));
											for (Book book1 : list1) {
												book1.displayData();
											}
											break;
										case 3:
											continue theLabel2;
										}
									}
									catch(Exception e) {  
									     System.out.println("Vui lòng nhập số");               
								    }
								}while(true);
							case 4:
								Book bk = new Book();
								System.out.println(bund.getString("Nhapidsachmuonsua"));
								String id = sc.nextLine();
								bk = bookImp.getById(id);
								if(bk != null) {
									bk.updateData(listbook,listcategory,bk,locale);
									bookImp.update(bk,id);
									listbook = bookImp.getAll();
								}
								else {
									System.out.println(bund.getString("Idkhongtontai"));
								}
								break;
							case 5:
								continue theLabel;
							}
						}
						catch(Exception e) {  
						     System.out.println("Vui lòng nhập số");               
					    }
					}while(true);
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println(bund.getString("Vuilongnhaplai"));
					break;
				}
			}
			catch(Exception e) {  
			     System.out.println("Vui lòng nhập số");               
		    }
		}while(true);
	}
}

