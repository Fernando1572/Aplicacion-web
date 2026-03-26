/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package murach.data;

import murach.business.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Permite realizat las operaciones CRUD en la entidad
 * user de la base de datos murach
 * @author fer_g
 */
public class UserDB {
    /**
     * Inserta un nuevo usuario en la bd
     * @param user
     * @return 
     */
    
    public static int insert(User user) {
        // Obtiene una instancia del ConnectionPool para gestionar
        //las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();
        
        //Obtiene una conexion de la pool
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        //Consulta SQL para insertar un nuevo usuario en la tabla user
        String query 
                = "INSERT INTO user (Email, FirstName, LastName)"
                + "Values (?, ?, ?)";
        
        try {
            //Prepara la declaracion SQL
            ps = connection.prepareStatement(query);
            
            //Establece los valores en la consulta preparada a partir del objeto User
            //Dado
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            
            //Ejecuta la actualizacion de la base de datos y retorna el numero de filas
            //Afectadas
            return ps.executeUpdate();
        } catch (SQLException e) {
            //Si ocurre una excepcion SQLException, captura el error
            System.out.println(e);
            Error.descripcion = e.getMessage(); //Asigna el mensaje de error a un objeto Error
            return 0; //Retorna 0 para indicar fallo en la insercion
        } finally {
            //Asegura el cierre de la preparedStatement y la liberacion de la conexion en el bloque finally
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }//Fin del metodo insert
    
    public static List<User> getAllUsers() {
        //Obttiene una instancia del ConnectionPool para gestionar las conexiones a la base de datos
        ConnectionPool pool = ConnectionPool.getInstance();
        
        //obtiene una conexion del pool
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        //Consulta para obtener todos los usuarios
        String query = "SELECT * FROM user";
        
        try {
            //Prepara la declaracion SQL
            ps = connection.prepareStatement(query);
            
            //ejecuta la consulta
            rs = ps.executeQuery();
            
            //coleccion para almacenar la lista de usuarios
            ArrayList<User> users = new ArrayList<>();
            
            //creamos un objeto de tipo user
            User user = null;
            
            //se recorre la lsita de elementos encontrados en la bd
            while(rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                
                users.add(user);//se agrega el elemento a la coleccion
            }
            
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            Error.descripcion = e.getMessage();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
