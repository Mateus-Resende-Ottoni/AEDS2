
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// Método para ler string
char *readstring( char * texto)
{
    char palavra[81];
    int tamanho = 0;

    fgets(palavra, 81, stdin);

    tamanho = strlen(palavra) - 1;
    palavra[tamanho] = '\0';

    texto = palavra;

    return ( texto );
}

bool palindromo( char* palavra )
{
    // Dados
    bool palindro = true;
    int tamanho = strlen(palavra);
    int x = 0, y = tamanho - 1;

    // Loop enquanto for palindromo e não chegar ao final da string
    while ( palindro == true && x < tamanho / 2) {

        // Comparar cada caractere
        if ( palavra[x] != palavra[y] ) {
            palindro = false;
        }

        // Prosseguir para o próximo caractere
        x = x + 1;
        y = y - 1;
    }

// Retornar o resultado
return (palindro);
}

int main(void)
{
    // Para evitar problemas de caracteres
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
        if (palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M' && tamanho == 3) {
        in_loop = false;
        } 
        // No contrário, chamar o método
        else {
            palindro = palindromo (palavra);

            // Print do resultado
            if (palindro) {
                printf("SIM\n");
            }
            else {
                printf("NAO\n");
            }

        }

    }
}