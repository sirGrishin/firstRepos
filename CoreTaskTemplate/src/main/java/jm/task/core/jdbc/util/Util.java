package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/mydbtest?autoReconnect=true&useSSL=false";
    private static final String username = "root";
    private static final String password = "admin";
    static Connection connection = null;

    public static Connection connect() {
        try  {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to Store DB succesfull!");
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return connection;
    }
}
//import java.sql.Connection;
//        import java.sql.DriverManager;
//
//public class Program{
//
//    public static void main(String[] args) {
//        try{
//            String url = "jdbc:mysql://localhost/store";
//            String username = "root";
//            String password = "password";
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            try (Connection conn = DriverManager.getConnection(url, username, password)){
//
//                System.out.println("Connection to Store DB succesfull!");
//            }
//        }
//        catch(Exception ex){
//            System.out.println("Connection failed...");
//
//            System.out.println(ex);
//        }
//    }