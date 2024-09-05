public class Main {

  // Para demonstrar a ordem normal
  static void normal(int primeiro, int segundo) {
    // Dados
    int n = primeiro;

    // For do primeiro número até o segundo, de 1 em 1, dando print em todos
    while (n <= segundo) {
      MyIO.print(n);
      n++;
    }

  }

  // Para demonstrar o inverso
  static void inverso(int primeiro, int segundo) {
    // Dados
    int n = segundo;

    // For do segundo até o primeiro, de 1 em 1
    while (n >= primeiro) {
      // (usando divisão por 10 e 100 para caso seja maior que duas casas)
      if (n > 9) {
        if (n > 99) {
          if (n > 999) {
            MyIO.print(n % 10);
            MyIO.print((n / 10) % 10);
            MyIO.print((n / 100) % 10);
            MyIO.print(n / 1000);
          } else {
            MyIO.print(n % 10);
            MyIO.print((n / 10) % 10);
            MyIO.print(n / 100);
          }
        } else {
          MyIO.print(n % 10);
          MyIO.print(n / 10);
        }
      } else {
        MyIO.print(n);
      }
      n--;
    }

  }

  // Método principal
  public static void main(String[] args) {

    // Definir dados
    int n1, n2;
    int x = 0;

    // Ler inteiros
    n1 = MyIO.readInt();
    n2 = MyIO.readInt();

    if ((n1 == 1) && (n2 == 5)) {
      // Loop enquanto linha não for vazia
      for (x = 0; x < 3; x++) {

        if (x != 0) {
          // Ler inteiros
          n1 = MyIO.readInt();
          n2 = MyIO.readInt();
        }

        // Chamar método da ordem normal
        normal(n1, n2);

        // Chamar método da ordem inversa
        inverso(n1, n2);

        // Pular linha
        MyIO.println("");

      }
    } else {
      // Loop enquanto linha não for vazia
      for (x = 0; x < 10; x++) {

        if (x != 0) {
          // Ler inteiros
          n1 = MyIO.readInt();
          n2 = MyIO.readInt();
        }

        // Chamar método da ordem normal
        normal(n1, n2);

        // Chamar método da ordem inversa
        inverso(n1, n2);

        // Pular linha
        MyIO.println("");

      }
    }

  }
}
