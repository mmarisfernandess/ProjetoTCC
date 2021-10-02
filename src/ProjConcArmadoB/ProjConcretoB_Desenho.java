package ProjConcArmadoB;
// TCC - MARIANA  - DATA: 12/09/21

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke; // Para desenhar as linhas mais grossas
import java.awt.Font; // Para mudar a fonte de texto


import javax.swing.JPanel;
import javax.swing.JTextField;

// Bibliotecas para evento de mouse
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;



public class ProjConcretoB_Desenho extends JPanel implements MouseListener, MouseMotionListener // Herda a classe JPanel 
{
    // Variáveis de instancia
    private double Ximin,Ximax, Yimin,Yimax; 
    
    // Variáveis de instância para cálculo das escalas
    private int maxX, maxY, centroX, centroY;
    private double pixelLargura, pixelAltura, pixelTamanho;
    private double XXmax,  YYmax;  
    private double realLargura; // vai ser alterado em função dos vetor x
    private double realAltura;  // vai ser alterado em função dos vetor y         
    
    //***********************************
    private int centroX1, centroY1;
    //***********************************
    
    private int icodePutEscala=1;
    
    private double NumeroPixelPorMetro;
    private double CoordX, CoordY; // Coordenadas do mouse
    private JTextField H,V,metroTamanho; 
    private JTextField CampoXA, CampoYA; // Campo do mouse
    
    private ProjConcretoB_Arquivo dados;
    // Para desenhar seção e diagrama de deformação
    private int IcodeDesenhar; 
    private int IcodeDeformação;
    private int IcodeArmadura;
    
    private double bw, h, dlinha;
    
    //******************************************************
    // 1*MÉTODO ******** Método Construtor   **********************
    public ProjConcretoB_Desenho(JTextField Htransf,
                                 JTextField Vtransf, 
                                 JTextField metroTamanhotransf,
                                 ProjConcretoB_Arquivo dados,
                                 JTextField CampoX,
                                 JTextField CampoY)
    {     
       // Colocar ação de evento de mouse
       this.addMouseListener(this);
       this.addMouseMotionListener(this);
        
       H = Htransf;     
       V = Vtransf;   
       metroTamanho  = metroTamanhotransf;     
       this.dados = dados;
       CampoXA = CampoX;
       CampoYA = CampoY;
       
       IcodeDesenhar = 0; 
       IcodeDeformação = 0;
       IcodeArmadura = 0;
       
       
    }    
    //********************************************************************
    // 2*MÉTODO ******** Método paintComponent (PARA DESENHAR)************
    // Método "paintComponent" - Obrigatório programar
    // Recebe o objeto "g" da classe Graphics 
    //                               (Classe que tem os métodos de desenho)
    public void paintComponent(Graphics g)
    { 
        System.out.printf("dentro do paint DESENHO\n");
        // Acessar o método paintComponent já existente 
        // Escrever o código emcima do método já existente
        super.paintComponent(g);  
        this.setBackground(Color.WHITE);         
        // Para acessar a classe Graphics2D (espessura de linha  etc) 
        // Cria o objeto "g2d"                 
        Graphics2D g2d = (Graphics2D)g;    
        
        // Data:13/08/21 ******KATRI ***********************************
        calculaEscala();
        desenhaRetânguloLimite(g2d); // Desenha um retângulo no limite da área que deseja desenhar
     
        
        
        if(IcodeDesenhar==1)
        {
            g2d.setColor(Color.BLACK);
            // Chama o método desenhaEixos
            desenhaEixos(g2d);  
            g2d.setStroke(new BasicStroke(3.0f));
            DesenharSeção(g2d); 
        }
        
         if(IcodeDeformação==1)
        { 
            g2d.setStroke(new BasicStroke(3.0f));
            DesenharDiagramaDeformação(g2d); 
        }
  
         
         if(IcodeArmadura==1)
        { 
            g2d.setStroke(new BasicStroke(3.0f));
            DesenharArmadura(g2d); 
        }
         
         
    }
    
    
    
    
    public void desenhaEixos(Graphics g2d)
    {
        g2d.drawLine(centroX, centroY, iXD((Ximax-Ximin)/2*0.85), centroY);
        g2d.drawLine(centroX, centroY, centroX, iYD((Yimax-Yimin)/2*0.85));
    }
    
    
    
    
    public void DesenharPoligonal()
    {
        IcodeDesenhar = 1;
        repaint();
    }
    
    
    
    public void DesenharSeção(Graphics g2d)
    {
        g2d.setColor(Color.RED);
                
        double pontosSeção[][];
                
        // Rece os valores da poligonal da seção que está na classe PropGeomGauss 
        pontosSeção = dados.setPoligonalCG();
                
        int nSec = dados.getnSec();
        int nPontos = nSec+1;
        
        int xPixel[] = new int[nPontos];
        int yPixel[] = new int[nPontos];
        
        for (int i = 0; i < nPontos; i++)
        {
            xPixel[i] = iXD(pontosSeção[0][i]);
            yPixel[i] = iYD(pontosSeção[1][i]);
        }
        g2d.drawPolyline(xPixel,yPixel,nPontos); 
        
    }
    
    
    
    public void DesenharDeformação()
    {
        IcodeDeformação = 1;
        repaint();
    }
    
    
    public void DesenharDiagramaDeformação(Graphics2D g2d) 
    {
        double SeçãoHerda[] = dados.getSEÇÃO();
        bw = SeçãoHerda[0];
        h = SeçãoHerda[1];
        dlinha = SeçãoHerda[2];
        dlinha = 4.0; // 4 cm
        
       // g2d.translate(iXD(1.5*bw), 0);
        
        // Esd   e Ecd obter da programação da MARIANA
        //double Esd =  9.0/1000.;
        //double Ecd = 3.5/1000.;    
        
        double Esd =  2.0/1000.;
        double Ecd = 3.5/1000.;  
        
        double []xx,yy;
        xx = new double[5];   yy = new double[5];
        xx[0]=   2.0*bw; 
        yy[0]= - h/2;
        
        xx[1]=(2.0*bw - Esd*1000.0); // Escala do diagrama]
        yy[1]= - h/2;
        
        xx[2]=(2.0*bw + Ecd*1000.0);          
        yy[2]= + h/2;
        
        xx[3]=2.0*bw;  
        yy[3]= + h/2;
        
        xx[4]=2.0*bw; 
        yy[4]=- h/2;
        
        g2d.setColor(Color.GREEN);
         int[]Px,Py;          
         Px = new int[5];
         Py = new int[5];
         for(int i=0;i<5;i++)
        {
           Px[i]=iXD(xx[i]); 
           Py[i]=iYD(yy[i]); 
        }
        g2d.drawPolyline(Px, Py, 5);   
    }
    
    
    
    
     public void DesenharBarrasAço()
    {
        IcodeArmadura = 1;
        repaint();
    }
    
   
     
    public void DesenharArmadura(Graphics2D g2d) 
    {
        g2d.setColor(Color.BLUE);
        
        
        double x1 = bw/4 - bw/16;
        double y1 = -h/2 + dlinha - bw/16;
        double x2 = bw/4 - bw/16 + bw/8;
        double y2 = - h/2 + dlinha - bw/16 + bw/8;  
        
        int A1x = iXD(x1);
        int A1y = iYD(y1);
        int A2x = iXD(x1);
        int A2y = iYD(y1);
        
        g2d.fillOval(A1x, A1y, A2x, A2y);
    }
    
    
    
    
    
/******************************************************************************/    

    
    
    
    //********* 3, 4, 5 e 6* MÉTODOS ********************************
    public void LimitesXimin(double Ximin)
    {       this.Ximin=Ximin;      }
    public void LimitesXimax(double Ximax)
    {       this.Ximax=Ximax;      }
    public void LimitesYimin(double Yimin)
    {       this.Yimin=Yimin;      }
    public void LimitesYimax(double Yimax)
    {       this.Yimax=Yimax;      
            repaint(); // Redesenha somente por este método
    }
   //********* FIM  MÉTODOS ********************************
    
    /****************************************************
    * 7* MÉTODO - calculaEscala
    ****************************************************/
    public void calculaEscala()
    {
     System.out.printf("No início de calculaEscala \n");    
        // Largura e altura em pixels da janela definida no dispositivo
        maxX = getWidth()-1;// Largura medida em intervalos de pixels
        maxY = getHeight()-1;// Altura medida em intervalos de pixels
        System.out.printf("maxX (pixels) = %d\n", maxX);
        System.out.printf("maxY (pixels) = %d\n", maxY);   
        
        if(icodePutEscala==1)
        {
         //**********************************************
         XXmax=Math.abs(Ximax-Ximin);//largura em dimensão real(mundo)
         XXmax=XXmax*1.10;
         YYmax=Math.abs(Yimax-Yimin);//altura  em dimensão real(mundo)
         YYmax=YYmax*1.10;  
         //**********************************************                

         realLargura=XXmax;
         realAltura=YYmax;  
         System.out.printf("realLargura INICIAL: %.3f\n",realLargura);
         System.out.printf("realAltura INICIAL: %.3f\n",realAltura);
         
        }// Fim do if       
        //********************************************** 
        // Mapeamento isotrópico
        pixelLargura = realLargura/maxX; // Variável double
        pixelAltura  = realAltura/maxY;
        pixelTamanho = Math.max(pixelLargura, pixelAltura);  
        realLargura  = pixelTamanho*maxX;
        realAltura   = pixelTamanho*maxY;     
        // Coordenadas do centro da tela em pixel        
        centroX = maxX/2;
        // centroX = centroX-iXD((Ximax-Ximin))/2;
        centroY = maxY/2;
        // centroY = centroY-iYD((Yimax-Yimin))/2;       
           
        System.out.printf("Largura total do JPanel em pixel: %d\n",getWidth());
        System.out.printf("Altura total do JPanel em pixel: %d\n",getHeight());
        System.out.printf("maxX (pixels) = %d\n", maxX);
        System.out.printf("maxY (pixels) = %d\n", maxY);  
        System.out.printf("centroX = %d\n",centroX);
        System.out.printf("centroY = %d\n",centroY);        
        System.out.printf("XXmax = %.3f\n",XXmax);
        System.out.printf("YYmax = %.3f\n",YYmax);
        System.out.printf("realLargura = %.3f\n",realLargura);
        System.out.printf("realAltura = %.3f\n",realAltura);      
        System.out.printf("pixelLargura = %10.6f\n",pixelLargura);
        System.out.printf("pixelAltura = %10.6f\n",pixelAltura);
        System.out.printf("pixelTamanho = %10.6f\n",pixelTamanho);
        System.out.printf("NumeroPixelPorMetro = %10.6f\n",NumeroPixelPorMetro);  

        
        NumeroPixelPorMetro = maxX/realLargura;  
        // NumeroPixelPorMetro é o escalaPixel
        H.setText(String.format("%.3f",realLargura));
        V.setText(String.format("%.3f",realAltura));   
        metroTamanho.setText(String.format("%.3f",NumeroPixelPorMetro));  
    }// Fim do método calculaEscala      
    /******************************************************/   
   
    
    
    /******************************************************/    
    //   8* MÉTODO - putEscala
    //***************************************************/
    public void putEscala(double NumPixelPorMetro)              
    { 
      // System.out.printf("Dentro do putEscala = \n");  
      // System.out.printf("NumPixelPorMetro = \n",NumPixelPorMetro);  
       realAltura  = maxY/NumPixelPorMetro; 
       realLargura = maxX/NumPixelPorMetro;  
       NumeroPixelPorMetro=NumPixelPorMetro;
      // System.out.printf("realAltura= %.4f \n",realAltura);
      // System.out.printf("realLargura= %.4f \n",realLargura); 
       icodePutEscala=2;
    } 
    /******************************************************/    
    //   9* MÉTODO - getEscala
    //***************************************************/
    public double getEscala()
    {
        // Número de pixels para 1 unidade (escalaPixel)
        return NumeroPixelPorMetro;  
    }       
    /******************************************************/    
    //   10* MÉTODO - RecalculaEscala
    //***************************************************/
    public void RecalculaEscala()
    { // Chama o paintComponent que chama o calculaEscala
        repaint();
    }
    /******************************************************/    
    //   11* MÉTODO - putAlturaV
    //***************************************************/
    public void putAlturaV(double valor)// Não está sendo usado
    {          
       realAltura  = valor;
       icodePutEscala=2;       
    } 
    /******************************************************/    
    //   12* MÉTODO - getAlturaV
    /***************************************/ 
    public double getAlturaV()
    {
        return realAltura;
    }
    /******************************************************/    
    //   13* MÉTODO - putLarguraH
    //***************************************************/
    public void putLarguraH(double valor)//Não está sendo usado
    {          
        realLargura  = valor;
        icodePutEscala=2;        
    }    
    /******************************************************/    
    //   14* MÉTODO - getLarguraH
    /******************************************************/ 
    public double getLarguraH()
    {
        return realLargura;
    }    
    //***********************************************************
    
    //***********************************************************  
    public void putCoord(int Xp, int Yp)
    { 
       CoordX = dXi1(Xp);
       CoordY = dYi1(Yp);     
    }
    //**********************************   
    public double getCoordX()
    {
       return CoordX;       
    }
    //**********************************   
    public double getCoordY()
    {
       return CoordY;       
    }   

    /***********************************************************/    
    //   Métodos dXi que transforma real em pixel para desenhar
    //   Entra um número inteiro e sai real
    //***********************************************************       
    public double dXi(int x) 
    {   return (x - centroX)*pixelTamanho;  }   
    //*****************************************************     
    public double dYi(int y)
    {   return (centroY - y)*pixelTamanho;  }    
    //********************************************************       
    public double dXi1(int Xc) // Entra inteiro e sai real
    {   return ((Xc - centroX1)*pixelTamanho + Ximin); }   
    //*****************************************************     
    public double dYi1(int Yc)
    {   return ((-Yc + centroY1)*pixelTamanho + Yimin); }       
    //***************************************************/
    //************ IGUAL DO CURSO 1 DE JAVA ************************
    public int iXD(double x)
    {
        // Desenha do centro da tela para a direita
        // eixo X positivo para direita
        double AA = (centroX + x/pixelTamanho);
        //System.out.printf(" dentro de iXD ==> AA= %.5f\n", AA);
        //double AA = (x/pixelTamanho);
        int valor = transfDoubleInt(AA);
        // valor = Número de pixels para desenhar a partir 
        //         do canto esquerdo da tela
        return valor;
    }
    //**************** IGUAL DO CURSO 1 DE JAVA **************************
    public int iYD(double y)
    {
        // Desenha do centro da tela para cima (eixo y positivo para cima)
        double AA = (centroY-y/pixelTamanho);
        // System.out.printf(" dentro de iYD ==> AA= %.5f\n", AA);
        //double AA = (- y/pixelTamanho);
        int valor = transfDoubleInt(AA);
        // valor = Número de pixels para desenhar a partir 
        //         do canto superior da tela 
        return valor;
    }
    
    //***************   Métodos NOVOS ************************ 
    public int iXD1(double Xc)
    {
        double AA = (centroX1 + ((Xc - Ximin)/pixelTamanho));
        int valor = transfDoubleInt(AA);
        return valor;
    }
     public int iYD1(double Yc)
    {
        double AA = (centroY1 - ((Yc - Yimin)/pixelTamanho));     
        int valor = transfDoubleInt(AA);     
        return valor;
    }  
    //**************************************************************  

    //**************************************************************    
    public int DeltaiXD(double x)
    {
        // Desenha do centro da tela para a direita
        // eixo X positivo para direita
        double AA = ( x/pixelTamanho);
       // System.out.printf(" dentro de iXD ==> AA= %.5f\n", AA);
        //double AA = (x/pixelTamanho);
        int valor = transfDoubleInt(AA);
        // valor = Número de pixels para desenhar a partir 
        //         do canto esquerdo da tela
        return valor;
    }  
    //***************************************************/
    public int DeltaiYD(double y)
    {
        // Desenha do centro da tela para cima (eixo y positivo para cima)
        double AA = (- y/pixelTamanho);
       // System.out.printf(" dentro de iYD ==> AA= %.5f\n", AA);
        //double AA = (- y/pixelTamanho);
        int valor = transfDoubleInt(AA);
        // valor = Número de pixels para desenhar a partir 
        //         do canto superior da tela 
        return valor;
    } 
    
    /****************************************************
    *    Método auxiliar 
    ***************************************************/
    public int transfDoubleInt(double valorReal)
    {
        // retorna um número double que é o mais próximo do valor do argumento
        // real arrendondado para o valor inteiro mais próximo
        // esse número real tem apenas uma casa decimal e o valor na casa
        // decimal é zero
        //System.out.printf("valorReal =%10.6f\n",valorReal);
        double BB = Math.rint(valorReal);
       // System.out.printf("BB =%10.6f\n",BB);
        // retorna a representação do valor inteiro em String
        String CC = String.valueOf(BB);
       // System.out.printf("CC = %s\n",CC);
        // retorna o tamanho da string (número de caracteres)
        int tamanhoString = CC.length();
       // System.out.printf("tamanho string = %d\n",tamanhoString);
        // retira o ponto e o zero do número BB
        int valor = tamanhoString - 2;
      //  System.out.printf("valor= tamanho string-2 = %d\n",valor);
        String DD = CC.substring(0,valor);
       // System.out.printf("String DD = %s\n",DD);
        int EE = Integer.parseInt(DD);
        return EE;        
    }// Fim do método
    
    //******************** KATRI Data: 13/08/21 *************
    public void desenhaRetânguloLimite(Graphics g2d)
    {     
        //*********************************************************************
        //Desenha área definida por Ximin; Ximax;Yimin;Yimax;
        int [] xlim ={(iXD(-(Ximax-Ximin)/2)),
                      (iXD((Ximax-Ximin)/2)),
                      (iXD((Ximax-Ximin)/2)),
                      (iXD(-(Ximax-Ximin)/2)),
                      (iXD(-(Ximax-Ximin)/2))};      
        centroX1=(iXD(-(Ximax-Ximin)/2)); //19/04/2020          
        for (int i=0; i<5; i++)
        {
         System.out.printf("xlim[%d] = %d\n", i, xlim[i]);
        }
        int [] ylim ={(iYD(-(Yimax-Yimin)/2)),
                    (iYD(-(Yimax-Yimin)/2)),
                     (iYD((Yimax-Yimin)/2)),
                      (iYD((Yimax-Yimin)/2)),
                     (iYD(-(Yimax-Yimin)/2))};
        centroY1=(iYD(-(Yimax-Yimin)/2)); //19/04/2020         
       // System.out.printf("centroX1 = %d  centroY1 = %d\n", centroX1, centroY1);       
        
        for (int i=0; i<5; i++)
        {
         // System.out.printf("ylim[%d] = %d\n", i, ylim[i]);
        }
        System.out.print("Antes de g2d.drawPolyline(xlim, ylim, 5) \n");
        // Desenha o retangulo definido por Ximin,Ximax, Yimin, Yimax         
        g2d.setColor(Color.BLUE);
        g2d.drawPolyline(xlim, ylim, 5);
       
        // Desenha a diagonal do retangulo definido por Ximin,Ximax, Yimin, Yimax 
        int [] xdiglim ={(iXD(-(Ximax-Ximin)/2)),                      
                       (iXD((Ximax-Ximin)/2))};
        int [] ydiglim ={(iYD(-(Yimax-Yimin)/2)),                  
                         (iYD((Yimax-Yimin)/2))};         
        // g2d.setColor(Color.RED);
        // g2d.drawPolyline(xdiglim, ydiglim, 2);        
        
        System.out.print("Depois de g2d.drawPolyline(xlim, ylim, 5) \n");
        // Fim do Desenha área definida por Ximin; Ximax;Yimin;Yimax;  
       
    } // Fim do método desenhaRetanguloLimite     
//******************************************************************************
    
    
    
    //*************************************************************
   // Capturando os eventos com o mouse = AÇÂO DE EVENTO DE MOUSE
   //*************************************************************    
   // Trata evento quando o mouse é PRESSIONADO
    public void mousePressed( MouseEvent event)
    {     }  
    // Trata evento quando o usuário arrasta o mouse 
    // com o botão pressionado
    public void mouseDragged( MouseEvent event)
    {       }
    // Trata evento quando o mouse é liberado depois 
    // da operação de ARRASTAR
   public void mouseReleased( MouseEvent event)
    {     }    
    // Trata evento quando o mouse entra em certa área especificada
    public void mouseEntered( MouseEvent event)
    {  this.setBackground(Color.MAGENTA);
    }        
    // Trata evento quando o mouse sai da área especificada
    public void mouseExited( MouseEvent event)
    {  this.setBackground( new java.awt.Color(231,234,240));        
    }         
    // Trata evento de clique de mouse 
    public void mouseClicked ( MouseEvent event)
    {    
    }   
    // Trata evento quando o usuário move o mouse
    public void mouseMoved( MouseEvent event)
    {   
        int CoordMouseXpixel = event.getX();   
        int CoordMouseYpixel = event.getY();     
        
        metroTamanho.setText(String.format("%.2f",getEscala()));
        V.setText(String.format("%.2f",getAlturaV()));
        H.setText(String.format("%.2f",getLarguraH()));
        metroTamanho.setHorizontalAlignment(JTextField.CENTER);
        V.setHorizontalAlignment(JTextField.CENTER);
        H.setHorizontalAlignment(JTextField.CENTER);
                  
        putCoord(CoordMouseXpixel,CoordMouseYpixel); 
        
        CampoXA.setText(String.format("%.2f",getCoordX()));
        CampoYA.setText(String.format("%.2f",getCoordY())); 
        CampoXA.setHorizontalAlignment(JTextField.CENTER);
        CampoYA.setHorizontalAlignment(JTextField.CENTER);            
    }           
//****************************************************************//  

    
 
//******************************************************************************   
}// Fim da classe "Desenho"
