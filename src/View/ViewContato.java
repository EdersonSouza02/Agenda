package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import Controller.AgendaDeContatosController;
import DAO.ContatoDAO;
import VO.ContatoVO;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.BevelBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewContato extends JFrame {

	private JPanel contentPane;
	public JTextField txttel;
	private JTextArea textArea;
	public JTextField txtddd;
	public JTextField txtnome;
	public JTextField txtid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewContato frame = new ViewContato();
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
	public ViewContato() {
		setResizable(false);
		setForeground(Color.LIGHT_GRAY);
		setTitle("Agenda Final Version");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Agenda Final Version");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel.setBounds(160, 11, 193, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(10, 31, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setBounds(10, 58, 46, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("DDD");
		lblNewLabel_3.setBounds(10, 92, 46, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Tel");
		lblNewLabel_4.setBounds(10, 145, 46, 14);
		contentPane.add(lblNewLabel_4);

		txttel = new JTextField();
		txttel.setColumns(10);
		txttel.setBounds(59, 137, 86, 27);
		contentPane.add(txttel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fun\u00E7\u00F5es",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(216, 35, 208, 145);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					try {
					
						salvarPessoa();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
					
			
		);
		btnCadastrar.setBounds(6, 16, 89, 23);
		panel.add(btnCadastrar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}

		});
		btnLimpar.setBounds(6, 48, 89, 23);
		panel.add(btnLimpar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					
					deletarPessoa();
				
				} catch (SQLException e) {
					textArea.setText(e.getMessage());
				}

				}
			
		});
		btnDeletar.setBounds(113, 16, 89, 23);
		panel.add(btnDeletar);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if (!txtid.getText().equals("")) {
					try {
						PesquisarPorId();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (!txtnome.getText().equals("")) {
					try {
						pesquisarPorNome();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}else if(!txttel.getText().equals("")){
						try{
						pesquisarPorTelefone();
						
					}catch(SQLException e){
						e.printStackTrace();
					}
				}
			}

			
			
			
		}
		);
		btnPesquisar.setBounds(113, 49, 89, 23);
		panel.add(btnPesquisar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(111, 82, 89, 23);
		panel.add(btnSair);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					listarTodos();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnListar.setBounds(6, 82, 89, 23);
		panel.add(btnListar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					alterarContato();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

			
		});
		btnAlterar.setBounds(6, 116, 89, 23);
		panel.add(btnAlterar);

		txtddd = new JTextField();
		txtddd.setColumns(10);
		txtddd.setBounds(59, 90, 86, 27);
		contentPane.add(txtddd);

		txtnome = new JTextField();
		txtnome.setColumns(10);
		txtnome.setBounds(59, 53, 86, 27);
		contentPane.add(txtnome);

		txtid = new JTextField();
		txtid.setColumns(10);
		txtid.setBounds(60, 23, 86, 27);
		contentPane.add(txtid);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 192, 420, 134);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane tabela = new JScrollPane();
		tabela.setEnabled(false);
		tabela.setBounds(0, 0, 420, 134);
		panel_1.add(tabela);
		
		table = new JTable();
		tabela.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "DDD", "Telefone"
			}
		));
	}

	

	public void salvarPessoa() throws SQLException {
		validarId();
		ContatoVO contatoVO = new ContatoVO();
		int id = 0;
		
		Pattern ValidarNumeros = Pattern.compile("[0-9]");
		if(ValidarNumeros.matcher(txtid.getText()).find()) {
		id = Integer.parseInt(txtid.getText());
		}
		contatoVO.setId(id);
		contatoVO.setNome(txtnome.getText());
		contatoVO.setDdd(txtddd.getText());
		contatoVO.setTelefone(txttel.getText());
		
		AgendaDeContatosController contatoController = new AgendaDeContatosController();
		contatoController.salvarContato(contatoVO);
		
		AgendaDeContatosController agendaDeContatosController = new AgendaDeContatosController();
		boolean salvou = agendaDeContatosController.salvarContato(contatoVO);	
	}

	public void limpar() {
		this.txtid.setText("");
		this.txtnome.setText("");
		this.txtddd.setText("");
		this.txttel.setText("");
		((DefaultTableModel) table.getModel()).setRowCount(0);
		this.txtid.requestFocus();
		

	}

	public void deletarPessoa() throws SQLException {
		validarId();
		ContatoVO contatoVO = new ContatoVO();
		ContatoDAO contatoDAO = new ContatoDAO();
		int id = 0;
		Pattern ValidarNumeros = Pattern.compile("[0-9]");
		
		if(ValidarNumeros.matcher(txtid.getText()).find()) {
		id = Integer.parseInt(txtid.getText());
		}
		contatoVO.setId(id);
		contatoVO.setNome(txtnome.getText());
		contatoVO.setDdd(txtddd.getText());
		contatoVO.setTelefone(txttel.getText());
		String cont = ContatoDAO.excluirPessoa(contatoVO);
		JOptionPane.showMessageDialog(null, cont);

		limpar();
		this.txtid.requestFocus();

	}
	

	public void validarId() throws IllegalArgumentException {
		if (txtid.getText().equals(null)) {
			JOptionPane.showMessageDialog(null, "Favor informar o id da pessoa");

		}
	}

	
	private void listarTodos() throws SQLException {
		limpar();
		AgendaDeContatosController Agendacontroller = new AgendaDeContatosController();
		
		Agendacontroller.pesquisarContatoTodos(this.table);
		
	}

	public void PesquisarPorId() throws SQLException {
		AgendaDeContatosController AgendaController = new AgendaDeContatosController();
		int idtxt = Integer.parseInt(txtid.getText());
		ContatoVO vo = AgendaController.pesquisarContatoPorId(idtxt);
		
		String idtext = Integer.toString(vo.getId());
		
		txtid.setText(idtext);
		txtnome.setText(vo.getNome());
		txtddd.setText(vo.getDdd());
		txttel.setText(vo.getTelefone());
		
		
	}
	public void pesquisarPorNome() throws SQLException {

		AgendaDeContatosController Agendacontroller = new AgendaDeContatosController();
		ContatoVO contatoVO = new ContatoVO();

		String nometxt = txtnome.getText();

		Agendacontroller.pesquisarContatoPorNome(nometxt, table);

	}
	public  void pesquisarPorTelefone() throws SQLException {
		AgendaDeContatosController Agendacontroller = new AgendaDeContatosController();
		ContatoVO contatoVO = new ContatoVO();

		String telefone = txttel.getText();

		Agendacontroller.pesquisarContatoPorTelefone(telefone, table);
		
		
	}
	public void alterarContato() throws SQLException {
		ContatoVO contatoVO = new ContatoVO();
		int id = 0;
		Pattern ValidarNumeros = Pattern.compile("[0-9]");
		
		if(ValidarNumeros.matcher(txtid.getText()).find()) {
		id = Integer.parseInt(txtid.getText());
		}
		contatoVO.setId(id);
		contatoVO.setNome(txtnome.getText());
		contatoVO.setDdd(txtddd.getText());
		contatoVO.setTelefone(txttel.getText());
		AgendaDeContatosController agendaDeContatosController = new AgendaDeContatosController();
		agendaDeContatosController.alterarContato(contatoVO);
		
	}
	
	}
	


