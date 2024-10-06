
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
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


// Definição do tipo Pokemon
typedef              	
 struct Pokemon_struct  	
 {                   	
   int           id;      	
   int           generation;
   char         *name;
   char         *description;
   int           n_types;
   char         *types [2];
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
/*----------------------------------------*/


// Construtores

Pokemon new_Poke () {

int n = 24;

Pokemon novo = null;

novo = malloc ( 1 * sizeof (Poke) );

if ( novo ) {

  novo->id = 0;
  novo->generation = 0;

  novo->name = malloc ( n * sizeof (char) );
  novo->name = "Missingno";

  novo->description = malloc ( n * sizeof (char) );
  novo->description = "This is wrong";

  novo->n_types = 1;
  novo->types[0] = "Unknown";
  novo->types[1] = "0";

  novo->n_abilities = 1;
  novo->abilities = malloc ( (4*n) * sizeof (char) );
  novo->abilities[0] = "Crash the program";

  novo->weight = 0.0;
  novo->height = 0.0;

  novo->capture_rate = 0;
  novo->legendary = true;

  novo->date = malloc ( n * sizeof (char) );
  novo->date = "99/99/2012";


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
pokemon->name = malloc ( strlen(name) + 1 );
strcpy ( pokemon->name, name);
}

void Poke_set_description ( Pokemon pokemon, char * description ) {
pokemon->description = malloc ( strlen(description) + 1 );
strcpy ( pokemon->description, description);
}

void Poke_set_n_types ( Pokemon pokemon, int n_types ) {
pokemon->n_types = n_types;
}

void Poke_set_type ( Pokemon pokemon, char * type, int n ) {
pokemon->types[n] = malloc ( strlen(type) + 1 );
strcpy ( pokemon->types[n], type);
}

void Poke_set_n_abilities ( Pokemon pokemon, int n_abilities ) {
pokemon->n_abilities = n_abilities;
}

void Poke_set_ability ( Pokemon pokemon, char * ability, int n ) {
pokemon->abilities[n] = malloc ( strlen(ability) + 1 );
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
pokemon->date = malloc ( strlen(date) + 1 );
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
char * arquivo_name = "../pokemon.csv";
//char * arquivo_name = "/tmp/pokemon.csv";
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




int main (void)
{
// Variaveis e dados
Pokemon my_pokedex [80];
int lista_ids [80];
int cont = 0; int x = 0;
char idPoke[8];


// Loop para ler ids
while (idPoke[0] != 'F') {

  fgets (idPoke, 8, stdin);
  idPoke [strlen (idPoke) - 1] = '\0';

  lista_ids [cont] = atoi(idPoke);

  cont++;
}

// Loop para ler do arquivo com base nos ids
x = 0;
while (x < cont-1) {

  my_pokedex [x] = new_Poke();
  Poke_read(lista_ids[x], my_pokedex [x]);

  x++;
} 

// Loop para dar print
x = 0;
while (x < cont-1) {

  Poke_print (my_pokedex[x]);

  x++;
}


return ( 0 );
}



