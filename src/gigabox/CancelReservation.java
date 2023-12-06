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
		System.out.print("  * ���� ��� ��ȭ ��ȣ: " +  mno +", ");
		System.out.print("���� ��� ���� ��ȣ: " + cno +",  ");
		System.out.print("���� ��� �󿵰� ��ȣ: " + tno + ", ");
		System.out.print("���� ��� �ڸ� ��ȣ: " + sitNo + ", ");
		System.out.print("���� ��� ȸ�� ���̵�: " + ID + ", ");
		System.out.print("���� ��� �� ��¥: " + screeningDate + ", ");
		System.out.print("���� ��� �� �ð�: " + screeningTime + ", ");
		System.out.print("���� ��� �ο���: " + peopleCount + ", ");
		System.out.print("���� ��� ��¥: " + cancelDate + ", ");
		System.out.println();
	}
}
