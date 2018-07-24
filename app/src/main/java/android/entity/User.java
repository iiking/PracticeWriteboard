package android.entity;

import java.io.Serializable;

public class User implements Serializable{
private String title;
private String news;
private String kecheng;
private int miDrawFreq;


public int getMiDrawFreq() {
	return miDrawFreq;
}
public void setMiDrawFreq(int miDrawFreq) {
	this.miDrawFreq = miDrawFreq;
}
public String getKecheng() {
	return kecheng;
}
public void setKecheng(String kecheng) {
	this.kecheng = kecheng;
}
public User() {
	super();
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getNews() {
	return news;
}
public void setNews(String news) {
	this.news = news;
}

}
