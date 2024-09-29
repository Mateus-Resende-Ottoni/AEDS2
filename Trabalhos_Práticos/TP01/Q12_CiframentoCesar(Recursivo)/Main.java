
public class Main {

  // Método para converter na versão criptografada
  static char[] cifra_cesar(String palavra, int chave, int n) {

    int tamanho = palavra.length();
    char[] c_array = new char[tamanho];

    if ( n < (tamanho - 1) ) {
      c_array = cifra_cesar(palavra, chave, n+1);
    }

    // Obter caracter da palavra original
    c_array [n] = palavra.charAt(n);

    // Aplicar chave
    for (int x = 0; x < chave; x++) {
      c_array[n]++;
    }

    return c_array;
  }

  // Método para chamar o método recursivo
  static String cifra_cesar(String palavra) {

    int tamanho = palavra.length();
    char[] c_array = new char[tamanho];

    c_array = cifra_cesar(palavra, 3, 0);

    // Aplicar caracteres convertidos a uma nova string
    String convertida = new String(c_array);

    return convertida;
  }

  // Método Principal
  public static void main(String[] args) {

    String palavra = "Ayo";
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
        String convertida = cifra_cesar(palavra);

        MyIO.println(convertida);
      }

    }

  }

}
