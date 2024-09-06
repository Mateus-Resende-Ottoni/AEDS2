

class Parenteses_correto {

  public static boolean  parenteses (String expressao) {
  
    // Dados
    boolean  parenteses_correct = true;
    int parenteses = 0;
    int x = 0, tamanho = expressao.length();

    while ( (x < tamanho) && (parenteses >= 0) ) {

	if ( expressao.charAt(x) == '(' ) {
	parenteses++;
	}
	else if ( expressao.charAt(x) == ')' ) {
	parenteses--;
	}


	x++;
    }
  
    if ( parenteses !=  0 ) {
	parenteses_correct = false;
    }
  
    return parenteses_correct; 
  }

  public static void main (String[] args) {
  
MyIO.setCharset("UTF-8");

    // Dados
    String palavra;
    boolean  in_loop = true;
    boolean  parenteses_correct;
  
    // Loop enquanto diferente de 'FIM'
    while (in_loop) {
	
	// Ler entrada
	palavra = MyIO.readLine();

	if (palavra.length() == 3 &&
	    palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M') {
		in_loop = false;
	    }

	if (in_loop) {

	  parenteses_correct = parenteses(palavra);
	  
	  if (parenteses_correct) {
		MyIO.println ("correto");
	  }
	  else {
		MyIO.println ("incorreto");
	  }


	}


    }
  
  }



}
