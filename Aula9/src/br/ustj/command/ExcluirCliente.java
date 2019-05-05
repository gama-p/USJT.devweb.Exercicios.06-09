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

public class ExcluirCliente implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pId = request.getParameter("id");

		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		// instanciar o javabean
		Cliente cliente = new Cliente();
		cliente.setId(id);

		// instanciar o service
		ClienteService service = new ClienteService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		service.excluir(cliente.getId());
		@SuppressWarnings("unchecked")
		ArrayList<Cliente> lista = (ArrayList<Cliente>) session.getAttribute("lista");
		lista.remove(busca(cliente, lista));
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("ListarClientes.jsp");

		view.forward(request, response);

	}
	
	public int busca(Cliente cliente, ArrayList<Cliente> lista) {
		Cliente to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == cliente.getId()) {
				return i;
			}
		}
		return -1;
	}

}
