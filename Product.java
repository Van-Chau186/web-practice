package model;

/**
 * 商品情報を管理するクラス（モデルクラス）
 */
public class Product {

    // 商品コード
    private int productCode;

    // 商品名
    private String productName;

    // 価格
    private int price;

    /**
     * 商品コードを取得するメソッド
     * @return productCode
     */
    public int getProductCode() {
        return productCode;
    }

    /**
     * 商品コードを設定するメソッド
     * @param productCode 商品コード
     */
    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    /**
     * 商品名を取得するメソッド
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 商品名を設定するメソッド
     * @param productName 商品名
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 価格を取得するメソッド
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * 価格を設定するメソッド
     * @param price 価格
     */
    public void setPrice(int price) {
        this.price = price;
    }
}