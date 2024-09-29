
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// Metodo para ler string
char *readstring(char *texto)
{
    char palavra[80];
    int tamanho = 0;

    fgets(palavra, 80, stdin);

    tamanho = strlen(palavra) - 1;
    palavra[tamanho] = '\0';

    texto = palavra;

    return (texto);
}


bool palindromo(char *palavra, int x, int y)
{
    // Dados
    bool palindro = true;

    // Loop recursivo enquanto for palindromo e não chegar ao meio da string
    if (x < y)
    {

        // Comparar cada caractere
        if ( palavra[x] != palavra[y] )
        {
            palindro = false;
        }
        else {
            palindro = palindromo ( palavra, (x+1), (y-1) );
        }


    }

    // Retornar o resultado
    return (palindro);
}

bool palindromo_call(char *palavra)
{
    return ( palindromo( palavra, 0, ( strlen(palavra) - 2 ) ) );
}

int main(void)
{
    setlocale(LC_ALL, "Portuguese");

    // Dados
    int tamanho = 0;
    char *palavra;
    bool palindro = true, in_loop = true;

    // Loop enquanto diferente de 'FIM'
    while (in_loop)
    {

        // Ler a palavra

        palavra = readstring(palavra);
        tamanho = strlen(palavra);


        // Se for 'FIM', fazer nada
        if (palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && tamanho == 3)
        {
            in_loop = false;
        }
        // No contrário, chamar o método
        else
        {
            palindro = palindromo_call(palavra);

            if (palindro)
            {
                printf("SIM\n");
            }
            else
            {
                printf("NAO\n");
            }
        }

        // Print do resultado
    }
}