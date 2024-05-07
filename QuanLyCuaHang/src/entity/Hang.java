package entity;


import java.sql.Date;
import java.time.LocalDate;

import java.util.Objects;

public class Hang {
private String MaHang;
private String TenHang;
private String LoaiHang;
private NhaCungCap ncc;
private Date NgaySX;
private Date NgayHetHan;
private int soLuong;
private double DonGia;
public String getMaHang() {
	return MaHang;
}
public void setMaHang(String maHang) {
	MaHang = maHang;
}
public String getTenHang() {
	return TenHang;
}
public void setTenHang(String tenHang) {
	TenHang = tenHang;
}
public String getLoaiHang() {
	return LoaiHang;
}
public void setLoaiHang(String loaiHang) {
	LoaiHang = loaiHang;
}
public NhaCungCap getNcc() {
	return ncc;
}
public void setNcc(NhaCungCap ncc) {
	this.ncc = ncc;
}
public Date getNgaySX() {
	return NgaySX;
}
public void setNgaySX(Date ngaySX) {
	NgaySX = ngaySX;
}
public Date getNgayHetHan() {
	return NgayHetHan;
}
public void setNgayHetHan(Date ngayHetHan) {
	NgayHetHan = ngayHetHan;
}
public int getSoLuong() {
	return soLuong;
}
public void setSoLuong(int soLuong) {
	this.soLuong = soLuong;
}
public double getDonGia() {
	return DonGia;
}
public void setDonGia(double donGia) {
	DonGia = donGia;
}

@Override
public int hashCode() {
	return Objects.hash(MaHang);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Hang other = (Hang) obj;
	return Objects.equals(MaHang, other.MaHang);
}
public Hang() {
	super();
	// TODO Auto-generated constructor stub
}
public Hang(String maHang) {
	super();
	this.MaHang=maHang;
	// TODO Auto-generated constructor stub
}
public Hang(String maHang, String tenHang, String loaiHang, NhaCungCap ncc, Date ngaySX, Date ngayHetHan, int soLuong,
		double donGia) {
	super();
	MaHang = maHang;
	TenHang = tenHang;
	LoaiHang = loaiHang;
	this.ncc = ncc;
	NgaySX = ngaySX;
	NgayHetHan = ngayHetHan;
	this.soLuong = soLuong;
	DonGia = donGia;
}




}
