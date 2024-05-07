package entity;

import java.util.Objects;

public class KhachHang {
private String MaKH;
private String TenKH;
private int Tuoi;
private boolean GioiTinh;
private String SDT;
public String getMaKH() {
	return MaKH;
}
public void setMaKH(String maKH) {
	MaKH = maKH;
}
public String getTenKH() {
	return TenKH;
}
public void setTenKH(String tenKH) {
	TenKH = tenKH;
}
public int getTuoi() {
	return Tuoi;
}
public void setTuoi(int tuoi) {
	Tuoi = tuoi;
}
public boolean isGioiTinh() {
	return GioiTinh;
}
public void setGioiTinh(boolean gioiTinh) {
	GioiTinh = gioiTinh;
}
public String getSDT() {
	return SDT;
}
public void setSDT(String sDT) {
	SDT = sDT;
}
@Override
public int hashCode() {
	return Objects.hash(MaKH);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	KhachHang other = (KhachHang) obj;
	return Objects.equals(MaKH, other.MaKH);
}
public KhachHang() {
	super();
	// TODO Auto-generated constructor stub
}
public KhachHang(String MaKH) {
	super();
	this.MaKH=MaKH;
	// TODO Auto-generated constructor stub
}
public KhachHang(String maKH, String tenKH, int tuoi, boolean gioiTinh, String sDT) {
	super();
	MaKH = maKH;
	TenKH = tenKH;
	Tuoi = tuoi;
	GioiTinh = gioiTinh;
	SDT = sDT;
}


}
