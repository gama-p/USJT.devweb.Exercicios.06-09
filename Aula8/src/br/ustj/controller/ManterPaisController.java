package br.ustj.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.buf.C2BConverter;

import br.ustj.model.Cliente;
import br.ustj.model.Pais;
import br.ustj.model.Pais;
import br.ustj.service.PaisService;
import br.ustj.service.PaisService;

@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pAcao = request.getParameter("acao");

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

		if (pAcao.equals("Criar")) {
			service.criar(pais);
			ArrayList<Pais> lista = new ArrayList<>();
			lista.add(pais);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");
		} else if (pAcao.equals("Excluir")) {
			service.excluir(pais.getId());
			ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
			lista.remove(busca(pais, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");
		} else if (pAcao.equals("Alterar")) {
			service.atualizar(pais);
			ArrayList<Pais> lista = (ArrayList<Pais>) session.getAttribute("lista");
			int pos = busca(pais, lista);
			lista.remove(pos);
			lista.add(pos, pais);
			session.setAttribute("lista", lista);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
		} else if (pAcao.equals("Visualizar")) {
			pais = service.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
		} else if (pAcao.equals("Editar")) {
			pais = service.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("AlterarPais.jsp");
		}

		view.forward(request, response);

	}

	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == pais.getId()) {
				return i;
			}
		}
		return -1;
	}

}
