package ProjConcArmado;

// TCC - Mariana Fernandes

// 1a classe - método main 
public class ProjConcretoA_Test 
{
  public static void main (String args[])
  {
      // Cria objeto da classe ProjConcretoA_Dados
      ProjConcretoA_AlgoritmoMid dados = new ProjConcretoA_AlgoritmoMid();
      
      // Cria o método setDados da classe ProjConcretoA_Dados
      // para ler os dados do problema
      
      // Objeto.Método
      dados.setDados();
      
      dados.CálculoMid();
      
      // dados.imprimirMatrizTabUniversal();
      // NAO USAR = dados.imprimirMatrizReal(Tabela,TabUniversal);
      
  } // Fim do main
} // Fim da classe

