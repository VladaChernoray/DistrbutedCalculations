package DatabaseConnection;

import Werehouse.Section;
import Werehouse.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WerehouseDatabaseConnection {

    private static final String jdbcDriverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/?user=root&password=538734962318&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "538734962318";
    private static Logger logger = Logger.getLogger(WerehouseDatabaseConnection.class.getName());

    public static ArrayList<Product> getAllProducts() {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM werehouse.products");
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                products.add(product);
            }
            connection.close();
            return products;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static Product getProductsByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM werehouse.product " +
                    "WHERE id=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Product product = null;
            while (rs.next()) {
                product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
            }
            connection.close();
            return product;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Product> getProductsByID(int sectionID) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM werehouse.products " +
                    "WHERE sectionID=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, sectionID);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                products.add(product);
            }
            connection.close();
            return products;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Product> getProductsBySectionName(String sectionName) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            return null;
        } else {
            return getProductsBySectionID(section.getSectionID());
        }
    }

    public static ArrayList<Product> getProductsByPriceGreaterThan(int price) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM werehouse.products " +
                    "WHERE age>?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, price);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getInt(5));
                products.add(product);
            }
            connection.close();
            return products;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static Section getSectionByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM werehouse.section " +
                    "WHERE id=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Section section = null;
            while (rs.next()) {
                section = new Section(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            connection.close();
            return section;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static Section getSectionByName(String name) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM werehouse.section " +
                    "WHERE name=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            Section section = null;
            while (rs.next()) {
                section = new Section(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            connection.close();
            return section;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Section> getAllSections() {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM werehouse.sections;");
            ArrayList<Section> sections = new ArrayList<>();
            while (rs.next()) {
                Section section = new Section(rs.getInt(1), rs.getString(2), rs.getInt(3));
                sections.add(section);
            }
            connection.close();
            return sections;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static ArrayList<Section> getSectionByType(int type) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "SELECT * FROM werehouse.sections WHERE type=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, type);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Section> sections = new ArrayList<>();
            while (rs.next()) {
                Section section = new Section(rs.getInt(1), rs.getString(2), rs.getInt(3));
                sections.add(section);
            }
            connection.close();
            return sections;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public static final boolean addSection(String name, int type) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url);
            String sqlQuery = "INSERT INTO werehouse.sections(name,type) VALUES(?,?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, name);
            stmt.setInt(2, course);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    private static boolean insertProduct(int sectionID, String country, String name, int price) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url);
            String sqlQuery = "INSERT INTO werehouse.products(country,name,price,sectionID) VALUES(?,?,?,?);";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setString(1, country);
            stmt.setString(2, name);
            stmt.setInt(3, price);
            stmt.setInt(4, sectionID);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public static final boolean addProductToSection(String sectionName, String country, String name, int price) {
        Section section = getSectionByName(sectionName);
        if (section == null) {
            return false;
        } else {
            return insertProduct(section.getSectionID(), country, name, price);
        }
    }

    public static final boolean addProductToSection(int sectionID, String country, String name, int price) {
        Section section = getSectionByID()yID(sectionID);
        if (section == null) {
            return false;
        } else {
            return insertProduct(section.getSectionID(), country, name, price);
        }
    }

    public static final boolean deleteProductByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "DELETE FROM werehouse.[roduct WHERE id=?;";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }


    public static final boolean deleteSectionByID(int id) {
        try {
            Class.forName(jdbcDriverName);
            Connection connection = DriverManager.getConnection(url, user, password);
            String sqlQuery = "DELETE FROM werehouse.sections WHERE id=?";
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.execute();
            sqlQuery = "DELETE FROM werehouse.sections WHERE sectionID=?;";
            stmt = connection.prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.execute();
            connection.close();
            return true;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public static final boolean deleteSectionByName(String name) {
        Section section = getSectionByName(name);
        if (section == null) {
            return false;
        } else {
            try {
                Class.forName(jdbcDriverName);
                Connection connection = DriverManager.getConnection(url, user, password);
                String sqlQuery = "DELETE FROM werehouse.sections WHERE name=?";
                PreparedStatement stmt = connection.prepareStatement(sqlQuery);
                stmt.setString(1, name);
                stmt.execute();
                sqlQuery = "DELETE FROM werehouse.productes WHERE sectionID=?;";
                stmt = connection.prepareStatement(sqlQuery);
                stmt.setInt(1, section.getSectionID());
                stmt.execute();
                connection.close();
                return true;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                return false;
            }
        }
    }

    public static final boolean changeSectionForProducts(int newSectionID, int productID) {
        Section section = getSectionByID(newSectionID);
        if (section == null) {
            return false;
        } else {
            try {
                Class.forName(jdbcDriverName);
                Connection connection = DriverManager.getConnection(url, user, password);
                String sqlQuery = "UPDATE werehouse.products " +
                        "SET sectionID=? " +
                        "WHERE id=?";
                PreparedStatement stmt = connection.prepareStatement(sqlQuery);
                stmt.setInt(1, newSectionID);
                stmt.setInt(2, productID);
                stmt.execute();
                connection.close();
                return true;
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage());
                return false;
            }
        }
    }

}
