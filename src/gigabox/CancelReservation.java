package gigabox;

import java.time.LocalDate;

public class CancelReservation {
	private int mno;
	private int cno;
	private int tno;
	private String sitNo;
	private String ID;
	private String screeningDate;
	private String screeningTime;
	private int peopleCount;
	private String cancelDate;
	
	public CancelReservation(Reservation res) {
		this.mno = res.getMno();
		this.cno = res.getCno();
		this.tno = res.getTno();
		this.sitNo = res.getSitNo();
		this.ID = res.getID();
		this.screeningDate = res.getScreeningDate();
		this.screeningTime = res.getScreeningTime();
		this.peopleCount = res.getPeopleCount();
		
		LocalDate nowDate = LocalDate.now();
		this.cancelDate = nowDate.toString();
	}
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}
	public String getScreeningDate() {
		return screeningDate;
	}
	public void setScreeningDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}
	public String getScreeningTime() {
		return screeningTime;
	}
	public void setScreeningTime(String screeningTime) {
		this.screeningTime = screeningTime;
	}
	public String getSitNo() {
		return sitNo;
	}
	public void setSitNo(String sitNo) {
		this.sitNo = sitNo;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	
	public void output() {
		System.out.print("  * 예매 취소 영화 번호: " +  mno +", ");
		System.out.print("예매 취소 극장 번호: " + cno +",  ");
		System.out.print("예매 취소 상영관 번호: " + tno + ", ");
		System.out.print("예매 취소 자리 번호: " + sitNo + ", ");
		System.out.print("예매 취소 회원 아이디: " + ID + ", ");
		System.out.print("예매 취소 상영 날짜: " + screeningDate + ", ");
		System.out.print("예매 취소 상영 시각: " + screeningTime + ", ");
		System.out.print("예매 취소 인원수: " + peopleCount + ", ");
		System.out.print("예매 취소 날짜: " + cancelDate + ", ");
		System.out.println();
	}
}
