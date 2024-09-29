public class Main {

	// Método para detectar se é somente composto por vogais
	static boolean x1(String palavra) {
		return x1(palavra, 0);
	}

	static boolean x1(String palavra, int n) {

		// Definir dados
		boolean all_vowel = true;
		int tamanho = palavra.length();
		char char_atual;

		if (n < tamanho) {

			char_atual = palavra.charAt(n);

			// Detectar se não é vogal
			if ((char_atual != 'a') && (char_atual != 'e') && (char_atual != 'i') &&
					(char_atual != 'o') && (char_atual != 'u') && (char_atual != 'A') &&
					(char_atual != 'E') && (char_atual != 'I') && (char_atual != 'O') && (char_atual != 'U')) {

				all_vowel = false;

			} else {
				// Chamar o método para a próxima letra
				all_vowel = x1(palavra, n + 1);
			}
		}

		return all_vowel;

	}

	// Método para detectar se é composto somente por consoantes
	static boolean x2(String palavra) {
		return x2(palavra, 0);
	}

	static boolean x2(String palavra, int n) {

		// Definir dados
		boolean all_con = true;
		int tamanho = palavra.length();
		char char_atual;

		if (n < tamanho) {

			char_atual = palavra.charAt(n);

			// Detectar se não é letra ou se é vogal
			if ((char_atual < 'A') || (char_atual > 'Z' && char_atual < 'a') || (char_atual > 'z')) {
				all_con = false;
			} else if (char_atual == 'a' || char_atual == 'e' || char_atual == 'i' ||
					char_atual == 'o' || char_atual == 'u' || char_atual == 'A' ||
					char_atual == 'E' || char_atual == 'I' || char_atual == 'O' || char_atual == 'U') {
				all_con = false;
			} else {
				// Chamar o método para a próxima letra
				all_con = x2(palavra, n + 1);
			}

		}

		return all_con;

	}

	// Método para detectar se é um número inteiro
	static boolean x3(String palavra) {
		return x3(palavra, 0);
	}

	static boolean x3(String palavra, int n) {

		// Definir dados
		boolean all_num = true;
		int tamanho = palavra.length();
		char char_atual;

		if (n < tamanho) {

			char_atual = palavra.charAt(n);

			// Detectar se não é um número
			if (!((char_atual >= '1' && char_atual <= '9') || char_atual == '0')) {

				all_num = false;

			} else {
				all_num = x3(palavra, n + 1);
			}

		}

		return all_num;

	}

	// Método para detectar se é um número real
	static boolean x4(String palavra) {
		return x4(palavra, 0, 0);
	}

	static boolean x4(String palavra, int virgula_count, int n) {

		// Definir dados
		boolean all_num = true;
		int tamanho = palavra.length();
		char char_atual;

		if (n < tamanho) {

			char_atual = palavra.charAt(n);

			// Detectar se não é um número
			if (!((char_atual >= '1' && char_atual <= '9') || char_atual == '0')) {

				// Detectar se já foi utilizado uma vírgula ou ponto para separar a parte
				// inteira da decimal
				if (char_atual == '.' || char_atual == ',') {
					if (virgula_count == 0) {
						all_num = x4(palavra, virgula_count+1, n+1);
					} else {
						all_num = false;
					}
				} else {
					all_num = false;
				}

			}
			else {
				all_num = x4(palavra, virgula_count, n+1);
			}


		}

		return all_num;

	}

	public static void main(String[] args) {

		// Definir dados
		String palavra = "Ayo";
		boolean x1, x2, x3, x4;
		boolean in_loop = true;

		// Loop enquanto diferente de 'FIM'
		while (in_loop) {

			// Ler input
			palavra = MyIO.readLine();

			// Se for 'FIM', parar o loop
			// Feito por recognição individual dos caracteres
			if (palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M'
					&& palavra.length() == 3) {
				in_loop = false;
			}
			// No contrário, chamar os métodos
			else {

				// Obter resultados dos métodos
				x1 = x1(palavra);
				x2 = x2(palavra);
				x3 = x3(palavra);
				x4 = x4(palavra);

				// Mostrar resultado x1
				if (x1) {
					MyIO.print("SIM ");
				} else {
					MyIO.print("NAO ");
				}

				// Mostrar resultado x2
				if (x2) {
					MyIO.print("SIM ");
				} else {
					MyIO.print("NAO ");
				}

				// Mostrar resultado x3
				if (x3) {
					MyIO.print("SIM ");
				} else {
					MyIO.print("NAO ");
				}

				// Mostrar resultado x4
				if (x4) {
					MyIO.println("SIM");
				} else {
					MyIO.println("NAO");
				}

			}

		}

	}

}
