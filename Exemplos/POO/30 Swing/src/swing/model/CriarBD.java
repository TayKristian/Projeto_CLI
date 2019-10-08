package swing.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CriarBD {

	public static void main(String[] args) {

		
		String sql = "create table if not exists contato("
				+ "id int primary key auto_increment,"
				+ "nome varchar(100),"
				+ "telefone varchar(20))";
		
		String url = "jdbc:h2:tcp://localhost/~/bd_agenda";
		
		try {
			Connection con = DriverManager.getConnection(url);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();		
			System.out.println("Tabela criada com sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
