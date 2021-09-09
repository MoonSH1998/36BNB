package dao;

public class FeedoptionObj {
	private String id, no, fid, type, content;
	
    public FeedoptionObj(String id, String no, String fid, String type, String content)
    {
    	this.id = id;
    	this.no = no;
    	this.fid = fid;
    	this.type = type;
    	this.content = content;
    }
    
    public String getId() { return this.id; }
    public String getNo() { return this.no; }
    public String getFid() { return this.fid; }
    public String getType() { return this.type; }
    public String getContent() { return this.content; }
}
	