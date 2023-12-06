<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*, gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 영화 정보 선택</title>
</head>
<body>
	    <%
    	request.setCharacterEncoding("euc-kr");

    	ResultSet rs = DB.getAllMoviesRS();

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('생성된 영화가 하나도 없습니다.')</script>");
//     		out.print("<script>window.top.location='systemAfteRootLogin.jsp'</script>");
    	} else {
    		request.setAttribute("title", "모든 영화 리스트");

    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    	%>
</body>
</html>