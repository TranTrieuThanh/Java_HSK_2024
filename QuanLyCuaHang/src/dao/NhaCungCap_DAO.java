package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.NhaCungCap;

public class NhaCungCap_DAO {
private static NhaCungCap_DAO instance = new NhaCungCap_DAO();
	
	public static NhaCungCap_DAO getInstance() {
		if (instance == null)
			instance = new NhaCungCap_DAO();
		return instance;
	}
	
	public List<NhaCungCap> getAllNhaCungCap(){
		List<NhaCungCap> dsNV = new ArrayList<NhaCungCap>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		try (Statement statement = con.createStatement()){
			String sql = "select * from NhaCungCap";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maNCC = rs.getString(1);
                String tenNCC = rs.getString(2);
                String diaChi = rs.getString(3);
                String sdt= rs.getString(4);
             
                	NhaCungCap nv = new NhaCungCap(maNCC, tenNCC, diaChi ,sdt);
                dsNV.add(nv);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	
	public void addNhaCungCap(NhaCungCap nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into NhaCungCap values(?,?,?,?)");
			stmt.setString(1, nv.getMaNCC());
			stmt.setString(2, nv.getTenNCC());
			stmt.setString(3, nv.getDiaChi());
			stmt.setString(4, nv.getSDT());
			
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	
	public void updateNhaCungCap(NhaCungCap nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"update NhaCungCap "
				+ "set [tenNCC] = ?, " 
				+ "[DiaChi] = ?, " 
				+ "[SDT] = ?, " 
				+ "where IDNCC = ?");
			stmt.setString(1, nv.getMaNCC());
			stmt.setString(2, nv.getTenNCC());
			stmt.setString(3, nv.getDiaChi());
			stmt.setString(4, nv.getSDT());
			
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}

	public void deleteNhaCungCap(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from NhaCungCap where IDNCC = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maNV);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	private void close(PreparedStatement stmt) {
		
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
