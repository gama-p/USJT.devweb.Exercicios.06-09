package br.ustj.dao;

import br.ustj.model.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class PaisDAO {

    public int criar(Pais pais) {
        String sqlInsert = "INSERT INTO Paises(nome, populacao, area) VALUES (?, ?, ?)";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
            stm.setString(1, pais.getNome());
            stm.setLong(2, pais.getPopulacao());
            stm.setDouble(3, pais.getArea());
            stm.execute();
            String sqlQuery = "SELECT LAST_INSERT_ID()";
            try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
                 ResultSet rs = stm2.executeQuery();) {
                if (rs.next()) {
                    pais.setId(rs.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais.getId();
    }

    public void atualizar(Pais pais) {
        String sqlUpdate = "UPDATE Paises SET nome=?, populacao=?, area=? WHERE id=?";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
            stm.setString(1, pais.getNome());
            stm.setLong(2, pais.getPopulacao());
            stm.setDouble(3, pais.getArea());
            stm.setInt(4, pais.getId());
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sqlDelete = "DELETE FROM Paises WHERE id = ?";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
            stm.setInt(1, id);
            stm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Pais carregar(int id) {
        Pais pais = new Pais();
        pais.setId(id);
        String sqlSelect = "SELECT id, nome, area, populacao FROM Paises WHERE Paises.id = ?";
        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
            stm.setInt(1, pais.getId());
            preencherObjeto(pais, stm);
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return pais;
    }

    // e. Crie um método que retorna o país com maior número de habitantes.
    public Pais obterPaisComMaiorPopulacao(){
        Pais pais = new Pais();
        String sqlSelect = "SELECT id, nome, area, populacao FROM Paises ORDER BY Paises.populacao DESC LIMIT 1;";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
            preencherObjeto(pais, stm);
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return pais;
    }

    private void preencherObjeto(Pais pais, PreparedStatement stm) {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                pais.setId(rs.getInt("id"));
                pais.setArea(rs.getDouble("area"));
                pais.setNome(rs.getString("nome"));
                pais.setPopulacao(rs.getLong("populacao"));
            } else {
                pais.setId(-1);
                pais.setArea(-1);
                pais.setNome(null);
                pais.setPopulacao(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pais obterPaisComMenorArea() {
        Pais pais = new Pais();
        String sqlSelect = "SELECT id, nome, area, populacao FROM Paises ORDER BY Paises.area ASC LIMIT 1;";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
            preencherObjeto(pais, stm);
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return pais;
    }

    public Vector<Pais> obterPaisesMaisPopulosos() {
        Vector<Pais> paises = new Vector<Pais>();
        String sqlSelect = "select id, nome, area, populacao from Paises order by Paises.populacao DESC limit 3;";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Pais pais = new Pais();
                    pais.setId(rs.getInt("id"));
                    pais.setArea(rs.getDouble("area"));
                    pais.setNome(rs.getString("nome"));
                    pais.setPopulacao(rs.getLong("populacao"));

                    paises.add(pais);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return paises;
    }

	public ArrayList<Pais> obterTodos() {
		ArrayList<Pais> paises = new ArrayList<Pais>();
        String sqlSelect = "select id, nome, area, populacao from Paises order by nome;";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Pais pais = new Pais();
                    pais.setId(rs.getInt("id"));
                    pais.setArea(rs.getDouble("area"));
                    pais.setNome(rs.getString("nome"));
                    pais.setPopulacao(rs.getLong("populacao"));

                    paises.add(pais);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return paises;
	}

	public ArrayList<Pais> obterTodos(String chave) {
		ArrayList<Pais> paises = new ArrayList<Pais>();
        String sqlSelect = "select id, nome, area, populacao from Paises where upper(nome) like ? order by nome;";

        // usando o try with resources do Java 7, que fecha o que abriu
        try (Connection conn = ConnectionFactory.obtemConexao();
             PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
        	stm.setString(1, "%" + chave.toUpperCase() + "%");
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Pais pais = new Pais();
                    pais.setId(rs.getInt("id"));
                    pais.setArea(rs.getDouble("area"));
                    pais.setNome(rs.getString("nome"));
                    pais.setPopulacao(rs.getLong("populacao"));

                    paises.add(pais);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            System.out.print(e1.getStackTrace());
        }
        return paises;
	}
}
