package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectDB;
import entity.ChiTietHoaDon;
import entity.Hang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;

public class HoaDon_DAO {
private static HoaDon_DAO instance = new HoaDon_DAO();
	
	public static HoaDon_DAO getInstance() {
		if(instance == null)
			instance = new HoaDon_DAO();
		return instance;
	}
	public List<HoaDon> getAllHoaDon(){
		List<HoaDon> dsHang = new ArrayList<HoaDon>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		try(Statement statement = con.createStatement()){
			String sql = "select * from HoaDon3";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
//				ChiTietHoaDon ctHD= new ChiTietHoaDon(rs.getString(1));
				String maHD= rs.getString(1);
				KhachHang kh= new KhachHang(rs.getString(2));
				NhanVien nv= new NhanVien(rs.getString(3));
               Date ngaylapHD= rs.getDate(4);
                double TongTien = rs.getFloat(5);
                HoaDon hd= new HoaDon(maHD, kh, nv, ChiTietHoaDon_DAO.getInstance().selectAll(maHD), ngaylapHD, TongTien);
				dsHang.add(hd);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dsHang;
	}
	public HoaDon selectHDByID(String t)
	{
		HoaDon ketQua = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon3 WHERE IDHoaDon=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ctHD = new ChiTietHoaDon(rs.getString("IDHoaDon"));
                String maHD= rs.getString("IDHoaDon");
               KhachHang kh= new KhachHang(rs.getString("IDKhachHang"));
               NhanVien nv= new NhanVien(rs.getString("IDNhanVien"));

                Date thoiGianTao = rs.getDate("ngaylapHD");
                double tongTien = rs.getDouble("TongTien");
                ketQua=new HoaDon(maHD, kh, nv, ChiTietHoaDon_DAO.getInstance().selectAll(maHD), thoiGianTao, tongTien);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ketQua;
	}
	public void addHoaDon(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			 stmt = con.prepareStatement("insert into HoaDon3 (IDHoaDon, IDKhachHang, IDNhanVien, ngaylapHD, TongTien) values(?,?,?,?,?)");
//	            stmt.setString(1, hd.getCTHD().getMaHD());
			 stmt.setString(1, hd.getMaHD());
	            stmt.setString(2, hd.getKh().getMaKH());
	            stmt.setString(3, hd.getNv().getMaNV());
	            stmt.setDate(4,  hd.getNgaylapHD());
	            stmt.setDouble(5, hd.getTongTien());
	            stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
	}
	public void updateHang(HoaDon hd) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update HoaDon3 set IDKhachHang = ?, IDNhanVien = ?, ngaylapHD = ?, TongTien = ?, where IDHoaDon = ?");
			stmt.setString(1, hd.getMaHD());
            stmt.setString(2, hd.getKh().getMaKH());
            stmt.setString(3, hd.getNv().getMaNV());
            stmt.setDate(4,  hd.getNgaylapHD());
            stmt.setDouble(5, hd.getTongTien());
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
		String sql = "delete from HoaDon3 where IDHoaDon =?";
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
