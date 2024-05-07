package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connection.ConnectDB;
import dao.Hang_DAO;
import dao.NhaCungCap_DAO;
import entity.Hang;
import entity.NhaCungCap;



public class Hang_GUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel lblMaHang, lblTenHang, lblLoaiHang, lblGiaHang;
	private JPanel pnContent, pnNorth, pnCenter, pnThem, pnSouth, pnTable;
	private JTextField txtMaHang, txtTenHang;
	private JComboBox  cbLoaiHang;
	private JButton btnThem, btnXoa, btnXoaTrang, btnSua, btnLuu, btnThoat;
	private DefaultTableModel model;
	private JTable table;
	

	Color mauNen = new Color(99, 184, 255);// màu đỏ
	Color mauBang = new Color(99, 184, 255);// màu lam
	
	/***
	 * Chỉnh sửa tiền tệ
	 */
	Locale localeVN = new Locale("vi", "VN");
	NumberFormat tienTeVN = NumberFormat.getCurrencyInstance(localeVN);
	private Hang_DAO dsHang;
	private JLabel lblTenNCC;
	private JLabel lblDonGia;
	private JTextField txtDonGia;
	private JDateChooser dateSX;
	private JDateChooser dateHH;
	private NhaCungCap_DAO dsNCC;
	
	private JLabel lblnhacc;
	private JComboBox cbTenNCC;
	private JLabel lblsoluong;
	private JTextField txtsoluong;
	private SimpleDateFormat sdf;

	//	Lấy dữ liêu từ CSDL vào combobox
	
	public Hang_GUI() throws Exception{
//		Kết nối SQL
		try {
			ConnectDB.getInstance().connect();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		dsHang= new Hang_DAO();
		dsNCC= new NhaCungCap_DAO();
		createGui();
	}

	public void createGui() {
		setTitle("Hàng");
		setSize(1030, 820);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
		quanLyHang();
	}
	
	public void quanLyHang() {
		pnContent = new JPanel();
		pnContent.setLayout(new BorderLayout());
		
		pnNorth = new JPanel();
		pnContent.add(pnNorth, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Quản lý hàng");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
//		Đổi màu chữ
		lblTitle.setForeground(Color.WHITE);
		
		pnNorth.add(lblTitle);
//		Đổi màu nền
		pnNorth.setBackground(mauNen);
		
		pnCenter = new JPanel();
		pnContent.add(pnCenter, BorderLayout.CENTER);

		pnThem = new JPanel();
		pnCenter.add(pnThem);
		
		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		Box b6 = Box.createHorizontalBox();
		Box b7 = Box.createHorizontalBox();
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(mauNen),
				"Thông tin hàng");
//		Canh giữa "Thông tin dịch vụ"
		titledBorder.setTitleJustification(TitledBorder.CENTER);
//		Thêm border "Thông tin dịch vụ" vào Giao diện
		pnThem.setBorder(titledBorder);
		
		lblMaHang = new JLabel("Mã hàng");
		txtMaHang = new JTextField(2);
		
		lblTenHang = new JLabel("Tên hàng");
		txtTenHang = new JTextField(20);
		lblLoaiHang = new JLabel("Loại hàng");
		cbLoaiHang = new JComboBox<>();
		cbLoaiHang.addItem("");
		cbLoaiHang.addItem("Thực phẩm");
		cbLoaiHang.addItem("Nước uống");
		cbLoaiHang.addItem("Gia dụng");
		//
		lblnhacc = new JLabel("Tên nhà cung cấp");
		cbTenNCC = new JComboBox<>();
		cbTenNCC.addItem("");

		JLabel lblNgaySX = new JLabel("Ngày sản xuất: ");
		dateSX = new JDateChooser();
		dateSX.setBounds(320, 90, 200, 30);
		dateSX.setDateFormatString("yyyy/MM/dd");
		JLabel lblNgayHH = new JLabel("Ngày hết hạn: ");
		dateHH = new JDateChooser();
		dateHH.setBounds(320, 90, 200, 30);
		dateHH.setDateFormatString("yyyy/MM/dd");
		lblDonGia = new JLabel("Giá");
		txtDonGia = new JTextField();
		lblsoluong = new JLabel("Số lượng ");
		txtsoluong = new JTextField();
		
		
		
		b1.add(lblMaHang);
		b1.add(Box.createHorizontalStrut(22));
		b1.add(txtMaHang);
		b1.add(Box.createHorizontalStrut(60));
		
		
		b2.add(lblTenHang);
		b2.add(Box.createHorizontalStrut(22));
		b2.add(txtTenHang);
		b2.add(Box.createHorizontalStrut(100));
		b2.add(lblLoaiHang);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(cbLoaiHang);
		b2.add(Box.createHorizontalStrut(60)); 
		
		//
		b3.add(lblnhacc);
		b3.add(Box.createHorizontalStrut(22));
		b3.add(cbTenNCC);
		b3.add(Box.createHorizontalStrut(60)); 
		//
		
		//
		b4.add(lblNgaySX);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(dateSX);
		b4.add(Box.createHorizontalStrut(60));
		
		b4.add(lblNgayHH);
		b4.add(Box.createHorizontalStrut(10));
		b4.add(dateHH);
		b4.add(Box.createHorizontalStrut(60));
		
		//
		b5.add(lblsoluong);
		b5.add(Box.createHorizontalStrut(10));
		b5.add(txtsoluong);
		b5.add(Box.createHorizontalStrut(60));
		//
		b5.add(lblDonGia);
		b5.add(Box.createHorizontalStrut(10));
		b5.add(txtDonGia);
		b5.add(Box.createHorizontalStrut(60));
		
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
		b.add(b5);
	
		
		
		pnSouth = new JPanel();
		pnContent.add(pnSouth, BorderLayout.SOUTH);
//		pnSouth.setBorder(BorderFactory.createTitledBorder("Chọn chức năng"));
		btnThem = new JButton("Thêm");
//		Đổi màu nền của button
		btnThem.setBackground(mauNen);
//		Đổi màu chữ
//		new JButton chỉ là khởi tạo thôi, chứ nó chưa có thêm vô giao diện nha
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
		
		pnTable = new JPanel();
//		createTable(): là đem public void createTable() lên đây thì nó mới thực thi trong giao diện được
		createTable();
//		Thêm bảng vào trong giao diện
		pnSouth.add(pnTable);
//		Tạo khung bao và tiêu đề cho bẳng
		TitledBorder titledBorder1 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(mauNen),
				"Danh sách phòng ");
		titledBorder1.setTitleJustification(TitledBorder.CENTER);
		pnTable.setBorder(titledBorder1);

//		Nhớ phải có cái này:
//		pnContent = new JPanel();
//		pnContent.setLayout(new BorderLayout()); ở phía trên chỉ mới khai báo
//		Cái this.add(pnContent); mới thêm vô giao diện nha
		this.add(pnContent);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThoat.addActionListener(this);
	
		khoaTxt();
	}
	public void createTable() { 
		model = new DefaultTableModel();
//		Giờ khai báo table này để add model vào rỗng nè
		table = new JTable(model);
//		addColumn là thêm tiêu đè cột thôi
		model.addColumn("Mã hàng");
		model.addColumn("Tên hàng");
		model.addColumn("Loại hàng");
		model.addColumn("Tên NCC");
		model.addColumn("Ngày sản xuất");
		model.addColumn("Ngày hết hạn");
		model.addColumn("Số lượng");
		model.addColumn("Giá");
		

		try {
			for(NhaCungCap ncc: dsNCC.getAllNhaCungCap())
			{
				cbTenNCC.addItem(ncc.getTenNCC());
			}
			
			for (Hang hg : dsHang.getAllHang()) {
				for(NhaCungCap ncc: dsNCC.getAllNhaCungCap())
				{
					if(ncc.getMaNCC().contains(hg.getNcc().getMaNCC()))
					{
						Object[] row = { hg.getMaHang(), hg.getTenHang(), 
								hg.getLoaiHang(),
								 ncc.getTenNCC(),hg.getNgaySX(),hg.getNgayHetHan(),hg.getSoLuong(),tienTeVN.format(hg.getDonGia())};
						model.addRow(row);
					}
				}
				
				}
			}catch(Exception e) {
				e.printStackTrace();
		}
		
//		Chỉnh cỡ chữ của dữ liệu trong bảng
		Font fontTable = new Font("Arial", Font.PLAIN, 14);
		table.setFont(fontTable);
		
//		Giờ mới thêm bảng vào Panel nè
		pnTable.add(table);
//		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED 2 cái này là thêm cái thanh trên dưới á
//		Nói sao ta thôi thử xóa 2 cái đó đi là biết:>>
		JScrollPane sp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		Tạo kích thước của bảng
		sp.setPreferredSize(new Dimension(750, 330));
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

			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();
				String loai;
				
				txtMaHang.setText(model.getValueAt(row, 0).toString());
				txtTenHang.setText(model.getValueAt(row, 1).toString());
				loai = (model.getValueAt(row, 2).toString());
				cbLoaiHang.setSelectedItem(loai);
				cbTenNCC.setSelectedItem((model.getValueAt(row, 3).toString()));
				dateSX.setDateFormatString(model.getValueAt(row, 4).toString());
				dateHH.setDateFormatString(model.getValueAt(row, 5).toString());
				txtsoluong.setText(model.getValueAt(row, 6).toString());
				txtDonGia.setText(model.getValueAt(row, 7).toString());

				
			}
			
			

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	public static void main(String[] args) throws Exception {
		new Hang_GUI().setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnThem)) {
			if(btnThem.getText().equalsIgnoreCase("Thêm")) {
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
			}
		else if (o.equals(btnXoa)) {
			try {
				xoa();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaRong();
		} else if (o.equals(btnLuu)) {
			int row = table.getSelectedRow();
			
			String maHangMoi = txtMaHang.getText();
			boolean maDaTonTai = false;
			
			for(Hang Hang : dsHang.getAllHang()) {
				if(Hang.getMaHang().contains(maHangMoi)) {
					maDaTonTai = true;
					break;
				}
			}
			if(btnThem.getText().equals("Hủy")){
//				Kiểm tra tồn tại mã khi thêm vào bảng
				if(maDaTonTai == true) {
					JOptionPane.showMessageDialog(this, "Mã phòng đã tồn tại");	
				}else {
					if(kTraLoi() == true) {
						
						String loai = (String) cbLoaiHang.getSelectedItem();
						String tenNCC = (String) cbTenNCC.getSelectedItem();
						
						java.util.Date date = dateSX.getDate();
						java.sql.Date ngaysx = new java.sql.Date(date.getTime());
						
						java.util.Date date1 = dateHH.getDate();
						java.sql.Date ngayHH = new java.sql.Date(date1.getTime());
						for(NhaCungCap ncc: dsNCC.getAllNhaCungCap())
						{
							if(ncc.getTenNCC().equalsIgnoreCase(tenNCC))
							{
								Hang Hang = new Hang(txtMaHang.getText(),txtTenHang.getText(),loai,ncc,ngaysx,ngayHH,Integer.parseInt(txtsoluong.getText()),
										Double.parseDouble(txtDonGia.getText()));
								dsHang.addHang(Hang);
								break;
							}
						}
						
						JOptionPane.showMessageDialog(this, "Lưu thành công!");
						 sdf = new SimpleDateFormat("yyyy-MM-dd");
			          
//						String[] data = { txtMaHang.getText(), txtTenHang.getText(), loai,
//								tenNCC,sdf.format(date),sdf.format(date1),txtsoluong.getText(),tienTeVN.format(txtDonGia.getText()) };
						String[] data1= {txtMaHang.getText(),txtTenHang.getText(),loai,tenNCC,sdf.format(date),sdf.format(date1),txtsoluong.getText(),tienTeVN.format(Double.parseDouble(txtDonGia.getText()))};
						model.addRow(data1);
						reset();
					}
						
					}
				}else if(btnSua.getText().equals("Hủy")) {
					if (kTraLoi() == true) {
						String loai = (String) cbLoaiHang.getSelectedItem();
						String tenNCC = (String) cbTenNCC.getSelectedItem();
						
						java.util.Date date = dateSX.getDate();
						java.sql.Date ngaysx = new java.sql.Date(date.getTime());
						
						java.util.Date date1 = dateHH.getDate();
						java.sql.Date ngayHH = new java.sql.Date(date1.getTime());
						for(NhaCungCap ncc: dsNCC.getAllNhaCungCap())
						{
							if(ncc.getTenNCC().equalsIgnoreCase(tenNCC))
							{
								Hang Hang = new Hang(txtMaHang.getText(),txtTenHang.getText(),loai,ncc,ngaysx,ngayHH,Integer.parseInt(txtsoluong.getText()),
										Double.parseDouble(txtDonGia.getText()));
								
								dsHang.updateHang(Hang);
								break;
							}
						}
						String[] data1= {txtMaHang.getText(),txtTenHang.getText(),loai,tenNCC,sdf.format(date),sdf.format(date1),txtsoluong.getText(),tienTeVN.format(Double.parseDouble(txtDonGia.getText()))};
						
						model.insertRow(row, data1);
						model.removeRow(row + 1);
						
						
						JOptionPane.showMessageDialog(this, "Lưu thành công");
						reset();
					}
					
			}
		}else if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			if(row != -1) {
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
			}else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng để sửa !");
			}
		}else if (o.equals(btnThoat)) {
			dispose();
		}
	}

	public void xoa() throws Exception{
		// TODO Auto-generated method stub
		int r = table.getSelectedRow();
		if (r != -1) {
			int tb = JOptionPane.showConfirmDialog(null, "Bạn có chắc xóa hàng này không!", "delete",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (tb == JOptionPane.YES_OPTION) {
				String ma = txtMaHang.getText();
			dsHang.deleteHang(ma);
			model.removeRow(r);
			}
		} else
			JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng muốn xóa");
	}

	public void xoaRong() {
		// TODO Auto-generated method stub
		txtMaHang.setText("");
		txtTenHang.setText("");
		txtDonGia.setText("");
		cbLoaiHang.setSelectedItem(null);
		cbTenNCC.setSelectedItem(null);
		dateHH.setSelectableDateRange(null, null);
		dateSX.setSelectableDateRange(null, null);
		txtsoluong.setText("");
	}

	public boolean kTraLoi() {
		// TODO Auto-generated method stub
		String ma = txtMaHang.getText();
		String loai = (String) cbLoaiHang.getSelectedItem();
		String TenNCC = (String) cbLoaiHang.getSelectedItem();
		String ten = txtTenHang.getText();
		if (ma.equals("") || ten.equals("") || 
				txtDonGia.getText().equals("")  ||loai.equals("")||TenNCC.equals("")||txtsoluong.getText().equals("") ) {
			JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
			return false;
		}
		if(!ma.matches("H\\d{3}")) {
			JOptionPane.showMessageDialog(this, "Mã hàng lỗi! Theo dạng Hxxx với x từ [0-9]");
			txtMaHang.requestFocus();
			return false;
		}
		java.util.Date date = dateSX.getDate();
		java.sql.Date ngaysx = new java.sql.Date(date.getTime());
		
		java.util.Date date1 = dateHH.getDate();
		java.sql.Date ngayHH = new java.sql.Date(date1.getTime());
		if(date.compareTo(date1)>0)
		{
			JOptionPane.showMessageDialog(this, "Ngày hết hạn phải lớn hơn ngày sản xuất ");
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
			txtMaHang.setEditable(false);
		else
			txtMaHang.setEditable(true);
		txtTenHang.setEditable(true);
		cbLoaiHang.setEditable(true);
		cbTenNCC.setEditable(true);
		dateSX.setEnabled(true);
		dateHH.setEnabled(true);
		txtDonGia.setEditable(true);
		txtsoluong.setEditable(true);
		
		
		
	
	}

	public void khoaTxt() {
		// TODO Auto-generated method stub
		txtMaHang.setEditable(false);
		txtTenHang.setEditable(false);
		cbLoaiHang.setEditable(false);
		cbTenNCC.setEditable(false);
		dateSX.setEnabled(false);
		dateHH.setEnabled(false);
		txtDonGia.setEditable(false);
		txtsoluong.setEditable(false);
	}
}
