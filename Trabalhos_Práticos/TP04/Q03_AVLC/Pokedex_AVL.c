
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

/*_____________________________________________________________*/

/* Métodos para a struct Pokemon */

// Definição do tipo Pokemon
typedef struct Poke
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
    //printf("Start cloning\n");

    Pokemon clone = null;
    clone = new_Poke();

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

    //printf("Return cloning\n");
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

/*_____________________________________________________________*/

/*_____________________________________________________________*/

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
    fprintf(arquivo, "%d\t%d\t%lf", log->matricula, log->comparacoes, difference);
    //fprintf(arquivo, "%d\t%d\t%d\t%lf", log->matricula, log->comparacoes, log->movimentacoes, difference);
    

    fclose(arquivo);
}

/*----------------------------------------*/

/*_____________________________________________________________*/

/* Métodos para a struct No Pokemon */

typedef struct No {
    struct No* esq;
    struct No* dir;
    Pokemon elemento;
    int nivel;
} No;

typedef No* NoAVL;

NoAVL new_NoAVL(Pokemon elemento) {
    //printf("Creating NoAVL\n");
    NoAVL novo = null;
    novo = malloc (1 * sizeof (No));

    //printf("Attempt novo Poke\n");
    novo->elemento = new_Poke();

    //printf("Attempt elemento\n");
    if (elemento != NULL) {
        novo->elemento = Poke_clone(elemento);
    }
    novo->esq = NULL;
    novo->dir = NULL;
    novo->nivel = 0;

    return (novo);
}

int NoAVL_GetNivel(NoAVL no) {
    int nivel = 0;
    if (no != NULL) {
        nivel = no->nivel;
    }
    return nivel;
}

void NoAVL_SetNivel(NoAVL no) {
    int nivel = 1;
    if ( NoAVL_GetNivel(no->esq) > NoAVL_GetNivel(no->dir) ) {
        nivel += NoAVL_GetNivel(no->esq);
    }
    else {
        nivel += NoAVL_GetNivel(no->dir);
    }
    no->nivel = nivel;
}

/*_____________________________________________________________*/

/* Métodos para a struct Arvore AVL Pokedex */

/* Construtor */
typedef struct AVL {
    NoAVL raiz;
} AVL;

typedef AVL* PokedexAVL;

PokedexAVL new_PokedexAVL() {
    PokedexAVL novo = null;
    novo = malloc(1 * sizeof(AVL));

    novo->raiz = null;

    return (novo);
}
/* */

/* Balancear/Rotacionar */

NoAVL rotacionarDir (NoAVL no) {
    NoAVL noEsq = no->esq;
    NoAVL noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    NoAVL_SetNivel(no);
    NoAVL_SetNivel(noEsq);

    return (noEsq);
}

NoAVL rotacionarEsq (NoAVL no) {
    NoAVL noDir = no->dir;
    NoAVL noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;

    NoAVL_SetNivel(no);
    NoAVL_SetNivel(noDir);

    return (noDir);
}

NoAVL AVL_balancear(NoAVL no) {
    //printf("Inicio do balanceamento\n");
    if (no != null) {
        int fator = NoAVL_GetNivel(no->dir) - NoAVL_GetNivel(no->esq);
        // Balanceada
        if (abs(fator) <= 1) {
            NoAVL_SetNivel(no);
        }
        // Desbalanceada para a direita (rotação principal para esquerda)
        else if (fator == 2) {
            int fatorFilhoDir = NoAVL_GetNivel(no->dir->dir) - NoAVL_GetNivel(no->dir->esq);
            // Se o filho estiver em direção oposta ao pai
            if (fatorFilhoDir == -1) {
                no->dir = rotacionarDir(no->dir);
            }
            no = rotacionarEsq(no);
        }
        // Desbalanceada para a direita (rotação principal para esquerda)
        else if (fator == -2) {
            int fatorFilhoEsq = NoAVL_GetNivel(no->esq->dir) - NoAVL_GetNivel(no->esq->esq);
            // Se o filho estiver em direção oposta ao pai
            if (fatorFilhoEsq == 1) {
                no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);
        }
        else {
            //printf("Erro no balanceamento\n");
        }
    }
    //printf("Fim do balanceamento\n");
    return (no);
}

/* */

/* Inserir */

NoAVL AVL_insert(Pokemon pokemon, NoAVL i, XData log) {
    //printf("Insercao(solo)_Teste1\n");
    if (i == null) {
        i = new_NoAVL(pokemon);
    }
    else if ( strcmp(pokemon->name, i->elemento->name) < 0 ) {
        aumentar_comparacoes(log, 1);
        i->esq = AVL_insert(pokemon, i->esq, log);
    }
    else if ( strcmp(pokemon->name, i->elemento->name) > 0 ) {
        aumentar_comparacoes(log, 2);
        i->dir = AVL_insert(pokemon, i->dir, log);
    }
    else {
        aumentar_comparacoes(log, 2);
        printf("Erro\n");
    }
    //printf("Insercao(solo)_Return\n");
    return (AVL_balancear(i));
}

void AVL_inserir(Pokemon pokemon, PokedexAVL arvore, XData log) {
    //printf("Inicio da insercao (solo)\n");
    arvore->raiz = AVL_insert(pokemon, arvore->raiz, log);
    //printf("Fim da insercao (solo)\n");
}
/* */

/* Pesquisar */
bool pesquisar( char* pokemon, NoAVL i, XData log) {
    bool resp;

    if (i == null) {
        resp = false;
    }
    else if ( strcmp(pokemon, i->elemento->name) == 0 ) {
        aumentar_comparacoes(log, 1);
        resp = true;
    }
    else if ( strcmp(pokemon, i->elemento->name) < 0 ) {
        aumentar_comparacoes(log, 2);
        printf(" esq");
        //printf("(%s)", i->elemento->name);
        resp = pesquisar(pokemon, i->esq, log);
    }
    else {
        aumentar_comparacoes(log, 2);
        printf(" dir");
        //printf("(%s)", i->elemento->name);
        resp = pesquisar(pokemon, i->dir, log);
    }

    return ( resp );
}

bool AVL_pesquisar(char* pokemon, PokedexAVL arvore, XData log) {
    bool resp;
    
    printf("%s\n", pokemon);
    printf("raiz");
    
    resp = ( pesquisar(pokemon, arvore->raiz, log) );

    if (resp) {
        printf(" SIM\n");
    }
    else {
        printf(" NAO\n");
    }

    return (resp);
}
/* */


/*_____________________________________________________________*/

/* Método main */

void main(void)
{
    //printf("Inicio\n");

    // Dados
    int *lista_ids = (int *)malloc(1000 * sizeof(int));
    PokedexAVL my_pokedex = new_PokedexAVL();
    XData process_data = new_Data("matrícula_avl.txt", 855842);
    Pokemon to_insert;
    int cont = 0;
    int x = 0, y = 0;
    char idPoke[5];
    char namePoke[15];
    int idRead;

    //printf("Inicio da leitura\n");
    // Loop para ler ids
    while (idPoke[0] != 'F')
    {

        fgets(idPoke, 5, stdin);
        idPoke[strlen(idPoke) - 1] = '\0';

        lista_ids[cont] = atoi(idPoke);

        cont++;
    }
    cont--;
    //printf("Fim da leitura\n");

    //printf("Inicio da insercao\n");
    // Loop para ler do arquivo com base nos ids
    x = 0;
    while (x < cont)
    {

        to_insert = new_Poke();
        Poke_read(lista_ids[x], to_insert);
        //Poke_print(to_insert);

        AVL_inserir(to_insert, my_pokedex, process_data);

        x++;
    }
    free(lista_ids);
    //printf("Fim da insercao\n");

    // Chamada do método de pesquisa
    set_Inicio(process_data);

    fgets(namePoke, 15, stdin);
    namePoke[strlen(namePoke) - 1] = '\0';
    while (strcmp(namePoke, "FIM") != 0)
    {
        AVL_pesquisar(namePoke, my_pokedex, process_data);
        
        fgets(namePoke, 15, stdin);
        namePoke[strlen(namePoke) - 1] = '\0';
    }

    set_Fim(process_data);

    // Arquivo do log
    save_log(process_data);

    free(my_pokedex);
}

/*_____________________________________________________________*/