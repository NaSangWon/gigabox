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
		// ��ȭ ���� ���
		System.out.print("  * ��ȭ ��ȣ: " + mname +",  ");
		System.out.print("��ȭ ����: " + mno +", ");
		System.out.print("��Ÿ��: " + mruntime +",  ");
		System.out.print("������: " +  mrelease +", ");
		System.out.print("������: " + mattendance +",  ");
		System.out.println();
	}

}
