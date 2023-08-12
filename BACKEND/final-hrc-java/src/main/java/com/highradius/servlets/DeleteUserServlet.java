//package com.highradius.servlets;
//
//import com.highradius.connection.UserDAO;
//import com.google.gson.Gson;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//
//@WebServlet("/api/deleteUser")
//public class DeleteUserServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private UserDAO userDAO;
//
//    public void init() {
//        userDAO = new UserDAO();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("Access-Control-Max-Age", "3600");
//
//        String customerOrderIdParam = request.getParameter("CUSTOMER_ORDER_ID");
//        if (customerOrderIdParam == null || customerOrderIdParam.isEmpty()) {
//            // Handle the case where CUSTOMER_ORDER_ID parameter is missing or empty
//            String jsonResponse = "{\"success\": false, \"message\": \"Missing or empty CUSTOMER_ORDER_ID parameter\"}";
//            response.setContentType("application/json");
//            response.getWriter().write(jsonResponse);
//            return;
//        }
//
//        int customerOrderId = Integer.parseInt(customerOrderIdParam);
//        boolean success = false;
//        String message = "";
//
//        try {
//            userDAO.deleteUser(customerOrderId);
//            success = true;
//            message = "User deleted successfully";
//        } catch (SQLException e) {
//            message = "Failed to delete user: " + e.getMessage();
//        }
//
//        // Create a JSON response
//        Gson gson = new Gson();
//        String jsonResponse = gson.toJson(new DeleteResponse(success, message));
//
//        // Set the response content type to JSON
//        response.setContentType("application/json");
//
//        // Write the JSON response to the response object
//        response.getWriter().write(jsonResponse);
//    }
//
//
//    // Inner class representing the response object
//    private static class DeleteResponse {
//        private boolean success;
//        private String message;
//
//        public DeleteResponse(boolean success, String message) {
//            this.success = success;
//            this.message = message;
//        }
//
//        public boolean isSuccess() {
//            return success;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//    }
//}
