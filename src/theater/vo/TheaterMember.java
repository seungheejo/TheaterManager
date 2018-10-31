package theater.vo;

public class TheaterMember {
	private int memberNum;
	private String name;
	private String grade;
	private String email;
	private String phoneNum;
	private String id;
	private String PW;
	private int points;
	
	public TheaterMember() {
		
	}
	
	public TheaterMember(String name, String grade, String email, String phoneNum, String id, String PW,
			int points) {
		super();
		this.name = name;
		this.grade = grade;
		this.email = email;
		this.phoneNum = phoneNum;
		this.id = id;
		this.PW = PW;
		this.points = points;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return PW;
	}

	public void setPw(String PW) {
		this.PW = PW;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "=====가입완료=====\n" + "회원번호: " + this.memberNum 
				+ "\n회원명: " + this.name + "\n회원등급: " + this.grade
				+ "\n이메일: " + this.email + "\n전화번호: " + this.phoneNum
				+ "\nid: " + this.id + "\n포인트: " + this.points;
	}

}
