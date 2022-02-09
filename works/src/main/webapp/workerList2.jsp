<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.ArrayList" %>


<% 
	ArrayList<dto.WorkDTO> wlist = (ArrayList<dto.WorkDTO>)request.getAttribute("wlist");
	ArrayList<dto.UserDTO> ulist = (ArrayList<dto.UserDTO>)request.getAttribute("ulist");
%>

<fmt:formatDate var="today" value="${date}" pattern="yyyy-MM-dd" />


<%!
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
	String date = d.format(calendar.getTime());
%>

<% 
	ArrayList<String> category1 = new ArrayList<>();
	ArrayList<String> category2 = new ArrayList<>();

%>



<%-- <jsp:useBean id="ulist" scope="request" type="java.util.ArrayList<dto.UserDTO>"/> --%>
<%-- <jsp:useBean id="wlist" scope="request" type="java.util.ArrayList<dto.WorkDTO>"/> --%>
<%-- <jsp:useBean id="now" scope="request" type="java.util.ArrayList<String>"/> --%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/worker/top.css">
    <script src="<%=request.getContextPath() %>/js/script.js" defer></script>
    <title>社員メニュー画面</title>
</head>
<body class="text-center bg-light">

    <div>
        <img src="<%=request.getContextPath() %>/img/logo.png" alt="ロゴマーク">
        <p id="item"></p>
    </div>
    <div class="border rounded bg-white menu">
        <h1>従業員一覧</h1>
        


        <table class="table table-bordered ">
            <thead>
                <tr>
                    <th scope="col">状況</th>
                    <th scope="col">従業員名</th>
                </tr>
            </thead>
            <tbody>
            
            	<% for(int i = 0; i < ulist.size(); i++) { %>
                	
                	<tr>
                		<td>
                		
                		<%
                			String message = null;
                			if(!wlist.get(i).getDate().equals(date)) {
                				message = "退勤";
                			} else if(wlist.get(i).getEndTime() != null) {
                				message = "退勤";
                			} else if(wlist.get(i).getEndBreakTime() != null) {
                				message = "出勤";
                			} else if(wlist.get(i).getBreakTime() != null) {
                				message = "休憩";
                			} else if(wlist.get(i).getStartTime() != null) {
                				message = "出勤";
                			} else {
                				message = "退勤";
                			}
                		
                		%>
                		
                		
                		<%= message %>
                		</td>
                		<td><%= ulist.get(i).getName() %></td>
                	
                	</tr>
                	
                <% } %>
                
            </tbody>
        </table>
        
    </div>
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