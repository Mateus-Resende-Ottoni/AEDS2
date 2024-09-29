
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static File save_in_archive(int n_numbers, String nome_arquivo) {

        // Dados
        double number;
        File arquivo_base = new File(nome_arquivo);
        RandomAccessFile arquivo;

        try {
            arquivo = new RandomAccessFile(arquivo_base, "rw");
            for (int x = 0; x < n_numbers; x++) {
                number = MyIO.readDouble();
                arquivo.writeDouble(number);
            }

            arquivo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arquivo_base;
    }

    public static void read_from_archive(File arquivo_base) {

        // Dados
        RandomAccessFile arquivo;
        try {
            arquivo = new RandomAccessFile(arquivo_base, "r");
            long tamanho = arquivo.length();
            long pointer = tamanho - 8;
            double current;
            int parte_inteira;
    

            // Definir apontador no final do arquivo
            arquivo.seek(pointer);
    
            while (pointer >= 0) {
                // Atualizar o apontador
                arquivo.seek(pointer);
    
                current = arquivo.readDouble();
                parte_inteira = (int) current;

                // Print do resultado
                if ( (current >= 1.0) && ( (current - parte_inteira) == 0.0 ) ) {
                    MyIO.println(parte_inteira);
                }
                else {
                MyIO.println(current);
                }
    
                pointer -= 8;
            }
    
            arquivo.close();
        } catch (FileNotFoundException e) {
            //
            e.printStackTrace();
        } catch (IOException e) {
            //
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        int n_numbers = 0;
        String nomearquivo = "dados.txt";

        n_numbers = MyIO.readInt();

        File arquivo = save_in_archive(n_numbers, nomearquivo);

        read_from_archive(arquivo);

    }
}
