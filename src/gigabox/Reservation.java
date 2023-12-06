package gigabox;

public class Reservation {
	private int mno;
	private int cno;
	private int tno;
	private String sitNo;
	private String ID;
	private String screeningDate;
	private String screeningTime;
	private int peopleCount;
	private int price = 0;
	private int rate;
	
	public Reservation() {
		
	}
	
	public Reservation(Screening sc) {
		this.mno = sc.getMno();
		this.cno = sc.getCno();
		this.tno = sc.getTno();
		this.screeningDate = sc.getScreeningDate();
		this.screeningTime = sc.getScreeningTime();
	}
	
	public void init(Screening sc, int ticketCount, String ID) {
		this.peopleCount = ticketCount;
		
		StringBuilder str = new StringBuilder();
		int lastSitNo = sc.getLastSit();
		
		for (; ticketCount > 0 ; ticketCount--) {
			str.append(++lastSitNo);
			str.append(" ");
		}
		
		this.sitNo = str.toString();
		this.ID = ID;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public void output() {
		System.out.print("  * 예매 영화 번호: " +  mno +", ");
		System.out.print("예매 극장 번호: " + cno +",  ");
		System.out.print("예매 상영관 번호: " + tno + ", ");
		System.out.print("예매 자리 번호: " + sitNo + ", ");
		System.out.print("예매 회원 아이디: " + ID + ", ");
		System.out.print("예매 상영 날짜: " + screeningDate + ", ");
		System.out.print("예매 상영 시각: " + screeningTime + ", ");
		System.out.print("예매 인원수: " + peopleCount + ", ");
		System.out.print("예매 결제 금액: " + price + ", ");
		System.out.print("예매 평점 점수: " + rate + ", ");
		System.out.println();
	}
}
