package array;

import java.util.Scanner;

public class AlunoArrayTeste {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		AlunoArray aa = new AlunoArray(5);
		Aluno aluno;
		
		for(;;) {
			System.out.println("Menu");
			System.out.println("1 - Matricular um aluno");
			System.out.println("2 - Remover um aluno");
			System.out.println("3 - Pesquisar por matricula um aluno");
			System.out.println("4 - Listar todos os alunos");
			System.out.println("5 - Quantidade de alunos matriculados");
			System.out.println("0 - Sair");
			System.out.print("Digite uma opção: ");
			int op = s.nextInt();
			
			switch (op) {
			case 1:
				System.out.println("Matricula: ");
				long matricula = s.nextLong();
				
				System.out.println("Nome: ");
				String nome = s.next();
				
				System.out.println("Curso: ");
				String curso = s.next();
				
				aluno = new Aluno(matricula, nome, curso);
				
				if(aa.inserir(aluno)) {
					System.out.println("Aluno matriculado com sucesso");
				}else {
					System.out.println("Turma lotada");
				}
				
				break;
			case 2:
				System.out.println("Digite a matricula do aluno a ser removido");
				long matriculaRem = s.nextLong();
				
				aluno = new Aluno();
				aluno.setMatricula(matriculaRem);
				
				if (aa.remover(aluno)) {
					System.out.println("Aluno removido");
				}else {
					System.out.println("Não foi possível remover o aluno");
				}
				break;
			case 3:
				System.out.println("Digite a matricula");
				long matPesq = s.nextLong();
				aluno = aa.pesquisar(matPesq);
				if(aluno != null) {
					System.out.println(aluno);
				}else {
					System.out.println("Aluno não encontrado");
				}
				
				break;
			case 4:
				Aluno alunos[] = aa.listarTodos();
				
				if (alunos.length > 0) {
					for (Aluno a : alunos) {
						System.out.println(a);
					}
				}else {
					System.out.println("Não tem alunos matriculados");
				}
				break;
			case 5:
				int quant = aa.quantidade();
				System.out.printf("Temos %d alunos matriculados\n", quant);
				
				break;
			case 0:
				System.out.println("Programa finalizado");
				System.exit(0);
				break;

			default:
				System.out.println("Opção inválida");
				break;
			}
		}
		
	}

}
