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
		// �󿵰� ���� ���
		System.out.print("  * ���� ��ȣ: " + tname +", ");
		System.out.print("�󿵰� ��ȣ : " +  tno +", ");
		System.out.print("�󿵰� �̸�: " + tname +",  ");
		System.out.print("������: " + tcost +",  ");
		System.out.print("�¼�����: " + sitCount +",  ");
		System.out.println();

	}
}
