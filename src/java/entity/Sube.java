/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author kmert
 */
public class Sube {

    private Long id;
    private String ad;
    private Date tarih;
    private String adres;
    private Dernek dernek;

    public Sube() {
    }

    public Sube(Long id, String ad, Date tarih, String adres, Dernek dernek) {
        this.id = id;
        this.ad = ad;
        this.tarih = tarih;
        this.adres = adres;
        this.dernek = dernek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Dernek getDernek() {
        return dernek;
    }

    public void setDernek(Dernek dernek) {
        this.dernek = dernek;
    }

    

}
