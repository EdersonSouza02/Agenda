package Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import Business.ContatoBO;
import DAO.ContatoDAO;
import VO.ContatoVO;

public class AgendaDeContatosController {
	
	public boolean salvarContato(ContatoVO contatoVO) throws SQLException {
		boolean retorno = true;
		ContatoBO contatoBO = new ContatoBO();
		retorno = contatoBO.manterContato(contatoVO);
		return retorno;
	}

	public String excluirPessoa(ContatoVO contatoVO) throws SQLException {
		
		ContatoDAO contatoDAO = new ContatoDAO();
		return contatoDAO.excluirPessoa(contatoVO);
	}
	
	

	public ContatoVO pesquisarContatoPorId(int id) throws SQLException {

		ContatoDAO contatoDAO = new ContatoDAO();
		ContatoVO contatoVO = contatoDAO.pesquisarContatoPorId(id);

		return contatoVO;
	}

	public List<ContatoVO> pesquisarContatoTodos(JTable table) throws SQLException {
		List<ContatoVO> retorno = new ArrayList<ContatoVO>();
		ContatoBO contatoBO = new ContatoBO();
		retorno = contatoBO.pesquisarContatoTodos(table);
		return retorno;
	}
	public ContatoVO pesquisarContatoPorNome(String nome, JTable table) throws SQLException {

		ContatoVO contatoVO = new ContatoVO();

		
		ContatoBO contatoBO = new ContatoBO();
		contatoVO = contatoBO.pesquisarContatoPorNome(nome,table);

		return contatoVO;
		
	}
	public ContatoVO pesquisarContatoPorTelefone (String telefone, JTable table) throws SQLException{
		ContatoVO contatoVO = new ContatoVO();

		
		ContatoBO contatoBO = new ContatoBO();
		contatoVO = contatoBO.pesquisarContatoPorTelefone(telefone,table);
		return contatoVO;
		
		
	}
	public boolean alterarContato(ContatoVO contatoVO) throws SQLException {
		boolean retorno = true;
		
		ContatoBO contatoBO = new ContatoBO();
		retorno = contatoBO.alterarContato(contatoVO);
		
		return retorno;
		
		
	}
	

}