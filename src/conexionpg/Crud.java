package conexionpg;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Notification;

public class Crud {
    
    private static ConexionPG con=new ConexionPG("localhost", "5432", "empleados", "postgres", "admin");

    public static void Create(long identificacion, String nombre, String fecha_nacimiento, String cargo){
        con.cerrarConexion();
        con.abrirConexion();
        try {
            Statement st=con.getCon().createStatement();
            st.executeUpdate("insert into empleados (identificacion, nombre, fecha_nacimiento, cargo) VALUES('"+identificacion+"','"+nombre+"','"+fecha_nacimiento+"', '"+cargo+"')");
        } catch (Exception ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
    }
    
    public static ResultSet Read(){
        con.abrirConexion();
        try {
            Statement st=con.getCon().createStatement();
            ResultSet rs=st.executeQuery("select * from empleados");
            return rs;
        } catch (Exception ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            con.cerrarConexion();
        }
    }
    
    public static void Update(long identificacion, String nombre, String fecha_nacimiento, String cargo){
        con.abrirConexion();
        try {
            Statement st=con.getCon().createStatement();
            st.executeUpdate("update empleados set nombre='"+nombre+"', fecha_nacimiento='"+fecha_nacimiento+"', cargo='"+cargo+"' where identificacion='"+identificacion+"'");
        } catch (Exception ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
    }
    
    public static int Delete(long identificacion){
        int a=0;
        con.abrirConexion();
        try {
            Statement st=con.getCon().createStatement();
            String sql="delete from empleados where identificacion="+identificacion;
            a=st.executeUpdate(sql);
        } catch (Exception ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
        return a;
    }
}
