package project.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import project.connection.ConnectionDB;
import project.entity.Book;
import project.entity.Category;

public class BookDAOimp implements BookDAO{
	@Override
	public List<Book> getAll() {
		Connection conn = null;
		CallableStatement callSt = null;
		List<Book> list = new ArrayList<Book>();
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call showAllBk()}");
			ResultSet res = callSt.executeQuery();
			while(res.next()) {
				Book bk = new Book();
				bk.setId(res.getString("id"));
				bk.setTitle(res.getString("title"));
				bk.setStatus(res.getBoolean("status"));
				bk.setPrice(res.getFloat("price"));
				bk.setDescription(res.getString("description"));
				bk.setAuthor(res.getString("author"));
				bk.setCategoryId(res.getString("categoryId"));
				list.add(bk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		return list;
	}

	@Override
	public boolean add(Book book) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement callSt = null;
		boolean check = false;
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call insertBk(?,?,?,?,?,?,?,?)}");
			// set gia tri cho tham so dau vao
			callSt.setString(1, book.getId());
			callSt.setString(2, book.getTitle());
			callSt.setBoolean(3, book.isStatus());
			callSt.setFloat(4, book.getPrice());
			callSt.setString(5, book.getDescription());
			callSt.setString(6, book.getAuthor());
			callSt.setString(7, book.getCategoryId());
			// Ðang ky du lieu cho tham so dau ra 
			callSt.registerOutParameter(8, Types.BIT);
			// thuc thi proc
			callSt.execute();
			// lay gia tri tham so dau ra gian cho bien check
			check = callSt.getBoolean(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		return check;
	}

	@Override
	public boolean update(Book book,String id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement callSt = null;
		boolean check = false;
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call updateBk(?,?,?,?,?,?,?,?)}");
			// set gia tri cho tham so dau vao
			callSt.setString(1, book.getTitle());
			callSt.setBoolean(2, book.isStatus());
			callSt.setFloat(3, book.getPrice());
			callSt.setString(4, book.getDescription());
			callSt.setString(5, book.getAuthor());
			callSt.setString(6, book.getCategoryId());
			callSt.setString(7, id);
			// Ðang ky du lieu cho tham so dau ra 
			callSt.registerOutParameter(8, Types.BIT);
			// thuc thi proc
			callSt.execute();
			// lay gia tri tham so dau ra gian cho bien check
			check = callSt.getBoolean(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		return check;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement callSt = null;
		boolean check = false;
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call deleteBk(?,?)}");
			// set gia tri cho tham so dau vao
			callSt.setString(1, id);
			// Ðang ky du lieu cho tham so dau ra 
			callSt.registerOutParameter(2, Types.BIT);
			// thuc thi proc
			callSt.execute();
			// lay gia tri tham so dau ra gian cho bien check
			check = callSt.getBoolean(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		return check;
	}

	@Override
	public Book getById(String id) {
		// TODO Auto-generated method stub
		Book book = null;
		Connection conn;
		CallableStatement callSt = null;
		ResultSet res = null;
		
		conn = ConnectionDB.connectionsql();
		try {
			callSt = conn.prepareCall("{call getBkById(?)}");
			callSt.setString(1,id);
			
			res = callSt.executeQuery();
			if(res.next()) {
				book = new Book();
				book.setId(res.getString("id"));
				book.setTitle(res.getString("title"));
				book.setStatus(res.getBoolean("status"));
				book.setPrice(res.getFloat("price"));
				book.setDescription(res.getString("description"));
				book.setAuthor(res.getString("author"));
				book.setCategoryId(res.getString("categoryId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		
		return book;
	}

	@Override
	public List<Book> getByCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		Book bk = null;
		Connection conn;
		CallableStatement callSt = null;
		ResultSet res = null;
		conn = ConnectionDB.connectionsql();
		List<Book> list = new ArrayList<Book>();
		try {
			callSt = conn.prepareCall("{call getBkByCatId(?)}");
			callSt.setString(1,categoryId);
			res = callSt.executeQuery();
			while(res.next()) {
				bk = new Book();
				bk.setId(res.getString("id"));
				bk.setTitle(res.getString("title"));
				bk.setStatus(res.getBoolean("status"));
				bk.setPrice(res.getFloat("price"));
				bk.setDescription(res.getString("description"));
				bk.setAuthor(res.getString("author"));
				bk.setCategoryId(res.getString("categoryId"));
				list.add(bk);			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<Book> orderByPrice(float min, float max) {
		// TODO Auto-generated method stub
		List<Book> list = new ArrayList<Book>();
		Connection conn;
		CallableStatement callSt = null;
		ResultSet res = null;
		
		conn = ConnectionDB.connectionsql();
		try {
			callSt = conn.prepareCall("{call getBkBetweenPrice(?,?)}");
			callSt.setFloat(1,min);
			callSt.setFloat(2,max);
			res = callSt.executeQuery();
			while(res.next()) {
				Book bk = new Book();
				bk.setId(res.getString("id"));
				bk.setTitle(res.getString("title"));
				bk.setStatus(res.getBoolean("status"));
				bk.setPrice(res.getFloat("price"));
				bk.setDescription(res.getString("description"));
				bk.setAuthor(res.getString("author"));
				bk.setCategoryId(res.getString("categoryId"));
				list.add(bk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		
		return list;
		
	}

	@Override
	public List<Book> orderByTitle() {
		// TODO Auto-generated method stub
		List<Book> list = new ArrayList<Book>();
		Connection conn;
		CallableStatement callSt = null;
		ResultSet res = null;
		
		conn = ConnectionDB.connectionsql();
		try {
			callSt = conn.prepareCall("{call orderBkByTitle}");
			
			res = callSt.executeQuery();
			while(res.next()) {
				Book bk = new Book();
				bk.setId(res.getString("id"));
				bk.setTitle(res.getString("title"));
				bk.setStatus(res.getBoolean("status"));
				bk.setPrice(res.getFloat("price"));
				bk.setDescription(res.getString("description"));
				bk.setAuthor(res.getString("author"));
				bk.setCategoryId(res.getString("categoryId"));
				list.add(bk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		
		return list;
	}
	
	@Override
	public List<Book> getByAuthorAndPrice(String author, float price) {
		// TODO Auto-generated method stub
		List<Book> list = new ArrayList<Book>();
		Connection conn;
		CallableStatement callSt = null;
		ResultSet res = null;
		
		conn = ConnectionDB.connectionsql();
		try {
			callSt = conn.prepareCall("{call getBkByAuthorAndPrice(?,?)}");
			callSt.setString(1,author);
			callSt.setFloat(2,price);
			res = callSt.executeQuery();
			while(res.next()) {
				Book bk = new Book();
				bk.setId(res.getString("id"));
				bk.setTitle(res.getString("title"));
				bk.setStatus(res.getBoolean("status"));
				bk.setPrice(res.getFloat("price"));
				bk.setDescription(res.getString("description"));
				bk.setAuthor(res.getString("author"));
				bk.setCategoryId(res.getString("categoryId"));
				list.add(bk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		
		return list;
	}

	@Override
	public boolean deletebyCategoryId(String categoryId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement callSt = null;
		boolean check = false;
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call deleteBkByCateId(?,?)}");
			// set gia tri cho tham so dau vao
			callSt.setString(1, categoryId);
			// Ðang ky du lieu cho tham so dau ra 
			callSt.registerOutParameter(2, Types.BIT);
			// thuc thi proc
			callSt.execute();
			// lay gia tri tham so dau ra gian cho bien check
			check = callSt.getBoolean(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		
		return check;
	}
}
