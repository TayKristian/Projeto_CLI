package swing.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import swing.control.ContatoController;
import swing.model.Contato;
import javax.swing.JList;

public class ContatoGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomeContato;
	private JTextField txtTelefoneContato;
	private JTextField txtIdContato;
	private boolean atualizou = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContatoGUI frame = new ContatoGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ContatoGUI() {
		ContatoController cc = new ContatoController();

		setTitle("Agenda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Contato");
		lblNewLabel.setBounds(198, -1, 118, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNomeDoContato = new JLabel("Nome do Contato");
		lblNomeDoContato.setBounds(34, 50, 141, 14);
		contentPane.add(lblNomeDoContato);

		txtNomeContato = new JTextField();
		txtNomeContato.setEditable(false);
		txtNomeContato.setBounds(198, 47, 221, 20);
		contentPane.add(txtNomeContato);
		txtNomeContato.setColumns(10);

		JLabel lblTelefoneDoContato = new JLabel("Telefone do Contato");
		lblTelefoneDoContato.setBounds(19, 75, 156, 14);
		contentPane.add(lblTelefoneDoContato);

		txtTelefoneContato = new JTextField();
		txtTelefoneContato.setEditable(false);
		txtTelefoneContato.setBounds(198, 72, 221, 20);
		contentPane.add(txtTelefoneContato);
		txtTelefoneContato.setColumns(10);

		JButton btnSalvarContato = new JButton("Salvar");
		btnSalvarContato.setEnabled(false);
		btnSalvarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = txtNomeContato.getText();
				String telefone = txtTelefoneContato.getText();
				boolean retorno = cc.insert(new Contato(nome, telefone));

				if (retorno) {
					JOptionPane.showMessageDialog(null, "Contato salvo");
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao salvar");
				}
				txtNomeContato.setEditable(false);
				txtTelefoneContato.setEditable(false);
				btnSalvarContato.setEnabled(false);
			}
		});
		btnSalvarContato.setBounds(98, 103, 105, 23);
		contentPane.add(btnSalvarContato);

		JButton btnCancelarContato = new JButton("Sair");
		btnCancelarContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Deseja sair?", "", JOptionPane.YES_NO_OPTION);

				if (op == JOptionPane.YES_OPTION) {
					System.exit(0);
				}

			}
		});
		btnCancelarContato.setBounds(213, 103, 103, 23);
		contentPane.add(btnCancelarContato);

		JButton btnLimparContato = new JButton("Limpar");
		btnLimparContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomeContato.setText("");
				txtTelefoneContato.setText("");
			}
		});
		btnLimparContato.setBounds(335, 103, 89, 23);
		contentPane.add(btnLimparContato);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(38, 32, 46, 14);
		contentPane.add(lblId);

		txtIdContato = new JTextField();
		txtIdContato.setEditable(false);
		txtIdContato.setBounds(198, 24, 86, 20);
		contentPane.add(txtIdContato);
		txtIdContato.setColumns(10);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomeContato.setEditable(true);
				txtTelefoneContato.setEditable(true);
				btnSalvarContato.setEnabled(true);

			}
		});
		btnNovo.setBounds(0, 103, 89, 23);
		contentPane.add(btnNovo);

		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resp = JOptionPane.showInputDialog(null, "Digite o ID que deseja procurar");
				long id = Long.parseLong(resp);

				Contato contato = cc.select(id);
				if (contato != null) {
					JOptionPane.showMessageDialog(null, contato);
				} else {
					JOptionPane.showMessageDialog(null, "Contato não encontrado");
				}

			}
		});
		btnProcurar.setBounds(55, 137, 89, 23);
		contentPane.add(btnProcurar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!atualizou) {
					String resp = JOptionPane.showInputDialog(null, "Digite o id que deseja atualizar");
					long id = Long.parseLong(resp);
					Contato contato = cc.select(id);
					txtIdContato.setText("" + contato.getId());
					txtNomeContato.setText("" + contato.getNome());
					txtTelefoneContato.setText("" + contato.getTelefone());
					txtNomeContato.setEditable(true);
					txtTelefoneContato.setEditable(true);
					atualizou = true;
					btnAtualizar.setText("Confirma");
				} else {
					long id = Long.parseLong(txtIdContato.getText());
					String nome = txtNomeContato.getText();
					String telefone = txtTelefoneContato.getText();
					boolean retorno = cc.update(new Contato(id, nome, telefone));

					if (retorno) {
						JOptionPane.showMessageDialog(null, "Contato atualizado");
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar");
					}
					atualizou = false;
				}

			}
		});
		btnAtualizar.setBounds(181, 137, 89, 23);
		contentPane.add(btnAtualizar);
		JList lstContatos = new JList();

		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Contato> lista = cc.select();
				lstContatos.setListData(lista.toArray());
			}
		});
		btnListar.setBounds(296, 137, 89, 23);
		contentPane.add(btnListar);

		lstContatos.setBounds(0, 188, 410, 211);
		contentPane.add(lstContatos);
	}
}
