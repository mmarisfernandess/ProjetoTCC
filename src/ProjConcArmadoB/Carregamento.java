
package ProjConcArmadoB;

/**
 *
 * @author mmari
 */
public class Carregamento {
    Double md;
    Double nd;
    Double e;
    Double microSD;
    
    public void calculaExentricidade(Double md, Double nd){
        this.md = md;
        this.nd = nd;
        e = md/nd;
    }
    
    public Double calculaMicroSD(Double msd, Double bw, Double d, Double fcd){
        // Calcular o parâmetro miSd = Msd/bw*d2*fcd = Solicitação[1] = mid
        this.microSD = msd*1000/(bw*(d*d)*fcd);
        return microSD;
    }
}
