package gigabox;

public class Screening {
	private int sno;
	private int mno;
	private int cno;
	private int tno;
	private String screeningDate;
	private String screeningTime;
	private int remainingSitCount;
	private int lastSit;
	
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getSno() {
		return sno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getMno() {
		return mno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getCno() {
		return cno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public int getTno() {
		return tno;
	}
	public void setScreeningDate(String screeningDate) {
		this.screeningDate = screeningDate;
	}
	public String getScreeningDate() {
		return screeningDate;
	}
	public void setScreeningTime(String screeningTime) {
		this.screeningTime = screeningTime;
	}
	public String getScreeningTime() {
		return screeningTime;
	}
	public void setRemainingSitCount(int remainingSitCount) {
		this.remainingSitCount = remainingSitCount;
	}
	public int getRemainingSitCount() {
		return remainingSitCount;
	}
	public void setLastSit(int lastSit) {
		this.lastSit = lastSit;
	}
	public int getLastSit() {
		return lastSit;
	}
	
	public void output() {
		// 상영 정보 출력
		System.out.print("  * 상영 번호: " + sno +", ");
		System.out.print("영화 번호: " +  mno +", ");
		System.out.print("극장 번호: " + cno +",  ");
		System.out.print("상영관 번호: " + tno + ", ");
		System.out.print("상영 날짜: " + screeningDate + ", ");
		System.out.print("상영 시각: " + screeningTime + ", ");
		System.out.print("잔여좌석수: " + remainingSitCount + ", ");
		System.out.print("마지막좌석: " + lastSit + ", ");
		System.out.println();

	}
}
