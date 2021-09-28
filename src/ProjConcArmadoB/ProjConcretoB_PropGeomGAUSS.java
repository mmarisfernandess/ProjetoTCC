package ProjConcArmadoB;

//  Programado por KATRI INGRID IKA FERREIRA - DATA: 10/08/21
/*
    Esta classe calcula as propriedades geométricas de 
    seções transversais quaisquer pelas integrais de GAUSS
    Centróide = CG, Momentos de inércia, Produto de inércia.  
    Seções = Inicialmente calcular para: 
             1) Seção retangular
             2) Seção T
             3) Seção I (para concreto protendido)
             4) Seção I com mísulas
             5) Seção L,  etc. (Seções não simétricas)
*/
/*  Calcula as propriedades geométricas da seção
    VARIÁVEIS DO PROBLEMA = VETOR PropSeção [6]
    PropSeção[0]=Área   // Área da seção transversal
    PropSeção[1]=XCG; // XCG da seção
    PropSeção[2]=YCG; // YCG da seção
    PropSeção[3]=Ixx;  // Momento de inércia em torno do eixo X (horizontal)
    PropSeção[4]=Iyy;  // Momento de inércia em torno do eixo y (vertical)
    PropSeção[5]=Ixy; // Produto de inércia  
*/
//*************************************************************
       //  SEÇÕES SIMÉTRICAS em relação ao EIXO VERTICAL
       //*************************************************************
       //  SEÇÃO RETANGULAR,  SEÇÃO T,  SEÇÃO I   
       //********************************************************************
       //  SEÇÕES NÃO SIMÉTRICAS - USO DAS EQUAÇÕES DO CÍRCULO DE MOHR 
       //                   PARA OBTENÇÃO DOS EIXOS PRINCIPAIS DE INÉRCIA
       //*********************************************************************
       //  SEÇÃO L 
 //********************************************************************** 
public class ProjConcretoB_PropGeomGAUSS extends ProjConcretoB_Dados
{
   // Declaração de variáveis de instância  
   private String nomeSeção; 
   private int     nSec;   // Número de nós da seção - poligonal CG = nSec+1
   
   private double bw, h, bf, hf, bfs, hfs, bfi,  hfi; 
      
   private double  Seção[],PropSeção[], InérciasPrincipais[];
               
   private double  PoligonalGauss[][], PoligonalCG[][];   
 
   private double  MatrizGauss[][], Qsi[], Eta[], DeltaQsi[], DeltaEta[], Qx, Qy;
   
   private double  QsiCG[], EtaCG[], DeltaQsiCG[], DeltaEtaCG[],
                   CoordXcg[], CoordYcg[];
   
   private double  Área, YCG, XCG, Ixx, Iyy, Ixy, I1, I2, Op1;
   
   private double  Cb, Ct, kb, kt, Wi, Ws, PropSeçãoCP[];  // Para Protendido  
   
   //******************************************************************
   // 1* Método CONSTRUTOR
   public ProjConcretoB_PropGeomGAUSS() 
   {   
      // Vetor de propriedades geométricas 
      PropSeção = new double[6];  
      // Vetor de eixos principais (Angulo TetaP, I1, I2)
      InérciasPrincipais = new double[3];
   }// Fim do método construtor
   //*******************************************************************  
   //******************************************************************* 
   public void setnSec()
   {   
      // Método getNomeSeção está na outra classe DADOS
      nomeSeção = getNomeSeção(); // COLOCA na variável de instancia
      System.out.printf("Dentro de nSec => nomeSeção=%s\n",nomeSeção);
      
      if("".equals(nomeSeção))
      {    nSec = 0;        }  
      if("SEÇÃO RETANGULAR".equals(nomeSeção))
      {   nSec = 4;   
          System.out.printf("Dentro setnSec DEPOIS=  nSec=%d\n",nSec);
      }
      if("SEÇÃO T".equals(nomeSeção))
       {    nSec = 8;       }
      if("SEÇÃO I".equals(nomeSeção)) 
       {    nSec = 12;   
            System.out.printf("Dentro setnSec DEPOIS=  nSec=%d\n",nSec);
       }
      if("SEÇÃO L".equals(nomeSeção))
       {    nSec = 6;      
            System.out.printf("Dentro setnSec DEPOIS=  nSec=%d\n",nSec);
       }
      System.out.printf("Dentro de nSec=>  nSec=%d\n",nSec);
   }
   public int getnSec()
   {     return nSec;    }
   //******************************************************************* 
   
   //******** 2* Método = setPoligonalGAUSS ********************************
   // Coordenadas dos pontos da seção em relação ao ponto inferior esquerdo
   // Coordenadas qsi  e  eta => (qsi,eta)
   public double[][] setPoligonalGAUSS() // Calcula as coordenadas (x,y)=(qsi,eta)
   {    
       System.out.printf("Dentro setPoligonalGAUSS =  nSec=%d\n",nSec);
             
       Seção = getSEÇÃO(); // Seção está na classe "Dados"   
       
       bw  = Seção[0];  h   = Seção[1];
       bf  = Seção[2];  hf  = Seção[3];
       bfs = Seção[4];  hfs = Seção[5];  bfi = Seção[6];  hfi  = Seção[7];
       
       PoligonalGauss = new double[2][nSec+1]; 
              
       // Seção RETANGULAR 
       // Numeração pelo canto inferior esquerdo, girando no sentido anti-horário
       // RETÂNGULO // 5 nós mas tem 4 eta, qsi e 4  
       if("SEÇÃO RETANGULAR".equals(nomeSeção))
       {
         System.out.printf("Dentro Seção Retangular setPoligonalGAUSS\n"); 
         PoligonalGauss[0][0]=(0.0);    PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][1]=(bw);     PoligonalGauss[1][1]=(0.0);
         PoligonalGauss[0][2]=(bw);     PoligonalGauss[1][2]=(h);
         PoligonalGauss[0][3]=(0.0);    PoligonalGauss[1][3]=(h);
         PoligonalGauss[0][4]=(0.0);    PoligonalGauss[1][4]=(0.0);  
       } 
       //*******************************************************************************
       if("SEÇÃO T".equals(nomeSeção))// Colocar para uma seção Não simétrica
       { 
         PoligonalGauss[0][0] =((bf-bw)/2);      PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][1] =((bf-bw)/2+bw);   PoligonalGauss[1][1]=(0.0);
         PoligonalGauss[0][2] =((bf-bw)/2+bw);   PoligonalGauss[1][2]=(h-hf);
         PoligonalGauss[0][3] =(bf);             PoligonalGauss[1][3]=(h-hf);
         PoligonalGauss[0][4] =(bf);             PoligonalGauss[1][4]=(h);
         PoligonalGauss[0][5] =(0.0);            PoligonalGauss[1][5]=(h);
         PoligonalGauss[0][6] =(0.0);            PoligonalGauss[1][6]=(h-hf);
         PoligonalGauss[0][7] =((bf-bw)/2);      PoligonalGauss[1][7]=(h-hf);
         PoligonalGauss[0][8] =((bf-bw)/2);      PoligonalGauss[1][8]=(0.0);         
       }
       //******************************************************************************
       if("SEÇÃO I".equals(nomeSeção)) // Colocar para uma seção GERAL 
       {
         System.out.printf("Dentro setPoligonalGAUSS I=  nSec=%d\n",nSec);
         PoligonalGauss[0][0] =(0.0);            PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][1] =(bfi);            PoligonalGauss[1][1]=(0.0);
         PoligonalGauss[0][2] =(bfi);            PoligonalGauss[1][2]=(hfi);
         PoligonalGauss[0][3] =(bfi-(bfi-bw)/2); PoligonalGauss[1][3]=(hfi);
         PoligonalGauss[0][4] =(bfs-(bfs-bw)/2); PoligonalGauss[1][4]=(h-hfs);
         PoligonalGauss[0][5] =(bfs);            PoligonalGauss[1][5]=(h-hfs);
         PoligonalGauss[0][6] =(bfs);            PoligonalGauss[1][6]=(h);
         PoligonalGauss[0][7] =(0.0);            PoligonalGauss[1][7]=(h);
         PoligonalGauss[0][8] =(0.0);            PoligonalGauss[1][8]=(h-hfs);
         PoligonalGauss[0][9] =((bfs-bw)/2);     PoligonalGauss[1][9]=(h-hfs);
         PoligonalGauss[0][10]=((bfi-bw)/2);     PoligonalGauss[1][10]=(hfi);
         PoligonalGauss[0][11]=(0.0);            PoligonalGauss[1][11]=(hfi);
         PoligonalGauss[0][12]=(0.0);            PoligonalGauss[1][12]=(0.0);         
       }
       //********************************************************************************** 
       if("SEÇÃO L1".equals(nomeSeção))// Colocar 4 opções de desenho
       {
         System.out.printf("Dentro setPoligonalGAUSS L1 =  nSec=%d\n",nSec);
         PoligonalGauss[0][0]=(0.0);      PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][1]=(bw);       PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][2]=(bw);       PoligonalGauss[1][2]=(h - hf);
         PoligonalGauss[0][3]=(bf);       PoligonalGauss[1][3]=(h - hf);
         PoligonalGauss[0][4]=(bf);       PoligonalGauss[1][4]=(h);
         PoligonalGauss[0][5]=(0.0);      PoligonalGauss[1][5]=(h);
         PoligonalGauss[0][6]=(0.0);      PoligonalGauss[1][6]=(0.0);
       }
       //***************************************************************************************
       //********************************************************************************** 
       if("SEÇÃO L2".equals(nomeSeção))// Colocar 4 opções de desenho
       {
         System.out.printf("Dentro setPoligonalGAUSS L2 =  nSec=%d\n",nSec);
         PoligonalGauss[0][0]=(bf-bw);    PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][1]=(bf);       PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][2]=(bf);       PoligonalGauss[1][2]=(h);
         PoligonalGauss[0][3]=(0.0);      PoligonalGauss[1][3]=(h);
         PoligonalGauss[0][4]=(0.0);      PoligonalGauss[1][4]=(h-hf);
         PoligonalGauss[0][5]=(bf-bw);    PoligonalGauss[1][5]=(h-hf);
         PoligonalGauss[0][6]=(bf-bw);    PoligonalGauss[1][6]=(0.0);
       }
       //***************************************************************************************
       //********************************************************************************** 
       if("SEÇÃO L3".equals(nomeSeção))// Colocar 4 opções de desenho
       {
         System.out.printf("Dentro setPoligonalGAUSS L3 =  nSec=%d\n",nSec);
         PoligonalGauss[0][0]=(0.0);      PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][1]=(bf);       PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][2]=(bf);       PoligonalGauss[1][2]=(hf);
         PoligonalGauss[0][3]=(bw);       PoligonalGauss[1][3]=(hf);
         PoligonalGauss[0][4]=(bw);       PoligonalGauss[1][4]=(h);
         PoligonalGauss[0][5]=(0.0);      PoligonalGauss[1][5]=(h);
         PoligonalGauss[0][6]=(0.0);      PoligonalGauss[1][6]=(0.0);
       }
       //***************************************************************************************
       //********************************************************************************** 
       if("SEÇÃO L4".equals(nomeSeção))// Colocar 4 opções de desenho
       {
         System.out.printf("Dentro setPoligonalGAUSS L4 =  nSec=%d\n",nSec);
         PoligonalGauss[0][0]=(0.0);      PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][1]=(bf);       PoligonalGauss[1][0]=(0.0);
         PoligonalGauss[0][2]=(bf);       PoligonalGauss[1][2]=(h);
         PoligonalGauss[0][3]=(bf-bw);    PoligonalGauss[1][3]=(hf);
         PoligonalGauss[0][4]=(bf-bw);    PoligonalGauss[1][4]=(hf);
         PoligonalGauss[0][5]=(0.0);      PoligonalGauss[1][5]=(hf);
         PoligonalGauss[0][6]=(0.0);      PoligonalGauss[1][6]=(0.0);
       }
       //***************************************************************************************
       
       return PoligonalGauss;
   }  
   //******************************************************************  
 
   
   //******************************************************************
   // 2* Método PropGeométricas
   public void setPropGeométricas() // Calcula as propriedades geométricas
   {   
       System.out.printf("Dentro setPropGeometricas =  nSec=%d\n",nSec);
       MatrizGauss = new double[4][nSec];
       
       Qsi=new double[nSec];       Eta=new double[nSec];            
       DeltaQsi=new double[nSec];  DeltaEta=new double[nSec];    
       
       CoordXcg = new double[nSec+1];  CoordYcg = new double[nSec+1];
       
       QsiCG=new double[nSec];       EtaCG=new double[nSec];            
       DeltaQsiCG=new double[nSec];  DeltaEtaCG=new double[nSec]; 
       
       
       for(int i=0; i<nSec; i++) // Gira de 0, 1, 2  e 3 DELTAS
       {
            DeltaQsi[i]= PoligonalGauss[0][i+1] - PoligonalGauss[0][i];
            DeltaEta[i]= PoligonalGauss[1][i+1] - PoligonalGauss[1][i];
            Qsi[i]= PoligonalGauss[0][i];
            Eta[i]= PoligonalGauss[1][i];                
       }     
       //**********************************************************************
       // 1* Sistema de eixos coordenados em relação ao ponto 
       //            inferior esquerdo da seção para cálculo das integrais
       //            para cálculo de área e momentos estáticos de área
       //**********************************************************************
       // Cálculo da ÁREA da seção transversal
          Área = 0.0;          
          for(int i=0; i<nSec; i++)
          {
              Área = Área -1.0 *((DeltaEta[i]/2.0+ Eta[i])*DeltaQsi[i]);
          }
       //*********************************
       // Cálculo do CENTRÓIDE da seção       
       //************************************************************      
       // Cálculo do MOMENTO ESTÁTICO de ÁREA em relação a X  =>  Qx
          Qx= 0.0;          
          for(int i=0; i<nSec; i++)
          {
            Qx = Qx -1.0*
             ((Math.pow(DeltaEta[i],2)/3.0+Eta[i]*(DeltaEta[i]+Eta[i]))*DeltaQsi[i]/2.0);        
          }
       //********************************
       // Cálculo de YCG (Ybarra)
          YCG = Qx/Área;
          Cb = YCG;       
       //********************************
       // Cálculo de Ct=h-Cb     
          Ct = Seção[1] - Cb;       
       //*************************************************************
       // Cálculo do MOMENTO ESTÁTICO de ÁREA em relação a Y  =>  Qy
          Qy= 0.0;
          for(int i=0; i<nSec; i++)
          {
            Qy = Qy - 1.0*                 
             ((Qsi[i]*(DeltaEta[i]/2.0+Eta[i])+DeltaQsi[i]*(DeltaEta[i]/3.0+Eta[i]/2.0))*DeltaQsi[i]);
          }  
       //********************************   
       // Cálculo de XCG (Xbarra)
          XCG = Qy/Área;          
       //********************************
      
       //**********************************************************************
       // 2* Sistema de eixos coordenados em relação ao CG da seção para 
       //            cálculo das integrais para momentos e produtos de inércia
       //**********************************************************************
       // Coordenadas cartesianas em relação ao CG da seção (EtaCG, QsiCG)
         for(int i=0; i<=nSec; i++) 
         {
              CoordXcg[i]= PoligonalGauss[0][i] - XCG; 
              CoordYcg[i]= PoligonalGauss[1][i] - YCG;              
         }      
         for(int i=0; i<nSec; i++) 
         {
              QsiCG[i]= CoordXcg[i];
              EtaCG[i]= CoordYcg[i];              
         }     
       //**********************************************************************
       // Coordenadas cartesianas em relação ao CG da seção (EtaCG, QsiCG)
         for(int i=0; i<nSec; i++) 
         {             
              DeltaQsiCG[i]= CoordXcg[i+1] - CoordXcg[i];
              DeltaEtaCG[i]= CoordYcg[i+1] - CoordYcg[i];            
         }     
       //**********************************************************************
       // Cálculo do MOMENTO de INÉRCIA em RELAÇÃO AO EIXO Xcg
         Ixx = 0.0;          
         for(int i=0; i<nSec; i++)
         {
           Ixx = Ixx -1.0*(( Math.pow(DeltaEtaCG[i],3)/4.0 + EtaCG[i]*(Math.pow(DeltaEtaCG[i],2)  + 
                             EtaCG[i]*(1.5*DeltaEtaCG[i] + EtaCG[i])))*DeltaQsiCG[i]/3.0);
         }    
       //**********************************************************************
       // Cálculo do MOMENTO de INÉRCIA em RELAÇÃO AO EIXO Ycg       
         Iyy=0.0;
         for(int i=0; i<nSec; i++)
         {             
           Iyy = Iyy  + 1.0* (( Math.pow(DeltaQsiCG[i],3)/4.0 + QsiCG[i]*(Math.pow(DeltaQsiCG[i],2)  + 
                           QsiCG[i]*(1.5*DeltaQsiCG[i] + QsiCG[i])))*DeltaEtaCG[i]/3.0);
         } 
       //**********************************************************************
       // Cálculo do PRODUTO de INÉRCIA em RELAÇÃO AO EIXO XY
         Ixy=0.0;  
         for(int i=0; i<nSec; i++)
         {
           Ixy = Ixy + 1.0* (QsiCG[i]*( Math.pow(DeltaEtaCG[i],2)/3.0 
                   
                           + EtaCG[i]*(DeltaEtaCG[i] + EtaCG[i]))
                   
                           + DeltaQsiCG[i]*(Math.pow(DeltaEtaCG[i],2)/4.0  
                   
                           + EtaCG[i]*(2*DeltaEtaCG[i]/3.0 + EtaCG[i]/2))) * DeltaQsi[i]/2.0;
         }                  
       //**********************************************************************
       // Coloca os valores de qsi, eta etc nas linhas da MatrizGauss
         for(int i=0; i<nSec; i++)
         {
            MatrizGauss[0][i]= QsiCG[i];
            MatrizGauss[1][i]= EtaCG[i];
            MatrizGauss[2][i]= DeltaQsiCG[i];
            MatrizGauss[3][i]= DeltaEtaCG[i];            
         }
        // Coloca as propriedades da seção no vetor PropSeção   
            PropSeção[0]= Área;
            PropSeção[1]= XCG;
            PropSeção[2]= YCG;
            PropSeção[3]= Ixx;
            PropSeção[4]= Iyy;
            PropSeção[5]= Ixy;   
            
        // Coloca as propriedades da seção no vetor PropSeçãoCP  
        // Seções para Concreto Protendido
           /* PropSeçãoCP[0]= Cb;
            PropSeçãoCP[1]= Ct;
            PropSeçãoCP[2]= kb;
            PropSeçãoCP[3]= kt;
            PropSeçãoCP[4]= Wi;
            PropSeçãoCP[5]= Ws;
          */
   }// Fim do método PropGeométricas
   //*******************************************************************
   
   
   //*************************************************************************  
   public void setInérciasPrincipais()
   {
        // Calcula os ângulos principais e os momentos principais de inércia
        Op1 = Math.toDegrees(Math.atan((-2*Ixy)/(Ixx-Iyy)))/2;
        InérciasPrincipais[0]= Op1;
        I1 = (Ixx+Iyy)/2+Math.sqrt(Math.pow((Ixx-Iyy)/2,2)+Math.pow(Ixy,2));
        InérciasPrincipais[1]= I1;
        I2 = (Ixx+Iyy)/2-Math.sqrt(Math.pow((Ixx-Iyy)/2,2)+Math.pow(Ixy,2));
        InérciasPrincipais[2]= I2;
   }
   //*************************************************************************
   public double[] getInérciasPrincipais()
   {  return InérciasPrincipais;
   }   
   //******************************************************************* 
   public double [] getEta()
   {    return Eta;        }  
   //*******************************************************************  
   public double [] getQsi()
   {    return Qsi;        }  
   //*******************************************************************   
   public double [] getDeltaQsi()
   {    return DeltaQsi;   }  
   //*******************************************************************
   public double [] getDeltaEta()
   {    return DeltaEta;   }     
   //******************************************************************   
   public double [][] getMatrizGauss()
   {   return MatrizGauss;  }
   //******************************************************************
   public double getÁrea()
   {    return Área;        }  
   //*******************************************************************
   public double getQx()
   {    return Qx;   }  
   //*******************************************************************   
   public double getYCG()
   {    return YCG;   }  
   //*******************************************************************  
   public double getQy()
   {    return Qy;   }  
   //*******************************************************************   
   public double getXCG()
   {    return XCG;   }  
   //*******************************************************************     
   public double[] getCoordXcg()
   {    return CoordXcg;   }  
   //*******************************************************************   
   public double[] getCoordYcg()
   {    return CoordYcg;   }  
   //*******************************************************************
   public double getIxx()
   {    return Ixx;        }  
   //*******************************************************************
   public double getIyy()
   {    return Iyy;        }  
   //*******************************************************************
   public double getIxy()
   {    return Ixy;       }  
   //*******************************************************************
   // Método que retorna o vetor com as propriedades geométricas da seção
   public double [] getPropGeom()
   {    return PropSeção;  }
   //********************************************************************
   public double [] getPropSeçãoCP()
   {    return PropSeçãoCP;  }
   //******************************************************************
   
   

   //************ DESENHAR a seção por poligonal
   // O cg da seção coincide com o centro do JPanel do centro da janela
   /********   Método = setPoligonal ****************************************/
   // Para pegar a poligonal da seção para desenhar na classe 
   // Interface_Painel_Desenho. 
   // Pega as coordenadas (x,y) dos pontos (nós) da seção 
   // Coordenadas em relação ao CG da seção   
   public double[][] setPoligonalCG()
   {        
       System.out.printf("Dentro PoligonalCG =  nSec=%d\n",nSec);
       System.out.printf("nomeSeção=%s\n",nomeSeção);
       
      PoligonalCG =  new double[2][nSec+1]; 
      
      Seção = getSEÇÃO(); // Seção está na classe "Dados"  
      bw  = Seção[0];  h   = Seção[1];
      bf  = Seção[2];  hf  = Seção[3];
      bfs = Seção[4];  hfs = Seção[5];  bfi = Seção[6];  hfi  = Seção[7];             
        
      if("SEÇÃO RETANGULAR".equals(nomeSeção))
      {
          System.out.printf("dentro do IF seção REtangualr\n");
        // Seção RETANGULAR   ***   PoligonalCG[2][5]  *************************      
        // Coordenadas (X,Y)=(0,1) 
        PoligonalCG[0][0]=(0.0 - bw/2);    PoligonalCG[1][0]=(0.0 - h/2);
        PoligonalCG[0][1]=(bw  - bw/2);    PoligonalCG[1][1]=(0.0 - h/2);
        PoligonalCG[0][2]=(bw  - bw/2);    PoligonalCG[1][2]=( h  - h/2);
        PoligonalCG[0][3]=(0.0 - bw/2);    PoligonalCG[1][3]=( h  - h/2);
        PoligonalCG[0][4]=(0.0 - bw/2);    PoligonalCG[1][4]=(0.0 - h/2);
      }
      //************************************************************************  
      if("SEÇÃO T".equals(nomeSeção))
      {
        // Seção T   ***   PoligonalCG[2][9]   *********************************  
        // Coordenadas (X,Y)=(0,1) 
        PoligonalCG[0][0]=(0.0 - bw/2);    PoligonalCG[1][0]=(0.0-YCG);
        PoligonalCG[0][1]=(bw/2);          PoligonalCG[1][1]=(0.0-YCG);
        PoligonalCG[0][2]=(bw/2);          PoligonalCG[1][2]=(h-hf-YCG);
        PoligonalCG[0][3]=(0.0+XCG);       PoligonalCG[1][3]=(h-hf-YCG);
        PoligonalCG[0][4]=(0.0+XCG);       PoligonalCG[1][4]=(h - YCG);
        PoligonalCG[0][5]=(0.0-XCG);       PoligonalCG[1][5]=(h - YCG);
        PoligonalCG[0][6]=(0.0-XCG);       PoligonalCG[1][6]=(h-hf-YCG);
        PoligonalCG[0][7]=(0.0 - bw/2);    PoligonalCG[1][7]=(h-hf-YCG);
        PoligonalCG[0][8]=(0.0 - bw/2);    PoligonalCG[1][8]=(0.0-YCG);
      } 
      //************************************************************************  
       if("SEÇÃO I".equals(nomeSeção))
      {  
          System.out.printf("dentro do IF seção I \n");
        // Seção I   ***    PoligonalCG[2][13]  ********************************
        // Coordenadas (X,Y)=(0,1) 
        PoligonalCG[0][0]= (0.0 - XCG);   PoligonalCG[1][0]=(0.0 - YCG);
        PoligonalCG[0][1]= (bfi - XCG);   PoligonalCG[1][1]=(0.0 - YCG);
        PoligonalCG[0][2]= (bfi - XCG);   PoligonalCG[1][2]=(0.0 - YCG + hfi);
        PoligonalCG[0][3]= (bw/2);        PoligonalCG[1][3]=(0.0 - YCG + hfi);
        PoligonalCG[0][4]= (bw/2);        PoligonalCG[1][4]=(h-hfs-YCG);
        PoligonalCG[0][5]= (bfs - XCG);   PoligonalCG[1][5]=(h-hfs-YCG);
        PoligonalCG[0][6]= (bfs - XCG);   PoligonalCG[1][6]= (h- YCG);
        PoligonalCG[0][7]= (0.0 - XCG);   PoligonalCG[1][7]=(h- YCG);
        PoligonalCG[0][8]= (0.0 - XCG);   PoligonalCG[1][8]=(h-hfs-YCG);
        PoligonalCG[0][9]=(-bw/2);        PoligonalCG[1][9]=(h-hfs-YCG);
        PoligonalCG[0][10]=(-bw/2);       PoligonalCG[1][10]=(0.0 - YCG + hfi);
        PoligonalCG[0][11]=(0.0 - XCG);   PoligonalCG[1][11]=(0.0 - YCG + hfi);       
        PoligonalCG[0][12]=(0.0 - XCG);   PoligonalCG[1][12]=(0.0 - YCG);          
      } 
      //************************************************************************/  
      if("SEÇÃO L".equals(nomeSeção))
      {
          System.out.printf("dentro do IF seção L \n");
        // Seção L   ***    PoligonalCG[2][7]   ********************************
        // Coordenadas (X,Y)=(0,1)           
        PoligonalCG[0][0]=(0.0 - XCG);         PoligonalCG[1][0]=(0.0 - YCG);
        PoligonalCG[0][1]=(0.0 - XCG + bw);    PoligonalCG[1][1]=(0.0 - YCG);
        PoligonalCG[0][2]=(0.0 - XCG + bw);    PoligonalCG[1][2]=(h -YCG - hf);
        PoligonalCG[0][3]=(0.0 + bf - XCG);    PoligonalCG[1][3]=(h -YCG - hf);
        PoligonalCG[0][4]=(0.0 + bf - XCG);    PoligonalCG[1][4]=(h-YCG);
        PoligonalCG[0][5]=(0.0 - XCG);         PoligonalCG[1][5]=(h-YCG);
        PoligonalCG[0][6]=(0.0 - XCG);         PoligonalCG[1][6]=(0.0 - YCG);
      }
      //************************************************************************ 
      return PoligonalCG;
   }    
 //****************************************************************************** 
   
    
   
//*********************************
}// Fim da Classe PropGeomGAUSS     
//******************************************************************************

 
 

