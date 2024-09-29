
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

char caracter_change(int mudanca)
{

    char caracter = 'a';

    for (int x = 0; x < mudanca; x++)
    {
        caracter++;
    }

    return (caracter);
}

// Método de alteração
void alterar(char palavra[100], char c1, char c2, int n)
{
    // Dados
    int tamanho = 0;
    tamanho = strlen(palavra) - 1;

    // Comparar o caractere
    if (palavra[n] == c1)
    {
        printf("%c", c2);
    }
    else
    {
        printf("%c", palavra[n]);
    }

    // Loop enquanto não chegar ao final da string por meio de recursividade
    if (n < (tamanho - 1))
    {
        alterar(palavra, c1, c2, (n + 1));
    }
}

void alterar_call(char *palavra, char c1, char c2)
{
    alterar(palavra, c1, c2, 0);
}

/*
char *alterar_nao_recursivo(char palavra[100], char c1, char c2)
{
    // Dados
    char *convertida;
    int tamanho = 0;
    tamanho = strlen(palavra) - 1;
    convertida = (char *)malloc(tamanho * sizeof(char));
    int x = 0;

    while (x < tamanho)
    {
        // Comparar o caractere
        if (palavra[x] == c1)
        {
            convertida[x] = c2;
        }
        else
        {
            convertida[x] = palavra[x];
        }

        x++;
    }

    // Retornar o resultado
    return (convertida);
}
*/

// Principal
int main(void)
{
    // Dados
    int tamanho = 0;
    int seed = 4;
    char palavra[100], *convertida;
    char carac1, carac2;
    bool in_loop = true;
    setlocale(LC_ALL, "en_US.UTF-8");

    // Definir seed
    srand(seed);

    // Loop enquanto diferente de 'FIM'
    while (in_loop)
    {

        // Ler a palavra
        fgets(palavra, sizeof(palavra), stdin);
        tamanho = strlen(palavra) - 1;

        // Se for 'FIM', fazer nada
        if (palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && tamanho == 3)
        {
            in_loop = false;
        }
        // No contrário, chamar o método
        else
        {
            // Definir letras a serem alteradas
            carac1 = caracter_change((rand() % 26));
            carac2 = caracter_change((rand() % 26));

            // printf("%c and %c\n", carac1, carac2);

            alterar_call(palavra, carac1, carac2);

            printf("\n");
        }

        // Print do resultado
    }
}