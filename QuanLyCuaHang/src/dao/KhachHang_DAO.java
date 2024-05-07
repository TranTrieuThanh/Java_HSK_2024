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

public class KhachHang_DAO {
private static KhachHang_DAO instance = new KhachHang_DAO();
	
	public static KhachHang_DAO getInstance() {
		if (instance == null)
			instance = new KhachHang_DAO();
		return instance;
	}
	public List<KhachHang> getAllKhachHang(){
		List<KhachHang> dsKH = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		try (Statement statement = con.createStatement()){
			String sql = "select * from KhachHang";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
			    String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                int tuoi = rs.getInt(3);
                boolean gioiTinh = rs.getBoolean(4);
                String sDT = rs.getString(5);
                KhachHang kh = new KhachHang(maKH, tenKH, tuoi, gioiTinh, sDT);
                dsKH.add(kh);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsKH;
	}
	public KhachHang getKHByID(String t)
	{
		 KhachHang acc = null;
	        try {
	            Connection con =  ConnectDB.getConnection();
	            String sql = "SELECT * FROM KhachHang WHERE IDKhachHang=?";
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, t);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	String maKH = rs.getString(1);
	                String tenKH = rs.getString(2);
	                int tuoi = rs.getInt(3);
	                boolean gioiTinh = rs.getBoolean(4);
	                String sDT = rs.getString(5);
	                 acc = new KhachHang(maKH, tenKH, tuoi, gioiTinh, sDT);
	                
	            }
	        } catch (Exception e) {
	            // TODO: handle exception           
	        }
	        return acc;
	}
	
	public void addKhachHang(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into KhachHang values(?,?,?,?,?)");
			  stmt.setString(1, kh.getMaKH());
	            stmt.setString(2, kh.getTenKH());
	            stmt.setInt(3, kh.getTuoi());
	            stmt.setBoolean(4, kh.isGioiTinh());
	            stmt.setString(5, kh.getSDT());
	            stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	
	public void updateKhachHang(KhachHang kh) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					 "update KhachHang "
				                + "set TenKH = ?, " 
				                + "Tuoi = ?, " 
				                + "GioiTinh = ?, " 
				                + "SDT = ? "
				                + "where IDKhachHang = ?");
			stmt.setString(1, kh.getMaKH());
			  stmt.setString(2, kh.getTenKH());
	            stmt.setInt(3, kh.getTuoi());
	            stmt.setBoolean(4, kh.isGioiTinh());
	            stmt.setString(5, kh.getSDT());
	            
	            stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}

	
	
	public void deleteKhachHang(String maKH) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from KhachHang where IDKhachHang = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maKH);
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
