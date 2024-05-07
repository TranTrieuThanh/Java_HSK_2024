package entity;

import java.util.Objects;

public class NhanVien {
private String MaNV;
private String TenNV;
private int Tuoi;
private String DiaChi;
private boolean GioiTinh;
private String ChucVu;
private double Luong;

public String getMaNV() {
	return MaNV;
}
public void setMaNV(String maNV) {
	MaNV = maNV;
}
public String getTenNV() {
	return TenNV;
}
public void setTenNV(String tenNV) {
	TenNV = tenNV;
}
public int getTuoi() {
	return Tuoi;
}
public void setTuoi(int tuoi) {
	Tuoi = tuoi;
}
public String getDiaChi() {
	return DiaChi;
}
public void setDiaChi(String diaChi) {
	DiaChi = diaChi;
}
public boolean isGioiTinh() {
	return GioiTinh;
}
public void setGioiTinh(boolean gioiTinh) {
	GioiTinh = gioiTinh;
}
public String getChucVu() {
	return ChucVu;
}
public void setChucVu(String chucVu) {
	ChucVu = chucVu;
}
public double getLuong() {
	return Luong;
}
public void setLuong(double luong) {
	Luong = luong;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	NhanVien other = (NhanVien) obj;
	return Objects.equals(MaNV, other.MaNV);
}
public NhanVien() {
	super();
	// TODO Auto-generated constructor stub
}
public NhanVien(String MaNV) {
	super();
	this.MaNV=MaNV;
	// TODO Auto-generated constructor stub
}
public NhanVien(String maNV, String tenNV, int tuoi, String diaChi, boolean gioiTinh, String chucVu, double luong) {
	super();
	MaNV = maNV;
	TenNV = tenNV;
	Tuoi = tuoi;
	DiaChi = diaChi;
	GioiTinh = gioiTinh;
	ChucVu = chucVu;
	Luong = luong;
}



}
