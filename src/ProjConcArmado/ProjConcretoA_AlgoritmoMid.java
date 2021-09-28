package ProjConcArmado;
// TCC - Mariana Fernandes

public class ProjConcretoA_AlgoritmoMid extends ProjConcretoA_Dados 
{
  // Variáveis de instância = todos os métodos abaixo tem acesso a elas
    
  // ********************************************
  // 2o método - Elaboração da Tabela Universal    
  public void CálculoMid()
  {
     System.out.printf("Entrou no método CálculoFlexão\n");
     
     // ******************************** DOMÍNIO 2A ************************
     // Deformação do Aço:        Esd2A = 10.0
     // Deformação do Concreto:   0.0<= Ecd2A <= 2.0;
        
     double a, ksi, ksiLinha, midCalc, midMd;    
     double E2, E1, Ecd1a, Ecd2a;     
     double tolErro=100.0 ; 
     double tolCompara=0.3;  // Erro de 1.0 %
     double Passo1, Passo2;       
     // Limites inferior e superior de mid da Tabela Universal
     double midLinf2A, midLsup2A; // Limites inferior e superior de mid da Tabela Universal
     midLinf2A  = 0.025;        midLsup2A = 0.089;
     System.out.printf("midLinf2A = %.5f\n", midLinf2A);
     System.out.printf("midLsup2A = %.5f\n", midLsup2A);
     
     midMd = getmid(); // midMd obtido dos dados (Classe Dados)
     System.out.printf("getmidMd = %.5f\n", midMd); 
     
     if((midMd >= midLinf2A) && (midMd <= midLsup2A))
     {     
      // 1a TENTATIVA - Escrever 4 equações
      E2 = 0.002;      E1 = 0.0;    
      Ecd1a = (E2-E1)/2.0;  // INTERVALO DIVIDIDO POR 2
      Passo1 = Ecd1a;
     
      System.out.printf("Ecd1a = %.5f\n", Ecd1a);
      System.out.printf("Passo1 = %.5f\n", Passo1);
     
      a = (Ecd1a/0.002)-Math.pow((Ecd1a/0.002),2)/3; 
      System.out.printf("a = %.5f\n", a);
     
      ksi = Ecd1a/(Ecd1a + 0.01);
      System.out.printf("ksi = %.5f\n", ksi);
     
      ksiLinha = 1-(((2/0.006)-((Ecd1a)/0.000016))/((1-(((Ecd1a)/0.002)/3))/0.002));         
      System.out.printf("ksiLinha = %.5f\n", ksiLinha);
     
      midCalc = 0.85*a*ksi*(1-ksiLinha*ksi);
      System.out.printf("midCalc = %.5f\n", midCalc);
      
      midMd = getmid(); // midMd obtido dos dados (Classe Dados)
      System.out.printf("getmidMd = %.5f\n", midMd);         
       
      do
      {
        if(midCalc > midMd)
        {  
            System.out.printf("Entrou A em midCalc > midMd\n");
                 
            Ecd2a = ((Ecd1a-E1)/5.0)+E1;    // INTERVALO DIVIDIDO POR 5      
            
            a = (Ecd2a/0.002)-Math.pow((Ecd2a/0.002),2)/3;   
            ksi = Ecd2a/(Ecd2a + 0.01);                     
            ksiLinha = 1-(((2/0.006)-((Ecd2a)/0.000016))/((1-(((Ecd2a)/0.002)/3))/0.002));            
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;    
            
            System.out.printf("Ecd2a A= %.5f\n", Ecd2a);   
            System.out.printf("a A= %.5f\n", a);
            System.out.printf("ksi A= %.5f\n", ksi);
            System.out.printf("ksiLinha A= %.5f\n", ksiLinha);
            System.out.printf("midCalc A= %.5f\n", midCalc);                 
            System.out.printf("midMd A= %.5f\n", midMd);                   
            System.out.printf("tolErro A= %.5f\n", tolErro); 
            System.out.printf("tolCompara A= %.5f\n", tolCompara); 
            
            Passo1 = Ecd1a;
            Passo2 = Ecd2a;
            Ecd1a  = Ecd2a;
            
          /*  if(midCalc < midMd)
            {
              do
              {
               Ecd2a = ((Passo1 - Passo2)/20.0) + Passo2; // INTERVALO DIVIDIDO POR 20
               
               a = (Ecd2a/0.002)-Math.pow((Ecd2a/0.002),2)/3; 
               ksi = Ecd2a/(Ecd2a + 0.01);               
               ksiLinha = 1-(((2/0.006)-((Ecd2a)/0.000016))/((1-(((Ecd2a)/0.002)/3))/0.002));
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Ecd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
                       
       */ }// Fim do if *******************************************************************
        
        if(midCalc < midMd)
        {   
            System.out.printf("Entrou em midCalc B< midMd\n");
            
            Ecd2a = ((E2-Ecd1a)/5.)+Ecd1a;  // INTERVALO DIVIDIDO POR 5
            
            a = (Ecd2a/0.002)-Math.pow((Ecd2a/0.002),2)/3; 
            ksi = Ecd2a/(Ecd2a + 0.01);             
            ksiLinha = 1-(((2/0.006)-((Ecd2a)/0.000016))/((1-(((Ecd2a)/0.002)/3))/0.002));            
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0; 
            
            System.out.printf("Ecd2a B= %.5f\n", Ecd2a);   
            System.out.printf("a B= %.5f\n", a);
            System.out.printf("ksi B= %.5f\n", ksi);
            System.out.printf("ksiLinha B= %.5f\n", ksiLinha);
            System.out.printf("midCalc B= %.5f\n", midCalc);                 
            System.out.printf("midMd B= %.5f\n", midMd);  
            System.out.printf("tolErro B= %.5f\n", tolErro);           
            System.out.printf("tolCompara B= %.5f\n", tolCompara);
            
            Passo1 = Ecd1a;
            Passo2 = Ecd2a;
            Ecd1a  = Ecd2a;
            
            if(midCalc > midMd)
            {
              do
              {
               Ecd2a = ((Passo2 - Passo1)/20.) + Passo1; // INTERVALO DIVIDIDO POR 20
               
               a = (Ecd2a/0.002)-Math.pow((Ecd2a/0.002),2)/3; 
               ksi = Ecd2a/(Ecd2a + 0.01);               
               ksiLinha = 1-(((2/0.006)-((Ecd2a)/0.000016))/((1-(((Ecd2a)/0.002)/3))/0.002));
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Ecd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
        }//Fim do if ***********************************************************
    } // Chave do "do"              
      while(tolErro >= tolCompara);   
      
     } // FIM DO DOMINIO 2A
     
    // ********************************** DOMÍNIO 2B ****************************
    // Deformação do Aço:       Esd2B = 10.0
    // Deformação do Concreto:  2.0<= Ecd2B <= 3.5;
    // Limites inferior e superior de mid da Tabela Universal
    double midLinf2B, midLsup2B; 
    midLinf2B = 0.091;           midLsup2B = 0.159;
    System.out.printf("midLinf2B = %.5f\n",midLinf2B);
    System.out.printf("midLsup2B = %.5f\n",midLsup2B);
    double E2B, E1B, x1, x2;
    
    if((midMd >= midLinf2B) && (midMd <= midLsup2B))
     {
    // 1a TENTATIVA - Escrever 4 equações
      E2B = 0.0035;      E1B = 0.0020482; 
      System.out.printf("E2B = %.5f\n",E2B);
      System.out.printf("E1B = %.5f\n",E1B);
      Ecd1a = (E2B-E1B)/2.0;  // INTERVALO DIVIDIDO POR 2
      Passo1 = Ecd1a;
     
      System.out.printf("Ecd1a = %.5f\n", Ecd1a);
      System.out.printf("Passo1 = %.5f\n", Passo1);
     
      a = 1.0 - ((0.002/Ecd1a)/3); 
      System.out.printf("a = %.5f\n", a);
     
      ksi = Ecd1a/(Ecd1a + 0.01);
      System.out.printf("ksi = %.5f\n", ksi);
      
      x1 = (Ecd1a-2.0)/Ecd1a;       x2 = 2.0/Ecd1a;
      System.out.printf("x1 = %.5f\n", x1);
      System.out.printf("x2 = %.5f\n", x2);
     
      ksiLinha = (1/a)*((Math.pow(x1, 2)/2)+(2/3)*x2-(10/24)*(Math.pow(x2, 2)));         
      System.out.printf("ksiLinha = %.5f\n", ksiLinha);
     
      midCalc = 0.85*a*ksi*(1-ksiLinha*ksi);
      System.out.printf("midCalc = %.5f\n", midCalc);
      
      midMd = getmid(); // midMd obtido dos dados (Classe Dados)
      System.out.printf("getmidMd = %.5f\n", midMd);    
      
      do
      {
        if(midCalc > midMd)
        {  
            System.out.printf("Entrou A em midCalc > midMd\n");
                 
            Ecd2a = ((Ecd1a-E1B)/5.0)+E1B;    // INTERVALO DIVIDIDO POR 5      
            
            a = 1.0 - ((0.002/Ecd1a)/3); 
            ksi = Ecd1a/(Ecd1a + 0.01);
            x1 = (Ecd1a-2.0)/Ecd1a;       x2 = 2.0/Ecd1a;
            ksiLinha = (1/a)*((Math.pow(x1, 2)/2)+(2/3)*x2-(10/24)*(Math.pow(x2, 2)));                   
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;    
            
            System.out.printf("Ecd2a A= %.5f\n", Ecd2a);   
            System.out.printf("a A= %.5f\n", a);
            System.out.printf("ksi A= %.5f\n", ksi);
            System.out.printf("ksiLinha A= %.5f\n", ksiLinha);
            System.out.printf("midCalc A= %.5f\n", midCalc);                 
            System.out.printf("midMd A= %.5f\n", midMd);                   
            System.out.printf("tolErro A= %.5f\n", tolErro); 
            System.out.printf("tolCompara A= %.5f\n", tolCompara); 
            
            Passo1 = Ecd1a;
            Passo2 = Ecd2a;
            Ecd1a  = Ecd2a;
            
          /*  if(midCalc < midMd)
            {
              do
              {
               Ecd2a = ((Passo1 - Passo2)/20.0) + Passo2; // INTERVALO DIVIDIDO POR 20
               
               a = (Ecd2a/0.002)-Math.pow((Ecd2a/0.002),2)/3; 
               ksi = Ecd2a/(Ecd2a + 0.01);               
               ksiLinha = 1-(((2/0.006)-((Ecd2a)/0.000016))/((1-(((Ecd2a)/0.002)/3))/0.002));
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Ecd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
                       
       */ }// Fim do if *******************************************************************
        
        if(midCalc < midMd)
        {   
            System.out.printf("Entrou em midCalc B< midMd\n");
            
            Ecd2a = ((E2B-Ecd1a)/5.)+Ecd1a;  // INTERVALO DIVIDIDO POR 5
            
            a = 1.0 - ((0.002/Ecd1a)/3); 
            ksi = Ecd1a/(Ecd1a + 0.01);
            x1 = (Ecd1a-2.0)/Ecd1a;       x2 = 2.0/Ecd1a;
            ksiLinha = (1/a)*((Math.pow(x1, 2)/2)+(2/3)*x2-(10/24)*(Math.pow(x2, 2)));            
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0; 
            
            System.out.printf("Ecd2a B= %.5f\n", Ecd2a);   
            System.out.printf("a B= %.5f\n", a);
            System.out.printf("ksi B= %.5f\n", ksi);
            System.out.printf("ksiLinha B= %.5f\n", ksiLinha);
            System.out.printf("midCalc B= %.5f\n", midCalc);                 
            System.out.printf("midMd B= %.5f\n", midMd);  
            System.out.printf("tolErro B= %.5f\n", tolErro);           
            System.out.printf("tolCompara B= %.5f\n", tolCompara);
            
            Passo1 = Ecd1a;
            Passo2 = Ecd2a;
            Ecd1a  = Ecd2a;
            
            if(midCalc > midMd)
            {
              do
              {
               Ecd2a = ((Passo2 - Passo1)/20.) + Passo1; // INTERVALO DIVIDIDO POR 20
               
               a = 1.0 - ((0.002/Ecd1a)/3); 
               ksi = Ecd1a/(Ecd1a + 0.01);
               x1 = (Ecd1a-2.0)/Ecd1a;       x2 = 2.0/Ecd1a;
               ksiLinha = (1/a)*((Math.pow(x1, 2)/2)+(2/3)*x2-(10/24)*(Math.pow(x2, 2)));
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Ecd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
        }//Fim do if ***********************************************************
    } // Chave do "do"              
      while(tolErro >= tolCompara);   
      
     } // FIM DO DOMINIO 2B
  
    
    
    
    
    
     
    // ********************************** DOMÍNIO 3 ***********************************
    // Deformação do Aço:        2,07 <= Esd3 <= 10.0   Aço CA50A
    // Deformação do Concreto:   Ecd3 = 3.5;  
     // Limites inferior e superior de mid da Tabela Universal
    double midLinf3, midLsup3; 
    midLinf3 = 0.160;           midLsup3 = 0.319;
    System.out.printf("midLinf3 = %.5f\n",midLinf3);
    System.out.printf("midLsup3 = %.5f\n",midLsup3);
    double E2Aaço, E1Aaço, Esd1a, Esd2a;
    
    if((midMd >= midLinf3) && (midMd <= midLsup3))
     {
    // 1a TENTATIVA - Escrever 4 equações
      E2Aaço = 0.00996;      E1Aaço = 0.00207; 
      Esd1a = (E2Aaço-E1Aaço)/2.0;  // INTERVALO DIVIDIDO POR 2
      Passo1 = Esd1a;
      System.out.printf("E2Aaço = %.5f\n",E2Aaço);
      System.out.printf("E1Aaço = %.5f\n",E1Aaço);
      System.out.printf("Esd1a = %.5f\n", Esd1a);
      System.out.printf("Passo1 = %.5f\n", Passo1);
     
      a = 17.0/21.0; 
      System.out.printf("a = %.5f\n", a);
     
      ksi = 0.0035/(0.0035 + Esd1a);
      System.out.printf("ksi = %.5f\n", ksi);
     
      ksiLinha = 0.41597;         
      System.out.printf("ksiLinha = %.5f\n", ksiLinha);
     
      midCalc = 0.85*a*ksi*(1-ksiLinha*ksi);
      System.out.printf("midCalc = %.5f\n", midCalc);
      
      midMd = getmid(); // midMd obtido dos dados (Classe Dados)
      System.out.printf("getmidMd = %.5f\n", midMd);         
        
      do
      {
        if(midCalc > midMd)
        {  
            System.out.printf("Entrou A em midCalc > midMd\n");
                 
            Esd2a = ((Esd1a-E1Aaço)/5.0)+E1Aaço;    // INTERVALO DIVIDIDO POR 5      
            
            a = 17.0/21.0;      
            ksi = 0.0035/(0.0035 + Esd1a);
            ksiLinha = 0.41597;                            
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;    
            
            System.out.printf("Esd2a A= %.5f\n", Esd2a);   
            System.out.printf("a A= %.5f\n", a);
            System.out.printf("ksi A= %.5f\n", ksi);
            System.out.printf("ksiLinha A= %.5f\n", ksiLinha);
            System.out.printf("midCalc A= %.5f\n", midCalc);                 
            System.out.printf("midMd A= %.5f\n", midMd);                   
            System.out.printf("tolErro A= %.5f\n", tolErro); 
            System.out.printf("tolCompara A= %.5f\n", tolCompara); 
            
            Passo1 = Esd1a;
            Passo2 = Esd2a;
            Esd1a  = Esd2a;
            
          /*  if(midCalc < midMd)
            {
              do
              {
               Ecd2a = ((Passo1 - Passo2)/20.0) + Passo2; // INTERVALO DIVIDIDO POR 20
               
               a = (Ecd2a/0.002)-Math.pow((Ecd2a/0.002),2)/3; 
               ksi = Ecd2a/(Ecd2a + 0.01);               
               ksiLinha = 1-(((2/0.006)-((Ecd2a)/0.000016))/((1-(((Ecd2a)/0.002)/3))/0.002));
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Ecd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
                       
       */ }// Fim do if *******************************************************************
        
        if(midCalc < midMd)
        {   
            System.out.printf("Entrou em midCalc B< midMd\n");
            
            Esd2a = ((E2Aaço-Esd1a)/5.)+Esd1a;  // INTERVALO DIVIDIDO POR 5
            
            a = 17.0/21.0;      
            ksi = 0.0035/(0.0035 + Esd1a);
            ksiLinha = 0.41597;                
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0; 
            
            System.out.printf("Esd2a B= %.5f\n", Esd2a);   
            System.out.printf("a B= %.5f\n", a);
            System.out.printf("ksi B= %.5f\n", ksi);
            System.out.printf("ksiLinha B= %.5f\n", ksiLinha);
            System.out.printf("midCalc B= %.5f\n", midCalc);                 
            System.out.printf("midMd B= %.5f\n", midMd);  
            System.out.printf("tolErro B= %.5f\n", tolErro);           
            System.out.printf("tolCompara B= %.5f\n", tolCompara);
            
            Passo1 = Esd1a;
            Passo2 = Esd2a;
            Esd1a  = Esd2a;
            
            if(midCalc > midMd)
            {
              do
              {
               Esd2a = ((Passo2 - Passo1)/20.) + Passo1; // INTERVALO DIVIDIDO POR 20
               
               a = 17.0/21.0;      
               ksi = 0.0035/(0.0035 + Esd1a);
               ksiLinha = 0.41597;    
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Esd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
        }//Fim do if ***********************************************************
    } // Chave do "do"              
      while(tolErro >= tolCompara);   
      
     } // FIM DO DOMÍNIO 3 
    
    
    
    
    
    // ********************************** DOMÍNIO 4 ***********************************
    // Deformação do Aço:        2,07 <= Esd3 <= 10.0   Aço CA50A
    // Deformação do Concreto:   Ecd3 = 3.5;  
     // Limites inferior e superior de mid da Tabela Universal
    double midLinf4, midLsup4; 
    midLinf4 = 0.320;           midLsup4 = 0.402;
    System.out.printf("midLinf3 = %.5f\n",midLinf4);
    System.out.printf("midLsup3 = %.5f\n",midLsup4);
    double E2Baço, E1Baço;
    
    if((midMd >= midLinf4) && (midMd <= midLsup4))
     {
    // 1a TENTATIVA - Escrever 4 equações
      E2Baço = 0.00197;      E1Baço = 0.00207; 
      Esd1a = (E2Baço-E1Baço)/2.0;  // INTERVALO DIVIDIDO POR 2
      Passo1 = Esd1a;
      System.out.printf("E2Baço = %.5f\n",E2Baço);
      System.out.printf("E1Baço = %.5f\n",E1Baço);
      System.out.printf("Esd1a = %.5f\n", Esd1a);
      System.out.printf("Passo1 = %.5f\n", Passo1);
     
      a = 17.0/21.0; 
      System.out.printf("a = %.5f\n", a);
     
      ksi = 0.0035/(0.0035 + Esd1a);
      System.out.printf("ksi = %.5f\n", ksi);
     
      ksiLinha = 0.41597;         
      System.out.printf("ksiLinha = %.5f\n", ksiLinha);
     
      midCalc = 0.85*a*ksi*(1-ksiLinha*ksi);
      System.out.printf("midCalc = %.5f\n", midCalc);
      
      midMd = getmid(); // midMd obtido dos dados (Classe Dados)
      System.out.printf("getmidMd = %.5f\n", midMd);         
  
       do
      {
        if(midCalc > midMd)
        {  
            System.out.printf("Entrou A em midCalc > midMd\n");
                 
            Esd2a = ((Esd1a-E1Baço)/5.0)+E1Baço;    // INTERVALO DIVIDIDO POR 5      
            
            a = 17.0/21.0;      
            ksi = 0.0035/(0.0035 + Esd1a);
            ksiLinha = 0.41597;                            
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;    
            
            System.out.printf("Esd2a A= %.5f\n", Esd2a);   
            System.out.printf("a A= %.5f\n", a);
            System.out.printf("ksi A= %.5f\n", ksi);
            System.out.printf("ksiLinha A= %.5f\n", ksiLinha);
            System.out.printf("midCalc A= %.5f\n", midCalc);                 
            System.out.printf("midMd A= %.5f\n", midMd);                   
            System.out.printf("tolErro A= %.5f\n", tolErro); 
            System.out.printf("tolCompara A= %.5f\n", tolCompara); 
            
            Passo1 = Esd1a;
            Passo2 = Esd2a;
            Esd1a  = Esd2a;
            
          /*  if(midCalc < midMd)
            {
              do
              {
               Ecd2a = ((Passo1 - Passo2)/20.0) + Passo2; // INTERVALO DIVIDIDO POR 20
               
               a = (Ecd2a/0.002)-Math.pow((Ecd2a/0.002),2)/3; 
               ksi = Ecd2a/(Ecd2a + 0.01);               
               ksiLinha = 1-(((2/0.006)-((Ecd2a)/0.000016))/((1-(((Ecd2a)/0.002)/3))/0.002));
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Ecd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
                       
       */ }// Fim do if *******************************************************************
        
        if(midCalc < midMd)
        {   
            System.out.printf("Entrou em midCalc B< midMd\n");
            
            Esd2a = ((E2Baço-Esd1a)/5.)+Esd1a;  // INTERVALO DIVIDIDO POR 5
            
            a = 17.0/21.0;      
            ksi = 0.0035/(0.0035 + Esd1a);
            ksiLinha = 0.41597;                
            midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
            tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0; 
            
            System.out.printf("Esd2a B= %.5f\n", Esd2a);   
            System.out.printf("a B= %.5f\n", a);
            System.out.printf("ksi B= %.5f\n", ksi);
            System.out.printf("ksiLinha B= %.5f\n", ksiLinha);
            System.out.printf("midCalc B= %.5f\n", midCalc);                 
            System.out.printf("midMd B= %.5f\n", midMd);  
            System.out.printf("tolErro B= %.5f\n", tolErro);           
            System.out.printf("tolCompara B= %.5f\n", tolCompara);
            
            Passo1 = Esd1a;
            Passo2 = Esd2a;
            Esd1a  = Esd2a;
            
            if(midCalc > midMd)
            {
              do
              {
               Esd2a = ((Passo2 - Passo1)/20.) + Passo1; // INTERVALO DIVIDIDO POR 20
               
               a = 17.0/21.0;      
               ksi = 0.0035/(0.0035 + Esd1a);
               ksiLinha = 0.41597;    
               midCalc = 0.85*a*ksi*(1-ksiLinha*ksi); 
               tolErro = Math.abs((midMd-midCalc)/midCalc)*100.0;
               
               System.out.printf("a B1= %.5f\n", a);
               System.out.printf("ksi B1= %.5f\n", ksi);
               System.out.printf("ksiLinha B1= %.5f\n", ksiLinha);
               System.out.printf("midCalc B1= %.5f\n", midCalc);
               System.out.printf("midMd B1= %.5f\n", midMd); 
               System.out.printf("tolErro B1= %.5f\n", tolErro);
               
               Passo1 = Passo2;
               Passo2 = Esd2a;                   
              }
              while(tolErro >= tolCompara);    
            }
        }//Fim do if ***********************************************************
    } // Chave do "do"              
      while(tolErro >= tolCompara);   
      
     } // FIM DO DOMÍNIO 4
     
     
  }// Fim do Método CálculoFlexão
}// Fim da classe Flexão
//*****************************************************************************
  
 



