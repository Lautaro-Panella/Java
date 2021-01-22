
package DataBase;

import java.sql.*;

public class SQLQuery {
    protected Connection connection;
    protected PreparedStatement consulta;
    protected ResultSet datos;
    
    // Método de conexión a la base de datos.
    public void conectar(String server, String base, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://" + server + "/" + base + "?useUnicode=true&serverTimezone=GMT-3",user, password);
    }
    
    // Método de desconexión a la base de datos.
    public void desconectar() throws SQLException { 
        this.connection.close();
        this.consulta.close();
    }

    public void desconectar(ResultSet re_Set) throws SQLException {
        this.desconectar();
        re_Set.close();
    }    
}
