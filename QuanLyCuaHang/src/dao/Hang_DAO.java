package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.Hang;
import entity.KhachHang;
import entity.NhaCungCap;



public class Hang_DAO {
private static Hang_DAO instance = new Hang_DAO();
	
	public static Hang_DAO getInstance() {
		if(instance == null)
			instance = new Hang_DAO();
		return instance;
	}
	public List<Hang> getAllHang(){
		List<Hang> dsHang = new ArrayList<Hang>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		try(Statement statement = con.createStatement()){
			String sql = "select * from Hang2";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString(1);
				String tenHang = rs.getString(2);
                String loaiHang = rs.getString(3);
                NhaCungCap ncc= new NhaCungCap(rs.getString(4));
               Date ngaySX= rs.getDate(5);
               Date ngayHH= rs.getDate(6);
               int soluong= rs.getInt(7);
                double giaHang = rs.getFloat(8);
                Hang p = new Hang(id , tenHang,loaiHang, ncc,ngaySX,ngayHH,soluong, giaHang);
				dsHang.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsHang;
	}
	public Hang getHangByID(String t)
	{
		 Hang acc = null;
	        try {
	            Connection con =  ConnectDB.getConnection();
	            String sql = "SELECT * FROM Hang2 WHERE IDHang=?";
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, t);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	            	String id = rs.getString(1);
					String tenHang = rs.getString(2);
	                String loaiHang = rs.getString(3);
	                NhaCungCap ncc= new NhaCungCap(rs.getString(4));
	               Date ngaySX= rs.getDate(5);
	               Date ngayHH= rs.getDate(6);
	               int soluong= rs.getInt(7);
	                double giaHang = rs.getFloat(8);
	                acc = new Hang(id , tenHang,loaiHang, ncc,ngaySX,ngayHH,soluong, giaHang);
	            }
	        } catch (Exception e) {
	            // TODO: handle exception           
	        }
	        return acc;
	}
	public void addHang(Hang p) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			 stmt = con.prepareStatement("insert into Hang2 values(?,?,?,?,?,?,?,?)");
	            stmt.setString(1, p.getMaHang());
	            stmt.setString(2, p.getTenHang());
	            stmt.setString(3, p.getLoaiHang());
	            stmt.setString(4, p.getNcc().getMaNCC());
	            stmt.setDate(5,  p.getNgaySX());
	            stmt.setDate(6,  p.getNgayHetHan());
	            stmt.setInt(7, p.getSoLuong());
	            stmt.setDouble(8, p.getDonGia());
	            stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	public void updateHang(Hang p) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update Hang2 set TenHang = ?, LoaiHang = ?, MaNCC = ?, NgaySX = ?, NgayHetHan = ?, SoLuong = ?, DonGia = ?, where IDHang = ?");
			stmt.setString(1, p.getMaHang());
            stmt.setString(2, p.getTenHang());
            stmt.setString(3, p.getLoaiHang());
            stmt.setString(4, p.getNcc().getMaNCC());
            stmt.setDate(5, p.getNgaySX());
            stmt.setDate(6,  p.getNgayHetHan());
            stmt.setInt(7, p.getSoLuong());
            stmt.setDouble(8, p.getDonGia());
	            stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
		public void updatesoluongHang(String maHang, int soluong) {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = null;
			try {
				stmt = con.prepareStatement("update Hang2 set TenHang = ?, LoaiHang = ?, MaNCC = ?, NgaySX = ?, NgayHetHan = ?, SoLuong = ?, DonGia = ?, where IDHang = ?");
				stmt.setString(1, maHang);
	            stmt.setInt(7, soluong);
	            
	            
		            stmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(stmt);
			}
	}
	
	
	
	
	public void deleteHang(String maHang) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		String sql = "delete from Hang2 where IDHang =?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHang);
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
