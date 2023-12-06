package gigabox;

public class Theater {
	private int cno;
	private int tno;
	private String tname;
	private int tcost;
	private int sitCount;
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getTcost() {
		return tcost;
	}
	public void setTcost(int tcost) {
		this.tcost = tcost;
	}
	public int getSitCount() {
		return sitCount;
	}
	public void setSitCount(int sitCount) {
		this.sitCount = sitCount;
	}
	
	public void output() {
		// 상영관 정보 출력
		System.out.print("  * 극장 번호: " + tname +", ");
		System.out.print("상영관 번호 : " +  tno +", ");
		System.out.print("상영관 이름: " + tname +",  ");
		System.out.print("관람료: " + tcost +",  ");
		System.out.print("좌석개수: " + sitCount +",  ");
		System.out.println();

	}
}
