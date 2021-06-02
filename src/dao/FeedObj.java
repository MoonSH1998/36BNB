package dao;
public class FeedObj {
private String id, start, end, type, trader, price, content, ts, images;
public FeedObj(String id, String type,  String start, String end, String price,String trader, String content, String ts, String images) {
this.id = id;
this.start = start;
this.end = end;
this.type = type;
this.price = price;
this.trader = trader;
this.content = content;
this.ts = ts;
this.images = images;
}
public String getId() { return this.id; }
public String getStart() { return this.start; }
public String getEnd() { return this.end; }
public String getType() { return this.type; }
public String getPrice() { return this.price; }
public String getTrader() { return this.trader; }
public String getContent() { return this.content; }
public String getTs() { return this.ts; }
public String getImages() { return this.images; }
}