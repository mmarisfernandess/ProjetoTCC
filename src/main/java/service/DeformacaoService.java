/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.service;

import java.util.ArrayList;
import java.util.List;
import main.java.model.Deformacao;

/**
 *
 * @author mmari
 */
public class DeformacaoService {

    List<Deformacao> tabelaUniversal;

    public DeformacaoService() {
        this.tabelaUniversal = new ArrayList();
        this.tabelaUniversal = setTabelaUniversal();
    }

    public Deformacao convertDadosBrutosParaDeformacao(Integer id, double concreto, double aco, double alfa, double ksi, double micro_sd, double zeta, double ksi_linha) {
        Deformacao d = new Deformacao(id, concreto, aco, alfa, ksi, micro_sd, zeta, ksi_linha);
        return d;
    }

    public List<Deformacao> getTabelaUniversal() {
        return tabelaUniversal;
    }
    
    
    
    
    
    
    
    
    

    public List<Deformacao> setTabelaUniversal() {
        List<Deformacao> tabela = new ArrayList();
        //tabela.add(this.convertDadosBrutosParaDeformacao({1, 0.8696, 10.000, 0.37177, 0.08000, 0.025, 0.972, 0.34746}))
       
        tabela.add(this.convertDadosBrutosParaDeformacao(1,0.8696,10.000,0.37177,0.08000,0.025,0.972,0.34746));
        tabela.add(this.convertDadosBrutosParaDeformacao(2,0.9890,10.000,0.41299,0.09000,0.031,0.969,0.34978));
        tabela.add(this.convertDadosBrutosParaDeformacao(3,1.1111,10.000,0.45267,0.10000,0.037,0.965,0.35227));
        tabela.add(this.convertDadosBrutosParaDeformacao(4,1.2360,10.000,0.49068,0.11000,0.044,0.961,0.35495));
        tabela.add(this.convertDadosBrutosParaDeformacao(5,1.3636,10.000,0.52686,0.12000,0.051,0.957,0.35784));
        tabela.add(this.convertDadosBrutosParaDeformacao(6,1.4943,10.000,0.56106,0.13000,0.059,0.953,0.36097));
        tabela.add(this.convertDadosBrutosParaDeformacao(7,1.6279,10.000,0.59311,0.14000,0.067,0.949,0.36436));
        tabela.add(this.convertDadosBrutosParaDeformacao(8,1.7647,10.000,0.62284,0.15000,0.075,0.945,0.36806));
        tabela.add(this.convertDadosBrutosParaDeformacao(9,1.9048,10.000,0.65004,0.16000,0.083,0.940,0.37209));
        tabela.add(this.convertDadosBrutosParaDeformacao(10,2.0000,10.000,0.66667,0.16667,0.089,0.938,0.37500));
        tabela.add(this.convertDadosBrutosParaDeformacao(11,2.0482,10.000,0.67451,0.17000,0.091,0.936,0.37652));
        tabela.add(this.convertDadosBrutosParaDeformacao(12,2.1951,10.000,0.69630,0.18000,0.099,0.931,0.38126));
        tabela.add(this.convertDadosBrutosParaDeformacao(13,2.3457,10.000,0.71579,0.19000,0.107,0.927,0.38611));
        tabela.add(this.convertDadosBrutosParaDeformacao(14,2.5000,10.000,0.73333,0.20000,0.115,0.922,0.39091));
        tabela.add(this.convertDadosBrutosParaDeformacao(15,2.6582,10.000,0.74921,0.21000,0.123,0.917,0.39559));
        tabela.add(this.convertDadosBrutosParaDeformacao(16,2.8205,10.000,0.76364,0.22000,0.130,0.912,0.40011));
        tabela.add(this.convertDadosBrutosParaDeformacao(17,2.9870,10.000,0.77681,0.23000,0.138,0.907,0.40444));
        tabela.add(this.convertDadosBrutosParaDeformacao(18,3.1579,10.000,0.78889,0.24000,0.145,0.902,0.40857));
        tabela.add(this.convertDadosBrutosParaDeformacao(19,3.3333,10.000,0.80000,0.25000,0.152,0.897,0.41250));
        tabela.add(this.convertDadosBrutosParaDeformacao(20,3.4953,10.000,0.80927,0.25900,0.159,0.892,0.41587));
        tabela.add(this.convertDadosBrutosParaDeformacao(21,3.5000,10.000,0.80952,0.25926,0.159,0.892,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(22,3.5000,9.960,0.80952,0.26003,0.160,0.892,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(23,3.5000,9.000,0.80952,0.28000,0.170,0.884,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(24,3.5000,8.170,0.80952,0.29991,0.181,0.875,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(25,3.5000,7.440,0.80952,0.31993,0.191,0.867,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(26,3.5000,6.790,0.80952,0.34014,0.201,0.859,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(27,3.5000,6.220,0.80952,0.36008,0.211,0.850,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(28,3.5000,5.710,0.80952,0.38002,0.220,0.842,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(29,3.5000,5.250,0.80952,0.40000,0.229,0.834,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(30,3.5000,4.830,0.80952,0.42017,0.239,0.825,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(31,3.5000,4.484,0.80952,0.43835,0.247,0.818,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(32,3.5000,4.450,0.80952,0.44025,0.247,0.817,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(33,3.5000,4.110,0.80952,0.45992,0.256,0.809,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(34,3.5000,4.070,0.80952,0.46233,0.257,0.808,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(35,3.5000,3.790,0.80952,0.48011,0.264,0.800,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(36,3.5000,3.660,0.80952,0.48883,0.268,0.797,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(37,3.5000,3.500,0.80952,0.50000,0.272,0.792,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(38,3.5000,3.230,0.80952,0.52006,0.280,0.784,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(39,3.5000,2.980,0.80952,0.54012,0.288,0.775,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(40,3.5000,2.750,0.80952,0.56000,0.296,0.767,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(41,3.5000,2.530,0.80952,0.58043,0.303,0.759,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(42,3.5000,2.330,0.80952,0.60034,0.310,0.750,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(43,3.5000,2.150,0.80952,0.61947,0.316,0.742,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(44,3.5000,2.070,0.80952,0.62832,0.319,0.739,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(45,3.5000,1.970,0.80952,0.63985,0.323,0.734,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(46,3.5000,1.800,0.80952,0.66038,0.330,0.725,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(47,3.5000,1.660,0.80952,0.67829,0.335,0.718,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(48,3.5000,1.650,0.80952,0.67961,0.335,0.717,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(49,3.5000,1.500,0.80952,0.70000,0.341,0.709,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(50,3.5000,1.360,0.80952,0.72016,0.347,0.700,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(51,3.5000,1.330,0.80952,0.72464,0.348,0.699,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(52,3.5000,1.325,0.80952,0.72538,0.349,0.698,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(53,3.5000,1.230,0.80952,0.73996,0.352,0.692,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(54,3.5000,1.110,0.80952,0.75922,0.357,0.684,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(55,3.5000,1.035,0.80952,0.77174,0.361,0.679,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(56,3.5000,0.990,0.80952,0.77951,0.362,0.676,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(57,3.5000,0.850,0.80952,0.80460,0.368,0.665,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(58,3.5000,0.770,0.80952,0.81967,0.372,0.659,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(59,3.5000,0.670,0.80952,0.83933,0.376,0.651,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(60,3.5000,0.570,0.80952,0.85995,0.380,0.642,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(61,3.5000,0.480,0.80952,0.87940,0.384,0.634,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(62,3.5000,0.390,0.80952,0.89974,0.387,0.626,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(63,3.5000,0.300,0.80952,0.92105,0.391,0.617,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(64,3.5000,0.220,0.80952,0.94086,0.394,0.609,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(65,3.5000,0.150,0.80952,0.95890,0.397,0.601,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(66,3.5000,0.070,0.80952,0.98039,0.399,0.592,0.41597));
        tabela.add(this.convertDadosBrutosParaDeformacao(67,3.5000,0.000,0.80952,1.00000,0.402,0.584,0.41597));      
        
        return tabela;
    }

}
