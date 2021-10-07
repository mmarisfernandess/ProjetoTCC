/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.facade;

import main.java.dao.GenericDAOImp;
import main.java.model.Deformacao;

/**
 *
 * @author PMBV-163979
 */
public class DeformacaoFacade extends GenericDAOImp<Deformacao>{

    public DeformacaoFacade() {
        super(Deformacao.class);
    }
    
}
