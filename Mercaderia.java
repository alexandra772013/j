
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author EQUIPO
 */
public class Mercaderia 
{
    public int codM;
    public String cod;
    public String detalle;
    public Date fecha;
    public int cantidad;
    public double valor_u;
    public String c_v;

    public int getCodM() {
        return codM;
    }

    public void setCodM(int codM) {
        this.codM = codM;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValor_u() {
        return valor_u;
    }

    public void setValor_u(double valor_u) {
        this.valor_u = valor_u;
    }

    public String getC_v() {
        return c_v;
    }

    public void setC_v(String c_v) {
        this.c_v = c_v;
    }
    
}
