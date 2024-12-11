
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

class Pokedex {
    // Dados
    protected Pokemon[] pokemons;
    protected int n;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    /* Construtores */

    Pokedex(int x) {
        this.pokemons = new Pokemon[x];
        this.setN(0);
    }

    /*  */

    /* Ler */

    public static Pokemon lerPokemon(int id) {
        Pokemon novo = new Pokemon();
        novo.ler(id);

        return novo;
    }

    /* */

    /* Sets */

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    public void setPokemon(Pokemon pokemon, int pos) {
        this.pokemons[pos] = pokemon;
    }

    void setN(int x) {
        this.n = x;
    }

    /*  */

    /* Gets */

    public Pokemon[] getPokemons() {
        return this.pokemons;
    }

    public Pokemon getPokemon(int pos) {
        return this.pokemons[pos];
    }

    int getN() {
        return this.n;
    }

    /*  */

    /* Métodos de troca */

    public void swap(int pos1, int pos2) {
        Pokemon temp = this.getPokemon(pos1);
        this.setPokemon(this.getPokemon(pos2), pos1);
        this.setPokemon(temp, pos2);
    }

    /* */

    /* Remoção */

    Pokemon removerInicio() {
        Pokemon removido = null;
        int n = this.getN();

        if (n > 0) {
            removido = this.getPokemon(0);

            for (int x = 0; x < n - 1; x++) {
                this.setPokemon(this.getPokemon(x + 1).clone(), x);
            }

            n--;
            this.setN(n);
        }

        return (removido);
    }

    Pokemon removerFim() {
        Pokemon removido = null;
        int n = this.getN();

        if (n > 0) {
            removido = this.getPokemon(n - 1);

            n--;
            this.setN(n);
        }

        return (removido);
    }

    Pokemon remover(int pos) {
        Pokemon removido = null;
        int n = this.getN();

        if (n > 0 && (0 < pos && pos < n)) {
            removido = this.getPokemon(pos);

            for (int x = pos; x < n - 1; x++) {
                this.setPokemon(this.getPokemon(x + 1).clone(), x);
            }

            n--;
            this.setN(n);
        }

        return (removido);
    }

    /* */

    /* Inserir */

    void inserirInicio(Pokemon to_set) {
        int n = this.getN();
        int length = this.getPokemons().length;

        if (n < length) {

            for (int x = n; x > 0; x--) {
                this.setPokemon(this.getPokemon(x - 1).clone(), x);
            }

            this.setPokemon(to_set.clone(), 0);

            n++;
            this.setN(n);
        }

    }

    void inserirFim(Pokemon to_set) {
        int n = this.getN();
        int length = this.getPokemons().length;

        if (n < length) {

            this.setPokemon(to_set.clone(), n);

            n++;
            this.setN(n);
        }

    }

    void inserir(Pokemon to_set, int pos) {
        int n = this.getN();
        int length = this.getPokemons().length;

        if (n < length) {

            for (int x = n; x > pos; x--) {
                this.setPokemon(this.getPokemon(x - 1).clone(), x);
            }

            this.setPokemon(to_set.clone(), pos);

            n++;
            this.setN(n);
        }

    }

    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class PokemonCelula {
    // Dados
    protected Pokemon pokemon;
    protected PokemonCelula next;

    /* Construtor */
    PokemonCelula() {
        this.setPokemon(new Pokemon());
        this.setNext(null);
    }
    /* */

    /* Clone */
    protected PokemonCelula clone() {

        PokemonCelula copia = new PokemonCelula();

        copia.setPokemon(this.getPokemon());
        copia.setNext(this.getNext());

        return copia;
    }
    /* */

    /* Ler */
    public static PokemonCelula lerPokemonCelula(int id) {
        Pokemon pokemon = new Pokemon();
        pokemon.ler(id);

        PokemonCelula novo = new PokemonCelula();
        novo.setPokemon(pokemon);

        return novo;
    }
    /* */

    /* Set */
    void setNext(PokemonCelula proximo) {
        this.next = proximo;
    }

    void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
    /* */

    /* Get */
    PokemonCelula getNext() {
        return this.next;
    }

    Pokemon getPokemon() {
        return this.pokemon;
    }
    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class Pokedex_Lista_Flex {
    // Dados
    protected PokemonCelula primeiro;
    protected PokemonCelula ultimo;

    /* Construtores */

    Pokedex_Lista_Flex() {
        this.setPrimeiro(new PokemonCelula());
        this.setUltimo(this.getPrimeiro());
    }

    /*  */

    /* Sets */
    void setPrimeiro(PokemonCelula primeiro) {
        this.primeiro = primeiro;
    }

    void setUltimo(PokemonCelula ultimo) {
        this.ultimo = ultimo;
    }
    /*  */

    /* Gets */
    PokemonCelula getPrimeiro() {
        return this.primeiro;
    }

    PokemonCelula getUltimo() {
        return this.ultimo;
    }
    /*  */

    /* Tamanho */
    int tamanho() {
        int tamanho = 0;

        PokemonCelula temp = this.getPrimeiro();
        while (temp != ultimo) {
            temp = temp.getNext();
            tamanho++;
        }

        return tamanho;
    }
    /* */

    /* Remoção */

    Pokemon removerInicio() {
        Pokemon removido = null;

        if (this.getPrimeiro() != this.getUltimo()) {
            removido = this.getPrimeiro().getNext().getPokemon();

            PokemonCelula temp = this.getPrimeiro().getNext();
            this.getPrimeiro().setNext(temp.getNext());

            if (this.getUltimo() == temp) {
                this.setUltimo(this.getPrimeiro());
            }

            temp.setNext(null);
            temp = null;
        }

        return (removido);
    }

    Pokemon removerFim() {
        Pokemon removido = null;

        if (this.getPrimeiro() != this.getUltimo()) {
            PokemonCelula temp = this.getPrimeiro();
            while (temp.getNext() != this.getUltimo()) {
                temp = temp.getNext();
            }

            removido = this.getUltimo().getPokemon();

            this.setUltimo(temp);

            temp = null;
            this.getUltimo().setNext(null);
        }

        return (removido);
    }

    Pokemon remover(int pos) {
        Pokemon removido = null;
        int tamanho = this.tamanho();

        if ((this.getPrimeiro() != this.getUltimo()) && ((0 < pos) && (pos < tamanho))) {
            if (pos == 0) {
                removido = this.removerInicio();
            } else if (pos == tamanho - 1) {
                removido = this.removerFim();
            } else {
                PokemonCelula i = this.getPrimeiro();
                for (int x = 0; x < pos; x++) {
                    i = i.getNext();
                }

                PokemonCelula temp = i.getNext();
                removido = temp.getPokemon();
                i.setNext(temp.getNext());

                temp.setNext(null);
                i = temp = null;
            }
        }

        return (removido);
    }

    /* */

    /* Inserir */

    void inserirInicio(Pokemon to_set) {

        PokemonCelula temp = new PokemonCelula();
        temp.setPokemon(to_set.clone());

        temp.setNext(this.getPrimeiro().getNext());
        this.getPrimeiro().setNext(temp);

        if (this.getPrimeiro() == this.getUltimo()) {
            this.setUltimo(temp);
        }

        temp = null;

    }

    void inserirFim(Pokemon to_set) {

        this.getUltimo().setNext(new PokemonCelula());
        this.setUltimo(this.getUltimo().getNext());

        this.getUltimo().setPokemon(to_set.clone());

    }

    void inserir(Pokemon to_set, int pos) {
        int tamanho = this.tamanho();

        if ((0 < pos) && (pos < tamanho)) {
            if (pos == 0) {
                this.inserirInicio(to_set);
            } else if (pos == tamanho - 1) {
                this.inserirFim(to_set.clone());
            } else {
                PokemonCelula i = this.getPrimeiro();
                for (int x = 0; x < pos; x++) {
                    i = i.getNext();
                }

                PokemonCelula temp = new PokemonCelula();
                temp.setNext(i.getNext());
                i.setNext(temp);

                i = temp = null;
            }
        }

    }

    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class Pokedex_Pilha_Flex {
    // Dados
    protected PokemonCelula topo;

    /* Construtores */

    Pokedex_Pilha_Flex() {
        this.setTopo(null);
    }

    /*  */

    /* Sets */
    void setTopo(PokemonCelula topo) {
        this.topo = topo;
    }
    /*  */

    /* Gets */
    PokemonCelula getTopo() {
        return this.topo;
    }
    /*  */

    /* Tamanho */
    int tamanho() {
        int tamanho = 0;

        PokemonCelula temp = this.getTopo();
        while (temp != null) {
            tamanho++;
            temp = temp.getNext();
        }

        return tamanho;
    }
    /* */

    /* Remoção */

    Pokemon remover() {
        PokemonCelula celula_removida = null;
        Pokemon removido = null;

        if (this.getTopo() != null) {
            
            // Obter célula removida
            celula_removida = this.getTopo();

            // Alterar links e topo
            this.setTopo(celula_removida.getNext());
            celula_removida.setNext(null);

            // Obter pokémon da célula removida
            removido = celula_removida.getPokemon();

        }
        celula_removida = null;
    
        return (removido);
    }

    /* */

    /* Inserir */

    void inserir(Pokemon to_set) {

        // Construir nova célula e linkar o next dela ao topo
        PokemonCelula new_one = new PokemonCelula();
        new_one.setPokemon(to_set.clone());
        new_one.setNext(this.getTopo());

        // Definir nova célula como o topo
        this.setTopo(new_one);

    }

    /* */

    /* Print */
    // Chamado do método de print
    void print_call() {
        print(this.getTopo());        
    }

    // Método de print recursivo
    int print(PokemonCelula atual) {
        // Variável de contador para saber o posicionamento
        int contador = 0;

        // Condição para parar chamada na última célula
        if (atual.getNext() != null) {
            // Retorno do contador
            contador = print(atual.getNext());
        }
        // O print
        System.out.print("[" + contador + "] ");
        atual.getPokemon().print();
        
        // Incremento do contador para o aumentar nas demais execuções
        contador++;

        return (contador);
    }

    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class Pilha_Pokedex_Flex {

    public static void main(String[] args) {

        MyIO.setCharset("UTF-8");

        // Dados
        Pokedex_Pilha_Flex pokedex = new Pokedex_Pilha_Flex();
        int[] lista_ids = new int[100];
        int cont = 0, x = 0, y = 0;
        int idRead;
        String operation;
        Pokemon to_insert = new Pokemon();

        String Poke = MyIO.readLine();

        // Ler ids dos pokemons
        while (!(Poke.length() == 3 && Poke.charAt(0) == 'F')) {
            lista_ids[cont] = Integer.parseInt(Poke);
            cont++;

            Poke = MyIO.readLine();
        }

        // System.out.println("Max length: " + pokedex.getPokemons().length);
        // Formar a lista de Pokemons usando os ids
        Pokemon para_inserir;
        while (x < cont) {

            para_inserir = new Pokemon();
            para_inserir.ler(lista_ids[x]);
            pokedex.inserir(para_inserir);

            x++;
        }
        // System.out.println("Resulting N: " + pokedex.getN());

        // Ler número de inserções/remoções
        cont = MyIO.readInt();

        // Remoções e inserções
        for (y = 0; y < cont; y++) {

            operation = MyIO.readLine();

            // Inserção
            if (operation.charAt(0) == 'I') {
                // Definir ID
                if (operation.length() > 3 && !(operation.charAt(operation.length() - 1) == ' ')) {
                    idRead = Integer.parseInt(operation.substring(2, operation.length()));
                } else {
                    idRead = Character.getNumericValue(operation.charAt(2));
                }

                // Ler pokemon no arquivo
                to_insert.ler(idRead);

                // Inserção
                // Como é pilha, a inserção é no topo
                pokedex.inserir(to_insert);

            }
            // Remoção e print do removido
            else if (operation.charAt(0) == 'R') {
                Pokemon removido = null;

                // Como é pilha, é remoção do topo
                removido = pokedex.remover();

                System.out.println("(R) " + removido.getName());
            }

        }

        // Print da pilha resultante
        pokedex.print_call();

    }

}
