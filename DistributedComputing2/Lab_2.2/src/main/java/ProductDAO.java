import lombok.var;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static ProductDTO findById(long id) {
        try (Connection connection = DBConnection.getConnection();) {
            String sql =
                    "SELECT * "
                            + "FROM Product "
                            + "WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductDTO product = null;
            if (resultSet.next()) {
                product = new ProductDTO();
                product.setId(resultSet.getLong(1));
                product.setName(resultSet.getString(2));
                product.setSectionId(resultSet.getLong(3));
                product.setPrice(resultSet.getLong(4));
            }
            preparedStatement.close();
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ProductDTO findByName(String name) {
        try (Connection connection = DBConnection.getConnection();) {
            String sql =
                    "SELECT * "
                            + "FROM Product "
                            + "WHERE name = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            ProductDTO product = null;
            if (resultSet.next()) {
                product = new ProductDTO();
                product.setId(resultSet.getLong(1));
                product.setName(resultSet.getString(2));
                product.setSectionId(resultSet.getLong(3));
                product.setSection(resultSet.getLong(4));
            }
            preparedStatement.close();
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean update(ProductDTO product) {
        try (Connection connection = DBConnection.getConnection();) {
            String sql =
                    "UPDATE product "
                            + "SET name = ?, sectionid = ?, price = ? "
                            + "WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getSectionId());
            preparedStatement.setLong(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());
            var result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insert(ProductDTO product) {
        try (Connection connection = DBConnection.getConnection();) {
            String sql =
                    "INSERT INTO product (name,sectionid,price) "
                            + "VALUES (?,?,?) "
                            + "RETURNING id";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getSectionId());
            preparedStatement.setLong(3, product.getPrice());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getLong(1));
            } else
                return false;
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(ProductDTO product) {
        try (Connection connection = DBConnection.getConnection();) {
            String sql =
                    "DELETE FROM product "
                            + "WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, product.getId());
            var result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<ProductDTO> findAll() {
        try (Connection connection = DBConnection.getConnection();) {
            String sql =
                    "SELECT * "
                            + "FROM product";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductDTO> list = new ArrayList<>();
            while (resultSet.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(resultSet.getLong(1));
                product.setName(resultSet.getString(2));
                product.setSectionId(resultSet.getLong(3));
                product.setPrice(resultSet.getLong(4));
                list.add(product);
            }
            preparedStatement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<ProductDTO> findBySectionId(Long id) {
        try (Connection connection = DBConnection.getConnection();) {
            String sql =
                    "SELECT * "
                            + "FROM product "
                            + "WHERE sectionid = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductDTO> list = new ArrayList<>();
            while (resultSet.next()) {
                ProductDTO product = new ProductDTO();
                product.setId(resultSet.getLong(1));
                product.setName(resultSet.getString(2));
                product.setSectionId(resultSet.getLong(3));
                product.setPrice(resultSet.getLong(4));
                list.add(product);
            }
            preparedStatement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
