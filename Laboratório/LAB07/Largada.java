
class Corrida {

  int n_competidores;
  int[] inicial;
  int[] final;

/* Construtores */
  Corrida () {

    this.n_competidores = 5;

    this.inicial = new int [this.getN()];
    this.final = new int [this.getN()];
  
  }

  Corrida(int n, int[] inicio, int[] fim) {

    this.setN(n);

    this.setIni(inicio);
    this.setFim(fim);

  }

  /* */

  /* Gets e sets */

  public int getN() {
    return (this.n_competidores);
  }

  public int get1Ini(int x) {
    return (this.inicial[x]);
  }

  public int get1Fim (int x) {
    return (this.final[x]);
  }

  public void setN(int n) {
    this.n_competidores = n;
  }

  public void setIni(int[] inicial) {
    this.inicial = new int[this.getN()];
    this.inicial = inicial;
  }

  public void setFim (int [] final) {
    this.final = new int [this.getN()];
    this.final = final;
  }
/* */

} // Fim da classe Corrida

class Largada {

  // Método para obter o número mínimo de ultrapassagens
  public int race_results (int [] inicio, int [] fim) {

  // Dados
  int tamanho = inicio.length;
  int ultrapasssagens = 0;
  int x = 0;
  int a = 0;
  
  while ( x < tamanho ) {
  
    a = x;
    // Achar onde ocorre o elemento 'x' do array de fim no de inicio
    while (inicio[a] != fim[x]) {
      a++;
    }
    
    // "Método de inserção" até serem iguais
    while (inicio[x] != fim[x]) {
      int temp = inicio [a];
      inicio [a] = inicio [a-1];
      inicio [a-1] = temp;
      
      a--;
      ultrapasssagens++;
    }
  
    x++;
  }


  return ( ultrapasssagens );
  }

  public void main (String[] args) {

    // Dados (Aplicar classe da corrida)
  
    int n = 0, x = 0, y = 0;
    String competidores_inicio;
    String competidores_fim;
    int [] inicio;
    int [] fim;
    int ultrapasssagens;
    Corrida[] competicoes = new Corrida[10];
  
    // Ler numero de competidores
    n = MyIO.readInt();
    while (n != EOF) {
  
      // Iniciar Strings e arrays com base no número de competidores
      inicio = new int [n];
      fim = new int [n];
    
      // Iniciar classe 'Corrida'
      competicoes[x] = new Corrida (n, inicio, fim);
  
      // Ler numero de competidores
      n = MyIO.readInt();

      x++;
    }

    while (y < x) {

      // Método de comparação
      ultrapassagens = race_results(inicio, fim);
      //ultrapassagens = race_results(competicao);
        
      // Print do resultado
      System.out.println(ultrapassagens);

      y++;
    }


  }

}
