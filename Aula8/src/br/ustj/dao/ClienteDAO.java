package br.ustj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ustj.model.Cliente;

public class ClienteDAO {

	public int criar(Cliente cliente) {
		String sqlInsert = "INSERT INTO cliente(nome, fone, email) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getFone());
			stm.setString(3, cliente.getEmail());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				System.out.print("ClienteDAO.obterTodos criar!");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.print("ClienteDAO.obterTodos criar!");
			e.printStackTrace();
		}
		return cliente.getId();
	}

	public void atualizar(Cliente cliente) {
		String sqlUpdate = "UPDATE cliente SET nome=?, fone=?, email=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getFone());
			stm.setString(3, cliente.getEmail());
			stm.setInt(4, cliente.getId());
			stm.execute();
		} catch (Exception e) {
			System.out.print("ClienteDAO.obterTodos atualizar!");
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			System.out.print("ClienteDAO.obterTodos excluir!");
			e.printStackTrace();
		}
	}

	public Cliente carregar(int id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		String sqlSelect = "SELECT nome, fone, email FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect)) {
			stm.setInt(1, cliente.getId());
			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					cliente.setNome(rs.getString("nome"));
					cliente.setFone(rs.getString("fone"));
					cliente.setEmail(rs.getString("email"));
				} else {
					cliente.setId(-1);
					cliente.setNome(null);
					cliente.setFone(null);
					cliente.setEmail(null);
				}
			} catch (SQLException e) {
				System.out.print("ClienteDAO.obterTodos carregar!");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print("ClienteDAO.obterTodos carregar!");
			System.out.print(e1.getStackTrace());
		}
		return cliente;
	}

	public ArrayList<Cliente> obterTodos() {
		Cliente cliente;
		ArrayList<Cliente> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, fone, email FROM cliente order by nome";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {			
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					cliente = new Cliente();
					cliente.setId(rs.getInt("id"));
					cliente.setNome(rs.getString("nome"));
					cliente.setFone(rs.getString("fone"));
					cliente.setEmail(rs.getString("email"));
					lista.add(cliente);
				}
			} catch (SQLException e) {
				System.out.print("ClienteDAO.obterTodos error!");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print("ClienteDAO.obterTodos error!");
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public ArrayList<Cliente> obterTodos(String chave) {
		Cliente cliente;
		ArrayList<Cliente> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, fone, email FROM cliente where upper(nome) like ? order by nome";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					cliente = new Cliente();
					cliente.setId(rs.getInt("id"));
					cliente.setNome(rs.getString("nome"));
					cliente.setFone(rs.getString("fone"));
					cliente.setEmail(rs.getString("email"));
					lista.add(cliente);
				}
			} catch (SQLException e) {
				System.out.print("ClienteDAO.obterTodos error!");
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print("ClienteDAO.obterTodos error!");
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}


}
