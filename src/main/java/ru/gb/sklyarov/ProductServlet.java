package ru.gb.sklyarov;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        resp.getWriter().printf("<html><body>");
        for (int i = 0; i < 10; i++) {
            int index = i + 1;
            resp.getWriter().printf("<h2>" + new Product(index, "Product #" + index, 10 * index) + "</h2>");
        }

        resp.getWriter().printf("</body></html>");
    }
}
