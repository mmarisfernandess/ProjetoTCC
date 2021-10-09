package ProjConcArmadoB;
// TCC - Mariana Fernandes

import java.util.Scanner;

// 2a classe - entrada de Dados pela tela
public class ProjConcretoB_Dados {

    // Declaração das variáveis de instância
    // ************* PROGRAMA DANIEL FELIPIN *************************
    private double SEÇÃO[];
    private String nomeSeção;

    private String nomeDetalhamento;

    // ******************** FIM DO PROGRAMA DANIEL ********************
    private double Seção[];
    // bw = largura         Seção[0] = bw
    // h = altura           Seção[1] = h
    // d'                   Seção[2] = d'
    // d = altura útil      Seção[3] = d

    private Double Material[];
    // Ler fck e divide por 1,4 = fcd     Material[0] = fcd
    // Ler fyd e divide por 1,15 = fyd    Material[1] = fyd

    private double Solicitação[];
    // Ler Msd = Solicitação[0]
    // Calcular o parâmetro miSd = Msd/bw*d2*fcd = Solicitação[1] = mid

    private double DETALHAMENTO[];

    private double AsBarra[];

    // 1o método - CONSTRUTOR
    // Este método é utilizado para dimensionar os vetores e/ou matrizes
    public ProjConcretoB_Dados() {
        Seção = new double[4];
        SEÇÃO = new double[8];
        Material = new Double[4];
        Solicitação = new double[2];
        DETALHAMENTO = new double[6];
        AsBarra = new double[5];
    }
//***************** DATA: 16/08/21 ********* KATRI IKA *********************

    /**
     * CONJUNTO DE MÉTODOS REFERENTES À SEÇÃO TRANSVERSAL
     * ***********************
     */
    // MÉTODO QUE RECEBE O VALOR CONTIDO NA CAIXA DE TEXTO NA INTERFACE GRÁFICA
    //******************************************************************/
    // Para colocar na primeira posição do vetor Seção[0] o valor da 
    // base da seção (bw). Este valor é obtido da caixa de texto da classe
    // Interface, quando o usuário digita esta dimensão da seção transversal  
    //********************************************
    // Seção Retangular, T, I e L 
    public void setNomeSeção(String nome) {
        nomeSeção = nome;
    }

    public String getNomeSeção() {
        return nomeSeção;
    }

    public void setbw(double bw) {
        SEÇÃO[0] = bw;
    }
    // Para colocar a "altura" na 2a posição do vetor Seção[1]

    public void seth(double h) {
        SEÇÃO[1] = h;
    }

    public void setbf(Double bf) {
        SEÇÃO[2] = bf;
    }

    public void sethf(double hf) {
        SEÇÃO[3] = hf;
    }

    public void setbfs(double bfs) {
        SEÇÃO[4] = bfs;
    }

    public void sethfs(double hfs) {
        SEÇÃO[5] = hfs;
    }

    public void setbfi(double bfi) {
        SEÇÃO[6] = bfi;
    }

    public void sethfi(double hfi) {
        SEÇÃO[7] = hfi;
    }

    public void setSeção(double[] SEÇÃO) {
        this.SEÇÃO = SEÇÃO;
    }

    public double[] getSEÇÃO() {
        return SEÇÃO;
    }

    //***********   FIM DOS DADOS DA SEÇÂO *******************
    //************ MATERIAL *************
    public void setfck(double fck) {
        Material[0] = fck;
        Material[1] = fck / 1.4;
    }

    public void setfyk(double fyk) {
        Material[2] = fyk;
        Material[3] = fyk / 1.15;
    }

    //************ FIM DOS DADOS DO MATERIAL *************
    public Double[] getMaterial() {
        return Material;
    }

    
    
    // Md, Nd, e = Md/Nd (retorna o valor), Msd (retorna o valor), miSd (retorna o valor)
    public double[] getSolicitação() {
        return Solicitação;
    }

    //************************ DETALHAMENTO *****************************  
    public void setNomeDetalhamento(String nome) {
        nomeDetalhamento = nome;
    }

    public String getNomeDetalhamento() {
        return nomeDetalhamento;
    }

    public void setcNom(double cNom) {
        DETALHAMENTO[0] = cNom;
    }

    public void setfiT(double fiT) {
        DETALHAMENTO[1] = fiT;
    }

    public void setfi(double fi) {
        DETALHAMENTO[2] = fi;
    }

    public void setnBarras(double nBarras) {
        DETALHAMENTO[3] = nBarras;
    }

    public void setDetalhamento(double[] DETALHAMENTO) {
        this.DETALHAMENTO = DETALHAMENTO;
    }

    public double[] getDETALHAMENTO() {
        return DETALHAMENTO;
    }

    //***********   FIM DOS DADOS DO DETALHAMENTO *******************
    // ******************** ÁREAS DAS BARRAS DE AÇO ******************
    public void setAs63(double As63) {
        AsBarra[0] = 0.132;
    }

    public void setAs8(double As8) {
        AsBarra[1] = 0.503;
    }

    public void setAs125(double As125) {
        AsBarra[2] = 1.23;
    }

    public void setAs16(double As16) {
        AsBarra[3] = 2.01;
    }

    public void setAs20(double As20) {
        AsBarra[4] = 3.14;
    }

    public void setAsBarra(double[] AsBarra) {
        this.AsBarra = AsBarra;
    }

    public double[] getAsBarra() {
        return AsBarra;
    }
    
    

    //********** FIM DOS DADOS DAS ÁREAS DAS BARRAS DE AÇO ***********
    // ******************************************************************   
} // Fim da CLASSE 

// 2o método - LEITURA DE DADOS
/*public void setDados()
    {
        
        // Variável local
        double fck;
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
        
    } // Fim do método setDados()
 */
// ***************************************************************************************************
/* public void putBase(double bw)
    {
        Seção[0] = bw; // bw
    }    
    // Para colocar a "altura" na 2a posição do vetor Seção[1]
    public void putAltura(double altura)
    {
        Seção[1] = altura; // h 
    }    
    // Para colocar a "d'" na 3a posição do vetor Seção[2]
    public void putdLinha(double dLinha)
    {
        Seção[2] = dLinha; // d' = dLinha
    }    
    public void putd(double d)
    {
        Seção[3] = d; // d
    }
    public void calculad()
    {
        Seção[3] = Seção[1] - Seção[2]; // d = h - d'
    }
 */
