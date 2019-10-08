package swing.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements GenericDAO<Contato> {

	public Connection getConnection() throws SQLException {
		// url do banco
		String url = "jdbc:h2:tcp://localhost/~/bd_agenda";

		// usuário do banco
		// String user = "";
		// Senha do banco
		// String password = "";

		// Class.forName("org.h2.Driver");
		return DriverManager.getConnection(url);
	}

	@Override
	public void insert(Contato e) {
		// TODO Auto-generated method stub

		try (Connection con = getConnection()) {
			String sql = "insert into contato (nome, telefone) values (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTelefone());
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Contato select(long id) {

		String sql = "select * from contato where id = ?";

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Contato c = new Contato();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				return c;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Contato> select() {
		ArrayList<Contato> lista = new ArrayList<Contato>();

		String sql = "select * from contato";

		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Contato c = new Contato();
				c.setId(rs.getLong("id"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				lista.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public void update(Contato e) {
		String sql = "update contato " + "set nome = ?, telefone = ? " + "where id = ?";
		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTelefone());
			ps.setLong(3, e.getId());
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	@Override
	public void delete(Contato e) {
		String sql = "delete from contato where id = ?";
		try (Connection con = getConnection()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, e.getId());
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
