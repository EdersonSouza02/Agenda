package Business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import DAO.ContatoDAO;
import Validation.ContatoValidation;
import VO.ContatoVO;

public class ContatoBO {

	public boolean manterContato(ContatoVO contatoVO) throws SQLException {

		boolean retorno = true;
		/* vamos validar os dados do contato */
		
		ContatoValidation contatoValidator = new ContatoValidation();
		retorno = contatoValidator.validarContato(contatoVO);

		/* vamos cadastrar o contato */
		ContatoDAO contatoDAO = new ContatoDAO();
		retorno = contatoDAO.InserirPessoa(contatoVO);

		return retorno;
	}

	public ContatoVO pesquisarContatoPorId(int id) throws SQLException {

		ContatoDAO contatoDAO = new ContatoDAO();
		ContatoVO contatoVO = contatoDAO.pesquisarContatoPorId(id);
		return contatoVO;
	}

	public List<ContatoVO> pesquisarContatoTodos(JTable tabelaSaidaDeDados) throws SQLException {
		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		ContatoDAO contatoDAO = new ContatoDAO();
		retorno = contatoDAO.pesquisarTodasPessoas(tabelaSaidaDeDados);
		return retorno;
	}
	public ContatoVO pesquisarContatoPorNome(String nome, JTable table) throws SQLException {
		ContatoVO contatoVO = new ContatoVO();
		
		ContatoDAO contatoDAO = new ContatoDAO();
		contatoVO = contatoDAO.pesquisarContatoPorNome(nome, table);
		return contatoVO;
	}
	
	public ContatoVO pesquisarContatoPorTelefone(String telefone, JTable table) throws SQLException{
		ContatoVO contatoVO = new ContatoVO();
		ContatoDAO contatoDAO = new ContatoDAO();
		contatoVO = contatoDAO.pesquisarPorTelefone(telefone, table);
		
		return contatoVO;
	}

	public boolean alterarContato(ContatoVO contatoVO) throws SQLException {
	boolean retorno = true;
	ContatoValidation contatoValidation = new ContatoValidation();
	retorno = contatoValidation.alterarContato(contatoVO);
	if(retorno) {
		ContatoDAO contatoDAO = new ContatoDAO();
		retorno = (boolean) ContatoDAO.alterarContato(contatoVO);
	}
		return false;
	}

}