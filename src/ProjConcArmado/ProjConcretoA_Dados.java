package ProjConcArmado;

// TCC - Mariana Fernandes

import java.util.Scanner;

// 2a classe - entrada de Dados pela tela
public class ProjConcretoA_Dados 
{
    // Declaração das variáveis de instância
    private final double Seção[];
    // bw = largura         Seção[0] = bw
    // h = altura           Seção[1] = h
    // d'                   Seção[2] = d'
    // d = altura útil      Seção[3] = d
    
    private final double Material[];
    // Ler fck e divide por 1,4 = fcd     Material[0] = fcd
    // Ler fyd e divide por 1,15 = fyd    Material[1] = fyd
    
    private final double Solicitação[];
    double mid;
    // Ler Msd = Solicitação[0]
    // Calcular o parâmetro miSd = Msd/bw*d2*fcd = Solicitação[1] = mid
    
    // 1o método - CONSTRUTOR
    // Este método é utilizado para dimensionar os vetores e/ou matrizes
    
    public ProjConcretoA_Dados()
    {
        Seção = new double[4];
        Material = new double[2];
        Solicitação = new double[2];
    }
    // 2o método - LEITURA DE DADOS
    public void setDados()
    {
         // Cria o objeto "entrada" da classe Scanner
        // para ler os números na tela
        Scanner entrada = new Scanner(System.in);
        
        // Ler os dados bw, h, d', fck, fyd e Msd
        // Vai calcular e preencher os vetores Seção, Material e Solicitação
 
        System.out.print("Projeto TCC - Tabela Universal\n"); 
        System.out.print("Entre com os valores da seção\n");
        System.out.print("Digite a largura bw (cm) da seção:\n");
        Seção[0] = entrada.nextDouble(); // Variável real = DOUBLE
        
        System.out.print("Digite a altura h (cm) da seção:\n");
        Seção[1] = entrada.nextDouble();
        
        System.out.print("Digite o valor de d' (cm) da seção:\n");
        Seção[2] = entrada.nextDouble();
        
        Seção[3] = Seção[1] - Seção[2]; // d = h-d'
        System.out.printf("O valor da altura útil d (cm) da seção equivale a: %.3f\n",Seção[3]);
        
        
        System.out.print("Entre com os valores do material\n");
        System.out.print("Digite o valor do fck (MPa) do concreto:\n");
        Material[0] = entrada.nextDouble();
        
        
        Material[0] = Material[0] / 1.4;
        // System.out.printf("a soma de %d e %d é: %d\n",número1,número2,somaInteiro); 
        System.out.printf("O valor do fcd (MPa) do concreto equivale a: %.3f\n",Material[0]);
        
        System.out.print("Digite o valor do fyd (MPa) do concreto:\n");
        Material[1] = entrada.nextDouble();
        
        Material[1] = (Material[1]*10) / 1.15;
        System.out.printf("O valor do fyd (MPa) do concreto equivale a: %.3f\n", Material[1]);
        
        System.out.print("Entre com os esforços solicitantes\n");
        System.out.print("Digite o valor do Msd (kNm):\n");
        Solicitação[0] = entrada.nextDouble();
        
        Solicitação[1] = (Solicitação[0]*Math.pow(10, 3)) / (Seção[0]*Math.pow(Seção[3], 2)*Material[0]);
        System.out.printf("O valor do miSd equivale a: %.3f\n",Solicitação[1]);
        mid = Solicitação[1];
        
    }
    
    // ******************************************
    public double getmid()
    {
        return mid;
    }
    
    
    // ***********************************************
    // ****************** NOVO MÉTODO *******************************
    public double[]getSeção()
    {
        return Seção;
    }
    
    public double[]getMaterial()
    {
        return Material;
    }
    
    public double[]getSolicitação()
    {
        return Solicitação;
    }
    
    
    
}
