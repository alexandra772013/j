/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author EQUIPO
 */
public class AsientoDetalle 
{
    public int cod;
    public String codDet;
    public String detalle;
    public double debe;
    public double haber;
    public int codAsi;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getCodDet() {
        return codDet;
    }

    public void setCodDet(String codDet) {
        this.codDet = codDet;
    }

   
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getDebe() {
        return debe;
    }

    public void setDebe(double debe) {
        this.debe = debe;
    }

    public double getHaber() {
        return haber;
    }

    public void setHaber(double haber) {
        this.haber = haber;
    }

    public int getCodAsi() {
        return codAsi;
    }

    public void setCodAsi(int codAsi) {
        this.codAsi = codAsi;
    }
    
    
}
