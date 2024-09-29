
// Bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

// Salvar dados no arquivo
void save_in_archive(char *nome_arquivo, int n_numbers)
{

	// Dados
	double number;
	FILE *arquivo = fopen(nome_arquivo, "wt");

	for (int x = 0; x < n_numbers; x++)
	{

		// Ler numero
		scanf("%lf", &number);
		getchar();

		// Inserir no arquivo
		fprintf(arquivo, "%lf\n", number);
	}

	fclose(arquivo);
}

// Ler dados do arquivo _ Metodo nao utilizado por nao funcionar no Verde, mas funcionou na minha maquina
void read_from_archive(char *nome_arquivo)
{

	// Dados
	FILE *arquivo = fopen(nome_arquivo, "rt");
	int x = -1, parte_inteira;
	double number = 1.0;
	double inteiro, fracionario;
	long beggining = ftell(arquivo);
	bool first_time = true;

	// Colocar apontador no último byte
	fseek(arquivo, x, SEEK_END);
	while (ftell(arquivo) > 0)
	{

		if (fgetc(arquivo) == '\n')
		{

			// Para evitar tentar ler a ultima linha (vazia)
			if (first_time)
			{
				first_time = false;
			}
			else
			{
				fscanf(arquivo, "%lf", &number);

				// Separar cada caso de numero para nao mostrar casas decimais vazias
				// Se o numero nao tiver valores nas casas decimais
				fracionario = modf(number, &inteiro);
				parte_inteira = fracionario * 1000;
				if ((number >= 1.0) && ((number - inteiro) == 0.0))
				{
					parte_inteira = number;
					printf("%d\n", parte_inteira);
				}
				// Se a parte decimal tiver as duas ultimas casas vazias
				else if ((parte_inteira % 100) == 0)
				{
					printf("%.1lf\n", number);
				}
				// Se a parte decimal tiver a ultima casa vazia
				else if ((parte_inteira % 10) == 0)
				{
					printf("%.2lf\n", number);
				}
				// Para os casos restantes
				else
				{
					printf("%.3lf\n", number);
				}
			}
		}

		x = x - 2;
		fseek(arquivo, x, SEEK_END);
	}

	// Para obter o primeiro numero, já que o loop para quando o apontador chega nele
	//	fseek ( arquivo, 0, SEEK_SET );
	fscanf(arquivo, "%lf", &number);
	printf("%.3lf\n", number);

	fclose(arquivo);
}


void read_archive(char *nome_arquivo, int n)
{

	// Dados
	FILE *arquivo = fopen(nome_arquivo, "rt");
	int x = 0, parte_inteira;
	double number = 1.0;
	double numbers[100];
	double inteiro, fracionario;
	bool first_time = true;

	// Ler inputs
	for (x = 0; x < n; x++)
	{

		fscanf(arquivo, "%lf", &number);
		numbers[x] = number;
	}

	x--;
	while (x >= 0)
	{

		// Separar cada caso de numero para nao mostrar casas decimais vazias
		// Se o numero nao tiver valores nas casas decimais
		fracionario = modf(numbers[x], &inteiro);
		parte_inteira = fracionario * 10000;
		if ((numbers[x] >= 1.0) && ((numbers[x] - inteiro) == 0.0))
		{
			parte_inteira = numbers[x];
			printf("%d\n", parte_inteira);
		}
		// Se a parte decimal tiver as duas ultimas casas vazias
		else if ((parte_inteira % 1000) == 0)
		{
			printf("%.1lf\n", numbers[x]);
		}
		// Se a parte decimal tiver a ultima casa vazia
		else if ((parte_inteira % 100) == 0)
		{
			printf("%.2lf\n", numbers[x]);
		}
		// Para os casos restantes
		else
		{
			printf("%.3lf\n", numbers[x]);
		}

		x--;
	}

	fclose(arquivo);
}

// Principal
void main()
{
	int n_numbers = 0;
	char *nome_arquivo = "dados.txt";

	scanf("%d", &n_numbers);
	getchar();

	save_in_archive(nome_arquivo, n_numbers);

	// Metodo para ler os dados (desabilitado por nao funcionar no Verde)
	// read_from_archive(nome_arquivo);

	read_archive(nome_arquivo, n_numbers);
}