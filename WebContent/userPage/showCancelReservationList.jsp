<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*, gigabox.*, java.util.Date"  %>

<HTML>
    <HEAD><TITLE>��ҳ��� ��ȸ</TITLE></HEAD>

    <BODY>
	<%
    	request.setCharacterEncoding("euc-kr");
		
		User userLogin = (User) session.getAttribute("userLogin");

    	ResultSet rs = DB.getCancelReservationRS(userLogin.getID());

    	int cnt = DB.getNoTuplesRS(rs);

    	if (cnt == 0) {
    		out.print("<script>alert('���� ��� ������ �ϳ��� �����ϴ�.')</script>");
    		out.print("<script>window.top.location=index.html</script>");
    	} else {
    		request.setAttribute("title", "���� ��� ����Ʈ");
    		request.setAttribute("RS", rs);
    		request.getRequestDispatcher("listRS.jsp").forward(request, response);
    	}
    %> 
     </BODY>
</HTML>