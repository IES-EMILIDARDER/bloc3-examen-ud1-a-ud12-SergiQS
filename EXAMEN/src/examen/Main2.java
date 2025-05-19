package examen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main2 {

    public static void main(String[] args) {
        
             List<Vehicle> vehicles = Arrays.asList(
    new Vehicle("1234-HJK", "Toyota",     "Corolla", 2010, 12000),
    new Vehicle("1230-BCN", "Toyota",     "Yaris", 2010, 12200),
    new Vehicle("5678-KLM", "Volkswagen", "Golf",    2018, 18000),
    new Vehicle("9012-NPR", "Seat",       "Ibiza",   2015, 10000),
    new Vehicle("3456-STU", "Tesla",      "Model 3", 2022, 40000),
    new Vehicle("7890-VWX", "Renault",    "Clio",    2012,  9000)
             );
        
        /* try (Stream<String> linies = Files.lines(Paths.get("C:\\temp\\vehicles.csv"))) {
            List<Vehicle> vehicles = linies.filter(linia -> !linia.isBlank() && !linia.startsWith("#")).map(linia -> linia.split(","))
                    .map(parts -> parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), Integer.parseInt(parts[4].trim()));
                
                    
                 
        } catch (IOException e) {
            e.printStackTrace();
        }
         
           /*int sumaVehicles =
                   try (Stream<String> linies = Files.lines(Paths.get("C:\\temp\\clients.csv"))) {
            List<Vehicle> vehicles = linies.filter(linia -> !linia.isBlank() && !linia.startsWith("#")).map(linia -> linia.split(","))
                    .map(parts -> new Vehicle(Integer.parseInt(parts[0].trim()),
                    parts[1].trim(), parts[2].trim())).collect(Collectors.toList());
           ;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
           
           
           int sumaVehicles = (int) vehicles.stream().filter(f -> f.getPreu() > 15000).distinct().count();
           System.out.println(sumaVehicles);
           
           double preuMitja = vehicles.stream().map(m -> m.getPreu()).mapToDouble(m -> m).average().orElse(0);
           double mesCar = vehicles.stream().map(m -> m.getPreu()).mapToDouble(m -> m).max().orElse(0);
           double  mesBarat = vehicles.stream().map(m -> m.getPreu()).mapToDouble(m -> m).min().orElse(0);
           
            System.out.println(preuMitja);
            System.out.println(mesCar);
            System.out.println(mesBarat);
           
           
    }
}       

