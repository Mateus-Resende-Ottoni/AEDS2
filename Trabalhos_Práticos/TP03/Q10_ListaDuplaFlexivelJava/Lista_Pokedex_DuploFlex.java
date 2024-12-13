
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*------------------------------------------------------------------------------------------------------------*/

class Log {
    protected String arquivo;
    protected int comparacoes;
    protected int movimentacoes;
    protected int matricula;
    protected Double inicio;
    protected Double fim;

    /* Construtor */
    Log(String arquivo, int matricula) {
        this.setArquivo(arquivo);
        this.setComparacoes(0);
        this.setMovimentacoes(0);
        this.setMatricula(matricula);
        this.setInicio();
        this.setFim();

    }

    /* */

    /* Sets */
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public void setComparacoes(int comparacoes) {
        this.comparacoes = comparacoes;
    }

    public void setMovimentacoes(int movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setInicio() {
        this.inicio = now();
    }

    public void setFim() {
        this.fim = now();
    }

    public double now() {
        return new Date().getTime();
    }
    /* */

    /* Gets */
    public String getArquivo() {
        return this.arquivo;
    }

    public int getComparacoes() {
        return this.comparacoes;
    }

    public int getMovimentacoes() {
        return this.movimentacoes;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public Double getInicio() {
        return this.inicio;
    }

    public Double getFim() {
        return this.fim;
    }
    /* */

    /* Incrementos */

    public void incrementComparacoes(int adicao) {
        this.comparacoes = this.comparacoes + adicao;
    }

    public void incrementMovimentacoes(int adicao) {
        this.movimentacoes = this.movimentacoes + adicao;
    }

    /* */

    /* Salvar log */
    public void save_log() {
        try {
            FileWriter arquivo = new FileWriter(this.getArquivo());
            // Número da matrícula
            arquivo.write(this.getMatricula() + "\t");
            // Número de comparações
            arquivo.write(this.getComparacoes() + "\t");
            // Número de movimentações
            arquivo.write(this.getMovimentacoes() + "\t");
            // Tempo de conversão
            arquivo.write((this.getFim() - this.getInicio()) + "");

            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* */

}

/*------------------------------------------------------------------------------------------------------------*/
class Pokemon {
    // Dados
    protected int id;
    protected int generation;
    protected String name;
    protected String description;
    protected String type1, type2;
    protected String[] abilities;
    protected int n_abilities;
    protected double weight, height;
    protected int capture_rate;
    protected boolean legendary;
    protected Date capture_date;

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
    protected Pokemon clone() {

        Pokemon copia = new Pokemon();

        copia.setId(this.getId());
        copia.setGeneration(this.getGeneration());
        copia.setName(this.getName());
        copia.setDescription(this.getDescription());
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

         File tabela = new File("/tmp/pokemon.csv");
        // File tabela = new File("../pokemon.csv");

        try (Scanner arquivo = new Scanner(tabela)) {
            for (int x = 0; x < id; x++) {
                arquivo.nextLine();
            }

            // Talvez dê problema por causa das aspas
            String dados = arquivo.nextLine();

            String token = dados;
            token.replace('\u00E9', '1');
            String separacao;
            int virgula_current = 0;
            int virgula_next = 0;

            // Id
            this.setId(id);

            // Geração
            while (token.charAt(virgula_current) != ',') {
                virgula_current++;
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

}

/*------------------------------------------------------------------------------------------------------------*/

class PokemonCelulaDupla {
    // Dados
    protected Pokemon pokemon;
    protected PokemonCelulaDupla next;
    protected PokemonCelulaDupla previous;

    /* Construtor */
    PokemonCelulaDupla() {
        this.setPokemon(new Pokemon());
        this.getPokemon().setName("Celula");
        this.setNext(null);
        this.setPrevious(null);
    }
    /* */

    /* Clone */
    public PokemonCelulaDupla clone() {

        PokemonCelulaDupla copia = new PokemonCelulaDupla();

        copia.setPokemon(this.getPokemon());
        copia.setNext(this.getNext());
        copia.setPrevious(this.getPrevious());

        return copia;
    }
    /* */

    /* Ler */
    public PokemonCelulaDupla lerPokemonCelula(int id) {
        Pokemon pokemon = new Pokemon();
        pokemon.ler(id);

        PokemonCelulaDupla novo = new PokemonCelulaDupla();
        novo.setPokemon(pokemon);

        return novo;
    }
    /* */

    /* Set */
    void setNext(PokemonCelulaDupla proximo) {
        this.next = proximo;
    }

    void setPrevious(PokemonCelulaDupla anterior) {
        this.previous = anterior;
    }

    void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon.clone();
    }
    /* */

    /* Get */
    PokemonCelulaDupla getNext() {
        return this.next;
    }

    Pokemon getPokemon() {
        return this.pokemon;
    }

    PokemonCelulaDupla getPrevious() {
        return this.previous;
    }
    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class Pokedex_Lista_DuploFlex {
    // Dados
    protected PokemonCelulaDupla primeiro;
    protected PokemonCelulaDupla ultimo;

    /* Construtores */

    Pokedex_Lista_DuploFlex() {
        this.primeiro = new PokemonCelulaDupla();
        this.ultimo = primeiro;
    }

    /*  */

    /* Tamanho */
    int tamanho() {
        int tamanho = 0;

        for (PokemonCelulaDupla i = this.primeiro; i != this.ultimo; i = i.getNext()) {
            tamanho++;
        }

        return tamanho;
    }

    int tamanho_parcial(PokemonCelulaDupla inicio, PokemonCelulaDupla fim) {
        int tamanho = 0;

        for (PokemonCelulaDupla i = inicio; i != fim; i = i.getNext()) {
            tamanho++;
        }

        return tamanho;
    }
    /* */

    /* Get */

    PokemonCelulaDupla getCelulaPos(int pos) {
        PokemonCelulaDupla resp = this.primeiro.getNext();
        ;
        int length = this.tamanho();

        if (0 < pos && pos < length) {
            for (int x = 0; x < pos; x++) {
                resp = resp.getNext();
            }

        }

        return (resp);
    }

    /* */

    /* Swap */
    void swap(PokemonCelulaDupla pkmn1, PokemonCelulaDupla pkmn2) {

        Pokemon temp = pkmn1.getPokemon();
        pkmn1.setPokemon(pkmn2.getPokemon());
        pkmn2.setPokemon(temp);

        temp = null;
    }

    /* */

    /* Remoção */

    Pokemon removerInicio() {
        Pokemon removido = null;

        if (this.primeiro != this.ultimo) {

            PokemonCelulaDupla temp = this.primeiro.getNext();
            removido = temp.getPokemon();
            this.primeiro.setNext(temp.getNext());

            if (this.ultimo == temp) {
                this.ultimo = this.primeiro;
            }

            temp.setNext(null);
            temp.setPrevious(null);
            temp = null;
        }

        return (removido);
    }

    Pokemon removerFim() {
        Pokemon removido = null;

        if (this.primeiro != this.ultimo) {
            PokemonCelulaDupla temp = this.ultimo;

            this.ultimo = this.ultimo.getPrevious();
            temp.setPrevious(null);
            this.ultimo.setNext(null);

            removido = temp.getPokemon();

            temp = null;
        }

        return (removido);
    }

    Pokemon remover(int pos) {
        Pokemon removido = null;
        int tamanho = this.tamanho();

        if ((this.primeiro != this.ultimo) && ((0 < pos) && (pos < tamanho))) {
            if (pos == 0) {
                removido = this.removerInicio();
            } else if (pos == tamanho - 1) {
                removido = this.removerFim();
            } else {
                PokemonCelulaDupla i = this.primeiro.getNext();
                for (int x = 0; x < pos; x++) {
                    i = i.getNext();
                }

                i.getPrevious().setNext(i.getNext());
                i.getNext().setPrevious(i.getPrevious());

                removido = i.getPokemon();

                i.setNext(null);
                i.setPrevious(null);
                i = null;
            }

        }

        return (removido);
    }

    /* */

    /* Inserir */

    void inserirInicio(Pokemon to_set) {

        // Criar nova célula
        PokemonCelulaDupla temp = new PokemonCelulaDupla();
        temp.setPokemon(to_set.clone());

        // Set das referências
        temp.setPrevious(this.primeiro);
        temp.setNext(this.primeiro.getNext());
        this.primeiro.setNext(temp);

        if (this.primeiro == this.ultimo) {
            this.ultimo = temp;
        } else {
            temp.getNext().setPrevious(temp);
        }

        temp = null;

    }

    void inserirFim(Pokemon to_set) {
        PokemonCelulaDupla ultimo = this.ultimo;

        // Criar nova célula
        PokemonCelulaDupla temp = new PokemonCelulaDupla();
        temp.setPokemon(to_set.clone());

        // Inserir nova célula na lista
        ultimo.setNext(temp);

        // Set das referências
        temp.setPrevious(ultimo);
        this.ultimo = temp;

        temp = ultimo = null;
    }

    void inserir(Pokemon to_set, int pos) {
        int tamanho = this.tamanho();

        if ((0 < pos) && (pos < tamanho)) {
            if (pos == 0) {
                this.inserirInicio(to_set);
            } else if (pos == tamanho - 1) {
                this.inserirFim(to_set.clone());
            } else {
                PokemonCelulaDupla i = this.primeiro;
                for (int x = 0; x < pos; x++) {
                    i = i.getNext();
                }

                PokemonCelulaDupla temp = new PokemonCelulaDupla();
                temp.setPrevious(i);
                temp.setNext(i.getNext());
                temp.getPrevious().setNext(temp);
                temp.getNext().setPrevious(temp);

                i = temp = null;
            }
        }

    }

    /* */

    /* Organização */

    void quicksort_call(Log operationLog) {
        this.quicksort(0, this.tamanho() - 1, operationLog);
    }

    void quicksort(int esquerda, int direita, Log operationLog) {

        // System.out.println("Inicio Quicksort");

        // Determinar limites e pivô
        int i = esquerda, j = direita;
        int pivo = (esquerda + direita) / 2;

        PokemonCelulaDupla pokemon_i = this.getCelulaPos(i);
        PokemonCelulaDupla pokemon_j = this.getCelulaPos(j);

        PokemonCelulaDupla pokemon_pivo = this.getCelulaPos(pivo);
        int generation_pivo = pokemon_pivo.getPokemon().getGeneration();

        // this.print();

        while (i <= j) {

            // Achar os não condizentes aos grupos
            operationLog.incrementComparacoes(1);
            while ((pokemon_i.getPokemon().getGeneration() < generation_pivo) ||
                    (pokemon_i.getPokemon().getGeneration() == generation_pivo &&
                            (nameCompare(pokemon_i, pokemon_pivo)))) {
                operationLog.incrementComparacoes(1);
                i++;
                pokemon_i = pokemon_i.getNext();
                // System.out.println("While: " + i + ", " + pokemon_i.getPokemon().getName() +
                // " - "
                // + pokemon_i.getPokemon().getGeneration());
            }
            operationLog.incrementComparacoes(1);
            while ((pokemon_j.getPokemon().getGeneration() > generation_pivo) ||
                    (pokemon_j.getPokemon().getGeneration() == generation_pivo &&
                            (nameCompare(pokemon_pivo, pokemon_j)))) {
                                operationLog.incrementComparacoes(1);
                j--;
                pokemon_j = pokemon_j.getPrevious();
            }

            // Troca para caso não tenham se cruzado
            if (i <= j) {
                operationLog.incrementMovimentacoes(3);
                swap(pokemon_i, pokemon_j);
                i++;
                pokemon_i = pokemon_i.getNext();
                j--;
                pokemon_j = pokemon_j.getPrevious();
            }

        }

        // System.out.println("Fim Quicksort");

        // Chamadas recursivas
        if (esquerda < j) {
            this.quicksort(esquerda, j, operationLog);
        }
        if (i < direita) {
            this.quicksort(i, direita, operationLog);
        }
    }

    // Retorna se o nome vem antes
    boolean nameCompare(PokemonCelulaDupla pokemon1, PokemonCelulaDupla pokemon2) {
        boolean antes = true;
        int a = 0;
        int b = 0;
        int length1 = pokemon1.getPokemon().getName().length();
        int length2 = pokemon2.getPokemon().getName().length();
        int diferenca = 0;
        while (a < length1 &&
                b < length2 &&
                diferenca == 0) {

            // Usar a diferença dos caracteres para definir (caso sejam diferentes)
            // qual é primeiro na ordem alfabética
            diferenca = pokemon1.getPokemon().getName().charAt(a) - pokemon2.getPokemon().getName().charAt(b);

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
            if (b == length2)
                antes = false;
        }

        return antes;
    }

    /* */

    /* Print */
    void print() {
        // int x = 0;
        PokemonCelulaDupla i = this.primeiro.getNext();

        while (i != null) {
            // System.out.print("[" + x + "] ");
            i.getPokemon().print();

            i = i.getNext();
            // x++;
        }
    }
    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class Lista_Pokedex_DuploFlex {
    public static void main(String[] args) {

        MyIO.setCharset("UTF-8");

        // Dados
        Log operationLog = new Log("matrícula_quicksort3.txt", 855842);
        Pokedex_Lista_DuploFlex pokedex = new Pokedex_Lista_DuploFlex();
        int[] lista_ids = new int[300];
        int cont = 0, x = 0;

        String Poke = MyIO.readLine();

        // Ler ids dos pokemons
        while (!(Poke.length() == 3 && Poke.charAt(0) == 'F')) {
            lista_ids[cont] = Integer.parseInt(Poke);
            cont++;

            Poke = MyIO.readLine();
        }

        // Formar a lista de Pokemons usando os ids
        Pokemon para_inserir;
        while (x < cont) {

            para_inserir = new Pokemon();
            para_inserir.ler(lista_ids[x]);
            pokedex.inserirFim(para_inserir);

            x++;
        }

        // Teste
        // pokedex.print();

        // Quicksort
        operationLog.setInicio();
        pokedex.quicksort_call(operationLog);
        pokedex.quicksort_call(operationLog);
        operationLog.setFim();

        // Salvar resultado da execução
        operationLog.save_log();

        // Print da lista resultante
        pokedex.print();

    }

}
