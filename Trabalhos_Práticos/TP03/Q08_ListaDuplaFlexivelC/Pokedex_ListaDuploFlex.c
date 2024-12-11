
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

/*_________________________________________*/

/* Métodos para a struct ExeData */

typedef struct ExeData
{
    char *arquivo;
    int matricula;
    int comparacoes;
    int movimentacoes;
    time_t inicio;
    time_t fim;
    struct tm inicio_s;
    struct tm fim_s;
} ExeData;

typedef ExeData *XData;

/* Construtor */

void set_Inicio(XData log) {
    log->inicio = time(null);
    log->inicio_s = *localtime(&(log->inicio));
}

void set_Fim(XData log) {
    log->fim = time(null);
    log->fim_s = *localtime(&(log->fim));
}

XData new_Data(char *nome, int num_matricula)
{

    XData novo = null;
    novo = malloc(1 * sizeof(ExeData));

    if (novo)
    {
        novo->arquivo = nome;
        novo->matricula = num_matricula;
        novo->comparacoes = 0;
        novo->movimentacoes = 0;
        set_Inicio(novo);
        set_Fim(novo);
    }

    return (novo);
}

/* */

void aumentar_comparacoes(XData log, int aumento)
{
    int num = log->comparacoes;
    num = num + aumento;
    log->comparacoes = num;
}

void aumentar_movimentacoes(XData log, int aumento)
{
    int num = log->movimentacoes;
    num = num + aumento;
    log->movimentacoes = num;
}

// Salvar log
void save_log(XData log)
{
    // Dados
    log->inicio     = mktime(&(log->inicio_s));
    log->fim        = mktime(&(log->fim_s));
    double difference = difftime(log->fim, log->inicio);

    FILE *arquivo = fopen(log->arquivo, "wt");

    // Inserir no arquivo
    fprintf(arquivo, "%d\t%d\t%d\t%lf", log->matricula, log->comparacoes, log->movimentacoes, difference);

    fclose(arquivo);
}

/*----------------------------------------*/

/*_________________________________________*/

/* Métodos para a struct Celula Dupla Pokemon */

typedef struct CelulaDupla
{
    Pokemon pokemon;
    struct CelulaDupla *proximo;
    struct CelulaDupla *anterior;
} CelulaDupla;

CelulaDupla *new_CelulaDupla(Pokemon pokemon)
{
    CelulaDupla *temp = (CelulaDupla *)malloc(sizeof(CelulaDupla));
    temp->pokemon = pokemon;
    temp->proximo = NULL;
    return temp;
}

void celula_setProximo(CelulaDupla *to_update, CelulaDupla *to_set)
{
    to_update->proximo = to_set;
}

void celula_setAnterior(CelulaDupla *to_update, CelulaDupla *to_set)
{
    to_update->anterior = to_set;
}

void celula_setPokemon(CelulaDupla *to_update, Pokemon to_set)
{
    to_update->pokemon = to_set;
}

CelulaDupla *celula_getProximo(CelulaDupla *to_get)
{
    return (to_get->proximo);
}

CelulaDupla *celula_getAnterior(CelulaDupla *to_get)
{
    return (to_get->anterior);
}

Pokemon celula_getPokemon(CelulaDupla *to_get)
{
    return (to_get->pokemon);
}

/*_________________________________________*/

/* Métodos para a struct Pokedex Duplo Flexível */

/* Definições */
// Definição do tipo Pokedex_ListaDuploFlex
typedef struct Pokedex_ListaDuploFlex
{
    CelulaDupla *primeiro;
    CelulaDupla *ultimo;
} Pokedex_ListaDuploFlex;

typedef Pokedex_ListaDuploFlex *Pokedex_LDF;
/* */

/* Construtores */
Pokedex_LDF new_Pokedex_ListaDuploFlex()
{

    Pokedex_LDF nova = null;

    nova = malloc(1 * sizeof(Pokedex_LDF));

    if (nova)
    {
        nova->primeiro = new_CelulaDupla(new_Poke());
        nova->ultimo = nova->primeiro;
    }

    return (nova);
}
/* */

/* Gets */

CelulaDupla *Dex_LDF_get_primeiro(Pokedex_LDF my_pokedex)
{
    return (my_pokedex->primeiro);
}

CelulaDupla *Dex_LDF_get_ultimo(Pokedex_LDF my_pokedex)
{
    return (my_pokedex->ultimo);
}

/* */

/* Sets */

void Dex_LDF_set_primeiro(Pokedex_LDF my_pokedex, CelulaDupla *new_one)
{
    my_pokedex->primeiro = new_one;
}

void Dex_LDF_set_ultimo(Pokedex_LDF my_pokedex, CelulaDupla *new_one)
{
    my_pokedex->ultimo = new_one;
}

/* */

/* Outros */

int Dex_LDF_tamanho(Pokedex_LDF my_pokedex)
{
    int tamanho = 0;
    CelulaDupla *i = Dex_LDF_get_primeiro(my_pokedex);
    CelulaDupla *ultimo = Dex_LDF_get_ultimo(my_pokedex);

    for (i; i != ultimo; i = i->proximo)
    {
        tamanho++;
    }

    return tamanho;
}

CelulaDupla *Dex_LDF_getCelulaPos(Pokedex_LDF my_pokedex, int pos)
{
    CelulaDupla *resp = celula_getProximo(Dex_LDF_get_primeiro(my_pokedex));
    int length = Dex_LDF_tamanho(my_pokedex);

    if (0 < pos && pos < length)
    {
        for (int x = 0; x < pos; x++)
        {
            resp = celula_getProximo(resp);
        }
    }

    return (resp);
}

void celula_swap(CelulaDupla *pkmn1, CelulaDupla *pkmn2)
{

    Pokemon temp = celula_getPokemon(pkmn1);
    celula_setPokemon(pkmn1, celula_getPokemon(pkmn2));
    celula_setPokemon(pkmn2, temp);

    temp = null;
}

// Retorna se o nome vem antes
bool nameCompare(CelulaDupla *pokemon1, CelulaDupla *pokemon2)
{
    bool antes = true;
    int a = 0;
    int b = 0;
    char *nome1 = Poke_get_name(celula_getPokemon(pokemon1));
    char *nome2 = Poke_get_name(celula_getPokemon(pokemon2));
    int length1 = strlen(nome1);
    int length2 = strlen(nome2);
    int diferenca = 0;
    while (a < length1 &&
           b < length2 &&
           diferenca == 0)
    {

        // Usar a diferença dos caracteres para definir (caso sejam diferentes)
        // qual é primeiro na ordem alfabética
        diferenca = nome1[a] - nome2[b];

        a++;
        b++;
    }

    // Se houver diferença nos caracteres
    if (diferenca != 0)
    {
        if (diferenca > 0)
        {
            antes = false;
        }
    }
    // Se não houver, comparar o tamanho (menor vem primeiro)
    else
    {
        if (b == length2)
            antes = false;
    }

    return antes;
}

/* */

/* Métodos de remoção */

Pokemon Dex_LDF_RemoverInicio(Pokedex_LDF my_pokedex)
{
    Pokemon removido;
    CelulaDupla *temp = Dex_LDF_get_primeiro(my_pokedex);
    CelulaDupla *temp2 = celula_getProximo(temp);

    if (celula_getProximo(temp) != null)
    {
        removido = celula_getPokemon(temp2);

        if (temp2 == Dex_LDF_get_ultimo(my_pokedex))
        {
            Dex_LDF_set_ultimo(my_pokedex, temp);
        }

        celula_setProximo(temp, celula_getProximo(temp2));
        celula_setAnterior(celula_getProximo(temp2), temp);

        celula_setProximo(temp2, null);
        celula_setAnterior(temp2, null);
    }

    temp = null;
    free(temp);
    temp2 = null;
    free(temp2);

    return (removido);
}

Pokemon Dex_LDF_RemoverFim(Pokedex_LDF my_pokedex)
{
    Pokemon removido;
    CelulaDupla *temp = Dex_LDF_get_ultimo(my_pokedex);
    CelulaDupla *temp2 = celula_getAnterior(temp);

    if (celula_getProximo(temp) != null)
    {
        removido = celula_getPokemon(temp);

        Dex_LDF_set_ultimo(my_pokedex, temp2);

        celula_setProximo(temp2, null);
        celula_setProximo(temp, null);
        celula_setAnterior(temp, null);
    }

    temp = null;
    free(temp);
    temp2 = null;
    free(temp2);

    return (removido);
}

Pokemon Dex_LDF_Remover(Pokedex_LDF my_pokedex, int pos)
{
    Pokemon removido;
    CelulaDupla *temp, *temp2;
    int x = 0;

    if (0 < pos)
    {
        temp = Dex_LDF_get_primeiro(my_pokedex);
        for (x = 0; x < pos; x++)
        {
            temp = celula_getProximo(temp);
        }

        temp2 = celula_getProximo(temp);
        removido = celula_getPokemon(temp2);

        celula_setProximo(temp, celula_getProximo(temp2));
        celula_setAnterior(celula_getProximo(temp2), temp);

        celula_setProximo(temp2, null);
        celula_setAnterior(temp2, null);
    }

    temp = null;
    free(temp);
    temp2 = null;
    free(temp2);

    return (removido);
}

/* */

/* Métodos de inserção */

void Dex_LDF_InserirInicio(Pokedex_LDF my_pokedex, Pokemon to_set)
{
    CelulaDupla *temp = new_CelulaDupla(to_set);
    CelulaDupla *primeiro = Dex_LDF_get_primeiro(my_pokedex);

    celula_setProximo(temp, celula_getProximo(primeiro));
    celula_setAnterior(celula_getProximo(temp), temp);
    celula_setProximo(primeiro, temp);

    if (Dex_LDF_get_ultimo(my_pokedex) == primeiro)
    {
        Dex_LDF_set_ultimo(my_pokedex, temp);
    }

    temp = null;
    free(temp);
}

void Dex_LDF_InserirFim(Pokedex_LDF my_pokedex, Pokemon to_set)
{
    CelulaDupla *temp = new_CelulaDupla(to_set);
    CelulaDupla *ultimo = Dex_LDF_get_ultimo(my_pokedex);

    celula_setProximo(ultimo, temp);
    celula_setAnterior(temp, ultimo);
    Dex_LDF_set_ultimo(my_pokedex, temp);

    ultimo = null;
    free(ultimo);
    temp = null;
    free(temp);
}

void Dex_LDF_Inserir(Pokedex_LDF my_pokedex, Pokemon to_set, int pos)
{
    CelulaDupla *temp, *temp2;
    int x = 0;

    if (0 < pos)
    {
        temp = Dex_LDF_get_primeiro(my_pokedex);
        for (x = 0; x < pos; x++)
        {
            temp = celula_getProximo(temp);
        }

        temp2 = new_CelulaDupla(to_set);
        celula_setProximo(temp2, celula_getProximo(temp));
        celula_setAnterior(temp2, temp);

        celula_setAnterior(celula_getProximo(temp), temp2);
        celula_setProximo(temp, temp2);
    }

    temp = null;
    free(temp);
    temp2 = null;
    free(temp2);
}

/* */

/* Organização */
void Dex_LDF_quicksort(Pokedex_LDF my_pokedex, int esquerda, int direita, XData process_data)
{
    // Determinar limites e pivô
    int i = esquerda, j = direita;
    int pivo = (esquerda + direita) / 2;

    CelulaDupla *pokemon_i = Dex_LDF_getCelulaPos(my_pokedex, i);
    CelulaDupla *pokemon_j = Dex_LDF_getCelulaPos(my_pokedex, j);

    CelulaDupla *pokemon_pivo = Dex_LDF_getCelulaPos(my_pokedex, pivo);
    int generation_pivo = Poke_get_generation(celula_getPokemon(pokemon_pivo));

    while (i <= j)
    {
        // Achar os não condizentes aos grupos
        aumentar_comparacoes(process_data, 1);
        while ( ( Poke_get_generation(celula_getPokemon(pokemon_i)) < generation_pivo) ||
                ( Poke_get_generation(celula_getPokemon(pokemon_i)) == generation_pivo &&
                                   (nameCompare(pokemon_i, pokemon_pivo))) )
        {
            aumentar_comparacoes(process_data, 1);
            i++;
            pokemon_i = celula_getProximo(pokemon_i);
        }
        aumentar_comparacoes(process_data, 1);
        while ( ( Poke_get_generation(celula_getPokemon(pokemon_j)) > generation_pivo) ||
                ( Poke_get_generation(celula_getPokemon(pokemon_j)) == generation_pivo &&
                                   (nameCompare(pokemon_pivo, pokemon_j))) )
        {
            aumentar_comparacoes(process_data, 1);
            j--;
            pokemon_j = celula_getAnterior(pokemon_j);
        }

        // Troca para caso não tenham se cruzado
        if (i <= j)
        {
            aumentar_movimentacoes(process_data, 3);
            celula_swap(pokemon_i, pokemon_j);
            i++;
            pokemon_i = celula_getProximo(pokemon_i);
            j--;
            pokemon_j = celula_getAnterior(pokemon_j);
        }
    }

    // Chamadas recursivas
    if (esquerda < j)
    {
        Dex_LDF_quicksort(my_pokedex, esquerda, j, process_data);
    }
    if (i < direita)
    {
        Dex_LDF_quicksort(my_pokedex, i, direita, process_data);
    }
}

void Dex_LDF_quicksort_call(Pokedex_LDF my_pokedex, XData process_data)
{
    Dex_LDF_quicksort(my_pokedex, 0, Dex_LDF_tamanho(my_pokedex) - 1, process_data);
}

/* */

/* */

/*_________________________________________*/

/* Método main */

void main(void)
{
    // Dados
    int *lista_ids = (int *)malloc(1000 * sizeof(int));
    Pokedex_LDF my_pokedex = new_Pokedex_ListaDuploFlex();
    XData process_data = new_Data("matrícula_quicksort2.txt", 855842);
    Pokemon to_insert;
    int cont = 0;
    int x = 0, y = 0;
    char idPoke[5];
    int idRead;

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
        Dex_LDF_InserirFim(my_pokedex, to_insert);

        x++;
    }
    free(lista_ids);

    // Chamada do método de organização
    set_Inicio(process_data);
    Dex_LDF_quicksort_call(my_pokedex, process_data);
    Dex_LDF_quicksort_call(my_pokedex, process_data);
    set_Fim(process_data);

    // Arquivo do log
    save_log(process_data);

    // Print da lista resultante
    CelulaDupla *temp = celula_getProximo(Dex_LDF_get_primeiro(my_pokedex));
    y = 0;
    while (temp != null)
    {
        // printf("[%d] ", y);
        Poke_print(celula_getPokemon(temp));

        y++;
        temp = celula_getProximo(temp);
    }

    temp = null;
    free(temp);
    free(my_pokedex);
}

/*_________________________________________*/