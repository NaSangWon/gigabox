package gigabox;

public class Cinema {
	private int cno;
	private String caddress;
	private String cname;
	
	public Cinema() {
		
	}
	
	public int getCno()	{
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public void output() {
		// ���� ���� ���
		System.out.print("  * �����ȣ: " + cno +", ");
		System.out.print("�����ּ�: " +  caddress +", ");
		System.out.print("�����̸�: " + cname +",  ");
		System.out.println();
	}
}
