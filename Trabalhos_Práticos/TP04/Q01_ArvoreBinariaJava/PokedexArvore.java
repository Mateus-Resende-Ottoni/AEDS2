
import java.util.Date;
import java.util.Locale;
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

/*-----------------------------------------------------------------------------------------------*/

class No {

    public Pokemon elemento; // Conteudo do no.
    public No esq, dir; // Filhos da esq e dir.
    public int nivel; // Numero de niveis abaixo do no

    /* Construtor */
    public No(Pokemon elemento) {
        this(elemento, null, null, 1);
    }

    public No(Pokemon elemento, No esq, No dir, int nivel) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }
    /* */

    /* Níveis */

    // Set
    public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    // Get
    public static int getNivel(No no) {
        return (no == null) ? 0 : no.nivel;
    }

    /* */

}

/*-----------------------------------------------------------------------------------------------*/

class Arvore {
    private No raiz;

    /* Construtor */
    public Arvore() {
        raiz = null;
    }
    /* */

    /* Pesquisa */
    public boolean pesquisar(String nome) {
        return pesquisar(nome, raiz);
    }

    private boolean pesquisar(String nome, No i) {
        boolean resp;
        if (i == null) {
            resp = false;

        } else if (nome == i.elemento.getName()) {
            resp = true;

        } else if (nome.compareTo(i.elemento.getName()) < 0) {
            resp = pesquisar(nome, i.esq);

        } else {
            resp = pesquisar(nome, i.dir);
        }
        return resp;
    }

    public boolean pesquisar_path(String nome, Log operationLog) {
        System.out.print("=>raiz");
        boolean resultado = pesquisar_path(nome, raiz, operationLog);
        if (resultado) {
            System.out.println(" SIM");
        } else {
            System.out.println(" NAO");
        }
        return resultado;
    }

    private boolean pesquisar_path(String nome, No i, Log operationLog) {
        boolean resp;
        operationLog.incrementComparacoes(1);
        if (i == null) {
            resp = false;

        } else if (nome.compareTo(i.elemento.getName()) == 0) {
            resp = true;

        } else if (nome.compareTo(i.elemento.getName()) < 0) {
            System.out.print(" esq");
            resp = pesquisar_path(nome, i.esq, operationLog);

        } else {
            System.out.print(" dir");
            resp = pesquisar_path(nome, i.dir, operationLog);
        }
        return resp;
    }
    /* */

    /* Caminhar */
    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    private void caminharCentral(No i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento.getName() + " ");
            caminharCentral(i.dir);
        }
    }

    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    private void caminharPre(No i) {
        if (i != null) {
            System.out.print(i.elemento.getName() + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    private void caminharPos(No i) {
        if (i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento.getName() + " ");
        }
    }
    /* */

    /* Inserir */
    public void inserirPai(Pokemon poke, Log operationLog) throws Exception {
        operationLog.incrementComparacoes(1);
        if (raiz == null) {
            raiz = new No(poke);
        } else if (poke.getName().compareTo(raiz.elemento.getName()) < 0) {
            inserirPai(poke, raiz.esq, raiz, operationLog);
        } else if (poke.getName().compareTo(raiz.elemento.getName()) > 0) {
            inserirPai(poke, raiz.dir, raiz, operationLog);
        } else {
            throw new Exception("Erro ao inserir Pai!");
        }
    }

    private void inserirPai(Pokemon poke, No i, No pai, Log operationLog) throws Exception {
        operationLog.incrementComparacoes(1);
        if (i == null) {
            if (poke.getName().compareTo(pai.elemento.getName()) < 0) {
                pai.esq = new No(poke);
            } else {
                pai.dir = new No(poke);
            }
        } else if (poke.getName().compareTo(i.elemento.getName()) < 0) {
            inserirPai(poke, i.esq, i, operationLog);
        } else if (poke.getName().compareTo(i.elemento.getName()) > 0) {
            inserirPai(poke, i.dir, i, operationLog);
        } else {
            throw new Exception("Erro ao inserir Pai!");
        }
    }
    /* */

    /* Remover */
    public void remover(Pokemon poke, Log operationLog) throws Exception {
        raiz = remover(poke, raiz, operationLog);
    }

    private No remover(Pokemon poke, No i, Log operationLog) throws Exception {

        operationLog.incrementComparacoes(1);
        if (i == null) {
            throw new Exception("Erro ao remover!");

        } else if (poke.getName().compareTo(i.elemento.getName()) < 0) {
            i.esq = remover(poke, i.esq, operationLog);

        } else if (poke.getName().compareTo(i.elemento.getName()) > 0) {
            i.dir = remover(poke, i.dir, operationLog);

            // Sem nó a direita.
        } else if (i.dir == null) {
            i = i.esq;

            // Sem nó a esquerda.
        } else if (i.esq == null) {
            i = i.dir;

            // Nó a esquerda e nó a direita.
        } else {
            i.esq = maiorEsq(i, i.esq);
        }

        return i;
    }

    private No maiorEsq(No i, No j) {

        // Encontrou o maximo da subarvore esquerda.
        if (j.dir == null) {
            i.elemento = j.elemento; // Substitui i por j.
            j = j.esq; // Substitui j por j.ESQ.

            // Existe no a direita.
        } else {
            // Caminha para direita.
            j.dir = maiorEsq(i, j.dir);
        }
        return j;
    }
    /* */

    /* Altura */
    public int getAltura() {
        return getAltura(raiz, 0);
    }

    public int getAltura(No i, int altura) {
        if (i == null) {
            altura--;
        } else {
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    }
    /* */

    /* */
    public Pokemon getRaiz_elemento() throws Exception {
        return raiz.elemento;
    }

    public Pokemon getNo_elemento(No i) throws Exception {
        return i.elemento;
    }
    /* */
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

/*------------------------------------------------------------------------------------------------------------*/

class PokedexArvore {

    public static void main(String[] args) {

        MyIO.setCharset("UTF-8");

        // Dados
        Arvore pokedex = new Arvore();
        Log operationLog = new Log("matrícula_arvoreBinaria.txt", 855842);
        int[] lista_ids = new int[300];
        int cont = 0, x = 0;

        String Poke = MyIO.readLine();

        // Ler ids dos pokemons
        while (!(Poke.length() == 3 && Poke.charAt(0) == 'F')) {
            lista_ids[cont] = Integer.parseInt(Poke);
            cont++;

            Poke = MyIO.readLine();
        }

        operationLog.setInicio();
        // Formar a lista de Pokemons usando os ids
        Pokemon para_inserir;
        while (x < cont) {

            para_inserir = new Pokemon();
            para_inserir.ler(lista_ids[x]);
            try {
                pokedex.inserirPai(para_inserir, operationLog);
            } catch (Exception e) {
                System.err.println(e);
            }

            x++;
        }

        // Ler pokémons e os pesquisar
        Poke = "";
        Poke = MyIO.readLine();
        while (!(Poke.length() == 3 && Poke.charAt(0) == 'F')) {
            System.out.println(Poke);
            pokedex.pesquisar_path(Poke, operationLog);

            Poke = MyIO.readLine();
        }
        operationLog.setFim();

        operationLog.save_log();

    }

}

/*-----------------------------------------------------------------------------------------------*/