/*
 * 
 */
package dgg.ucav.dao.modelos;

/**
 *
 * @author dgarcia25
 */
import java.sql.Connection;

public interface DAO {

       // Definición del método a declarar en las clases de los usuarios 
       public Connection getConnection(); 
}