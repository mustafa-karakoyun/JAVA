package cards.dto;

public class Response {

	private Long id;
	private String fullName;
	private int age;
	private int overall;
	private String nation;
	private String club;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	public Response(Long id,String fullName,int age, int overall, String nation,String club) {
		this.id=id;
		this.fullName=fullName;
		this.age=age;
		this.overall=overall;
		this.nation=nation;
		this.club=club;
	
	}
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        // Kartın benzersiz numarasını atar
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        // Futbolcunun tam adını günceller
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // Futbolcunun yaşını günceller
        this.age = age;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        // Kartın genel reytingini (Örn: 97) günceller
        this.overall = overall;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        // Futbolcunun ülkesini günceller
        this.nation = nation;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        // Futbolcunun oynadığı kulübü günceller
        this.club = club;
    }
	
	
	
}
