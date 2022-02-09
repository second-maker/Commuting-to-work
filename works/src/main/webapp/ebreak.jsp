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
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/worker/work.css">
	<script src="<%=request.getContextPath() %>/js/script.js" defer></script>
	<title>従業員休憩終了画面</title>
</head>
<body class="text-center bg-light">

    <img src="<%=request.getContextPath() %>/img/logo.png" alt="ロゴマーク">

    <h1 class="text-success">休憩終了画面</h1>
    <p id="item"></p>

    <form action="./EBreakWork" method="post" class="border rounded bg-white login-form">
        <div class="mb-3">
            <input type="text" name="userId" value="" class="form-control rounded-pill" placeholder="ユーザーID">
        </div>
        <div class="mb-3">
            <input type="password" name="password" value="" class="form-control rounded-pill" placeholder="パスワード">
        </div>
        <button type="submit" class="btn btn-success rounded-pill">休憩を終了する</button>
        
        <c:if test="${requestScope.alert != null && requestScope.alert != ''}">
        	<p class="text-danger alert"><c:out value="${requestScope.alert}"/></p>
        </c:if>
        
    </form>
    
	<p class="cancel"><a href="top.jsp">戻る</a></p>
	
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