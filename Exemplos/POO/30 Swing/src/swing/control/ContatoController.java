package swing.control;

import java.util.ArrayList;

import swing.model.Contato;
import swing.model.ContatoDAO;

public class ContatoController {

	ContatoDAO dao = new ContatoDAO();

	public boolean insert(Contato contato) {
		try {
			dao.insert(contato);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public Contato select(long id) {
		return dao.select(id);
	}

	public ArrayList<Contato> select() {

		return (ArrayList<Contato>) dao.select();
	}

	public boolean update(Contato contato) {
		try {
			dao.update(contato);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
