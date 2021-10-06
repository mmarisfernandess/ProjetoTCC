package ProjConcArmadoB;

/**
 *
 * @author mmari
 */
public class Carregamento {

    Double md;
    Double nd;
    Double x;
    Double e;
    Double es;
    Double microSD;
    Double msdComposto;

    public void calcularX(Double h, Double dLinha) {
        h = limitaCasasDecimais(3, h);
        dLinha = limitaCasasDecimais(3, dLinha);

        this.x = (h / 2) - dLinha;
        this.x = limitaCasasDecimais(3, this.x);

    }

    public void calculaExentricidade(Double md, Double nd) {
        md = limitaCasasDecimais(3, md);
        nd = limitaCasasDecimais(3, nd);

        this.md = md;
        this.md = limitaCasasDecimais(3, this.md);
        this.nd = nd;
        this.nd = limitaCasasDecimais(3, this.nd);
        this.e = md / nd;
        this.e = limitaCasasDecimais(3, this.e);
    }

    public Double calculaExentricidadeTracao(Double e, Double x) {
        e = limitaCasasDecimais(3, e);
        x = limitaCasasDecimais(3, x);
        if (e != null && x != null) {
            this.es = e - x;
        }
        this.es = limitaCasasDecimais(3, this.es);

        return calculaMomemtoComposto();
    }

    public Double calculaExentricidadeCompressao(Double e, Double x) {
        e = limitaCasasDecimais(3, e);
        x = limitaCasasDecimais(3, x);
        if (e != null && x != null) {
            this.es = e + x;
        }
        this.es = limitaCasasDecimais(3, this.es);
        return calculaMomemtoComposto();
    }

    public Double calculaMomemtoComposto() {
        this.msdComposto = nd * es;
        this.msdComposto = limitaCasasDecimais(3, this.msdComposto);
        return this.msdComposto;
    }

    public Double calculaMicroSD(Double momento, Double bw, Double d, Double fcd) {
        // Calcular o parâmetro miSd = Msd/bw*d2*fcd = Solicitação[1] = mid
        momento = limitaCasasDecimais(3, momento);
        bw = limitaCasasDecimais(3, bw);
        d = limitaCasasDecimais(3, d);
        fcd = limitaCasasDecimais(3, fcd);

        this.microSD = momento * 1000 / (bw * (d * d) * fcd);
        this.microSD = limitaCasasDecimais(3, this.microSD);
        return microSD;
    }

    public Double limitaCasasDecimais(Integer n, Double valor) {
        Double auxValor = 10.0;
        valor = (double) (Math.round(valor * Math.pow(auxValor, n)) / 1000.0);
        return valor;
    }
}
