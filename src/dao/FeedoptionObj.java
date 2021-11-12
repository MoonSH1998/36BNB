package dao;

public class FeedoptionObj {
	private String id, no, type, content;
	
    public FeedoptionObj(String id, String no, String type, String content)
    {
;
    	this.no = no;
    	this.type = type;
    	this.content = content;
    }
    
    public String getId() { return this.id; }
    public String getNo() { return this.no; }
    public String getType() { return this.type; }
    public String getContent() { return this.content; }
}
	