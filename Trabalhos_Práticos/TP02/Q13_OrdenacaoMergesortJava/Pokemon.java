
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.LocalDate;

class Pokemon {
    // Dados
    int id;
    int generation;
    String name;
    String description;
    String type1, type2;
    String[] abilities;
    int n_abilities;
    double weight, height;
    int capture_rate;
    boolean legendary;
    Date capture_date;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    /* Construtores */
    Pokemon() {
        this.id = 0;
        this.generation = 0;
        this.name = "Missingno";
        this.description = "This is wrong";
        this.type1 = "Unknown";
        this.type2 = "0";
        this.n_abilities = 1;
        this.abilities = new String[this.n_abilities];
        this.abilities[0] = "Crash the program";
        this.weight = 0;
        this.height = 0;
        this.capture_rate = 0;
        this.legendary = true;
        this.capture_date = new Date();
    }

    Pokemon(int id, int generation, String name,
            String description, String type1, String type2,
            String[] abilities, double weight, double height,
            int capture_rate, boolean legendary, Date capture_date) {

        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.type1 = type1;
        this.type2 = type2;
        this.n_abilities = abilities.length;
        this.abilities = new String[this.n_abilities];
        int x = 0;
        while (x < n_abilities) {

            this.abilities[x] = abilities[x];

            x++;
        }
        this.weight = weight;
        this.height = height;
        this.capture_rate = capture_rate;
        this.legendary = legendary;
        this.capture_date = capture_date;
    }
    /*  */

    /* Clone */

    // Dar um new e copiar individualmente as informações
    Pokemon clonePokemon() {

        Pokemon copia = new Pokemon();

        copia.setId(this.getId());
        copia.setGeneration(this.getGeneration());
        copia.setName(this.getName());
        copia.setType1(this.getType1());
        copia.setType2(this.getType2());
        copia.n_abilities = this.getAbilities().length;
        copia.setAbilities(this.getAbilities());
        copia.setWeight(this.getWeight());
        copia.setHeight(this.getHeight());
        copia.setCapture_rate(this.getCapture_rate());
        copia.setLegendary(this.getLegendary());
        copia.setCapture_date(this.getCapture_date());

        return copia;
    }

    /* */

    /* Ler */

    void ler(int id) {

        // File tabela = new File("/tmp/pokemon.csv");
        File tabela = new File("../pokemon.csv");

        try (Scanner arquivo = new Scanner(tabela)) {
            for (int x = 0; x < id; x++) {
                arquivo.nextLine();
            }

            // Talvez dê problema por causa das aspas
            String dados = arquivo.nextLine();
            // MyIO.println("1_" + dados);

            String token = dados;
            token.replace('\u00E9', '1');
            String separacao;
            int virgula_current = 0;
            int virgula_next = 0;

            // MyIO.println("2_" + dados);
            // MyIO.println("3_" + token);

            // Id
            this.setId(id);

            // Geração
            while (token.charAt(virgula_current) != ',') {
                virgula_current++;
                // MyIO.println(token.charAt(virgula_current));
            }
            virgula_next = virgula_current + 1;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }
            separacao = token.substring((virgula_current + 1), (virgula_next));
            this.setGeneration(Character.getNumericValue(token.charAt(virgula_current + 1)));

            // Nome
            virgula_current = virgula_next;
            virgula_next++;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }
            separacao = token.substring((virgula_current + 1), (virgula_next));
            this.setName(separacao);

            // Descrição
            virgula_current = virgula_next;
            virgula_next++;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }
            separacao = token.substring((virgula_current + 1), (virgula_next));
            separacao.replace('1', '\u00E9');
            this.setDescription(separacao);

            // Tipos
            virgula_current = virgula_next;
            virgula_next++;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }
            separacao = token.substring((virgula_current + 1), (virgula_next));
            this.setType1(separacao);
            if (token.charAt(virgula_next + 1) != ',') {
                virgula_current = virgula_next;
                virgula_next++;
                while (token.charAt(virgula_next) != ',') {
                    virgula_next++;
                }
                separacao = token.substring((virgula_current + 1), (virgula_next));
                this.setType2(separacao);
            } else {
                virgula_current = virgula_next;
                virgula_next++;
                this.setType2("0");
            }

            // Abilidades
            int limite_inf = virgula_next;
            int limite_sup = virgula_next;
            int n_abilities = 0;
            while (token.charAt(limite_inf) != '[') {
                limite_inf++;
            }
            limite_sup = limite_inf + 1;
            while (token.charAt(limite_sup) != ']') {
                if (token.charAt(limite_sup) == '\'') {
                    n_abilities++;
                }
                limite_sup++;
            }
            virgula_current = limite_inf + 1;
            virgula_next = limite_inf + 2;
            String[] abilities_list = new String[n_abilities / 2];

            n_abilities = 0;
            while (virgula_next < limite_sup) {
                while (token.charAt(virgula_next) != '\'') {
                    virgula_next++;
                }
                separacao = token.substring((virgula_current + 1), (virgula_next));
                abilities_list[n_abilities] = separacao;
                n_abilities++;
                virgula_next = virgula_next + 4;
                virgula_current = virgula_next - 1;
            }
            this.setAbilities(abilities_list);

            // Peso
            // virgula_next = virgula_current;
            // virgula_next++;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }
            if (token.charAt(virgula_current + 1) == ',') {
                this.setWeight(0.0);
            } else {
                separacao = token.substring((virgula_current + 1), (virgula_next));
                this.setWeight(Double.parseDouble(separacao));
            }

            // Altura
            virgula_current = virgula_next;
            virgula_next++;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }
            if (token.charAt(virgula_current + 1) == ',') {
                this.setHeight(0.0);
            } else {
                separacao = token.substring((virgula_current + 1), (virgula_next));
                this.setHeight(Double.parseDouble(separacao));
            }

            // Taxa de Captura
            virgula_current = virgula_next;
            virgula_next++;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }

            if (virgula_next - virgula_current == 2) {
                this.setCapture_rate(Character.getNumericValue(token.charAt(virgula_current + 1)));
            } else {
                separacao = token.substring((virgula_current + 1), (virgula_next));
                this.setCapture_rate(Integer.parseInt(separacao));
            }

            // Lendário
            virgula_current = virgula_next;
            virgula_next++;
            while (token.charAt(virgula_next) != ',') {
                virgula_next++;
            }
            separacao = token.substring((virgula_current + 1), (virgula_next));
            if (token.charAt(virgula_current + 1) == '0') {
                this.setLegendary(false);
            } else {
                this.setLegendary(true);
            }

            // Data de captura
            virgula_current = virgula_next;
            virgula_next = (token.length() - 1);
            separacao = token.substring((virgula_current + 1), (virgula_next));
            Date dia = new Date();
            try {
                dia = formatter.parse(separacao + token.charAt(virgula_next));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.setCapture_date(dia);

            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /* */

    /* Set */
    void setId(int id) {
        this.id = id;
    }

    void setGeneration(int generation) {
        this.generation = generation;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setType1(String type1) {
        this.type1 = type1;
    }

    void setType2(String type2) {
        this.type2 = type2;
    }

    void setAbilities(String[] abilities) {
        n_abilities = abilities.length;
        this.abilities = abilities;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    void setHeight(double height) {
        this.height = height;
    }

    void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    void setLegendary(boolean legendary) {
        this.legendary = legendary;
    }

    void setCapture_date(Date capture_date) {
        this.capture_date = capture_date;
    }
    /*  */

    /* Get */
    int getId() {
        return this.id;
    }

    int getGeneration() {
        return this.generation;
    }

    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }

    String getType1() {
        return this.type1;
    }

    String getType2() {
        return this.type2;
    }

    String[] getAbilities() {
        return this.abilities;
    }

    double getWeight() {
        return this.weight;
    }

    double getHeight() {
        return this.height;
    }

    int getCapture_rate() {
        return this.capture_rate;
    }

    boolean getLegendary() {
        return this.legendary;
    }

    Date getCapture_date() {
        return this.capture_date;
    }
    /*  */

    /* Imprimir */

    void print() {

        // [#id -> name: description - [types] - [abilities] - weight - height -
        // captureRate - isLegendary - generation] - captureDate];

        // Nome e ID e descrição
        MyIO.print("[#" + this.getId() + " -> " + this.getName() + ": ");

        // Descrição (\u00E9)
        MyIO.print(this.getDescription() + " - ");

        // Tipos
        if (this.getType2().length() == 1) {
            MyIO.print("['" + this.getType1() + "']");
        } else {
            MyIO.print("['" + this.getType1() + "', '" + this.getType2() + "']");
        }
        MyIO.print(" - ");

        // Abilidades
        MyIO.print("[");
        for (int x = 0; x < this.abilities.length; x++) {
            if (x != 0) {
                MyIO.print(", ");
            }
            MyIO.print("'" + this.abilities[x] + "'");
        }
        MyIO.print("] - ");

        // Peso, altura e taxa de captura
        MyIO.print(String.format(Locale.US, "%.1f", this.getWeight()));
        MyIO.print("kg - ");
        MyIO.print(String.format(Locale.US, "%.1f", this.getHeight()));
        MyIO.print("m - " + this.getCapture_rate() + "% - ");

        // Se é lendário
        if (this.getLegendary()) {
            MyIO.print("true - ");
        } else {
            MyIO.print("false - ");
        }

        // Número da geração
        MyIO.print(this.generation + " gen] - ");

        // Data de captura
        MyIO.println(formatter.format(this.getCapture_date()));

    }

    /* */

    /* Pesquisa/Organizar */

    // Obter tempo atual (usado para medir execução)
    public long now() {
        return new Date().getTime();
    }

    // Método para trocar pokemons de posição
    static void swap(Pokemon[] pokedex, int x, int y) {

        Pokemon temp = pokedex[x];
        pokedex[x] = pokedex[y];
        pokedex[y] = temp;

    }

    public static boolean find(Pokemon[] pokedex, String busca, int[] comparacoes) {
        // Dados
        boolean found = false;
        int x = 0;

        // Enquanto não achar ou não chegar no final
        while (found == false && x < pokedex.length) {

            // Essa implementação não funciona, então uso a outra
            /*
             * if (pokedex[x].name == busca) {
             * found = true;
             * }
             */

            comparacoes[0]++;
            // Pular comparacao se o tamanho for diferente
            if (pokedex[x].getName().length() == busca.length()) {
                found = true;
                // Comparar cada caracter individualmente
                for (int y = 0; y < busca.length(); y++) {
                    if (busca.charAt(y) != pokedex[x].getName().charAt(y)) {
                        found = false;
                    }
                }
            }

            x++;
        }

        return found;
    }

    // Ordenação pelo método de seleção (nome como atributo principal)
    static void selecao(Pokemon[] pokedex, int[] comparacoes, int[] movimentacoes) {

        // Dados
        int x = 0, y = 0;
        int a = 0, b = 0;
        int diferenca = 0;
        int menor;

        // Organizar a pokedex inteira
        for (x = 0; x < (pokedex.length - 1); x++) {

            menor = x;

            // Organizar a parte ainda não organizada (determinando o menor)
            for (y = x; y < pokedex.length; y++) {

                comparacoes[0]++;
                if (!(nameCompare(pokedex[menor], pokedex[y]))) {
                    menor = y;
                }

            }

            swap(pokedex, menor, x);
            movimentacoes[0] = movimentacoes[0] + 3;

        }

    }

    // Retorna se o nome do primeiro pokemon vem antes do do segundo
    static boolean nameCompare(Pokemon pokemon1, Pokemon pokemon2) {
        boolean antes = true;
        int a = 0;
        int b = 0;
        int diferenca = 0;
        while (a < pokemon1.getName().length() &&
                b < pokemon2.getName().length() &&
                diferenca == 0) {

            // Usar a diferença dos caracteres para definir (caso sejam diferentes)
            // qual é primeiro na ordem alfabética
            diferenca = pokemon1.getName().charAt(a) - pokemon2.getName().charAt(b);

            a++;
            b++;
        }

        // Se houver diferença nos caracteres
        if (diferenca != 0) {
            if (diferenca > 0) {
                antes = false;
            }
        }
        // Se não houver, comparar o tamanho (menor vem primeiro)
        else {
            if (b == pokemon2.getName().length())
                antes = false;
        }

        return antes;
    }

    // Ordenação pelo método de insercao (data como atributo principal, nome como
    // secundário)
    static void insercao(Pokemon[] pokedex, int[] comparacoes, int[] movimentacoes) {

        // Dados
        int x = 0, y = 0;
        Pokemon temp;

        // Organizar a pokedex inteira
        for (x = 1; x < pokedex.length; x++) {

            y = x - 1;
            movimentacoes[0] += 1;
            temp = pokedex[x];

            // Enquanto o anterior for maior
            while ((y >= 0) && (pokedex[0].dateCompare(temp, pokedex[y], comparacoes))) {
                movimentacoes[0] += 1;
                pokedex[y + 1] = pokedex[y];

                y--;
            }

            movimentacoes[0] += 1;
            pokedex[y + 1] = temp;

        }

    }

    // Retorna se a data do primeiro pokemon de input é menor (vem antes)
    public boolean dateCompare(Pokemon pokemon1, Pokemon pokemon2, int[] comparacoes) {

        boolean menor = true;

        comparacoes[0] += 1;
        if (pokemon1.getCapture_date().after(pokemon2.getCapture_date())) {
            menor = false;
        } else {
            // Se a data for a mesma
            comparacoes[0] += 1;
            if (pokemon1.getCapture_date() == pokemon2.getCapture_date()) {
                // Desempate pelo nome
                comparacoes[0] += 1;
                if (!(nameCompare(pokemon1, pokemon2))) {
                    menor = false;
                }
            }
        }

        return menor;
    }

    // Ordenação pelo método de insercao (data como atributo principal, nome como
    // secundário)
    static void counting(Pokemon[] pokedex, int[] comparacoes, int[] movimentacoes) {

        // Dados
        int[] contador = new int[maior_CaptureRate(pokedex) + 1];
        Pokemon[] pokedex_ordenada = new Pokemon[pokedex.length];

        // Valor nulo em cada posicao do array contador
        for (int i = 0; i < contador.length; i++) {
            contador[i] = 0;
        }

        // Para cada posicao do array contador, inserir a quantidade de elementos com
        // seu valor
        for (int i = 0; i < pokedex.length; i++) {
            contador[pokedex[i].getCapture_rate()]++;
        }

        // Retroativamente somar as posicoes menores em cada posicao
        for (int i = 1; i < contador.length; i++) {
            contador[i] += contador[i - 1];
        }

        // Inserir ordenado (pela taxa de captura) na pokedex temporaria
        for (int i = pokedex.length - 1; i >= 0; i--) {
            movimentacoes[0] += 1;
            pokedex_ordenada[contador[pokedex[i].getCapture_rate()] - 1] = pokedex[i];
            contador[pokedex[i].getCapture_rate()]--;
        }

        // Reorganizar os casos em que a taxa de captura é igual pelo nome
        // via método de bolha
        // Método desativado pois, analisando o pub.out, percebe-se
        // que o atributo secundário não é nome (como o enunciado diz),
        // mas sim ID
        /*
         * boolean organizado = false;
         * while ( !(organizado) ) {
         * organizado = true;
         * for (int i = 0; i < pokedex_ordenada.length-1; i++) {
         * 
         * comparacoes[0] += 2;
         * if ((pokedex_ordenada[i].getCapture_rate() ==
         * pokedex_ordenada[i+1].getCapture_rate()) &&
         * (!(nameCompare(pokedex_ordenada[i], pokedex_ordenada[i+1])))) {
         * organizado = false;
         * swap(pokedex_ordenada, i, i+1);
         * movimentacoes[0] += 3;
         * }
         * 
         * }
         * 
         * }
         */

        // Reorganizar os casos em que a taxa de captura é igual pelo id
        // via método de bolha
        boolean organizado = false;
        while (!(organizado)) {
            organizado = true;
            for (int i = 0; i < pokedex_ordenada.length - 1; i++) {

                comparacoes[0] += 2;
                if ((pokedex_ordenada[i].getCapture_rate() == pokedex_ordenada[i + 1].getCapture_rate()) &&
                        (pokedex_ordenada[i].getId() > pokedex_ordenada[i + 1].getId())) {
                    organizado = false;
                    swap(pokedex_ordenada, i, i + 1);
                    movimentacoes[0] += 3;
                }

            }

        }

        // Copiar da pokedex ordenada para a original
        for (int i = 0; i < pokedex.length; i++) {
            movimentacoes[0] += 1;
            pokedex[i] = pokedex_ordenada[i];
        }

    }

    static int maior_CaptureRate(Pokemon[] pokedex) {

        int maior = 0;

        for (int x = 0; x < pokedex.length; x++) {
            if (pokedex[x].getCapture_rate() > maior) {
                maior = pokedex[x].getCapture_rate();
            }
        }

        return maior;
    }

    // Ordenação pelo método de heapsort (Chave primária: altura)
    static void heapsort(Pokemon[] pokedex, int[] comparacoes, int[] movimentacoes) {

        int n = pokedex.length;
        // MyIO.print("n: " + n);

        // Alterar o vetor ignorando a posição zero
        Pokemon[] temp_poke = new Pokemon[n + 1];
        for (int i = 0; i < n; i++) {
            temp_poke[i + 1] = pokedex[i];
        }

        // Contrução do heap
        for (int tamanho = 2; tamanho <= n; tamanho++) {
            construir(temp_poke, tamanho, comparacoes, movimentacoes);
        }

        // Ordenação propriamente dita
        int tamanho = n;
        while (tamanho > 1) {
            swap(temp_poke, 1, tamanho--);
            reconstruir(temp_poke, tamanho, comparacoes, movimentacoes);
        }

        // Alterar o vetor para voltar à posição zero
        for (int i = 0; i < n; i++) {
            pokedex[i] = temp_poke[i + 1];
        }

    }

    static void construir(Pokemon[] pokedex, int tamanho, int[] comparacoes, int[] movimentacoes) {
        for (int i = tamanho; i > 1 &&
                (pokedex[i].getHeight() > pokedex[i / 2].getHeight() ||
                        (pokedex[i].getHeight() == pokedex[i / 2].getHeight()
                                && !(nameCompare(pokedex[i], pokedex[i / 2])))); i /= 2) {
            swap(pokedex, i, i / 2);
        }
    }

    static void reconstruir(Pokemon[] pokedex, int tamanho, int[] comparacoes, int[] movimentacoes) {
        int i = 1;
        while (i <= (tamanho / 2)) {
            int filho = getMaiorFilho(pokedex, i, tamanho, comparacoes);
            if (pokedex[i].getHeight() < pokedex[filho].getHeight()) {
                swap(pokedex, i, filho);
                i = filho;
            } else {
                // Apesar que essa comparação poderia ser colocada no if anterior,
                // a separação foi feita para ajudar na precisão das comparações
                // e o mesmo vale para a subdivisão de ifs a seguir
                if (pokedex[i].getHeight() == pokedex[filho].getHeight()) {
                    if ((nameCompare(pokedex[i], pokedex[filho]))) {
                        swap(pokedex, i, filho);
                        i = filho;
                    } else {
                        i = tamanho;
                    }
                } else {
                    i = tamanho;
                }
            }
        }
    }

    static int getMaiorFilho(Pokemon[] pokedex, int i, int tamanho, int[] comparacoes) {
        int filho;
        // Determinar se o filho é único,
        // se o primeiro tem a maior altura ou se
        // o primeiro tem um nome o qual vem depois (se as alturas forem iguais)
        if (2 * i == tamanho || pokedex[2 * i].getHeight() > pokedex[2 * i + 1].getHeight()
                || (pokedex[2 * i].getHeight() == pokedex[2 * i + 1].getHeight()
                        && !(nameCompare(pokedex[2 * i], pokedex[2 * i + 1])))) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    // Organização por mergesort (Chave primária: tipo)
    // O único tipo que importa na organização é o primeiro. No caso de empate
    // usa-se o nome
    static void mergesort_call(Pokemon[] pokedex, int[] comparacoes, int[] movimentacoes) {
        mergesort(pokedex, 0, pokedex.length-1, comparacoes, movimentacoes);
    }

    static void mergesort(Pokemon[] pokedex, int esquerda, int direita, int[] comparacoes, int[] movimentacoes) {
        if (esquerda < direita) {
            int meio = (esquerda + direita) / 2;
            mergesort(pokedex, esquerda, meio, comparacoes, movimentacoes);
            mergesort(pokedex, meio + 1, direita, comparacoes, movimentacoes);
            intercalar(pokedex, esquerda, meio, direita, comparacoes, movimentacoes);
        }
    }

    public static void intercalar(Pokemon[] pokedex, int esquerda, int meio, int direita, int[] comparacoes, int[] movimentacoes) {
        int n1, n2, i, j, k;

        // Definir tamanho dos dois subarrays
        n1 = meio - esquerda + 1;
        n2 = direita - meio;

        Pokemon[] a1 = new Pokemon[n1 + 1];
        Pokemon[] a2 = new Pokemon[n2 + 1];

        // Inicializar primeiro subarray
        for (i = 0; i < n1; i++) {
            movimentacoes[0]++;
            a1[i] = pokedex[esquerda + i];
        }

        // Inicializar segundo subarray
        for (j = 0; j < n2; j++) {
            movimentacoes[0]++;
            a2[j] = pokedex[meio + j + 1];
        }

        // Intercalacao propriamente dita
        i = 0; j = 0; k = esquerda;

        while ( i < n1 && j < n2 ) {
            comparacoes[0]++;
            if ( typeCompare(a1[i], a2[j]) < 0 ) {
                movimentacoes[0]++;
                pokedex[k] = a1[i++];
            }
            else {
                comparacoes[0]++;
                if ( (typeCompare(a1[i], a2[j]) == 0) &&
                     (nameCompare(a1[i], a2[j])) ) {
                    comparacoes[0]++; // Essa medição de comparações não é precisa,
                                      //  já que há o caso de o nameCompare() dar falso
                    movimentacoes[0]++;
                    pokedex[k] = a1[i++];
                }
                else {
                    movimentacoes[0]++;
                    pokedex[k] = a2[j++];
                }
            }
            k++;
        }

        while ( i < n1 ) {
            movimentacoes[0]++;
            pokedex[k] = a1[i];
            i++; k++;
        }

        while ( j < n2 ) {
            movimentacoes[0]++;
            pokedex[k] = a2[j];
            j++; k++;
        }


        //for (i = j = 0, k = esquerda; k <= direita; k++) {
        //    if ( nameCompare(a1[i], a2[j]) ) {
        //        pokedex[k] = a1[i++];
        //    }
        //    else {
        //        pokedex[k] = a2[j++];
        //    }
        //}
    }

    // Determina uma comparacao do primeiro tipo dos pokemons
    // ( < 0) = O primeiro tipo do primeiro vem antes
    // (== 0) = O primeiro tipo é igual em ambos
    // ( > 0) = O primeiro tipo do primeiro vem depois
    static int typeCompare(Pokemon pokemon1, Pokemon pokemon2) {

        int a = 0;
        int b = 0;
        int diferenca = 0;
        while (a < pokemon1.getType1().length() &&
                b < pokemon2.getType1().length() &&
                diferenca == 0) {

            // Usar a diferença dos caracteres para definir (caso sejam diferentes)
            // qual é primeiro na ordem alfabética
            diferenca = pokemon1.getType1().charAt(a) - pokemon2.getType1().charAt(b);

            a++;
            b++;
        }

        // Se houver diferença nos caracteres
        //if (diferenca != 0) {
        //    if (diferenca > 0) {
        //        resultado = -1;
        //    }
        //}
        // Se não houver, comparar o tamanho (menor vem primeiro)
        //else {
        //    if (b == pokemon2.getType1().length()) {
        //        resultado = -1;
        //    }
        //}

        return diferenca;
    }

    /* */

    /* Logs em arquivos */

    public static void save_log(String nome_arquivo, double inicio, double fim, int[] comparacoes) {

        // Dados
        int matricula = 855842;

        try {
            File myfile = new File(nome_arquivo);
            FileWriter arquivo = new FileWriter(nome_arquivo);
            // Número da matrícula
            arquivo.write(matricula + "\t");
            // Tempo de conversão
            arquivo.write((fim - inicio) + "\t");
            // Número de comparações (a conversão se mostrou necessária para o número ser
            // formatado certo)
            arquivo.write(String.valueOf(comparacoes[0]));

            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save_log(String nome_arquivo, double inicio, double fim, int[] comparacoes,
            int[] movimentacoes) {

        // Dados
        int matricula = 855842;

        try {
            File myfile = new File(nome_arquivo);
            FileWriter arquivo = new FileWriter(nome_arquivo);
            // Número da matrícula
            arquivo.write(matricula + "\t");
            // Número de comparações
            arquivo.write(comparacoes[0] + "\t");
            // Número de movimentações
            arquivo.write(movimentacoes[0] + "\t");
            // Tempo de conversão
            arquivo.write((fim - inicio) + "");

            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* */

    public static void main(String[] args) {

        MyIO.setCharset("UTF-8");

        // Dados
        Pokemon[] pokedex;
        int[] lista_ids = new int[100];
        int cont = 0, x = 0, y = 0;
        int[] comparacoes = new int[1];
        int[] movimentacoes = new int[1];
        double inicio = 0.0, fim = 0.0;
        String nome_arquivo = "matrícula_mergesort.txt";
        boolean[] found;
        String pokemon_nome;

        String Poke = MyIO.readLine();

        // Ler ids dos pokemons
        while (!(Poke.length() == 3 && Poke.charAt(0) == 'F')) {
            lista_ids[cont] = Integer.parseInt(Poke);
            cont++;

            Poke = MyIO.readLine();
        }

        // Dados
        pokedex = new Pokemon[cont];
        found = new boolean[cont];

        // Formar o array de Pokemons usando os ids
        while (x < cont) {

            pokedex[x] = new Pokemon();
            pokedex[x].ler(lista_ids[x]);

            x++;
        }

        comparacoes[0] = 0;
        movimentacoes[0] = 0;
        // Organização por MergeSort e tempo do processo
        inicio = pokedex[0].now();
        mergesort_call(pokedex, comparacoes, movimentacoes);
        fim = pokedex[0].now();

        // Salvar log
        save_log(nome_arquivo, inicio, fim, comparacoes, movimentacoes);

        // MyIO.println("");
        // MyIO.println("Resposta verdadeira: ");
        // Imprimir os pokemons
        x = 0;
        while (x < pokedex.length) {

            pokedex[x].print();

            x++;
        }
    }

}
