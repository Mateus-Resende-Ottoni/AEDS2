
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>



// Definição do tipo Corrida
typedef              	
  struct Corrida_struct  	
  {                   	
    int n_competidores;
    int	*inicial;
    int *final;	
  }                   	
Corrida_; 

typedef Corrida_* Corrida;

/*	Sets e gets 	*/

int getN (Corrida corrida) {
  return (corrida->n_competidores);
}

int get1Ini (Corrida corrida, int x) {
  return (corrida->inicial[x]);
}

int get1Fim (Corrida corrida, int x) {
  return (corrida->final[x]);
}

void setN (Corrida corrida, int n) {
  corrida->n_competidores = n;
}

void set1Ini (Corrida corrida, int x, int n) {
  corrida->inicial[n] = x;
}

void setIni (Corrida corrida, int *ini) {
  corrida->inicial = ini;
}

void set1Fim (Corrida corrida, int x, int n) {
  corrida->final[n] = x;
}

void setFim (Corrida corrida, int *fim){
  corrida->final = fim;
}

/*		*/

/*	Construtor	*/

Corrida newCorrida (int n, int *inicio, int *fim) {

Corrida novo;
novo = malloc ( sizeof (Corrida) );

setN (novo, n);
setIni (novo, inicio);
setFim (novo, fim);

}

/*		*/

/*	Método para obter o resultado de ultrapassagens	*/

int race_results (Corrida corrida) {

  //Dados
  int tamanho = getN (corrida);
  int ultrapassagens = 0;
  int x = 0, a = 0;

  while ( x < tamanho ) {

    a = x;
    // Achar onde ocorre o elemento 'x' do array de fim no de inicio
    while ( get1Ini(corrida, a) != get1Fim(corrida, x) ) {
      a++;
    }
    
    // "Método de inserção" até serem iguais
    while ( get1Ini(corrida, x) != get1Fim(corrida, x) ) {
      int temp = get1Ini(corrida, a);
      set1Ini ( corrida, get1Ini(corrida, a-1),   a );
      set1Ini ( corrida,                  temp, a-1 );
    
      a--;
      ultrapassagens++;
    }
  
    x++;
  }

  return ( ultrapassagens );
}

/*		*/

/*	Método main	*/
int main (void)
{

  // Variaveis e dados
  int n = 0, x = 0, y = 0;
  int *inicio;
  int *fim;
  int ultrapassagens;
  Corrida* competicoes = malloc ( 10 * sizeof(Corrida) );

  // Ler número de competidores e iniciar while
  while ( scanf("%d", &n) != EOF ) {

    //Iniciar arrays com base no número de competidores
    inicio = (int*) malloc ( n * sizeof(int) );
    fim = (int*) malloc ( n * sizeof(int) );

    // Ler arrays
    for (int i = 0; i < n; i++) {
	scanf( "%d", &inicio[i] );
    }
    for (int i = 0; i < n; i++) {
	scanf( "%d", &fim[i] );
    }

    //Iniciar classe corrida
    competicoes[x] = newCorrida (n, inicio, fim);

    x++;
  }

  for (y = 0; y < x; y++) {

    ultrapassagens = race_results(competicoes[y]);

    printf("%d\n", ultrapassagens);

  }

  return ( 0 );
}

/*		*/


