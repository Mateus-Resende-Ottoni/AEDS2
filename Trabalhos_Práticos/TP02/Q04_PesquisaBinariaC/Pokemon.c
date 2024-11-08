
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#include <string.h>
#include <locale.h>


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

// Um caminho para os testes na minha máquina, e outro para o envio no verde
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
char *lista_nomes[30];
int cont = 0; int cont2 = 0;
int x = 0;
char* log_name = "matrícula_binaria.txt";
char idPoke[6];
char *namePoke = (char*) malloc ( 24 * sizeof(char) );

// Determinar espaço para salvar comparações e movimentações
XData process_data = new_Data ();



// Loop para ler ids
while (idPoke[0] != 'F') {

  fgets (idPoke, 6, stdin);
  idPoke [strlen (idPoke) - 1] = '\0';

  lista_ids [cont] = atoi(idPoke);

  cont++;
}
cont--;


// Loop para ler nomes a serem procurados
while (namePoke[0] != 'F' || namePoke[1] != 'I' || namePoke[2] != 'M') {

  fgets (namePoke, 24, stdin);
  namePoke [strlen (namePoke) - 1] = '\0';

  lista_nomes [cont2] = (char*) malloc ( strlen(namePoke) + 1 );
  strcpy ( lista_nomes [cont2], namePoke);
  //printf("%d: %s\n", cont2, namePoke);

  cont2++;
}
cont2--;

// Teste dos nomes lidos
//for (x = 0; x < cont2; x++) {
//  printf("%s\n", lista_nomes[x]);
//}



// Determinar tamanho do array de pokemon e de booleans
Pokemon my_pokedex [cont];
bool search_results [cont2];

// Loop para ler do arquivo com base nos ids
x = 0;
while (x < cont) {

  my_pokedex [x] = new_Poke();
  Poke_read(lista_ids[x], my_pokedex [x]);

  x++;
}
free (lista_ids);


time(&process_data->inicio); // Iniciar timer
// Realizar organizacao
  for (x = 0; x < (cont - 1); x++) {
      int menor = x;

      for (int y = (x + 1); y < cont; y++){
	process_data->comparacoes++;
	if ( strcmp(Poke_get_name(my_pokedex[y]), (Poke_get_name(my_pokedex[menor])) ) < 0) {
	   menor = y;
	}

      }

      if (menor != x) {
	process_data->movimentacoes += 3;
	swap(my_pokedex, x, menor);
      }

  }

// Pesquisa binária
// Loop externo para os nomes a serem procurados
for (x = 0; x < cont2; x++) {

  bool found = false;
  int direita = (cont - 1), esquerda = 0, meio;
  int diferenca = 0;

  // Loop interno para conferir se o nome existe
  while (esquerda <= direita) {
    meio = (esquerda + direita) / 2;
    
    // Comparar
    process_data->comparacoes++;
    diferenca = strcmp(lista_nomes[x], Poke_get_name(my_pokedex[meio]));

    // Se tiver achado
    if ( diferenca == 0 ) {
      found = true;
      esquerda = direita + 1;
    }

    // Se o nome comparado vier antes
    else if ( diferenca > 0 ) {
      esquerda = meio + 1;
    }

    // Se o nome comparado vier depois
    else {
      direita = meio - 1;
    }

    // Teste
    //if ( x == 10 || x == 0 ) {
    //printf( "Looking for: %s. Found: %s\n", lista_nomes[x], Poke_get_name(my_pokedex[meio]) );
    //}

  }

  search_results[x] = found;

}

time(&process_data->fim); // Acabar timer

//printf("\nEl End\n");

// Loop para dar print nos resultados
for (x = 0; x < cont2; x++) {
  if (search_results[x]) {
    printf("SIM\n");
  }
  else {
    printf("NAO\n");
  }
}

save_log (log_name, process_data->comparacoes, process_data->movimentacoes, process_data->inicio, process_data->fim);

return ( 0 );
}



