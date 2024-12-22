
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

/*-----------------------------------------------------------------------------------------------*/

class NoAN {

    public Pokemon elemento; // Conteudo do no.
    public NoAN esq, dir; // Filhos da esq e dir.
    public int nivel; // Numero de niveis abaixo do no
    public boolean cor;

    /* Construtor */
    public NoAN(Pokemon elemento) {
        this(elemento, false);
    }

    public NoAN(Pokemon elemento, boolean cor) {
        this(elemento, null, null, 1, cor);
    }

    public NoAN(Pokemon elemento, NoAN esq, NoAN dir, int nivel, boolean cor) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
        this.cor = cor;
    }
    /* */

    /* Níveis */

    // Set
    public void setNivel() {
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }

    // Get
    public static int getNivel(NoAN no) {
        return (no == null) ? 0 : no.nivel;
    }

    /* */

    /* Cor */
    public void setCor(boolean cor) {
        this.cor = cor;
    }

    public boolean getCor() {
        return this.cor;
    }
    /* */

}

/*-----------------------------------------------------------------------------------------------*/

class Arvore {
    private NoAN raiz;

    /* Construtor */
    public Arvore() {
        raiz = null;
    }
    /* */

    /* Pesquisa */
    public boolean pesquisar_path(String nome, Log operationLog) {
        System.out.print("raiz");
        boolean resultado = pesquisar_path(nome, raiz, operationLog);
        if (resultado) {
            System.out.println(" SIM");
        } else {
            System.out.println(" NAO");
        }
        return resultado;
    }

    private boolean pesquisar_path(String nome, NoAN i, Log operationLog) {
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

    private void caminharCentral(NoAN i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento.getName() + " ");
            caminharCentral(i.dir);
        }
    }
    /* */

    /* Inserir/Construir */
    public void construir(Pokemon elemento, Log operationLog) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(elemento);

        } // Senão, se a árvore tiver um elemento
        else if (raiz.esq == null && raiz.dir == null) {
            operationLog.incrementComparacoes(1);
            if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
                raiz.esq = new NoAN(elemento);
            } else {
                raiz.dir = new NoAN(elemento);
            }

        } // Senão, se a árvore tiver dois elementos (raiz e dir)
        else if (raiz.esq == null) {
            operationLog.incrementComparacoes(1);
            if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
                raiz.esq = new NoAN(elemento);
            } else if (elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0) {
                operationLog.incrementComparacoes(1);
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
            } else {
                operationLog.incrementComparacoes(1);
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        } // Senão, se a árvore tiver dois elementos (raiz e esq)
        else if (raiz.dir == null) {
            operationLog.incrementComparacoes(1);
            if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
                raiz.dir = new NoAN(elemento);

            } else if (elemento.getName().compareTo(raiz.esq.elemento.getName()) < 0) {
                operationLog.incrementComparacoes(1);
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;

            } else {
                operationLog.incrementComparacoes(1);
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        } // Senão, a árvore tem três ou mais elementos
        else {
            inserir(elemento, null, null, null, raiz, operationLog);
        }
        raiz.cor = false;
    }

    private void inserir(Pokemon elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i, Log operationLog) throws Exception {
        if (i == null) {
            operationLog.incrementComparacoes(1);
            if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i, operationLog);
            }
        } else {
            // Achou um 4-no: é preciso fragmetá-lo e reequilibrar a árvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i, operationLog);
                }
            }
            operationLog.incrementComparacoes(1);
            if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
                inserir(elemento, avo, pai, i, i.esq, operationLog);
            } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
                operationLog.incrementComparacoes(1);
                inserir(elemento, avo, pai, i, i.dir, operationLog);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }
    /* */

    /* Altura */
    public int getAltura() {
        return getAltura(raiz, 0);
    }

    public int getAltura(NoAN i, int altura) {
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

    public Pokemon getNo_elemento(NoAN i) throws Exception {
        return i.elemento;
    }
    /* */

    /* Balancear */
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i, Log operationLog) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            // Pai está a direita do avô (rotação principal para a esquerda)
            operationLog.incrementComparacoes(2); // São duas comparações independentemente do que é feito
            if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) {
                if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            }
            // Pai está a esquerda do avô (rotação principal para a direita)
            else {
                if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
                operationLog.incrementComparacoes(1);
                bisavo.esq = avo;
            } else {
                operationLog.incrementComparacoes(1);
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)
    }

    private NoAN rotacaoDir(NoAN no) {
        // System.out.println("Rotacao DIR(" + no.elemento + ")");
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        // System.out.println("Rotacao ESQ(" + no.elemento + ")");
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
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
        Log operationLog = new Log("matrícula_avinegra.txt", 855842);
        int[] lista_ids = new int[200];
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
                pokedex.construir(para_inserir, operationLog);
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