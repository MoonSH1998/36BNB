package dao;
public class FeedObj {
private String id, start, end, type, trader, price, content, ts, image;
public FeedObj(String id, String image, String type,  String start, String end, String price,String trader, String content, String ts) {
this.id = id;
this.image = image;
this.start = start;
this.end = end;
this.type = type;
this.price = price;
this.trader = trader;
this.content = content;
this.ts = ts;
}
public String getId() { return this.id; }
public String getImage() { return this.image; }
public String getStart() { return this.start; }
public String getEnd() { return this.end; }
public String getType() { return this.type; }
public String getPrice() { return this.price; }
public String getTrader() { return this.trader; }
public String getContent() { return this.content; }
public String getTs() { return this.ts; }
}