package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ordered;
import model.Product;
import service.OrderedService;
import service.ProductService;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;

public class OrderdetailListController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    OrderedService orderedServcie = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ordered> orderedList = orderedServcie.getAll();
        req.setAttribute("orderedlist", orderedList);
        List<Product> products = productService.getAll();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/show-orderdetail.jsp");
        dispatcher.forward(req, resp);
    }
}
