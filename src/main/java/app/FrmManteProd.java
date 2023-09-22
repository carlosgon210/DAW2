package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame implements ActionListener {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCódigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JButton btnBuscar;
	private JLabel lblProveedor;
	private JComboBox cboProvedor;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(324, 71, 89, 23);
		contentPane.add(btnBuscar);
		
		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(225, 135, 102, 14);
		contentPane.add(lblProveedor);
		
		cboProvedor = new JComboBox();
		cboProvedor.setBounds(291, 131, 86, 22);
		contentPane.add(cboProvedor);
		
		llenaCombo();
	}

	void llenaCombo() {
		//obtener un listado de la tb Categoria
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		
		String jpql ="select c from Categoria c";
		List<Categoria> lstCategorias =em.createQuery(jpql ,Categoria.class).getResultList();
		cboCategorias.addItem("Seleccione");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());			
		}
		
		//provedor
		String jpql1 ="select pr from Proveedor pr";
		List<Proveedor> lstProveedors = em.createQuery(jpql1,Proveedor.class).getResultList();
		cboProvedor.addItem("Seleccione");
		for (Proveedor pr : lstProveedors) {
			cboProvedor.addItem(pr.getNombre_rs());
					
		}
	}
	
	void listado() {
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		
		String jpql ="select p from Producto p";
		List<Producto> lstProductos =em.createQuery(jpql ,Producto.class).getResultList();
		//imprimir el listado
		for (Producto p: lstProductos) {
			Imprimir("Codigo..:"+p.getId_prod());
			Imprimir("Nombre..:"+p.getDes_prod());
			Imprimir("Categoria: "+ p.getObjCategoria().getDescripcion());
			Imprimir("Proveedor: "+ p.getObjProverdor().getNombre_rs());
			Imprimir("--------------");
		
		}
	}
	private void Imprimir(String string) {
		txtSalida.append(string+"\n");
	}

	void registrar() {
		//leer los productos
		String id_prod=txtCódigo.getText();
		String des_prod= txtDescripcion.getText();
		int stk_prod =Integer.parseInt(txtStock.getText());
		double pre_prod= Double.parseDouble(txtPrecio.getText());
		int idcategoria=cboCategorias.getSelectedIndex();
		int est_prod=1;
		int idproveedor=cboProvedor.getSelectedIndex();//tarea
		
		
		Producto p =new Producto();
		p.setId_prod(id_prod);
		p.setDes_prod(des_prod);
		p.setStk_prod(stk_prod);
		p.setPre_prod(pre_prod);
		p.setIdcategoria(idcategoria);
		p.setEst_prod(est_prod);
		p.setIdproveedor(idproveedor);
		
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			aviso("Registro..");
		} catch (Exception e) {
			aviso("Error al registrar " + e.getMessage());
		}
		
	}

	private void aviso(String string) {
		JOptionPane.showMessageDialog(null, string);		
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		BuscarProducto();
	}

	void BuscarProducto() {
		//leer el codigo del producto a buscar
		String id_prod=txtCódigo.getText();
		//obtener un obj de tipo producto
		
		EntityManagerFactory fabrica =Persistence.createEntityManagerFactory("jpa_sesion02");
		EntityManager em = fabrica.createEntityManager();
		
		Producto p = em.find(Producto.class, id_prod);
		
		//mostrar si existe , si no mostrar mensaje de error
		if(p==null) {
			aviso("El codigo NO exixste");
		}else {
			txtDescripcion.setText(p.getDes_prod());
			txtStock.setText(p.getStk_prod()+"");
			txtPrecio.setText(p.getPre_prod()+"");
			cboCategorias.setSelectedIndex(p.getIdcategoria());
			cboProvedor.setSelectedIndex(p.getIdproveedor());
			
		}
		
	}
}