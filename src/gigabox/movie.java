package gigabox;

public class movie {
	private int mno;
	private String mrelease;
	private String mname;
	private int mattendance;
	private int mruntime;
	
	public int getMno()	{
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	
	public String getMrelease() {
		return mrelease;
	}
	public void setMrelease(String mrelease) {
		this.mrelease = mrelease;
	}
	
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	
	public int getMattendance() {
		return mattendance;
	}
	public void setMattendance(int mattendance) {
		this.mattendance = mattendance;
	}
	
	public int getMruntime() {
		return mruntime;
	}
	public void setMruntime(int mruntime) {
		this.mruntime = mruntime;
	}
	
	public void output() {
		// 영화 정보 출력
		System.out.print("  * 영화 번호: " + mname +",  ");
		System.out.print("영화 제목: " + mno +", ");
		System.out.print("런타임: " + mruntime +",  ");
		System.out.print("개봉일: " +  mrelease +", ");
		System.out.print("관객수: " + mattendance +",  ");
		System.out.println();
	}

}
