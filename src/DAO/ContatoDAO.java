package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.DAOFactory;
import VO.ContatoVO;
import View.ViewContato;

public class ContatoDAO {
	private static Connection connection;

	public ContatoDAO() throws SQLException {
		this.connection = DAOFactory.getConnection();
	}

	public static boolean InserirPessoa(ContatoVO contatoVO) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(buscaComandoInsert());
		int parameterIndex = 1;
		preparedStatement.setInt(parameterIndex++, contatoVO.getId());
		preparedStatement.setString(parameterIndex++, contatoVO.getNome());
		preparedStatement.setString(parameterIndex++, contatoVO.getDdd());
		preparedStatement.setString(parameterIndex++, contatoVO.getTelefone());
		int cont = preparedStatement.executeUpdate();
		if (cont == 1) {
			JOptionPane.showMessageDialog(null, "Contato Cadastrado com sucesso");
			return true;
		}
		return false;
	}

	public static String excluirPessoa(ContatoVO contatoVO) throws SQLException {
		String retorno = "Contato não deletado";

		PreparedStatement preparedStatement = connection.prepareStatement(buscaComandoDeletar());
		preparedStatement.setInt(1, contatoVO.getId());
		int cont = preparedStatement.executeUpdate();
		if (cont == 1) {
			retorno = "Contato Deletado";
		}
		return retorno;
	}

	public List<ContatoVO> pesquisarTodasPessoas(JTable table) throws SQLException {
		List<ContatoVO> contatos = new ArrayList();
		PreparedStatement preparedStatement = this.connection.prepareStatement(buscaComandoPesquisarPessoas());
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			ViewContato vc = new ViewContato();

			DefaultTableModel dados = (DefaultTableModel) table.getModel();
			ContatoVO contatoVO = new ContatoVO();
			contatoVO.setId(resultSet.getInt("id"));
			contatoVO.setNome(resultSet.getString("Nome"));
			contatoVO.setDdd(resultSet.getString("ddd"));
			contatoVO.setTelefone(resultSet.getString("telefone"));
			contatos.add(contatoVO);

			String id = Integer.toString(contatoVO.getId());

			dados.addRow(new String[] { id, contatoVO.getNome(), contatoVO.getDdd(), contatoVO.getTelefone() });
			vc.txtid.setText("");
			vc.txtnome.setText("");
			vc.txtddd.setText("");
			vc.txttel.setText("");
			vc.txtid.requestFocus();

		}

		return contatos;
	}

	public ContatoVO pesquisarContatoPorId(int id) throws SQLException {
		ContatoVO contatoVO = new ContatoVO();
		ViewContato contato = new ViewContato();
		PreparedStatement preparedStatement = connection.prepareStatement(buscaComandoPesquisarPorId());
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			contatoVO.setId(resultSet.getInt("id"));
			contatoVO.setNome(resultSet.getString("nome"));
			contatoVO.setDdd(resultSet.getString("ddd"));
			contatoVO.setTelefone(resultSet.getString("telefone"));

			String vc = Integer.toString(contatoVO.getId());

			contato.txtid.setText(vc);
			contato.txtnome.setText(contatoVO.getNome());
			contato.txttel.setText(contatoVO.getTelefone());
			contato.txtddd.setText(contatoVO.getDdd());

		}
		return contatoVO;
	}

	public ContatoVO pesquisarContatoPorNome(String nome, JTable table) throws SQLException {
		ContatoVO contatoVO = new ContatoVO();
		PreparedStatement preparedStatement = connection.prepareStatement(buscaComandoPesquisarPorNome());
		preparedStatement.setString(1, nome);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			contatoVO = new ContatoVO();
			contatoVO.setId(resultSet.getInt("id"));
			contatoVO.setNome(resultSet.getString("nome"));
			contatoVO.setDdd(resultSet.getString("ddd"));
			contatoVO.setTelefone(resultSet.getString("telefone"));

			ViewContato vc = new ViewContato();
			DefaultTableModel dados = (DefaultTableModel) table.getModel();

			String txtid = Integer.toString(contatoVO.getId());

			dados.addRow(new String[] { txtid, contatoVO.getNome(), contatoVO.getDdd(), contatoVO.getTelefone() });
			vc.txtid.setText("");
			vc.txtnome.setText("");
			vc.txtddd.setText("");
			vc.txttel.setText("");
			vc.txtid.requestFocus();

			return contatoVO;

		}
		return contatoVO;
	}

	public ContatoVO pesquisarPorTelefone(String telefone, JTable table) throws SQLException {
		ContatoVO contatoVO = new ContatoVO();
		PreparedStatement preparedStatement = connection.prepareStatement(buscaComandoPesquisarPorTelefone());
		preparedStatement.setString(1, telefone);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			contatoVO = new ContatoVO();
			contatoVO.setId(resultSet.getInt("id"));
			contatoVO.setNome(resultSet.getString("nome"));
			contatoVO.setDdd(resultSet.getString("ddd"));
			contatoVO.setTelefone(resultSet.getString("telefone"));

			ViewContato vc = new ViewContato();
			DefaultTableModel dados = (DefaultTableModel) table.getModel();

			String txtid = Integer.toString(contatoVO.getId());

			dados.addRow(new String[] { txtid, contatoVO.getNome(), contatoVO.getDdd(), contatoVO.getTelefone() });
			vc.txtid.setText("");
			vc.txtnome.setText("");
			vc.txtddd.setText("");
			vc.txttel.setText("");
			vc.txtid.requestFocus();

			return contatoVO;
		}
		return contatoVO;
	}

	private static String buscaComandoInsert() {
		return "insert into contato (id,nome,ddd,telefone) values (?,?,?,?)";

	}

	private String buscaComandoPesquisarPessoas() {
		return "select id,nome,ddd,telefone from contato";

	}

	private static String buscaComandoDeletar() {
		return "delete from contato where contato.id=?";

	}

	private String buscaComandoPesquisarPorId() {
		return "select id,nome,ddd,telefone from contato where id=?";
	}

	private String buscaComandoPesquisarPorNome() {
		return "select id,nome,ddd,telefone from contato where nome=?";
	}

	private String buscaComandoPesquisarPorTelefone() {
		return "select id,nome,ddd,telefone from contato where telefone=?";
	}

	private String buscaTodos() {
		return "select id,nome,ddd,telefone from contato where telefone=?";
	}

	public List<ContatoVO> validarDados() throws SQLException {
		ContatoVO contatoVO = new ContatoVO();

		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		PreparedStatement preparedStatement = connection.prepareStatement(buscaTodos());
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			contatoVO = new ContatoVO();
			contatoVO.setId(resultSet.getInt("id"));
			contatoVO.setNome(resultSet.getString("nome"));
			contatoVO.setDdd(resultSet.getString("ddd"));
			contatoVO.setTelefone(resultSet.getString("telefone"));
			retorno.add(contatoVO);
		}
		return retorno;
	}

	public static boolean alterarContato(ContatoVO contatoVO) throws SQLException {
		excluirPessoa(contatoVO);
		InserirPessoa(contatoVO);

		return true;
	}
}
