package br.ustj.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ustj.model.Cliente;
import br.ustj.service.ClienteService;

public class VisualizarCliente implements Command {

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

		cliente = service.carregar(cliente.getId());
		request.setAttribute("cliente", cliente);
		view = request.getRequestDispatcher("VisualizarCliente.jsp");

		view.forward(request, response);


	}

}
