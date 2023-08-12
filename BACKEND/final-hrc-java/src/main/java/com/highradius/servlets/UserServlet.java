//package com.highradius.servlets;
//
//import com.highradius.connection.*;
//
//import com.highradius.model.*;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//@WebServlet("/")
//public class UserServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private UserDAO userDAO;
//
//    public void init() {
//        userDAO = new UserDAO();
//    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        setAccessControlHeaders(response);
//        doGet(request, response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        setAccessControlHeaders(response);
//        String action = request.getServletPath();
//
//
//        try {
//            switch (action) {
//                case "/new":
//                    showNewForm(request, response);
//                    break;
//                case "/insert":
//                    insertUser(request, response);
//                    break;
//                case "/delete":
//                    deleteUser(request, response);
//                    break;
//                case "/edit":
//                    showEditForm(request, response);
//                    break;
//                case "/update":
//                    updateUser(request, response);
//                    break;
//                default:
//                    listUser(request, response);
//                    break;
//            }
//        } catch (SQLException ex) {
//            throw new ServletException(ex);
//        }
//    }
//    private void setAccessControlHeaders(HttpServletResponse response) {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("Access-Control-Max-Age", "3600");
//    }
//    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Forward the request to the user-form.jsp for new user creation
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//        int customerOrderId = Integer.parseInt(request.getParameter("CUSTOMER_ORDER_ID"));
//        int salesOrg = Integer.parseInt(request.getParameter("SALES_ORG"));
//        String distributionChannel = request.getParameter("DISTRIBUTION_CHANNEL");
//        int customerNumber = Integer.parseInt(request.getParameter("CUSTOMER_NUMBER"));
//        String orderCurrency = request.getParameter("ORDER_CURRENCY");
//        int companyCode = Integer.parseInt(request.getParameter("COMPANY_CODE"));
//        float amountInUSD = Float.parseFloat(request.getParameter("AMOUNT_IN_USD"));
//        String orderCreationDate = request.getParameter("ORDER_CREATION_DATE");
//
//        Invoice newUser = new Invoice(customerOrderId, salesOrg, distributionChannel, customerNumber, companyCode, orderCurrency, amountInUSD, orderCreationDate);
//        userDAO.insertUser(newUser);
//
//        // Create a JSON response object
//        JsonObject jsonResponse = new JsonObject();
//        jsonResponse.addProperty("message", "User inserted successfully");
//
//        // Convert the JSON object to string
//        Gson gson = new Gson();
//        String json = gson.toJson(jsonResponse);
//
//        // Set the response content type to JSON
//        response.setContentType("application/json");
//
//        // Write the JSON response to the response object
//        response.getWriter().write(json);
//    }
//
//    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        List<Invoice> listUser = userDAO.selectAllUsers();
//
//        // Convert the user list to JSON using Gson library
//        Gson gson = new Gson();
//        String json = gson.toJson(listUser);
//
//        // Set the response content type to JSON
//        response.setContentType("application/json");
//
//        // Write the JSON response to the response object
//        response.getWriter().write(json);
//    }
//
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        int customerOrderId = Integer.parseInt(request.getParameter("CUSTOMER_ORDER_ID"));
//        Invoice existingUser = userDAO.selectUser(customerOrderId);
//
//        // Forward the request to the user-form.jsp for editing the existing user
//        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//        request.setAttribute("user", existingUser);
//        dispatcher.forward(request, response);
//    }
//
//    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        int customerOrderId = Integer.parseInt(request.getParameter("CUSTOMER_ORDER_ID"));
//        int salesOrg = Integer.parseInt(request.getParameter("SALES_ORG"));
//        String distributionChannel = request.getParameter("DISTRIBUTION_CHANNEL");
//        int customerNumber = Integer.parseInt(request.getParameter("CUSTOMER_NUMBER"));
//        String orderCurrency = request.getParameter("ORDER_CURRENCY");
//        int companyCode = Integer.parseInt(request.getParameter("COMPANY_CODE"));
//        float amountInUSD = Float.parseFloat(request.getParameter("AMOUNT_IN_USD"));
//        String orderCreationDate = request.getParameter("ORDER_CREATION_DATE");
//
//        Invoice updatedUser = new Invoice(id, customerOrderId, salesOrg, distributionChannel, customerNumber, companyCode, orderCurrency, amountInUSD, orderCreationDate);
//        userDAO.updateUser(updatedUser);
//
//        // Redirect the user to the list page after successful update
//        response.sendRedirect("list");
//    }
//
//    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//        int customerOrderId = Integer.parseInt(request.getParameter("CUSTOMER_ORDER_ID"));
//        userDAO.deleteUser(customerOrderId);
//
//        // Redirect the user to the list page after successful deletion
//        response.sendRedirect("list");
//    }
//}