package ProjConcArmadoB;

// TCC - Mariana Fernandes

// Classe file para criar arquivos de texto
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

// Classe Formatter para escrever (gravar) em um arquivo de texto
import java.util.Formatter;

// Para ler do arquivo
import java.util.Scanner;

// Caixa de diálogo para exibir mensagens na tela
import javax.swing.JOptionPane;

// Esta classe Interface_Arquivo herda a classe ProjConcretoB_Dados
// para acessar todos os dados da seção transversal, dados do problema

public class ProjConcretoB_Arquivo extends ProjConcretoB_PropGeomGAUSS 
{
  private String nomeArquivo, nomeArquivoA, nomeArquivoEnt, nomeArquivoSai;
  private Formatter gravaEntrada, gravaSaída;
  
  private Scanner LerEntrada;
  private double SeçãoLida[];
  private int CódigoErroFormatação;
 
 /************************* 1o Método = CONSTRUTOR *************************/ 
  public ProjConcretoB_Arquivo()
  {
      // Dimensões lidas da seção transversal do vetor SeçãoLida[5]
      SeçãoLida = new double[4];
      CódigoErroFormatação = 0;
  }
  
  
    
  /********  2o Método = NovoArquivoEntrada *************************/
  // Cria o nome do arquivo em String com terminações "in" e "out", 
  // mas não cria o arquivo ainda 
  public void NovoArquivoEntrada(File name)
  {              
      nomeArquivo = name.getName();   
      if (nomeArquivo.endsWith("in"))
      {       
        int valor = nomeArquivo.length() - 3;     
        nomeArquivoA = nomeArquivo.substring(0,valor);
        nomeArquivoEnt = ".\\Arquivos\\"+nomeArquivoA+".in";
        nomeArquivoSai = ".\\Arquivos\\"+nomeArquivoA+".out";  
      }
      else
      {    
        nomeArquivoA = nomeArquivo;  
        nomeArquivoEnt = ".\\Arquivos\\"+nomeArquivoA+".in";
        nomeArquivoSai = ".\\Arquivos\\"+nomeArquivoA+".out"; 
      }   
  }   

  
/********  3o Método = AbrirArquivoEntrada *************************/
// Livro Deitel Cap.11 - Tratamento de Exceções -> Cap.11.4 página 339
// Cap. 17 sobre Arquivos File(Formatter) pág. 551
public void AbrirArquivoEntrada(File name)
{
    nomeArquivo = name.getName(); 
    if (nomeArquivo.endsWith("in"))
    {       
       int valor = nomeArquivo.length() - 3;     
       nomeArquivoA = nomeArquivo.substring(0,valor);
       nomeArquivoEnt = ".\\Arquivos\\"+nomeArquivoA+".in";
       nomeArquivoSai = ".\\Arquivos\\"+nomeArquivoA+".out";  
      }
      else
      {
        String Aviso1 = " Extensão do arquivo deve ser .in!";
        JOptionPane.showMessageDialog( null, Aviso1 );     
        nomeArquivoEnt="null";
      }
    
    
      try 
      { // LerEntrada = objeto Scanner le do arquivo
          System.out.printf("Passou pelo try antes de ler LerEntrada\n"); 
         // Se o arquivo for:  nomeArquivoEnt="null" 
         LerEntrada = new Scanner (new File(nomeArquivoEnt)); 
         // Ler os dados do Arquivo existente
         for (int i = 0; i < 4 ; i++)
         {
             System.out.printf("Dentro do for i= %d\n",i); 
             SeçãoLida[i] = LerEntrada.nextDouble();
             System.out.printf("SeçãoLida[i] %.2f   ", SeçãoLida[i] );                    
         }  
         CódigoErroFormatação = 0;//Não tem erro de formtação
         System.out.printf("Passou pelo try depois do for\n"); 
         LerEntrada.close();// Fecha o arquivo de dados de entrada pois já leu
         setSeção(SeçãoLida); // Está na classe Dados
        
      }
      
      catch(FileNotFoundException fileNotFoundException)
      {
           String Aviso2 = " O arquivo é nulo! ";
           JOptionPane.showMessageDialog( null, Aviso2 );             
      }  
      //*********************  2* try = Erro de formatação do arquivo      
      catch(InputMismatchException inputMismatchException)      
      {
          
           String Aviso2 = "Formatação do arquivo errada. \n"
                   + "O arquivo será fechado, mas não o Aplicativo.";
           JOptionPane.showMessageDialog( null, Aviso2 );   
           System.out.printf("Passou pelo InputMismatchException\n"); 
           CódigoErroFormatação = 1; //Tem um erro
           LerEntrada.close();
           //System.exit(1);
      }        
} 

      
 /********  4* Método =  getCódigoErroFormatação *************************/
  public int getCódigoErroFormatação()
  {
     return CódigoErroFormatação;
  }  
        
  /********  5* Método =  getNomeArquivo *************************/
  public String getNomeArquivoA()
  {
     return nomeArquivoA;
  }          
  
  
/********  6o Método = GravarArquivo *************************/
// Cria os arquivos entrada e saída com a classe Formatter
// Para gravar dados (escrever nos arquivos) de entrada e saída
  public void gravarArquivo()
   {
    try
      {  gravaEntrada = new Formatter(nomeArquivoEnt); 
         gravaSaída = new Formatter(nomeArquivoSai);        
      }
      catch (FileNotFoundException fileNotFoundException)
      {  System.err.println("Ocorreu um erro");
         System.exit(1); 
      } 
   }    
  
  /********  7o Método =  FecharArquivo *************************/
  public void fecharArquivo()
  {    
    if (gravaEntrada != null)
        gravaEntrada.close();
    if (gravaSaída != null)
        gravaSaída.close();       
  }   
  
  /********  8* Método =  getSeçãoLida *************************/
  public double[] getSeçãoLida()
  {
      return SeçãoLida;
  }   
        
        
  /********  9o Método =  gravarDados *************************/
  public void gravarDados()
  {     
   gravaDadosEntrada(); 
   gravaDadosSaída();       
  } 
  /********  10o Método =  gravaDadosEntrada *************************/
  public void gravaDadosEntrada()
  {
    //double[]vetorTeste;
   // vetorTeste = new double[2];
    //vetorTeste[0] = 10;
    //vetorTeste[1] = 11;
    //imprimeVetorDoubleEnt(vetorTeste);
    // imprimeVetorDoubleEnt(getSeção());   
     // O método getSeção() está na classe Interface_Dados
  
  // O método getSeção() está na classe "Dados"
      imprimeVetorDoubleEnt(getSEÇÃO());
  
  }
  
  /********  11* Método =  imprimeVetorDoubleEnt *************************/
  public void imprimeVetorDoubleEnt(double[]vetorDouble)
  {
    for (int linha = 0; linha < vetorDouble.length ; linha++)
    {
        gravaEntrada.format("%.2f\t",vetorDouble[linha]);
        gravaEntrada.format("\r\n");
    }
  }
  
   /********  12* Método = gravaDadosSaida *************************/
  public void gravaDadosSaída()
  {
    // O método getSeção() está na classe Interface_Dados  
    imprimeVetorDoubleSai(getSEÇÃO(),"Vetor Seção Transversal:");      
  }  
  /******** 13* Método =  imprimeVetorDoubleSai *************************/
  public void imprimeVetorDoubleSai(double [] vetorDouble,String vetorId)
  {
    gravaSaída.format("\r\n");
    gravaSaída.format("--> %s\r\n\r\n",vetorId);
    for (int linha = 0; linha < vetorDouble.length ; linha++)
    {
        gravaSaída.format("[" +  (linha+1) +  "] = ");
        gravaSaída.format("%10.4f \t",vetorDouble[linha]);
        gravaSaída.format("\r\n");
    }
        gravaSaída.format("\r\n");
  }// fim 
}
