
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

// import java.io.IOException;
// import java.io.RandomAccessFile;
// import java.io.FileWriter;

/*------------------------------------------------------------------------------------------------------------*/
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
        //File tabela = new File("../pokemon.csv");

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
    Pokemon[] pokemons;
    int n;

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
                this.setPokemon(this.getPokemon(x+1).clonePokemon(), x);
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

            for (int x = pos; x < n-1; x++) {
                this.setPokemon(this.getPokemon(x+1).clonePokemon(), x);
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
                this.setPokemon(this.getPokemon(x - 1).clonePokemon(), x);
            }

            this.setPokemon(to_set.clonePokemon(), 0);

            n++;
            this.setN(n);
        }

    }

    void inserirFim(Pokemon to_set) {
        int n = this.getN();
        int length = this.getPokemons().length;

        if (n < length) {

            this.setPokemon(to_set.clonePokemon(), n);

            n++;
            this.setN(n);
        }

    }

    void inserir(Pokemon to_set, int pos) {
        int n = this.getN();
        int length = this.getPokemons().length;

        if (n < length) {

            for (int x = n; x > pos; x--) {
                this.setPokemon(this.getPokemon(x - 1).clonePokemon(), x);
            }

            this.setPokemon(to_set.clonePokemon(), pos);

            n++;
            this.setN(n);
        }

    }

    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class Lista_Pokedex extends Pokedex {

    /* Construtor */
    Lista_Pokedex(int x) {
        super(x);
    }
    /* */

    public static void main(String[] args) {

        MyIO.setCharset("UTF-8");

        // Dados
        Lista_Pokedex pokedex;
        int[] lista_ids = new int[100];
        int cont = 0, x = 0, y = 0;
        int idRead, posRead;
        String operation;
        Pokemon to_insert = new Pokemon();

        String Poke = MyIO.readLine();

        // Ler ids dos pokemons
        while (!(Poke.length() == 3 && Poke.charAt(0) == 'F')) {
            lista_ids[cont] = Integer.parseInt(Poke);
            cont++;

            Poke = MyIO.readLine();
        }

        // Dados
        pokedex = new Lista_Pokedex(cont + 20);

        //System.out.println("Max length: " + pokedex.getPokemons().length);
        // Formar a lista de Pokemons usando os ids
        Pokemon para_inserir;
        while (x < cont) {

            para_inserir = lerPokemon(lista_ids[x]);
            pokedex.inserirFim(para_inserir);

            x++;
        }
        //System.out.println("Resulting N: " + pokedex.getN());

        // Ler número de inserções/remoções
        cont = MyIO.readInt();

        // Remoções e inserções
        for (y = 0; y < cont; y++) {

            operation = MyIO.readLine();

            // Inserções
            if (operation.charAt(0) == 'I') {
                // Definir com base no segundo caractere
                // Inserção Posicionada
                if (operation.charAt(1) == '*') {
                    // Definir posição e ID
                    int z = 4;
                    while (operation.charAt(z) != ' ') {
                        z++;
                    }
                    posRead = Integer.parseInt(operation.substring(3, z));
                    if (operation.length()-z > 2) {
                        idRead = Integer.parseInt(operation.substring(z+1, operation.length()));
                    }
                    else {
                        idRead = Character.getNumericValue(operation.charAt(z+1));
                    }
                    idRead = Integer.parseInt(operation.substring(z + 1, operation.length()));


                    // Ler pokemon no arquivo
                    to_insert = lerPokemon(idRead);

                    pokedex.inserir(to_insert, posRead);
                    
                    //System.out.println("Inserir (" + to_insert.getId() + "): " + to_insert.getName() + ". Result: " + pokedex.getPokemon(posRead).getName() );

                } else {
                    // Definir ID
                    if (operation.length() > 4 && !(operation.charAt(operation.length()-1) == ' ')) {
                        idRead = Integer.parseInt(operation.substring(3, operation.length()));
                    }
                    else {
                        idRead = Character.getNumericValue(operation.charAt(3));
                    }

                    // Ler pokemon no arquivo
                    to_insert.ler(idRead);
                    //System.out.print("Inserir: ");
                    //to_insert.print();

                    // Início
                    if (operation.charAt(1) == 'I') {
                        pokedex.inserirInicio(to_insert);
                        //System.out.println("InserirInicio: (" + to_insert.getId() + "): " + to_insert.getName() + ". Result: " + pokedex.getPokemon(0).getName() );
                    }
                    // Fim
                    else if (operation.charAt(1) == 'F') {
                        pokedex.inserirFim(to_insert);
                        //System.out.println("InserirFim: (" + to_insert.getId() + "): " + to_insert.getName() + ". Result: " + pokedex.getPokemon(pokedex.getN()-1).getName() );
                    }

                }
            }
            // Remoções e print dos removidos
            else if (operation.charAt(0) == 'R') {
                Pokemon removido = null;
                // Definir com base no segundo caractere
                // Remoção Posicionada
                if (operation.charAt(1) == '*') {
                    // Definir posição
                    if (operation.length() > 4 && !(operation.charAt(operation.length()-1) == ' ')) {
                        posRead = Integer.parseInt(operation.substring(3, operation.length()));
                    }
                    else {
                        posRead = Character.getNumericValue(operation.charAt(3));
                    }

                    //System.out.print("Remover (" + pokedex.getPokemon(posRead).getId() + "): " +  pokedex.getPokemon(posRead).getName() );
                    removido = pokedex.remover(posRead);
                    //System.out.println(". Result: " + removido.getName() );
                }
                // Remoção no início e no fim
                else {
                    // Início
                    if (operation.charAt(1) == 'I') {
                        //System.out.print("RemoverInicio (" + pokedex.getPokemon(0).getId() + "): " + pokedex.getPokemon(0).getName() );
                        removido = pokedex.removerInicio();
                        //System.out.println(". Result: " + removido.getName() );
                    }
                    // Fim
                    else if (operation.charAt(1) == 'F') {
                        //System.out.print("RemoverFim (" + pokedex.getPokemon(pokedex.getN()-1).getId() + "): " + pokedex.getPokemon(pokedex.getN()-1).getName() );
                        removido = pokedex.removerFim();
                        //System.out.println(". Result: " + removido.getName() );
                    }

                }

                System.out.println("(R) " + removido.getName());
            }

        }

        // Print da lista resultante
        int length = pokedex.getN();
        for (y = 0; y < length; y++) {
            System.out.print("[" + y + "] ");
            pokedex.getPokemon(y).print();
        }

    }

}
