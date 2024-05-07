package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import connection.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.Hang_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhaCungCap_DAO;
import dao.NhanVien_DAO;
import entity.ChiTietHoaDon;
import entity.Hang;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhaCungCap;
import entity.NhanVien;
import function.WritePDF;

public class HoaDon_GUI extends JFrame implements ActionListener {
private static final int QUESTION_MESSAGE = 0;
private JPanel jpWest;
private JPanel jpEast;
private JPanel jpNorthW;
private JPanel jpCenterW;
private JPanel jpSouthW;
private JTextField txtTimKiem;
private JButton btn_timkiem;
private DefaultTableModel model;
private JTable table;
private JButton btn_them;
private JTextField txt_Soluong;
private JPanel jpNorthE;
private JPanel row1E;
private JTextField txtMaHD;
private JPanel row2E;
private JTextField txtTenNV;
private JComboBox cbTenNV;
private DefaultTableModel modelHD;
private JTable tableHD;
private JPanel jpCenterE;
private JPanel jpSouthE;
private JPanel row1SE;
private JPanel row2SE;
private JScrollPane JScroPane;
private JScrollPane JScroPane1;
private JButton btn_XuatPDF;
private JButton btn_Suasoluong;
private JButton btn_XoaSanPham;
private JButton btn_XuatExcel;
private JLabel lbl_TongTien;
private JButton btn_XuatHD;
private JPanel row3E;
private JComboBox cbTenKH;
Locale localeVN = new Locale("vi", "VN");
Color mauBang = new Color(99, 184, 255);
NumberFormat tienTeVN = NumberFormat.getCurrencyInstance(localeVN);
private Hang_DAO dsHang;
private NhaCungCap_DAO dsNCC;
private NhanVien_DAO dsNV;
private KhachHang_DAO dsKH;
private HoaDon_DAO dsHD;
private ArrayList<ChiTietHoaDon> CTHoaDon;
private ChiTietHoaDon_DAO dsCTHD;
private int i;
private String randomKQ;
private String maHD;
private ChiTietHoaDon entityCTHD;
private KhachHang khMH;
private NhanVien nvMH;
private ArrayList<ChiTietHoaDon> dsCTHDnew;
private java.sql.Date ngaylapHD;
private HoaDon_DAO dsHoaDon;




public HoaDon_GUI() throws Exception
{
	
	//West
	jpWest= new JPanel();
	jpWest.setLayout(new BorderLayout());
	//North In West
	jpNorthW= new JPanel();
	jpNorthW.setLayout( new BoxLayout(jpNorthW,BoxLayout.X_AXIS));
	jpNorthW.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(99, 184, 255)),"Tìm kiếm theo tên"));
	jpNorthW.add(txtTimKiem= new JTextField(15));
	jpNorthW.add(Box.createHorizontalStrut(10));
	jpNorthW.add(btn_timkiem= new JButton("Tìm"));
	btn_timkiem.addActionListener(this);
	jpWest.add(jpNorthW,BorderLayout.NORTH);
	//Center in west
	jpCenterW= new JPanel();
	model = new DefaultTableModel();
//	Giờ khai báo table này để add model vào rỗng nè
	table = new JTable(model);
//	addColumn là thêm tiêu đè cột thôi
	model.addColumn("Mã hàng");
	model.addColumn("Tên hàng");
	model.addColumn("Loại hàng");
	model.addColumn("Tên NCC");
	model.addColumn("Ngày sản xuất");
	model.addColumn("Ngày hết hạn");
	model.addColumn("Số lượng");
	model.addColumn("Giá");
	//
	//Đổi màu xanh của thanh tiêu đề
	DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
	headerRenderer.setBackground(mauBang);
	table.getTableHeader().setDefaultRenderer(headerRenderer);
////	Đổi màu chữ của tiêu đề 
//	headerRenderer.setForeground(Color.WHITE);
//	Đổi màu viền của hàng và cột
	table.setGridColor(mauBang);
	//
	try {
		ConnectDB.getInstance().connect();
		dsNCC= new NhaCungCap_DAO();
		dsHang= new Hang_DAO();
		//add date vao bang

		for (Hang Hang : dsHang.getAllHang()) {
			for(NhaCungCap ncc: dsNCC.getAllNhaCungCap())
			{
				if(ncc.getMaNCC().contains(Hang.getNcc().getMaNCC()))
				{
					Object[] row = { Hang.getMaHang(), Hang.getTenHang(), 
							Hang.getLoaiHang(),
							 ncc.getTenNCC(),Hang.getNgaySX(),Hang.getNgayHetHan(),Hang.getSoLuong(),tienTeVN.format(Hang.getDonGia())};
					model.addRow(row);
				}
			}
			
		}
		}catch(SQLException e) {
		e.printStackTrace();
	}
	
	 jpCenterW.add(JScroPane = new JScrollPane(table),BorderLayout.CENTER);
	jpWest.add(jpCenterW,BorderLayout.CENTER);
	//South in west
	jpSouthW= new JPanel();
	jpSouthW.setLayout( new BoxLayout(jpSouthW,BoxLayout.X_AXIS));
	jpSouthW.add(new JLabel("Số lượng: "));
	jpSouthW.add(Box.createHorizontalStrut(10));
	jpSouthW.add(txt_Soluong= new JTextField(5));
	jpSouthW.add(Box.createHorizontalStrut(10));
	jpSouthW.add(btn_them= new JButton("Thêm"));
	btn_them.addActionListener(this);
	jpWest.add(jpSouthW,BorderLayout.SOUTH);
	add(jpWest, BorderLayout.WEST);
	//East--------------------------------------------------------------------------------------------
	jpEast= new JPanel();
	jpEast.setLayout(new BorderLayout());
	//North In East
	jpNorthE= new JPanel();
	jpNorthE.setLayout( new BoxLayout(jpNorthE,BoxLayout.Y_AXIS));
	row1E= new JPanel();
	row1E.setLayout( new BoxLayout(row1E,BoxLayout.X_AXIS));
	row1E.add(new JLabel("Mã hóa đơn: "));
	row1E.add(Box.createHorizontalStrut(15));
	row1E.add(txtMaHD= new JTextField(15));
	txtMaHD.setEditable(false);
	dsHD= new HoaDon_DAO();
	dsCTHD= new ChiTietHoaDon_DAO();
	maHD=createId(HoaDon_DAO.getInstance().getAllHoaDon());
	txtMaHD.setText(maHD);
	
	//
	row2E= new JPanel();
	row2E.setLayout( new BoxLayout(row2E,BoxLayout.X_AXIS));
	row2E.add(new JLabel("Tên nhân viên:"));
	row2E.add(Box.createHorizontalStrut(10));
	//
	cbTenNV = new JComboBox<>();
	cbTenNV.addItem("");
	//--------------------------
	dsNV= new NhanVien_DAO();
	try {
		ConnectDB.getInstance().connect();
		for (NhanVien nv:dsNV.getAllNhanVien())
		{
			cbTenNV.addItem(nv.getTenNV());
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	row2E.add(cbTenNV);
	//
	row3E= new JPanel();
	row3E.setLayout( new BoxLayout(row3E,BoxLayout.X_AXIS));
	row3E.add(new JLabel("Tên khách hàng:"));
	row3E.add(Box.createHorizontalStrut(10));
	//
	cbTenKH = new JComboBox<>();
	cbTenKH.addItem("");
	row3E.add(cbTenKH);
	dsKH= new KhachHang_DAO();
	dsHoaDon= new HoaDon_DAO();
	try {
		ConnectDB.getInstance().connect();
		for (KhachHang kh:dsKH.getAllKhachHang())
		{
			cbTenKH.addItem(kh.getTenKH());
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	//
	jpNorthE.add(row1E);
	//
	jpNorthE.add(Box.createVerticalStrut(10));
	
	jpNorthE.add(row2E);
	jpNorthE.add(Box.createVerticalStrut(10));
	
	jpNorthE.add(row3E);
	jpEast.add(jpNorthE,BorderLayout.NORTH);
		//Center in east
	jpCenterE= new JPanel();
	modelHD = new DefaultTableModel();
//		Giờ khai báo table này để add model vào rỗng nè
	tableHD = new JTable(modelHD);
//		addColumn là thêm tiêu đè cột thôi
	modelHD.addColumn("STT");
	modelHD.addColumn("Mã hàng");
	modelHD.addColumn("Tên Hàng");
	modelHD.addColumn("Số lượng");
	modelHD.addColumn("Đơn giá");
	// data for nhap hang
	
	//Đổi màu xanh của thanh tiêu đề
		DefaultTableCellRenderer headerRenderer1 = new DefaultTableCellRenderer();
		headerRenderer1.setBackground(mauBang);
		tableHD.getTableHeader().setDefaultRenderer(headerRenderer1);
////		Đổi màu chữ của tiêu đề 
//		headerRenderer.setForeground(Color.WHITE);
//		Đổi màu viền của hàng và cột
		tableHD.setGridColor(mauBang);
		//
	//
	 jpCenterE.add(JScroPane1 = new JScrollPane(tableHD),BorderLayout.CENTER);
	jpEast.add(jpCenterE,BorderLayout.CENTER);
		//South in east
		jpSouthE= new JPanel();
		jpSouthE.setLayout( new BoxLayout(jpSouthE,BoxLayout.Y_AXIS));
		row1SE= new JPanel();
		row1SE.setLayout( new BoxLayout(row1SE,BoxLayout.X_AXIS));
		row1SE.add(btn_XuatExcel= new JButton("Xuất Excel"));
		row1SE.add(Box.createHorizontalStrut(10));
		row1SE.add(btn_XoaSanPham= new JButton("Xóa Sản phẩm"));
		btn_XoaSanPham.addActionListener(this);
		row1SE.add(Box.createHorizontalStrut(10));
//		row1SE.add(btn_Suasoluong= new JButton("Sửa số lượng "));
		row2SE= new JPanel();
		row2SE.setLayout( new BoxLayout(row2SE,BoxLayout.X_AXIS));
		row2SE.add( new JLabel("Tổng tiền: "));
		row2SE.add(Box.createHorizontalStrut(10));
		row2SE.add(lbl_TongTien= new JLabel("0"));
		row2SE.add(Box.createHorizontalStrut(50));
		row2SE.add(btn_XuatHD= new JButton("Xuất hóa đơn"));
		btn_XuatHD.addActionListener(this);
		jpSouthE.add(row1SE);
		jpSouthE.add(Box.createVerticalStrut(10));
		jpSouthE.add(row2SE);
		i=0;
		jpEast.add(jpSouthE,BorderLayout.SOUTH);
	add(jpEast, BorderLayout.CENTER);
	setTitle("Hóa Đơn");
	setSize(1050, 650);
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setResizable(true);
	setLocationRelativeTo(null);
}
public static void main(String[] args) throws Exception {
	new HoaDon_GUI().setVisible(true);
}
public void loadDataToTableNhapHang() {
    double sum = 0;
    try {
    	
        modelHD.setRowCount(0);
        for (ChiTietHoaDon ctHD:  dsCTHD.getAllCTHoaDon()) {
        	int i=0;
            modelHD.addRow(new Object[]{
                i + 1, ctHD.getHg().getMaHang(), findHang(ctHD.getMaHD()).getTenHang(), ctHD.getSoluong(), tienTeVN.format(ctHD.getDonGia())
            });
            sum += ctHD.getDonGia() * ctHD.getSoluong();
            i++;
        }
    } catch (Exception e) {
    }
    lbl_TongTien.setText(tienTeVN.format(sum));
}
public Hang findHang(String maHang) {
    for (Hang hg  : dsHang.getAllHang()) {
        if (maHang.equals(hg.getMaHang())) {
            return hg;
        }
    }
    return null;
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
Object obj= e.getSource();
	
	if(obj.equals(btn_them))
	{
		 // TODO add your handling code here:
        int i_row = table.getSelectedRow();
        if (i_row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập hàng !");
        } else {
            int soluong;
           
                soluong = Integer.parseInt(txt_Soluong.getText().trim());
                if (soluong > 0) {
                    ChiTietHoaDon mtl = findCTHoaDon(table.getValueAt(i_row, 0).toString());
                    
                        Hang hg = findHang(table.getValueAt(i_row, 0).toString());
                        ChiTietHoaDon ctHD = new ChiTietHoaDon(txtMaHD.getText(), hg, soluong,hg.getDonGia());
                        dsCTHD.addCTHoaDon(ctHD);
                        i=i+1;
                         double giaTien=Integer.parseInt(txt_Soluong.getText())*hg.getDonGia();
                        String[] dataCTHD= {Integer.toString(i),table.getValueAt(i_row, 0).toString(),table.getValueAt(i_row, 1).toString(),txt_Soluong.getText(),tienTeVN.format(hg.getDonGia())};
                        modelHD.addRow(dataCTHD);;
                    
//                    loadDataToTableNhapHang();
                    lbl_TongTien.setText(tienTeVN.format(tinhTongTien()) );
                    JOptionPane.showMessageDialog(this, "Thêm vào hóa đơn thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0");
                }
            
        }
	
	}
	if(obj.equals(btn_XuatHD))
	{
		// TODO add your handling code here:
        if (dsCTHD.getAllCTHoaDon().size() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm để nhập hàng !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        } else {
            int check = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xuất hóa đơn ?", "Xác nhận nhập hàng", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                // Lay thoi gian hien tai
               long now = System.currentTimeMillis();
                Timestamp sqlTimestamp = new Timestamp(now);
                dsCTHDnew= new ArrayList<ChiTietHoaDon>();
                // Tao doi tuong phieu nhap
               
                for(ChiTietHoaDon ctHD:dsCTHD.getAllCTHoaDon())
                {
                	if(ctHD.getMaHD().equalsIgnoreCase(txtMaHD.getText()));
                	{
//                		entityCTHD=ctHD;
                		dsCTHDnew.add(ctHD);
                	}
                }
                
                for(KhachHang kh:dsKH.getAllKhachHang())
                {
                	if(kh.getTenKH().equalsIgnoreCase(cbTenKH.getSelectedItem().toString()))
                	{
                		khMH=kh;
                		
                	}
                	break;
                }
                for(NhanVien nv:dsNV.getAllNhanVien())
                {
                	if(nv.getTenNV().equalsIgnoreCase(cbTenNV.getSelectedItem().toString()))
                	{
                		nvMH=nv;
                		
                	}
                	break;
                }
                java.util.Date uDate = new java.util.Date();
              
                java.sql.Date sDate = convertUtilToSql(uDate);
                HoaDon hd6= new HoaDon(txtMaHD.getText(), khMH, nvMH,dsCTHDnew, sDate, tinhTongTien());
                try {
                    dsHoaDon.addHoaDon(hd6);
//                    Hang_DAO hdao = Hang_DAO.getInstance();
//                    for (var i : dsCTHDnew) {
//                    	for(Hang hg:dsHang.getAllHang())
//                        {
//                        	if(hg.getMaHang().equalsIgnoreCase(i.getHg().getMaHang()))
//                        	{
//                        		 dsHang.updatesoluongHang(hg.getMaHang(), hg.getSoLuong()-i.getSoluong());
//                        	}
//                        }
//                       
//                    }
                    
                    JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công !");
                    i=0;
                    int res = JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất file pdf ?","",JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        WritePDF writepdf = new WritePDF();
                        writepdf.writeHoaDon(txtMaHD.getText());
                    }
                    ArrayList<Hang> hg= (ArrayList<Hang>) Hang_DAO.getInstance().getAllHang();
                    txt_Soluong.setText("0");
                    
                    modelHD.setRowCount(0);
//                    CTPhieu = new ArrayList<>();
                    lbl_TongTien.setText("0");
                    
                    txtMaHD.setText(createId(HoaDon_DAO.getInstance().getAllHoaDon()));
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
	}
	if(obj.equals(btn_XoaSanPham))
	{
		 int i_row = tableHD.getSelectedRow();
	        if (i_row == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xoá khỏi bảng nhập hàng !");
	        } else {
	            
	            dsCTHD.deleteChiTietHoaDon(txtMaHD.getText(),tableHD.getValueAt(i_row, 1).toString());
	            
	            lbl_TongTien.setText(tienTeVN.format(tinhTongTien()) + "đ");
	            modelHD.removeRow(i_row);
	        }
	}
	
	if(obj.equals(btn_timkiem))
	{
	
        String textSearch = txtTimKiem.getText().toLowerCase();
        ArrayList<Hang> hg = new ArrayList<>();
        for (Hang i : dsHang.getAllHang()) {
            if (i.getTenHang().toLowerCase().contains(textSearch)) {
               hg.add(i);
            }
        }
        loadDataToTableProduct(hg);
        model.setRowCount(0);
        for (Hang hang : hg) {
			for(NhaCungCap ncc: dsNCC.getAllNhaCungCap())
			{
				if(ncc.getMaNCC().contains(hang.getNcc().getMaNCC()))
				{
					Object[] row = { hang.getMaHang(), hang.getTenHang(), 
							hang.getLoaiHang(),
							 ncc.getTenNCC(),hang.getNgaySX(),hang.getNgayHetHan(),hang.getSoLuong(),tienTeVN.format(hang.getDonGia())};
					model.addRow(row);
				}
			}
    
	}}
     
}
private void loadDataToTableProduct(ArrayList<Hang> hg) {
	// TODO Auto-generated method stub
	
}
public ChiTietHoaDon findCTHoaDon(String maHang) {
    for (var i : dsCTHD.getAllCTHoaDon()) {
        if (maHang.equals(i.getHg().getMaHang())&&i.getMaHD().contains(txtMaHD.getText())==false) {
            return i;
        }
    }
    return null;
}
public double tinhTongTien() {
    double tt = 0;
    for (ChiTietHoaDon ctHD: dsCTHD.getAllCTHoaDon()) {
    	if(ctHD.getMaHD().equals(txtMaHD.getText()))
    	{
        tt += ctHD.getDonGia() * ctHD.getSoluong();
    	}
    }
    return tt;
}
public String createId(List<HoaDon> list) {
    int id = list.size() + 1;
    String check = "";
    for (HoaDon hd: list) {
        if (hd.getMaHD().equals("HD" + id)) {
            check = hd.getMaHD();
        }
    }
    while (check.length() != 0) {
        String c = check;
        id++;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaHD().equals("HD" + id)) {
                check = list.get(i).getMaHD();
            }
        }
        if (check.equals(c)) {
            check = "";
        }
    }
    return "HD" + id;
}
private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
    java.sql.Date sDate = new java.sql.Date(uDate.getTime());
    return sDate;
}
}
