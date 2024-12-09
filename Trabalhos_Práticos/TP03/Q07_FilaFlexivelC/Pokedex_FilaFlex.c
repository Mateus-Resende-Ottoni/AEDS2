
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>
#include <string.h>

#define null NULL

/* Métodos gerais */

/*----------------------------------------*/
void str_copy(char *original, char *new, int init, int end)
{

    int y = 0;

    for (int x = init + 1; x < end; x++)
    {
        new[y] = original[x];
        y++;
    }
    new[y] = '\0';
}
/*----------------------------------------*/

/*_________________________________________*/

/* Métodos para a struct Pokemon */

// Definição do tipo Pokemon
typedef struct Pokemon_struct
{
    int id;
    int generation;
    char *name;
    char *description;
    int n_types;
    char *types[2];
    int n_abilities;
    char **abilities;
    double weight;
    double height;
    int capture_rate;
    bool legendary;
    char *date;
} Poke;

typedef Poke *Pokemon;

/* Construtores */
Pokemon new_Poke()
{

    Pokemon novo = null;

    novo = malloc(1 * sizeof(Poke));

    if (novo)
    {

        novo->id = 0;
        novo->generation = 0;

        novo->name = (char *)malloc(strlen("Missingno") + 1);
        strcpy(novo->name, "Missingno");

        novo->description = (char *)malloc(strlen("This is wrong") + 1);
        novo->description = "This is wrong";

        novo->n_types = 1;
        novo->types[0] = malloc(strlen("Unknown") + 1);
        novo->types[1] = malloc(strlen("Unow") + 1);
        novo->types[0] = "Unknown";
        novo->types[1] = "0";

        novo->n_abilities = 1;
        novo->abilities = malloc(4 * (strlen("Program Crash") + 1));
        novo->abilities[0] = "Program Crash";

        novo->weight = 0.0;
        novo->height = 0.0;

        novo->capture_rate = 0;
        novo->legendary = true;

        novo->date = (char *)malloc(strlen("99/99/2012") + 1);
        novo->date = "99/99/2012";
    }

    return (novo);
}
/* */

/* Sets */
void Poke_set_id(Pokemon pokemon, int id)
{
    pokemon->id = id;
}

void Poke_set_generation(Pokemon pokemon, int generation)
{
    pokemon->generation = generation;
}

void Poke_set_name(Pokemon pokemon, char *name)
{
    pokemon->name = (char *)malloc(strlen(name) + 1);
    strcpy(pokemon->name, name);
}

void Poke_set_description(Pokemon pokemon, char *description)
{
    pokemon->description = (char *)malloc(strlen(description) + 1);
    strcpy(pokemon->description, description);
}

void Poke_set_n_types(Pokemon pokemon, int n_types)
{
    pokemon->n_types = n_types;
}

void Poke_set_type(Pokemon pokemon, char *type, int n)
{
    pokemon->types[n] = (char *)malloc(strlen(type) + 1);
    strcpy(pokemon->types[n], type);
}

void Poke_set_n_abilities(Pokemon pokemon, int n_abilities)
{
    pokemon->n_abilities = n_abilities;
}

void Poke_set_ability(Pokemon pokemon, char *ability, int n)
{
    pokemon->abilities[n] = (char *)malloc(strlen(ability) + 1);
    strcpy(pokemon->abilities[n], ability);
}

void Poke_set_weight(Pokemon pokemon, double weight)
{
    pokemon->weight = weight;
}

void Poke_set_height(Pokemon pokemon, double height)
{
    pokemon->height = height;
}

void Poke_set_capture_rate(Pokemon pokemon, int capture_rate)
{
    pokemon->capture_rate = capture_rate;
}

void Poke_set_legendary(Pokemon pokemon, bool legendary)
{
    pokemon->legendary = legendary;
}

void Poke_set_date(Pokemon pokemon, char *date)
{
    pokemon->date = (char *)malloc(strlen(date) + 1);
    strcpy(pokemon->date, date);
}
/* */

/* Gets */

int Poke_get_id(Pokemon pokemon)
{
    return (pokemon->id);
}

int Poke_get_generation(Pokemon pokemon)
{
    return (pokemon->generation);
}

char *Poke_get_name(Pokemon pokemon)
{
    return (pokemon->name);
}

char *Poke_get_description(Pokemon pokemon)
{
    return (pokemon->description);
}

int Poke_get_n_types(Pokemon pokemon)
{
    return (pokemon->n_types);
}

char *Poke_get_type(Pokemon pokemon, int n)
{
    return (pokemon->types[n]);
}

int Poke_get_n_abilities(Pokemon pokemon)
{
    return (pokemon->n_abilities);
}

char *Poke_get_ability(Pokemon pokemon, int n)
{
    return (pokemon->abilities[n]);
}

double Poke_get_weight(Pokemon pokemon)
{
    return (pokemon->weight);
}

double Poke_get_height(Pokemon pokemon)
{
    return (pokemon->height);
}

int Poke_get_capture_rate(Pokemon pokemon)
{
    return (pokemon->capture_rate);
}

bool Poke_get_legendary(Pokemon pokemon)
{
    return (pokemon->legendary);
}

char *Poke_get_date(Pokemon pokemon)
{
    return (pokemon->date);
}
/* */

/* Clone */
Pokemon Poke_clone(Pokemon pokemon)
{

    Pokemon clone = null;

    Poke_set_id(clone, Poke_get_id(pokemon));
    Poke_set_generation(clone, Poke_get_generation(pokemon));
    Poke_set_name(clone, Poke_get_name(pokemon));
    Poke_set_description(clone, Poke_get_description(pokemon));
    Poke_set_n_types(clone, Poke_get_n_types(pokemon));
    for (int x = 0; x < Poke_get_n_types(clone); x++)
    {
        Poke_set_type(clone, Poke_get_type(pokemon, x), x);
    }
    Poke_set_n_abilities(clone, Poke_get_n_abilities(pokemon));
    for (int x = 0; x < Poke_get_n_abilities(clone); x++)
    {
        Poke_set_ability(clone, Poke_get_ability(pokemon, x), x);
    }
    Poke_set_weight(clone, Poke_get_weight(pokemon));
    Poke_set_height(clone, Poke_get_height(pokemon));
    Poke_set_legendary(clone, Poke_get_legendary(pokemon));
    Poke_set_capture_rate(clone, Poke_get_capture_rate(pokemon));
    Poke_set_date(clone, Poke_get_date(pokemon));

    return (clone);
}
/* */

/* Print */
void Poke_print(Pokemon pokemon)
{

    // Id, Nome e Descrição
    printf("[#%d -> %s: %s - ", Poke_get_id(pokemon), Poke_get_name(pokemon), Poke_get_description(pokemon));

    // Tipos
    if (Poke_get_n_types(pokemon) == 1)
    {
        printf("['%s']", Poke_get_type(pokemon, 0));
    }
    else
    {
        printf("['%s', '%s']", Poke_get_type(pokemon, 0), Poke_get_type(pokemon, 1));
    }
    printf(" - ");

    // Habilidades
    printf("[");
    for (int x = 0; x < Poke_get_n_abilities(pokemon); x++)
    {
        if (x > 0)
        {
            printf(", ");
        }
        printf("'%s'", Poke_get_ability(pokemon, x));
    }
    printf("] - ");

    // Peso, altura e taxa de captura
    printf("%.1lfkg - ", Poke_get_weight(pokemon));
    printf("%.1lfm - ", Poke_get_height(pokemon));
    printf("%d%% - ", Poke_get_capture_rate(pokemon));

    // Se é lendário
    if (Poke_get_legendary(pokemon))
    {
        printf("true - ");
    }
    else
    {
        printf("false - ");
    }

    // Número da geração
    printf("%d gen] - ", Poke_get_generation(pokemon));

    // Data de captura
    printf("%s\n", Poke_get_date(pokemon));
}
/* */

/* Read/Leitura */
void Poke_read(int id, Pokemon new)
{

    // Exemplo de dado para leitura
    // 1,1,Bulbasaur,Seed Pokémon,grass,poison,"['Overgrow', 'Chlorophyll']",6.9,0.7,45,0,05/01/1996

    // Um caminho para os testes na minha máquina, e outro para o envio no Verde
    // char *arquivo_name = "../pokemon.csv";
    char *arquivo_name = "/tmp/pokemon.csv";
    FILE *arquivo = fopen(arquivo_name, "rt");

    char dados[160];
    char separacao[32];

    // Ler todas as linhas antes da que queremos para chegar nela
    for (int x = 0; x < id; x++)
    {
        fgets(dados, 160, arquivo);
    }

    fgets(dados, 160, arquivo); // Leitura da linha que queremos

    int virgula_current = 0;
    int virgula_next = 0;

    // Id
    Poke_set_id(new, id);

    // Geração
    while (dados[virgula_current] != ',')
    {
        virgula_current++;
    }
    virgula_next = virgula_current + 1;
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    Poke_set_generation(new, atoi(separacao));

    // Nome
    virgula_current = virgula_next;
    virgula_next++;
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    Poke_set_name(new, separacao);
    // printf("%s\n", Poke_get_name ( new ) );

    // Descrição
    virgula_current = virgula_next;
    virgula_next++;
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    Poke_set_description(new, separacao);

    // Tipos
    virgula_current = virgula_next;
    virgula_next++;
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    Poke_set_type(new, separacao, 0);
    if (dados[virgula_next + 1] == ',')
    {
        virgula_current = virgula_next;
        virgula_next++;
        Poke_set_n_types(new, 1);
        Poke_set_type(new, "0", 1);
    }
    else
    {
        virgula_current = virgula_next;
        virgula_next++;
        while (dados[virgula_next] != ',')
        {
            virgula_next++;
        }
        str_copy(dados, separacao, virgula_current, virgula_next);
        Poke_set_n_types(new, 2);
        Poke_set_type(new, separacao, 1);
    }

    // Habilidades
    int limite = 0;
    int n_abilities = 0;
    while (dados[virgula_next] != '[')
    {
        virgula_next++;
    }
    virgula_current = virgula_next;
    limite = virgula_next;
    while (dados[limite] != ']')
    {
        if (dados[limite] == '\'')
        {
            n_abilities++;
        }
        limite++;
    }
    Poke_set_n_abilities(new, n_abilities / 2);

    virgula_current = virgula_current + 1;
    virgula_next = virgula_next + 2;
    n_abilities = 0;
    while (virgula_next < limite)
    {
        while (dados[virgula_next] != '\'')
        {
            virgula_next++;
        }
        str_copy(dados, separacao, virgula_current, virgula_next);
        Poke_set_ability(new, separacao, n_abilities);

        n_abilities++;
        virgula_current = virgula_next + 3;
        virgula_next = virgula_current + 1;
    }

    // Peso
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    Poke_set_weight(new, atof(separacao));

    // Altura
    virgula_current = virgula_next;
    virgula_next++;
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    Poke_set_height(new, atof(separacao));

    // Taxa de Captura
    virgula_current = virgula_next;
    virgula_next++;
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    Poke_set_capture_rate(new, atoi(separacao));

    // Lendário
    virgula_current = virgula_next;
    virgula_next++;
    while (dados[virgula_next] != ',')
    {
        virgula_next++;
    }
    str_copy(dados, separacao, virgula_current, virgula_next);
    if (separacao[0] == '0')
    {
        Poke_set_legendary(new, false);
    }
    else
    {
        Poke_set_legendary(new, true);
    }

    // Data de Captura
    virgula_current = virgula_next;
    str_copy(dados, separacao, virgula_current, (virgula_next + 11));
    Poke_set_date(new, separacao);

    fclose(arquivo);
}
/* */

/*_________________________________________*/

/* Métodos para a struct XData */

// Struct para salvar dados acerca da execução
typedef struct Execution
{
    int comparacoes;
    int movimentacoes;
    time_t inicio;
    time_t fim;
} Exe_Data;

typedef Exe_Data *XData;

/* Construtores */
XData new_Data()
{

    XData novo = null;
    novo = malloc(1 * sizeof(Exe_Data));

    if (novo)
    {

        novo->comparacoes = 0;
        novo->movimentacoes = 0;
        time(&novo->inicio);
        time(&novo->fim);
    }

    return (novo);
}
/* */

/*_________________________________________*/

/* Métodos para a struct Celula Pokemon */

typedef struct Celula
{
    Pokemon pokemon;
    struct Celula *proximo;
} Celula;

Celula *new_Celula(Pokemon pokemon)
{
    Celula *temp = (Celula *)malloc(sizeof(Celula));
    temp->pokemon = pokemon;
    temp->proximo = NULL;
    return temp;
}

void celula_setProximo(Celula *to_update, Celula *to_set)
{
    to_update->proximo = to_set;
}

void celula_setPokemon(Celula *to_update, Pokemon to_set)
{
    to_update->pokemon = to_set;
}

Celula *celula_getProximo(Celula *to_get)
{
    return (to_get->proximo);
}

Pokemon celula_getPokemon(Celula *to_get)
{
    return (to_get->pokemon);
}

/*_________________________________________*/

/* Métodos para a struct Pokedex Flexível */

/* Definições */
// Definição do tipo Pokedex_Fila
typedef struct Pokedex_FilaFlex
{
    Celula *primeiro;
    Celula *ultimo;
    int length;
} Pokedex_FilaFlex;

typedef Pokedex_FilaFlex *Pokedex_FF;
/* */

/* Construtores */
Pokedex_FF new_Pokedex_FilaFlex(int length)
{

    Pokedex_FF nova = null;

    nova = malloc(1 * sizeof(Pokedex_FF));

    if (nova)
    {
        nova->length = length + 1;
        nova->primeiro = new_Celula(new_Poke());
        nova->ultimo = nova->primeiro;

        for (int x = 1; x < nova->length; x++)
        {
            nova->primeiro->proximo = new_Celula(new_Poke());
            nova->primeiro = nova->primeiro->proximo;
        }
        nova->primeiro->proximo = nova->ultimo;
        nova->primeiro = nova->primeiro->proximo;
    }

    return (nova);
}
/* */

/* Gets */

int Dex_FF_get_length(Pokedex_FF my_pokedex)
{
    return (my_pokedex->length);
}

Celula *Dex_FF_get_primeiro(Pokedex_FF my_pokedex)
{
    return (my_pokedex->primeiro);
}

Celula *Dex_FF_get_ultimo(Pokedex_FF my_pokedex)
{
    return (my_pokedex->ultimo);
}

/* */

/* Sets */

void Dex_FF_set_primeiro(Pokedex_FF my_pokedex, Celula *new_one)
{
    my_pokedex->primeiro = new_one;
}

void Dex_FF_set_ultimo(Pokedex_FF my_pokedex, Celula *new_one)
{
    my_pokedex->ultimo = new_one;
}

/* */

/* Método de remoção */

Pokemon Dex_FF_Remover(Pokedex_FF my_pokedex)
{
    Pokemon removido;
    Celula *primeiro = Dex_FF_get_primeiro(my_pokedex);
    Celula *ultimo = Dex_FF_get_ultimo(my_pokedex);

    if (primeiro != ultimo)
    {
        removido = celula_getPokemon(primeiro);

        Dex_FF_set_primeiro(my_pokedex, primeiro->proximo);
    }

    primeiro = null;
    free(primeiro);
    ultimo = null;
    free(ultimo);

    return (removido);
}

/* */

/* Método de inserção */

void Dex_FF_Inserir(Pokedex_FF my_pokedex, Pokemon to_set)
{
    Celula *primeiro = Dex_FF_get_primeiro(my_pokedex);
    Celula *ultimo = Dex_FF_get_ultimo(my_pokedex);

    if (ultimo->proximo == primeiro)
    {
        Dex_FF_Remover(my_pokedex);
    }

    celula_setPokemon(ultimo, to_set);
    Dex_FF_set_ultimo(my_pokedex, ultimo->proximo);


    ultimo = null;
    free(ultimo);
    primeiro = null;
    free(primeiro);
}

/* */

/* Média de taxa de captura */

int Dex_FF_Median_CR(Pokedex_FF my_pokedex)
{
    Celula *primeiro = Dex_FF_get_primeiro(my_pokedex);
    Celula *i = primeiro;
    Celula *ultimo = Dex_FF_get_ultimo(my_pokedex);
    int capture_rate = 0;
    int n = 0;
    int sum = 0;
    int median = 0;
    double resto = 0.0;

    while (i != ultimo)
    {
        capture_rate = Poke_get_capture_rate(celula_getPokemon(i));
        sum = sum + capture_rate;
        n++;
        i = i->proximo;
    }

    if (n > 0)
    {
        median = sum / n;

        // Cálculo para saber se o valor deve ser arredondado para cima
        resto = (sum * 1.0 / n * 1.0) - median;
        if (resto >= 0.5)
        {
            median++;
        }
    }

    return (median);
}

void Dex_FF_Median_CR_print(Pokedex_FF my_pokedex)
{
    int median = Dex_FF_Median_CR(my_pokedex);
    printf("Média: %d\n", median);
}

/* */

/*_________________________________________*/

/* Método main */

void main(void)
{
    // Dados
    int *lista_ids = (int *)malloc(50 * sizeof(int));
    Pokedex_FF my_pokedex = new_Pokedex_FilaFlex(5);
    Pokemon to_insert;
    int cont = 0;
    int x = 0, y = 0;
    char idPoke[5];
    char operation[12];
    int idRead, posRead;

    // Loop para ler ids
    while (idPoke[0] != 'F')
    {

        fgets(idPoke, 5, stdin);
        idPoke[strlen(idPoke) - 1] = '\0';

        lista_ids[cont] = atoi(idPoke);

        cont++;
    }
    cont--;

    // Loop para ler do arquivo com base nos ids
    x = 0;
    while (x < cont)
    {

        to_insert = new_Poke();
        Poke_read(lista_ids[x], to_insert);
        Dex_FF_Inserir(my_pokedex, to_insert);
        Dex_FF_Median_CR_print(my_pokedex);

        x++;
    }
    free(lista_ids);

    // Ler número de inserções/remoções
    scanf("%d", &cont);

    // Remoções e inserções
    for (y = 0; y <= cont; y++)
    {

        fgets(operation, 12, stdin);
        // Inserção
        if (operation[0] == 'I')
        {
            // Definir ID
            int z = 2;
            while (operation[z] != '\0')
            {
                z++;
            }
            str_copy(operation, idPoke, 1, z);
            idRead = atoi(idPoke);

            // Ler pokemon no arquivo
            to_insert = new_Poke();
            Poke_read(idRead, to_insert);

            Dex_FF_Inserir(my_pokedex, to_insert);
            Dex_FF_Median_CR_print(my_pokedex);
        }
        // Remoção e print do removido
        else if (operation[0] == 'R')
        {
            Pokemon removido;

            removido = Dex_FF_Remover(my_pokedex);

            printf("(R) %s\n", Poke_get_name(removido));
        }
    }

    // Print da lista resultante
    Celula *temp = Dex_FF_get_primeiro(my_pokedex);
    Celula *ultimo = Dex_FF_get_ultimo(my_pokedex);
    y = 0;
    printf("\n");
    while (temp != ultimo)
    {
        printf("[%d] ", y);
        Poke_print(celula_getPokemon(temp));

        y++;
        temp = temp->proximo;
    }
    temp = null;
    free(temp);
    ultimo = null;
    free(ultimo);
    free(my_pokedex);
}

/*_________________________________________*/