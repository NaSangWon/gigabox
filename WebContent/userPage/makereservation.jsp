<%@ page contentType="text/html;charset=utf-8"
	import="java.sql.*, gigabox.*, java.util.Date""%>
<html>
<head>
<title>상영시간표에서 선택</title>
</head>

<body>
	<br>
	<h3 align=center>원하는 영화 선택</h3>
	<table align=center border=1 cellpadding=20>
		<tr>
			<td> 		
<% DB.loadConnectGigabox(); %>
<%
    	request.setCharacterEncoding("euc-kr");

    	ResultSet rs = DB.getMakeReservationsRS();

    	int cnt = DB.getNoTuplesRS(rs);

    	request.setAttribute("title", "원하는 영화 선택");
		request.setAttribute("RS", rs);
		
 	    request.setAttribute("pageToMove", "viewreservationlist.jsp");		
		request.getRequestDispatcher("ListRsAddMoveButton.jsp").forward(request, response);	
%>
	<table align="center" style="margin : auto;">
		<tr>
			<td>평점</td><td><input type="text" name="ratenum"></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		
	</table>
	<div align="center">
		<input type="submit" value="평점등록">
	</div>		   
			</td>
		</tr>
	</table>

</body>
</html>
