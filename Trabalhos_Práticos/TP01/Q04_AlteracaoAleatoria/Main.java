
import java.util.Random;

public class Main {

    // Método para converter na versão criptografada
    static String alterar_random (String palavra, Random gerador) {
  
      int tamanho = palavra.length();
  
      int x = 0;

      char c1; char c2;
  
      String convertida;
      // Array de char para armazenar as conversões
      char [] c_array = new char [tamanho];

      // Determinar as letras minusculas a serem alteradas
      c1 = (char) ('a' + (Math.abs(gerador.nextInt())) % 26  );
      c2 = (char) ('a' + (Math.abs(gerador.nextInt())) % 26  );

      /*
      do {
        c2 = (char) ('a' + (Math.abs(gerador.nextInt())) % 26  );
      } while (c2 == c1);
      */

      // Repetir vezes iguais ao tamanho da palavra
      while ( x < tamanho ){

        // Se a letra for a primeira das selecionadas, alterar ela
        if ( palavra.charAt(x) == c1 )
        {
        c_array[x] = c2;
        }
        // No contrário, só copiar
        else {
        // Obter caracter da palavra
        c_array[x] = palavra.charAt(x);
        }
  
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

      // Gerador definido fora do método pois do contrário ele é redefinido toda vez que o método for chamado, efetivamente só convertendo a mesma letra
      Random gerador = new Random ();
      gerador.setSeed(4);
  
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
          String convertida = alterar_random(palavra, gerador);
  
          MyIO.println(convertida);
        }
  
      }
  
    }
  
  }





