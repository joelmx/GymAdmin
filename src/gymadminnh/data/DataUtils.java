/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gymadminnh.data;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author joel
 */
public class DataUtils {

    private final String dbUrl = "jdbc:h2:database/GYMDB";
    private final String driver = "org.h2.Driver";
    private final String userName = "sa";
    private final String password = "";

    private Connection conectar(Connection c) {
        try {
            Class.forName(driver).newInstance();
            c = DriverManager.getConnection(dbUrl, userName, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }

    public String[] getListaNombres() {
        ArrayList<String> n = obtenerListaNombres();
        String[] nombres = new String[n.size()];
        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = n.get(i);
        }
        return nombres;
    }

    public List<Cliente> getListaClientes(String nombre) {
        ArrayList<Cliente> clientes = obtenerClientes(nombre);
        return clientes;
    }

    public Cliente getClienteDB(int id) {
        Cliente c = new Cliente();
        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT * FROM GYMDB.CLIENTES WHERE IDCLIENTE='" + id + "'";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int idCliente = rs.getInt(1);
                String nombre = rs.getString(2);
                Date fecha = rs.getDate(3);
                String tel = rs.getString(4);
                String email = rs.getString(5);
                String dir = rs.getString(6);

                c.setIdCliente(idCliente);
                c.setNombre(nombre);
                c.setNacidoCuando(fecha);
                c.setTelefono(tel);
                c.setEmail(email);
                c.setDireccion(dir);
            }

        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return c;
    }

    public ArrayList<Cliente> getTodosClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT * FROM GYMDB.CLIENTES";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int idCliente = rs.getInt(1);
                String nombre = rs.getString(2);
                Date fecha = rs.getDate(3);
                String tel = rs.getString(4);
                String email = rs.getString(5);
                String dir = rs.getString(6);

                Cliente cliente = new Cliente();
                cliente.setIdCliente(idCliente);
                cliente.setNombre(nombre);
                cliente.setNacidoCuando(fecha);
                cliente.setTelefono(tel);
                cliente.setEmail(email);
                cliente.setDireccion(dir);

                clientes.add(cliente);
            }

        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return clientes;
    }

    public ArrayList<MedidasCliente> getMedidasCliente(int idCliente) {
        ArrayList<MedidasCliente> lmc = new ArrayList<MedidasCliente>();

        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT * FROM GYMDB.MEDIDAS WHERE IDCLIENTE='" + idCliente + "'";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int idMedida = rs.getInt("IDMEDIDA");
                Date fecha = rs.getDate("FECHA");
                double peso = rs.getDouble("PESO");
                double bicepDer = rs.getDouble("BICEPDER");
                double bicepIzq = rs.getDouble("BICEPIZQ");
                double pectoral = rs.getDouble("PECTORAL");
                double abdomen = rs.getDouble("ABDOMEN");
                double cintura = rs.getDouble("CINTURA");
                double gluteos = rs.getDouble("GLUTEOS");
                double cadera = rs.getDouble("CADERA");
                double piernaDer = rs.getDouble("PIERNADER");
                double piernaIzq = rs.getDouble("PIERNAIZQ");

                MedidasCliente mc = new MedidasCliente();
                mc.setIdMedida(idMedida);
                mc.setFecha(fecha);
                mc.setPeso(peso);
                mc.setBicepDer(bicepDer);
                mc.setBicepIzq(bicepIzq);
                mc.setPectoral(pectoral);
                mc.setAbdomen(abdomen);
                mc.setCintura(cintura);
                mc.setGluteos(gluteos);
                mc.setCadera(cadera);
                mc.setPiernaDer(piernaDer);
                mc.setPiernaIzq(piernaIzq);

                lmc.add(mc);
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        return lmc;
    }

    public int agregarClienteDB(String nombre, Date fechaSQL, String telefono, String email, String dir) {
        String query = "INSERT INTO GYMDB.CLIENTES (NOMBRE, FECHA_NAC, "
                + "TELEFONO, EMAIL, DIRECCION) VALUES ( ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = null;
        Connection conn = null;
        int response = 0;
        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nombre);
            pstmt.setDate(2, fechaSQL);
            pstmt.setString(3, telefono);
            pstmt.setString(4, email);
            pstmt.setString(5, dir);

            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    public int modificarClienteDB(Cliente cliente) {
        String query = "UPDATE GYMDB.CLIENTES SET NOMBRE=?, FECHA_NAC=?, "
                + "TELEFONO=?, EMAIL=?, DIRECCION=? WHERE IDCLIENTE=?";

        PreparedStatement pstmt = null;
        Connection conn = null;

        java.sql.Date fechaSQL = new java.sql.Date(cliente.getNacidoCuando().getTime());
        int response = 0;
        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cliente.getNombre());
            pstmt.setDate(2, fechaSQL);
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getEmail());
            pstmt.setString(5, cliente.getDireccion());
            pstmt.setInt(6, cliente.getIdCliente());

            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    public int agregarMedidasDB(MedidasCliente mc, int idCliente) {
        String query = "INSERT INTO GYMDB.MEDIDAS (IDCLIENTE, FECHA, PESO, "
                + "BICEPDER, BICEPIZQ, PECTORAL, ABDOMEN, CINTURA, GLUTEOS, "
                + "CADERA, PIERNADER, PIERNAIZQ) VALUES ( ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = null;
        Connection conn = null;
        int response = 0;

        java.sql.Date fechaSQL = new java.sql.Date(mc.getFecha().getTime());

        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idCliente);
            pstmt.setDate(2, fechaSQL);
            pstmt.setDouble(3, mc.getPeso());
            pstmt.setDouble(4, mc.getBicepDer());
            pstmt.setDouble(5, mc.getBicepIzq());
            pstmt.setDouble(6, mc.getPectoral());
            pstmt.setDouble(7, mc.getAbdomen());
            pstmt.setDouble(8, mc.getCintura());
            pstmt.setDouble(9, mc.getGluteos());
            pstmt.setDouble(10, mc.getCadera());
            pstmt.setDouble(11, mc.getPiernaDer());
            pstmt.setDouble(12, mc.getPiernaIzq());

            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    public int modificarMedidasDB(MedidasCliente mc) {
        String query = "UPDATE GYMDB.MEDIDAS SET FECHA=?, PESO=?, BICEPDER=?, "
                + "BICEPIZQ=?, PECTORAL=?, ABDOMEN=?, CINTURA=?, GLUTEOS=?, "
                + "CADERA=?, PIERNADER=?, PIERNAIZQ=? WHERE IDMEDIDA=?";

        PreparedStatement pstmt = null;
        Connection conn = null;
        int response = 0;

        java.sql.Date fechaSQL = new java.sql.Date(mc.getFecha().getTime());

        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, fechaSQL);
            pstmt.setDouble(2, mc.getPeso());
            pstmt.setDouble(3, mc.getBicepDer());
            pstmt.setDouble(4, mc.getBicepIzq());
            pstmt.setDouble(5, mc.getPectoral());
            pstmt.setDouble(6, mc.getAbdomen());
            pstmt.setDouble(7, mc.getCintura());
            pstmt.setDouble(8, mc.getGluteos());
            pstmt.setDouble(9, mc.getCadera());
            pstmt.setDouble(10, mc.getPiernaDer());
            pstmt.setDouble(11, mc.getPiernaIzq());
            pstmt.setInt(12, mc.getIdMedida());

            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    public int agregarMembresiaDB(Membresia m, int idCliente) {
        String query = "INSERT INTO GYMDB.MEMBRESIAS (IDCLIENTE, FECHA_INICIO, "
                + "DURACION, DESCRIPCION, PRECIO) VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = null;
        int response = 0;

        java.sql.Date fechaInicioSQL = new java.sql.Date(m.getFechaInicio().getTime());

        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idCliente);
            pstmt.setDate(2, fechaInicioSQL);
            pstmt.setShort(3, m.getDuracion());
            pstmt.setString(4, m.getDescripcion());
            pstmt.setDouble(5, m.getPrecio());

            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    public int modificarMembresiaDB(Membresia m) {
        String query = "UPDATE GYMDB.MEMBRESIAS SET FECHA_INICIO=?, "
                + "DURACION=?, DESCRIPCION=?, PRECIO=? WHERE IDMEMBRESIA=?";

        PreparedStatement pstmt = null;
        Connection conn = null;
        int response = 0;

        java.sql.Date fechaInicioSQL = new java.sql.Date(m.getFechaInicio().getTime());

        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, fechaInicioSQL);
            pstmt.setShort(2, m.getDuracion());
            pstmt.setString(3, m.getDescripcion());
            pstmt.setDouble(4, m.getPrecio());
            pstmt.setInt(5, m.getIdMembresia());


            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    private ArrayList<String> obtenerListaNombres() {
        ArrayList<String> nombres = new ArrayList<String>();
        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT NOMBRE FROM GYMDB.CLIENTES";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String str = rs.getString(1);
//                System.out.println(str);
                nombres.add(str);
            }

        } catch (SQLException ex) {
            System.out.println("ERROR:" + ex);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        }

        return nombres;

    }

    private ArrayList<Cliente> obtenerClientes(String n) {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT * FROM GYMDB.CLIENTES WHERE NOMBRE='" + n + "'";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int idCliente = rs.getInt(1);
                String nombre = rs.getString(2);
                Date fecha = rs.getDate(3);
                String tel = rs.getString(4);
                String email = rs.getString(5);
                String dir = rs.getString(6);

                Cliente cliente = new Cliente();
                cliente.setIdCliente(idCliente);
                cliente.setNombre(nombre);
                cliente.setNacidoCuando(fecha);
                cliente.setTelefono(tel);
                cliente.setEmail(email);
                cliente.setDireccion(dir);

                clientes.add(cliente);
            }

        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return clientes;
    }

    public ArrayList<Membresia> getMembresias(int idCliente) {
        ArrayList<Membresia> mems = new ArrayList<Membresia>();

        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT * FROM GYMDB.MEMBRESIAS WHERE IDCLIENTE='" + idCliente + "'";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int idMembresia = rs.getInt("IDMEMBRESIA");
                Date fecha = rs.getDate("FECHA_INICIO");
                short duracion = rs.getShort("DURACION");
                String descripcion = rs.getString("DESCRIPCION");
                double precio = rs.getDouble("PRECIO");

                Membresia m = new Membresia();

                m.setIdMembresia(idMembresia);
                m.setFechaInicio(fecha);
                m.setDuracion(duracion);
                m.setDescripcion(descripcion);
                m.setPrecio(precio);

                mems.add(m);
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return mems;
    }

    public int agregarAsistenciaDB(Asistencia a, int idCliente) {
        String query = "INSERT INTO GYMDB.ASISTENCIAS (IDCLIENTE, FECHA, HORA) "
                + "VALUES ( ?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = null;
        int response = 0;

        java.sql.Date fecha = new java.sql.Date(a.getFechaAsistencia().getTime());
        java.sql.Time hora = new java.sql.Time(a.getFechaAsistencia().getTime());

        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idCliente);
            pstmt.setDate(2, fecha);
            pstmt.setTime(3, hora);

            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    public ArrayList<Asistencia> obtenerAsistenciasClienteDB(int idCliente) {
        ArrayList<Asistencia> ass = new ArrayList<Asistencia>();

        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT * FROM GYMDB.ASISTENCIAS WHERE IDCLIENTE='" + idCliente + "'";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int idAsistencia = rs.getInt("IDASISTENCIA");
                Date fecha = rs.getDate("FECHA");
                Time hora = rs.getTime("HORA");

                StringBuilder sb = new StringBuilder();
                sb.append(fecha.toString());
                sb.append(" ");
                sb.append(hora.toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                java.util.Date d = new java.util.Date();
                try {
                    d = sdf.parse(sb.toString());
                } catch (ParseException ex) {
                    System.out.println(ex);
                }

                Asistencia a = new Asistencia();
                a.setIdAsistencia(idAsistencia);
                a.setFechaAsistencia(d);
                ass.add(a);
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return ass;
    }

    public TableModel obtenerAsistenciasDiaDB(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT TC.IDCLIENTE, NOMBRE, HORA FROM "
                + "GYMDB.CLIENTES TC JOIN GYMDB.ASISTENCIAS AS TA ON "
                + "TC.IDCLIENTE = TA.IDCLIENTE WHERE FECHA='"
                + sdf.format(date) + "' ORDER BY HORA";
        ResultSet rs = null;

        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            ResultSetMetaData metaData = rs.getMetaData();
            int numCols = metaData.getColumnCount();
            Vector nombresCols = new Vector();

            // Get the column names
            for (int column = 0; column < numCols; column++) {
                nombresCols.addElement(metaData.getColumnLabel(column + 1));
            }

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= numCols; i++) {
                    newRow.addElement(rs.getObject(i));
                }
                rows.addElement(newRow);
            }
            
            return new DefaultTableModel(rows, nombresCols);
            
        } catch (SQLException se) {
            System.out.println("Error: " + se);
            return null;
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public TableModel obtenerResumenFinanzasDB(int ano, String mes) {
        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT * FROM GYMDB.FINANZAS WHERE FECHA LIKE '"
                + ano + "-"
                + mes + "-%'";
        ResultSet rs = null;
        
        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            ResultSetMetaData metaData = rs.getMetaData();
            int numCols = metaData.getColumnCount();
            Vector nombresCols = new Vector();

            // Get the column names
            for (int column = 0; column < numCols; column++) {
                nombresCols.addElement(metaData.getColumnLabel(column + 1));
            }

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= numCols; i++) {
                    newRow.addElement(rs.getObject(i));
                }
                rows.addElement(newRow);
            }

            return new DefaultTableModel(rows, nombresCols);
        } catch (SQLException se) {
            System.out.println("Error: " + se);
            return null;
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public int agregarMovimiento(java.util.Date fecha, Double precio, String descr) {
        String query = "INSERT INTO GYMDB.FINANZAS (FECHA, DESCRIPCION, PRECIO) "
                + "VALUES ( ?, ?, ?)";

        PreparedStatement pstmt = null;
        Connection conn = null;
        int response = 0;

        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

        try {
            conn = conectar(conn);
            pstmt = conn.prepareStatement(query);
            pstmt.setDate(1, fechaSQL);
            pstmt.setString(2, descr);
            pstmt.setDouble(3, precio);

            response = pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return response;
        }
    }

    public TableModel obtenerResumenMembresiasDB(int ano, String mes) {
        Connection conn = null;
        Statement stmt = null;
        String query = "SELECT TC.IDCLIENTE, NOMBRE, FECHA_INICIO, DURACION, "
                + "DESCRIPCION, PRECIO FROM GYMDB.CLIENTES TC JOIN "
                + "GYMDB.MEMBRESIAS AS TM ON TC.IDCLIENTE = TM.IDCLIENTE WHERE "
                + "FECHA_INICIO LIKE '" + ano + "-" + mes + "-%'";
        ResultSet rs = null;
        
        try {
            conn = conectar(conn);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            ResultSetMetaData metaData = rs.getMetaData();
            int numCols = metaData.getColumnCount();
            Vector nombresCols = new Vector();

            // Get the column names
            for (int column = 0; column < numCols; column++) {
                nombresCols.addElement(metaData.getColumnLabel(column + 1));
            }

            // Get all rows.
            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();
                for (int i = 1; i <= numCols; i++) {
                    newRow.addElement(rs.getObject(i));
                }
                rows.addElement(newRow);
            }

            return new DefaultTableModel(rows, nombresCols);
        } catch (SQLException se) {
            System.out.println("Error: " + se);
            return null;
        } finally {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }
}
