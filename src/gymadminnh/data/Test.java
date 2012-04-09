/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gymadminnh.data;

import java.sql.*;

public class Test {

    public static void main(String[] a) throws Exception {
        
        String dbUrl = "jdbc:h2:database/GYMDB";
        String driver = "org.h2.Driver";
        String userName = "sa";
        String password = "";
        
        String test = "this is a test";
        
        Connection conn = null;
        Statement stmt = null;
        
        
        conn = DriverManager.getConnection(dbUrl, userName, password);
        stmt = conn.createStatement();
        //PreparedStatement pss =null;
        
        
        ResultSet rs = null;
        
        rs = stmt.executeQuery("SELECT * FROM GYMDB.CLIENTES");
        
        while ( rs.next() ){
            int id = rs.getInt(1);
            String nombre = rs.getString(2);
            String fecha = rs.getString(3);
            String tel = rs.getString(4);
            String mail = rs.getString(5);
            String dir = rs.getString(6);
            System.out.println("hola!");
            String x;
            x = String.format("'%s', '%s', '%s', '%s', '%s', '%s'", id, nombre, fecha, tel, mail, dir);
            System.out.println(x);
        }
        stmt.close();
        conn.close();
        
    }

}
