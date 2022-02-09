<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String userName = (String)session.getAttribute("userName");
	ArrayList<dto.WorkDTO> wlist = (ArrayList<dto.WorkDTO>)session.getAttribute("wlist");
%>


<!DOCTYPE html>
<html lang="ja">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" href="css/admin/work.css">
	<script src="js/script.js" defer></script>
	<title>社員情報編集画面</title>
</head>
<body class="text-center bg-light">

    <img src="img/logo.png" alt="ロゴマーク">

    <div class="title-info2">
        <p><%= userName %>　さんについて</p>
        <p>日付を入力して</p>
        <p>削除、または変更内容を入力してください。</p>
    </div>


    <table class="table table-success table-striped">
        <tr>
            <th>日付</th>
            <th>出勤</th>
            <th>退勤</th>
            <th>休憩</th>
            <th>休憩終了</th>
        </tr>
        
     <% for(int i = 0; i < wlist.size(); i++) { %>

		<tr>
            <td><%= wlist.get(i).getDate() %></td>
           	<td><%= wlist.get(i).getStartTime() %></td>
           	<td><%= wlist.get(i).getEndTime() %></td>
           	<td><%= wlist.get(i).getBreakTime() %></td>
           	<td><%= wlist.get(i).getEndBreakTime() %></td>
		</tr>

	<% } %>
        
    </table>
    

    <form action="./EditServlet" method="post" class="edit-form">
        
        <div class="edit-box">
            
            <input type="text" name="date" value="" class="form-control" placeholder="日付">
            
            <p>変更場所</p>
            <select name="category" class="form-select" aria-label="Default select example">
                <option value="">-------</option>
                <option value="0">出勤</option>
                <option value="1">退勤</option>
                <option value="2">休憩</option>
                <option value="3">休憩終了</option>
            </select>
            
            <p>変更内容</p>
            <input type="text" name="time" value="" class="form-control">
            
            <p>変更理由</p>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="ダミーダミーダミーダミーダミーダミーダミーダミーダミーダミーダミーダミーダミーダミーダミーダミー"></textarea>
            
        </div>
        
        <button type="submit" class="btn btn-primary rounded-pill" name="send" value="change">変更</button>
        <button type="submit" class="btn btn-danger rounded-pill" name="send" value="delete">削除</button>
    
    	<c:if test="${requestScope.alert != null && requestScope.alert != ''}">
        	<p class="text-danger"><c:out value="${requestScope.alert}"/></p>
        </c:if>
    
    
    </form>
	
	
	
	<p class="cancel"><a href="edit.jsp">戻る</a></p>
	
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