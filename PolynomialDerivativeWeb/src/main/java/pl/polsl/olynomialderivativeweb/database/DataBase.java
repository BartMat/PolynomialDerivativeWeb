package pl.polsl.olynomialderivativeweb.database;

import java.sql.*;
import java.util.UUID;

public class DataBase {

    public void createTables() {

        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Polynomial_Derivative", "root", "root")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE Polynomials "
                    + "(id INTEGER, polynomial VARCHAR(50))");
            
             statement.executeUpdate("CREATE TABLE Derivatives "
                    + "(id INTEGER, polynomial VARCHAR(50))");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
   public void insertDataPolynomials(int id, String polynomial) {

        String arg = String.valueOf(id) + ", " + polynomial;
        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        // make a connection to DB
        try ( Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Polynomial_Derivative", "root", "root")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO Polynomials VALUES (" + arg + ")");
            System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void insertDataDerivatives(int id, String derivative) {

        String arg = String.valueOf(id) + ", " + derivative;
        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        // make a connection to DB
        try ( Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Polynomial_Derivative", "root", "root")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO Derivatives VALUES (" + arg + ")");
            System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
       public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str = "" + idOne;
        int uid = str.hashCode();
        String filterStr = "" + uid;
        str = filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
