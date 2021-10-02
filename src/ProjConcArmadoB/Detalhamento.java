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
    public Double calculaD(Double h){
        this.d = h - this.dLinha;
        return d;
    }
    public Double calculaD(Double h, Double dLinha){
        this.d = h - dLinha;
        return d;
    }  
    
}
