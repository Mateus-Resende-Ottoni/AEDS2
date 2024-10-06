import java.util.*;

public class Quicksort {
    protected int[] array;
    protected int n;

    /* Construtores */

    public Quicksort() {
        array = new int[100];
        n = array.length;
    }

    public Quicksort(int tamanho) {
        array = new int[tamanho];
        n = array.length;
    }

    public Quicksort(Quicksort clone) {
        array = new int[clone.array.length];
        n = array.length;

        for (int x = 0; x < n; x++) {
            array[x] = clone.array[x];
        }
    }

    /* */

    /* Alteração de valores */
    public void crescente() {
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
    }

    public void decrescente() {
        for (int i = 0; i < n; i++) {
            array[i] = n - 1 - i;
        }
    }

    public void semialeatorio() {
        this.crescente();

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i < (n / 20); i++) {
            int random = Math.abs(rand.nextInt()) % n;
            if (random == n - 1) {
                swap(random, random - 1);
            } else {
                swap(random, random + 1);
            }
        }
    }

    public void aleatorio() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        crescente();
        for (int i = 0; i < n; i++) {
            swap(i, Math.abs(rand.nextInt()) % n);
        }
    }

    /* */

    /* Algoritmos de Quicksort */
    private void QuicksortFirstPivot(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[esq];
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuicksortFirstPivot(esq, j);
        if (i < dir)
            QuicksortFirstPivot(i, dir);
    }

    private void QuicksortLastPivot(int esq, int dir) {
        int i = esq, j = dir;
        int pivo = array[dir];
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuicksortLastPivot(esq, j);
        if (i < dir)
            QuicksortLastPivot(i, dir);
    }

    private void QuicksortRandomPivot(int esq, int dir) {
        int i = esq, j = dir;
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        // System.out.print("esq + dir:" + esq + " " + dir);
        int n_rand = (Math.abs(rand.nextInt()) % (dir - esq)) + esq;
        // System.out.println(" - rand:" + n_rand);
        int pivo = array[n_rand];
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuicksortRandomPivot(esq, j);
        if (i < dir)
            QuicksortRandomPivot(i, dir);
    }

    private void QuicksortMedianOfThreePivot(int esq, int dir) {
        int i = esq, j = dir;

        int numeros[] = new int[3];

        /* Organizacao para achar a Mediana dos 3 escolhidos */
        int first = array[esq];
        int mid = array[(esq + dir) / 2];
        int end = array[dir];
        numeros[0] = first;
        numeros[1] = mid;
        numeros[2] = end;

        int x = 0;
        boolean organized = false;
        while (x < 3 && organized == false) {
            organized = true;

            int y = 0;
            while (y < 2) {
                if (numeros[y] > numeros[y + 1]) {
                    int temp = numeros[y];
                    numeros[y] = numeros[y + 1];
                    numeros[y + 1] = temp;

                    organized = false;
                }
                y++;
            }

            x++;
        }
        /* */

        int pivo = array[numeros[1]];
        while (i <= j) {
            while (array[i] < pivo)
                i++;
            while (array[j] > pivo)
                j--;
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (esq < j)
            QuicksortMedianOfThreePivot(esq, j);
        if (i < dir)
            QuicksortMedianOfThreePivot(i, dir);
    }

    /* */

    /* Outros */

    public void mostrar() {
        System.out.print("[");

        for (int i = 0; i < n; i++) {
            System.out.print(" (" + i + ")" + array[i]);
        }

        System.out.println("]");
    }

    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public long now() {
        return new Date().getTime();
    }

    /* */

    public static void main(String[] args) {
        double inicio1 = 0.0000, fim1 = 0.0000;
        double inicio2 = 0.0000, fim2 = 0.0000;
        double inicio3 = 0.0000, fim3 = 0.0000;
        double inicio4 = 0.0000, fim4 = 0.0000;

        /* Array Aleatório - INÍCIO */
        /* Teste com array de 100 elementos */

        Quicksort array1 = new Quicksort(100);
        array1.aleatorio();
        Quicksort array2 = new Quicksort(array1);
        Quicksort array3 = new Quicksort(array1);
        Quicksort array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Aleatório _ 100 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 1000 elementos */

        array1 = new Quicksort(1000);
        array1.aleatorio();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Aleatório _ 1000 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 10000 elementos */

        array1 = new Quicksort(10000);
        array1.aleatorio();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Aleatório _ 10000 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 17500 elementos */

        array1 = new Quicksort(17500);
        array1.aleatorio();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Aleatório _ 17500 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Array Aleatório - FIM */

        /* Array Organizado - INÍCIO */
        /* Teste com array de 100 elementos */

        array1 = new Quicksort(100);
        array1.crescente();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Organizado _ 100 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 1000 elementos */

        array1 = new Quicksort(1000);
        array1.crescente();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Organizado _ 1000 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 10000 elementos */

        array1 = new Quicksort(10000);
        array1.crescente();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Organizado _ 10000 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 17500 elementos */

        array1 = new Quicksort(17500);
        array1.crescente();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Organizado _ 17500 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Array Organizado - FIM */

        /* Array Semi-organizado - INÍCIO */
        /* Teste com array de 100 elementos */

        array1 = new Quicksort(100);
        array1.semialeatorio();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Semi-organizado _ 100 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 1000 elementos */

        array1 = new Quicksort(1000);
        array1.semialeatorio();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Semi-organizado _ 1000 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 10000 elementos */

        array1 = new Quicksort(10000);
        array1.semialeatorio();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Semi-organizado _ 10000 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Teste com array de 17500 elementos */

        array1 = new Quicksort(17500);
        array1.semialeatorio();
        array2 = new Quicksort(array1);
        array3 = new Quicksort(array1);
        array4 = new Quicksort(array1);

        System.out.println("");
        System.out.println("-------------------------");
        // array1.mostrar();
        System.out.println("");
        System.out.println("");

        inicio1 = array1.now();
        array1.QuicksortFirstPivot(0, (array1.n - 1));
        fim1 = array1.now();
        inicio2 = array1.now();
        array2.QuicksortLastPivot(0, (array2.n - 1));
        fim2 = array1.now();
        inicio3 = array1.now();
        array3.QuicksortRandomPivot(0, (array3.n - 1));
        fim3 = array1.now();
        inicio4 = array1.now();
        array4.QuicksortMedianOfThreePivot(0, (array4.n - 1));
        fim4 = array1.now();

        System.out.println("Array organizado: Semi-organizado _ 17500 elementos");
        System.out.println("Tempos para ordenar: ");
        System.out.printf("FirstPivot: %.4fs.\n", (fim1 - inicio1) / 1000);
        System.out.printf("LastPivot: %.4fs.\n", (fim2 - inicio2) / 1000);
        System.out.printf("RandomPivot: %.4fs.\n", (fim3 - inicio3) / 1000);
        System.out.printf("MedianOfThreePivot: %.4fs.\n", (fim4 - inicio4) / 1000);
        System.out.println("");
        System.out.println("-------------------------");

        /* */

        /* Array Semi-organizado - FIM */

    }

}
