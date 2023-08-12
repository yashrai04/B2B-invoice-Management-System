package com.highradius.connection;

import com.highradius.model.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/oap_h2h";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "Rohan@123";

    private final static String INSERT_USERS_SQL = "INSERT INTO h2h_oap (CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, AMOUNT_IN_USD, ORDER_CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT Sl_no, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, AMOUNT_IN_USD, ORDER_CREATION_DATE FROM h2h_oap WHERE Sl_no=?";
    private static final String SELECT_ALL_USERS = "SELECT Sl_no, CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, AMOUNT_IN_USD, ORDER_CREATION_DATE FROM h2h_oap ORDER BY Sl_no DESC LIMIT 1000";
    private static final String DELETE_USERS_SQL = "DELETE FROM h2h_oap WHERE Sl_no=?";
    private static final String UPDATE_USERS_SQL = "UPDATE invoice_table SET order_currency=?, company_code=?, distribution_channel=? WHERE Sl_no=?";

    public UserDAO() {
    }

    protected static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
				try {
					connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Invoice selectUser(int Sl_no) {
        Invoice user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, Sl_no);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int CUSTOMER_ORDER_ID = rs.getInt("CUSTOMER_ORDER_ID");
                int sale_org = rs.getInt("SALES_ORG");
                String distribution_channel = rs.getString("DISTRIBUTION_CHANNEL");
                int customer_number = rs.getInt("CUSTOMER_NUMBER");
                String orderCurrency = rs.getString("ORDER_CURRENCY");
                int company_code = rs.getInt("COMPANY_CODE");
                float AMOUNT_IN_USD = rs.getFloat("AMOUNT_IN_USD");
                String ORDER_CREATION_DATE = rs.getString("ORDER_CREATION_DATE");
                user = new Invoice(Sl_no, CUSTOMER_ORDER_ID, sale_org, distribution_channel, customer_number, company_code, orderCurrency, AMOUNT_IN_USD, ORDER_CREATION_DATE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Invoice> selectAllUsers() {
        List<Invoice> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Sl_no = rs.getInt("Sl_no");
                int CUSTOMER_ORDER_ID = rs.getInt("CUSTOMER_ORDER_ID");
                int sale_org = rs.getInt("SALES_ORG");
                String distribution_channel = rs.getString("DISTRIBUTION_CHANNEL");
                int customer_number = rs.getInt("CUSTOMER_NUMBER");
                String orderCurrency = rs.getString("ORDER_CURRENCY");
                int company_code = rs.getInt("COMPANY_CODE");
                float AMOUNT_IN_USD = rs.getFloat("AMOUNT_IN_USD");
                String ORDER_CREATION_DATE = rs.getString("ORDER_CREATION_DATE");
                users.add(new Invoice(Sl_no, CUSTOMER_ORDER_ID, sale_org, distribution_channel, customer_number, company_code, orderCurrency, AMOUNT_IN_USD, ORDER_CREATION_DATE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void insertUser(Invoice invoice) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, invoice.getCustomerOrderID());
            preparedStatement.setInt(2, invoice.getSalesOrg());
            preparedStatement.setString(3, invoice.getDistributionChannel());
            preparedStatement.setInt(4, invoice.getCustomerNumber());
            preparedStatement.setInt(5, invoice.getCompanyCode());
            preparedStatement.setString(6, invoice.getOrderCurrency());
            preparedStatement.setFloat(7, invoice.getAmountInUsd());
            preparedStatement.setString(8, invoice.getOrderCreationDate());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(Invoice invoice) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            preparedStatement.setString(1, invoice.getOrderCurrency());
            preparedStatement.setInt(2, invoice.getCompanyCode());
            preparedStatement.setString(3, invoice.getDistributionChannel()); // Update the Sl_no column
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

//    private void printSQLException(SQLException ex) {
//        for (Throwable e : ex) {
//            if (e instanceof SQLException) {
//                e.printStackTrace(System.err);
//                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//                System.err.println("Message: " + e.getMessage());
//                Throwable t = ex.getCause();
//                while (t != null) {
//                    System.out.println("Cause: " + t);
//                    t = t.getCause();
//                }
//            }
//        }
//    }
}
