package ProjConcArmado;

// TCC - Mariana Fernandes

public class ProjConcretoA_TabUniversal //extends ProjConcretoA_Dados 
{
  // Variáveis de instância = todos os métodos abaixo tem acesso a elas
  private double Tabela[][];
  String TabUniversal;  
 
  // 1o método = método construtor (mesmo nome da classe)
  // Objetivo: dimensionar a matriz tabela
  public ProjConcretoA_TabUniversal()
  {
      // Tabela = new double[67][7]; 
      Tabela = new double[21][7]; 
  }
  
  // ********************************************
  // 2o método - Elaboração da Tabela Universal    
  public void CalculoTabUniversal ()
  {
      // Declaração das variáveis locais 
        double a, k, ksiLinha, mid;
        int i,j;
        

        
        double Ecd[], Esd[], ksi[];
        Ecd = new double[21];
        Esd = new double[21];
        ksi = new double[21];
        
        
  
        // *********** DOMÍNIO 2A**************
        double EsdA = 10.0;
        double EcdA = 2.0;
        double inc = 0.01;
        double ksiA;
        ksiA = EcdA/(EcdA+EsdA); // Posição da linha neutra
        ksi[0] = 0.08;
        ksi[9] = ksiA; // ksi[9] = 0.16667;
        // ************************************
        
        // *********** DOMÍNIO 2B**************
        double EsdB = 10.0;
        double EcdB = 3.5;
        double ksiB;
        ksiB = EcdB/(EcdB+EsdB);
        ksi[10] = 0.17;
        ksi[19] = 0.2590;
        ksi[20] = ksiB; // ksi[20] = 0.25926;
        // ************************************
        
        // *********** DOMÍNIO 3***************
        // double EsdC = 4.484;
        // double EcdC = 3.5;
        // double ksiC;
        // ksiC = EcdC/(EcdC+EsdC);
        // ksi[21] = 0.26003;
        // ksi[30] = ksiC;
       
        
        for(i=1; i<9;i++) // DOMÍNIO 2A
        {
            ksi[i]=ksi[i-1]+inc;
        }
        
         for(i=11; i<19;i++) // DOMÍNIO 2B
        {
            ksi[i]=ksi[i-1]+inc;
        }
        
        for(i=0; i<9; i++) // DOMÍNIO 2A
        {
          Ecd[i]=ksi[i]*EsdA/(1-ksi[i]);  
        }
        Ecd[9] = EcdA;
        
        for(i=10; i<20; i++) // DOMÍNIO 2B
        {
          Ecd[i]=ksi[i]*EsdB/(1-ksi[i]);  
        }
        Ecd[20] = EcdB;
        
        
        // Preenche os primeiros valores - Ecd[] e ksi[]
        for(i=0; i<21; i++)
        {
           // System.out.printf("Ecd[i]: %d, ksi[i]:%d\n",Ecd[i], ksi[i]);
           System.out.printf("Ecd[%d]: %.4f, ksi[%d]:%.4f\n", i, Ecd[i], i, ksi[i]);
        }
           System.out.println(); // Para pular 1 linha
           
        // Preencher ou colocar os valores dos vetores na matriz TabUniversal
        for(i=0; i<21; i++)// Varre por linha
        {
            // for(j=0; j<2; j++)// Varre por coluna
            // {
                Tabela[i][0] = Ecd[i];
                Tabela[i][1] = EsdA; 
                Tabela[i][3] = ksi[i];
            // }
        }
        TabUniversal = "TabUniversal";
  // Chamar o método Imprimir Matriz Real para imprimir a matriz Tabela
  imprimirMatrizReal(Tabela, TabUniversal);
  } // Fim do método CalculoTabUniversal
   // ********************************************
  
  
  // ********************************************
  // 3o método - getTabela --> retorna a matriz Tabela
  public double[][] getTabela()
  {
      return Tabela;
  }
  // ********************************************

  
 // ********************************************
 // 4o método - Imprime matriz real
 // Cria o método para impressão da matriz real
 public void imprimirMatrizReal(double[][]matriz,String nomeMatriz)
 {
     System.out.print("Matriz"+nomeMatriz+":\n");
     for(int i=0;i<matriz.length;i++) // Varre por linha
     {
         System.out.println(); // Para pular uma linha
         for(int j=0;j<matriz[0].length;j++) // Varre por coluna
         {
             System.out.printf(nomeMatriz+"(%d,%d)=%.4f ",i+1,j+1,matriz[i][j]);
         } // Fim do for interno
     } // Fim do for externo
     System.out.println(); // Para pular 1 linha
 } // Fim do método imprimirMatrizReal
  // ********************************************
 
}
