<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<% 
	ArrayList<dto.UserDTO> ulist = (ArrayList<dto.UserDTO>)session.getAttribute("ulist");
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
	<title>社員選択画面</title>
</head>
<body class="text-center bg-light">

    <img src="img/logo.png" alt="ロゴマーク">

    <div class="title-info2">
        <p>給与計算したい</p>
        <p>社員を選択してください。</p>
    </div>

    <form action="./CalcUserServlet" method="post" class="border rounded bg-white select-form">
        
        <table class="table edit-table">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">氏名</th>
                </tr>
            </thead>
            <tbody>
            	
            	<% for(int i = 0; i < ulist.size(); i++) { %>
            		
            		<tr>
            			<th scope="row"><%= (i+1) %></th>
            			<td><%= ulist.get(i).getName() %></td>
            		</tr>
            		
            		
            	<%} %>
            
            </tbody>
        </table>
        
        <p style="font-weight: bold; margin-top: 40px;">名前</p>
        <input type="text" name="userName" value="" class="form-control">

        <button type="submit" class="btn btn-primary rounded-pill">決定</button>
        
        <c:if test="${requestScope.alert != null && requestScope.alert != ''}">
        	<p class="text-danger"><c:out value="${requestScope.alert}"/></p>
        </c:if>

    </form>



	<p class="cancel"><a href="menu.jsp">戻る</a></p>
	
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