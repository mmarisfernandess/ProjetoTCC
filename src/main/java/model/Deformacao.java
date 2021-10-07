/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mmari
 */

@Entity(name="Deformacao")
@Table(name = "deformacao", schema = "public")
public class Deformacao implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;
    double concreto;
    double aco;
    double alfa;
    double ksi;
    double micro_sd;
    double zeta;
    double ksi_linha;

    public Deformacao() {
    }

    public Deformacao(Integer id, double concreto, double aco, double alfa, double ksi, double micro_sd, double zeta, double ksi_linha) {
        this.id = id;
        this.concreto = concreto;
        this.aco = aco;
        this.alfa = alfa;
        this.ksi = ksi;
        this.micro_sd = micro_sd;
        this.zeta = zeta;
        this.ksi_linha = ksi_linha;
    }

    
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getConcreto() {
        return concreto;
    }

    public void setConcreto(double concreto) {
        this.concreto = concreto;
    }

    public double getAco() {
        return aco;
    }

    public void setAco(double aco) {
        this.aco = aco;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getKsi() {
        return ksi;
    }

    public void setKsi(double ksi) {
        this.ksi = ksi;
    }

    public double getMicro_sd() {
        return micro_sd;
    }

    public void setMicro_sd(double micro_sd) {
        this.micro_sd = micro_sd;
    }

    public double getZeta() {
        return zeta;
    }

    public void setZeta(double zeta) {
        this.zeta = zeta;
    }

    public double getKsi_linha() {
        return ksi_linha;
    }

    public void setKsi_linha(double ksi_linha) {
        this.ksi_linha = ksi_linha;
    }

    @Override
    public String toString() {
        return "Deformacao{" + "id=" + id + ", concreto=" + concreto + ", aco=" + aco + ", alfa=" + alfa + ", ksi=" + ksi + ", micro_sd=" + micro_sd + ", zeta=" + zeta + ", ksi_linha=" + ksi_linha + '}';
    }

    

}
