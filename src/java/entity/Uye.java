/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author kmert
 */
public class Uye {

    private Long uyeId;
    private Long tcNo;
    private String adiSoyadi;
    private Long telNo;
    private String meslek;
    private String ogrenimDurumu;

    public Uye() {
    }

    public Uye(Long uyeId, Long tcNo, String adiSoyadi, Long telNo, String meslek, String ogrenimDurumu) {
        this.uyeId = uyeId;
        this.tcNo = tcNo;
        this.adiSoyadi = adiSoyadi;
        this.telNo = telNo;
        this.meslek = meslek;
        this.ogrenimDurumu = ogrenimDurumu;
    }

    public Long getUyeId() {
        return uyeId;
    }

    public void setUyeId(Long uyeId) {
        this.uyeId = uyeId;
    }

    public Long getTcNo() {
        return tcNo;
    }

    public void setTcNo(Long tcNo) {
        this.tcNo = tcNo;
    }

    public String getAdiSoyadi() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi = adiSoyadi;
    }

    public Long getTelNo() {
        return telNo;
    }

    public void setTelNo(Long telNo) {
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
