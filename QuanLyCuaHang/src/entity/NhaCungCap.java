package entity;

import java.util.Objects;

public class NhaCungCap {
private String maNCC;
private String tenNCC;
private String DiaChi;
private String SDT;
public String getMaNCC() {
	return maNCC;
}
public void setMaNCC(String maNCC) {
	this.maNCC = maNCC;
}
public String getTenNCC() {
	return tenNCC;
}
public void setTenNCC(String tenNCC) {
	this.tenNCC = tenNCC;
}
public String getDiaChi() {
	return DiaChi;
}
public void setDiaChi(String diaChi) {
	DiaChi = diaChi;
}
public String getSDT() {
	return SDT;
}
public void setSDT(String sDT) {
	SDT = sDT;
}
@Override
public int hashCode() {
	return Objects.hash(maNCC);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	NhaCungCap other = (NhaCungCap) obj;
	return Objects.equals(maNCC, other.maNCC);
}

public NhaCungCap() {
	super();
	// TODO Auto-generated constructor stub
}
public NhaCungCap(String maNCC) {
	super();
	this.maNCC=maNCC;
	// TODO Auto-generated constructor stub
}
public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sDT) {
	super();
	this.maNCC = maNCC;
	this.tenNCC = tenNCC;
	DiaChi = diaChi;
	SDT = sDT;
}

}
