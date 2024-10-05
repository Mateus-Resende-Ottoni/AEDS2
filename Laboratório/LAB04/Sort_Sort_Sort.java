



class Sort_Sort_Sort {

  public static int [] conjunto (int quant) {

    int [] array = new int [quant];

    for (int x = 0; x < quant; x++) {

      array[x] = MyIO.readInt();

    }


    return array;
  }

  public static void sort ( int array [], int quant, int divisor ) {

    // Método de Bolha
    for (int x = 0; x < quant-2; x++) {

      for (int y = quant-1; y > x; y--) {

	// Se o resto for menor, trocar de posição
	if ((array[y] % divisor) < (array[y-1] % divisor)) {

	  int temp = array[y];
	  array[y] = array[y-1];
	  array[y-1] = temp;

	}
	// Se o resto for igual, checar as condições de troca
	else if ((array[y] % divisor) == (array[y-1] % divisor)) {

	  // Se forem dois pares, o menor tem preferência para estar antes
	  if ( (array[y] % 2 == 0) && (array[y-1] % 2 == 0) ) {

	    if ( array[y] < array[y-1] ) {

	      int temp = array[y];
	      array[y] = array[y-1];
	      array[y-1] = temp;

	    }

	  }
	  // Se forem dois ímpares, o maior tem preferência para estar antes
	  else if ( (array[y] % 2 == 1) && (array[y-1] % 2 == 1) ) {

	    if ( array[y] > array[y-1] ) {

	      int temp = array[y];
	      array[y] = array[y-1];
	      array[y-1] = temp;

	    }

	  }
	  // No restante dos casos (um ímpar e um par), ímpar tem preferência para estar antes
	  else {

	    if ( array[y] % 2 == 1 ) {

	      int temp = array[y];
	      array[y] = array[y-1];
	      array[y-1] = temp;

	    }

	  }
	}

      }


    }


  }


  public static void print ( int array [], int quant ) {

    for (int x = 0; x < quant; x++) {

      MyIO.println(array[x]);

    }

  }

  public static void main (String [] args) {

    int n_num = 1, divisor = 1;

    // Ler os números, e parar o programa se for 0 0
    while ( n_num != 0 ) {
      n_num = MyIO.readInt();
      divisor = MyIO.readInt();

      MyIO.print(n_num);
      MyIO.println(" " + divisor);

      if ( n_num != 0 ) {
	// Método para ler os números e os colocar em um array(não ordenado)
	int [] numeros = conjunto (n_num);
      
	// Método para reorganizar o array com base nos restos pelo divisor e as outras regras estabelecidas na instrução
	sort (numeros, n_num, divisor);

	// Print do array
        print (numeros, n_num);

      }

    }



  }




}
