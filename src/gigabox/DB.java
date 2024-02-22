package gigabox;

import java.sql.*;
import java.io.IOException;
import java.util.Vector;

/**
 * DB Ŭ����
 *  o MySQL DBMS�� �����ϰ� DB ���̺��� ���� �� �˻��� ���� �޼ҵ� ���� Ŭ����
 *    
 *  o DBMS ����, ���̺� ���� ó�� ��û�� ��� public static �޼ҵ�� �����ǹǷ� 
 *    �ٸ� Ŭ�������� DB Ŭ������ �޼ҵ带 ȣ���� �� �����ڴ� DB
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

	static String URLRemoteMySQL = "jdbc:mysql://xxx.xxx.xxx.xxx:xxxx/"; // TODO: URL ���...

	static {
		driver = driverMySQL;
		dbms = "MySQL";
		URL = URLLocalMySQL;
		database = "gigabox";
	}

	// DEBUG ���� ���� true�̸� debug�� ���� ������ ��µ�
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

	// JDBC ����̹� �ε� �� ����, ���� �����̸� true, ���и� false ��ȯ�ϴ� �޼ҵ�
	public static boolean loadConnectGigabox()  {
		return loadConnect("gigabox");
	}

	// ����̺� �ε� �� �����ϴ� �޼ҵ�
	public static boolean loadConnect(String database)  {
		try {
			// ����̹� �ε�
			Class.forName(driverMySQL);
		} catch ( java.lang.ClassNotFoundException e ) {
			System.err.println("\n  ??? Driver load error in loadConnect(): " + e.getMessage() );
			e.printStackTrace();
		}

		try {
			// �����ϱ� - HSbankJSP �����ͺ��̽��� ����
			con = DriverManager.getConnection(URL + database, "root", "onlyroot");  
			outputForDebug("���� ����: " + URL + database + "�� �����");

			return true;
		} catch( SQLException ex ) {
			System.err.println("\n  ??? Connection error in loadConnect(): " + ex.getMessage() );
			ex.printStackTrace();
		}	   		

		return false;
	}

	// �־��� SQL ���� �����ϴ� �޼ҵ�
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

	// �־��� ���̵�� �н������� ȸ�� Ž���Ͽ� �����ϸ� �ش� ȸ�� ��ü�� ��ȯ�ϴ� �޼ҵ�
	// Ž�� ���н� null ��ȯ
	public static User getUser(String ID, String password) {
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ȸ�� where ȸ�����̵�=? and ��й�ȣ=?;" ;
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
	 * 	  ResultSet ��ü�� banker ���̺��� ������ ����Ǿ� ���� ��, �̸� Banker ��ü�� ��ȯ�ϴ� �޼ҵ�
	 *    ������� root ������� �Ϲ� ������� ������, root ������� ID�� "root"��
	 *    �׷��Ƿ� ������ ID ��Ʈ����Ʈ ���� "root"�̸�  RootBanker ��ü��, �� �ܴ� NormalBanker ��ü�� ��ȯ
	 */	      
	public static User getUserFromRS(ResultSet rs) {  
		User user = null;

		try {
			if (rs.getRow() ==  0)
				return null;

			String ID = rs.getString("ȸ�����̵�");  // ID ��Ʈ����Ʈ ���� ����

			if (ID.equals("root"))           // root ������̸�
				user = new RootUser();   // RootBanker ��ü�� �����Ͽ� banker ������ �����ϰ� ��
			else
				user = new NormalUser(); // �ƴϸ� NormalBanker��ü�� �����Ͽ�  banker ������ �����ϰ� ��

			user.setID( rs.getString("ȸ�����̵�") );  // ResultSet�� ��Ʈ����Ʈ ���� get�Ͽ� �ʵ��� ������ ����
			user.setPassword( rs.getString("��й�ȣ") );
			user.setPhone( rs.getString("�ڵ�����ȣ"));
			user.setBirth( rs.getString("�������"));
			user.setEmail( rs.getString("�̸���"));
		} catch( SQLException ex ) 	    {
			System.err.println("\n  ??? SQL exec error in getBankerFromRS(): " + ex.getMessage() );
		}

		return user;
	}

	// ��� ��ȭ Ž���Ͽ� ResultSet ��ü�� ��ȯ�ϴ� �޼ҵ�
	public static ResultSet getAllMoviesRS() {
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ��ȭ" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ȸ��" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ����" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ���� where ȸ�����̵� = ?" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ���� where ���Ź�ȣ = ?" ;
			outputForDebug("In insertTheater() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, rno);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			res.setMno(rs.getInt("��ȭ��ȣ"));
			res.setCno(rs.getInt("�����ȣ"));
			res.setTno(rs.getInt("�󿵰���ȣ"));
			res.setSitNo(rs.getString("�ڸ���ȣ"));
			res.setID(rs.getString("ȸ�����̵�"));
			res.setScreeningDate(rs.getString("�󿵳�¥"));
			res.setScreeningTime(rs.getString("�󿵽ð�"));
			res.setPeopleCount(rs.getInt("�ο���"));
			res.setPrice(rs.getInt("�����ݾ�"));
			res.setRate(rs.getInt("��������"));
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in getScreeningBySno(): " + ex.getMessage() );
		}
		
		return res;
	}

	public static ResultSet getAllCinemasRS() {
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ����" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ��ȭ" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ��" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from �󿵰�" ;
			outputForDebug("In getAllTheatersRS() SQL : " + sql);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);  

			return rs;

		} catch( SQLException ex ) {             
			System.err.println("\n  ??? SQL exec error in getAllTheatersRS(): " + ex.getMessage() );
		}

		return null;
	}

	// Cinema ��ü�� ����� ���̺� ������ ���÷� �����ϴ� �޼ҵ�
	public static int insertCinema(Cinema cinema) {
		int updateCnt = 0;

		try {                      
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "insert into ���� values (?, ?, ?);" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ��" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ����" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from ������� where ȸ�����̵� = ?" ;
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

	// Theater ��ü�� ����� ���̺� ������ ���÷� �����ϴ� �޼ҵ�
	public static int insertTheater(Theater theater) {
		int updateCnt = 0;

		try {                      
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "insert into �󿵰� values (?, ?, ?, ?, ?);" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "insert into ��ȭ values (?, ?, ?, ?, ?);" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "insert into �� values (NULL, ?, ?, ?, ?, ?, ?, ?);" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select * from �� where �󿵹�ȣ = ?" ;
			outputForDebug("In insertTheater() : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, sno);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			screening.setSno(rs.getInt("�󿵹�ȣ"));
			screening.setMno(rs.getInt("��ȭ��ȣ"));
			screening.setCno(rs.getInt("�����ȣ"));
			screening.setTno(rs.getInt("�󿵰���ȣ"));
			screening.setScreeningDate(rs.getString("�󿵳�¥"));
			screening.setScreeningTime(rs.getString("�󿵽ð�"));
			screening.setRemainingSitCount(rs.getInt("�ܿ��¼���"));
			screening.setLastSit(rs.getInt("�������¼�"));
			
		} catch( SQLException ex ) {

			System.err.println("\n  ??? SQL exec error in getScreeningBySno(): " + ex.getMessage() );
		}
		
		return screening;
	} 
	
	public static void insertReservation(Reservation res) {
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "insert into ����(���Ź�ȣ, ��ȭ��ȣ, �����ȣ, �󿵰���ȣ, �ڸ���ȣ, ȸ�����̵�, �󿵳�¥, �󿵽ð�, �ο���, �����ݾ�, ��������) "
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "insert into �������(��ȭ��ȣ, �����ȣ, �󿵰���ȣ, �ڸ���ȣ, ȸ�����̵�, �󿵳�¥, �󿵽ð�, �ο���, ��ҳ�¥) "
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "update �� set �ܿ��¼��� = �ܿ��¼��� - ?, �������¼� = �������¼� + ? where �󿵹�ȣ = ?" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "delete from ���� where ���Ź�ȣ = ?" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "update ���� set �������� = ? where ���Ź�ȣ = ?";
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "delete from ���� where �����ȣ = ?" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "delete from �󿵰� where �����ȣ = ? and �󿵰���ȣ = ?" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "delete from ��ȭ where ��ȭ��ȣ = ?" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "delete from �� where �󿵹�ȣ = ?" ;
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
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select �����ȣ, �󿵰���ȣ from �� where �󿵹�ȣ = ?" ;
			outputForDebug("In getPrice() in theater selection : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);

			prStmt.setInt(1, screeningNumber);

			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			cno = rs.getInt("�����ȣ");
			tno = rs.getInt("�󿵰���ȣ");
			
		} catch( SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in getPrice() in theater selection : " + ex.getMessage());
			
		}
		
		try {                      
			// SQL ���ǹ��� �����Ѵ�.
			String sql = "select ������ from �󿵰�  where �����ȣ = ? and �󿵰���ȣ = ?" ;
			outputForDebug("In getPrice() in price selection : " + sql);

			PreparedStatement prStmt = con.prepareStatement(sql);
			
			prStmt.setInt(1, cno);
			prStmt.setInt(2, tno);
			
			ResultSet rs = prStmt.executeQuery(); 
			
			rs.next();
			
			price = rs.getInt("������") * ticketCount;
			
			return price;
			
		} catch( SQLException ex ) {
			System.err.println("\n  ??? SQL exec error in getPrice() in price selection : " + ex.getMessage());
			
		}
		
		return -1;
	}
}

