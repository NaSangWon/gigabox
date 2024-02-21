<%@ page contentType="text/html;charset=euc-kr"
         import="java.sql.*, gigabox.*, java.util.Date"  %>
<HTML>
  <HEAD><TITLE>사용자 로그인 처리</TITLE></HEAD>
  
  <BODY>
  	<% DB.loadConnectGigabox(); %>
    <%  
   	   request.setCharacterEncoding("euc-kr");
   	   
	   String ID = request.getParameter("ID");
       String password = request.getParameter("password");
       
       System.out.println("  <<for debug >> loginID: '" + ID + "', password='" + password + "'\n");      
              
       if (ID.equals("") || password.equals("")) {
       		out.print("<script>alert('로그인 아이디와 패스워드가 모두 입력되지 않았습니다.')</script>");
       		
       		out.print("<script>window.top.frames[0].location='topFrameForLogin.html'</script>");
        }
         
		User userLogin = DB.getUser(ID, password);
		
		session.setAttribute("userLogin", userLogin);   // 세션 객체에 로그인한 사용자 객체 userLogin를 이름 "userLogin"로 저장
        
		System.out.println("  <<for debug >> loginID: '" + ID + "', password='" + password + "'\n");      
     
       if (userLogin == null) {
      		out.print("<script>alert('아이디: " + ID + ", 패스워드: " + password + " - 잘못된 아이디 또는 패스워드입니다.')</script>");     
      		
       		out.print("<script>window.top.location='login.html'</script>");

       }
       else {

			System.out.println("  <<for debug >> 로그인한 회원 아이디 : " + userLogin.getID() + "\n");
   
     		session.setAttribute("bankerLogin", userLogin);  // // 세션 객체에 로그인한 은행원 객체 bankerLogin를 이름 "bankerLogin"로 저장
     	
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