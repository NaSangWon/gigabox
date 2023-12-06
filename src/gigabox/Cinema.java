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
		// ±ØÀå Á¤º¸ Ãâ·Â
		System.out.print("  * ±ØÀå¹øÈ£: " + cno +", ");
		System.out.print("±ØÀåÁÖ¼Ò: " +  caddress +", ");
		System.out.print("±ØÀåÀÌ¸§: " + cname +",  ");
		System.out.println();
	}
}
