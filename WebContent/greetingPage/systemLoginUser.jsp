<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*, gigabox.*, java.util.Date"  %>
<HTML>
  <HEAD><TITLE>����� �α��� ó��</TITLE></HEAD>
  
  <BODY>
  	<% DB.loadConnectGigabox(); %>
    <%  
   	   request.setCharacterEncoding("euc-kr");
   	   
	   String ID = request.getParameter("ID");
       String password = request.getParameter("password");
       
       System.out.println("  <<for debug >> loginID: '" + ID + "', password='" + password + "'\n");      
              
       if (ID.equals("") || password.equals("")) {
       		out.print("<script>alert('�α��� ���̵�� �н����尡 ��� �Էµ��� �ʾҽ��ϴ�.')</script>");
       		
       		out.print("<script>window.top.frames[0].location='topFrameForLogin.html'</script>");
        }
         
		User userLogin = DB.getUser(ID, password);
		
		session.setAttribute("userLogin", userLogin);   // ���� ��ü�� �α����� ����� ��ü userLogin�� �̸� "userLogin"�� ����
        
		System.out.println("  <<for debug >> loginID: '" + ID + "', password='" + password + "'\n");      
     
       if (userLogin == null) {
      		out.print("<script>alert('���̵�: " + ID + ", �н�����: " + password + " - �߸��� ���̵� �Ǵ� �н������Դϴ�.')</script>");     
      		
       		out.print("<script>window.top.location='login.html'</script>");

       }
       else {

			System.out.println("  <<for debug >> �α����� ȸ�� ���̵� : " + userLogin.getID() + "\n");
   
     		session.setAttribute("bankerLogin", userLogin);  // // ���� ��ü�� �α����� ����� ��ü bankerLogin�� �̸� "bankerLogin"�� ����
     	
     		if (userLogin instanceof RootUser){
     			out.print("<script>window.top.location='../adminPage/index.html'</script>"); 
      		 }
       		else if (userLogin instanceof NormalUser){
     			out.print("<script>window.top.location='../userPage/index.html'</script>"); 
       		}
       }
    %>
    
  </BODY>
</HTML>