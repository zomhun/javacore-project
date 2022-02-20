package project.DAO;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import project.connection.ConnectionDB;
import project.entity.Category;

public class CategoryDAOimp  implements CategoryDAO{
	
	@Override
	public List<Category> getAll() {
		Connection conn = null;
		CallableStatement callSt = null;
		List<Category> list = new ArrayList<Category>();
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call showAllCate()}");
			ResultSet res = callSt.executeQuery();
			while(res.next()) {
				Category cate = new Category();
				cate.setId(res.getString("id"));
				cate.setName(res.getString("name"));
				cate.setStatus(res.getBoolean("status"));
				cate.setParentId(res.getString("parentId"));
				list.add(cate);
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
	public boolean add(Category category) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement callSt = null;
		boolean check = false;
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call insertCate(?,?,?,?,?)}");
			// set gia tri cho tham so dau vao
			callSt.setString(1, category.getId());
			callSt.setString(2, category.getName());
			callSt.setBoolean(3, category.isStatus());
			callSt.setString(4, category.getParentId());
			// Ðang ky du lieu cho tham so dau ra 
			callSt.registerOutParameter(5, Types.BIT);
			// thuc thi proc
			callSt.execute();
			// lay gia tri tham so dau ra gian cho bien check
			check = callSt.getBoolean(5);
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
		Connection conn = null;
		CallableStatement callSt = null;
		boolean check = false;
		
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call deleteCate(?,?)}");
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
	public Category getById(String id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement callSt = null;
		Category cate = new Category();
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call searchCateById(?)}");
			callSt.setString(1, id);
			ResultSet res = callSt.executeQuery();
			while(res.next()) {
				cate.setId(res.getString("id"));
				cate.setName(res.getString("name"));
				cate.setStatus(res.getBoolean("status"));
				cate.setParentId(res.getString("parentId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		return cate;
	}
	@Override
	public Category getByName(String name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		CallableStatement callSt = null;
		Category cate = new Category();
		try {
			conn = ConnectionDB.connectionsql();
			callSt = conn.prepareCall("{call searchCateByName(?)}");
			callSt.setString(1, "%"+name+"%");
			ResultSet res = callSt.executeQuery();
			while(res.next()) {
				cate.setId(res.getString("id"));
				cate.setName(res.getString("name"));
				cate.setStatus(res.getBoolean("status"));
				cate.setParentId(res.getString("parentId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionDB.closeConnection(conn);
		}
		return cate;
	}
}
