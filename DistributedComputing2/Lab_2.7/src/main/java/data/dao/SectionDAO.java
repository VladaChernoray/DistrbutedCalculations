package data.dao;

import data.DBConnection;
import data.dto.SectionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO {
    public static SectionDTO findById(long id) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql =
                    "SELECT * "
                            + "FROM section "
                            + "WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            SectionDAO section = null;
            if (resultSet.next()) {
                section = new SectionDTO();
                section.setId(resultSet.getLong(1));
                section.setName(resultSet.getString(2));
            }
            preparedStatement.close();
            return section;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SectionDTO findByName(String name) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql =
                    "SELECT * "
                            + "FROM section "
                            + "WHERE name = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            SectionDTO section = null;
            if (resultSet.next()) {
                section = new SectionDTO();
                section.setId(resultSet.getLong(1));
                section.setName(resultSet.getString(2));
            }
            preparedStatement.close();
            return section;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean update(SectionDTO section) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql =
                    "UPDATE section "
                            + "SET name = ? "
                            + "WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, section.getName());
            preparedStatement.setLong(2, section.getId());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insert(SectionDTO section) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql =
                    "INSERT INTO section (name) "
                            + "VALUES (?) "
                            + "RETURNING id";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, section.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                section.setId(resultSet.getLong(1));
            } else
                return false;
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(SectionDTO section) {
        try (Connection connection = DBConnection.getConnection()) {
            String sql =
                    "DELETE FROM section "
                            + "WHERE id = ?";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, section.getId());
            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<SectionDTO> findAll() {
        try (Connection connection = DBConnection.getConnection()) {
            String sql =
                    "SELECT * "
                            + "FROM section";
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<SectionDTO> list = new ArrayList<>();
            while (resultSet.next()) {
                SectionDTO section = new SectionDTO();
                section.setId(resultSet.getLong(1));
                section.setName(resultSet.getString(2));
                list.add(section);
            }
            preparedStatement.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
