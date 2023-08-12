package com.highradius.servlets;

import com.highradius.model.Invoice;
import com.highradius.connection.UserDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/fetchUsers")
public class FetchUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");

        List<Invoice> listUser = userDAO.selectAllUsers();

        // Convert the user list to JSON using Gson library
        Gson gson = new Gson();
        String json = gson.toJson(listUser);

        // Set the response content type to JSON
        response.setContentType("application/json");

        // Write the JSON response to the response object
        response.getWriter().write(json);
    }
}
