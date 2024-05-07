package entity;


import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDon {
private String maHD;
private KhachHang kh;
private NhanVien nv;
private ArrayList<ChiTietHoaDon> cthd;
private Date  ngaylapHD;
private double TongTien;
public String getMaHD() {
	return maHD;
}
public void setMaHD(String maHD) {
	this.maHD = maHD;
}
public KhachHang getKh() {
	return kh;
}
public void setKh(KhachHang kh) {
	this.kh = kh;
}
public NhanVien getNv() {
	return nv;
}
public void setNv(NhanVien nv) {
	this.nv = nv;
}
public ArrayList<ChiTietHoaDon> getCthd() {
	return cthd;
}
public void setCthd(ArrayList<ChiTietHoaDon> cthd) {
	this.cthd = cthd;
}
public Date getNgaylapHD() {
	return ngaylapHD;
}
public void setNgaylapHD(Date ngaylapHD) {
	this.ngaylapHD = ngaylapHD;
}
public double getTongTien() {
	return TongTien;
}
public void setTongTien(double tongTien) {
	TongTien = tongTien;
}
public HoaDon() {
	super();
	// TODO Auto-generated constructor stub
}
public HoaDon(String maHD, KhachHang kh, NhanVien nv, ArrayList<ChiTietHoaDon> cthd, Date ngaylapHD, double tongTien) {
	super();
	this.maHD = maHD;
	this.kh = kh;
	this.nv = nv;
	this.cthd = cthd;
	this.ngaylapHD = ngaylapHD;
	TongTien = tongTien;
}



}
