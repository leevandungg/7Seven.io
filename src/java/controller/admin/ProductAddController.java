package controller.admin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Catalog;
import model.Product;
import service.CategoryService;
import service.ProductService;
import service.impl.CategoryServicesImpl;
import service.impl.ProductServiceImpl;

public class ProductAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryService cateService = new CategoryServicesImpl();
        List<Catalog> cateList = cateService.getAll();
        req.setAttribute("catelist", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/addproduct.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String product_cate = req.getParameter("product-cate");
        String product_name = req.getParameter("product-name");
        String product_price = req.getParameter("product-price");
        String product_status = "1";
        String product_desc = req.getParameter("product-desc");
        String product_content = req.getParameter("product-content");
        String product_discount = req.getParameter("product-discount");
        String product_inventory = req.getParameter("product-sum");
        String product_image = req.getParameter("product-image");
        String product_day = req.getParameter("product-day");

        Product product = new Product();
        product.setCatalog_id(product_cate);
        product.setName(product_name);
        product.setPrice(product_price);
        if(Integer.parseInt(product_inventory) == 0){
            product_status = "0";
        }
        product.setStatus(product_status);
        product.setDescription(product_desc);
        product.setContent(product_content);
        product.setDiscount(product_discount);
        product.setImage_link(product_image);
        product.setCreated(product_day);
        product.setInventory(Integer.parseInt(product_inventory));
        productService.insert(product);
        resp.sendRedirect(req.getContextPath() + "/admin/product/list");
    }
}
