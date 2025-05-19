package examen;
import examen.Vehicle;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main5 {
    
    public static void main(String[] args) {
        
         final String MYSQL_CON = "c:\\temp\\mysql.con";
        GestorBBDD gestorBBDD = new GestorBBDD(MYSQL_CON);

        try (Stream<String> linies = Files.lines(Paths.get("c:\\temp\\vehicles.csv")); Connection conn = gestorBBDD.getConnectionFromFile()) {
            linies.filter(linia -> !linia.isBlank() && !linia.startsWith("#")).map(linia -> linia.split(","))
                    .forEach(parts -> {
                        try {
                            gestorBBDD.executaSQL(conn, "INSERT INTO vehicles (matricula, marca, model, any, preu) VALUES (?, ?, ?, ?, ? )", parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), Integer.parseInt(parts[4].trim()));
                        } catch (SQLException e) {
                              try {
                                    if ((e.getSQLState().equals("23000") && e.getErrorCode() == 1062)){
                                        // si la primary key esta repetida hacemos un update
                                        String  matricula = parts[0].trim();
                                        String marca = parts[1].trim();
                                        String model = parts[2].trim();
                                        String any =parts[3].trim() ;
                                        double preu = Integer.parseInt(parts[4].trim());
                                        
                                        gestorBBDD.executaSQL(conn,"UPDATE vehicles SET matricula = ?, marca = ? , model = ? , any = ? , preu = ?  ", matricula, marca, model , any, preu);
                                        
                                    }else {
                                        throw new RuntimeException(e);
                                    }
                              }catch (SQLException e2){
                                  throw new RuntimeException(e2);
                                  
                              }
                        }
                    });

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    }

