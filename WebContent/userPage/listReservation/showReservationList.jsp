<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*, gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� ���� ���</title>
</head>
<body>
	<h3 align=center>�� ���</h3>
			<%
		    	request.setCharacterEncoding("euc-kr");
				
				User userLogin = (User) session.getAttribute("userLogin");
			
		    	ResultSet rs = DB.getAllReservationsByID(userLogin.getID());
		
		    	int cnt = DB.getNoTuplesRS(rs);
		
		    	if (cnt == 0) {
		    		out.print("<script>alert('���� ������ �����ϴ�.')</script>");
		    		out.print("<script>window.top.location='../index.html'</script>");
		    	} else {
		    		request.setAttribute("title", "ȸ������ ���� ����");
		    		request.setAttribute("RS", rs);
		    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
		    	}
		    %>
</body>
</html>