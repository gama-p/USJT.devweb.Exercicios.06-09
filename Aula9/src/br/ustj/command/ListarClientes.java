package br.ustj.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ustj.model.Cliente;

public class ListarClientes implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Cliente> lista = null;
		HttpSession session = request.getSession();

		session.setAttribute("lista", null);

		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarClientes.jsp");
		dispatcher.forward(request, response);
		
	}

}
