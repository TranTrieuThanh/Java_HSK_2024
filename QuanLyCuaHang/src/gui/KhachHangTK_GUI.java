package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connection.ConnectDB;
import dao.KhachHang_DAO;
import entity.KhachHang;

public class KhachHangTK_GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton btnTimKiem;
	private JPanel pnContent, pnCenter, pnNorth, pnSouth, pnThem, pnTable;
	private JLabel lblMaKH, lblTenKH, lblSDT, lblTuoi, lblGioiTinh, lblTrangThai;
	private JRadioButton radNam, radNu;
	private JComboBox<String> cbTrangThai;
	private JTextField txtMaKH, txtTenKH, txtSDT, txtTuoi;
	private DefaultTableModel model;
	private JTable table;
	private ButtonGroup bg;
	
	
//	Khai báo màu
	Color mauNen = new Color(99, 184, 255);// màu đỏ
	Color mauBang = new Color(99, 184, 255);// màu lam
	private KhachHang_DAO dskh;
	private String gioitinh;
	
	public KhachHangTK_GUI() throws Exception{
		try {
			ConnectDB.getInstance().connect();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dskh= new KhachHang_DAO();
		createGui();
	}

	public void createGui() {
//		Khởi tạo khung Giao diện
		setTitle("Danh sách khách hàng");
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		khachHang();
	}
	public void khachHang() {
//		Khai báo JPanel tổng
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());

//		Khai báo JPanel Tiêu đề
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);

		JLabel lblTitle = new JLabel("Quản lý thông tin khách hàng");
//		Đổi kiểu chữ
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
//		Đổi màu chữ
		lblTitle.setForeground(Color.WHITE);
//		Thêm lblTitle vào Giao diện Tiêu đề (cái này quan trọng, không thêm lblTitle là nó không thêm Tiêu đề được)
		pnNorth.add(lblTitle);
//		Đổi màu nền
		pnNorth.setBackground(mauNen);

//		Khai báo Panel Thêm thông tin
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);

		pnThem = new JPanel();
		pnCenter.add(pnThem);
//		Tạo các Box để nó auto canh lable với textfield
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
//		b.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(mauNen), "Thông tin khách hàng"));
//		BorderFactory.createLineBorder(mauNen): đổi màu khung bao
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(mauNen),
				"Thông tin khách hàng");
//		Canh giữa "Thông tin khách hàng"
		titledBorder.setTitleJustification(TitledBorder.CENTER);
//		Thêm border "Thông tin khách hàng" vào Giao diện
		pnThem.setBorder(titledBorder);
		
		lblMaKH = new JLabel("Mã khách hàng");
		txtMaKH = new JTextField(20);
		b1.add(lblMaKH);
//		Box.createHorizontalStrut(10): tạo khoảng cách giữa Jlabel với JTextField
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtMaKH);
		
//		Box.createHorizontalStrut(50): tạo khoảng cách của "Mã khách hàng" và "Giới tính" trên cùng 1 dòng 
		b1.add(Box.createHorizontalStrut(50));
		
		lblGioiTinh = new JLabel("Giới tính");
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		bg = new ButtonGroup();
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblGioiTinh);
		b1.add(Box.createHorizontalStrut(30));
		b1.add(radNam); b1.add(radNu);
		bg.add(radNam); bg.add(radNu);
		 
		lblTenKH = new JLabel("Tên khách hàng ");
		txtTenKH = new JTextField();
		b2.add(lblTenKH);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtTenKH);
		b2.add(Box.createHorizontalStrut(40));
		lblSDT = new JLabel("SDT");
		txtSDT = new JTextField();
		b2.add(lblSDT);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtSDT);
		
		lblTuoi = new JLabel("        Tuổi");
		txtTuoi = new JTextField(10);
		b3.add(lblTuoi);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txtTuoi);
		b3.add(Box.createHorizontalStrut(50));
//		lblTrangThai = new JLabel("Trạng thái");
//		String trangthai[] = {"Đã thanh toán", "Chưa thanh toán"};
//		cbTrangThai = new JComboBox(trangthai);
//		cbTrangThai.setBounds(100, 50, 150, 20);
//		b3.add(lblTrangThai);
//		b3.add(Box.createHorizontalStrut(10));
//		b3.add(cbTrangThai);
		
//		Canh tiêu đề 'mã khách hàng' bằng tiêu đề 'tên khách hàng', 
//		...
		lblMaKH.setPreferredSize(lblTenKH.getPreferredSize());
		lblTuoi.setPreferredSize(lblTenKH.getPreferredSize());
//		lblSDT.setPreferredSize(lblTrangThai.getPreferredSize());
		
//		Thêm các Box vào giao diện chính
		pnThem.add(b);
		b.add(b1);
//		Box.createRigidArea(new DimensionUIResource(0, 20)): tạo khoảng cách giữa label trên và label dưới
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b2);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b3);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		b.add(b4);
		b.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
		
//		Tạo chức năng tìm kiếm
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
//		pnSouth.setBorder(BorderFactory.createTitledBorder("Chọn chức năng"));
		btnTimKiem = new JButton("Tìm Kiếm");
//		Đổi màu nền của button
		btnTimKiem.setBackground(mauNen);
//		Đổi màu chữ
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(mauNen);
		btnTimKiem.setForeground(Color.white);
//		Thêm các chức năng đó vào Giao diện
		pnSouth.add(btnTimKiem);
		pnSouth.add(Box.createHorizontalStrut(30));
		
//		Khởi tạo bảng
		pnTable = new JPanel();
//		createTable(): là đem public void createTable() lên đây thì nó mới thực thi trong giao diện được
		createTable();
//		Thêm bảng vào trong giao diện
		pnSouth.add(pnTable);
//		Tạo khung bao và tiêu đề cho bẳng
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(mauNen),
				"Danh sách khách hàng ");
		titledBorder1.setTitleJustification(TitledBorder.CENTER);
		pnTable.setBorder(titledBorder1);
		
//		pnContent = new JPanel();
//		pnContent.setLayout(new BorderLayout()); ở phía trên chỉ mới khai báo
		this.add(pnContent);
		
		btnTimKiem.addActionListener(this);
		khoaTxt();
	}
//	Khởi tạo bảng
	public void createTable() {
//		khai báo model để tạo 1 khung table rỗng 
		model = new DefaultTableModel();
//		Giờ khai báo table này để add model vào rỗng nè
		table = new JTable(model);
//		addColumn là thêm tiêu đè cột thôi
		model.addColumn("Mã khách hàng");
		model.addColumn("Tên khách hàng");
		model.addColumn("Tuổi");
		model.addColumn("Giới tính");
		model.addColumn("SĐT");
		

//		Thêm dữ liệu từ SQL và bảng
		try {
			for(KhachHang khachHang : dskh.getAllKhachHang()) {
				if(khachHang.isGioiTinh()==false)
				{
					 gioitinh= "Nữ";
					 
				}
				else
				{
					gioitinh="Nam";
				}
				Object[] row = { khachHang.getMaKH(), khachHang.getTenKH(),
						khachHang.getTuoi(), gioitinh,
						khachHang.getSDT()};
				model.addRow(row);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

//		Chỉnh cỡ chữ của dữ liệu trong bảng
		Font fontTable = new Font("Arial", Font.PLAIN, 14);
		table.setFont(fontTable);
		
//		Thêm bảng vào Panel
		pnTable.add(table);
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		Tạo kích thước của bảng
		sp.setPreferredSize(new Dimension(750, 300));
		pnTable.add(sp);
		
//		Đổi màu xanh của thanh tiêu đề
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(mauBang);
		table.getTableHeader().setDefaultRenderer(headerRenderer);
//		Đổi màu chữ của tiêu đề 
		headerRenderer.setForeground(Color.WHITE);
//		Đổi màu viền của hàng và cột
		table.setGridColor(mauNen);
		table.addMouseListener(new MouseListener() {
//		Click chuột vào bảng thì dữ liệu sẽ chuyển lên trên JTextField
		
//		@Override
		public void mouseReleased(MouseEvent e) {			
			int row = table.getSelectedRow();
			txtMaKH.setText(model.getValueAt(row, 0).toString());
			txtTenKH.setText(model.getValueAt(row, 1).toString());
			txtTuoi.setText(model.getValueAt(row, 2).toString());
			String gioiTinh = (model.getValueAt(row, 3).toString());
			if(gioiTinh.equals("Nam")) {
				radNam.setSelected(true);
			}else
				radNu.setSelected(true);
			txtSDT.setText(model.getValueAt(row, 4).toString());
	
		
		}

//		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

//		@Override
		public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

		}

//		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

//		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	});
}

	public static void main(String[] args) throws Exception {
		new KhachHangTK_GUI().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			if (btnTimKiem.getText().equalsIgnoreCase("Tìm Kiếm")) {
				moKhoaTxt();
				btnTimKiem.setText("Hủy");
				btnTimKiem.setEnabled(false);
			} else {
				khoaTxt();
				btnTimKiem.setText("Tìm Kiếm");
				btnTimKiem.setEnabled(true);
			}
			
		int row = table.getSelectedRow();

			String maKHMoi = txtMaKH.getText();
			boolean maDaTonTai = false;

			for (KhachHang khachHang : dskh.getAllKhachHang()) {
				if (khachHang.getMaKH().contains(maKHMoi)) {
					maDaTonTai = true;
					break;
				}
			}
			if (btnTimKiem.getText().equals("Hủy")) {
				if (maDaTonTai == true) {
					JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại");
				} else {
					if (kTraLoi() == true) {
						String tinhTrang = (String) cbTrangThai.getSelectedItem();
						String gt;
						if(radNam.isSelected())
							gt = "Nam";
						else
							gt = "Nữ";
						KhachHang khachHang = new KhachHang(txtMaKH.getText(), txtTenKH.getText(),
								Integer.parseInt(txtTuoi.getText()), radNam.isSelected(),txtSDT.getText() );
						dskh.addKhachHang(khachHang);
						JOptionPane.showMessageDialog(this, "Lưu thành công!");
						String[] data = { txtMaKH.getText(), txtTenKH.getText(), txtTuoi.getText(),
								gt, txtSDT.getText(), tinhTrang };
						model.addRow(data);
						reset();
					}
				}
			
				if (kTraLoi() == true) {
					String tinhTrang = (String) cbTrangThai.getSelectedItem();
					String gt;
					if (radNam.isSelected())
						gt = "Nam";
					else
						gt = "Nữ";
					KhachHang khachHang = new KhachHang(txtMaKH.getText(), txtTenKH.getText(),
							Integer.parseInt(txtTuoi.getText()), radNam.isSelected(),txtSDT.getText() );
					String[] data = { txtMaKH.getText(), txtTenKH.getText(), txtTuoi.getText(), gt, txtSDT.getText(),
							tinhTrang };
//				Thêm dòng mới vào Jtable
					model.insertRow(row, data);
					model.removeRow(row + 1);
					dskh.updateKhachHang(khachHang);
					JOptionPane.showMessageDialog(this, "Lưu thành công");
					reset();
				}
				
			}
		
			
			if (row != -1) {
				
				
					moKhoaTxt();
					
					btnTimKiem.setEnabled(false);
			
				} else {
					khoaTxt();
					btnTimKiem.setEnabled(true);

				}
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để tìm kiếm !");
			}
	
			dispose();
		}
	

	

	public boolean kTraLoi() {
		String ma = txtMaKH.getText();
		String ten = txtTenKH.getText();
		String tuoi = txtTuoi.getText();
		String sDT = txtSDT.getText();
		
		if(ma.equals("") || ten.equals("") || tuoi.equals("") || 
				sDT.equals(""))
//				radNam.getSelectedIcon() == null && radNu.getSelectedIcon() == null)) {
			{JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
		return false;
		}
		if(!ma.matches("KH\\d{6}")) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng lỗi! Theo dạng KHxxxxxx với x từ [0-9]");
			txtMaKH.requestFocus();
			return false;
		}
		try {
			if (Integer.parseInt(tuoi) < 0) {
				JOptionPane.showMessageDialog(this, "Tuổi phải lớn hơn 0");
				txtTuoi.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Tuổi phải nhập vào số");
			return false;
		}
		try {
			if (Integer.parseInt(sDT) < 0 || sDT.length() != 10 || sDT.charAt(0) != '0') {
				JOptionPane.showMessageDialog(this, "Sai định dạng SDT");
				txtSDT.requestFocus();
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "SDT phải nhập vào số");
			return false;
		}
		return true;
	}
	public void reset() {
		// TODO Auto-generated method stub
		khoaTxt();

		if (btnTimKiem.getText().equals("Hủy")) {
			btnTimKiem.setText("Tìm Kiếm");
		}

		
			btnTimKiem.setEnabled(true);
			
		}
	
	public void moKhoaTxt() {
		// TODO Auto-generated method stub
		
			txtMaKH.setEditable(false);
		
			txtMaKH.setEditable(true);
		txtTenKH.setEditable(true);
		txtTuoi.setEditable(true);
		radNam.setFocusable(true);
		radNu.setFocusable(true);
		txtSDT.setEditable(true);
		
	}
	public void khoaTxt() {
		// TODO Auto-generated method stub
		txtMaKH.setEditable(false);
		txtTenKH.setEditable(false);
		txtTuoi.setEditable(false);
		radNam.setFocusable(false);
		radNu.setFocusable(false);
		txtSDT.setEditable(false);
		
	}
}
