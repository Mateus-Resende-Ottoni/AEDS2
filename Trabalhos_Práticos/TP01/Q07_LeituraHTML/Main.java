
import java.io.*;
import java.net.*;
import java.lang.Object;

public class Main {

    public static String getHtml(String endereco) {
        URL url;
        URI uri;
        try {
            uri = new URI(endereco);
            InputStream is = null;
            BufferedReader br;
            String resp = "", line;

            try {
                url = uri.toURL();
                is = url.openStream(); // throws an IOException
                br = new BufferedReader(new InputStreamReader(is));

                while ((line = br.readLine()) != null) {
                    resp += line + "\n";
                }
            } catch (MalformedURLException mue) {
                mue.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            try {
                is.close();
            } catch (IOException ioe) {
                // nothing to see here

            }

            return resp;
        } catch (URISyntaxException e) {
            //
            e.printStackTrace();
            return endereco;
        }
    }

    public static String analise(String html, String titulo) {
        /* Dados */
        // Gerais
        int tamanho = html.length(), x = 0;
        // 'a' a 'u'
        int x1 = 0, x2 = 0, x3 = 0, x4 = 0, x5 = 0;
        // 'á' a 'ú'
        int x6 = 0, x7 = 0, x8 = 0, x9 = 0, x10 = 0;
        // 'à' a 'ù'
        int x11 = 0, x12 = 0, x13 = 0, x14 = 0, x15 = 0;
        // 'ã' e 'õ'
        int x16 = 0, x17 = 0;
        // 'â' a 'û'
        int x18 = 0, x19 = 0, x20 = 0, x21 = 0, x22 = 0;
        // consoantes
        int x23 = 0;
        // <br> e <table>
        int x24 = 0, x25 = 0;
        /*               */

        /* Valores decimais e hexadecimais de alguns elementos */
        /* Encontrei dois valores hexadecimais para alguns, então anotei ambos */
        //
        // á = 160 = A0 / 00E1
        // é = 130 = 82 / 00E9
        // í = 161 = A1 / 00ED
        // ó = 162 = A2 / 00F3
        // ú = 163 = A3 / 00FA
        //
        // à = 133 = 85 / 00E0
        // è = 138 = 8A / 00E8
        // ì = 141 = 8D / 00EC
        // ò = 149 = 95 / 00F2
        // ù = 151 = 97 / 00F9
        //
        // ã = 00E3
        // õ = 00F5
        //
        // â = 131 = 83 / 00E2
        // ê = 136 = 88 / 00EA
        // î = 140 = 8C / 00EE
        // ô = 147 = 93 / 00F4
        // û = 150 = 96 / 00FB
        //
        //
        /*                       */

        // Contar elementos
        while (x < tamanho) {

            // Detectar se é letra
            if (html.charAt(x) >= 'a' && html.charAt(x) <= 'z') {
                // Detectar se é vogal
                if ((html.charAt(x) == 'a') || (html.charAt(x) == 'e') ||
                        (html.charAt(x) == 'i') || (html.charAt(x) == 'o') || (html.charAt(x) == 'u')) {
                    if (html.charAt(x) == 'a') {
                        x1++;
                    } else if (html.charAt(x) == 'e') {
                        x2++;
                    } else if (html.charAt(x) == 'i') {
                        x3++;
                    } else if (html.charAt(x) == 'o') {
                        x4++;
                    } else if (html.charAt(x) == 'u') {
                        x5++;
                    }
                } else {
                    // Se for consoante
                    x23++;
                }
            }
            // Se for vogal com acento agudo
            else if ((html.charAt(x) == '\u00E1') || (html.charAt(x) == '\u00E9') ||
                    (html.charAt(x) == '\u00ED') || (html.charAt(x) == '\u00F3') || (html.charAt(x) == '\u00FA')) {
                if (html.charAt(x) == '\u00E1') {
                    x6++;
                } else if (html.charAt(x) == '\u00E9') {
                    x7++;
                } else if (html.charAt(x) == '\u00ED') {
                    x8++;
                } else if (html.charAt(x) == '\u00F3') {
                    x9++;
                } else if (html.charAt(x) == '\u00FA') {
                    x10++;
                }
            }
            // Se for vogal com acento grave
            else if ((html.charAt(x) == '\u00E0') || (html.charAt(x) == '\u00E8') ||
                    (html.charAt(x) == '\u00EC') || (html.charAt(x) == '\u00F2') || (html.charAt(x) == '\u00F9')) {
                if (html.charAt(x) == '\u00E0') {
                    x11++;
                } else if (html.charAt(x) == '\u00E8') {
                    x12++;
                } else if (html.charAt(x) == '\u00EC') {
                    x13++;
                } else if (html.charAt(x) == '\u00F2') {
                    x14++;
                } else if (html.charAt(x) == '\u00F9') {
                    x15++;
                }
            }
            // Se for 'a' ou 'o' com til
            else if ((html.charAt(x) == '\u00E3') || (html.charAt(x) == '\u00F5')) {
                if (html.charAt(x) == '\u00E3') {
                    x16++;
                } else if (html.charAt(x) == '\u00F5') {
                    x17++;
                }
            }
            // Se for vogal com acento circunflexo
            else if ((html.charAt(x) == '\u00E2') || (html.charAt(x) == '\u00EA') ||
                    (html.charAt(x) == '\u00EE') || (html.charAt(x) == '\u00F4') || (html.charAt(x) == '\u00FB')) {
                if (html.charAt(x) == '\u00E2') {
                    x18++;
                } else if (html.charAt(x) == '\u00EA') {
                    x19++;
                } else if (html.charAt(x) == '\u00EE') {
                    x20++;
                } else if (html.charAt(x) == '\u00F4') {
                    x21++;
                } else if (html.charAt(x) == '\u00FB') {
                    x22++;
                }
            }
            // Se for <br>
            if ((tamanho - x) >= 4) {
                if (html.charAt(x) == '<' && html.charAt(x + 1) == 'b' && html.charAt(x + 2) == 'r'
                        && html.charAt(x + 3) == '>') {
                    x24++;
                }
            }
            // Se for <table>
            if ((tamanho - x) >= 7) {
                if (html.charAt(x) == '<' && html.charAt(x + 1) == 't' && html.charAt(x + 2) == 'a'
                        && html.charAt(x + 3) == 'b' && html.charAt(x + 4) == 'l' && html.charAt(x + 5) == 'e'
                        && html.charAt(x + 6) == '>') {
                    x25++;
                }
            }

            x++;
        }

        // Montar string
        String resposta = new String("a(" + (x1-1) + ") e(" + (x2-1) + ") i(" + x3 + ") o(" + x4 + ") u(" + x5 +
                ") \u00E1(" + x6 + ") \u00E9(" + x7 + ") \u00ED(" + x8 + ") \u00F3(" + x9 + ") \u00FA(" + x10 +
                ") \u00E0(" + x11 + ") \u00E8(" + x12 + ") \u00EC(" + x13 + ") \u00F2(" + x14 + ") \u00F9(" + x15 +
                ") \u00E3(" + x16 + ") \u00F5(" + x17 +
                ") \u00E2(" + x18 + ") \u00EA(" + x19 + ") \u00EE(" + x20 + ") \u00F4(" + x21 + ") \u00FB(" + x22 +
                ") consoante(" + (x23-3) +
                ") <br>(" + x24 + ") <table>(" + x25 + ") " + titulo);

        // String resposta = new String("a(" + x1 + ") e(" + x2 + ") i(" + x3 + ") o(" +
        // x4 + ") u(" + x5 +
        // ") \u00E1(" + x6 + ") \u00E9(" + x7 + ") \u00ED(" + x8 + ") \u00F2(" + x9 +
        // ") \u00FA(" + x10 +
        // ") \u00E0(" + x11 + ") \u00E8(" + x12 + ") \u00EC(" + x13 + ") \u00F2(" + x14
        // + ") \u00F9(" + x15 +
        // ") \u00E3(" + x16 + ") \u00F5(" + x17 +
        // ") \u0083(" + x18 + ") \u00EA(" + x19 + ") \u00EE(" + x20 + ") \u00F4(" + x21
        // + ") \u00FB(" + x22 +
        // ") consoante(" + x23 +
        // ") <br>(" + x24 + ") <table>(" + x25 + ") " + titulo);

        return resposta;
    }

    public static void main(String[] args) {

        MyIO.setCharset("UTF-8");

        /*
         * MyIO.println("aeiouáéíóúàèìòù");
         * char teste = 160;
         * MyIO.println(teste);
         */

        // Dados
        String endereco, html = "Ayo", titulo;
        boolean in_loop = true;

        // Loop enquanto diferente de 'FIM'
        while (in_loop) {
            // MyIO.println("In_Loop");
            // Ler a palavra
            titulo = MyIO.readLine();

            // Se for 'FIM', fazer nada
            // Feito dessa forma pois não funcionou colocar ( titulo != "Fim" )
            if (titulo.charAt(0) == 'F' && titulo.charAt(1) == 'I' && titulo.charAt(2) == 'M'
                    && titulo.length() == 3) {
                in_loop = false;
            }
            // No contrário, chamar o método
            else {
                endereco = MyIO.readLine();
                // MyIO.println(titulo + " - " + endereco);

                html = getHtml(endereco);

                String resposta = analise(html, titulo);

                MyIO.println(resposta);
            }

        }
    }

}

/*
// Se for vogal com acento agudo
            else if ((html.charAt(x) == 'á') || (html.charAt(x) == 'é') ||
                    (html.charAt(x) == 'í') || (html.charAt(x) == 'ó') || (html.charAt(x) == 'ú')) {
                if (html.charAt(x) == 'á') {
                    x6++;
                } else if (html.charAt(x) == 'é') {
                    x7++;
                } else if (html.charAt(x) == 'í') {
                    x8++;
                } else if (html.charAt(x) == 'ó') {
                    x9++;
                } else if (html.charAt(x) == 'ú') {
                    x10++;
                }
            }
            // Se for vogal com acento grave
            else if ((html.charAt(x) == 'à') || (html.charAt(x) == 'è') ||
                    (html.charAt(x) == 'ì') || (html.charAt(x) == 'ò') || (html.charAt(x) == 'ù')) {
                if (html.charAt(x) == 'à') {
                    x11++;
                } else if (html.charAt(x) == 'è') {
                    x12++;
                } else if (html.charAt(x) == 'ì') {
                    x13++;
                } else if (html.charAt(x) == 'ò') {
                    x14++;
                } else if (html.charAt(x) == 'ù') {
                    x15++;
                }
            }
            // Se for 'a' ou 'o' com til
            else if ((html.charAt(x) == 'ã') || (html.charAt(x) == 'õ')) {
                if (html.charAt(x) == 'ã') {
                    x16++;
                } else if (html.charAt(x) == 'õ') {
                    x17++;
                }
            }
            // Se for vogal com acento circunflexo
            else if ((html.charAt(x) == 'â') || (html.charAt(x) == 'ê') ||
                    (html.charAt(x) == 'î') || (html.charAt(x) == 'ô') || (html.charAt(x) == 'û')) {
                if (html.charAt(x) == 'â') {
                    x18++;
                } else if (html.charAt(x) == 'ê') {
                    x19++;
                } else if (html.charAt(x) == 'î') {
                    x20++;
                } else if (html.charAt(x) == 'ô') {
                    x21++;
                } else if (html.charAt(x) == 'û') {
                    x22++;
                }
            }
 */