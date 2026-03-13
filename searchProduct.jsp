<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Listクラスを使用するためのインポート -->
<%@ page import="java.util.List"%>

<!-- ArrayListクラスを使用するためのインポート -->
<%@ page import="java.util.ArrayList"%>

<!-- Productクラスを使用するためのインポート -->
<%@ page import="model.Product"%>

<%
/* ===============================
   Servletから送られてきたデータ取得
   =============================== */

// 商品リストを取得
List<Product> products = (List<Product>) request.getAttribute("products");

// productsがnullの場合は空のリストを作成
if (products == null) {
	products = new ArrayList<Product>();
}

// 検索した商品名を取得
String productName = (String) request.getAttribute("productName");

// productNameがnullの場合は空文字にする
if (productName == null) {
	productName = "";
}
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品検索</title>

<style>

/* ===============================
   画面全体のデザイン
   =============================== */
body {
	font-family: Arial, sans-serif;
}

/* 画面全体の枠 */
.container {
	width: 700px;
	border: 2px solid #333;
	padding: 20px;
}

/* タイトル部分 */
.title {
	font-size: 28px;
	margin-bottom: 20px;
}

/* 検索エリア */
.search-area {
	margin-bottom: 20px;
}

.search-area label {
	margin-right: 10px;
}

/* 商品名入力欄 */
.search-area input {
	width: 300px;
	height: 25px;
}

/* 検索ボタン */
.search-area button {
	height: 30px;
	width: 70px;
	margin-left: 10px;
	background-color: #c9d7e8;
	border: 1px solid #333;
}

/* テーブルの設定 */
table {
	border-collapse: collapse;
	width: 100%;
}

/* セルのデザイン */
th, td {
	border: 1px solid #333;
	padding: 8px;
	text-align: center;
}

/* ヘッダー背景色 */
th {
	background-color: #eee;
}

/* 編集ボタン */
.edit-btn {
	background-color: #c9d7e8;
	border: 1px solid #333;
	padding: 4px 10px;
}

/* エラーメッセージ */
.error-message {
	color: red;
	font-size: 12px;
	margin-top: 5px;
	display: none;
}
</style>

<script>
	/* ===============================
	 商品名の文字数チェック
	 50文字以上ならエラー表示
	 =============================== */

	function checkLength() {

		// 入力された商品名を取得
		var text = document.getElementById("productName").value;

		// エラーメッセージの要素取得
		var error = document.getElementById("errorMessage");

		// 文字数チェック
		if (text.length > 50) {
			error.style.display = "block"; // エラー表示
		} else {
			error.style.display = "none"; // 非表示
		}
	}
</script>

</head>

<body>

	<div class="container">

		<!-- 画面タイトル -->
		<div class="title">商品検索</div>

		<!-- ===============================
		     商品検索フォーム
		     =============================== -->
		<form action="SearchProduct" method="get">

			<div class="search-area">

				<label>商品名</label>

				<!-- 商品名入力 -->
				<input type="text" name="productName" id="productName"
					placeholder="商品名を入力してください" value="<%=productName%>"
					oninput="checkLength()">

				<!-- 検索ボタン -->
				<button type="submit">検索</button>

				<!-- エラーメッセージ -->
				<p id="errorMessage" class="error-message">50文字以内で入力してください</p>

			</div>

		</form>

		<!-- 検索結果件数 -->
		<p>
			件数:
			<%=products.size()%>
		</p>

		<!-- ===============================
		     商品一覧テーブル
		     =============================== -->

		<table>

			<thead>
				<tr>
					<th>商品コード</th>
					<th>商品名</th>
					<th>単価</th>
					<th>操作</th>
				</tr>
			</thead>

			<tbody>

				<%
				// 商品リストを1件ずつ表示
				for (Product product : products) {
				%>

				<tr>

					<!-- 商品コード -->
					<td><%=product.getProductCode()%></td>

					<!-- 商品名 -->
					<td><%=product.getProductName()%></td>

					<!-- 単価 -->
					<td><%=product.getPrice()%></td>

					<!-- 編集ボタン -->
					<td><a href="modifyOrDeleteProduct.jsp">
							<button type="button" class="edit-btn">編集</button>
					</a></td>

				</tr>

				<%
				}
				%>

			</tbody>

		</table>

	</div>

</body>
</html>