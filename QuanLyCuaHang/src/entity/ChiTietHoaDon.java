package entity;

import java.util.Objects;

public class ChiTietHoaDon {
private String MaHD;
private Hang hg;
private int Soluong;
private double DonGia;
public String getMaHD() {
	return MaHD;
}
public void setMaHD(String maHD) {
	MaHD = maHD;
}
public Hang getHg() {
	return hg;
}
public void setHg(Hang hg) {
	this.hg = hg;
}
public int getSoluong() {
	return Soluong;
}
public void setSoluong(int soluong) {
	Soluong = soluong;
}
public double getDonGia() {
	return DonGia;
}
public void setDonGia(double donGia) {
	DonGia = donGia;
}
public ChiTietHoaDon() {
	super();
	// TODO Auto-generated constructor stub
}
public ChiTietHoaDon(String MaHD) {
	super();
	this.MaHD=MaHD;
	// TODO Auto-generated constructor stub
}
public ChiTietHoaDon(String maHD, Hang hg, int soluong, double donGia) {
	super();
	MaHD = maHD;
	this.hg = hg;
	Soluong = soluong;
	DonGia = donGia;
}


}
