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
public class MalBildirim {
    int id;
    String nitelik;
    Date alinmaTarihi;
    Date bildirimTarihi;
    String fiyat;
    String adres;

    public MalBildirim() {
    }
    
    

    public MalBildirim(int id, String nitelik, Date alinmaTarihi, Date bildirimTarihi, String fiyat, String adres) {
        this.id = id;
        this.nitelik = nitelik;
        this.alinmaTarihi = alinmaTarihi;
        this.bildirimTarihi = bildirimTarihi;
        this.fiyat = fiyat;
        this.adres = adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNitelik() {
        return nitelik;
    }

    public void setNitelik(String nitelik) {
        this.nitelik = nitelik;
    }

    public Date getAlinmaTarihi() {
        return alinmaTarihi;
    }

    public void setAlinmaTarihi(Date alinmaTarihi) {
        this.alinmaTarihi = alinmaTarihi;
    }

    public Date getBildirimTarihi() {
        return bildirimTarihi;
    }

    public void setBildirimTarihi(Date bildirimTarihi) {
        this.bildirimTarihi = bildirimTarihi;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
   
    
    
}
