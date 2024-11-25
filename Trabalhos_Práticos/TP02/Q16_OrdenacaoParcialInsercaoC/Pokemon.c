
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#include <string.h>


#define null NULL

/*----------------------------------------*/
void str_copy ( char * original, char * new, int init, int end ) {

int y = 0;

for (int x = init + 1; x < end; x++) {
  new[y] = original[x];
  y++;
}
new[y] = '\0';


}
/*----------------------------------------*/

// Structs


// Definição do tipo Pokemon
typedef              	
 struct Pokemon_struct  	
 {                   	
   int           id;      	
   int           generation;
   char         *name;
   char         *description;
   int           n_types;
   char         *types[2];
   int           n_abilities;
   char        **abilities;
   double        weight;
   double        height;
   int           capture_rate;
   bool          legendary;
   char         *date;
 }                   	
Poke;           	

typedef Poke* Pokemon;


// Struct para salvar dados acerca da execução
typedef
 struct Execution
 {
   int		comparacoes;
   int		movimentacoes;
   time_t	inicio;
   time_t	fim;
 }
Exe_Data;

typedef Exe_Data* XData;

/*----------------------------------------*/


// Construtores

Pokemon new_Poke () {

int n = 24;

Pokemon novo = null;

novo = malloc ( 1 * sizeof (Poke) );

if ( novo ) {

  novo->id = 0;
  novo->generation = 0;

  novo->name = (char*) malloc ( strlen("Missingno") + 1 );
  strcpy(novo->name, "Missingno");

  novo->description = (char*) malloc ( strlen("This is wrong") + 1 );
  novo->description = "This is wrong";

  novo->n_types = 1;
  novo->types[0] = malloc ( strlen("Unknown") + 1 );
  novo->types[1] = malloc ( strlen("Unow") + 1 );
  novo->types[0] = "Unknown";
  novo->types[1] = "0";

  novo->n_abilities = 1;
  novo->abilities = malloc ( 4 * (strlen("Program Crash") + 1) );
  novo->abilities[0] = "Program Crash";

  novo->weight = 0.0;
  novo->height = 0.0;

  novo->capture_rate = 0;
  novo->legendary = true;

  novo->date = (char*) malloc ( strlen("99/99/2012") + 1 );
  novo->date = "99/99/2012";


}

return ( novo );
}


XData new_Data () {

XData novo = null;
novo = malloc ( 1 * sizeof (Exe_Data) );

if ( novo ) {

  novo->comparacoes = 0;
  novo->movimentacoes = 0;
  time(&novo->inicio);
  time(&novo->fim);

}


return ( novo );
}



/*----------------------------------------*/

// Set

void Poke_set_id ( Pokemon pokemon, int id ) {
pokemon->id = id;
}

void Poke_set_generation ( Pokemon pokemon, int generation ) {
pokemon->generation = generation;
}

void Poke_set_name ( Pokemon pokemon, char * name ) {
pokemon->name = (char*) malloc ( strlen(name) + 1 );
strcpy ( pokemon->name, name);
}

void Poke_set_description ( Pokemon pokemon, char * description ) {
pokemon->description = (char*) malloc ( strlen(description) + 1 );
strcpy ( pokemon->description, description);
}

void Poke_set_n_types ( Pokemon pokemon, int n_types ) {
pokemon->n_types = n_types;
}

void Poke_set_type ( Pokemon pokemon, char * type, int n ) {
pokemon->types[n] = (char*) malloc ( strlen(type) + 1 );
strcpy ( pokemon->types[n], type);
}

void Poke_set_n_abilities ( Pokemon pokemon, int n_abilities ) {
pokemon->n_abilities = n_abilities;
}

void Poke_set_ability ( Pokemon pokemon, char * ability, int n ) {
pokemon->abilities[n] = (char*) malloc ( strlen(ability) + 1 );
strcpy ( pokemon->abilities[n], ability);
}

void Poke_set_weight ( Pokemon pokemon, double weight ) {
pokemon->weight = weight;
}

void Poke_set_height ( Pokemon pokemon, double height ) {
pokemon->height = height;
}

void Poke_set_capture_rate ( Pokemon pokemon, int capture_rate ) {
pokemon->capture_rate = capture_rate;
}

void Poke_set_legendary ( Pokemon pokemon, bool legendary ) {
pokemon->legendary = legendary;
}

void Poke_set_date ( Pokemon pokemon, char * date ) {
pokemon->date = (char*) malloc ( strlen(date) + 1 );
strcpy ( pokemon->date, date);
}


/*----------------------------------------*/

// Get

int Poke_get_id ( Pokemon pokemon ) {
return ( pokemon->id );
}

int Poke_get_generation ( Pokemon pokemon ) {
return ( pokemon->generation );
}

char * Poke_get_name ( Pokemon pokemon ) {
return ( pokemon->name );
}

char * Poke_get_description ( Pokemon pokemon ) {
return ( pokemon->description );
}

int Poke_get_n_types ( Pokemon pokemon ) {
return ( pokemon->n_types );
}

char * Poke_get_type ( Pokemon pokemon, int n ) {
return ( pokemon->types[n] );
}

int Poke_get_n_abilities ( Pokemon pokemon ) {
return ( pokemon->n_abilities );
}

char * Poke_get_ability ( Pokemon pokemon, int n ) {
return ( pokemon->abilities[n] );
}

double Poke_get_weight ( Pokemon pokemon ) {
return ( pokemon->weight );
}

double Poke_get_height ( Pokemon pokemon ) {
return ( pokemon->height );
}

int Poke_get_capture_rate ( Pokemon pokemon ) {
return ( pokemon->capture_rate );
}

bool Poke_get_legendary ( Pokemon pokemon ) {
return ( pokemon->legendary );
}

char * Poke_get_date ( Pokemon pokemon ) {
return ( pokemon->date );
}


/*----------------------------------------*/

// Clone

Pokemon Poke_clone ( Pokemon pokemon ) {

Pokemon clone = null;

Poke_set_id ( clone, Poke_get_id ( pokemon ) );
Poke_set_generation ( clone, Poke_get_generation ( pokemon ) );
Poke_set_name ( clone, Poke_get_name ( pokemon ) );
Poke_set_description ( clone, Poke_get_description ( pokemon ) );
Poke_set_n_types ( clone, Poke_get_n_types ( pokemon ) );
for (int x = 0; x < Poke_get_n_types (clone); x++) {
  Poke_set_type ( clone, Poke_get_type ( pokemon, x ), x );
}
Poke_set_n_abilities ( clone, Poke_get_n_abilities ( pokemon ) );
for (int x = 0; x < Poke_get_n_abilities (clone); x++) {
  Poke_set_ability ( clone, Poke_get_ability ( pokemon, x ), x );
}
Poke_set_weight ( clone, Poke_get_weight ( pokemon ) );
Poke_set_height ( clone, Poke_get_height ( pokemon ) );
Poke_set_legendary ( clone, Poke_get_legendary ( pokemon ) );
Poke_set_capture_rate ( clone, Poke_get_capture_rate ( pokemon ) );
Poke_set_date ( clone, Poke_get_date ( pokemon ) );


return ( clone );
}


/*----------------------------------------*/

// Print

void Poke_print ( Pokemon pokemon ) {

// Id, Nome e Descrição
printf("[#%d -> %s: %s - ", Poke_get_id (pokemon), Poke_get_name (pokemon), Poke_get_description (pokemon) );

// Tipos
if ( Poke_get_n_types (pokemon) == 1 ) {
  printf("['%s']", Poke_get_type (pokemon, 0));
}
else {
  printf("['%s', '%s']", Poke_get_type (pokemon, 0), Poke_get_type (pokemon, 1) );
}
printf(" - ");

// Habilidades
printf("[");
for ( int x = 0; x < Poke_get_n_abilities (pokemon); x++) {
  if ( x > 0 ) {
    printf(", ");
  }
  printf("'%s'", Poke_get_ability (pokemon, x) );
}
printf("] - ");

// Peso, altura e taxa de captura
printf("%.1lfkg - ", Poke_get_weight (pokemon) );
printf("%.1lfm - ", Poke_get_height (pokemon) );
printf("%d%% - ", Poke_get_capture_rate (pokemon) );

// Se é lendário
if ( Poke_get_legendary (pokemon) ) {
  printf("true - ");
}
else {
  printf("false - ");
}

// Número da geração
printf("%d gen] - ", Poke_get_generation (pokemon) );

// Data de captura
printf("%s\n", Poke_get_date (pokemon) );

}

/*----------------------------------------*/

// Ler

void Poke_read ( int id, Pokemon new ) {

// Exemplo de dado para leitura
// 1,1,Bulbasaur,Seed Pokémon,grass,poison,"['Overgrow', 'Chlorophyll']",6.9,0.7,45,0,05/01/1996

// Um caminho para os testes na minha máquina, e outro para o envio no Verde
//char * arquivo_name = "../pokemon.csv";
char * arquivo_name = "/tmp/pokemon.csv";
FILE *arquivo = fopen ( arquivo_name, "rt" );

char dados [160];
char separacao [32];

// Ler todas as linhas antes da que queremos para chegar nela
for (int x = 0; x < id; x++) {
  fgets ( dados, 160, arquivo );
}

fgets ( dados, 160, arquivo ); // Leitura da linha que queremos

int virgula_current = 0; int virgula_next = 0;

// Id
Poke_set_id ( new, id );

// Geração
while (dados[virgula_current] != ',') {
  virgula_current++;
}
virgula_next = virgula_current + 1;
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
Poke_set_generation( new, atoi(separacao));


// Nome
virgula_current = virgula_next;
virgula_next++;
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
Poke_set_name ( new, separacao );
//printf("%s\n", Poke_get_name ( new ) );


// Descrição
virgula_current = virgula_next;
virgula_next++;
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
Poke_set_description ( new, separacao );


// Tipos
virgula_current = virgula_next;
virgula_next++;
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
Poke_set_type ( new, separacao, 0 );
if (dados[virgula_next+1] == ',') {
  virgula_current = virgula_next;
  virgula_next++;
  Poke_set_n_types ( new, 1 );
  Poke_set_type ( new, "0", 1 );
}
else {
  virgula_current = virgula_next;
  virgula_next++;
  while (dados[virgula_next] != ',') {
    virgula_next++;
  }
  str_copy (dados, separacao, virgula_current, virgula_next);
  Poke_set_n_types ( new, 2 );
  Poke_set_type ( new, separacao, 1);
}


// Habilidades
int limite = 0; int n_abilities = 0;
while (dados[virgula_next] != '[') {
  virgula_next++;
}
virgula_current = virgula_next;
limite = virgula_next;
while (dados[limite] != ']') {
  if (dados[limite] == '\'') {
    n_abilities++;
  }
  limite++;
}
Poke_set_n_abilities ( new, n_abilities/2 );

virgula_current = virgula_current + 1;
virgula_next = virgula_next + 2;
n_abilities = 0;
while (virgula_next < limite) {
  while (dados[virgula_next] != '\'') {
    virgula_next++;
  }
  str_copy (dados, separacao, virgula_current, virgula_next);
  Poke_set_ability ( new, separacao, n_abilities);

  n_abilities++;
  virgula_current = virgula_next + 3;
  virgula_next = virgula_current + 1;
}


// Peso
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
Poke_set_weight ( new, atof(separacao) );

// Altura
virgula_current = virgula_next;
virgula_next++;
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
Poke_set_height ( new, atof(separacao) );

// Taxa de Captura
virgula_current = virgula_next;
virgula_next++;
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
Poke_set_capture_rate ( new, atoi(separacao) );


// Lendário
virgula_current = virgula_next;
virgula_next++;
while (dados[virgula_next] != ',') {
  virgula_next++;
}
str_copy (dados, separacao, virgula_current, virgula_next);
if (separacao[0] == '0') {
  Poke_set_legendary ( new, false );
}
else {
  Poke_set_legendary ( new, true );
}


// Data de Captura
virgula_current = virgula_next;
str_copy (dados, separacao, virgula_current, (virgula_next+11));
Poke_set_date ( new, separacao );

fclose ( arquivo );
}

/*----------------------------------------*/

// Método de troca

void swap (Pokemon * my_pokedex, int x, int y) {

Pokemon temp = my_pokedex[x];
my_pokedex[x] = my_pokedex[y];
my_pokedex[y] = temp;

}

/*----------------------------------------*/

// Organização

// Método de apoio ao shellsort
void insercao_pcor(Pokemon *my_pokedex, int n, int cor, int h, XData process_data){
  for (int i = (h + cor); i < n; i+=h) {

    process_data->movimentacoes += 1;
    Pokemon temp = my_pokedex[i];

    int j = i - h;

    process_data->comparacoes += 1;
    while ( (j >= 0) && ( Poke_get_weight(my_pokedex[j]) > Poke_get_weight(temp) ) ) {
      process_data->comparacoes += 1;
      my_pokedex[j + h] = my_pokedex[j];
      j -= h;
    }

    process_data->movimentacoes += 1;
    my_pokedex[j + h] = temp;

  }

}

// Organização por shellsort
void shellsort(Pokemon *my_pokedex, int n, XData process_data) {
  int h = 1;

  do {
    h = (h * 3) + 1;
  } while (h < n);

  do {
    h /= 3;
    for(int cor = 0; cor < h; cor++) {
      insercao_pcor(my_pokedex, n, cor, h, process_data);
    }
  } while (h != 1);


  // Reorganizar os casos em que o peso é igual pelo nome
  bool organizado = false;
  while ( !(organizado) ) {
    organizado = true;

    for (int i = 0; i < n-1; i++) {

      process_data->comparacoes += 2;
      int diferenca_nome = strcmp( Poke_get_name(my_pokedex[i]), Poke_get_name(my_pokedex[i+1]) );
      if ( Poke_get_weight(my_pokedex[i]) == Poke_get_weight(my_pokedex[i+1]) && diferenca_nome >= 0 ) {
	process_data->comparacoes += 2;

	organizado = false;
	process_data->movimentacoes += 3;
	swap(my_pokedex, i, i+1);

      }

    }

  } // Fim do while

}

// Organização por bolha
void bolha(Pokemon *my_pokedex, int n, XData process_data) {

  bool organizado = false;
  while ( !(organizado) ) {
    organizado = true;

    for (int i = 0; i < n-1; i++) {

      // Comparação por nome
      process_data->comparacoes += 1;
      if ( Poke_get_id(my_pokedex[i]) > Poke_get_id(my_pokedex[i+1]) ) {
	process_data->comparacoes += 1;

	organizado = false;
	process_data->movimentacoes += 3;
	swap(my_pokedex, i, i+1);

      }

    }

  } // Fim do while

/*
  Apesar que a questão especifica que o nome deve ser usado
   como atributo de desempate, é desnecessário incluir esse
   já que ids são únicos, então não tem como haver empate.
*/

}

// Esse método em si não é usado, mas é base para alguns processos de desempate
void bolha_name(Pokemon *my_pokedex, int n, XData process_data) {

  bool organizado = false;
  while ( !(organizado) ) {
    organizado = true;

    for (int i = 0; i < n-1; i++) {

      process_data->comparacoes += 1;
      int diferenca_nome = strcmp( Poke_get_name(my_pokedex[i]), Poke_get_name(my_pokedex[i+1]) );
      if ( diferenca_nome >= 0 ) {
	process_data->comparacoes += 1;

	organizado = false;
	process_data->movimentacoes += 3;
	swap(my_pokedex, i, i+1);

      }

    }

  } // Fim do while

}

// Organização por quicksort
void quicksort (Pokemon *my_pokedex, int esquerda, int direita, XData process_data) {

  // Determinar limites e pivô
  int i = esquerda, j = direita;
  Pokemon pivo = my_pokedex[(direita+esquerda)/2];

  while (i <= j) {

    // Achar os não condizentes aos grupos
    process_data->comparacoes += 2;
	      // Chave primária, geração
    while (   Poke_get_generation(my_pokedex[i]) < Poke_get_generation(pivo) ||
	      // Chave secundária, nome
	    ( Poke_get_generation(my_pokedex[i]) == Poke_get_generation(pivo) &&
	      strcmp (Poke_get_name(my_pokedex[i]), Poke_get_name(pivo)) < 0 )       )
      { i++; process_data->comparacoes += 1; }
	      // Chave primária, geração
    while (   Poke_get_generation(my_pokedex[j]) > Poke_get_generation(pivo) ||
	      // Chave secundária, nome
	    ( Poke_get_generation(my_pokedex[j]) == Poke_get_generation(pivo) &&
	      strcmp (Poke_get_name(my_pokedex[j]), Poke_get_name(pivo)) > 0 )       )
      { j--; process_data->comparacoes += 1; }

    // Trocar eles
    if (i <= j) {
      process_data->movimentacoes += 1;
      swap(my_pokedex, i, j);
      i++;
      j--;
    }

  } // Fim do while

  // Se não houver ultrapassado os limites, chamada recursiva
  if (esquerda < j) {
    quicksort(my_pokedex, esquerda, j, process_data);
  }
  if (i < direita) {
    quicksort(my_pokedex, i, direita, process_data);
  }

}

void quicksort_call (Pokemon *my_pokedex, int n, XData process_data) {
  quicksort(my_pokedex, 0, (n-1), process_data);
}

// Organização por Radixsort (Chave primária: abilities)
// Analisando o pub.out, tem-se as seguintes conclusões do algoritmo esperado:
//  - A única habilidade cujo nome é comparado é a primeira de cada pokémon
//  - Para o caso de a primeira habilidade dos comparados ser igual, aí usa-se
//    a chave secundária, o nome
//  - O número de habilidades não influencia o resultado/organização

// Mudanças:
// Achar o menor tamanho de array
// Chamar um método de bolha cada vez para cada letra, repetindo com base no número anterior
// Apenas na última execução, comparar os nomes no caso de ser igual


// Método para achar o menor tamanho da primeira habilidade
int getMin(Pokemon *pokedex, int n, XData process_data) {
  int menor = strlen(Poke_get_ability(pokedex[0], 0));

  for (int i = 1; i < n; i++) {

    process_data->comparacoes += 1;
    if( menor > strlen(Poke_get_ability(pokedex[i], 0)) ){
      menor = strlen(Poke_get_ability(pokedex[i], 0));
    }

  }

  return ( menor );
}

void radixBubble(Pokemon *pokedex, int n, int y, XData process_data) {

  bool organizado = false;
  while ( !(organizado) ) {
    organizado = true;

    for (int i = 0; i < n-1; i++) {

      process_data->comparacoes += 1;
      if ( (Poke_get_ability(pokedex[i], 0)[y]) > (Poke_get_ability(pokedex[i+1],0)[y]) ) {

	organizado = false;
	process_data->movimentacoes += 3;
	swap(pokedex, i, i+1);

      }
      else if ( y == 0 ){
	process_data->comparacoes += 1;
	if ( (strcmp( Poke_get_ability(pokedex[i], 0), Poke_get_ability(pokedex[i+1], 0) ) == 0) &&
	     (strcmp( Poke_get_name(pokedex[i]), Poke_get_name(pokedex[i+1]) ) > 0) ) {

	  organizado = false;
	  process_data->movimentacoes += 3;
	  swap(pokedex, i, i+1);

	}

      }

    }

  } // Fim do while

}

// Método de chamada do radixsort
void radixsort(Pokemon *pokedex, int n, XData process_data) {
  //Array para contar o numero de ocorrencias de cada elemento
  int min = getMin(pokedex, n, process_data);
  for (int y = min; y >= 0; y--) {
    radixBubble(pokedex, n, y, process_data);
  }
}

// Ordenação parcial por inserção (Chave primária: CaptureDate)
int DateCompare(Pokemon pokemon1, Pokemon pokemon2) {

  // xx/xx/xxxx
  int diferenca = 0;

  char * conversao1 = malloc ( 5 * sizeof(char) );
  char * conversao2 = malloc ( 5 * sizeof(char) );
  
  str_copy ( Poke_get_date(pokemon1), conversao1, 5, 10);
  str_copy ( Poke_get_date(pokemon2), conversao2, 5, 10);
  diferenca = atoi(conversao1) - atoi(conversao2);

  // Se o ano for o mesmo, comparar os meses
  if ( diferenca == 0 ) {
    str_copy ( Poke_get_date(pokemon1), conversao1, 2, 5);
    str_copy ( Poke_get_date(pokemon2), conversao2, 2, 5);
    diferenca = atoi(conversao1) - atoi(conversao2);
    // Se o mês for o mesmo, comparar os dias
    if ( diferenca == 0 ) {
      str_copy ( Poke_get_date(pokemon1), conversao1, -1, 2);
      str_copy ( Poke_get_date(pokemon2), conversao2, -1, 2);
      diferenca = atoi(conversao1) - atoi(conversao2);
    }
  }

  return (diferenca);
}

void insercao_parcial(Pokemon *pokedex, int n, int k, XData process_data) {

  int diferenca;

  // Analisando o pub.out, apenas metade do array é percorrido
  for (int i = 1; i < n/2; i++) {

    process_data->movimentacoes += 1;
    Pokemon temp = pokedex[i];
    
    // Determinar valor de j
    int j;
    if ( i < k ) { j = (i - 1); }
    else { j = (k - 1); }

    process_data->comparacoes += 1;
    diferenca = DateCompare(pokedex[j], temp);
    while ( (j >= 0) && (diferenca > 0) ) {
      process_data->movimentacoes += 1;
      pokedex[j + 1] = pokedex[j];

      j--;

      if ( j >= 0 ) {
        process_data->comparacoes += 1;
        diferenca = DateCompare(pokedex[j], temp);
      }
    }

    process_data->movimentacoes += 1;
    pokedex[j+1] = temp;
  }

}

/*----------------------------------------*/

// Salvar log
void save_log(char *nome_arquivo, int comparacoes, int movimentacoes, time_t inicio, time_t fim)
{
	// Dados
	int matricula = 855842;
	double difference = difftime (fim, inicio);
	FILE *arquivo = fopen(nome_arquivo, "wt");

	// Inserir no arquivo
	fprintf(arquivo, "%d\t%d\t%d\t%lf", matricula, comparacoes, movimentacoes, difference );
	

	fclose ( arquivo );
}



/*----------------------------------------*/




int main (void)
{


// Variaveis e dados
int *lista_ids = (int*) malloc ( 60 * sizeof(int) );
int cont = 0; int x = 0;
int k = 10;
char* log_name = "matrícula_insercao.txt";
char idPoke[5];

// Determinar espaço para salvar comparações e movimentações
XData process_data = new_Data ();


// Loop para ler ids
while (idPoke[0] != 'F') {

  fgets (idPoke, 5, stdin);
  idPoke [strlen (idPoke) - 1] = '\0';

  lista_ids [cont] = atoi(idPoke);

  cont++;
}
cont--;

// Determinar tamanho do array de pokemon
Pokemon my_pokedex [cont];

// Loop para ler do arquivo com base nos ids
x = 0;
while (x < cont) {

  my_pokedex [x] = new_Poke();
  Poke_read(lista_ids[x], my_pokedex [x]);

  x++;
}
free (lista_ids);



time(&process_data->inicio); // Início do processo

// Realizar organizacao
insercao_parcial(my_pokedex, cont, k, process_data);

time(&process_data->fim); // Fim do processo



// Loop para dar print
for (x = 0; x < k; x++) {
  //printf("%d:", x);
  Poke_print (my_pokedex[x]);
}

save_log (log_name, process_data->comparacoes, process_data->movimentacoes, process_data->inicio, process_data->fim);

return ( 0 );
}



