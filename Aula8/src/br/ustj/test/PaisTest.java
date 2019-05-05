package br.ustj.test;

import br.ustj.model.Pais;
import br.ustj.service.PaisService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Vector;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
    Pais pais, copia;
    PaisService paisService;
    static int id = 0;

    /*
     * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
     * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
     * houver, delete.
     * Certifique-se também que sobrecarregou o equals na classe
     * Cliente.
     * Certifique-se que a fixture cliente1 foi criada no banco.
     * Além disso, a ordem de execução dos testes é importante; por
     * isso a anotação FixMethodOrder logo acima do nome desta classe
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("setup");

        pais = new Pais();
        pais.setId(id);
        pais.setNome("Iraque");
        pais.setPopulacao(1000);
        pais.setArea(1000);

        copia = new Pais();
        copia.setId(id);
        copia.setNome("Iraque");
        copia.setPopulacao(1000);
        copia.setArea(1000);

        paisService = new PaisService();
        System.out.println(pais);
        System.out.println(copia);
        System.out.println(id);
    }

    @Test
    public void test00Carregar() {
        System.out.println("carregar");

        Pais fixture = new Pais();
        fixture.setId(2);
        fixture.setNome("Argentina");
        fixture.setPopulacao(1);
        fixture.setArea(1.00);

        PaisService novoService = new PaisService();
        Pais carregar = novoService.carregar(2);
        assertEquals("testa carregar", carregar, fixture);
    }

    @Test
    public void test01Criar() {
        System.out.println("criar");
        id = paisService.criar(pais);
        System.out.println(id);
        copia.setId(id);
        assertEquals("testa criacao", pais, copia);
    }

    @Test
    public void test02Atualizar() {
        System.out.println("atualizar");

        pais.setPopulacao(99);
        pais.setArea(99);

        copia.setPopulacao(99);
        copia.setArea(99);

        paisService.atualizar(pais);
        pais = paisService.carregar(pais.getId());
        assertEquals("testa atualizacao", pais, copia);
    }

    @Test
    public void test03Excluir() {
        System.out.println("excluir");
        copia.setId(-1);
        copia.setNome(null);
        copia.setPopulacao(0);
        copia.setArea(0);

        paisService.excluir(id);
        pais = paisService.carregar(id);

        assertEquals("testa exclusao", pais, copia);
    }

    @Test
    public void test04ObterPaisComMaiorPopulacao(){
        System.out.println("obterPaisComMaiorPopulacao");

        Pais fixture = new Pais();
        fixture.setId(1);
        fixture.setNome("Brasil");
        fixture.setPopulacao(190000000);
        fixture.setArea(5000000.00);

        PaisService service = new PaisService();
        Pais obter = service.obterPaisComMaiorPopulacao();
        assertEquals("testa pais com maior população", fixture, obter);

    }

    @Test
    public void test05ObterPaisComMenorArea(){
        System.out.println("obterPaisComMenorArea");

        Pais fixture = new Pais(2, "Argentina", 1, 1.0);

        PaisService service = new PaisService();
        Pais obter = service.obterPaisComMenorArea();

        assertEquals("testa pais com menor área", fixture, obter);
    }

    @Test
    public void test06ObterPaisesMaisPopulosos(){
        System.out.println("obterPaisComMenorArea");

        Vector<Pais> paisesPopulosos = new Vector<Pais>();

        paisesPopulosos.add(new Pais(1, "Brasil", 190000000, 5000000.00));
        paisesPopulosos.add(new Pais(5, "Canadá", 4, 4));
        paisesPopulosos.add(new Pais(4, "Paraguai", 3, 3));

        PaisService service = new PaisService();
        Vector<Pais> paisesObter = service.obterPaisesMaisPopulosos();

        assertTrue(paisesPopulosos.equals(paisesObter));
    }

}
