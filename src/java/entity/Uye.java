/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author kmert
 */
public class Uye {

    private int uyeId;
    private int tcNo;
    private String adiSoyadi;
    private int telNo;
    private String meslek;
    private String ogrenimDurumu;

    public Uye() {
    }

    public Uye(int uyeId, int tcNo, String adiSoyadi, int telNo, String meslek, String ogrenimDurumu) {
        this.uyeId = uyeId;
        this.tcNo = tcNo;
        this.adiSoyadi = adiSoyadi;
        this.telNo = telNo;
        this.meslek = meslek;
        this.ogrenimDurumu = ogrenimDurumu;
    }

    public int getUyeId() {
        return uyeId;
    }

    public void setUyeId(int uyeId) {
        this.uyeId = uyeId;
    }

    public int getTcNo() {
        return tcNo;
    }

    public void setTcNo(int tcNo) {
        this.tcNo = tcNo;
    }

    public String getAdiSoyadi() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi = adiSoyadi;
    }

    public int getTelNo() {
        return telNo;
    }

    public void setTelNo(int telNo) {
        this.telNo = telNo;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public String getOgrenimDurumu() {
        return ogrenimDurumu;
    }

    public void setOgrenimDurumu(String ogrenimDurumu) {
        this.ogrenimDurumu = ogrenimDurumu;
    }

}
