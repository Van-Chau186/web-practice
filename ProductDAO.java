package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品データをデータベースから取得するDAOクラス
 */
public class ProductDAO {

	// JDBCドライバーのクラス名
	private static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

	// データベース接続URL
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/kadaidb?serverTimezone=Asia/Tokyo";

	// データベースユーザー名
	private static final String DATABASE_USER = "root";

	// データベースパスワード
	private static final String DATABASE_PASSWORD = "root1234";

	/**
	 * 商品テーブルの全データを取得するメソッド
	 */
	public List<Product> findAll() {

		// 商品リストを作成
		List<Product> products = new ArrayList<>();

		try {
			// JDBCドライバーを読み込む
			Class.forName(JDBC_DRIVER_CLASS_NAME);

			// データベースに接続
			Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

			// SQL文（全商品取得）
			String sql = "SELECT product_code, product_name, price FROM m_product";

			// SQLを実行する準備
			PreparedStatement pstm = conn.prepareStatement(sql);

			// SQL実行
			ResultSet rs = pstm.executeQuery();

			// 結果を1行ずつ取得
			while (rs.next()) {

				// Productオブジェクトを作成
				Product p = new Product();

				// DBの値をProductにセット
				p.setProductCode(rs.getInt("product_code"));
				p.setProductName(rs.getString("product_name"));
				p.setPrice(rs.getInt("price"));

				// リストに追加
				products.add(p);
			}

			// リソースをクローズ
			rs.close();
			pstm.close();
			conn.close();

		} catch (Exception e) {
			// エラー発生時
			e.printStackTrace();
		}

		// 商品リストを返す
		return products;
	}

	/**
	 * 商品名で検索するメソッド
	 */
	public List<Product> findByName(String name) {

		// 商品リスト
		List<Product> products = new ArrayList<>();

		try {
			// JDBCドライバー読み込み
			Class.forName(JDBC_DRIVER_CLASS_NAME);

			// データベース接続
			Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

			// 商品名で検索するSQL
			String sql = "SELECT product_code, product_name, price " + "FROM m_product " + "WHERE product_name LIKE ?";

			// SQL準備
			PreparedStatement pstm = conn.prepareStatement(sql);

			// LIKE検索用のパラメータ設定
			pstm.setString(1, "%" + name + "%");

			// SQL実行
			ResultSet rs = pstm.executeQuery();

			// 結果を1行ずつ取得
			while (rs.next()) {

				// Productオブジェクト作成
				Product product = new Product();

				// DBの値をセット
				product.setProductCode(rs.getInt("product_code"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));

				// リストに追加
				products.add(product);
			}

			// リソースをクローズ
			rs.close();
			pstm.close();
			conn.close();

		} catch (Exception e) {
			// エラー処理
			e.printStackTrace();
		}

		// 検索結果を返す
		return products;
	}
}