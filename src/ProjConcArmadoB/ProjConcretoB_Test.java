package ProjConcArmadoB;

// TCC - Mariana Fernandes
// 2a versão - VERSÃO GRÁFICA

// 1a classe - ProjConcretoB_Test - inicia o método MAIN
// Importa a classe JFrame (janela) da biblioteca javax.swing
import javax.swing.JFrame;

public class ProjConcretoB_Test
{
    public static void main(String args[]) 
    {
        // Cria o objeto "janela" da classe ProjConcretoB_Interface
        ProjConcretoB_Interface janela = new ProjConcretoB_Interface();
        
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(1920,1045); // Configura o tamanho da janela
        janela.setVisible(true); // Torna a janela visível
    }
}
