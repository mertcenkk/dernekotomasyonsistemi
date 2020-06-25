/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kmert
 */
public class Dernek {
    private int dernekId;
    private String adi;
    private String kurulusTipi;
    private String adresi;
    private String amaci;
    private int telNo;
    private Date kurulusTarihi;
    private Uye uye;

    public Dernek() {
    }

    public Dernek(int dernekId, String adi, String kurulusTipi, String adresi, String amaci, int telNo, Date kurulusTarihi,Uye uye) {
        this.dernekId = dernekId;
        this.adi = adi;
        this.kurulusTipi = kurulusTipi;
        this.adresi = adresi;
        this.amaci = amaci;
        this.telNo = telNo;
        this.kurulusTarihi = kurulusTarihi;
        this.uye = uye;
    }

    public int getDernekId() {
        return dernekId;
    }

    public void setDernekId(int dernekId) {
        this.dernekId = dernekId;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getKurulusTipi() {
        return kurulusTipi;
    }

    public void setKurulusTipi(String kurulusTipi) {
        this.kurulusTipi = kurulusTipi;
    }

    public String getAdresi() {
        return adresi;
    }

    public void setAdresi(String adresi) {
        this.adresi = adresi;
    }

    public String getAmaci() {
        return amaci;
    }

    public void setAmaci(String amaci) {
        this.amaci = amaci;
    }

    public int getTelNo() {
        return telNo;
    }

    public void setTelNo(int telNo) {
        this.telNo = telNo;
    }

    public Date getKurulusTarihi() {
        return kurulusTarihi;
    }

    public void setKurulusTarihi(Date kurulusTarihi) {
        this.kurulusTarihi = kurulusTarihi;
    }

    

    public Uye getUye() {
        if(this.uye==null)
            this.uye=new Uye();
        return uye;
    }

    public void setUye(Uye uye) {
        this.uye = uye;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hash(dernekId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dernek other = (Dernek) obj;
        if (this.dernekId != other.dernekId) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
}
