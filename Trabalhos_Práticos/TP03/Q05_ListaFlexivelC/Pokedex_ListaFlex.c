
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
    // char * arquivo_name = "../pokemon.csv";
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

void setProximo(Celula *to_update, Celula *to_set)
{
    to_update->proximo = to_set;
}

void setPokemon(Celula *to_update, Pokemon to_set)
{
    to_update->pokemon = to_set;
}

Celula *getProximo(Celula *to_get)
{
    return (to_get->proximo);
}

Pokemon getPokemon(Celula *to_get)
{
    return (to_get->pokemon);
}

/*_________________________________________*/

/* Métodos para a struct Pokedex Flexível */

/* Definições */
// Definição do tipo Pokedex_Lista
typedef struct Pokedex_ListaFlex
{
    Celula *primeiro;
    Celula *ultimo;
} Pokedex_ListaFlex;

typedef Pokedex_ListaFlex *Pokedex_LF;
/* */

/* Construtores */
Pokedex_LF new_Pokedex_ListaFlex()
{

    Pokedex_LF nova = null;

    nova = malloc(1 * sizeof(Pokedex_LF));

    if (nova)
    {
        nova->primeiro = new_Celula(new_Poke());
        nova->ultimo = nova->primeiro;
    }

    return (nova);
}
/* */

/* Gets */

Celula *Dex_LF_get_primeiro(Pokedex_LF my_pokedex)
{
    return (my_pokedex->primeiro);
}

Celula *Dex_LF_get_ultimo(Pokedex_LF my_pokedex)
{
    return (my_pokedex->ultimo);
}

/* */

/* Sets */

void Dex_LF_set_primeiro(Pokedex_LF my_pokedex, Celula *new_one)
{
    my_pokedex->primeiro = new_one;
}

void Dex_LF_set_ultimo(Pokedex_LF my_pokedex, Celula *new_one)
{
    my_pokedex->ultimo = new_one;
}

/* */

/* Métodos de remoção */

Pokemon Dex_LF_RemoverInicio(Pokedex_LF my_pokedex)
{
    Pokemon removido;
    Celula *temp = Dex_LF_get_primeiro(my_pokedex);

    if (temp->proximo != null)
    {
        removido = getPokemon(temp->proximo);

        if (temp->proximo == Dex_LF_get_ultimo(my_pokedex))
        {
            Dex_LF_set_ultimo(my_pokedex, temp);
        }
        temp->proximo = temp->proximo->proximo;
    }

    temp = null;
    free(temp);

    return (removido);
}

Pokemon Dex_LF_RemoverFim(Pokedex_LF my_pokedex)
{
    Pokemon removido;
    Celula *temp = Dex_LF_get_primeiro(my_pokedex);

    if (temp->proximo != null)
    {
        while (temp->proximo != Dex_LF_get_ultimo(my_pokedex))
        {
            temp = temp->proximo;
        }

        removido = getPokemon(Dex_LF_get_ultimo(my_pokedex));

        Dex_LF_set_ultimo(my_pokedex, temp);
        temp->proximo = null;
    }

    temp = null;
    free(temp);

    return (removido);
}

Pokemon Dex_LF_Remover(Pokedex_LF my_pokedex, int pos)
{
    Pokemon removido;
    Celula *temp, *temp2;
    int x = 0;

    if (0 < pos)
    {
        temp = Dex_LF_get_primeiro(my_pokedex);
        for (x = 0; x < pos; x++)
        {
            temp = temp->proximo;
        }

        temp2 = temp->proximo;
        removido = getPokemon(temp2);

        temp->proximo = temp2->proximo;
        temp2->proximo = null;
    }

    temp = null;
    free(temp);
    temp2 = null;
    free(temp2);

    return (removido);
}

/* */

/* Métodos de inserção */

void Dex_LF_InserirInicio(Pokedex_LF my_pokedex, Pokemon to_set)
{
    Celula *temp = new_Celula(to_set);
    Celula *primeiro = Dex_LF_get_primeiro(my_pokedex);

    temp->proximo = primeiro->proximo;
    primeiro->proximo = temp;

    if (Dex_LF_get_ultimo(my_pokedex) == primeiro)
    {
        Dex_LF_set_ultimo(my_pokedex, temp);
    }

    temp = null;
    free(temp);
}

void Dex_LF_InserirFim(Pokedex_LF my_pokedex, Pokemon to_set)
{
    Celula *temp = new_Celula(to_set);
    Celula *ultimo = Dex_LF_get_ultimo(my_pokedex);

    ultimo->proximo = temp;
    Dex_LF_set_ultimo(my_pokedex, temp);

    ultimo = null;
    free(ultimo);
    temp = null;
    free(temp);
}

void Dex_LF_Inserir(Pokedex_LF my_pokedex, Pokemon to_set, int pos)
{
    Celula *temp, *temp2;
    int x = 0;

    if (0 < pos)
    {
        temp = Dex_LF_get_primeiro(my_pokedex);
        for (x = 0; x < pos; x++)
        {
            temp = temp->proximo;
        }

        temp2 = new_Celula(to_set);
        temp2->proximo = temp->proximo;
        temp->proximo = temp2;
    }

    temp = null;
    free(temp);
    temp2 = null;
    free(temp2);
}

/* */

/*_________________________________________*/

/* Método main */

void main(void)
{
    // Dados
    int *lista_ids = (int *)malloc(50 * sizeof(int));
    Pokedex_LF my_pokedex = new_Pokedex_ListaFlex();
    Pokemon to_insert;
    int cont = 0;
    int x = 0, y = 0;
    char idPoke[5];
    char posPoke[5];
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
        Dex_LF_InserirFim(my_pokedex, to_insert);

        x++;
    }
    free(lista_ids);

    // Ler número de inserções/remoções
    scanf("%d", &cont);

    // Remoções e inserções
    for (y = 0; y <= cont; y++)
    {

        fgets(operation, 12, stdin);
        // Inserções
        if (operation[0] == 'I')
        {

            // Definir com base no segundo caractere
            // Inserção Posicionada
            if (operation[1] == '*')
            {
                // Definir posição e ID
                int z = 3;
                while (operation[z] != ' ')
                {
                    z++;
                }
                str_copy(operation, idPoke, 2, z);
                posRead = atoi(idPoke);
                int w = z + 1;
                while (operation[w] != '\0')
                {
                    w++;
                }
                str_copy(operation, posPoke, z, w);
                idRead = atoi(posPoke);

                // Ler pokemon no arquivo
                Pokemon to_insert = new_Poke();
                Poke_read(idRead, to_insert);

                Dex_LF_Inserir(my_pokedex, to_insert, posRead);
            }
            else
            {
                // Definir ID
                int z = 3;
                while (operation[z] != '\0')
                {
                    z++;
                }
                str_copy(operation, idPoke, 2, z);
                idRead = atoi(idPoke);
                // printf("idRead: %d\n", idRead);

                // Ler pokemon no arquivo
                to_insert = new_Poke();
                Poke_read(idRead, to_insert);

                // Início
                if (operation[1] == 'I')
                {
                    Dex_LF_InserirInicio(my_pokedex, to_insert);
                }
                // Fim
                else if (operation[1] == 'F')
                {
                    Dex_LF_InserirFim(my_pokedex, to_insert);
                }
            }

            // printf("Insercao - fim\n");
        }
        // Remoções e print dos removidos
        else if (operation[0] == 'R')
        {

            // printf("Remocao - comeco\n");

            Pokemon removido;

            // Definir com base no segundo caractere
            // Remoção Posicionada
            if (operation[1] == '*')
            {
                // Definir posição
                int z = 3;
                while (operation[z] != '\0')
                {
                    z++;
                }
                str_copy(operation, posPoke, 2, z);
                posRead = atoi(posPoke);
                // printf("posRead: %d\n", posRead);

                removido = Dex_LF_Remover(my_pokedex, posRead);
            }
            else
            {
                // Início
                if (operation[1] == 'I')
                {
                    removido = Dex_LF_RemoverInicio(my_pokedex);
                }
                // Fim
                else if (operation[1] == 'F')
                {
                    removido = Dex_LF_RemoverFim(my_pokedex);
                }
            }

            printf("(R) %s\n", Poke_get_name(removido));

            // printf("Remocao - fim\n");
        }
    }

    // Print da lista resultante
    Celula *temp = Dex_LF_get_primeiro(my_pokedex)->proximo;
    y = 0;
    while (temp != null)
    {
        printf("[%d] ", y);
        Poke_print(getPokemon(temp));

        y++;
        temp = temp->proximo;
    }
    temp = null;
    free(temp);
    free(my_pokedex);
}

/*_________________________________________*/