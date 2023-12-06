<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.sql.*, gigabox.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table align=center border=1 cellpadding=20>
		<tr>
			<td> 
			<% 
		    	ResultSet rs = DB.getAllMovieShowRS();  // user에 저장된 사용자 정보를 user 테이블에 삽입


				request.setAttribute("title", "예약하고자 하는 상영 선택");
				request.setAttribute("RS", rs);
				
			    request.setAttribute("attributeToChoose", "학번");
		 	    request.setAttribute("pageToMove", "Ex3_2_ListCounselInfo.jsp");		
				
				request.getRequestDispatcher("ListRsAddMoveButton.jsp").forward(request, response);			
			%>
					   
			</td>
		</tr>
	</table>
</body>
</html>