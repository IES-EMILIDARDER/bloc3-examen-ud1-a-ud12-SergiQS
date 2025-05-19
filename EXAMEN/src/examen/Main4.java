package examen;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main4 {

    public static void main(String[] args)throws FileNotFoundException, IOException {
       
       
        String servidor = "jdbc:mysql://192.168.126.245:3306/";
        String bdades = "gbd";
        String usuari = "root";
        String passwd = "";
        String sql = """
                     SELECT marca , model  FROM departments WHERE any = 2020
                     """;
      
       
        try ( Connection connexio = DriverManager.getConnection(servidor+bdades, usuari, passwd);
              Statement statement = connexio.createStatement() ) {
            int any = 2020; 
            
            System.out.println("Connexió amb la base de dades MySQL exitosa.");
            
            sql += any;  
            try (ResultSet resultSet = statement.executeQuery(sql)) {

          
                while (resultSet.next()) {
                    System.out.println( resultSet.getInt("marca") + " " +
                                        resultSet.getString("model")
                                      );
                }
            } catch (SQLException e) {
                System.err.println("Error al executar la instrucció SQL: " + e.getMessage());
            }
            System.out.println("Connexió tancada.");
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de dades: " + e.getMessage());
        }
        
        
    }
}
    
    
