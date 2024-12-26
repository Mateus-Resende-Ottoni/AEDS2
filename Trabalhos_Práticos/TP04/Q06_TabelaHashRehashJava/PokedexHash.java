
import java.util.Date;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*-----------------------------------------------------------------------------------------------*/
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

}

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
            // arquivo.write(this.getMovimentacoes() + "\t");
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

/*-----------------------------------------------------------------------------------------------*/

class HashDireta {

    protected Pokemon lista[];
    protected int tamanho;

    /* Construtor */
    HashDireta(int tamanho) {
        this.lista = new Pokemon[tamanho];
        this.tamanho = tamanho;
    }
    /* */

    /* Hash */
    int hash(String nome) {
        int pos = 0;

        for (int x = 0; x < nome.length(); x++) {
            pos += nome.charAt(x);
        }

        pos = pos % this.tamanho;

        return pos;
    }

    int rehash(String nome) {
        int pos = 0;

        for (int x = 0; x < nome.length(); x++) {
            pos += nome.charAt(x);
        }

        pos = (pos+1) % this.tamanho;

        return pos;
    }
    /* */

    /* Inserção */
    void inserir(Pokemon poke) {
        int pos = hash(poke.getName());

        if (lista[pos] == null) {
            lista[pos] = poke.clone();
        }
        // Tentar o rehash
        else {
            pos = rehash(poke.getName());
            if (lista[pos] == null) {
                lista[pos] = poke.clone();
            }
        }

    }
    /* */

    /* Pesquisa */
    boolean pesquisa(String pokemon, Log operationLog) {
        boolean found = false;
        int pos = hash(pokemon);

        operationLog.incrementComparacoes(1);
        if (lista[pos].getName().compareTo(pokemon) == 0) {
            found = true;
        }
        // Pesquisar na área reserva
        else {
            pos = rehash(pokemon);
            if (lista[pos].getName().compareTo(pokemon) == 0) {
                found = true;
            }
        }

        System.out.print("=> " + pokemon + ": ");
        if (found) {
            System.out.println("(Posicao: " + pos + ") SIM");
        }
        else {
            System.out.println("NAO");
        }

        return found;
    }
    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class PokedexHash {

    public static void main(String[] args) {

        MyIO.setCharset("UTF-8");

        // Dados
        HashDireta pokedex = new HashDireta(21);
        Log operationLog = new Log("matrícula_hashRehash.txt", 855842);
        int[] lista_ids = new int[200];
        int cont = 0, x = 0;

        String pokeRead = MyIO.readLine();

        // Ler ids dos pokemons
        while (!(pokeRead.length() == 3 && pokeRead.charAt(0) == 'F')) {
            lista_ids[cont] = Integer.parseInt(pokeRead);
            cont++;

            pokeRead = MyIO.readLine();
        }

        // Formar a tabela hash de Pokemons usando os ids
        Pokemon para_inserir;
        while (x < cont) {

            para_inserir = new Pokemon();
            para_inserir.ler(lista_ids[x]);
            pokedex.inserir(para_inserir);

            x++;
        }

        operationLog.setInicio();
        // Ler pokémons e os pesquisar
        pokeRead = "";
        pokeRead = MyIO.readLine();
        while (pokeRead.compareTo("FIM") != 0) {
            //System.out.println(pokeRead);
            pokedex.pesquisa(pokeRead, operationLog);

            pokeRead = MyIO.readLine();
        }
        operationLog.setFim();

        operationLog.save_log();

    }

}

/*-----------------------------------------------------------------------------------------------*/