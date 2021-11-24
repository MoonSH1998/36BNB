package dao;

public class FeedooptionObj {
	private String id, fid, no, content;
	
    public FeedooptionObj(String id, String fid, String no,String content)
    {
;
    	this.no = no;
    	this.id = id;
    	this.fid = fid;
    	this.content = content;
    }
    
    public String getId() { return this.id; }
    public String getNo() { return this.no; }
    public String getFid() { return this.fid; }
    public String getContent() { return this.content; }
}