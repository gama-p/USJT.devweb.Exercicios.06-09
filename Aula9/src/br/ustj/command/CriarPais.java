package br.ustj.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ustj.model.Pais;
import br.ustj.service.PaisService;

public class CriarPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		long pPopulacao = (request.getParameter("populacao") != null ? Long.parseLong(request.getParameter("populacao"))
				: -1);
		double pArea = (request.getParameter("area") != null ? Double.parseDouble(request.getParameter("area")) : -1);

		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		// instanciar o javabean
		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulacao(pPopulacao);
		pais.setArea(pArea);

		// instanciar o service
		PaisService service = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		service.criar(pais);
		ArrayList<Pais> lista = new ArrayList<>();
		lista.add(pais);
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("ListarPaises.jsp");

		view.forward(request, response);

	}

}
