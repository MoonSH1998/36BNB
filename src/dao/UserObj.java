package dao;

public class UserObj {
    private String name, stu_num, birth, phone_num, email, ps;
	
    public UserObj(String name, String stu_num, String birth, String phone_num, String email, String ps) {
        this.name = name;
        this.stu_num = stu_num;
        this.birth = birth;
        this.phone_num = phone_num;
        this.email = email;
        this.ps = ps;
        
    }
    
    public String getName() { return this.name; }
    public String getStu_num() { return this.stu_num; }
    public String getBirth() { return this.birth; }
    public String getPhone_num() { return this.phone_num; }
    public String getEmail() { return this.email; }
    public String getps() { return this.ps; }

}
