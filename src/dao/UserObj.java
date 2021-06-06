package dao;

public class UserObj {
    private String id, name, stu_num, birth, sex, phone_num, ps;
	
    public UserObj(String id, String name, String stu_num, String birth, String sex, String phone_num, String ps) {
    	this.id = id;
    	this.name = name;
        this.stu_num = stu_num;
        this.birth = birth;
        this.sex = sex;
        this.phone_num = phone_num;
        this.ps = ps;
        
    }
    
    public String getId() { return this.id; }
    public String getName() { return this.name; }
    public String getStu_num() { return this.stu_num; }
    public String getBirth() { return this.birth; }
    public String getSex() { return this.sex; }
    public String getPhone_num() { return this.phone_num; }
    public String getPs() { return this.ps; }
    
}
