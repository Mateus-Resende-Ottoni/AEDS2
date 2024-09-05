
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void combinar(char *palavra1, char *palavra2)
{

    // Dados
    int x = 0, y = 0;
    int z = 0;
    int tamanho1 = strlen(palavra1);
    int tamanho2 = strlen(palavra2);
    char combinacao[128];

    while ((x < tamanho1) && (y < tamanho2))
    {

        combinacao[z] = palavra1[x];
        z++;
        combinacao[z] = palavra2[y];

        z++;
        x++;
        y++;
    }

    if (x == tamanho1)
    {
        while (y < tamanho2)
        {
            combinacao[z] = palavra2[y];
            z++;
            y++;
        }
    }
    else if (y == tamanho2)
    {
        while (x < tamanho1)
        {
            combinacao[z] = palavra1[x];
            z++;
            x++;
        }
    }

    combinacao[z] = '\0';

    // Print do resultado
    printf("%s\n", combinacao);
}

int main(void)
{
    // Dados
    char palavra1[64], palavra2[64], *resultado;

    // Loop enquanto não acabar, e também lendo as palavras
    while ((scanf("%s %s", palavra1, palavra2)) != EOF)
    {
        // Chamar o método
        combinar(palavra1, palavra2);
    }
}