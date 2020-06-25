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
public class Kayit {
    private int id;
    private Uye uye;
    private Dernek dernek;

    public Kayit() {
    }

    public Kayit(int id, Uye uye, Dernek dernek) {
        this.id = id;
        this.uye = uye;
        this.dernek = dernek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uye getUye() {
        return uye;
    }

    public void setUye(Uye uye) {
        this.uye = uye;
    }

    public Dernek getDernek() {
        return dernek;
    }

    public void setDernek(Dernek dernek) {
        this.dernek = dernek;
    }

    
    
    
}
