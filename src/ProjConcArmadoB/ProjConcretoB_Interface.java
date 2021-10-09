package ProjConcArmadoB;
// TCC - Mariana Fernandes

// 2a classe - Interface
// Importa janela (classe JFrame)
import javax.swing.*;

//Painel para desenhar - importa classe JPanel
// Gerenciadores de layout do JFrame (janela)
import java.awt.*;

// Bibliotecas de Menus
// Caixa de diálogo
// Biblioteca para gerenciar arquivos
import java.io.File;

// Cores
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.border.LineBorder; // Linhas de borda
import javax.swing.border.TitledBorder;

// Classe ProjConcretoB_Interface herda a classe JFrame
public class ProjConcretoB_Interface extends JFrame {

    // Declaração das variáveis de instância - para arquivo de dados
    private File name;
    private int Flag, ErroFormato;// ????????????????????????    
    private int FlagSeção;//?????????????????????????

    //********************************************* --- 21/07/2021
    // O objeto "dados" da classe ProjConcretoB_Arquivo - Cria e inicializa
    ProjConcretoB_Arquivo dados = new ProjConcretoB_Arquivo();
    // ******************************************** --- 21/07/2021

    //Objeto "desenhar" da classe Desenho
    ProjConcretoB_Desenho desenhar;

    ProjConcretoB_Dados bDados;

    Carregamento carregamento;

    Detalhamento detalhamento;

    // VARIÁVEIS para o Material
    // Guardar 4 dados no vetor seção[4] = (fck, , , )
    // Variáveis obtidas das caixas de texto do Painel à direita
    //private double material[];
    // VARIÁVEIS para a Solicitação
    // Guardar 4 dados no vetor seção[4] = (fck, , , )
    // Variáveis obtidas das caixas de texto do Painel à direita
    private double solicitação[];

    // *********** 11/08/2021 ***********************************************
    // Para colocar um PAINEL NO SUL para ESCALA
    // KATRI   ************* DATA: 10/08/21
    // COPIADO do projetoC_Gráficos_Listas_Interface (aplicativo de curvas)
    private JTextField Unidade, metroTamanho, H, V;
    private double NumPixelPorMetro;
    private JPanel painelSul;
    // Variáveis dos paineis colocadas na parte inferior da tela  
    private JLabel mensagem;
    // KATRI   ************* DATA: 10/08/21

    // Para determinar os limites da tela para desenhar
    // KATRI   ************* DATA: 10/08/21
    private JTextField CampoLimites[];
    private double XXimin, XXimax, YYimin, YYimax;
    // KATRI   ************* DATA: 10/08/21

    // KATRI   ************* DATA: 16/08/21 ***********************************
    // COPIADO DO CProtendido
    private JButton botão[];
    private String NomeBotão[] = {"Seção Transversal", "Material", "Detalhamento",
        "Carregamento", "ELU - Flexão"};
    // 5 Botões
    // KATRI   ************* DATA: 16/08/21 ************************************

    // VARIÁVEIS para a seção transversal
    private Double SEÇÃO[];

    // VARIÁVEIS para o material
    // private double Material[];
    // Variáveis para o detalhamento da armadura 
    private double DETALHAMENTO[];

    // KATRI   ************* DATA: 20/08/21 ***********************************    
    //************************************************************************  
    // Caixa de combinação para armazenar nomes de ícones(figuras = fotos = imagens)
    private JComboBox menuSeçãoJComboBox;
    private JComboBox menuDetalhamentoJComboBox;
    private JComboBox menuDetalhamentoCamadasJComboBox;
    private JComboBox menuCarregamentoFlexãoJComboBox;
    private JComboBox menuCarregamentoFlexãoCompostaJComboBox;

    // Cria vetor de nomes das imagens para depois carregá-las
    private String nomesImagens[] = {"SeçãoGen.jpg", "Seção_Retangular.jpg",
        "Seção_T.jpg", "Seção_I.jpg",
        "Seção_L.jpg"};

    private Icon icons[] = {new ImageIcon(getClass().getResource(nomesImagens[0])),
        new ImageIcon(getClass().getResource(nomesImagens[1])),
        new ImageIcon(getClass().getResource(nomesImagens[2])),
        new ImageIcon(getClass().getResource(nomesImagens[3])),
        new ImageIcon(getClass().getResource(nomesImagens[4]))};

    private static final String[] nomesSeção = {"  Selecione uma Seção  ",
        "Seção Retangular",
        "Seção T", "Seção I",
        "Seção L"};
    private static final String[] nomesCamadasDupla = {"Selecione um nº de camadas",
        "1 camada (tração)",
        "2 camadas (tração)", "1 camada (compressão)",
        "2 camadas (compressão)"};
    private static final String[] nomesCamadasSimple = {"Selecione um nº de camadas",
        "1 camada (tração)",
        "2 camadas (tração)"};

    /* private String nomesImagensDet[]= { "  Detalhamento_viga.jpg ",
                                        "Armadura_simples.jpg",
                                        "Armadura_dupla.jpg" }; 
     */
 /* 
    private String nomesImagensDet[]= { "Seção_L1.jpg",
                                        "Seção_L2.jpg",
                                        "Seção_L3.jpg" };
     */
    private String nomesImagensDet[] = {"Detalhamento__viga.jpg",
        "Armadura__simples.jpg",
        "Armadura__dupla.jpg"};

    private Icon iconsDetalhamento[] = {new ImageIcon(getClass().getResource(nomesImagensDet[0])),
        new ImageIcon(getClass().getResource(nomesImagensDet[1])),
        new ImageIcon(getClass().getResource(nomesImagensDet[2]))};

    private static final String[] nomesDetalhamento = {"Selecione uma disposição de armadura",
        "Armadura simples",
        "Armadura dupla"};

    private static final String[] nomesCarregamentoFlexão = {"Selecione um tipo de flexão", "Flexão simples",
        "Flexão composta"};
    private static final String[] nomesCarregamentoFlexãoComposta = {"Selecione um caso de flexão composta", "Flexo-tração",
        "Flexo-compressão"};

    private JLabel labelMenuSeção; // Rótulo para exibir a imagem selecionada     
    private String nomeSeçãoInterface;
    private JLabel labelMenuDetalhamento;
    private String nomeDetalhamentoInterface;

    // KATRI   ************* DATA: 20/08/21 ***********************************    
    //************************************************************************  
    // 1o método Construtor - para programar todo o layout da janela
    public ProjConcretoB_Interface() {
        super("TCC Mariana Fernandes - Dimensionamento em CA de seções retangulares e T sob flexão");
        SEÇÃO = new Double[8];
        bDados = new ProjConcretoB_Dados();
        carregamento = new Carregamento();
        detalhamento = new Detalhamento();

//        nomesCamadasDupla = new Vector();
//        nomesCamadasSimple = new Vector();
//        nomesCamadasDupla.add("");
        //--->>> 1o PASSO = BARRA DE MENU -------<<<<<<<
        /**
         * *******************************************
         */
        // 1o Menu - ARQUIVO
        JMenu arquivoMenu = new JMenu("Arquivo"); // Cria o Menu Arquivo
        arquivoMenu.setMnemonic('A'); // Se quiser clicar pelo teclado A --> atalho pelo teclado

        /**
         * ***********************************************************
         ***********************************************************
         */
        // Cria os outros itens do menu Arquivo   
        JMenuItem novoItem = new JMenuItem("Novo");// Criar o item Novo
        novoItem.setMnemonic('N');
        arquivoMenu.add(novoItem);// Adiciona o item Novo ao menu Arquivo

        JMenuItem abrirItem = new JMenuItem("Abrir");// Criar o item Abrir
        abrirItem.setMnemonic('B');
        arquivoMenu.add(abrirItem);// Adiciona o item Abrir ao menu Arquivo      

        JMenuItem salvarItem = new JMenuItem("Salvar");// Cria o item Salvar
        salvarItem.setMnemonic('V');
        arquivoMenu.add(salvarItem);// Adiciona o item Salvar ao menu Arquivo

        JMenuItem salvarComoItem = new JMenuItem("Salvar Como");// Cria o item Salvar
        salvarComoItem.setMnemonic('C');
        arquivoMenu.add(salvarComoItem);// Adiciona o item Salvar ao menu Arquivo      
        /**
         * ***********************************************************
         ***********************************************************
         */

        // 2o Menu - RELATÓRIO
        //JMenu relatórioMenu = new JMenu("Relatório"); // Cria o Menu Relatório
        //relatórioMenu.setMnemonic('R'); // Se quiser clicar pelo teclado R --> atalho pelo teclado
        // 3o Menu - AJUDA
        // JMenu ajudaMenu = new JMenu("Ajuda"); // Cria o Menu Ajuda
        //ajudaMenu.setMnemonic('H'); // Se quiser clicar pelo teclado H --> atalho pelo teclado
        //****** ITEM DE MENU (3o Menu - Menu Ajuda) - FORMULÁRIO
        // JMenuItem formulárioItem = new JMenuItem("Formulário");
        // ajudaMenu.add(formulárioItem);
        //****** ITEM DE MENU (3o Menu - Menu Ajuda) - SOBRE O PROGRAMA
        //JMenuItem programaItem = new JMenuItem("Sobre o Programa");
        //ajudaMenu.add(programaItem);
        // ****************************************************/
        // Cria uma barra de Menus para adicionar o Menu Arquivo, Relatório e Ajuda
        JMenuBar barra = new JMenuBar();
        barra.add(arquivoMenu); // Adiciona o Menu Arquivo à barra de menus
        //barra.add(relatórioMenu); // Adiciona o Menu Relatório à barra de menus
        //barra.add(ajudaMenu); // Adiciona o Menu Ajuda à barra de menus

        // final Color azulClaro = new Color(115,149,233);
        // barra.setBackground(azulClaro);
        // setJMenuBar(barra); // Adiciona a barra de menus ao JFrame
        // KATRI *****************   DATA:  10/08/21 *******************  
        // Data: 26-07-20
        // Cria o 1* item de Menu "LimitesTela"
        JMenu limitesItem = new JMenu("LimitesTela");
        limitesItem.setMnemonic('L');
        arquivoMenu.add(limitesItem);
        arquivoMenu.addSeparator();

        // Cria uma barra de menus para adicionar o menu Arquivo   
        barra.add(limitesItem);
        final Color azulclaro = new Color(115, 149, 233);
        barra.setBackground(azulclaro);
        setJMenuBar(barra); // Adiciona a barra de menus ao JFrame   
        // KATRI *****************   DATA:  10/08/21 *******************   

        // Cria a caixa de texto branca
        JTextField CampoSeçãoA[];
        CampoSeçãoA = new JTextField[4];
        for (int i = 0; i < 4; i++) {
            CampoSeçãoA[i] = new JTextField("", 4);
            // CampoSeção[i].setEditable(false);
        }

        //--->>> 2o PASSO = AÇÃO DE EVENTO DOS MENUS -------<<<<<<<
        /**
         * ******************************************************
         */
        //--->>>> TRATAMENTO DE EVENTOS DO MENU ARQUIVO: "NOVO"----<<<   
        novoItem.addActionListener(
                new ActionListener() {//Exibe uma caixa de diálogo de mensagem quando o usuário selecionar NOVO
            public void actionPerformed(ActionEvent event) {
                // Para zerar os dados na classe DADOS de uma rodada anterior do programa
                // de um exemplo anterior
                // MUDOU - NOVA VERSÃO RETIRAR &&&&&&&
                /*seçãoA[0]=0.0; dados.putBase(seçãoA[0]); // base = bw
           seçãoA[1]=0.0; dados.putAltura(seçãoA[1]); // altura 
           seçãoA[2]=0.0; dados.putdLinha(seçãoA[2]); // d'
           seçãoA[3]=0.0; dados.putd(seçãoA[3]); // d
                 */
                //??????????????????????????????????????????????
                //CampoSeçãoA[0].setText(String.format("%.2f",seçãoA[0]));
                //CampoSeçãoA[1].setText(String.format("%.2f",seçãoA[1]));
                //CampoSeçãoA[2].setText(String.format("%.2f",seçãoA[2]));
                //CampoSeçãoA[3].setText(String.format("%.2f",seçãoA[3]));

                // Para colocar nas caixas de texto à esquerda
                // TESTE TEMPORÁRIO NOME - ZERO - DATA: 02.08.2021
                // double nome = 55.0;
                // CampoSeção[0].setText(String.format("%.2f",seção[0]));
                Flag = 0;
                String mensagemTela_02 = String.format(
                        "Seção Transversal\n"
                        + "Digite os dados da seção pelo painel à esquerda");
                JOptionPane.showMessageDialog(null, mensagemTela_02);
            }
        }
        );// Fim do parênteses do new actionPerformed do MENU NOVO
//--->>>>>> FIM DO TRATAMENTO DE EVENTO DO MENU ARQUIVO: NOVO

        //--->>>> TRATAMENTO DE EVENTOS DO MENU ARQUIVO: "SALVAR"----<<<   
        salvarItem.addActionListener // Adiciona a interface ActionListener
                (
                        new ActionListener() // Classe anônima 
                { // Exibe uma caixa de diálogo de mensagem quando o usuário selecionar SALVAR
                    public void actionPerformed(ActionEvent event) // Método obrigatório
                    {
                        // Chama o 2o método desta classe e retorna o nome do arquivo
                        name = getFileOrDirectory();
                        if (name.exists()) // Verifica se o arquivo já existe no diretório
                        { // O nome do arquivo existe
                            String AvisoArquivo = JOptionPane.showInputDialog("O arquivo já existe. Deseja substituí-lo?"
                                    + "1-SIM, 2-NÃO");
                            int escolhaArquivo = Integer.parseInt(AvisoArquivo);
                            if (escolhaArquivo == 1) // O arquivo será substituído por outro
                            {
                                String mensagemTela_04 = String.format("Escolha = %s=> O arquivo será salvo e o"
                                        + " Aplicativo não será fechado.", AvisoArquivo);
                                JOptionPane.showMessageDialog(null, mensagemTela_04);
                                // Coloca na classe ProjConcretoB_Arquivo os nomes "in e out"
                                // para nomear os arquivos de entrada e saída
                                dados.NovoArquivoEntrada(name); // Está na classe ****Arquivo
                                dados.gravarArquivo();
                                dados.gravarDados();
                                dados.fecharArquivo();
                            }

                        }

                    } // Fim do método actionPerformed
                } // Fecha chave do ActionListener
                ); // Fecha parênteses        
//--->>>>>> FIM DO TRATAMENTO DE EVENTO DO MENU ARQUIVO: SALVAR

//************ KATRI *************** DATA: 10/08/21 *************************
//----- DATA: 26-07-20 ************************************************
//----------->>>>>>>> TRATAMENTO DE EVENTOS DO MENU ARQUIVO: 1*iTEM = "LimitesTela"------<<<<<<<<<<<<<       
        // o item "limitesItem" 
        ////////////////// montagem do painel painelLimites ////////////////// 
        //Cria os elementos que vão formar o painel de entrada para
        //os limites da área de desenho 
        // JLabels
        //JLabel painelLimitesTítulo = new JLabel("Limites de Área");
        JLabel Xmin = new JLabel(" Xmin=");
        Xmin.setToolTipText("Após digitar tecle ENTER");
        JLabel Xmax = new JLabel(" Xmax=");
        Xmax.setToolTipText("Após digitar tecle ENTER");

        JLabel Ymin = new JLabel(" Ymin=");
        Ymin.setToolTipText("Após digitar tecle ENTER");
        JLabel Ymax = new JLabel(" Ymax=");
        Ymax.setToolTipText("Após digitar tecle ENTER");

        CampoLimites = new JTextField[4];
        // Cria caixas de texto    
        CampoLimites[0] = new JTextField("0.0", 4);
        CampoLimites[1] = new JTextField("180.0", 4);
        CampoLimites[2] = new JTextField("0.0", 4);
        CampoLimites[3] = new JTextField("100.0", 4);

        for (int i = 0; i < 4; i++) { // CampoLimites[i]= new JTextField("",4);          
            CampoLimites[i].setToolTipText("Após digitar tecle ENTER");
        }

        // Cria os painéis que serão a linhas do painelLimites
        // Estes painéis serão colocados na estrutura "verticalLimites"            
        JPanel LinhaspainelLimites[];
        LinhaspainelLimites = new JPanel[4];
        // coloca os JLabel e Caixas de texto alinhados a esquerda
        FlowLayout layoutLinhaspainelLimites[];
        layoutLinhaspainelLimites = new FlowLayout[4];
        for (int i = 0; i < 4; i++) {
            LinhaspainelLimites[i] = new JPanel();
            layoutLinhaspainelLimites[i] = new FlowLayout();
            layoutLinhaspainelLimites[i].setAlignment(FlowLayout.CENTER);
            LinhaspainelLimites[i].setLayout(layoutLinhaspainelLimites[i]);
            LinhaspainelLimites[i].setBackground(new java.awt.Color(231, 234, 240));
        }
        // Preenche(Forma) os paineis com labels e caixas texto 
        // Forma as linhas do painelLimites
        // Preenche(Forma) os paineis com labels e caixas texto       
        // 1* linha         
        // x= ---- (JLabel + JTextField)
        LinhaspainelLimites[0].add(Xmin);//x=      
        LinhaspainelLimites[0].add(CampoLimites[0]);//Campo para digitar      
        // 2* linha  
        // x= ---- (JLabel + JTextField)
        LinhaspainelLimites[1].add(Xmax);
        LinhaspainelLimites[1].add(CampoLimites[1]);//Campo para digitar 
        // 3* linha         
        // y= ---- (JLabel + JTextField)
        LinhaspainelLimites[2].add(Ymin);//x=      
        LinhaspainelLimites[2].add(CampoLimites[2]);//Campo para digitar
        // 4* linha  
        // Y= ---- (JLabel + JTextField)
        LinhaspainelLimites[3].add(Ymax);
        LinhaspainelLimites[3].add(CampoLimites[3]);//Campo para digitar           

        // Cria a estrutura Box "verticalLimites"
        Box verticalLimites;
        verticalLimites = Box.createVerticalBox();
        // Coloca os painéis na estrutura BoxVertical "verticalBotões" 
        verticalLimites.add(LinhaspainelLimites[0]);// Inserindo Ponto(JLabel)        
        verticalLimites.add(LinhaspainelLimites[1]);// x= ---- (JLabel + JTextField)
        verticalLimites.add(LinhaspainelLimites[2]);// y= ---- (JLabel + JTextField)
        verticalLimites.add(LinhaspainelLimites[3]);
        /////////////////////////////////////////////////////////////////////////

        JPanel painelLimites = new JPanel();
        painelLimites.setBackground(new java.awt.Color(115, 149, 233));

        // Adiciona a estrutura vertical "verticalLimites" ao painel que engloba todos os paineis
        // "painelLimites"
        painelLimites.add(verticalLimites);

        // Adiciona o painel geral (painelLimites) ao menu "limitesItem"
        limitesItem.add(painelLimites);

        // >>>>>>>>>>>>>>> Tratamento de evento Botão "Inserir Ponto"
        for (int i = 0; i < CampoLimites.length; i++) {
            CampoLimites[i].addActionListener(
                    new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (event.getSource() == CampoLimites[0]) {
                        XXimin = Double.parseDouble(event.getActionCommand());
                        desenhar.LimitesXimin(XXimin);
                    }
                    if (event.getSource() == CampoLimites[1]) {
                        XXimax = Double.parseDouble(event.getActionCommand());
                        desenhar.LimitesXimax(XXimax);
                    }
                    if (event.getSource() == CampoLimites[2]) {
                        YYimin = Double.parseDouble(event.getActionCommand());
                        desenhar.LimitesYimin(YYimin);
                    }
                    if (event.getSource() == CampoLimites[3]) {
                        YYimax = Double.parseDouble(event.getActionCommand());
                        desenhar.LimitesYimax(YYimax);
                        repaint();
                    }
                }
            }
            );
        }
        // >>>>>>>>>>>>>>> Fim do Tratamento de evento         
//----- DATA: 26-07-20     *****************************************

//************ KATRI *************** DATA: 10/08/21 ************************
//************ KATRI *************** DATA: 10/08/21 ************************
        //--->>> 3o PASSO = ADICIONAR O PAINEL À ESQUERDA COM CAIXAS DE TEXTO -------<<<<<<<
        /**
         * *******************************************************************************
         */
        // Layout da Janela - gerenciador de Layout BorderLayout
        // Objeto "layoutJanela" da classe BorderLayout
        BorderLayout layoutJanela = new BorderLayout(); // Norte, Sul, Leste, Oeste
        setLayout(layoutJanela); // Configurou o layout do JFrame
        // setLayout(new BorderLayout());

        //**********************  KATRI  -   DATA: 16/08/21 *****************************
        // COLOCAR PAINEL NO NORTE PARA COLOCAR BOTÕES PARA ABRIR PAINEIS DE SEÇÕES 
        // COPIAR DO PROGRAMA CProtendido
        //*******************************************************************************
        //----->>>>> 1* PAINEL = PAINEL DE BOTÕES NO TOPO DA JANELA
        // INICIAR COM BOTÃO = SEÇÃO TRANSVERSAL 
        JPanel painelBotão = new JPanel();
        painelBotão.setBorder(new LineBorder(Color.white));
        FlowLayout layoutpainelBotão = new FlowLayout();
        layoutpainelBotão.setAlignment(FlowLayout.LEFT);
        painelBotão.setLayout(layoutpainelBotão);
        painelBotão.setBackground(new java.awt.Color(121, 214, 239));
        botão = new JButton[NomeBotão.length];
        for (int i = 0; i < NomeBotão.length; i++) {
            botão[i] = new JButton(NomeBotão[i]);
            painelBotão.add(botão[i]);
        }
        // Adiciona painelBoxSeção na janela (no JFrame no EAST)
        add(painelBotão, BorderLayout.NORTH);
        // ******************  FIM DO 1* PAINEL painelBOTÂO *******************

        // KATRI *****************   DATA:  10/08/21 ******************* 
        //KATRI ******************* PAINEL SUL PARA ESCALAS DO DESENHO 
        /**
         * *******************PAINEL SUL ****************
         */
        // Data: 29-03-2020 - COPIADO DO APLICATIVO DE CURVAS
        //*****  21 de Abril de 2019 - copiado do Tolstoi ****//  
        // Criação do Painel ao SUL      
        Unidade = new JTextField("1,00", 3);
        Unidade.setHorizontalAlignment(JTextField.CENTER);
        Unidade.setEditable(false);
        JLabel Escalaunidade = new JLabel("unid:");
        metroTamanho = new JTextField("", 6);
        metroTamanho.setEditable(true);
        metroTamanho.setHorizontalAlignment(JTextField.RIGHT);
        metroTamanho.setToolTipText("Campo recebe digitação"
                + "(número real com ponto)!");
        JLabel pixels = new JLabel("pixels: ");
        /**
         * *******************************************************
         */
        //  Criação do painel "Escala" para a Escala do Zoom    
        JPanel Escala = new JPanel();

        Escala.setBackground(new java.awt.Color(231, 234, 240));
        Escala.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Escala Zoom"));
        Escala.add(Unidade);
        Escala.add(Escalaunidade);
        Escala.add(metroTamanho);
        Escala.add(pixels);
        /* Controle de Zoom Geral  *****/
        JLabel JanelaA = new JLabel("H=");
        H = new JTextField("", 6);
        JLabel JanelaB = new JLabel("unid  V=");
        V = new JTextField("", 6);
        JLabel JanelaC = new JLabel("unid ");

        JPanel JanelaDesenho = new JPanel();
        JanelaDesenho.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Janela"));

        JanelaDesenho.setBackground(new java.awt.Color(231, 234, 240));
        JanelaDesenho.add(JanelaA);
        JanelaDesenho.add(H);
        JanelaDesenho.add(JanelaB);
        JanelaDesenho.add(V);
        JanelaDesenho.add(JanelaC);

        //*************** MOUSE ********************//    NÃO ESTÁ USANDO   
        JPanel painelMouse = new JPanel();
        painelMouse.setBorder(
                new TitledBorder(new LineBorder(Color.BLACK), "Coords Mouse:"));
        JLabel CoordX = new JLabel("X=");
        JTextField CampoX = new JTextField("", 6);
        JLabel UnidadeX = new JLabel("unid");
        JLabel CoordY = new JLabel("Y=");
        JTextField CampoY = new JTextField("", 6);
        JLabel UnidadeY = new JLabel("unid");
        painelMouse.add(CoordX);
        painelMouse.add(CampoX);
        painelMouse.add(UnidadeX);
        painelMouse.add(CoordY);
        painelMouse.add(CampoY);
        painelMouse.add(UnidadeY);
        JLabel mensagemLabel = new JLabel("      Click o mouse !");
        // painelMouse.add(mensagemLabel);               
        //***************FIM DO MOUSE ********************************//

        //***********************************************//       
        JPanel painelStatusDesenho = new JPanel();
        mensagem = new JLabel("Insira ou Selecione Nós");
        painelStatusDesenho.setBorder(
                new TitledBorder(new LineBorder(Color.BLACK), "Editando"));
        painelStatusDesenho.add(mensagem);
        painelStatusDesenho.setBackground(new java.awt.Color(231, 234, 240));
        //***********************************************//  

        // Criação do JPanel "PanelSul" ********** 
        painelSul = new JPanel();
        painelSul.setBorder(new LineBorder(Color.white));
        painelSul.setBackground(new java.awt.Color(121, 214, 239));
        FlowLayout layoutpainelSul = new FlowLayout();
        painelSul.setLayout(layoutpainelSul);
        layoutpainelSul.setAlignment(FlowLayout.LEFT);

        // Adiçao das caixas de texti painel "PanelInf"
        painelSul.add(painelStatusDesenho);
        painelSul.add(Escala);
        painelSul.add(JanelaDesenho);
        painelSul.add(painelMouse);

        //---->>>> Adiciona painelMensagem na janela (no JFrame no SOUTH)  
        add(painelSul, BorderLayout.SOUTH);
//************ FIM DO PAINEL SUL ***************************

//************ PAINEL CENTRO ***************************
        // KATRI *****************   DATA:  13/08/21 *******************
        /*    painelGráfico = 
          new Graf_DesenhaGráfico(H,V,metroTamanho,CampoX,CampoY,
                   dadosCurva, PosiçãoListaPainel1, TEXTO,
                   tamanhoLista, textoRelatório, CampoXY1, mensagemLabel); 
     //*****************************************************************/
        // Cria e inicializa o objeto da classe ProjConcretoB_Desenho    
        desenhar = new ProjConcretoB_Desenho(H, V, metroTamanho, dados,
                CampoX, CampoY);

        //XXimin= -1.0;   XXimax=4.0 ;   YYimin=0.0 ; YYimax=3.0 ;
        XXimin = 0.0;
        XXimax = 180.0;
        YYimin = 0.0;
        YYimax = 100.0;
        desenhar.LimitesXimin(XXimin);
        desenhar.LimitesXimax(XXimax);
        desenhar.LimitesYimin(YYimin);
        desenhar.LimitesYimax(YYimax);
        desenhar.setBorder(new LineBorder(Color.white));

        // Adiciona o painel desenhar na janela (CENTER)
        add(desenhar, BorderLayout.CENTER);
        //********************************************************************          

// KATRI *****************   DATA:  10/08/21 ******************* 
        // **** KATRI ***** DATA: 17/08/21 **************** 
// Data: 28-07-20 *****************************************
        metroTamanho.addActionListener( // Abre parenteses = início da chamada do addActionListener  
                new ActionListener() // Corpo da classe ANONIMA interna private
        {// Abre chaves = início da classe ANONIMA
            // método actionPerformed ( ActionEvent event)
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == metroTamanho) {
                    System.out.printf("dentro do metroTamanho1\n");
                    NumPixelPorMetro = Double.parseDouble(event.getActionCommand());
                    System.out.printf("NumPixelPorMetro = \n", NumPixelPorMetro);
                    desenhar.putEscala(NumPixelPorMetro);
                }
                desenhar.RecalculaEscala();
            }// Fim do metodo actionPerformed
        } // fecha chaves =Fim da classe interna anonima
        );// Fim do parenteses = fim da chamada addAcionListener 
        // ********* Fim da adição de evento ao campo "metroTamanho" do controle de zoom

        // ********* Adição de evento ao campo H" do controle de zoom **********
        H.addActionListener( // Abre parenteses = início da chamada do addActionListener  
                new ActionListener() // Corpo da classe ANONIMA interna private
        {// Abre chaves = início da classe ANONIMA
            // método actionPerformed ( ActionEvent event)
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == H) {
                    desenhar.putLarguraH(Double.parseDouble(event.getActionCommand()));
                }
                desenhar.RecalculaEscala();
            }// Fim do metodo actionPerformed
        } // fecha chaves =Fim da classe interna anonima
        );// Fim do parenteses = fim da chamada addAcionListener 
        // ********* Fim da  adição de evento ao campo H" do controle de zoom **********

        // ********* Adição de evento ao campo V" do controle de zoom **********        
        V.addActionListener( // Abre parenteses = início da chamada do addActionListener  
                new ActionListener() // Corpo da classe ANONIMA interna private
        {// Abre chaves = início da classe ANONIMA
            // método actionPerformed ( ActionEvent event)
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == V) {
                    desenhar.putAlturaV(Double.parseDouble(event.getActionCommand()));
                }
                desenhar.RecalculaEscala();
            }// Fim do metodo actionPerformed
        } // fecha chaves =Fim da classe interna anonima
        );// Fim do parenteses = fim da chamada addAcionListener 
        // ********* Fim da  adição de evento ao campo V" do controle de zoom ******            
        // **** KATRI ***** DATA: 17/08/21 ****************  

//**********  KATRI ********* DATA: 20/08/21 ******************************
        // **** RETIRADO DA PROGRAMAÇÃO DO Daniel Filippin 
        //**************************************************************************
        /**
         * **** PAINEL DE PROPRIEDADES DA SEÇÃO *******************************
         */
        // Criação dos paineis de propriedades com os campos de Texto
        JLabel LabelSeção[] = new JLabel[32];

        LabelSeção[0] = new JLabel("bw =");
        LabelSeção[1] = new JLabel("h =   ");
        LabelSeção[2] = new JLabel("bf =  ");
        LabelSeção[3] = new JLabel("hf =  ");
        LabelSeção[4] = new JLabel("bfs = ");
        LabelSeção[5] = new JLabel("hfs = ");
        LabelSeção[6] = new JLabel("bfi = ");
        LabelSeção[7] = new JLabel("hfi = ");

        // Como o FTOOL faz -  colocar na tela abaixo das dimensões da seção                
        LabelSeção[8] = new JLabel("Área =  ");
        LabelSeção[9] = new JLabel("Xcg =   ");
        LabelSeção[10] = new JLabel("Ycg =   ");
        LabelSeção[11] = new JLabel("Ixx =   ");
        LabelSeção[12] = new JLabel("Iyy =   ");
        LabelSeção[13] = new JLabel("Ixy =   ");

        JTextField CampoSeção[] = new JTextField[14];

        LabelSeção[14] = new JLabel("cm ");
        LabelSeção[15] = new JLabel("cm ");
        LabelSeção[16] = new JLabel("cm ");
        LabelSeção[17] = new JLabel("cm ");
        LabelSeção[18] = new JLabel("cm ");
        LabelSeção[19] = new JLabel("cm ");
        LabelSeção[20] = new JLabel("cm ");
        LabelSeção[21] = new JLabel("cm ");
        LabelSeção[22] = new JLabel("cm2");
        LabelSeção[23] = new JLabel("cm ");
        LabelSeção[24] = new JLabel("cm ");
        LabelSeção[25] = new JLabel("cm4");
        LabelSeção[26] = new JLabel("cm4");
        LabelSeção[27] = new JLabel("cm4");

        JPanel painelSeção[] = new JPanel[18];
        FlowLayout layoutPainelSeção[] = new FlowLayout[18];

        for (int i = 0; i <= 13; i++) {
            CampoSeção[i] = new JTextField("", 8);
            painelSeção[i] = new JPanel();

            painelSeção[i].add(LabelSeção[i]);
            painelSeção[i].add(CampoSeção[i]);
            painelSeção[i].add(LabelSeção[i + 14]);

            layoutPainelSeção[i] = new FlowLayout();
            layoutPainelSeção[i].setAlignment(FlowLayout.CENTER);
            painelSeção[i].setLayout(layoutPainelSeção[i]);
            painelSeção[i].setBackground(new java.awt.Color(231, 234, 240));
        }
        // ATE AQUI 14 PAINEIS

        /**
         * **** ESTRUTURAÇÃO DOS PAINEIS *****************************
         */
        // TÍTULOS DOS GRUPOS DE PAINÉIS
        LabelSeção[28] = new JLabel("SEÇÃO TRANSVERSAL");
        LabelSeção[29] = new JLabel("Tipo de Seção:");
        LabelSeção[30] = new JLabel("Dimensões:");

        for (int i = 14; i <= 16; i++) // 3 PAINEIS=  14,  15  e  16 
        {
            painelSeção[i] = new JPanel();
            painelSeção[i].add(LabelSeção[i + 14]); // 14+14 = 28
            painelSeção[i].setBackground(new java.awt.Color(231, 234, 240));
        }
        // Painel 17 Propriedades Geométricas para mostrar na tela 
        LabelSeção[31] = new JLabel("Propriedades Geométricas:");
        painelSeção[17] = new JPanel();
        painelSeção[17].add(LabelSeção[31]);//PropGeom
        painelSeção[17].setBackground(new java.awt.Color(231, 234, 240));

        /**
         * ********************************************************************
         */
        //PAINEIS NÃO VISÍVEIS
        for (int i = 0; i <= 13; i++) //  14 paineis
        {
            painelSeção[i].setVisible(false);
        }
        painelSeção[16].setVisible(false);
        painelSeção[17].setVisible(false); // False nas Prop. Geométricas 

        for (int i = 8; i <= 13; i++) //  14 paineis
        {
            CampoSeção[i].setEditable(false);
        }

        //********** FIM DA KATRI     ***********************************************    
        // MENU DE ESCOLHA DO TIPO DE SEÇÃO   DATA: 19/08/21 KATRI *********
        //******************************************************************
        // Inicializa objeto JComboBox, passa o vetor nomesSeção
        menuSeçãoJComboBox = new JComboBox(nomesSeção);
        menuSeçãoJComboBox.setMaximumRowCount(5); // Exibe 5 linhas (4 seções)

        JPanel painelSeçãoJComboBox = new JPanel();
        painelSeçãoJComboBox.setBackground(new java.awt.Color(231, 234, 240));
        // Coloca em um painel 
        painelSeçãoJComboBox.add(menuSeçãoJComboBox);
        /**
         * ***************************************************************************
         */

        /**
         * **** ESPAÇADOR INVISÍVEL DO PAINEL DE SEÇÃO
         * *******************************
         */
        JTextField CampoSeçãoInv = new JTextField("", 25);
        CampoSeçãoInv.setBackground(new java.awt.Color(231, 234, 240));
        CampoSeçãoInv.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        CampoSeçãoInv.setEditable(false);

        JPanel painelSeçãoInv = new JPanel();

        painelSeçãoInv.setBackground(new java.awt.Color(231, 234, 240));
        painelSeçãoInv.add(CampoSeçãoInv);
        /**
         * ***************************************************************************
         */

        /**
         * **** BOTÃO para Calcular Propriedades Geométricas
         * ***************************************************
         */
        JButton BotãoPropGeom = new JButton("Calcular propriedades geométricas");
        JPanel painelSeçãoBotãoPropGeom = new JPanel();

        painelSeçãoBotãoPropGeom.add(BotãoPropGeom);

        painelSeçãoBotãoPropGeom.setVisible(false);
        painelSeçãoBotãoPropGeom.setBackground(new java.awt.Color(231, 234, 240));
        painelSeçãoBotãoPropGeom.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        painelSeçãoBotãoPropGeom.setLayout(new FlowLayout());
        /**
         * ***************************************************************************
         */

        /**
         * **** BOTÃO para Desenhar a SEÇÂO
         * ********************************************
         */
        JButton BotãoDesSeção = new JButton("Desenhar a seção ");
        JPanel painelSeçãoBotãoDesSeção = new JPanel();

        painelSeçãoBotãoDesSeção.add(BotãoDesSeção);

        painelSeçãoBotãoDesSeção.setVisible(false);
        painelSeçãoBotãoDesSeção.setBackground(new java.awt.Color(231, 234, 240));
        painelSeçãoBotãoDesSeção.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        painelSeçãoBotãoDesSeção.setLayout(new FlowLayout());
        /**
         * ***************************************************************************
         */

        /**
         * **** PAINEL QUE APRESENTA AS IMAGENS DOS TIPOS DE SEÇÕES
         * ******************
         */
        labelMenuSeção = new JLabel(icons[0]);
        labelMenuSeção.setBackground(new java.awt.Color(231, 234, 240));

        JPanel painelSeçãolabelMenuSeção = new JPanel();
        painelSeçãolabelMenuSeção.add(labelMenuSeção);

        painelSeçãolabelMenuSeção.setBackground(new java.awt.Color(231, 234, 240));
        //****************************************************************   

        /**
         * **** TRATAMENTO DE EVENTO: MENU DE ESCOLHA DAS SEÇÕES
         * *********************
         */
        menuSeçãoJComboBox.addItemListener(
                new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    labelMenuSeção.setIcon(icons[menuSeçãoJComboBox.getSelectedIndex()]);
                    for (int i = 0; i <= 13; i++) {
                        painelSeção[i].setVisible(false);
                    }

                    switch (menuSeçãoJComboBox.getSelectedIndex()) {
                        case 0:
                            painelSeçãolabelMenuSeção.setVisible(true);
                            // Definição do tipo de Seção
                            nomeSeçãoInterface = "";
                            painelSeção[16].setVisible(false);
                            painelSeção[17].setVisible(false);
                            for (int i = 0; i <= 7; i++) {
                                CampoSeção[i].setText(String.format(""));
                            }

                            painelSeçãoBotãoDesSeção.setVisible(false);
                            painelSeçãoBotãoPropGeom.setVisible(false);

                            CampoSeção[8].setText(String.format(""));
                            CampoSeção[9].setText(String.format(""));
                            CampoSeção[10].setText(String.format(""));
                            CampoSeção[11].setText(String.format(""));
                            CampoSeção[12].setText(String.format(""));
                            CampoSeção[13].setText(String.format(""));

                            break;
                        case 1:
                            // Caso o tipo de seção selecionado seja diferente do atual
                            // Os campos de texto são limpos
                            if (!"SEÇÃO RETANGULAR".equals(nomeSeçãoInterface)) {
                                for (int i = 0; i <= 7; i++) {
                                    CampoSeção[i].setText(String.format(""));
                                    painelSeção[i].setVisible(false);
                                }
                            }

                            painelSeçãolabelMenuSeção.setVisible(true);

                            nomeSeçãoInterface = "SEÇÃO RETANGULAR";
                            painelSeção[0].setVisible(true);
                            painelSeção[1].setVisible(true);
                            painelSeção[14].setVisible(true);
                            painelSeção[15].setVisible(true);
                            painelSeção[16].setVisible(true);
                            painelSeção[17].setVisible(true);

                            painelSeçãoBotãoDesSeção.setVisible(true);
                            painelSeçãoBotãoPropGeom.setVisible(true);
                            for (int i = 8; i <= 13; i++) {
                                painelSeção[i].setVisible(true);
                            }

                            CampoSeção[8].setText(String.format(""));
                            CampoSeção[9].setText(String.format(""));
                            CampoSeção[10].setText(String.format(""));
                            CampoSeção[11].setText(String.format(""));
                            CampoSeção[12].setText(String.format(""));
                            CampoSeção[13].setText(String.format(""));

                            break;

                        case 2:
                            if (!"SEÇÃO T".equals(nomeSeçãoInterface)) {
                                for (int i = 0; i <= 7; i++) {
                                    CampoSeção[i].setText(String.format(""));
                                    painelSeção[i].setVisible(false);
                                }
                            }

                            painelSeçãolabelMenuSeção.setVisible(true);

                            nomeSeçãoInterface = "SEÇÃO T";
                            painelSeção[2].setVisible(true);
                            painelSeção[1].setVisible(true);
                            painelSeção[0].setVisible(true);
                            painelSeção[3].setVisible(true);
                            painelSeção[14].setVisible(true);
                            painelSeção[15].setVisible(true);
                            painelSeção[16].setVisible(true);
                            painelSeção[17].setVisible(true);
                            painelSeçãoBotãoPropGeom.setVisible(true);
                            for (int i = 8; i <= 13; i++) {
                                painelSeção[i].setVisible(true);
                            }

                            painelSeçãoBotãoDesSeção.setVisible(true);
                            painelSeçãoBotãoPropGeom.setVisible(true);

                            CampoSeção[8].setText(String.format(""));
                            CampoSeção[9].setText(String.format(""));
                            CampoSeção[10].setText(String.format(""));
                            CampoSeção[11].setText(String.format(""));
                            CampoSeção[12].setText(String.format(""));
                            CampoSeção[13].setText(String.format(""));

                            break;
                        case 3:
                            if (!"SEÇÃO I".equals(nomeSeçãoInterface)) {
                                for (int i = 0; i <= 7; i++) {
                                    CampoSeção[i].setText(String.format(""));
                                    painelSeção[i].setVisible(false);
                                }
                            }

                            painelSeçãolabelMenuSeção.setVisible(true);

                            nomeSeçãoInterface = "SEÇÃO I";
                            painelSeção[1].setVisible(true);
                            painelSeção[4].setVisible(true);
                            painelSeção[5].setVisible(true);
                            painelSeção[6].setVisible(true);
                            painelSeção[7].setVisible(true);
                            painelSeção[0].setVisible(true);
                            painelSeção[14].setVisible(true);
                            painelSeção[15].setVisible(true);
                            painelSeção[16].setVisible(true);
                            painelSeção[17].setVisible(true);
                            painelSeçãoBotãoPropGeom.setVisible(true);
                            for (int i = 8; i <= 13; i++) {
                                painelSeção[i].setVisible(true);
                            }

                            painelSeçãoBotãoDesSeção.setVisible(true);
                            painelSeçãoBotãoPropGeom.setVisible(true);

                            CampoSeção[8].setText(String.format(""));
                            CampoSeção[9].setText(String.format(""));
                            CampoSeção[10].setText(String.format(""));
                            CampoSeção[11].setText(String.format(""));
                            CampoSeção[12].setText(String.format(""));
                            CampoSeção[13].setText(String.format(""));

                            break;

                        case 4:
                            if (!"SEÇÃO L".equals(nomeSeçãoInterface)) {
                                for (int i = 0; i <= 7; i++) {
                                    CampoSeção[i].setText(String.format(""));
                                    painelSeção[i].setVisible(false);
                                }
                            }

                            painelSeçãolabelMenuSeção.setVisible(true);

                            nomeSeçãoInterface = "SEÇÃO L";
                            painelSeção[1].setVisible(true);
                            painelSeção[2].setVisible(true);
                            painelSeção[0].setVisible(true);
                            painelSeção[3].setVisible(true);
                            painelSeção[14].setVisible(true);
                            painelSeção[15].setVisible(true);
                            painelSeção[16].setVisible(true);
                            painelSeção[17].setVisible(true);

                            painelSeçãoBotãoDesSeção.setVisible(true);
                            painelSeçãoBotãoPropGeom.setVisible(true);

                            for (int i = 8; i <= 13; i++) {
                                painelSeção[i].setVisible(true);
                            }

                            CampoSeção[8].setText(String.format(""));
                            CampoSeção[9].setText(String.format(""));
                            CampoSeção[10].setText(String.format(""));
                            CampoSeção[11].setText(String.format(""));
                            CampoSeção[12].setText(String.format(""));
                            CampoSeção[13].setText(String.format(""));

                            break;
                    }
                }
            }
        }
        );
        /**
         * ***************************************************************************
         */

        // SUBIR ESSE CÓDIGO 
        /**
         * **** PAINEL PRINCIPAL DE PROPRIEDADES DAS SEÇÕES
         * **************************
         */
        JPanel painelBoxSeção = new JPanel();

        //setLayout(new BorderLayout());
        //add(scroll, BorderLayout.CENTER);
        /*setSize(300, 300);
        setVisible(true);
        painelBoxSeção.setBackground(new java.awt.Color(231, 234, 240));*/
        /**
         * ***************************************************************************
         */
        /**
         * **** ESTRUTURA VERTICAL DO PAINEL PRINCIPAL DE PROPRIEDADES DAS
         * SEÇÕES ****
         */
        Box verticalSeção = Box.createVerticalBox();
        /*  verticalSeção.pack();
        verticalSeção.setLocationRelativeTo(null);*/
        verticalSeção.setVisible(true);
        verticalSeção.add(Box.createVerticalStrut(5));
        verticalSeção.add(painelSeçãoInv);// Espaçador para padronizar tamanho        
        verticalSeção.add(painelSeção[14]);// SEÇÂO TRANSVERSAL
        verticalSeção.add(painelSeção[15]);// Tipo de Seção

        verticalSeção.add(painelSeçãoJComboBox); // JComboBox menu       

        verticalSeção.add(painelSeçãolabelMenuSeção); // Label Figuras das seções                        

        verticalSeção.add(painelSeção[16]);//Dimensões das Seções        
        for (int i = 0; i <= 7; i++) {
            verticalSeção.add(painelSeção[i]); // Dimensões das seções
        }
        verticalSeção.add(painelSeçãoBotãoDesSeção); // Botão = Desenha a seção
        verticalSeção.add(painelSeçãoBotãoPropGeom); // Botão = Calcula as Prop.Geom.
        verticalSeção.add(painelSeção[17]); // Propridades Geométricas

        for (int i = 8; i <= 13; i++) {
            verticalSeção.add(painelSeção[i]);// Prop.= A, Ycg, Xcg, Ixx, Iyy, Ixy        
        }

        verticalSeção.setMaximumSize(new Dimension(298, 800));
        verticalSeção.setBackground(new java.awt.Color(231, 234, 240));
        DrawingPanel painelSecaoScroll = new DrawingPanel(verticalSeção, 800, 238);
        painelSecaoScroll.setBackground(new java.awt.Color(231, 234, 240));

        final JScrollPane scroll = new JScrollPane(painelSecaoScroll);
        scroll.setBackground(new java.awt.Color(231, 234, 240));
        scroll.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        scroll.setVisible(true);
        painelBoxSeção.add(scroll);

        /**
         * ***************************************************************************
         */
        /**
         * **** ADIÇÃO DO PAINEL PRINCIPAL DE PROPRIEDADES DAS SEÇÕES A DIREITA
         * ********
         */
        add(painelBoxSeção, BorderLayout.EAST);
        /**
         * ***************************************************************************
         */

//*********************************************************************/ 
// ******************** PAINEL PROPRIEDADES DO MATERIAL ***************
        JLabel LabelMaterial[] = new JLabel[11]; //Vetor de Label

        LabelMaterial[0] = new JLabel("fck = ");
        LabelMaterial[1] = new JLabel("fyk = ");

        // Painéis com dados que serão calculados e fornecidos (não editáveis)
        LabelMaterial[2] = new JLabel("fcd = ");
        LabelMaterial[3] = new JLabel("fyd = ");

        JTextField CampoMaterial[] = new JTextField[4];

        LabelMaterial[4] = new JLabel("MPa");
        LabelMaterial[5] = new JLabel("MPa");
        LabelMaterial[6] = new JLabel("MPa");
        LabelMaterial[7] = new JLabel("MPa");

        JPanel painelMaterial[] = new JPanel[8];
        FlowLayout layoutPainelMaterial[] = new FlowLayout[8];

        for (int i = 0; i <= 3; i++) {
            CampoMaterial[i] = new JTextField("", 8);
            painelMaterial[i] = new JPanel();

            painelMaterial[i].add(LabelMaterial[i]);
            painelMaterial[i].add(CampoMaterial[i]);
            painelMaterial[i].add(LabelMaterial[i + 4]);

            layoutPainelMaterial[i] = new FlowLayout();
            layoutPainelMaterial[i].setAlignment(FlowLayout.CENTER);
            painelMaterial[i].setLayout(layoutPainelMaterial[i]);
            painelMaterial[i].setBackground(new java.awt.Color(231, 234, 240));

        }

        // ************* ESTRUTURAÇÃO DOS PAINÉIS ********************
        // TÍTULOS DOS GRUPOS DE PAINÉIS
        LabelMaterial[8] = new JLabel("MATERIAL");
        LabelMaterial[9] = new JLabel("Dados do Material:");

        for (int i = 4; i <= 5; i++) {
            painelMaterial[i] = new JPanel();
            painelMaterial[i].add(LabelMaterial[i + 4]);
            painelMaterial[i].setBackground(new java.awt.Color(231, 234, 240));

        }

//        // Painel dos dados que serão calculados - não editáveis
//        LabelMaterial[10] = new JLabel("Propriedades do Material:");
//        painelMaterial[6] = new JPanel();
//        painelMaterial[6].add(LabelMaterial[10]);
//        painelMaterial[6].setBackground(new java.awt.Color(231, 234, 240));
        // PAINÉIS VISÍVEIS
        for (int i = 0; i <= 3; i++) {
            painelMaterial[i].setVisible(true);
        }

        painelMaterial[4].setVisible(true);
        painelMaterial[5].setVisible(true); // False nas Propriedades do material 

        for (int i = 2; i <= 3; i++) {
            CampoMaterial[i].setEditable(false);
        }

        /**
         * **** ESPAÇADOR INVISÍVEL DO PAINEL DE MATERIAL
         * *******************************
         */
        JTextField CampoMaterialInv = new JTextField("", 25);
        CampoMaterialInv.setBackground(new java.awt.Color(231, 234, 240));
        CampoMaterialInv.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        CampoMaterialInv.setEditable(false);

        JPanel painelMaterialInv = new JPanel();

        painelMaterialInv.setBackground(new java.awt.Color(231, 234, 240));
        painelMaterialInv.add(CampoMaterialInv);
        /**
         * ***************************************************************************
         */

        /**
         * **** BOTÃO para Calcular Propriedades do Material
         * ***************************************************
         */
        JButton BotãoPropMaterial = new JButton("Propriedades do Material");
        JPanel painelMaterialBotãoPropMaterial = new JPanel();

        painelMaterialBotãoPropMaterial.add(BotãoPropMaterial);

        painelMaterialBotãoPropMaterial.setVisible(true);
        painelMaterialBotãoPropMaterial.setBackground(new java.awt.Color(231, 234, 240));
        painelMaterialBotãoPropMaterial.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        painelMaterialBotãoPropMaterial.setLayout(new FlowLayout());
        /**
         * ***************************************************************************
         */

//*********** PAINEL PRINCIPAL DE PROPRIEDADES DO MATERIAL *******
        JPanel painelBoxMaterial = new JPanel();
        painelBoxMaterial.setBackground(new java.awt.Color(231, 234, 240));
//****************************************************************

        /**
         * **** ESTRUTURA VERTICAL DO PAINEL PRINCIPAL DE PROPRIEDADES DAS
         * SEÇÕES ****
         */
        Box verticalMaterial = Box.createVerticalBox();

        verticalMaterial.add(painelMaterialInv);// Espaçador para padronizar tamanho        
        verticalMaterial.add(painelMaterial[4]);// Material
        verticalMaterial.add(painelMaterial[5]);// Tipo de Seção

        //verticalMaterial.add(painelMateriallabelMenuMaterial); // Label Figuras das seções                        
        verticalMaterial.add(painelMaterial[2]);//Dimensões das Seções        
        for (int i = 0; i <= 1; i++) {
            verticalMaterial.add(painelMaterial[i]); // Dimensões das seções
        }
        verticalMaterial.add(painelMaterialBotãoPropMaterial); // Botão = Calcula as propriedades do material        
        verticalMaterial.add(painelMaterial[3]); // Propridades do material

        for (int i = 2; i <= 3; i++) {
            verticalMaterial.add(painelMaterial[i]); // Propriedades do material      
        }
        painelBoxMaterial.add(verticalMaterial);
        /**
         * ***************************************************************************
         */

        /**
         * **** ADIÇÃO DO PAINEL PRINCIPAL DE PROPRIEDADES DAS SEÇÕES A DIREITA
         * ********
         */
        add(painelBoxMaterial, BorderLayout.EAST);
        /**
         * ***************************************************************************
         */

        //******************* FIM DO painelBoxMaterial na esquerda (west)
        // ******************** ESFORÇOS SOLICITANTES - BOX VERTICAL ************************
        JLabel LabelCarregamento[] = new JLabel[17]; //Vetor de Label

        LabelCarregamento[0] = new JLabel("     Md = ");
        LabelCarregamento[1] = new JLabel("    Nd = ");
        LabelCarregamento[2] = new JLabel("    Msd = ");
        LabelCarregamento[3] = new JLabel("    X = ");
        LabelCarregamento[4] = new JLabel("e = ");
        LabelCarregamento[5] = new JLabel("es = ");
        LabelCarregamento[6] = new JLabel("µsd = ");

        JTextField CampoCarregamento[] = new JTextField[7];

        LabelCarregamento[7] = new JLabel("kNm");
        LabelCarregamento[8] = new JLabel("kN");
        LabelCarregamento[9] = new JLabel("kNm");
        LabelCarregamento[10] = new JLabel("m");
        LabelCarregamento[11] = new JLabel("m");
        LabelCarregamento[12] = new JLabel("m");
        LabelCarregamento[13] = new JLabel("");

        JPanel painelCarregamento[] = new JPanel[13];
        FlowLayout layoutPainelCarregamento[] = new FlowLayout[9];

        for (int i = 0; i <= 6; i++) {
            CampoCarregamento[i] = new JTextField("", 8);
            painelCarregamento[i] = new JPanel();
            painelCarregamento[i].add(LabelCarregamento[i]);
            painelCarregamento[i].add(CampoCarregamento[i]);

            painelCarregamento[i].add(LabelCarregamento[i + 7]);

            layoutPainelCarregamento[i] = new FlowLayout();
            layoutPainelCarregamento[i].setAlignment(FlowLayout.CENTER);
            painelCarregamento[i].setLayout(layoutPainelCarregamento[i]);
            painelCarregamento[i].setBackground(new java.awt.Color(231, 234, 240));

        }

// ************* ESTRUTURAÇÃO DOS PAINÉIS ********************
        // TÍTULOS DOS GRUPOS DE PAINÉIS
        LabelCarregamento[14] = new JLabel("CARREGAMENTO");
        LabelCarregamento[15] = new JLabel("Dados do Carregamento:");

        for (int i = 7; i <= 8; i++) {
            painelCarregamento[i] = new JPanel();
            painelCarregamento[i].add(LabelCarregamento[i + 7]);
            painelCarregamento[i].setBackground(new java.awt.Color(231, 234, 240));

        }

        //LabelCarregamento[16] = new JLabel("  ");
        // painelCarregamento[4].add(LabelCarregamento[10]);
        // PAINÉIS VISÍVEIS
        for (int i = 0; i <= 3; i++) {
            painelCarregamento[i].setVisible(true);
        }
        // Painel não editável - visibilidade falsa 
        CampoCarregamento[0].setEditable(false);
        CampoCarregamento[1].setEditable(false);
        CampoCarregamento[2].setEditable(false);
        CampoCarregamento[3].setEditable(false);
        CampoCarregamento[4].setEditable(false);

        // MENU DE ESCOLHA DO TIPO DE FLEXÃO - SIMPLES OU COMPOSTA  *********
        //******************************************************************
        // Inicializa objeto JComboBox, para o vetor nomesSeção
        menuCarregamentoFlexãoJComboBox = new JComboBox(nomesCarregamentoFlexão);
        menuCarregamentoFlexãoJComboBox.setMaximumRowCount(3); // Exibe 2 linhas (2 armaduras)

        menuCarregamentoFlexãoCompostaJComboBox = new JComboBox(nomesCarregamentoFlexãoComposta);
        menuCarregamentoFlexãoCompostaJComboBox.setMaximumRowCount(3); // Exibe 2 linhas (2 armaduras)

        JPanel painelCarregamentoFlexãoJComboBox = new JPanel();
        painelCarregamentoFlexãoJComboBox.setBackground(new java.awt.Color(231, 234, 240));
        // Coloca em um painel 
        painelCarregamentoFlexãoJComboBox.add(menuCarregamentoFlexãoJComboBox);

        JPanel painelCarregamentoFlexãoCompostaJComboBox = new JPanel();
        painelCarregamentoFlexãoCompostaJComboBox.setBackground(new java.awt.Color(231, 234, 240));
        // Coloca em um painel 
        painelCarregamentoFlexãoCompostaJComboBox.add(menuCarregamentoFlexãoCompostaJComboBox);
        /**
         * ***************************************************************************
         */

        /**
         * **** ESPAÇADOR INVISÍVEL DO PAINEL DE CARREGAMENTO
         * *******************************
         */
        JTextField CampoCarregamentoInv = new JTextField("", 25);
        CampoCarregamentoInv.setBackground(new java.awt.Color(231, 234, 240));
        CampoCarregamentoInv.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        CampoCarregamentoInv.setEditable(false);

        JPanel painelCarregamentoInv = new JPanel();
        painelCarregamentoInv.setBackground(new java.awt.Color(231, 234, 240));
        painelCarregamentoInv.add(CampoCarregamentoInv);
        /**
         * ***************************************************************************
         */

        /**
         * **** BOTÃO para Calcular Propriedades do Carregamento
         * ***************************************************
         */
        JButton BotãoCalcExcentricidade = new JButton("Calcular excentricidade");
        BotãoCalcExcentricidade.setEnabled(false);
        BotãoCalcExcentricidade.setText("Selecione uma flexão");
        JPanel painelCarregamentoBotãoCalcExcentricidade = new JPanel();

        painelCarregamentoBotãoCalcExcentricidade.add(BotãoCalcExcentricidade);

        painelCarregamentoBotãoCalcExcentricidade.setVisible(true);
        painelCarregamentoBotãoCalcExcentricidade.setBackground(new java.awt.Color(231, 234, 240));
        painelCarregamentoBotãoCalcExcentricidade.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        painelCarregamentoBotãoCalcExcentricidade.setLayout(new FlowLayout());
        /**
         * ***************************************************************************
         */

//*********** PAINEL PRINCIPAL DE PROPRIEDADES DO CARREGAMENTO *******
        JPanel painelBoxCarregameto = new JPanel();
        painelBoxCarregameto.setBackground(new java.awt.Color(231, 234, 240));
//****************************************************************

        /**
         * **** ESTRUTURA VERTICAL DO PAINEL PRINCIPAL DE PROPRIEDADES DAS
         * SEÇÕES ****
         */
        Box verticalCarregamento = Box.createVerticalBox();

        verticalCarregamento.add(painelCarregamentoInv);// Espaçador para padronizar tamanho   
        verticalCarregamento.add(painelCarregamento[7]);
        verticalCarregamento.add(painelCarregamento[8]);
        verticalCarregamento.add(painelCarregamentoFlexãoJComboBox);
        verticalCarregamento.add(painelCarregamentoFlexãoCompostaJComboBox);
        verticalCarregamento.add(painelCarregamento[0]);// Material
        verticalCarregamento.add(painelCarregamento[1]);
        verticalCarregamento.add(painelCarregamento[2]);// Tipo de Seção
        verticalCarregamento.add(painelCarregamentoBotãoCalcExcentricidade);   // Botão = Calcula as propriedades do material         
        verticalCarregamento.add(painelCarregamento[3]);
        verticalCarregamento.add(painelCarregamento[4]);
        verticalCarregamento.add(painelCarregamento[5]);
        verticalCarregamento.add(painelCarregamento[6]);

        painelBoxCarregameto.add(verticalCarregamento);
        /**
         * ***************************************************************************
         */

        /**
         * **** ADIÇÃO DO PAINEL PRINCIPAL DE PROPRIEDADES DO CARREGAMENTO À
         * DIREITA ********
         */
        add(painelBoxCarregameto, BorderLayout.EAST);
        painelCarregamento[0].setVisible(false);
        painelCarregamento[1].setVisible(false);
        painelCarregamento[2].setVisible(false);
        painelCarregamento[3].setVisible(false);
        painelCarregamento[4].setVisible(false);
        painelCarregamento[5].setVisible(false);
        painelCarregamento[6].setVisible(false);
        painelCarregamentoFlexãoCompostaJComboBox.setVisible(false);
        BotãoCalcExcentricidade.setVisible(false);

        /**
         * ***************************************************************************
         */
        menuCarregamentoFlexãoCompostaJComboBox.addItemListener(
                new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    if (menuCarregamentoFlexãoCompostaJComboBox.getSelectedIndex() == 0) {
                        painelCarregamento[0].setVisible(false);
                        painelCarregamento[1].setVisible(false);
                        painelCarregamento[2].setVisible(false);
                        painelCarregamento[3].setVisible(false);
                        painelCarregamento[4].setVisible(false);
                        painelCarregamento[5].setVisible(false);
                        painelCarregamento[6].setVisible(false);
                        BotãoCalcExcentricidade.setText("Calcular excentricidade e µsd");
                        CampoCarregamento[5].setText("");
                        CampoCarregamento[6].setText("");
                        BotãoCalcExcentricidade.setVisible(false);

                    } else if (menuCarregamentoFlexãoCompostaJComboBox.getSelectedIndex() == 1) {
                        painelCarregamento[0].setVisible(true);
                        painelCarregamento[1].setVisible(true);
                        painelCarregamento[2].setVisible(false);
                        painelCarregamento[3].setVisible(true);
                        painelCarregamento[4].setVisible(true);
                        painelCarregamento[5].setVisible(true);
                        painelCarregamento[6].setVisible(true);
                        painelCarregamentoFlexãoCompostaJComboBox.setVisible(true);
                        BotãoCalcExcentricidade.setText("Calcular excentricidade e µsd");
                        CampoCarregamento[6].setText("");
                        BotãoCalcExcentricidade.setVisible(true);
                        CampoCarregamento[5].setText("");

                    } else {
                        painelCarregamento[0].setVisible(true);
                        painelCarregamento[1].setVisible(true);
                        painelCarregamento[2].setVisible(false);
                        painelCarregamento[3].setVisible(true);
                        painelCarregamento[4].setVisible(true);
                        painelCarregamento[5].setVisible(true);
                        painelCarregamento[6].setVisible(true);
                        painelCarregamentoFlexãoCompostaJComboBox.setVisible(true);
                        BotãoCalcExcentricidade.setText("Calcular excentricidade e µsd");
                        CampoCarregamento[5].setText("");
                        CampoCarregamento[6].setText("");
                        BotãoCalcExcentricidade.setVisible(true);

                    }
                }
            }
        });

        menuCarregamentoFlexãoJComboBox.addItemListener(
                new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                CampoCarregamento[0].setEditable(true);
                CampoCarregamento[1].setEditable(true);
                CampoCarregamento[2].setEditable(true);
                CampoCarregamento[3].setEditable(false);
                CampoCarregamento[4].setEditable(false);
                CampoCarregamento[5].setEditable(false);
                CampoCarregamento[6].setEditable(false);
//                painelCarregamento[4].setVisible(false);
//                painelCarregamento[5].setVisible(false);
//                painelCarregamento[6].setVisible(false);
                BotãoCalcExcentricidade.setEnabled(true);
                BotãoCalcExcentricidade.setVisible(true);

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    //labelMenuDetalhamento.setIcon(icons[menuCarregamentoFlexãoJComboBox.getSelectedIndex()]);
                    if (menuCarregamentoFlexãoJComboBox.getSelectedIndex() == 1) {
                        menuCarregamentoFlexãoCompostaJComboBox.setSelectedIndex(0);
                        BotãoCalcExcentricidade.setVisible(true);
                        painelCarregamento[0].setVisible(false);
                        painelCarregamento[1].setVisible(false);
                        painelCarregamento[2].setVisible(true);
                        painelCarregamento[3].setVisible(false);
                        painelCarregamento[4].setVisible(false);
                        painelCarregamento[5].setVisible(false);
                        painelCarregamento[6].setVisible(true);
                        BotãoCalcExcentricidade.setText("Calcular µsd");
                        CampoCarregamento[6].setText("");
                        painelCarregamentoFlexãoCompostaJComboBox.setVisible(false);

                    } else if (menuCarregamentoFlexãoJComboBox.getSelectedIndex() == 2) {
//                        painelCarregamento[0].setVisible(true);
//                        painelCarregamento[1].setVisible(false);
//                        painelCarregamento[2].setVisible(true);
                        painelCarregamento[2].setVisible(false);
//                        painelCarregamento[4].setVisible(true);
//                        painelCarregamento[5].setVisible(true);
                        painelCarregamento[6].setVisible(false);
                        BotãoCalcExcentricidade.setVisible(false);
                        painelCarregamentoFlexãoCompostaJComboBox.setVisible(true);
                        //BotãoCalcExcentricidade.setText("Calcular excentricidade e µsd");
                        CampoCarregamento[6].setText("");

                    } else {
                        painelCarregamentoFlexãoCompostaJComboBox.setVisible(false);
                        painelCarregamento[0].setVisible(false);
                        painelCarregamento[1].setVisible(false);
                        painelCarregamento[2].setVisible(false);
                        painelCarregamento[3].setVisible(false);
                        painelCarregamento[4].setVisible(false);
                        painelCarregamento[5].setVisible(false);
                        painelCarregamento[6].setVisible(false);
                        painelCarregamentoFlexãoCompostaJComboBox.setVisible(false);
                        menuCarregamentoFlexãoCompostaJComboBox.setSelectedIndex(0);
                        BotãoCalcExcentricidade.setVisible(false);
                        BotãoCalcExcentricidade.setText("Selecione uma flexão");
                        CampoCarregamento[6].setText("");
                    }

                }
            }
        }
        );

        //******************* FIM DO painelBoxSolicitação na esquerda (west)
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        /**
         * **** PAINEL DE PROPRIEDADES DO DETALHAMENTO
         * *******************************
         */
        // Criação dos paineis de propriedades com os campos de Texto
        JLabel LabelDetalhamento[] = new JLabel[29];

        LabelDetalhamento[0] = new JLabel("    c nom =  ");
        LabelDetalhamento[1] = new JLabel("       ϕt =    ");
        LabelDetalhamento[2] = new JLabel("ϕ (compressão) = ");
        LabelDetalhamento[3] = new JLabel("ϕ (tração) =  ");
        LabelDetalhamento[4] = new JLabel("ϕ (agregado) = ");
        LabelDetalhamento[5] = new JLabel("aH (tração) = ");
        LabelDetalhamento[6] = new JLabel("aV (tração) = ");
        LabelDetalhamento[7] = new JLabel("aH (compressão) =");
        LabelDetalhamento[8] = new JLabel("aV (compressão) =");

        // Não editáveis - será calculado e fornecido
        LabelDetalhamento[9] = new JLabel("         d' =   ");
        LabelDetalhamento[10] = new JLabel("        d =    ");
        LabelDetalhamento[11] = new JLabel("        d\" =  ");

        JTextField CampoDetalhamento[] = new JTextField[12];

        LabelDetalhamento[12] = new JLabel("cm   ");
        LabelDetalhamento[13] = new JLabel("cm ");
        LabelDetalhamento[14] = new JLabel("cm               ");
        LabelDetalhamento[15] = new JLabel("cm      ");
        LabelDetalhamento[16] = new JLabel("cm          ");
        LabelDetalhamento[17] = new JLabel("cm      ");
        LabelDetalhamento[18] = new JLabel("cm      ");
        LabelDetalhamento[19] = new JLabel("cm                ");
        LabelDetalhamento[20] = new JLabel("cm                ");
        LabelDetalhamento[21] = new JLabel("cm   ");
        LabelDetalhamento[22] = new JLabel("cm   ");
        LabelDetalhamento[23] = new JLabel("cm   ");

        JPanel painelDetalhamento[] = new JPanel[16];
        FlowLayout layoutPainelDetalhamento[] = new FlowLayout[16];

        for (int i = 0; i <= 11; i++) {
            CampoDetalhamento[i] = new JTextField("", 6);
            painelDetalhamento[i] = new JPanel();

            painelDetalhamento[i].add(LabelDetalhamento[i]);
            painelDetalhamento[i].add(CampoDetalhamento[i]);
            painelDetalhamento[i].add(LabelDetalhamento[i + 12]);

            layoutPainelDetalhamento[i] = new FlowLayout();
            layoutPainelDetalhamento[i].setAlignment(FlowLayout.CENTER);
            painelDetalhamento[i].setLayout(layoutPainelDetalhamento[i]);
            painelDetalhamento[i].setBackground(new java.awt.Color(231, 234, 240));
        }

        /**
         * **** ESTRUTURAÇÃO DOS PAINÉIS *****************************
         */
        // TÍTULOS DOS GRUPOS DE PAINÉIS
        LabelDetalhamento[24] = new JLabel("DETALHAMENTO");
        LabelDetalhamento[25] = new JLabel("Disposição da Armadura:");
        LabelDetalhamento[26] = new JLabel("Parâmetros de cálculo:");

        for (int i = 12; i <= 14; i++) // 3 PAINEIS 
        {
            painelDetalhamento[i] = new JPanel();
            painelDetalhamento[i].add(LabelDetalhamento[i + 12]); // 
            painelDetalhamento[i].setBackground(new java.awt.Color(231, 234, 240));
        }

        // Painel dos cálculos fornecidos
        LabelDetalhamento[28] = new JLabel("");
        painelDetalhamento[15] = new JPanel();
        painelDetalhamento[15].add(LabelDetalhamento[28]);
        painelDetalhamento[15].setBackground(new java.awt.Color(231, 234, 240));

        /**
         * ********************************************************************
         */
        //PAINEIS NÃO VISÍVEIS
        for (int i = 0; i <= 15; i++) //  4 painéis
        {
            painelDetalhamento[i].setVisible(true);
        }
        painelDetalhamento[7].setVisible(false);
        painelDetalhamento[8].setVisible(false); // False nas Prop. Geométricas 

        for (int i = 3; i <= 4; i++) {
            CampoDetalhamento[i].setEditable(false);
        }

        // MENU DE ESCOLHA DO TIPO DE DETALHAMENTO  DATA: 14/09/21 *********
        //******************************************************************
        // Inicializa objeto JComboBox, para o vetor nomesSeção
        menuDetalhamentoJComboBox = new JComboBox(nomesDetalhamento);
        menuDetalhamentoJComboBox.setMaximumRowCount(3); // Exibe 2 linhas (2 armaduras)

        JPanel painelDetalhamentoJComboBox = new JPanel();
        painelDetalhamentoJComboBox.setBackground(new java.awt.Color(231, 234, 240));
        // Coloca em um painel 
        painelDetalhamentoJComboBox.add(menuDetalhamentoJComboBox);

        menuDetalhamentoCamadasJComboBox = new JComboBox(nomesCamadasSimple);
        menuDetalhamentoCamadasJComboBox.setMaximumRowCount(5); // Exibe 5 linhas (4 seções)

        JPanel painelDetalhamentoCamadasJComboBox = new JPanel();
        painelDetalhamentoCamadasJComboBox.setBackground(new java.awt.Color(231, 234, 240));
        // Coloca em um painel 
        painelDetalhamentoCamadasJComboBox.add(menuDetalhamentoCamadasJComboBox);
        /**
         * ***************************************************************************
         */

        /**
         * **** ESPAÇADOR INVISÍVEL DO PAINEL DE DETALHAMENTO
         * *******************************
         */
        JTextField CampoDetalhamentoInv = new JTextField("", 25);
        CampoDetalhamentoInv.setBackground(new java.awt.Color(231, 234, 240));
        CampoDetalhamentoInv.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        CampoDetalhamentoInv.setEditable(false);

        JPanel painelDetalhamentoInv = new JPanel();

        painelDetalhamentoInv.setBackground(new java.awt.Color(231, 234, 240));
        painelDetalhamentoInv.add(CampoDetalhamentoInv);
        /**
         * ***************************************************************************
         */

        //****************** BOTÃO para Calcular as distâncias d' e d
        JButton BotãoCalcDist = new JButton("Calcular distâncias");
        JPanel painelDetalhamentoBotãoCalcDist = new JPanel();

        painelDetalhamentoBotãoCalcDist.add(BotãoCalcDist);
        painelDetalhamentoBotãoCalcDist.setVisible(false);
        painelDetalhamentoBotãoCalcDist.setBackground(new java.awt.Color(231, 234, 240));
        painelDetalhamentoBotãoCalcDist.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));

        painelDetalhamentoBotãoCalcDist.setLayout(new FlowLayout());

        /**
         * **** BOTÃO para Desenhar o DETALHAMENTO
         * ********************************************
         */
        JButton BotãoEspacamentoDetalhamento = new JButton("Verificação espaçamentos norma");
        JPanel painelDetalhamentoBotãoEspacamentoDetalhamento = new JPanel();

        painelDetalhamentoBotãoEspacamentoDetalhamento.add(BotãoEspacamentoDetalhamento);

        painelDetalhamentoBotãoEspacamentoDetalhamento.setVisible(false);
        painelDetalhamentoBotãoEspacamentoDetalhamento.setBackground(new java.awt.Color(231, 234, 240));
        painelDetalhamentoBotãoEspacamentoDetalhamento.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        painelDetalhamentoBotãoEspacamentoDetalhamento.setLayout(new FlowLayout());
        /**
         * ***************************************************************************
         */

        /**
         * **** PAINEL QUE APRESENTA AS IMAGENS DOS TIPOS DE
         * DETALHAMENTOS******************
         */
        labelMenuDetalhamento = new JLabel(iconsDetalhamento[0]);
        labelMenuDetalhamento.setBackground(new java.awt.Color(231, 234, 240));

        JPanel painelDetalhamentolabelMenuDetalhamento = new JPanel();
        painelDetalhamentolabelMenuDetalhamento.add(labelMenuDetalhamento);

        painelDetalhamentolabelMenuDetalhamento.setBackground(new java.awt.Color(231, 234, 240));
        //****************************************************************   

        /**
         * **** TRATAMENTO DE EVENTO: MENU DE ESCOLHA DAS SEÇÕES
         * *********************
         */
        for (int i = 0; i <= 15; i++) {
            painelDetalhamento[i].setVisible(false);
        }
        painelDetalhamento[12].setVisible(true);
        painelDetalhamento[13].setVisible(true);
        painelDetalhamentoBotãoEspacamentoDetalhamento.setVisible(false);
        painelDetalhamentoBotãoCalcDist.setVisible(false);
        menuDetalhamentoCamadasJComboBox.setVisible(false);
        menuDetalhamentoJComboBox.addItemListener(
                new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    labelMenuDetalhamento.setIcon(iconsDetalhamento[menuDetalhamentoJComboBox.getSelectedIndex()]);

                    switch (menuDetalhamentoJComboBox.getSelectedIndex()) {
                        case 0:
                            painelDetalhamentolabelMenuDetalhamento.setVisible(true);
                            // Definição do tipo de Detalhamento
                            nomeDetalhamentoInterface = "";
                            for (int i = 0; i <= 15; i++) {
                                painelDetalhamento[i].setVisible(false);
                            }
                            painelDetalhamento[12].setVisible(true);
                            painelDetalhamento[13].setVisible(true);
                            painelDetalhamentoBotãoEspacamentoDetalhamento.setVisible(false);

                            painelDetalhamentoBotãoCalcDist.setVisible(false);
                            menuDetalhamentoCamadasJComboBox.setVisible(false);

                            CampoDetalhamento[3].setText(String.format(""));
                            CampoDetalhamento[4].setText(String.format(""));

                            break;
                        case 1:
                            //menuDetalhamentoCamadasJComboBox = new JComboBox(nomesCamadasSimples);
                            //menuDetalhamentoCamadasJComboBox.removeAllItems();
                            //menuDetalhamentoCamadasJComboBox.addItem();
                            //painelDetalhamentoCamadasJComboBox.add(menuDetalhamentoCamadasJComboBox);

                            for (int i = 0; i <= 15; i++) {
                                if (i != 2) {
                                    painelDetalhamento[i].setVisible(true);
                                }
                            }

                            painelDetalhamentoBotãoEspacamentoDetalhamento.setVisible(true);
                            painelDetalhamentoBotãoCalcDist.setVisible(true);
                            menuDetalhamentoCamadasJComboBox.setVisible(true);

                            painelDetalhamento[2].setVisible(false);
                            painelDetalhamento[7].setVisible(false);
                            painelDetalhamento[8].setVisible(false);
                            painelDetalhamento[11].setVisible(false);
                            CampoDetalhamento[3].setEditable(true);
                            CampoDetalhamento[4].setEditable(true);
                            CampoDetalhamento[5].setEditable(false);
                            CampoDetalhamento[6].setEditable(false);
                            CampoDetalhamento[9].setEditable(false);
                            CampoDetalhamento[10].setEditable(false);

                            break;

                        case 2:
                            for (int i = 0; i <= 15; i++) {
                                painelDetalhamento[i].setVisible(true);
                            }
                            break;
                    }
                }
            }
        }
        );

        menuDetalhamentoCamadasJComboBox.addItemListener(
                new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                var itemSelecionado = menuDetalhamentoCamadasJComboBox.getSelectedIndex();
                System.out.println(menuDetalhamentoCamadasJComboBox.getSelectedIndex());
                CampoDetalhamento[5].setEditable(false);
                CampoDetalhamento[6].setEditable(false);
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    if (itemSelecionado == 1) {
                        painelDetalhamentoBotãoCalcDist.setVisible(true);
                        //CampoDetalhamento[5].setEditable(true);
                        painelDetalhamento[6].setVisible(false);
                    } else if (itemSelecionado == 2) {
                        //painelDetalhamento[5].setVisible(false);
                        painelDetalhamento[6].setVisible(true);
                    } else {
                        CampoDetalhamento[5].setEditable(false);
                        CampoDetalhamento[6].setEditable(false);
                        painelDetalhamento[5].setVisible(true);
                        painelDetalhamento[6].setVisible(true);
                    }
                }
            }
        });

        /**
         * **** PAINEL PRINCIPAL DE PROPRIEDADES DO DETALHAMENTO
         * **************************
         */
        JPanel painelBoxDetalhamento = new JPanel();
        painelBoxDetalhamento.setBackground(new java.awt.Color(231, 234, 240));
        /**
         * ***************************************************************************
         */

        /**
         * **** ESTRUTURA VERTICAL DO PAINEL PRINCIPAL DE PROPRIEDADES DO
         * DETALHAMENTO ****
         */
        Box verticalDetalhamento = Box.createVerticalBox();
        verticalDetalhamento.add(Box.createVerticalStrut(5));
        verticalDetalhamento.add(painelDetalhamentoInv);
        verticalDetalhamento.add(painelDetalhamento[12]);
        verticalDetalhamento.add(painelDetalhamento[13]);
        verticalDetalhamento.add(painelDetalhamentoJComboBox);
        verticalDetalhamento.add(painelDetalhamentolabelMenuDetalhamento);
        verticalDetalhamento.add(painelDetalhamento[0]);
        verticalDetalhamento.add(painelDetalhamento[1]);
        verticalDetalhamento.add(painelDetalhamento[2]);
        verticalDetalhamento.add(painelDetalhamento[3]);
        verticalDetalhamento.add(painelDetalhamento[4]);
        verticalDetalhamento.add(painelDetalhamentoCamadasJComboBox);
        verticalDetalhamento.add(painelDetalhamentoBotãoEspacamentoDetalhamento);
        verticalDetalhamento.add(painelDetalhamento[5]);
        verticalDetalhamento.add(painelDetalhamento[6]);
        verticalDetalhamento.add(painelDetalhamento[7]);
        verticalDetalhamento.add(painelDetalhamento[8]);
        verticalDetalhamento.add(painelDetalhamentoBotãoCalcDist);
        verticalDetalhamento.add(painelDetalhamento[9]);

        verticalDetalhamento.add(painelDetalhamento[10]);

        for (int i = 3; i <= 4; i++) {
            verticalDetalhamento.add(painelDetalhamento[11]);
        }
        painelBoxDetalhamento.add(verticalDetalhamento);

        verticalDetalhamento.setMaximumSize(new Dimension(298, 800));
        verticalDetalhamento.setBackground(new java.awt.Color(231, 234, 240));
        DrawingPanel painelDetalhamentoScroll = new DrawingPanel(verticalDetalhamento, 800, 268);
        painelDetalhamentoScroll.setBackground(new java.awt.Color(231, 234, 240));

        final JScrollPane scrollDetalhamento = new JScrollPane(painelDetalhamentoScroll);
        painelDetalhamentoScroll.setBackground(new java.awt.Color(231, 234, 240));
        painelDetalhamentoScroll.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        //painelDetalhamentoScroll.setBorder(null);
        painelDetalhamentoScroll.setVisible(true);
        painelBoxDetalhamento.add(scrollDetalhamento);
        /**
         * ***************************************************************************
         */

        /**
         * **** ADIÇÃO DO PAINEL PRINCIPAL DE PROPRIEDADES DO DETALHAMENTO À
         * DIREITA ********
         */
        add(painelBoxDetalhamento, BorderLayout.EAST);
        /**
         * ***************************************************************************
         */

        //*************************** BOX ELU – FLEXÃO **************************
// ******************** PAINEL PROPRIEDADES DA FLEXÃO ***************
        JLabel LabelFlexão[] = new JLabel[8]; //Vetor de Label

        // Painéis com dados que serão calculados e fornecidos (não editáveis)
        LabelFlexão[0] = new JLabel("µsd = ");
        LabelFlexão[1] = new JLabel("Domínio = ");
        LabelFlexão[2] = new JLabel("As = ");
        LabelFlexão[3] = new JLabel("cm2");

        JTextField CampoFlexão[] = new JTextField[3];

        JPanel painelFlexão[] = new JPanel[8];
        FlowLayout layoutPainelFlexão[] = new FlowLayout[8];

        for (int i = 0; i <= 2; i++) {
            CampoFlexão[i] = new JTextField("", 8);
            painelFlexão[i] = new JPanel();

            painelFlexão[i].add(LabelFlexão[i]);
            painelFlexão[i].add(CampoFlexão[i]);

            layoutPainelFlexão[i] = new FlowLayout();
            layoutPainelFlexão[i].setAlignment(FlowLayout.CENTER);
            painelFlexão[i].setLayout(layoutPainelFlexão[i]);
            painelFlexão[i].setBackground(new java.awt.Color(231, 234, 240));

        }

        painelFlexão[2].add(LabelFlexão[3]);

        // ************* ESTRUTURAÇÃO DOS PAINÉIS ********************
        // TÍTULOS DOS GRUPOS DE PAINÉIS
        LabelFlexão[4] = new JLabel("ELU – FLEXÃO");
        LabelFlexão[5] = new JLabel("");

        for (int i = 4; i <= 5; i++) {
            painelFlexão[i] = new JPanel();
            painelFlexão[i].add(LabelFlexão[i]);
            painelFlexão[i].setBackground(new java.awt.Color(231, 234, 240));
        }

//         Painel dos dados que serão calculados - não editáveis
        LabelFlexão[6] = new JLabel("      ");
        LabelFlexão[7] = new JLabel("                 ");
        painelFlexão[0].add(LabelFlexão[6]);
        painelFlexão[1].add(LabelFlexão[7]);
//        painelFlexão[3] = new JPanel();
//        painelFlexão[3].add(LabelFlexão[6]);
//        painelFlexão[3].setBackground(new java.awt.Color(231, 234, 240));

        // PAINÉIS VISÍVEIS
        for (int i = 0; i <= 2; i++) {
            painelFlexão[i].setVisible(true);
        }

        painelFlexão[4].setVisible(true);
        painelFlexão[5].setVisible(true); // False nas Propriedades do material 

        for (int i = 0; i <= 2; i++) {
            CampoFlexão[i].setEditable(false);
        }

        /**
         * **** ESPAÇADOR INVISÍVEL DO PAINEL DE MATERIAL
         * *******************************
         */
        JTextField CampoFlexãoInv = new JTextField("", 25);
        CampoFlexãoInv.setBackground(new java.awt.Color(231, 234, 240));
        CampoFlexãoInv.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        CampoFlexãoInv.setEditable(false);

        JPanel painelFlexãoInv = new JPanel();

        painelFlexãoInv.setBackground(new java.awt.Color(231, 234, 240));
        painelFlexãoInv.add(CampoFlexãoInv);
        /**
         * ***************************************************************************
         */

        /**
         * **** BOTÃO para Calcular o Domínio de Funcionamento
         * ***************************************************
         */
        JButton BotãoFlexãomiSd = new JButton("Calcular µsd");
        JPanel painelFlexãoBotãoFlexãomiSd = new JPanel();

        painelFlexãoBotãoFlexãomiSd.add(BotãoFlexãomiSd);

        painelFlexãoBotãoFlexãomiSd = this.adicionarLayoutBotao(painelFlexãoBotãoFlexãomiSd);
//        painelFlexãoBotãoFlexãomiSd.setVisible(true);
//        painelFlexãoBotãoFlexãomiSd.setBackground(new java.awt.Color(231, 234, 240));
//        painelFlexãoBotãoFlexãomiSd.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
//        painelFlexãoBotãoFlexãomiSd.setLayout(new FlowLayout());
        /**
         * ***************************************************************************
         */

        /**
         * **** BOTÃO para Calcular o Domínio de Funcionamento
         * ***************************************************
         */
        JButton BotãoCalcDomínioFlexão = new JButton("Domínio de funcionamento da seção");
        JPanel painelFlexãoBotãoCalcDomínioFlexão = new JPanel();

        painelFlexãoBotãoCalcDomínioFlexão.add(BotãoCalcDomínioFlexão);

        painelFlexãoBotãoCalcDomínioFlexão.setVisible(true);
        painelFlexãoBotãoCalcDomínioFlexão.setBackground(new java.awt.Color(231, 234, 240));
        painelFlexãoBotãoCalcDomínioFlexão.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        painelFlexãoBotãoCalcDomínioFlexão.setLayout(new FlowLayout());
        /**
         * ***************************************************************************
         */

        /**
         * **** BOTÃO para Calcular Armadura de Flexão
         * ***************************************************
         */
        JButton BotãoCalcArmaduraFlexão = new JButton("Calcular a armadura");
        JPanel painelFlexãoBotãoCalcArmaduraFlexão = new JPanel();

        painelFlexãoBotãoCalcArmaduraFlexão.add(BotãoCalcArmaduraFlexão);

        painelFlexãoBotãoCalcArmaduraFlexão = this.adicionarLayoutBotao(painelFlexãoBotãoCalcArmaduraFlexão);
        /**
         * ***************************************************************************
         */

//*********** PAINEL PRINCIPAL DE PROPRIEDADES DO MATERIAL *******
        JPanel painelBoxFlexão = new JPanel();
        painelBoxFlexão.setBackground(new java.awt.Color(231, 234, 240));
//****************************************************************

        /**
         * **** ESTRUTURA VERTICAL DO PAINEL PRINCIPAL DE PROPRIEDADES DAS
         * SEÇÕES ****
         */
        Box verticalFlexão = Box.createVerticalBox();

        verticalFlexão.add(painelFlexãoInv);// Espaçador para padronizar tamanho        
        verticalFlexão.add(painelFlexão[4]);// Material

        //verticalMaterial.add(painelMateriallabelMenuMaterial); // Label Figuras das seções                        
        verticalFlexão.add(painelFlexão[5]);//Dimensões das Seções        
        verticalFlexão.add(painelFlexãoBotãoFlexãomiSd);
        verticalFlexão.add(painelFlexão[0]);
        verticalFlexão.add(painelFlexãoBotãoCalcDomínioFlexão); // Botão = Calcula as propriedades do material   
        verticalFlexão.add(painelFlexão[1]);
        verticalFlexão.add(painelFlexãoBotãoCalcArmaduraFlexão); // Botão = Calcula as propriedades do material 
        verticalFlexão.add(painelFlexão[2]);
//        verticalFlexão.add(painelFlexão[3]); // Propridades do material

//        for (int i = 0; i <= 2; i++) {
//            verticalFlexão.add(painelFlexão[i]); // Propriedades do material      
//        }
        painelBoxFlexão.add(verticalFlexão);
        /**
         * ***************************************************************************
         */

        /**
         * **** ADIÇÃO DO PAINEL PRINCIPAL DE PROPRIEDADES DAS SEÇÕES A DIREITA
         * ********
         */
        add(painelBoxFlexão, BorderLayout.EAST);
        /**
         * ***************************************************************************
         */

        //******************* FIM DO painelBoxFlexão na direita (east)
        //*********************************** KATRI DATA: 18.08.2021 ***********************************
        ////---->>>>> Adiciona AÇÃO DE EVENTOS aos botões do TOPO (NORTH)       
        for (int i = 0; i < botão.length; i++) {
            botão[i].addActionListener(
                    new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (event.getSource() == botão[0]) // Seção
                    {
                        painelBoxMaterial.setVisible(false);
                        add(painelBoxSeção, BorderLayout.EAST);
                        painelBoxSeção.setVisible(true);
                        painelBoxCarregameto.setVisible(false);
                        painelBoxDetalhamento.setVisible(false);
                        painelBoxFlexão.setVisible(false);
                        //**************************************************************
                        // Ação de evento das caixas de texto (campo de texto)
                        for (int i = 0; i < CampoSeção.length; i++) {
                            CampoSeção[i].addActionListener(
                                    new ActionListener() {
                                public void actionPerformed(ActionEvent event) {
                                    if (event.getSource() == CampoSeção[0]) // bw
                                    {  // Está na classe Dados
                                        SEÇÃO[0] = Double.parseDouble(event.getActionCommand());
                                        dados.setbw(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoSeção[1]) // h
                                    {
                                        SEÇÃO[1] = Double.parseDouble(event.getActionCommand());
                                        dados.seth(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoSeção[2]) // bf
                                    {
                                        SEÇÃO[2] = Double.parseDouble(event.getActionCommand());
                                        dados.setbf(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoSeção[3]) //hf
                                    {
                                        SEÇÃO[3] = Double.parseDouble(event.getActionCommand());
                                        dados.sethf(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoSeção[4]) // bfs
                                    {
                                        SEÇÃO[4] = Double.parseDouble(event.getActionCommand());
                                        dados.setbfs(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoSeção[5]) // hfs
                                    {
                                        SEÇÃO[5] = Double.parseDouble(event.getActionCommand());
                                        dados.sethfs(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoSeção[6]) // bfi
                                    {
                                        SEÇÃO[6] = Double.parseDouble(event.getActionCommand());
                                        dados.setbfi(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoSeção[7]) // hfi
                                    {
                                        SEÇÃO[7] = Double.parseDouble(event.getActionCommand());
                                        dados.sethfi(Double.parseDouble(event.getActionCommand()));
                                    }
                                    //********************************************************
                                } // Fim do método actionPerformed
                            } // Fim da classe interna anônima
                            ); // Fim do ActionListener CampoSeção[i]
                        } // Fim do for
                    }// Fim if dos eventos do botão[0] // Seção
                    //******************************************************************

                    //******************************************************************
                    if (event.getSource() == botão[1]) // Material
                    {
                        //painelBoxSeção.setVisible(false);
                        painelBoxMaterial.setVisible(true);
                        add(painelBoxMaterial, BorderLayout.EAST);
                        painelBoxSeção.setVisible(false);
                        painelBoxCarregameto.setVisible(false);
                        painelBoxDetalhamento.setVisible(false);
                        painelBoxFlexão.setVisible(false);
                        //**************************************************************
                        // Ação de evento das caixas de texto (campo de texto)
                        for (int i = 0; i < CampoMaterial.length; i++) {
                            CampoMaterial[i].addActionListener(
                                    new ActionListener() {
                                public void actionPerformed(ActionEvent event) {
                                    if (event.getSource() == CampoMaterial[0]) {
                                        //bDados.getMaterial()[0] = Double.parseDouble(event.getActionCommand());
                                        bDados.setfck(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoMaterial[1]) {
                                        //bDados.getMaterial()[2] = Double.parseDouble(event.getActionCommand());

                                        bDados.setfyk(Double.parseDouble(event.getActionCommand()));
                                    }
                                }
                            }
                            ); // Fim do ActionListener
                        } // Fim do for das caixas de texto
                        //************************************************************
                    }// Fim dos eventos do botão[1] // Material
                    //******************************************************************

                    if (event.getSource() == botão[2]) // Detalhamento
                    {
                        painelBoxMaterial.setVisible(false);
                        painelBoxSeção.setVisible(false);
                        painelBoxCarregameto.setVisible(false);
                        add(painelBoxDetalhamento, BorderLayout.EAST);
                        painelBoxDetalhamento.setVisible(true);
                        painelBoxFlexão.setVisible(false);
                        //**************************************************************
                        // Ação de evento das caixas de texto (campo de texto)
                        for (int i = 0; i < CampoDetalhamento.length; i++) {
                            CampoDetalhamento[i].addActionListener(
                                    new ActionListener() {
                                public void actionPerformed(ActionEvent event) {
                                    if (event.getSource() == CampoDetalhamento[0]) // c nom
                                    {  // Está na classe Dados
                                        dados.setcNom(Double.parseDouble(event.getActionCommand()));
                                    }

                                    if (event.getSource() == CampoDetalhamento[1]) // ϕt
                                    {
                                        dados.setfiT(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoDetalhamento[2]) // ϕ
                                    {
                                        dados.setfi(Double.parseDouble(event.getActionCommand()));
                                    }
                                    //********************************************************
                                } // Fim do método actionPerformed
                            } // Fim da classe interna anônima
                            ); // Fim do ActionListener CampoDetalhamento[i]
                        } // Fim do for
                    }// Fim if dos eventos do botão[4] // Detalhamento

                    //************************************************************************
                    if (event.getSource() == botão[3]) // Solicitação
                    {
                        painelBoxSeção.setVisible(false);
                        painelBoxMaterial.setVisible(false);
                        add(painelBoxCarregameto, BorderLayout.EAST);
                        painelBoxCarregameto.setVisible(true);
                        painelBoxDetalhamento.setVisible(false);
                        painelBoxFlexão.setVisible(false);
                        //**************************************************************
                        // Ação de evento das caixas de texto (campo de texto)
                        for (int i = 0; i < CampoCarregamento.length; i++) {
                            CampoCarregamento[i].addActionListener(
                                    new ActionListener() {
                                public void actionPerformed(ActionEvent event) {
//                                    if (event.getSource() == CampoMaterial[0]) {
//                                        //bDados.getMaterial()[0] = Double.parseDouble(event.getActionCommand());
//                                        bDados.setfck(Double.parseDouble(event.getActionCommand()));
//                                    }
//                                    if (event.getSource() == CampoMaterial[1]) {
//                                        //bDados.getMaterial()[2] = Double.parseDouble(event.getActionCommand());
//
//                                        bDados.setfyk(Double.parseDouble(event.getActionCommand()));
//                                    }
                                }
                            }
                            ); // Fim do ActionListener
                        } // Fim do for das caixas de texto
                        //************************************************************
                    }// Fim dos eventos do botão[2] // Solicitação
                    //***********************************************************

                    if (event.getSource() == botão[4]) // ELU - FLEXÃO
                    {
                        painelBoxMaterial.setVisible(false);
                        painelBoxSeção.setVisible(false);
                        painelBoxCarregameto.setVisible(false);
                        painelBoxDetalhamento.setVisible(false);
                        painelBoxFlexão.setVisible(true);
                        add(painelBoxFlexão, BorderLayout.EAST);
                        //**************************************************************
                        // Ação de evento das caixas de texto (campo de texto)
                        for (int i = 0; i < CampoFlexão.length; i++) {
                            CampoFlexão[i].addActionListener(
                                    new ActionListener() {
                                public void actionPerformed(ActionEvent event) {
                                    if (event.getSource() == CampoFlexão[0]) // c nom
                                    {  // Está na classe Dados
                                        dados.setcNom(Double.parseDouble(event.getActionCommand()));
                                    }

                                    if (event.getSource() == CampoDetalhamento[1]) // ϕt
                                    {
                                        dados.setfiT(Double.parseDouble(event.getActionCommand()));
                                    }
                                    if (event.getSource() == CampoDetalhamento[2]) // ϕ
                                    {
                                        dados.setfi(Double.parseDouble(event.getActionCommand()));
                                    }
                                    //********************************************************
                                } // Fim do método actionPerformed
                            } // Fim da classe interna anônima
                            ); // Fim do ActionListener CampoDetalhamento[i]
                        } // Fim do for
                    }// Fim if dos eventos do botão[4] // ELU – FLEXÃO

                }
            }
            );
        }
//*************************************************************

//*****************   BOTÃO DESENHAR A SEÇÃO  *******************************
// Colocar um botão para desenhar a seção 
        BotãoDesSeção.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //****************************************************************
                if (nomeSeçãoInterface.equals("SEÇÃO RETANGULAR")) {
                    if ((SEÇÃO[0] != 0.0) && (SEÇÃO[1] != 0.0)) {
                        dados.setNomeSeção(nomeSeçãoInterface);// Na classe DADOS
                        dados.setnSec(); // Na classe PropGeomGAUSS
                        dados.setPoligonalGAUSS();// Na classe PropGeomGAUSS
                        dados.setPropGeométricas();// Na classe PropGeomGAUSS
                        dados.setPoligonalCG();// Na classe PropGeomGAUSS

                        desenhar.DesenharPoligonal();   // Na classe Desenho
                        desenhar.DesenharDeformação();
                        desenhar.DesenharBarrasAço();
                    }
                }
                //****************************************************************
                if (nomeSeçãoInterface.equals("SEÇÃO T")) {
                    if ((SEÇÃO[0] != 0.0) && (SEÇÃO[1] != 0.0) && (SEÇÃO[2] != 0.0) && (SEÇÃO[3] != 0.0)) {
                        dados.setNomeSeção(nomeSeçãoInterface);
                        dados.setnSec();
                        dados.setPoligonalGAUSS();
                        dados.setPropGeométricas();
                        dados.setPoligonalCG();
                        // desenhar.DesenharPoligonal();
                    } // Fim do if para desenhar
                }
                //****************************************************************
                if (nomeSeçãoInterface.equals("SEÇÃO I")) {
                    if ((SEÇÃO[0] != 0.0) && (SEÇÃO[1] != 0.0)
                            && (SEÇÃO[4] != 0.0) && (SEÇÃO[5] != 0.0 && (SEÇÃO[6] != 0.0) && (SEÇÃO[7] != 0.0))) {
                        dados.setNomeSeção(nomeSeçãoInterface);
                        dados.setnSec();
                        dados.setPoligonalGAUSS();
                        dados.setPropGeométricas();
                        dados.setPoligonalCG();
                        // desenhar.DesenharPoligonal();
                    } // Fim do if para desenhar
                }
                //****************************************************************
                if (nomeSeçãoInterface.equals("SEÇÃO L")) {
                    if ((SEÇÃO[0] != 0.0) && (SEÇÃO[1] != 0.0) && (SEÇÃO[2] != 0.0) && (SEÇÃO[3] != 0.0)) {
                        dados.setNomeSeção(nomeSeçãoInterface);
                        dados.setnSec();
                        dados.setPoligonalGAUSS();
                        dados.setPropGeométricas();
                        dados.setPoligonalCG();
                        // desenhar.DesenharPoligonal();
                    } // Fim do if para desenhar
                }
                //***************************************************************
            }// Fim do actionPerformed
        }// Fim do ActionListener
        );// Fim do addActionListener
//**************************************************************************************

        /**
         * **** TRATAMENTO DE EVENTO: BOTÃO PARA CALCULAR PROPRIEDADES
         * GEOMÉTRICAS**********
         */
        FlagSeção = 0; // ?????????????????????????????????

        BotãoPropGeom.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dados.setbw(Double.parseDouble(CampoSeção[0].getText()));
                SEÇÃO[0] = Double.parseDouble(CampoSeção[0].getText());
                dados.seth(Double.parseDouble(CampoSeção[1].getText()));
                SEÇÃO[1] = Double.parseDouble(CampoSeção[1].getText());
                
                
                dados.setbf(Double.parseDouble(CampoSeção[2].getText()));
                SEÇÃO[2] = Double.parseDouble(CampoSeção[2].getText());
                dados.sethf(Double.parseDouble(CampoSeção[3].getText()));
                SEÇÃO[3] = Double.parseDouble(CampoSeção[3].getText());
                if (dados != null) {
                    // Calcula as propriedades geométricas
                    // Está na classe de PropGeomGAUSS
                    dados.setNomeSeção(nomeSeçãoInterface);
                    dados.setnSec();
                    dados.setPoligonalGAUSS();
                    dados.setPropGeométricas();
                    double[] PropSeção = dados.getPropGeom();
                    // COLOCAR NAS aussCAIXAS DE TEXTO - PROPRIEDADES GEOMÉTRICAS
                    CampoSeção[8].setText(String.format("%.2f", PropSeção[0]));
                    CampoSeção[9].setText(String.format("%.2f", PropSeção[1]));
                    CampoSeção[10].setText(String.format("%.2f", PropSeção[2]));
                    CampoSeção[11].setText(String.format("%.2f", PropSeção[3]));
                    CampoSeção[12].setText(String.format("%.2f", PropSeção[4]));
                    CampoSeção[13].setText(String.format("%.2f", PropSeção[5]));

                }

            }
        }
        );
        //*********************************************************************/

        BotãoPropMaterial.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (bDados != null) {
                    bDados.setfck(Double.parseDouble(CampoMaterial[0].getText()));
                    bDados.setfyk(Double.parseDouble(CampoMaterial[1].getText()));

                    CampoMaterial[2].setText(String.format("%.3f", bDados.getMaterial()[1]));
                    CampoMaterial[3].setText(String.format("%.3f", bDados.getMaterial()[3]));
                }
            }
        }
        );

        BotãoCalcDist.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (detalhamento != null) {
                    if (menuDetalhamentoCamadasJComboBox.getSelectedIndex() == 1) {

                        detalhamento.calculaDLinha(
                                Double.parseDouble(CampoDetalhamento[0].getText()),
                                Double.parseDouble(CampoDetalhamento[1].getText()),
                                Double.parseDouble(CampoDetalhamento[2].getText())
                        );

                    } else if (menuDetalhamentoCamadasJComboBox.getSelectedIndex() == 2) {
                        detalhamento.calculaDLinha2Camadas(
                                Double.parseDouble(CampoDetalhamento[0].getText()),
                                Double.parseDouble(CampoDetalhamento[1].getText()),
                                Double.parseDouble(CampoDetalhamento[2].getText())
                        );
                    }

                    CampoDetalhamento[9].setText(String.format("%.3f", detalhamento.dLinha));

                    detalhamento.calculaD(Double.parseDouble(CampoSeção[1].getText()));

                    CampoDetalhamento[10].setText(String.format("%.3f", detalhamento.d));

                }
            }
        }
        );
        BotãoEspacamentoDetalhamento.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (menuDetalhamentoCamadasJComboBox.getSelectedIndex() == 1) {
                    Double aH = detalhamento.definirAH(
                            Double.parseDouble(CampoDetalhamento[3].getText()),
                            Double.parseDouble(CampoDetalhamento[4].getText())
                    );
                    CampoDetalhamento[5].setText(String.format("%.3f", aH));
                } else if (menuDetalhamentoCamadasJComboBox.getSelectedIndex() == 2) {
                    Double aH = detalhamento.definirAH(
                            Double.parseDouble(CampoDetalhamento[3].getText()),
                            Double.parseDouble(CampoDetalhamento[4].getText())
                    );
                    CampoDetalhamento[5].setText(String.format("%.3f", aH));

                    Double aV = detalhamento.definirAV(
                            Double.parseDouble(CampoDetalhamento[3].getText()),
                            Double.parseDouble(CampoDetalhamento[4].getText())
                    );
                    CampoDetalhamento[6].setText(String.format("%.3f", aV));
                }
            }
        }
        );

        BotãoCalcExcentricidade.addActionListener(
                new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (carregamento != null && menuCarregamentoFlexãoJComboBox.getSelectedIndex() == 2) {
                    //Calculo exentricidade
                    carregamento.calculaExentricidade(
                            Double.parseDouble(CampoCarregamento[0].getText()),
                            Double.parseDouble(CampoCarregamento[1].getText())
                    );
                    carregamento.calcularX(SEÇÃO[1], detalhamento.dLinha);
                    CampoCarregamento[4].setText(String.format("%.6f", carregamento.e));
                    CampoCarregamento[3].setText(String.format("%.6f", carregamento.x));
                    if (menuCarregamentoFlexãoCompostaJComboBox.getSelectedIndex() == 1) {

                        //Calculo microSD
                        Double microSd = carregamento.calculaMicroSD(
                                carregamento.calculaExentricidadeTracao(carregamento.e, carregamento.x), //md
                                SEÇÃO[0],
                                detalhamento.d,
                                Double.parseDouble(CampoMaterial[2].getText().replace(",", ".")));
                        CampoCarregamento[5].setText(String.format("%.6f", carregamento.es));
                        String resutadoFinal = String.format(microSd.toString());
                        CampoCarregamento[6].setText(String.format("%.6f", microSd));
                    } else {
                        //Calculo microSD
                        Double microSd = carregamento.calculaMicroSD(
                                carregamento.calculaExentricidadeCompressao(carregamento.e, carregamento.x), //md
                                SEÇÃO[0],
                                detalhamento.d,
                                Double.parseDouble(CampoMaterial[2].getText().replace(",", ".")));

                        String resutadoFinal = String.format(microSd.toString());
                        CampoCarregamento[5].setText(String.format("%.6f", carregamento.es));
                        CampoCarregamento[6].setText(String.format("%.6f", microSd));

                    }

                } else if (menuCarregamentoFlexãoJComboBox.getSelectedIndex() == 1) {
                    Double microSd = carregamento.calculaMicroSD(
                            Double.parseDouble(CampoCarregamento[2].getText()), //msd
                            SEÇÃO[0],
                            detalhamento.d,
                            Double.parseDouble(CampoMaterial[2].getText().replace(",", ".")));

                    /*System.out.println(CampoSolicitação[3].getText());
                            System.out.println(SEÇÃO[0]);
                            System.out.println(detalhamento.d);
                            System.out.println(Double.parseDouble(CampoMaterial[2].getText()));
                            System.out.println(carregamento.microSD);*/
                    String resutadoFinal = String.format(microSd.toString());
                    CampoCarregamento[6].setText(String.format("%.3f", microSd));
                }
            }
        }
        );

    }// Fim do 1* método CONSTRUTOR
//*********************************************************************/   
//*********************************************************************/ 

    //*********************************************************************/
    //  2* MÉTODO AUXILIAR retorna o valor da variavel "file" do Menu Novo 
    //  ou Abrir.   Arquivo "file" já existente no diretorio do computador
    //*********************************************************************/
    private File getFileOrDirectory() {
        // Exibe o diálogo de arquivo para o usuário escolher o arquivo para abrir
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showSaveDialog(this);

        // Se o usuário clicou no botão CANCEL na caixa de diálogo 
        if (result == JFileChooser.CANCEL_OPTION) {
            System.exit(1);
        }
        // Obtem o arquivo selecionado 
        File fileName = fileChooser.getSelectedFile();
        // Exibe erro se inválido
        if ((fileName == null) || (fileName.getName().equals(""))) {
            JOptionPane.showMessageDialog(this, "Invalid File Name",
                    "Invalid File Name", JOptionPane.ERROR_MESSAGE);
            System.exit(1);// Sai e fecha o programa
        }
        return fileName;
    }// Fim do método getFileOrDirectory

    private JPanel adicionarLayoutBotao(JPanel painelBotao) {
        painelBotao.setVisible(true);
        painelBotao.setBackground(new java.awt.Color(231, 234, 240));
        painelBotao.setBorder(new LineBorder(new java.awt.Color(231, 234, 240)));
        painelBotao.setLayout(new FlowLayout());
        return painelBotao;
    }
    /**
     * **********************************************************************
     */

}// Fim da classe INTERFACE

