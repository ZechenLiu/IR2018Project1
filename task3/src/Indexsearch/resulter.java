package Indexsearch;

import java.lang.String;
public class resulter {
    /*标题，作者，日期，从属关系，地址和全文*/
    private String title;
    private String authors;
    private String date;
    private String affiliation;
    private String address;
    private String fulltext;
    private String docName;
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
    public String getDocName() {
		return docName;
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
    public void setDocName(String docName) {
		this.docName = docName;
	}
    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}
