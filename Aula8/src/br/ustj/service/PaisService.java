package br.ustj.service;

import br.ustj.dao.PaisDAO;
import br.ustj.model.Pais;

import java.util.ArrayList;
import java.util.Vector;

public class PaisService {
    PaisDAO dao = new PaisDAO();

    public int criar(Pais pais) {
        return dao.criar(pais);
    }

    public void atualizar(Pais pais){
        dao.atualizar(pais);
    }

    public void excluir(int id){
        dao.excluir(id);
    }

    public Pais carregar(int id){
        return dao.carregar(id);
    }

    public Pais obterPaisComMaiorPopulacao(){
        return dao.obterPaisComMaiorPopulacao();
    }

    public Pais obterPaisComMenorArea() {
        return dao.obterPaisComMenorArea();
    }

    public Vector<Pais> obterPaisesMaisPopulosos() {
        return dao.obterPaisesMaisPopulosos();
    }

	public ArrayList<Pais> obterTodos() {
		return dao.obterTodos();
	}
	
	public ArrayList<Pais> obterTodos(String chave) {
		return dao.obterTodos(chave);
	}
}
