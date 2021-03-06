package Validation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.ContatoDAO;
import VO.ContatoVO;

public class ContatoValidation {

	public boolean validarContato(ContatoVO contatoVO) throws SQLException {

		boolean retorno = true;
		
			if (contatoVO.getId() <= 0) {
			JOptionPane.showMessageDialog(null,"Favor informar um ID maior que 0.");
			retorno = false;
		}
		
	
		if (contatoVO.getNome().trim().equals("")) {
			JOptionPane.showMessageDialog(null,"O nome do contato n�o pode ser nulo.");
			retorno = false;
		}
		
		
		if (contatoVO.getNome().equals("")) {
			JOptionPane.showMessageDialog(null,"Favor informar o nome do contato.");
			retorno = false;
		}
		
		if (contatoVO.getNome().trim().equals("")) {
			JOptionPane.showMessageDialog(null,"Favor informar o nome do contato.");
			retorno = false;
		}
		
		if (contatoVO.getDdd() == null || contatoVO.getDdd().trim().equals("") || contatoVO.getDdd().length() != 3) {
			JOptionPane.showMessageDialog(null,"Favor informar um DDD v�lido.");
			retorno = false;
		}
		
		
		if (contatoVO.getTelefone() == null || contatoVO.getTelefone().trim().equals("")
				|| (contatoVO.getTelefone().length() != 8 && contatoVO.getTelefone().length() != 9)) {
			JOptionPane.showMessageDialog(null,"Favor informar um telefone v�lido.");
			retorno = false;

	
	}
		return retorno;
	}
	

	public boolean alterarContato(ContatoVO contatoVO) throws SQLException {
		boolean retorno = true;

		
		if (contatoVO.getId() <= 0) {
			JOptionPane.showMessageDialog(null,"Favor informar um ID maior que 0.");
			retorno = false;
		}

		if (contatoVO.getNome().trim().equals("")) {
			JOptionPane.showMessageDialog(null,"O nome do contato n�o pode ser nulo.");
			retorno = false;
		}

		if (contatoVO.getNome().equals("")) {
			JOptionPane.showMessageDialog(null,"Favor informar o nome do contato.");
			retorno = false;
		}

		if (contatoVO.getNome().trim().equals("")) {
			JOptionPane.showMessageDialog(null,"Favor informar o nome do contato.");
			retorno = false;
		}

		if (contatoVO.getDdd() == null || contatoVO.getDdd().trim().equals("") || contatoVO.getDdd().length() != 3) {
			JOptionPane.showMessageDialog(null,"Favor informar um DDD v�lido.");
			retorno = false;
		}

		if (contatoVO.getTelefone() == null || contatoVO.getTelefone().trim().equals("")
				|| (contatoVO.getTelefone().length() != 8 && contatoVO.getTelefone().length() != 9)) {
			JOptionPane.showMessageDialog(null,"Favor informar um telefone v�lido.");
			retorno = false;

		}

		return retorno;
		
	}

}
