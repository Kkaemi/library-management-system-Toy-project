package text;

public class BlackDTO {
	private String id;
	private String name;
	private int bookSeq;
	private int memSeq;
	private String bookName;
	private int overdue;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBookSeq() {
		return bookSeq;
	}
	public void setBookSeq(int bookSeq) {
		this.bookSeq = bookSeq;
	}
	public int getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(int memSeq) {
		this.memSeq = memSeq;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getOverdue() {
		return overdue;
	}
	public void setOverdue(int overdue) {
		this.overdue = overdue;
	}

}
