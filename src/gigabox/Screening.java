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
		// �� ���� ���
		System.out.print("  * �� ��ȣ: " + sno +", ");
		System.out.print("��ȭ ��ȣ: " +  mno +", ");
		System.out.print("���� ��ȣ: " + cno +",  ");
		System.out.print("�󿵰� ��ȣ: " + tno + ", ");
		System.out.print("�� ��¥: " + screeningDate + ", ");
		System.out.print("�� �ð�: " + screeningTime + ", ");
		System.out.print("�ܿ��¼���: " + remainingSitCount + ", ");
		System.out.print("�������¼�: " + lastSit + ", ");
		System.out.println();

	}
}
