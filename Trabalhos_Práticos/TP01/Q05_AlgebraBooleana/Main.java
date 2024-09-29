
public class Main {

  // Metodo para cortar a string
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

  // Metodo para descobrir quantas vírgulas (e assim descobrir quantas partes) há
  // em uma operação lógica
  static int virgula_count(String logica, int inicio) {
    // Dados
    int virgulas = 0, x = inicio+2;
    int parenteses = 0;
    char char_atual;

    while (parenteses != -1) {
      char_atual = logica.charAt(x);
      
      if (char_atual == ')') {
        parenteses = parenteses - 1;
      } else if (char_atual == '(') {
        parenteses = parenteses + 1;
      } else if (char_atual == ',') {
        if (parenteses == 0) {
          virgulas++;
        }
      }

      x++;
    }

    return virgulas;
  }

  // Char para int
  static int char_to_int(char caracter) {

    // Dados
    int resultado = 0;

    if (caracter == '1') {
      resultado = 1;
    } else if (caracter == '2') {
      resultado = 2;
    } else if (caracter == '3') {
      resultado = 3;
    } else if (caracter == '4') {
      resultado = 4;
    } else if (caracter == '5') {
      resultado = 5;
    } else if (caracter == '6') {
      resultado = 6;
    }

    return resultado;
  }

  // Simplificar a string da logica para facilitar sua leitura
  public static String simplificar(String expressao) {
    // Dados
    int tamanho = expressao.length();
    int x = 0, y = 0;
    char[] resultado = new char[tamanho];
    char char_atual;

    for (x = 0; x < tamanho; x++) {
      char_atual = expressao.charAt(x);

      // Se for um espaço vazio, não copiar
      if (!(char_atual == ' ')) {
        // Se for 'and'
        if (char_atual == 'a') {
          resultado[y] = '&';
          x = x + 2; // Pular o restante da palavra
        }
        // Se for 'or'
        else if (char_atual == 'o') {
          resultado[y] = '|';
          x = x + 1; // Pular o restante da palavra
        }
        // Se for 'not'
        else if (char_atual == 'n') {
          resultado[y] = '~';
          x = x + 2; // Pular o restante da palavra
        }
        // Copiar o restante
        else {
          resultado[y] = char_atual;
        }

        y++;
      }
    }

    // Fazer uma cópia sem espaço vazio
    int blank_spaces = 0, a = 0, b = 0;
    for (a = 0; a < tamanho; a++) {
      if ((resultado[a] == 'A') || (resultado[a] == 'B') || (resultado[a] == 'C') ||
          (resultado[a] == '&') || (resultado[a] == '|') || (resultado[a] == '~') ||
          (resultado[a] == '(') || (resultado[a] == ')') || (resultado[a] == ',')) {
        blank_spaces = blank_spaces + 1;
      }
    }
    char[] resultado_refinado = new char[blank_spaces];
    for (a = 0; a < tamanho; a++) {
      if ((resultado[a] == 'A') || (resultado[a] == 'B') || (resultado[a] == 'C') ||
          (resultado[a] == '&') || (resultado[a] == '|') || (resultado[a] == '~') ||
          (resultado[a] == '(') || (resultado[a] == ')') || (resultado[a] == ',')) {
        resultado_refinado[b] = resultado[a];
        b = b + 1;
      }
    }

    return (new String(resultado_refinado));
  }

  // Método para substituir as letras por seus valores
  public static String substituir_letras(String expressao, boolean A, boolean B, boolean C) {
    // Dados
    int tamanho = expressao.length();
    int x = 0;
    char[] resultado = new char[tamanho];
    char char_atual;

    for (x = 0; x < tamanho; x++) {
      char_atual = expressao.charAt(x);

      if (char_atual == 'A') {
        if (A) {
          resultado[x] = '1';
        } else {
          resultado[x] = '0';
        }
      } else if (char_atual == 'B') {
        if (B) {
          resultado[x] = '1';
        } else {
          resultado[x] = '0';
        }
      } else if (char_atual == 'C') {
        if (C) {
          resultado[x] = '1';
        } else {
          resultado[x] = '0';
        }
      } else {
        resultado[x] = char_atual;
      }

    }

    return (new String(resultado));
  }

  // Reorganizar a ordem da expressao
  public static String reorganizar(String expressao) {
    // Dados
    int tamanho = expressao.length();
    // MyIO.println(expressao + "-|- tamanho: " + tamanho);
    int x = 0, y = 0, z = -1;
    int virgulas = 0;
    char[] resultado = new char[tamanho * 2];
    char[] operacoes = new char[tamanho * 2];
    char char_atual;

    while (x < tamanho) {
      char_atual = expressao.charAt(x);
      // MyIO.println("x: " + x + ", y: " + y + ", z: " + z + ", char: " +
      // char_atual);

      if ((char_atual == '0') || (char_atual == '1')) {
        resultado[y] = char_atual;
        y = y + 1;
      }

      // Se for um 'and' ou 'or', adicionar ao stack de operadores
      else if ((char_atual == '&') || (char_atual == '|')) {

        // Inserir a quantidade de valores que fazem parte da expressao
        virgulas = virgula_count(expressao, x);
        if (virgulas == 1) {
          z = z + 1;
          operacoes[z] = '2';
        } else if (virgulas == 2) {
          z = z + 1;
          operacoes[z] = '3';
        } else if (virgulas == 3) {
          z = z + 1;
          operacoes[z] = '4';
        } else if (virgulas == 4) {
          z = z + 1;
          operacoes[z] = '5';
        }

        z = z + 1;
        operacoes[z] = char_atual;
      }

      // Se for um 'not'
      else if (char_atual == '~') {
        // Se for um not simples, adicionar o inverso do que estiver entre parenteses ao
        // resultado
        if (expressao.charAt(x + 3) == ')') {
          if (expressao.charAt(x + 2) == '0') {
            resultado[y] = '1';
          } else if (expressao.charAt(x + 2) == '1') {
            resultado[y] = '0';
          }
          y = y + 1;
          x = x + 3;
        }
        // Do contrario, adicionar ao stack de operações
        else {
          z = z + 1;
          operacoes[z] = char_atual;
        }
      }

      // Se for uma vírgula
      else if (char_atual == ',') {
        //
        while (operacoes[z] != '(') {
          resultado[y] = operacoes[z];
          y = y + 1;
          z = z - 1;
        }
      }

      // Se for uma abertura de parênteses
      else if (char_atual == '(') {
        z = z + 1;
        operacoes[z] = char_atual;
      }

      // Se for um fechamento de parênteses
      else if (char_atual == ')') {
        //
        while (operacoes[z] != '(') {
          resultado[y] = operacoes[z];
          y = y + 1;
          z = z - 1;
        }

        // Para sobrepor o parênteses
        z = z - 1;

        // Para detectar and e or
        if ((operacoes[z] == '2') || (operacoes[z] == '3')
            || (operacoes[z] == '4') || (operacoes[z] == '5')) {
          resultado[y] = operacoes[z];
          y = y + 1;
          z = z - 1;
          resultado[y] = operacoes[z];
          y = y + 1;
          z = z - 1;
        }
        // Para not
        else if (operacoes[z] == '~') {
          resultado[y] = operacoes[z];
          y = y + 1;
          z = z - 1;
        }
      }

      x = x + 1;
    }

    // Inserir operações restantes
    while (z >= 0) {
      resultado[x] = operacoes[z];
      x = x + 1;
      z = z - 1;
    }

    return (new String(resultado));
  }

  public static boolean algebra_bool(String logica_full, boolean A, boolean B, boolean C) {

    // Conversão da String de logica
    String simplificado = substituir_letras(simplificar(logica_full), A, B, C);
    //MyIO.println(simplificado);
    String logica = reorganizar(simplificado);
    //MyIO.println(logica);

    // Dados
    int x = 0, y = 0, tamanho = logica.length();
    boolean[] resultado_array = new boolean[tamanho];
    char char_atual;
    boolean resposta = true;

    for (x = 0; x < tamanho; x = x + 1) {
      char_atual = logica.charAt(x);
      // Detectar e o caracter está ou não dentre os esperados para evitar erros.
      // Os numeros para representar as quantidades de partes não são necessários
      // já que eles sempre tem antes um & ou |
      if ((char_atual == '0') || (char_atual == '1') ||
          (char_atual == '~') || (char_atual == '|') || (char_atual == '&')) {
        // MyIO.println("x: " + x + ", y: " + y + ", char: " + char_atual + ", tamanho:
        // " + tamanho);

        // Se for um valor booleano
        if ((char_atual == '0') || (char_atual == '1')) {
          if (char_atual == '1') {
            resultado_array[y] = true;
          } else {
            resultado_array[y] = false;
          }

          y = y + 1;
        }

        // Se for um Not
        else if (char_atual == '~') {
          y = y - 1;
          if (resultado_array[y] == false) {
            resultado_array[y] = true;
          } else if (resultado_array[y] == true) {
            resultado_array[y] = false;
          }

          y = y + 1;
        }

        // Se for and ou or (ou seja, o restante dos casos)
        else {
          boolean p1 = false, p2 = false, p3 = false, p4 = false, p5 = false, p6 = false;
          boolean resultado = false;
          // Ajustar o x para pular a leitura do numero no inicio da
          // proxima repeticao do for
          x = x + 1;
          char n_virgulas = logica.charAt(x);
          //MyIO.println("n_virgulas: " + n_virgulas);
          int virgulas = char_to_int(n_virgulas);

          // Definir primeira parte
          y = y - 1;
          if (resultado_array[y] == true) {
            p1 = true;
          }
          // Definir segunda parte
          y = y - 1;
          if (resultado_array[y] == true) {
            p2 = true;
          }

          // Se tiver >2 partes
          if (virgulas > 2) {
            y = y - 1;
            if (resultado_array[y] == true) {
              p3 = true;
            }

            // Se tiver >3 partes
            if (virgulas > 3) {
              y = y - 1;
              if (resultado_array[y] == true) {
                p4 = true;
              }

              // Se tiver >4 partes
              if (virgulas > 4) {
                y = y - 1;
                if (resultado_array[y] == true) {
                  p5 = true;
                }

                // Se tiver >5 partes
                if (virgulas > 5) {
                  y = y - 1;
                  if (resultado_array[y] == true) {
                    p6 = true;
                  }
                }
              }
            }
          }

          // Definir operacoes
          if (virgulas == 2) {
            // Se for and
            if (char_atual == '&') {
              resultado = p1 && p2;
            }
            // Se for or
            if (char_atual == '|') {
              resultado = p1 || p2;
            }
          } else if (virgulas == 3) {
            // Se for and
            if (char_atual == '&') {
              resultado = p1 && p2 && p3;
            }
            // Se for or
            if (char_atual == '|') {
              resultado = p1 || p2 || p3;
            }
          } else if (virgulas == 4) {
            // Se for and
            if (char_atual == '&') {
              resultado = p1 && p2 && p3 && p4;
            }
            // Se for or
            if (char_atual == '|') {
              resultado = p1 || p2 || p3 || p4;
            }
          } else if (virgulas == 5) {
            // Se for and
            if (char_atual == '&') {
              resultado = p1 && p2 && p3 && p4 && p5;
            }
            // Se for or
            if (char_atual == '|') {
              resultado = p1 || p2 || p3 || p4 || p5;
            }
          } else if (virgulas == 6) {
            // Se for and
            if (char_atual == '&') {
              resultado = p1 && p2 && p3 && p4 && p5 && p6;
            }
            // Se for or
            if (char_atual == '|') {
              resultado = p1 || p2 || p3 || p4 || p5 || p6;
            }
          }

          // Redefinir posicionamento (y)
          if (resultado) {
            resultado_array[y] = true;
          } else {
            resultado_array[y] = false;
          }

          y = y + 1;
        }
      }
    }

    resposta = resultado_array[0];

    return resposta;
  }

  // Chamada da funcao de algebra booleana
  public static boolean algebra_bool_call(String logica_full) {

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
