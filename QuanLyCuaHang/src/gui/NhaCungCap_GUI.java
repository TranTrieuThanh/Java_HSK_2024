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
import dao.NhaCungCap_DAO;
import entity.KhachHang;
import entity.NhaCungCap;

public class NhaCungCap_GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnSua, btnThem, btnXoa, btnXoaTrang, btnLuu, btnThoat;
	private JPanel pnContent, pnCenter, pnNorth, pnSouth, pnThem, pnTable;
	private JLabel lblMaNCC, lblTenNCC, lblSDT, lblTuoi, lblGioiTinh, lblTrangThai;
	private JRadioButton radNam, radNu;
	private JComboBox<String> cbTrangThai;
	private JTextField txtMaNCC, txtTenNCC, txtSDT, txtDiaChi;
	private DefaultTableModel model;
	private JTable table;
	private ButtonGroup bg;
	
	
//	Khai báo màu
	Color mauNen = new Color(99, 184, 255);// màu đỏ
	Color mauBang = new Color(99, 184, 255);// màu lam
	private KhachHang_DAO dskh;
	private String gioitinh;
	private NhaCungCap_DAO dsNCC;
	
	public NhaCungCap_GUI() throws Exception{
		try {
			ConnectDB.getInstance().connect();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dsNCC= new NhaCungCap_DAO();
		createGui();
	}

	public void createGui() {
//		Khởi tạo khung Giao diện
		setTitle("Danh sách nhà cung cấp");
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

		JLabel lblTitle = new JLabel("Quản lý nhà cung cấp");
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
		

		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(mauNen),
				"Thông tin nhà cung cấp");
//		Canh giữa "Thông tin khách hàng"
		titledBorder.setTitleJustification(TitledBorder.CENTER);
//		Thêm border "Thông tin khách hàng" vào Giao diện
		pnThem.setBorder(titledBorder);
		
		lblMaNCC = new JLabel("Mã nhà cung cấp");
		txtMaNCC = new JTextField(20);
		b1.add(lblMaNCC);
//		Box.createHorizontalStrut(10): tạo khoảng cách giữa Jlabel với JTextField
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtMaNCC);
		
//		Box.createHorizontalStrut(50): tạo khoảng cách của "Mã khách hàng" và "Giới tính" trên cùng 1 dòng 
		b1.add(Box.createHorizontalStrut(50));
		
		
		;
		b1.add(Box.createHorizontalStrut(30));
		
		 
		lblTenNCC = new JLabel("Tên nhà cung cấp ");
		txtTenNCC = new JTextField();
		b2.add(lblTenNCC);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtTenNCC);
		b2.add(Box.createHorizontalStrut(40));
		lblSDT = new JLabel("SDT");
		txtSDT = new JTextField();
		b2.add(lblSDT);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtSDT);
		
		lblTuoi = new JLabel("Địa chỉ");
		txtDiaChi = new JTextField(10);
		b3.add(lblTuoi);
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txtDiaChi);
		b3.add(Box.createHorizontalStrut(50));

		
//		Canh tiêu đề 'mã khách hàng' bằng tiêu đề 'tên khách hàng', 
//		...
		lblMaNCC.setPreferredSize(lblTenNCC.getPreferredSize());
		lblTuoi.setPreferredSize(lblTenNCC.getPreferredSize());
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
		
//		Tạo các chức năng như thêm, xóa, xóa trắng, cập nhật, lưu, thoát
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
//		pnSouth.setBorder(BorderFactory.createTitledBorder("Chọn chức năng"));
		btnThem = new JButton("Thêm");
//		Đổi màu nền của button
		btnThem.setBackground(mauNen);
//		Đổi màu chữ
		btnThem.setForeground(Color.WHITE);
		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(mauNen);
		btnXoa.setForeground(Color.WHITE);
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setBackground(mauNen);
		btnXoaTrang.setForeground(Color.white);
		btnSua = new JButton("Cập nhật");
		btnSua.setBackground(mauNen);
		btnSua.setForeground(Color.white);
		btnLuu = new JButton("Lưu");
		btnLuu.setBackground(mauNen);
		btnLuu.setForeground(Color.white);
		btnThoat = new JButton("Thoát");
		btnThoat.setBackground(mauNen);
		btnThoat.setForeground(Color.white);
//		Thêm các chức năng đó vào Giao diện
		pnSouth.add(btnThem);
		pnSouth.add(Box.createHorizontalStrut(30));
		pnSouth.add(btnXoa);
		pnSouth.add(Box.createHorizontalStrut(30));
		pnSouth.add(btnXoaTrang);
		pnSouth.add(Box.createHorizontalStrut(30));
		pnSouth.add(btnSua);
		pnSouth.add(Box.createHorizontalStrut(30));
		pnSouth.add(btnLuu);
		pnSouth.add(Box.createHorizontalStrut(30));
		pnSouth.add(btnThoat);
		
//		Khởi tạo bảng
		pnTable = new JPanel();
//		createTable(): là đem public void createTable() lên đây thì nó mới thực thi trong giao diện được
		createTable();
//		Thêm bảng vào trong giao diện
		pnSouth.add(pnTable);
//		Tạo khung bao và tiêu đề cho bẳng
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(mauNen),
				"Danh sách nhà cung cấp ");
		titledBorder1.setTitleJustification(TitledBorder.CENTER);
		pnTable.setBorder(titledBorder1);
		
//		pnContent = new JPanel();
//		pnContent.setLayout(new BorderLayout()); ở phía trên chỉ mới khai báo
		this.add(pnContent);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
		khoaTxt();
	}
//	Khởi tạo bảng
	public void createTable() {
//		khai báo model để tạo 1 khung table rỗng 
		model = new DefaultTableModel();
//		Giờ khai báo table này để add model vào rỗng nè
		table = new JTable(model);
//		addColumn là thêm tiêu đè cột thôi
		model.addColumn("Mã nhà cung cấp");
		model.addColumn("Tên nhà cung cấp");
		model.addColumn("Địa chỉ");
		model.addColumn("SĐT");
		

//		Thêm dữ liệu từ SQL và bảng
		try {
			for( NhaCungCap ncc : dsNCC.getAllNhaCungCap()) {
				
				Object[] row = { ncc.getMaNCC(), ncc.getTenNCC(), ncc.getDiaChi(),ncc.getSDT()};
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
			txtMaNCC.setText(model.getValueAt(row, 0).toString());
			txtTenNCC.setText(model.getValueAt(row, 1).toString());
			txtDiaChi.setText(model.getValueAt(row, 2).toString());
			txtSDT.setText(model.getValueAt(row, 3).toString());
	
		
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
		new NhaCungCap_GUI().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equalsIgnoreCase("Thêm")) {
				moKhoaTxt();
				btnThem.setText("Hủy");
				btnLuu.setEnabled(true);
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnXoaTrang.setEnabled(false);
			} else {
				khoaTxt();
				btnThem.setText("Thêm");
				btnLuu.setEnabled(false);
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(true);
			}
		} else if (o.equals(btnXoa)) {
			try {
				xoa();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaRong();
		} else if (o.equals(btnLuu)) {
			int row = table.getSelectedRow();

			String maNCCMoi = txtMaNCC.getText();
			boolean maDaTonTai = false;

			for (NhaCungCap ncc : dsNCC.getAllNhaCungCap()) {
				if (ncc.getMaNCC().contains(maNCCMoi)) {
					maDaTonTai = true;
					break;
				}
			}
			if (btnThem.getText().equals("Hủy")) {
				if (maDaTonTai == true) {
					JOptionPane.showMessageDialog(this, "Mã nhà cung cấp đã tồn tại");
				} else {
					if (kTraLoi() == true) {
						NhaCungCap ncc= new NhaCungCap(txtMaNCC.getText(), txtTenNCC.getText(),txtDiaChi.getText(),txtSDT.getText());
						dsNCC.addNhaCungCap(ncc);
						JOptionPane.showMessageDialog(this, "Lưu thành công!");
						String[] data = { txtMaNCC.getText(), txtTenNCC.getText(), txtDiaChi.getText(),
								 txtSDT.getText() };
						model.addRow(data);
						reset();
					}
				}
			} else if (btnSua.getText().equals("Hủy")) {
				if (kTraLoi() == true) {
					
					
					NhaCungCap ncc= new NhaCungCap(txtMaNCC.getText(), txtTenNCC.getText(),txtDiaChi.getText(),txtSDT.getText());
					String[] data = { txtMaNCC.getText(), txtTenNCC.getText(), txtDiaChi.getText(),
							 txtSDT.getText() };
//				Thêm dòng mới vào Jtable
					model.insertRow(row, data);
					model.removeRow(row + 1);
					dsNCC.updateNhaCungCap(ncc);
					JOptionPane.showMessageDialog(this, "Lưu thành công");
					reset();
				}
				
			}
		} else if (o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				if (btnSua.getText().equals("Sửa")) {
					btnSua.setText("Hủy");
					moKhoaTxt();
					btnLuu.setEnabled(true);
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
				} else {
					khoaTxt();
					btnSua.setText("Sửa");
					btnLuu.setEnabled(false);
					btnThem.setEnabled(true);
					btnXoa.setEnabled(true);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để sửa !");
			}
		} else if (o.equals(btnThoat)) {
			dispose();
		}
	}
	private void xoaRong() {
		// TODO Auto-generated method stub
		txtMaNCC.setText("");
		txtTenNCC.setText("");
		txtDiaChi.setText("");
		radNam.setDisabledSelectedIcon(null);
		radNu.setDisabledSelectedIcon(null);
		txtSDT.setText("");
		
	}

	private void xoa() {
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa nhà cung cấp này không!", "delete",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				String ma = txtMaNCC.getText();
			dsNCC.deleteNhaCungCap(ma);
			model.removeRow(r);
			}
		} else
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa");
	}

	public boolean kTraLoi() {
		String ma = txtMaNCC.getText();
		String ten = txtTenNCC.getText();
		String tuoi = txtDiaChi.getText();
		String sDT = txtSDT.getText();
		
		if(ma.equals("") || ten.equals("") || tuoi.equals("") || 
				sDT.equals("") )
//				radNam.getSelectedIcon() == null && radNu.getSelectedIcon() == null)) {
			{JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
		return false;
		}
		if(!ma.matches("NCC\\d{3}")) {
			JOptionPane.showMessageDialog(this, "Mã NCC lỗi! Theo dạng NCCxxx với x từ [0-9]");
			txtMaNCC.requestFocus();
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

		if (btnThem.getText().equals("Hủy")) {
			btnThem.setText("Thêm");
			btnLuu.setEnabled(false);
			btnSua.setEnabled(true);
			btnXoa.setEnabled(true);
			btnXoaTrang.setEnabled(true);
		}

		if (btnSua.getText().equals("Hủy")) {
			btnSua.setText("Sửa");
			btnLuu.setEnabled(false);
			btnThem.setEnabled(true);
			btnXoa.setEnabled(true);
			btnXoaTrang.setEnabled(true);
		}
	}
	public void moKhoaTxt() {
		// TODO Auto-generated method stub
		if (btnSua.getText().equals("Hủy"))
			txtMaNCC.setEditable(false);
		else
			txtMaNCC.setEditable(true);
		txtTenNCC.setEditable(true);
		txtDiaChi.setEditable(true);
		
		txtSDT.setEditable(true);
		
	}
	public void khoaTxt() {
		// TODO Auto-generated method stub
		txtMaNCC.setEditable(false);
		txtTenNCC.setEditable(false);
		txtDiaChi.setEditable(false);
	
		txtSDT.setEditable(false);
		
	}
}
