package br.ustj.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ustj.model.Cliente;
import br.ustj.service.ClienteService;

public class ListarClientesBuscar implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String chave = request.getParameter("data[search]");

		ClienteService service = new ClienteService();
		ArrayList<Cliente> lista = null;
		HttpSession session = request.getSession();
		
		if (chave != null && chave.length() > 0) {
			lista = service.obterTodos(chave);
		} else {
			lista = service.obterTodos();
		}
		session.setAttribute("lista", lista);		

		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarClientes.jsp");
		dispatcher.forward(request, response);		

	}	

}
