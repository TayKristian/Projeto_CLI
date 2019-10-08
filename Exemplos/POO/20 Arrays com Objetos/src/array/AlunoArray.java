package array;

public class AlunoArray {

	private Aluno[] alunos;
	private int quant;

	public AlunoArray() {
		alunos = new Aluno[10];
		quant = 0;
	}

	public AlunoArray(int tamanho) {
		alunos = new Aluno[tamanho];
		quant = 0;
	}

	// Inserir aluno no vetor
	public boolean inserir(Aluno aluno) {
		if (!estaCheio()) {
			alunos[quant] = aluno;
			quant++;
			return true;
		}
		return false;
	}

	private boolean estaCheio() {
		return quant == alunos.length;
		// return alunos[alunos.length - 1] != null;
	}

	private int quantAlunos() {
		return quant;
	}

	// Remover um aluno no vetor
	public boolean remover(Aluno aluno) {
		int pos = -1;
		for (int i = 0; i < quant; i++) {
			if (aluno.equals(alunos[i])) {
				pos = i;
				break;
			}
		}

		if (pos != -1) {
			for (int i = pos; i < quant - 1; i++) {
				alunos[i] = alunos[i + 1];
			}
			alunos[quant - 1] = null;
			quant--;
			return true;
		}
		return false;
		
	}

	// Pesquisar aluno no vetor pela matrícula
	public Aluno pesquisar(long matricula) {
		for (int i = 0; i < quant; i++) {
			if(matricula == alunos[i].getMatricula()) {
				return alunos[i];
			}
		}
		return null;		
	}
	
	// Informar quantos alunos estão matriculados
	public int quantidade() {
		return quant;
	}
	
	// Listar todos os alunos no vetor
	public Aluno[] listarTodos() {
		Aluno novo[] = new Aluno[quant];
		
		for (int i = 0; i < quant; i++) {
			novo[i] = alunos[i];
		}
		return novo;
	}
}
