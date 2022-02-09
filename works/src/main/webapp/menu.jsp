<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" href="css/admin/menu.css">
	<script src="js/script.js" defer></script>
	<title>管理者メニュー画面</title>
</head>
<body class="text-center bg-light">

	<jsp:useBean id="admin" scope="session" type="dto.AdminDTO"/>

    <p class="login-name">ようこそ　<c:out value="${admin.name}"/>さん</p>

    <div>
        <img src="img/logo.png" alt="ロゴマーク">
    </div>
    <div class="border rounded bg-white menu">
        <h1>勤怠管理システム</h1>

        <p id="item"></p>
        <div class="form">
            <div class="menu-category menu-form">
                <button type="button" class="btn btn-secondary btn-lg"><a href="register.jsp" class="text-white">従業員新規登録</a></button>
                <form action="./EditListServlet" method="get">
                	<button type="submit" class="btn btn-secondary btn-lg">編集</button>
                </form>
            </div>
            
            <div class="menu-category menu-form">
                <form action="./CalcListServlet" method="get">
                	<button type="submit" class="btn btn-secondary btn-lg">給与計算</button>
                </form>
                <form action="./WorkerList2" method="get">
                	<button type="submit" class="btn btn-secondary btn-lg">従業員状況</button>
                </form>
            </div>

            <div class="menu-category menu-form">
                <form action="./CalcTableServlet" method="get">
                	<button type="submit" class="btn btn-secondary btn-lg">給与一覧</button>
                </form>
                <button type="button" class="btn btn-secondary btn-lg"><a href="#" class="text-white">ダミー</a></button>
            </div>
        </div>
    
        
        
    </div>
    
    <form action="./LogoutServlet" method="post">
		<button type="submit" class="logout">ログアウト</button>
	</form>
    
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
</body>
</html>