
public class Main {

  static String corte_String(String palavra, int inicio, int fim) {

    char[] c_array = new char[(fim - inicio) + 1];
    int x = 0, y = 0;

    for (y = inicio; y <= fim; y++) {
      c_array[x] = palavra.charAt(y);
      x++;
    }

    String corte = new String(c_array);
    return corte;
  }

  static boolean valores_letras(char caractere, boolean A, boolean B, boolean C) {
    // Dados
    boolean resultado = false;

    if (caractere == 'A') {
      resultado = A;
    } else if (caractere == 'B') {
      resultado = B;
    } else if (caractere == 'C') {
      resultado = C;
    }

    return resultado;
  }

  static boolean algebra_bool(String logica, boolean A, boolean B, boolean C) {

    // Dados
    boolean resultado = true;
    boolean parenteses1 = false, parenteses2 = false, parenteses3 = false, parenteses4 = false;
    int x = 0, virgula1 = 0, virgula2 = 0, virgula3 = 0, begin = 0;
    int parenteses = 0;
    boolean p1 = false, p2 = false, p3 = false, p4 = false;

    // Achar começo do que está entre parenteses
    while (logica.charAt(x) != '(') {
      x++;
    }
    x++;
    begin = x;

    // Determinar onde estão as vírgulas e se há mais operações dentro do parenteses
    // atual
    while (parenteses != -1) {

      if (logica.charAt(x) == '(') {

        parenteses++;
        if (virgula1 != 0) {
          if (virgula2 != 0) {
            if (virgula3 != 0) {
              parenteses4 = true;

            } else {
              parenteses3 = true;

            }
          } else {
            parenteses2 = true;

          }
        } else {
          parenteses1 = true;

        }

      } else if (logica.charAt(x) == ')') {
        parenteses--;

      } else if ((logica.charAt(x) == ',') && (parenteses == 0)) {
        if (virgula1 == 0) {
          virgula1 = x;
        } else {
          if (virgula2 == 0) {
            virgula2 = x;
          } else {
            if (virgula3 == 0) {
              virgula3 = x;
            }
          }
        }

      }

      x++;
    } // End while

    String operacao = corte_String(logica, 0, begin - 2);

    // Detectar se é uma operação de not (seu tratamento é diferenciado das demais)
    if (operacao.charAt(0) == 'n' && operacao.charAt(1) == 'o' && operacao.charAt(2) == 't'
        && operacao.length() == 3) {

      if (parenteses1) {
        resultado = !(algebra_bool(corte_String(logica, begin, (logica.length() - 2)), A, B, C));

      } else {
        resultado = !(valores_letras(logica.charAt(begin), A, B, C));

      }

    }
    // Se for and ou or
    else {

      // Se houver uma operacao no primeiro elemento
      if (parenteses1) {

        int z1 = virgula1;
        while (logica.charAt(z1) != ')') {
          z1--;
        }

        p1 = algebra_bool(corte_String(logica, begin, z1), A, B, C);

      } else {
        p1 = valores_letras(logica.charAt(begin), A, B, C);

      }

      // Se houver uma operacao no segundo elemento
      if (parenteses2) {
        if (virgula2 == 0) {
          p2 = algebra_bool(corte_String(logica, (virgula1 + 2), (logica.length() - 2)), A, B, C);

        } else {
          p2 = algebra_bool(corte_String(logica, (virgula1 + 2), (virgula2 - 2)), A, B, C);

        }

      }
      // Do contrário, obter o valor da letra se ela existir
      else {
        p2 = valores_letras(logica.charAt((virgula1 + 2)), A, B, C);

      }

      // Se houver uma operacao no terceiro elemento
      if (parenteses3) {
        if (virgula3 == 0) {
          p3 = algebra_bool(corte_String(logica, (virgula2 + 2), (logica.length() - 2)), A, B, C);

        } else {
          p3 = algebra_bool(corte_String(logica, (virgula2 + 2), (virgula3 - 2)), A, B, C);

        }

      }
      // Do contrário, obter o valor da letra se ela existir
      else if (virgula2 != 0) {
        p3 = valores_letras(logica.charAt((virgula2 + 2)), A, B, C);

      }

      // Se houver uma operacao no quarto elemento
      if (parenteses4) {
        p4 = algebra_bool(corte_String(logica, (virgula3 + 2), (logica.length() - 2)), A, B, C);

      }
      // Do contrário, obter o valor da letra se ela existir
      else if (virgula3 != 0) {
        p4 = valores_letras(logica.charAt((virgula3 + 2)), A, B, C);

      }

      // Se for 'and'
      if (operacao.charAt(0) == 'a' && operacao.charAt(1) == 'n' && operacao.charAt(2) == 'd'
          && operacao.length() == 3) {
        // Se tiver 4 partes
        if (virgula3 != 0) {
          resultado = (p1 && p2 && p3 && p4);
        }
        // Se tiver 3 partes
        else if (virgula2 != 0) {
          resultado = (p1 && p2 && p3);
        }
        // Se tiver 2 partes
        else {
          resultado = (p1 && p2);
        }

      }
      // Se for 'or'
      else if (operacao.charAt(0) == 'o' && operacao.charAt(1) == 'r'
          && operacao.length() == 2) {
        // Se tiver 4 partes
        if (virgula3 != 0) {
          resultado = (p1 || p2 || p3 || p4);
        }
        // Se tiver 3 partes
        else if (virgula2 != 0) {
          resultado = (p1 || p2 || p3);
        }
        // Se tiver 2 partes
        else {
          resultado = (p1 || p2);
        }

      }

    }

    return resultado;
  }

  static boolean algebra_bool_call(String logica_full) {

    String logica;
    boolean resultado;
    int tamanho = (logica_full.length() - 1);
    boolean A = false, B = false, C = false;

    if (logica_full.charAt(0) == '2') {
      logica = corte_String(logica_full, 6, tamanho);

      if (logica_full.charAt(2) == '1') {
        A = true;
      }
      if (logica_full.charAt(4) == '1') {
        B = true;
      }

    } else {
      logica = corte_String(logica_full, 8, tamanho);

      if (logica_full.charAt(2) == '1') {
        A = true;
      }
      if (logica_full.charAt(4) == '1') {
        B = true;
      }
      if (logica_full.charAt(6) == '1') {
        C = true;
      }
    }

    resultado = algebra_bool(logica, A, B, C);

    return resultado;
  }

  // Método Principal
  public static void main(String[] args) {

    String logica = "Ayo";
    boolean in_loop = true;

    // Loop enquanto diferente de '0'
    while (in_loop) {
      // Ler a string
      logica = MyIO.readLine();

      // Se for '0', fazer nada
      // Feito dessa forma pois não funcionou colocar ( logica != "0 )
      if (logica.charAt(0) == '0' && logica.length() == 1) {
        in_loop = false;

      }
      // No contrário, chamar o método
      else {
        boolean resultado = algebra_bool_call(logica);

        if (resultado) {
          MyIO.println("1");
        } else {
          MyIO.println("0");
        }

      }

    }

  }

}
