package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.ChiTietHoaDon;
import entity.Hang;


public class ChiTietHoaDon_DAO {
private static ChiTietHoaDon_DAO instance = new ChiTietHoaDon_DAO();
	
	public static ChiTietHoaDon_DAO getInstance() {
		if(instance == null)
			instance = new ChiTietHoaDon_DAO();
		return instance;
	}
	public ArrayList<ChiTietHoaDon> getAllCTHoaDon()
	{
			ArrayList<ChiTietHoaDon> dsCTHoaDon = new ArrayList<ChiTietHoaDon>();
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			try(Statement statement = con.createStatement()){
				String sql = "select * from ChiTietHoaDon1";
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					String id = rs.getString(1);
					Hang hg= new Hang(rs.getString(2));
	                int soluong= rs.getInt(3);
	                double dongia= rs.getFloat(4);
	              
	              ChiTietHoaDon ctHD= new ChiTietHoaDon(id, hg, soluong, dongia);
	             
					dsCTHoaDon.add(ctHD);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return dsCTHoaDon;
	}
	public ArrayList<ChiTietHoaDon> selectAll(String t) {
        ArrayList<ChiTietHoaDon> ketQua = new ArrayList<ChiTietHoaDon>();
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM ChiTietHoaDon1 WHERE IDHoaDon=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieu = rs.getString("IDHoaDon");
                Hang hg= new Hang(rs.getString("IDHang"));
                int soLuong = rs.getInt("Soluong");
                double donGia = rs.getDouble("DonGia");
                
                ChiTietHoaDon ctHD= new ChiTietHoaDon(maPhieu, hg, soLuong, donGia);
                ketQua.add(ctHD);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
    }
	public void addCTHoaDon(ChiTietHoaDon ctHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			 stmt = con.prepareStatement("insert into ChiTietHoaDon1 values(?,?,?,?)");
	            stmt.setString(1, ctHD.getMaHD());
	            stmt.setString(2, ctHD.getHg().getMaHang());
	            stmt.setInt(3, ctHD.getSoluong());
	            stmt.setDouble(4, ctHD.getDonGia());
	            
	            stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	public void updateCTHoaDon(ChiTietHoaDon ctHD) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update ChiTietHoaDon1 set IDHang = ?, Soluong = ?, DonGia = ?, where IDHoaDon = ?");
			stmt.setString(1, ctHD.getMaHD());
            stmt.setString(2, ctHD.getHg().getMaHang());
            stmt.setInt(3, ctHD.getSoluong());
            stmt.setDouble(4, ctHD.getDonGia());
        
	            stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	
	
	
	
	public void deleteChiTietHoaDon(String maHD, String maHang) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from ChiTietHoaDon1 where IDHoaDon =? and IDHang= ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			stmt.setString(2, maHang);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	private void close(PreparedStatement stmt) {
		if(stmt != null)
			try {
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
}
