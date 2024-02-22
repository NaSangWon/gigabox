package gigabox;

import java.sql.*;
import java.io.IOException;
import java.util.Vector;

/**
 * DB 클래스
 *  o MySQL DBMS와 연결하고 DB 테이블의 저장 및 검색을 위한 메소드 갖는 클래스
 *    
 *  o DBMS 연결, 테이블에 대한 처리 요청은 모두 public static 메소드로 구현되므로 
 *    다른 클래스에서 DB 클래스의 메소드를 호출할 때 수신자는 DB
 *    
 */

public class DB {
	static  Connection con         = null;

	static String driver;
	public static String dbms;
	static String URL;
	static String database;

	static String driverMySQL = "com.mysql.jdbc.Driver";
	static String URLLocalMySQL = "jdbc:mysql://localhost:3306/";

	static String URLRemoteMySQL = "jdbc:mysql://xxx.xxx.xxx.xxx:xxxx/"; // TODO: URL 명시...

	static {
		driver = driverMySQL;
		dbms = "MySQL";
		URL = URLLocalMySQL;
		database = "gigabox";
	}

	// DEBUG 변수 값이 true이면 debug을 위한 정보가 출력됨
	static boolean DEBUG = false;

	static void outputForDebug(String msg) {
		if (DEBUG)
			System.out.println("  << for debug >> " + msg);		
	}

	public static void setDBMS(String dbmsTo) {
		outputForDebug("in setSBMS(): DBMS = " + dbmsTo); 

		if (dbmsTo.equals("MySQL")){
			driver = driverMySQL;
			dbms = "MySQL";
			URL = URLLocalMySQL;
		}
		else if (dbmsTo.equals("Remote MySQL")){
			driver = driverMySQL;
			dbms = "Remote MySQL";
			URL = URLRemoteMySQL;
		}

		loadConnectGigabox();
	}

	// JDBC 드라이버 로드 및 연결, 연경 성공이면 true, 실패면 false 반환하는 메소드
	public static boolean loadConnectGigabox()  {
		return loadConnect("gigabox");
	}

	// 드라이브 로드 및 연결하는 메소드
	public static boolean loadConnect(String database)  {
		try {
			// 드라이버 로딩
			Class.forName(driverMySQL);
		} catch ( java.lang.ClassNotFoundException e ) {
			System.err.println("\n  ??? Driver load error in loadConnect(): " + e.getMessage() );
			e.printStackTrace();
		}

		try {
			// 연결하기 - HSbankJSP 데이터베이스와 연결
			con = DriverManager.getConnection(URL + database, "root", "onlyroot");  
			outputForDebug("연결 성공: " + URL + database + "에 연결됨");

			return true;
		} catch( SQLException ex ) {
			System.err.println("\n  ??? Connection error in loadConnect(): " + ex.getMessage() );
			ex.printStackTrace();
		}	   		

		return false;
	}

	// 주어진 SQL 문을 실행하는 메소드
	public static void executeAnyQuery(String sql) {
		try {
			Statement stmt = con.createStatement();

			stmt.execute(sql);
		}
		catch(SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in executeAnyQuery(): " + ex.getMessage() );
			ex.printStackTrace();
		}	   
	}

	// 주어진 아이디와 패스워드의 회원 탐색하여 성공하면 해당 회원 객체를 반환하는 메소드
	// 탐색 실패시 null 반환
	public static User getUser(String ID, String password) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 회원 where 회원아이디=? and 비밀번호=?;" ;
			outputForDebug("In getBanker() SQL : " + sql);
			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, ID);
			prStmt.setString(2, password);

			ResultSet rs = prStmt.executeQuery();  
			if (rs.next())  { 
				User user = getUserFromRS(rs);
				return user;
			}			
		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getBanker(): " + ex.getMessage() );
		}

		return null;
	}

	/*
	 * 	  ResultSet 객체에 banker 테이블의 투플이 저장되어 있을 때, 이를 Banker 객체로 변환하는 메소드
	 *    은행원은 root 은행원과 일반 은행원이 있으며, root 은행원의 ID는 "root"임
	 *    그러므로 투플의 ID 애트리뷰트 값이 "root"이면  RootBanker 객체로, 그 외는 NormalBanker 객체로 반환
	 */	      
	public static User getUserFromRS(ResultSet rs) {  
		User user = null;

		try {
			if (rs.getRow() ==  0)
				return null;

			String ID = rs.getString("회원아이디");  // ID 애트리뷰트 값을 저장

			if (ID.equals("root"))           // root 은행원이면
				user = new RootUser();   // RootBanker 객체를 생성하여 banker 변수가 참조하게 함
			else
				user = new NormalUser(); // 아니면 NormalBanker객체를 생성하여  banker 변수가 참조하게 함

			user.setID( rs.getString("회원아이디") );  // ResultSet의 애트리뷰트 값을 get하여 필드의 값으로 저장
			user.setPassword( rs.getString("비밀번호") );
			user.setPhone( rs.getString("핸드폰번호"));
			user.setBirth( rs.getString("생년월일"));
			user.setEmail( rs.getString("이메일"));
		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getBankerFromRS(): " + ex.getMessage() );
		}

		return user;
	}

	// 모든 영화 탐색하여 ResultSet 객체로 반환하는 메소드
	public static ResultSet getAllMoviesRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 영화" ;
			outputForDebug("In getAllBankersRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllBankerRS(): " + ex.getMessage() );
		}

		return null;
	}

	public static int getNoTuplesRS(ResultSet rs) {
		int cnt = 0;
		try {
			rs.last();

			cnt = rs.getRow();

			rs.beforeFirst();

		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getNoTuplesRS : " + ex.getMessage() );
		}
		return cnt;
	}

	public static ResultSet getAllUsersRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 회원" ;
			outputForDebug("In getAllUsersRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllUsersRS(): " + ex.getMessage() );
		}

		return null;
	}

	public static ResultSet getAllReservationsRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 예매" ;
			outputForDebug("In getAllReservationsRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllReservationsRS(): " + ex.getMessage() );
		}

		return null;
	}
	
	public static ResultSet getAllReservationsByID(String ID) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 예매 where 회원아이디 = ?" ;
			outputForDebug("In getAllReservationsByID() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, ID);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			return rs;
			
		} catch( SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in getAllReservationsByID(): " + ex.getMessage());
			
			return null;
		}
		
	}
	
	public static Reservation getReservation(int rno) {
		Reservation res = new Reservation();
		
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 예매 where 예매번호 = ?" ;
			outputForDebug("In insertTheater() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, rno);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			res.setMno(rs.getInt("영화번호"));
			res.setCno(rs.getInt("극장번호"));
			res.setTno(rs.getInt("상영관번호"));
			res.setSitNo(rs.getString("자리번호"));
			res.setID(rs.getString("회원아이디"));
			res.setScreeningDate(rs.getString("상영날짜"));
			res.setScreeningTime(rs.getString("상영시각"));
			res.setPeopleCount(rs.getInt("인원수"));
			res.setPrice(rs.getInt("결제금액"));
			res.setRate(rs.getInt("평점점수"));
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in getScreeningBySno(): " + ex.getMessage() );
		}
		
		return res;
	}

	public static ResultSet getAllCinemasRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 극장" ;
			outputForDebug("In getAllCinemasRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllCinemasRS(): " + ex.getMessage() );
		}

		return null;
	}

	public static ResultSet getAllMovieRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 영화" ;
			outputForDebug("In getAllMovieRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllMovieRS(): " + ex.getMessage() );
		}

		return null;
	}

	public static ResultSet getAllMovieShowRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 상영" ;
			outputForDebug("In getAllMovieShowRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllMovieShowRS(): " + ex.getMessage() );
		}

		return null;
	}

	public static ResultSet getAllTheatersRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 상영관" ;
			outputForDebug("In getAllTheatersRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllTheatersRS(): " + ex.getMessage() );
		}

		return null;
	}

	// Cinema 객체를 은행원 테이블 극장의 투플로 삽입하는 메소드
	public static int insertCinema(Cinema cinema) {
		int updateCnt = 0;

		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into 극장 values (?, ?, ?);" ;
			outputForDebug("In insertCinema() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, cinema.getCno());
			prStmt.setString(2, cinema.getCaddress());
			prStmt.setString(3, cinema.getCname());

			updateCnt = prStmt.executeUpdate();  		
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertCinema(): " + ex.getMessage() );
		}

		return updateCnt;
	}

	public static ResultSet getMakeReservationsRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 상영" ;
			outputForDebug("In getMakeReservationsRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getMakeReservationsRS(): " + ex.getMessage() );
		}

		return null;
	}

	public static ResultSet getAllReservationUsersRS() {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 예매" ;
			outputForDebug("In getAllReservationUsersRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllReservationUsersRS(): " + ex.getMessage() );
		}

		return null;
	}

	public static ResultSet getCancelReservationRS(String ID) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 예매취소 where 회원아이디 = ?" ;
			outputForDebug("In getCancelReservationRS() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setString(1, ID);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			return rs;
			
		} catch( SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in getCancelReservationRS(): " + ex.getMessage());
			
			return null;
		}
	}

	// Theater 객체를 은행원 테이블 극장의 투플로 삽입하는 메소드
	public static int insertTheater(Theater theater) {
		int updateCnt = 0;

		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into 상영관 values (?, ?, ?, ?, ?);" ;
			outputForDebug("In insertTheater() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, theater.getCno());
			prStmt.setInt(2, theater.getTno());
			prStmt.setString(3, theater.getTname());
			prStmt.setInt(4, theater.getTcost());
			prStmt.setInt(5, theater.getSitCount());

			updateCnt = prStmt.executeUpdate();        
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertTheater(): " + ex.getMessage() );
		}

		return updateCnt;
	}

	public static int insertMovie(movie movie) {
		int updateCnt = 0;

		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into 영화 values (?, ?, ?, ?, ?);" ;
			outputForDebug("In insertMovie() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, movie.getMno());
			prStmt.setString(2, movie.getMname());
			prStmt.setInt(3, movie.getMruntime());
			prStmt.setString(4, movie.getMrelease());
			prStmt.setInt(5, movie.getMattendance());

			updateCnt = prStmt.executeUpdate();        
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertMovie(): " + ex.getMessage() );
		}

		return updateCnt;
	}
	
	public static int insertScreening(Screening screening) {
		int updateCnt = 0;

		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into 상영 values (NULL, ?, ?, ?, ?, ?, ?, ?);" ;
			outputForDebug("In insertScreening() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, screening.getMno());
			prStmt.setInt(2, screening.getCno());
			prStmt.setInt(3, screening.getTno());
			prStmt.setString(4, screening.getScreeningDate());
			prStmt.setString(5, screening.getScreeningTime());
			prStmt.setInt(6, screening.getRemainingSitCount());
			prStmt.setInt(7, screening.getLastSit());

			updateCnt = prStmt.executeUpdate();        
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertScreening(): " + ex.getMessage() );
		}

		return updateCnt;
	}
	
	public static Screening getScreeningBySno(int sno) {
		Screening screening = new Screening();
		
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select * from 상영 where 상영번호 = ?" ;
			outputForDebug("In insertTheater() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, sno);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			screening.setSno(rs.getInt("상영번호"));
			screening.setMno(rs.getInt("영화번호"));
			screening.setCno(rs.getInt("극장번호"));
			screening.setTno(rs.getInt("상영관번호"));
			screening.setScreeningDate(rs.getString("상영날짜"));
			screening.setScreeningTime(rs.getString("상영시각"));
			screening.setRemainingSitCount(rs.getInt("잔여좌석수"));
			screening.setLastSit(rs.getInt("마지막좌석"));
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in getScreeningBySno(): " + ex.getMessage() );
		}
		
		return screening;
	} 
	
	public static void insertReservation(Reservation res) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into 예매(예매번호, 영화번호, 극장번호, 상영관번호, 자리번호, 회원아이디, 상영날짜, 상영시각, 인원수, 결제금액, 평점점수) "
					+ "values(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, NULL)" ;
			outputForDebug("In insertReservation() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, res.getMno());
			prStmt.setInt(2, res.getCno());
			prStmt.setInt(3, res.getTno());
			prStmt.setString(4, res.getSitNo());
			prStmt.setString(5, res.getID());
			prStmt.setString(6, res.getScreeningDate());
			prStmt.setString(7, res.getScreeningTime());
			prStmt.setInt(8, res.getPeopleCount());
			prStmt.setInt(9, res.getPrice());
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertReservation(): " + ex.getMessage() );
		}
	}
	
	public static void insertCancelRservation(Reservation res) {
		CancelReservation cres = new CancelReservation(res);
		cres.output();
		
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "insert into 예매취소(영화번호, 극장번호, 상영관번호, 자리번호, 회원아이디, 상영날짜, 상영시각, 인원수, 취소날짜) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
			outputForDebug("In insertCancelRservation() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, cres.getMno());
			prStmt.setInt(2, cres.getCno());
			prStmt.setInt(3, cres.getTno());
			prStmt.setString(4, cres.getSitNo());
			prStmt.setString(5, cres.getID());			
			prStmt.setString(6, cres.getScreeningDate()); 
			prStmt.setString(7, cres.getScreeningTime());
			prStmt.setInt(8, cres.getPeopleCount());
			prStmt.setString(9, cres.getCancelDate());
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in insertCancelRservation(): " + ex.getMessage() );
		}
	}
	
	public static void updateScreening(int ticketCount, int sno) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "update 상영 set 잔여좌석수 = 잔여좌석수 - ?, 마지막좌석 = 마지막좌석 + ? where 상영번호 = ?" ;
			outputForDebug("In updateScreening() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, ticketCount);
			prStmt.setInt(2, ticketCount);
			prStmt.setInt(3, sno);
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in updateScreening(): " + ex.getMessage() );
		}
	}
	
	public static void deleteReservation(int rno) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "delete from 예매 where 예매번호 = ?" ;
			outputForDebug("In deleteReservation() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, rno);
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in deleteReservation(): " + ex.getMessage() );
		}
	}
	
	public static void rate(int rno, int rate) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "update 예매 set 평점점수 = ? where 예매번호 = ?";
			outputForDebug("In rate() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, rate);
			prStmt.setInt(2, rno);
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in rate(): " + ex.getMessage() );
		}
	}
	
	public static void deleteCinema(int cno) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "delete from 극장 where 극장번호 = ?" ;
			outputForDebug("In deleteCinema() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, cno);
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in deleteCinema(): " + ex.getMessage() );
		}
	}
	
	public static void deleteTheater(int cno, int tno) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "delete from 상영관 where 극장번호 = ? and 상영관번호 = ?" ;
			outputForDebug("In deleteTheater() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, cno);
			prStmt.setInt(2, tno);
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in deleteTheater(): " + ex.getMessage() );
		}
	}
	
	public static void deleteMovie(int mno) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "delete from 영화 where 영화번호 = ?" ;
			outputForDebug("In deleteMovie() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, mno);
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in deleteMovie(): " + ex.getMessage() );
		}
	}
	
	public static void deleteScreening(int sno) {
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "delete from 상영 where 상영번호 = ?" ;
			outputForDebug("In deleteScreening() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, sno);
			
			prStmt.executeUpdate();
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in deleteScreening(): " + ex.getMessage() );
		}
	}
	
	public static int getPrice(int screeningNumber, int ticketCount) {
		int price;
		int cno = -1;
		int tno = -1;
		
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select 극장번호, 상영관번호 from 상영 where 상영번호 = ?" ;
			outputForDebug("In getPrice() in theater selection : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, screeningNumber);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			cno = rs.getInt("극장번호");
			tno = rs.getInt("상영관번호");
			
		} catch( SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in getPrice() in theater selection : " + ex.getMessage());
			
		}
		
		try {                      
			// SQL 질의문을 수행한다.
			String sql = "select 관람료 from 상영관  where 극장번호 = ? and 상영관번호 = ?" ;
			outputForDebug("In getPrice() in price selection : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, cno);
			prStmt.setInt(2, tno);
			
			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			price = rs.getInt("관람료") * ticketCount;
			
			return price;
			
		} catch( SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in getPrice() in price selection : " + ex.getMessage());
			
		}
		
		return -1;
	}
}

