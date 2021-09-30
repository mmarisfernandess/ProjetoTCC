package ProjConcArmadoB;


public class ProjConcretoB_Flexão extends ProjConcretoB_PropGeomGAUSS
{
  public void CalculaFlexão()
  {
      Double Material[];
      Material = getMaterial();
      double fcd = Material[1];
      double fyd = Material[3];
      
      // Verificação do espaçamento horizontal 
      double SEÇÃO[];
      SEÇÃO = getSEÇÃO();
      
      double DETALHAMENTO[];
      DETALHAMENTO = getDETALHAMENTO();
      
      double bw = SEÇÃO[0];
      double cNom = DETALHAMENTO[0];
      double fiT = DETALHAMENTO[1];
      double fi = DETALHAMENTO[2];
      double nBarras = DETALHAMENTO[4];
      
      
      // Parâmetros temporários que herdam da classe AlgoritmoMid - valor fixo temporário
      double mid = 0.170;
      double ksi = 0.28;
      double ksiLinha = 0.41597;
      double Ecd = 3.5;
      double Esd = 9.0;
      //**************************************************************************************
      
      double zeta = (1-ksiLinha*ksi);
      // Parâmetro que vai herdar da classe dados quando entrar na caixa DETALHAMENTO - getd
      double d = 46.0;
      //*************************************************************************************
      double z = zeta*d;
      
      
      // Cálculo armadura
      // Parâmetro que vai herdar da classe dados quando entrar na caixa Solicitação - getMd
      double Md = 126.0;
      double As = ((1/fyd)*(Md/z));
      
 
      
      // Escolha do diâmetro da barra - razão entre As e AsBarra (área da barra)
      // double razãoÁrea;
      // razãoÁrea = As/AsBarra;
  
      
      
      

      
      // Verificação das camadas segundo a NBR 6118:2014
      double ahVerif;
      double ahoriz_A = 20.0;
      double ahoriz_B = DETALHAMENTO[5];
      double ahoriz_C = 22.8;
      
      if(ahoriz_A > ahoriz_B)
      {
          ahVerif = ahoriz_A;
          
      }
      else
          ahVerif = ahoriz_B;
      
      
      
      if(ahoriz_A > ahoriz_C)
      {
          ahVerif = ahoriz_A;
      }
      else
          ahVerif = ahoriz_C;
      
      
      
      if(ahoriz_B > ahoriz_C)
      {
          ahVerif = ahoriz_B;
      }
      else
          ahVerif = ahoriz_C;
      
 
      
      
      double ah = (bw-2*(cNom+fiT)-nBarras*fi)/3;
      
      if(ah > ahVerif)
      {
          System.out.println("Passou!");
      }
      else
          
          System.out.println("Não passou!");
      
  }
}
