package cards.dto;

import jakarta.validation.constraints.*;

public class Request {
	@NotBlank(message="Name cant be blank.")
	private String fullName;
	@Positive(message="Age must be pozitive.")
	private int age;
	@Min(value=0,message="Minimum 0 can be")
	@Max(value=100,message="Maximum 100 can be")
	private int overall;
	@NotBlank(message="Nation cant be blank.")
	private String nation;
	
	private String club;
	
	
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
