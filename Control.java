
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author EQUIPO
 */
public class Control 
{

     String driver = "org.postgresql.Driver"; 
    String connectString = "jdbc:postgresql://localhost:5432/contabilidad"; 
    String user = "postgres";  
    String password = "1234"; 
    Connection coneccion;
    
    public void conectarBD()
    {
        try {
            coneccion= DriverManager.getConnection(connectString,user,password);
        } catch (SQLException ex) {
            System.out.println("Error al conectar");
            System.out.println(ex);     
        }
    }
    public void cerrarBD()
    {
         try {
             coneccion.close();
         } catch (SQLException ex) {
             System.out.println("Error al cerrar conexion");
         }
    }
    
     public Usuario  inicioSesion(String usuario,String contrasena) 
    {
          Usuario u = null;
        boolean res = false;
        try {
            Statement s = null;
            ResultSet r = null;
            String sql = "";
            String rel = "";
                sql = "SELECT * from \"Usuarios\" ;";
                s = coneccion.createStatement();
                r = s.executeQuery(sql);
                while (r.next()) {
                    if (r.getString("usuario").equals(usuario)) {
                        Usuario us = new Usuario();
                        us.setCod(r.getInt("cod"));
                        us.setUsuario(r.getString("usuario"));
                        us.setContrasena(r.getString("contraseña"));
                        u = us;
                        res = true;
                    }
                }
            
            if (!res) {
                JOptionPane.showMessageDialog(null, "NO EXISTE REGISTRO");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return u;
    }
    
    public ArrayList listarPlanCue()
    {
        ArrayList lista=new ArrayList<>();
         try {
            Statement sta =null;   
            ResultSet rs=null;
             String sql;
                 sql = "SELECT * FROM \"PlanCuentas\";";
                 sta = coneccion.createStatement();
                 rs = sta.executeQuery(sql);
                 while (rs.next()) {
                     PlanCuentas pc=new PlanCuentas();
                     pc.setCod(rs.getString("codigo"));
                     pc.setDescr(rs.getString("descripcion"));
                     lista.add(pc);
                 }
        } catch (SQLException ex) {
            System.out.println("Error del SQL");
        }
        return lista;
    }
    
    public ArrayList listarAsiento()
    {
        ArrayList lista=new ArrayList<>();
         try {
            Statement sta =null;   
            ResultSet rs=null;
             String sql;
                 sql = "SELECT * FROM \"Asiento\" order by codigo;";
                 sta = coneccion.createStatement();
                 rs = sta.executeQuery(sql);
                 while (rs.next()) {
                     Asiento a=new Asiento();
                     a.setCod(rs.getInt("codigo"));
                     a.setFecha(rs.getDate("fecha"));
                     a.setDescr(rs.getString("descripcion"));
                     lista.add(a);
                 }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lista;
    }
     
    
    public int maximoPC()
    {
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT MAX (\"codigo\") FROM \"PlanCuentas\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            res=r.getInt("max");
            }
         } catch (SQLException ex) {
             System.out.println(ex);
        }  catch (NullPointerException ex) {
        }
        return res;
    }
     
     public int maximoA()
    {
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT MAX (\"codigo\") FROM \"Asiento\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            res=r.getInt("max");
            }
         } catch (SQLException ex) {
             System.out.println(ex);
        }  catch (NullPointerException ex) {
        }
        return res;
    }
    
     public int maximoMer()
    {
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT MAX (\"codM\") FROM \"Mercaderia\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            res=r.getInt("max");
            }
         } catch (SQLException ex) {
             System.out.println(ex);
        }  catch (NullPointerException ex) {
        }
        return res;
    }
        
     public  void guardarMercaderia(Mercaderia mer)
    {
         try{
            java.sql.Statement guardar = coneccion.createStatement();
            String sql = "";
            String r = "";
                Mercaderia m=new Mercaderia();
                m = mer;
                sql = "INSERT INTO \"Mercaderia\" (\"codM\",\"codi\",\"detalle\",\"fecha\",\"cantidad\",\"v/u\",\"comp_vent\") values('" + m.getCodM() + "','"+ m.getCod() + "','" + m.getDetalle()+ "','" + m.getFecha() + "','" + m.getCantidad() + "','" + m.getValor_u() + "','" + m.getC_v() + "');";
                guardar.executeUpdate(sql);
            guardar.executeUpdate(r);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     
     
     public  void guardarEmpresa(Empresa e)
    {
         try{
            java.sql.Statement guardar = coneccion.createStatement();
            String sql = "";
            String r = "";
                Empresa em=new Empresa();
                em=e;
                sql = "INSERT INTO \"Empresa\" (\"cod\",\"nombre\",\"ruc\",\"direccion\",\"telefono\") values('" + em.getCod() + "','"+ em.getNombre() + "','" + em.getRuc()+ "','" + em.getDireccion() + "','" +em.getTelefono()+ "');";
                guardar.executeUpdate(sql);
            guardar.executeUpdate(r);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     
     
      public  void guardarAsiento(Asiento as)
    {
         try{
            java.sql.Statement guardar = coneccion.createStatement();
            String sql = "";
            String r = "";
                Asiento a=new Asiento();
                a = as;
                sql = "INSERT INTO \"Asiento\" (\"codigo\",\"fecha\",\"descripcion\") values('" + a.getCod() + "','" + a.getFecha()+ "','" + a.getDescr() + "');";
                guardar.executeUpdate(sql);
            guardar.executeUpdate(r);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
      
      public  void guardarVal(ArrayList l)
    {
         try{
            java.sql.Statement guardar = coneccion.createStatement();
            String sql = "";
            String r = "";
                ArrayList lis=new ArrayList();
                lis = l;
                sql = "INSERT INTO \"PerV\" (\"cod\",\"vh_e\",\"vh_s\",\"xiii\",\"xiv\",\"fd\",\"iess\",\"ir\",\"bp\",\"ba\",\"cv\",\"m\",\"an\",\"rj\",\"ph\",\"pq\",\"sbu\") values('" + lis.get(0)+ "','" + lis.get(1)+ "','" + lis.get(2)+ "','" + lis.get(3) +"','" + lis.get(4) +"','" + lis.get(5) +"','" + lis.get(6) + "','" + lis.get(7) +"','" + lis.get(8)+ "','" + lis.get(9)+ "','" + lis.get(10) +"','" + lis.get(11) +"','" + lis.get(12) +"','" + lis.get(13) + "','" + lis.get(14) +"','" + lis.get(15) +"','" + lis.get(16) +"');";
                System.out.println(sql);
                guardar.executeUpdate(sql);
            guardar.executeUpdate(r);
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }
      
      
       public  void guardarAsiDet(AsientoDetalle asde)
    {
         try{
            java.sql.Statement guardar = coneccion.createStatement();
            String sql = "";
            String r = "";
                AsientoDetalle ad=new AsientoDetalle();
                ad = asde;
                sql = "INSERT INTO \"AsientoDetalle\" (\"codAsDe\",\"codD\",\"detalle\",\"debe\",\"haber\",\"codAsie\") values('" + ad.getCod()+ "','" + ad.getCodDet()+ "','" + ad.getDetalle()+ "','" + ad.getDebe() +"','" + ad.getHaber() +"','" + ad.getCodAsi() + "');";
                guardar.executeUpdate(sql);
            guardar.executeUpdate(r);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
     
       public  void guardarPersona(Persona p)
    {
         try{
            java.sql.Statement guardar = coneccion.createStatement();
            String sql = "";
            String r = "";
                Persona per=new Persona();
                per=p;
                sql = "INSERT INTO \"Persona\" (\"cod\",\"nombre\",\"cargo\",\"sbu\",\"nh-s\",\"nh-e\",\"bp\",\"ba\",\"com-v\",\"ant\",\"mul\",\"rj\",\"ph\",\"pq\",\"salud\",\"alim\",\"edu\",\"viv\",\"vest\",\"c\") values('" 
                        + per.getCod()+ "','" + per.getNombre()+ "','" + per.getCargo()+ "','" + per.getSbu() +"','" + per.getNh_s() +"','" +per.getNh_e()+"','" +per.getBp()+"','" +per.getBa()+"','" 
                        + per.getCom_v()+ "','" + per.getAnt()+ "','" + per.getMul() +"','" + per.getRj() +"','" + per.getPh()+"','"+ per.getPq()+ "','" + per.getSalud()+ "','" + per.getAlim() +"','" + per.getEdu()
                        +"','" + per.getViv()+"','" + per.getVest()+"','" + per.getC()+"');";
             guardar.executeUpdate(sql);
            guardar.executeUpdate(r);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       
       
       
     public int maximoAsDet()
    {
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT MAX (\"codAsDe\") FROM \"AsientoDetalle\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            res=r.getInt("max");
            }
         } catch (SQLException ex) {
             System.out.println(ex);
        }  catch (NullPointerException ex) {
        }
        return res;
    }
     
     
     public int maximoPer()
    {
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT MAX (\"cod\") FROM \"Persona\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            res=r.getInt("max");
            }
         } catch (SQLException ex) {
             System.out.println(ex);
        }  catch (NullPointerException ex) {
        }
        return res;
    }
     
     public ArrayList selectDis()
    {
        ArrayList l=new ArrayList();
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT distinct (\"detalle\") FROM \"AsientoDetalle\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            l.add(r.getString("detalle"));
            }
         } catch (SQLException ex) {
             System.out.println(ex);
        }  catch (NullPointerException ex) {
        }
        return l;
    }
     
     public ArrayList ListV()
    {
        ArrayList l=new ArrayList();
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT * FROM \"PerV\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            l.add(r.getInt("cod"));
            l.add(r.getInt("vh_e"));
            l.add(r.getInt("vh_s"));
            l.add(r.getInt("xiii"));
            l.add(r.getInt("xiv"));
            l.add(r.getInt("fd"));
            l.add(r.getInt("iess"));
            l.add(r.getInt("ir"));
            l.add(r.getInt("bp"));
            l.add(r.getInt("ba"));
            l.add(r.getInt("cv"));
            l.add(r.getInt("m"));
            l.add(r.getInt("an"));
            l.add(r.getInt("rj"));
            l.add(r.getInt("ph"));
            l.add(r.getInt("pq"));
            l.add(r.getInt("sbu"));
            }
         } catch (SQLException ex) {
             System.out.println("eroor");
        }  catch (NullPointerException ex) {
        }
        return l;
    }
     
     
     
     public int maxV()
    {
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT MAX (\"cod\") FROM \"PerV\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            res=r.getInt("max");
            }
         } catch (SQLException ex) {
             System.out.println("error");
        }  catch (NullPointerException ex) {
        }
        return res;
    }
     
     
     public int maximoEm()
    {
        int res=0;
         try {
            Statement s=null;
            ResultSet r=null;
            String sql="SELECT MAX (\"cod\") FROM \"Empresa\" ;";
            s=coneccion.createStatement();
            r=s.executeQuery(sql);
            while(r.next())
            {
            res=r.getInt("max");
            }
         } catch (SQLException ex) {
             System.out.println(ex);
        }  catch (NullPointerException ex) {
        }
        return res;
    }
     
     public ArrayList listarAsDet() {
        ArrayList lista = new ArrayList<>();
        try {
            Statement sta = null;
            ResultSet rs = null;
            String sql;
            sql = "SELECT * FROM \"AsientoDetalle\";";
            sta = coneccion.createStatement();
            rs = sta.executeQuery(sql);
            while (rs.next()) {
                AsientoDetalle ad = new AsientoDetalle();
                ad.setCod(rs.getInt("codAsDe"));
                ad.setCodDet(rs.getString("codD"));
                ad.setDetalle(rs.getString("detalle"));
                ad.setDebe(rs.getDouble("debe"));
                ad.setHaber(rs.getDouble("haber"));
                ad.setCodAsi(rs.getInt("codAsie"));
                lista.add(ad);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lista;
    }
    
     
     public ArrayList listarPer() {
        ArrayList lista = new ArrayList<>();
        try {
            Statement sta = null;
            ResultSet rs = null;
            String sql;
            sql = "SELECT * FROM \"Persona\";";
            sta = coneccion.createStatement();
            rs = sta.executeQuery(sql);
            while (rs.next()) {
                Persona p=new Persona();
                p.setCod(rs.getInt("cod"));
                p.setNombre(rs.getString("nombre"));
                p.setCargo(rs.getString("cargo"));
                p.setSbu(rs.getDouble("sbu"));
                p.setNh_s(rs.getInt("nh-s"));
                p.setNh_e(rs.getInt("nh-e"));
                p.setBp(rs.getDouble("bp"));
                p.setBa(rs.getDouble("ba"));
                p.setCom_v(rs.getDouble("com-v"));
                p.setAnt(rs.getDouble("ant"));
                p.setMul(rs.getDouble("mul"));
                p.setRj(rs.getDouble("rj"));
                p.setPh(rs.getDouble("ph"));
                p.setPq(rs.getDouble("pq"));
                p.setSalud(rs.getDouble("salud"));
                p.setAlim(rs.getDouble("alim"));
                p.setEdu(rs.getDouble("edu"));
                p.setViv(rs.getDouble("viv"));
                p.setVest(rs.getDouble("vest"));
                p.setC(rs.getString("c"));
                p.setC(rs.getString("c"));
                
                lista.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lista;
    }
     
     
     
     
      public ArrayList listarEmp() {
        ArrayList lista = new ArrayList<>();
        try {
            Statement sta = null;
            ResultSet rs = null;
            String sql;
            sql = "SELECT * FROM \"Empresa\";";
            sta = coneccion.createStatement();
            rs = sta.executeQuery(sql);
            while (rs.next()) {
                Empresa e=new Empresa();
                e.setCod(rs.getInt("cod"));
                e.setNombre(rs.getString("nombre"));
                e.setRuc(rs.getString("ruc"));
                e.setDireccion(rs.getString("direccion"));
                e.setTelefono(rs.getString("telefono"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lista;
    }
     
     
      public ArrayList listarMerc() {
        ArrayList lista = new ArrayList<>();
        try {
            Statement sta = null;
            ResultSet rs = null;
            String sql;
            sql = "SELECT * FROM \"Mercaderia\";";
            sta = coneccion.createStatement();
            rs = sta.executeQuery(sql);
            while (rs.next()) {
                Mercaderia m=new Mercaderia();
                m.setCodM(rs.getInt("codM"));
                m.setCod(rs.getString("codi"));
                m.setDetalle(rs.getString("detalle"));
                m.setFecha(rs.getDate("fecha"));
                m.setCantidad(rs.getInt("cantidad"));
                m.setValor_u(rs.getInt("v/u"));
                m.setC_v(rs.getString("comp_vent"));
                lista.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lista;
    }
     
}
