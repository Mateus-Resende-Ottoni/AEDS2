class CelulaMatriz {
    // Dados
    protected int valor;
    protected CelulaMatriz acima;
    protected CelulaMatriz embaixo;
    protected CelulaMatriz esquerda;
    protected CelulaMatriz direita;

    /* Construtor */
    CelulaMatriz() {
        this.setValor(0);
        this.setAcima(null);
        this.setEmbaixo(null);
        this.setEsquerda(null);
        this.setDireita(null);
    }

    CelulaMatriz(int valor) {
        this.setValor(valor);
        this.setAcima(null);
        this.setEmbaixo(null);
        this.setEsquerda(null);
        this.setDireita(null);
    }
    /* */

    /* Clone */
    public CelulaMatriz clone() {

        CelulaMatriz copia = new CelulaMatriz();

        copia.setValor(this.getValor());
        copia.setAcima(this.getAcima());
        copia.setEmbaixo(this.getEmbaixo());
        copia.setEsquerda(this.getEsquerda());
        copia.setDireita(this.getDireita());

        return copia;
    }
    /* */

    /* Set */
    void setAcima(CelulaMatriz acima) {
        this.acima = acima;
    }

    void setEmbaixo(CelulaMatriz embaixo) {
        this.embaixo = embaixo;
    }

    void setEsquerda(CelulaMatriz esquerda) {
        this.esquerda = esquerda;
    }

    void setDireita(CelulaMatriz direita) {
        this.direita = direita;
    }

    void setValor(int valor) {
        this.valor = valor;
    }
    /* */

    /* Get */
    CelulaMatriz getAcima() {
        return this.acima;
    }

    CelulaMatriz getEmbaixo() {
        return this.embaixo;
    }

    CelulaMatriz getEsquerda() {
        return this.esquerda;
    }

    CelulaMatriz getDireita() {
        return this.direita;
    }

    int getValor() {
        return this.valor;
    }
    /* */

}

/*------------------------------------------------------------------------------------------------------------*/

class Matriz {
    // Dados
    protected CelulaMatriz inicio;
    protected int linha, coluna;

    /* Construtores */

    Matriz() {
        this(3, 3);
    }

    Matriz(int linhas, int colunas) {
        this.setLinha(linhas);
        this.setColuna(colunas);
        this.setInicio(new CelulaMatriz());

        CelulaMatriz i = this.getInicio();
        CelulaMatriz j;
        for (int x = 0; x < linhas; x++) {
            j = i;
            for (int y = 1; y < colunas; y++) {
                // Construção da linha
                j.setDireita(new CelulaMatriz());
                j.getDireita().setEsquerda(j);
                // Para as linhas depois da primeira, conexão vertical
                if (x != 0) {
                    j.getDireita().setAcima(j.getAcima().getDireita());
                    j.getDireita().getAcima().setEmbaixo(j.getDireita());
                }
                j = j.getDireita();
            }
            // Construir início da nova linha se não for a última
            if (x != (linhas - 1)) {
                i.setEmbaixo(new CelulaMatriz());
                i.getEmbaixo().setAcima(i);
                i = i.getEmbaixo();
            }

        }

        i = j = null;
    }

    Matriz(int linhas, int colunas, int valor) {
        this.setLinha(linhas);
        this.setColuna(colunas);
        this.setInicio(new CelulaMatriz(valor));

        CelulaMatriz i = this.getInicio();
        CelulaMatriz j;
        for (int x = 0; x < linhas; x++) {
            j = i;
            for (int y = 1; y < colunas; y++) {
                // Construção da linha
                j.setDireita(new CelulaMatriz(j.getValor() + 1));
                j.getDireita().setEsquerda(j);
                // Para as linhas depois da primeira, conexão vertical
                if (x != 0) {
                    j.getDireita().setAcima(j.getAcima().getDireita());
                    j.getDireita().getAcima().setEmbaixo(j.getDireita());
                }
                j = j.getDireita();
            }
            // Construir início da nova linha se não for a última
            if (x != (linhas - 1)) {
                i.setEmbaixo(new CelulaMatriz(i.getValor() + colunas));
                i.getEmbaixo().setAcima(i);
                i = i.getEmbaixo();
            }

        }

        i = j = null;
    }

    /*  */

    /* Set */

    void setLinha(int linha) {
        this.linha = linha;
    }

    void setColuna(int coluna) {
        this.coluna = coluna;
    }

    void setInicio(CelulaMatriz inicio) {
        this.inicio = inicio;
    }

    void setElemento(int linha, int coluna, int valor) {

        CelulaMatriz i = this.getInicio();
        int x = 0, y = 0;

        for (x = 0; x < linha; x++) {
            i = i.getEmbaixo();
        }
        for (y = 0; y < coluna; y++) {
            i = i.getDireita();
        }

        //System.out.println("Set linha " + linha + ", coluna " + coluna + ". Posiçao atual (" + x + "," + y + ")");

        i.setValor(valor);

        i = null;
    }

    /* */

    /* Get */

    int getLinha() {
        return this.linha;
    }

    int getColuna() {
        return this.coluna;
    }

    CelulaMatriz getInicio() {
        return this.inicio;
    }

    int getElemento(int linha, int coluna) {

        int elemento = 0;
        int x = 0;

        CelulaMatriz i = this.getInicio();

        for (x = 0; x < linha; x++) {
            i = i.getEmbaixo();
        }
        for (x = 0; x < coluna; x++) {
            i = i.getDireita();
        }

        elemento = i.getValor();

        i = null;

        return (elemento);
    }

    /* */

    /* Prints */

    void print() {

        CelulaMatriz i = this.getInicio();

        while (i != null) {

            CelulaMatriz j = i;

            while (j != null) {
                System.out.print(j.getValor() + " ");
                j = j.getDireita();
            }
            System.out.println("");

            i = i.getEmbaixo();
        }

    }

    void mostrardiagonalPrincipal() {
        //System.out.println("");

        // Conferir se há diagonal na matriz
        if (this.getLinha() == this.getColuna()) {
            CelulaMatriz i = this.getInicio();

            while (i.getEmbaixo() != null) {
                System.out.print(i.getValor() + " ");
                i = i.getEmbaixo().getDireita();
            }
            System.out.println(i.getValor() + " ");

            i = null;
        }

        //System.out.println("");
    }

    void mostrardiagonalSecundaria() {
        //System.out.println("");

        // Conferir se há diagonal na matriz
        if (this.getLinha() == this.getColuna()) {
            CelulaMatriz i = this.getInicio();
            while (i.getDireita() != null) {
                i = i.getDireita();
            }

            while (i.getEmbaixo() != null) {
                System.out.print(i.getValor() + " ");
                i = i.getEmbaixo().getEsquerda();
            }
            System.out.println(i.getValor() + " ");

            i = null;
        }

        //System.out.println("");
    }

    /* */

        /* Operações */
        public static Matriz soma(Matriz matriz1, Matriz matriz2) {
            Matriz resultado = new Matriz();
            int coluna1 = matriz1.getColuna();
            int coluna2 = matriz2.getColuna();
            int linha1 = matriz1.getLinha();
            int linha2 = matriz2.getLinha();
    
            if (coluna1 != coluna2 || linha1 != linha2) {
                System.out.println("Erro de tamanho");
            }
            // Prosseguir com a operação apenas se as
            // matrizes terem o mesmo tamanho
            else {
                // Iniciar matriz com tamanho correto
                resultado = new Matriz(linha1, coluna1);
    
                // Ponteiros para o início das matrizes
                CelulaMatriz a1 = matriz1.getInicio();
                CelulaMatriz b1 = matriz2.getInicio();
    
                // Loop de fora representa a mudança de linhas
                for (CelulaMatriz x = resultado.getInicio(); x != null; //
                        x = x.getEmbaixo(), a1 = a1.getEmbaixo(), b1 = b1.getEmbaixo()) {
                    // Loop de dentro representa a mudança de colunas
                    CelulaMatriz a2 = a1;
                    CelulaMatriz b2 = b1;
                    for (CelulaMatriz y = x; y != null; //
                            y = y.getDireita(), a2 = a2.getDireita(), b2 = b2.getDireita()) {
                        y.setValor(a2.getValor() + b2.getValor());
                    }
                }
    
            }
    
            return (resultado);
        }
    
        public static Matriz multiplicacao(Matriz matriz1, Matriz matriz2) {
            Matriz resultado = new Matriz();
            int coluna1 = matriz1.getColuna();
            int coluna2 = matriz2.getColuna();
            int linha1 = matriz1.getLinha();
            int linha2 = matriz2.getLinha();
    
            // Conferir se pode haver multiplicação das matrizes
            if (coluna1 == linha2) {
                resultado = new Matriz(linha1, coluna2);
                CelulaMatriz a1 = matriz1.getInicio();
                CelulaMatriz b1 = matriz2.getInicio();
    
                for (CelulaMatriz s1 = resultado.getInicio(); s1 != null; //
                        s1 = s1.getEmbaixo(), a1 = a1.getEmbaixo()) {
    
                    b1 = matriz2.getInicio();
    
                    for (CelulaMatriz s2 = s1; s2 != null; //
                            s2 = s2.getDireita(), b1 = b1.getDireita()) {
    
                        CelulaMatriz a2 = a1;
                        CelulaMatriz b2 = b1;
    
                        while (a2 != null) {
                            s2.setValor(s2.getValor() + (a2.getValor() * b2.getValor()) );
                            a2 = a2.getDireita();
                            b2 = b2.getEmbaixo();
                        }
                    }
                }
    
            }
    
            return (resultado);
        }
    
        /* */
        public static void main(String[] args) {
    
            MyIO.setCharset("UTF-8");
    
            // Dados
            int n_casos = 0;
            int colunas = 0, linhas = 0;
            String valores;
            int espaco1 = 0, espaco2 = 0;
            Matriz matriz1, matriz2;
            Matriz resultado_soma, resultado_multiplicacao;
            int a = 0, b = 0, c = 0;
    
            // Ler número de operações
            n_casos = MyIO.readInt();
    
            // Casos
            for (a = 0; a < n_casos; a++) {
    
                // Ler número de linhas e colunas
                linhas = MyIO.readInt();
                colunas = MyIO.readInt();
    
                // Inicializar matriz 1
                matriz1 = new Matriz(linhas, colunas);
                //System.out.println(" - Matriz 1 (" + matriz1.getLinha() + " " + matriz1.getColuna() + ") - ");
                //System.out.println("Início do print 1 - pré sets");
                //matriz1.print();
                //System.out.println("Fim do print 1 - pré sets");
    
    
                // Ler e inserir valores
                for (b = 0; b < linhas; b++) {
                    valores = MyIO.readLine();
                    //System.out.println("Matriz 1_Linha " + b + ": " + valores);
                    espaco1 = 0;
                    espaco2 = 0;
                    for (c = 0; c < colunas; c++) {
                        while (espaco2 < valores.length() && valores.charAt(espaco2) != ' ') {
                            espaco2++;
                        }
    
                        if (espaco2 - espaco1 > 1) {
                            matriz1.setElemento(b, c, Integer.parseInt(valores.substring(espaco1, espaco2)));
                        } else if (espaco2 - espaco1 == 1) {
                            matriz1.setElemento(b, c, Character.getNumericValue(valores.charAt(espaco1)));
                        }
    
                        espaco2++;
                        espaco1 = espaco2;
    
                    } // Fim ( for 'c' )
                } // Fim ( for 'b' )
                //System.out.println("Início do print 1");
                //matriz1.print();
                //System.out.println("Fim do print 1");
    
                // Ler número de linhas e colunas
                linhas = MyIO.readInt();
                colunas = MyIO.readInt();
    
                // Inicializar matriz 2
                matriz2 = new Matriz(linhas, colunas);
                //System.out.println(" - Matriz 2 (" + matriz2.getLinha() + " " + matriz2.getColuna() + ") - ");
    
                // Ler e inserir valores
                for (b = 0; b < linhas; b++) {
                    valores = MyIO.readLine();
                    //System.out.println("Matriz 2_Linha " + b + ": " + valores);
                    espaco1 = 0;
                    espaco2 = 0;
                    for (c = 0; c < colunas; c++) {
                        while (espaco2 < valores.length() && valores.charAt(espaco2) != ' ') {
                            espaco2++;
                        }
    
                        if (espaco2 - espaco1 > 1) {
                            matriz2.setElemento(b, c, Integer.parseInt(valores.substring(espaco1, espaco2)));
                        } else if (espaco2 - espaco1 == 1) {
                            matriz2.setElemento(b, c, Character.getNumericValue(valores.charAt(espaco1)));
                        }
    
                        espaco2++;
                        espaco1 = espaco2;
    
                    } // Fim ( for 'c' )
                } // Fim ( for 'b' )
                //System.out.println("Início do print 2");
                //matriz2.print();
                //System.out.println("Fim do print 2");
    
                // Resultado de operações com matrizes
                resultado_soma = soma(matriz1, matriz2);
                resultado_multiplicacao = multiplicacao(matriz1, matriz2);
                //System.out.println(" - Matriz Soma (" + resultado_soma.getLinha() + " " + resultado_soma.getColuna() + ") - ");
                //System.out.println(" - Matriz Multiplicaçao (" + resultado_multiplicacao.getLinha() + " " + resultado_multiplicacao.getColuna() + ") - ");
    
                // Diagonal principal e secundária da primeira matriz
                //System.out.println("Início do print diagonais");
                matriz1.mostrardiagonalPrincipal();
                matriz1.mostrardiagonalSecundaria();
                //System.out.println("Fim do print diagonais");
    
                // Print dos resultados
                //System.out.println("Início do print matrizes resultantes");
                //System.out.println("-");
                //System.out.println("Soma: ");
                resultado_soma.print();
                //System.out.println("-");
                //System.out.println("Multiplicaçao: ");
                resultado_multiplicacao.print();
                //System.out.println("-");
                //System.out.println("Fim do print matrizes resultantes");
    
                matriz1 = matriz2 = resultado_soma = resultado_multiplicacao = null;
    
            } // Fim ( for 'a' )
    
        }

}

/*------------------------------------------------------------------------------------------------------------*/