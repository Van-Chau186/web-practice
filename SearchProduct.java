package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.ProductDAO;

/**
 * 商品検索画面の処理を行うServlet
 */
@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet {

    // シリアルバージョンUID（Servletのバージョン管理用）
    private static final long serialVersionUID = 1L;

    /**
     * GETリクエストを受け取ったときの処理
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 日本語文字化け防止のため文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // リクエストパラメータから商品名を取得
        String productName = request.getParameter("productName");

        // productNameがnullの場合は空文字を設定
        if (productName == null) {
            productName = "";
        }

        // ProductDAOのインスタンスを作成
        ProductDAO dao = new ProductDAO();

        // 商品リストを格納する変数
        List<Product> products;

        // 商品名が空の場合 → 全件検索
        if (productName.trim().isEmpty()) {
            products = dao.findAll();
        } 
        // 商品名が入力されている場合 → 商品名で検索
        else {
            products = dao.findByName(productName);
        }

        // デバッグ用：コンソールに検索情報を表示
        System.out.println("=== SearchProduct called ===");
        System.out.println("productName = [" + productName + "]");
        System.out.println("products size = " + products.size());

        // JSPに渡すためリクエストスコープにデータを保存
        request.setAttribute("products", products);
        request.setAttribute("productName", productName);

        // searchProduct.jspへフォワード（画面表示）
        request.getRequestDispatcher("/WEB-INF/view/searchProduct.jsp")
               .forward(request, response);
    }
}