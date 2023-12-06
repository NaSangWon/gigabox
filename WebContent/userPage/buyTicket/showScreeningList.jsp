<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*, gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상영 정보 출력과 선택</title>
</head>
<body>
	<h3 align=center>상영 목록</h3>
			<%
		    	request.setCharacterEncoding("euc-kr");
		
		    	ResultSet rs = DB.getAllMovieShowRS();
		
		    	int cnt = DB.getNoTuplesRS(rs);
		
		    	if (cnt == 0) {
		    		out.print("<script>alert('상영 예정이 없습니다.')</script>");
		    		out.print("<script>window.top.location='menuForLogin.jsp'</script>");
		    	} else {
		    		request.setAttribute("title", "상영시간표");
		    		request.setAttribute("RS", rs);
		    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
		    	}
		    %>
</body>
</html>