package lucene;
import java.lang.String;
import java.util.ArrayList;
public class Xml {
	/*标题，作者，日期，从属关系，地址和全文*/
	private String title;
	private String authors;
	private String date;
	private String affiliation;
	private String address;
	private String fulltext;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	/**/
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	/**/
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	/**/
	public String getAffiliation() {
		return affiliation;
	}
	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	/**/
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	/**/
	public String getFulltext() {
		return fulltext;
	}
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}
	@Override
	public String toString() {
		return ("title="+title+"\n"+"authors="+authors+"\n"+"date="+date+"\n"+"affiliation="+affiliation+"\n"+"address="+address+"\n"+"fulltext="+fulltext);
		
	}
}