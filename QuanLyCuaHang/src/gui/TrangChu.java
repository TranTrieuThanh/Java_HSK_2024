package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class TrangChu extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel pnContent;
	private JMenuBar menuBar;
	private JMenu menuTaiKhoan, menuHang, menuKhachHang, menuDichVu, menuHoaDon,
	menuTraCuu, menuThoat;
	private JMenuItem itemDangXuat, itemQLHang, itemDatHang, itemQL_TTKH, itemDatDichVu, itemQL_TTDV,
	itemLapHoaDon, itemTraCuuHang, itemTraCuuDV, itemTraCuuHD, itemTraCuuKH, itemTraCuuNV;
	private JPanel pCen;
	private JMenu menuNhanVien;
	private JMenuItem itemQL_TTNV;
	private JMenuItem itemTTCa;
	private JMenuItem itemChiaCa;
	private Icon iconQuanLy1;
	private JMenuItem itemTimHang;
	private JMenu menuNCC;
	private JMenuItem itemQL_NCC;
	private JMenuItem itemTIM_NCC;
	private JMenuItem itemTraCuuNCC;
	
	public TrangChu() throws Exception {
		buildGUI();
	}
	public void buildGUI() throws Exception {
		setTitle("Trang chủ");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		createGUI();
		createMenuGUI();
	}
	
	
	public void createGUI() throws IOException {
		pCen = new JPanel();
		pCen.setLayout(new FlowLayout());
		Color mauNen = new Color(99, 184, 255);
		

		JLabel lblTitle = new JLabel("PHẦN MỀM BÁN HÀNG TẠI CỬA HÀNG TIỆN LỢI ");
		pCen.add(lblTitle);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.white);

		BufferedImage image = ImageIO.read(new File("images/banner.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(image));
		pCen.add(picLabel);
		pCen.setBackground(mauNen);
		Container container = getContentPane();
		container.add(pCen);
	}
	
	
	public void createMenuGUI() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		
//		Menu phong
		menuHang = new JMenu("HÀNG");
		menuBar.add(menuHang);
		
		itemQLHang = new JMenuItem("QUẢN LÝ HÀNG");
		menuHang.add(itemQLHang);
	


		
		
		//Menu Nhan Vien
		menuNhanVien = new JMenu("NHÂN VIÊN");
		menuBar.add(menuNhanVien);
		itemQL_TTNV = new JMenuItem("QUẢN LÝ NHÂN VIÊN");
		menuNhanVien.add(itemQL_TTNV);

		
//		Menu Khach Hang
		menuKhachHang = new JMenu("KHÁCH HÀNG");
		menuBar.add(menuKhachHang);
		itemQL_TTKH = new JMenuItem("QUẢN LÝ KHÁCH HÀNG");
		menuKhachHang.add(itemQL_TTKH);


//		
		
//NHA CUNG CAP
		menuNCC = new JMenu("NHÀ CUNG CẤP");
		menuBar.add(menuNCC);
		itemQL_NCC = new JMenuItem("QUẢN LÝ NHÀ CUNG CẤP");
		
		menuNCC.add(itemQL_NCC);


		
		

		
//		Menu Lap Hoa Don
		menuHoaDon = new JMenu("HÓA ĐƠN");
		menuBar.add(menuHoaDon);
		itemLapHoaDon = new JMenuItem("TẠO HÓA ĐƠN");
		menuHoaDon.add(itemLapHoaDon);


		
		
//		Menu Tra Cuu
		menuTraCuu = new JMenu("TRA CỨU");
		itemTraCuuHang = new JMenuItem("TRA CỨU HÀNG");
		itemTraCuuNCC= new JMenuItem("TRA CỨU NHÀ CUNG CẤP");
		itemTraCuuHD = new JMenuItem("TRA CỨU HÓA ĐƠN");
		itemTraCuuKH = new JMenuItem("TRA CỨU KHÁCH HÀNG");
		itemTraCuuNV = new JMenuItem("TRA CỨU NHÂN VIÊN");
		menuHang.add(itemTraCuuHang);
		menuNCC.add(itemTraCuuNCC);
		menuHoaDon.add(itemTraCuuHD);
		menuKhachHang.add(itemTraCuuKH);
		menuNhanVien.add(itemTraCuuNV);
		

		
		itemQLHang.addActionListener(this);
		
		itemQL_TTKH.addActionListener(this);
		itemLapHoaDon.addActionListener(this);
		
		itemTraCuuHD.addActionListener(this);
		itemTraCuuKH.addActionListener(this);
		itemTraCuuNV.addActionListener(this);
		itemTraCuuHang.addActionListener(this);
		itemTraCuuNCC.addActionListener(this);

	}
	public static void main(String[] args) throws Exception {
		new TrangChu().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object o = e.getSource();
			if (o.equals(itemQLHang)) {
				new Hang_GUI().setVisible(true);;
				this.dispose();
			}
			else if (o.equals(itemQL_TTNV))
				new NhanVien_GUI().setVisible(true);
			else if (o.equals(itemQL_NCC))
				new NhaCungCap_GUI().setVisible(true);
			else if (o.equals(itemQL_TTKH))
				new KhachHang_GUI().setVisible(true);
			else if (o.equals(itemLapHoaDon))
				new HoaDon_GUI().setVisible(true);
			else if (o.equals(itemTraCuuHang))
				new HangTK_GUI().setVisible(true);
			else if (o.equals(itemTraCuuKH))
				new KhachHangTK_GUI().setVisible(true);
			else if (o.equals(itemTraCuuNV))
				new NhanVienTK_GUI().setVisible(true);
			else if (o.equals(itemTraCuuNCC))
				new NhaCungCapTK_GUI().setVisible(true);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
