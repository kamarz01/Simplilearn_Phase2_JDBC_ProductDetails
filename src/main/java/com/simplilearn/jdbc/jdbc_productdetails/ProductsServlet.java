package com.simplilearn.jdbc.jdbc_productdetails;

import com.simplilearn.jdbc.jdbc_productdetails.jdbc.DbConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet(name = "ProductsServlet", value = "/products")
public class ProductsServlet extends HttpServlet {
    Properties props;

    @Override
    public void init() throws ServletException {
        super.init();
        InputStream dbDetails = this.getServletContext().getResourceAsStream("WEB-INF/jdbc.properties");
        props = new Properties();
        try {
            props.load(dbDetails);
            DbConnection connection = new DbConnection(props.getProperty("url"),props.getProperty("user"),props.getProperty("password"));
            Statement statement = connection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate("insert into products (name, price, date_added) values ('Product1', 1000, now())");
            statement.executeUpdate("insert into products (name, price, date_added) values ('Product2', 2000, now())");
            statement.executeUpdate("insert into products (name, price, date_added) values ('Product3', 3000, now())");
            statement.executeUpdate("insert into products (name, price, date_added) values ('Product4', 4000, now())");
            statement.executeUpdate("insert into products (name, price, date_added) values ('Product5', 5000, now())");
            statement.close();
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("productId");
        try {
            DbConnection connection = new DbConnection(props.getProperty("url"),props.getProperty("user"),props.getProperty("password"));
            Statement statement = connection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet result = statement.executeQuery("select * from products where id =" + id);
            if (result.next() == false){
                response.sendRedirect("no.html");
            }else{
                result.beforeFirst();
                List<product> products = new ArrayList<>();
                while (result.next()){
                    product p = new product();
                    p.setId(Integer.parseInt(result.getString("id")));
                    p.setName(result.getString("name"));
                    p.setPrice(Integer.parseInt(result.getString("price")));
                    products.add(p);
                }
                statement.close();
                connection.closeConnection();
                request.setAttribute("products",products);
                RequestDispatcher dispatcher = request.getRequestDispatcher("productDetails.jsp");
                dispatcher.forward(request,response);
            }



        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
