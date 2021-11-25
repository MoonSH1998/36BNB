package dao;

public class FeedoptionObj {
	private String id, fid, no, content, state, list;
	
    public FeedoptionObj(String id, String list, String fid, String no, String state, String content)
    {
;		
		this.list = list;
    	this.no = no;
    	this.no = state;
    	this.id = id;
    	this.fid = fid;
    	this.content = content;
    }
    public String getList() { return this.list; }
    public String getId() { return this.id; }
    public String getState() { return this.state; }
    public String getNo() { return this.no; }
    public String getFid() { return this.fid; }
    public String getContent() { return this.content; }
}