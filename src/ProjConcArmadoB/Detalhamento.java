/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjConcArmadoB;

/**
 *
 * @author mmari
 */
public class Detalhamento {
    Double cobrimento;
    Double diametroEstribo;
    Double diametroBarra;
    Double dLinha;
    Double d;
    
    public Double calculaDLinha(Double cobrimento, Double diametroEstribo, Double diametroBarra){
        this.dLinha = cobrimento + diametroEstribo + (diametroBarra/2);
        return dLinha;
    }
    public Double calculaDLinha2Camadas(Double cobrimento, Double diametroEstribo, Double diametroBarra){
        this.dLinha = cobrimento + diametroEstribo + diametroBarra;
        return dLinha;
    }
    public Double calculaD(Double h){
        Long d = Math.round(h - this.dLinha);
        this.d = Double.parseDouble(d.toString());
        return this.d;
    }
    public Double calculaD(Double h, Double dLinha){
        this.d = h - dLinha;
        return d;
    }  
    
    public Double definirAH(Double fiTracao, Double fiAgregado){
        fiAgregado = 1.2 * fiAgregado;
        Double doisCentimentros = 2.0;
        if(doisCentimentros > fiAgregado && doisCentimentros > fiTracao){
            return doisCentimentros;
        }else if( fiAgregado > doisCentimentros &&  fiAgregado > fiTracao){
            return fiAgregado;
        }else{
            return fiTracao;
        }
    }
    public Double definirAV(Double fiTracao, Double fiAgregado){
        fiAgregado = 0.5 * fiAgregado;
        Double doisCentimentros = 2.0;
        if(doisCentimentros > fiAgregado && doisCentimentros > fiTracao){
            return doisCentimentros;
        }else if( fiAgregado > doisCentimentros &&  fiAgregado > fiTracao){
            return fiAgregado;
        }else{
            return fiTracao;
        }
    }
    
}
