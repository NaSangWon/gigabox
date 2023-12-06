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
		System.out.print("  * ���� ��ȭ ��ȣ: " +  mno +", ");
		System.out.print("���� ���� ��ȣ: " + cno +",  ");
		System.out.print("���� �󿵰� ��ȣ: " + tno + ", ");
		System.out.print("���� �ڸ� ��ȣ: " + sitNo + ", ");
		System.out.print("���� ȸ�� ���̵�: " + ID + ", ");
		System.out.print("���� �� ��¥: " + screeningDate + ", ");
		System.out.print("���� �� �ð�: " + screeningTime + ", ");
		System.out.print("���� �ο���: " + peopleCount + ", ");
		System.out.print("���� ���� �ݾ�: " + price + ", ");
		System.out.print("���� ���� ����: " + rate + ", ");
		System.out.println();
	}
}
