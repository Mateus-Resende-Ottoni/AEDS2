
public class Main {

  // Método para determinar se é palíndromo
  static boolean palindromo(String palavra) {
    boolean palindro = true;
    int tamanho = palavra.length();
    int x = 0;
    int y = tamanho - 1;

    // Loop enquanto for palindromo e não chegar ao final da string
    while (palindro == true && x < tamanho / 2) {
      // Comparar cada caractere
      if (palavra.charAt(x) != palavra.charAt(y)) {
        palindro = false;
      }
      // MyIO.println ( " " + palavra.charAt(x) + " " + palavra.charAt(y) + " " );

      // Prosseguir para o próximo caractere
      x = x + 1;
      y = y - 1;
    }

    // Retornar o resultado
    return palindro;
  }

  // Método Principal
  public static void main(String[] args) {

    MyIO.setCharset("UTF-8");

    String palavra = "Ayo";
    boolean palindro = true;
    boolean in_loop = true;

    // Loop enquanto diferente de 'FIM'
    while (in_loop) {
      // Ler a palavra
      palavra = MyIO.readLine();

      // Se for 'FIM', fazer nada
      // Feito dessa forma pois não funcionou colocar ( palavra != "Fim" )
      if (palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M' && palavra.length() == 3) {
        in_loop = false;
      }
      // No contrário, chamar o método
      else {
        palindro = palindromo(palavra);
        // Print do resultado
        if (palindro) {
          MyIO.println("SIM");
        } else {
          MyIO.println("NAO");
        }
      }

    }

  }

}
