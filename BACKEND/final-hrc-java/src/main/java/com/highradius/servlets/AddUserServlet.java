package com.highradius.servlets;

import com.highradius.model.Invoice;
import com.highradius.connection.UserDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
        try {
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            Invoice invoice = gson.fromJson(reader, Invoice.class);

            // Insert the invoice into the database
            UserDAO.insertUser(invoice);

            // Send a success response
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"User added successfully!\"}");
        } catch (SQLException e) {
            // Send an error response
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error adding user to the database.\"}");
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        

        try {
            // Retrieve all users from the database
            List<Invoice> users = userDAO.selectAllUsers();

            // Convert the list of users to JSON
            Gson gson = new Gson();
            String jsonUsers = gson.toJson(users);

            // Send the JSON response
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(jsonUsers);
        } catch (IOException e) {
            // Send an error response
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Error retrieving users from the database.\"}");
            e.printStackTrace();
        }

    }
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set CORS headers for preflight requests
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");
    }

}
