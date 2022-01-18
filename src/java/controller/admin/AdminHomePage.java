package controller.admin;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ordered;
import model.Product;
import model.Transactions;
import service.OrderedService;
import service.ProductService;
import service.TransactionService;
import service.impl.OrderedServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.TransactionServicesImpl;

public class AdminHomePage extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TransactionService transactionService = new TransactionServicesImpl();
    OrderedService catalogService = new OrderedServiceImpl();
    ProductService productService = new ProductServiceImpl();

    public AdminHomePage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transactions> list_trans = transactionService.getAll();
        List<Ordered> list_ordered = catalogService.getAll();
        List<Product> list_product = productService.getAll();
        int order = transactionService.getTotalTransactions();
        long soldPrice = 0;
        int sumTrans = 0;
        long quantitySold = 0;
        List<Transactions> transactionsSoldList = new ArrayList<>();
        List<Ordered> listOrderedPaid = new ArrayList<>();
        long priceSoldGameConsole = 0;
        long priceSoldCD = 0;
        long priceSoldChair = 0;
        long priceSoldCategory = 0;
        for (Transactions a : list_trans) {
            int id = a.getId();
            String x = a.getStatus();
            if (x == null) {
                x = "";
            }
            if (x.equals("Đã thanh toán")) {
                for (Ordered b : list_ordered) {
                    int orderedID = Integer.parseInt(b.getTransaction_id());
                    if (id == orderedID) {
                        listOrderedPaid.add(b);
                    }
                }
            }
        }
        for (Product a : list_product) {
            String catalog = a.getCatalog_id();
            String id = a.getId();
            int productID = Integer.parseInt(id);
            int catalog_id = Integer.parseInt(catalog);
            if (catalog_id == 1) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceSoldGameConsole += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 2) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceSoldChair += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 3) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceSoldCD += totalPriceAfterSales;
                    }
                }
            }
            if (catalog_id == 4) {
                for (Ordered b : listOrderedPaid) {
                    String x = b.getProduct_id();
                    int orderedID = Integer.parseInt(x);
                    if (productID == orderedID) {
                        int qty = b.getQty();
                        long price = Long.parseLong(a.getPrice().replace(".", ""));
                        long totalPrice = qty * price;
                        int percent = Integer.parseInt(a.getDiscount());
                        double balance = (double) percent / 100;
                        double percentBalance = 1 - balance;
                        long totalPriceAfterSales = (long) Math.round(totalPrice * percentBalance);
                        priceSoldCategory += totalPriceAfterSales;
                    }
                }
            }
        }
        for (Transactions a : list_trans) {
            String x = a.getStatus();
            if (x == null) {
                x = "";
            }
            if (x.compareTo("Đã thanh toán") == 0) {
                long price = Long.parseLong(a.getAmount().replace(".", ""));
                soldPrice += price;
                sumTrans++;
                transactionsSoldList.add(a);
            }
        }
        for (Ordered a : list_ordered) {
            int orderedID = Integer.parseInt(a.getTransaction_id());
            for (Transactions b : list_trans) {
                if (b.getStatus() == null) {
                    b.setStatus("Chưa thanh toán");
                    transactionService.edit(b);
                }
                if (orderedID == b.getId() && !b.getStatus().equals("Đã hủy")) {
                    Product product = productService.get(Integer.parseInt(a.getProduct_id()));
                    long sold = product.getSold();
                    if (sold > 0) {
                        quantitySold += a.getQty();
                        System.out.println(quantitySold);
                    } else {
                        quantitySold = a.getQty();
                        System.out.println(quantitySold);
                    }
                }
            }
        }
        System.out.println(soldPrice);
        request.setAttribute("transactions", list_trans);
        request.setAttribute("sold-transactions", transactionsSoldList);
        request.setAttribute("ordered", list_ordered);
        request.setAttribute("place-order", order);
        request.setAttribute("sold-sum", sumTrans);
        request.setAttribute("sold-quantity", quantitySold);

        request.setAttribute("sold-gameconsole", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceSoldGameConsole)));
        request.setAttribute("sold-gaming", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceSoldChair)));
        request.setAttribute("sold-cd", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceSoldCD)));
        request.setAttribute("sold-category", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(priceSoldCategory)));

        request.setAttribute("sold-price", String.valueOf(NumberFormat.getNumberInstance(Locale.GERMANY).format(soldPrice)));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin/index.jsp");
        dispatcher.forward(request, response);
    }
}
