
public class Main {

  // Método para converter na versão criptografada
  static String cifra_cesar (String palavra) {

    int tamanho = palavra.length();
    int chave = 3;

    int x = 0;
    int y = 0;

    String convertida = palavra;
    // Array de char para armazenar as conversões
    char [] c_array = new char [tamanho];

    // Repetir vezes iguais ao tamanho da palavra
    while ( x < tamanho ){
      // Obter caracter da palavra
      c_array[x] = palavra.charAt(x);

      // Aplicar chave de ciframento
      for (y = 0; y < chave; y++)
      {
        c_array[x]++;
      }

      /*
      // Separar entre letras maiúsculas e minúsculas e outros para poder determinar se ultrapassou o grupo ao qual pertence
      if (palavra.charAt(x) >= 'A' && palavra.charAt(x) <= 'Z')
      {
        if (c_array[x] > 'Z')
        {
          for (z = 0; z < 26; z++)
          {
            c_array[x]--;
          }
        }
      }
      else if (palavra.charAt(x) >= 'a' && palavra.charAt(x) <= 'z')
      {
        if (c_array[x] > 'z')
        {
          for (z = 0; z < 26; z++)
          {
            c_array[x]--;
          }
        }
      }
      */

      x++;
    }

    // Aplicar caracteres convertidos a uma nova string
    convertida = new String(c_array);

    // Retornar o resultado
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
