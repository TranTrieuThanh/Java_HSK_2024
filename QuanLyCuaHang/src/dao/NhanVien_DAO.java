package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

public class NhanVien_DAO {
private static NhanVien_DAO instance = new NhanVien_DAO();
	
	public static NhanVien_DAO getInstance() {
		if (instance == null)
			instance = new NhanVien_DAO();
		return instance;
	}
	
	public List<NhanVien> getAllNhanVien(){
		List<NhanVien> dsNV = new ArrayList<NhanVien>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		try (Statement statement = con.createStatement()){
			String sql = "select * from NhanVien";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                int tuoi = rs.getInt(3);
                String diaChi = rs.getString(4);
                boolean gioiTinh = rs.getBoolean(5);
                String chucVu = rs.getString(6);
                float luong = rs.getFloat(7);
                NhanVien nv = new NhanVien(maNV, tenNV, tuoi, diaChi , gioiTinh,chucVu, luong);
                dsNV.add(nv);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsNV;
	}
	public NhanVien getNVByID(String t)
	{
		 NhanVien acc = null;
	        try {
	            Connection con =  ConnectDB.getConnection();
	            String sql = "SELECT * FROM NhanVien WHERE IDNhanVien=?";
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, t);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	String maNV = rs.getString(1);
	                String tenNV = rs.getString(2);
	                int tuoi = rs.getInt(3);
	                String diaChi = rs.getString(4);
	                boolean gioiTinh = rs.getBoolean(5);
	                String chucVu = rs.getString(6);
	                float luong = rs.getFloat(7);
	                 acc = new NhanVien(maNV, tenNV, tuoi, diaChi , gioiTinh,chucVu, luong);
	                
	            }
	        } catch (Exception e) {
	            // TODO: handle exception           
	        }
	        return acc;
	}
	public void addNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into NhanVien values(?,?,?,?,?,?,?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setInt(3, nv.getTuoi());
			stmt.setString(4, nv.getDiaChi());
			stmt.setBoolean(5, nv.isGioiTinh());
			stmt.setString(6, nv.getChucVu());
			stmt.setFloat(7, (float)nv.getLuong());
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	
	public void updateNhanVien(NhanVien nv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"update NhanVien "
				+ "set [TenNV] = ?, " 
				+ "[Tuoi] = ?, " 
				+ "[DiaChi] = ?, " 
				+ "[GioiTinh] = ? "
				+ "[ChucVu] = ?, " 
				+ "[Luong] = ?, "
				+ "where IDNhanVien = ?");
			
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setInt(3, nv.getTuoi());
			stmt.setString(4, nv.getDiaChi());	
			stmt.setBoolean(5, nv.isGioiTinh());
			stmt.setString(6, nv.getChucVu());
			stmt.setFloat(7, (float)nv.getLuong());
			stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}

	public void deleteNhanVien(String maNV) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from NhanVien where IDNhanVien = ?";
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
