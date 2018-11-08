package lucene;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import lucene.Xml;
import java.lang.String;
import java.nio.file.Paths;
//用DOM4J方法读取xml文件
public class ReadXMLByDom4j {
	private static List<Xml> xmlList = null;
	private Xml xml = null;
	public List<Xml> getXml(File file){
		SAXReader reader = new SAXReader(); 
		try {
			org.dom4j.Document document = reader.read(file);
			Element xmlstore = document.getRootElement();
			xmlList = new ArrayList<Xml>();
			xml = new Xml();
			//title
			Element title1=xmlstore.element("teiHeader");
			Element title2=title1.element("fileDesc");
			Element title3=title2.element("titleStmt");
			Element title4=title3.element("title");
			String titleName = title4.getText();
			xml.setTitle(titleName);
			//authors
			String authorsname = "";
			Element name1 = xmlstore.element("teiHeader");
			Element name2 = name1.element("fileDesc");
			Element name3 = name2.element("sourceDesc");
			Element name4 = name3.element("biblStruct");
			Element name5 = name4.element("analytic");
			Iterator name6 = name5.elementIterator();
			while(name6.hasNext()){
				Element name7 = (Element) name6.next();
				if(name7.getName().equals("author")) {
					Iterator name11 = name7.elementIterator();
					String authorname = "";
					while(name11.hasNext()) {
						Element name12 =(Element) name11.next();
						if(name12.getName().equals("persName")) {
						Element name8 = name7.element("persName");
						Iterator name9 = name8.elementIterator();
						//String authorname = "";
						while(name9.hasNext()) {
							Element name10 = (Element) name9.next();
							authorname = authorname +  "." + name10.getText();	
							}
					}
					}
					authorsname = authorsname + authorname.replaceFirst(".", "") + "\n";	
				}
			}
			xml.setAuthors(authorsname.trim());	
			/*}*/
			//date
			String dates = "";
			Element date1 = xmlstore.element("teiHeader");
			Element date2 = date1.element("fileDesc");
            Element date3 = date2.element("publicationStmt");
            Iterator date4 = date3.elementIterator();
            while(date4.hasNext()) {
            	Element date5 = (Element) date4.next();
            	if(date5.getName().equals("date")) {
            		dates = dates + date5.getText() + "\n";
            	}
            }
			xml.setDate(dates.trim());
			//affiliation
			Element af1 = xmlstore.element("teiHeader");
			Element af2 = af1.element("fileDesc");
			Element af3 = af2.element("sourceDesc");
			Element af4 = af3.element("biblStruct");
			Element af5 = af4.element("analytic");
			Iterator af6 = af5.elementIterator();
			String affiliation = "";
			while(af6.hasNext()) {
				Element af7 = (Element) af6.next();
				if(af7.getName().equals("author")) {
					Iterator af11 = af7.elementIterator();
					while(af11.hasNext()) {
						Element af12 = (Element) af11.next();
						if(af12.getName().equals("affiliation")) {
							Element af8 = af7.element("affiliation");
							Iterator af9 = af8.elementIterator();
							while(af9.hasNext()) {
								Element af10 = (Element) af9.next();
								if(af10.getName().equals("orgName")){
									affiliation = affiliation + af10.getText() + "\n";
										}
									}	
						}
					}
						
						}
					}
			xml.setAffiliation(affiliation.trim());//trim()去掉首尾空格
			//address
			Element ad1 = xmlstore.element("teiHeader");
			Element ad2 = ad1.element("fileDesc");
			Element ad3 = ad2.element("sourceDesc");
			Element ad4 = ad3.element("biblStruct");
			Element ad5 = ad4.element("analytic");
			Iterator ad6 = ad5.elementIterator();
			String address = "";
			while(ad6.hasNext()) {
				Element ad7 = (Element) ad6.next();
				if(ad7.getName().equals("author")) {
					Iterator ad12 = ad7.elementIterator();
					while(ad12.hasNext()) {
						Element ad13 = (Element) ad12.next();
						if(ad13.getName().equals("affiliation")) {
					Element ad8 = ad7.element("affiliation");
					Iterator ad14 = ad8.elementIterator();
					while(ad14.hasNext()) {
						Element ad15 = (Element) ad14.next();
						if(ad15.getName().equals("address")) {
					
					Element ad9 = ad8.element("address");
					Iterator ad10 = ad9.elementIterator();
					String tem_address = "";
					while(ad10.hasNext()) {
						Element ad11 = (Element) ad10.next();
						tem_address = tem_address + ad11.getText() + " ";
					}
					tem_address = tem_address.trim();
					address = address + tem_address + "\n";
				}
			}
				}
			}
				}
			}
			xml.setAddress(address.trim());
			//fulltext
			String fulltext = "";
			String fu_by_div = "";
			String fu_by_fig = "";
			String fu_bk = "";
			Element fu = xmlstore.element("text");
			Element fu_by1 = fu.element("body");
			Iterator fu_by2 = fu_by1.elementIterator();
			while(fu_by2.hasNext()) {
				Element fu_by3 = (Element) fu_by2.next();
				if(fu_by3.getName().equals("div")) {
					Iterator fu_by_div1 = fu_by3.elementIterator();
					while(fu_by_div1.hasNext()) {
						Element fu_by_div2 = (Element) fu_by_div1.next();
						String getname = fu_by_div2.getName();
						if(getname.equals("head")) {
							fu_by_div = fu_by_div + fu_by_div2.attributeValue("n") + " " +fu_by_div2.getText() + "\n";
						}else if(getname.equals("p")) {
							Iterator fu_by_div3 = fu_by_div2.elementIterator();
							if(fu_by_div3.hasNext()) {
								String fu_by_div5 = "";
								while(fu_by_div3.hasNext()) {
									Element fu_by_div4 = (Element) fu_by_div3.next();
									fu_by_div5 = fu_by_div5 + fu_by_div4.getText() + " ";
									//fu_by_div = fu_by_div + fu_by_div2.getText() + " " + fu_by_div4.getText() + "\n";
									}
								fu_by_div = fu_by_div + fu_by_div2.getText() + " " + fu_by_div5.trim() + "\n";
								}else {
									fu_by_div = fu_by_div + fu_by_div2.getText() + "\n";
									}
							}else if(getname.equals("formula")) {
								fu_by_div = fu_by_div + fu_by_div2.getText() + "\n";
							}
						}
					}
				else if(fu_by3.getName().equals("figure")) {
					Iterator fu_by_fig1 =fu_by3.elementIterator();
					String fu_by_fig3 = "";
					while(fu_by_fig1.hasNext()) {
						Element fu_by_fig2 = (Element) fu_by_fig1.next();
						fu_by_fig3 = fu_by_fig3 + fu_by_fig2.getText() + " ";
					}
					fu_by_fig = fu_by_fig + fu_by_fig3 + "\n";
				}
			}
			//System.out.println(fu_by_div.trim());
			//System.out.println(fu_by_fig.trim());
			Element fu_bk1 = fu.element("back");
			Iterator fu_bk2 = fu_bk1.elementIterator();
			while(fu_bk2.hasNext()) {
				Element fu_bk3 = (Element) fu_bk2.next();
				if(fu_bk3.attributeValue("type").equals("acknowledgement")) {
					Iterator fu_bk7 = fu_bk3.elementIterator();
					while(fu_bk7.hasNext()) {
						Element fu_bk8 = (Element) fu_bk7.next();
						if(fu_bk8.getName().equals("div")) {
					Element fu_bk4 = fu_bk3.element("div");
					Iterator fu_bk5 = fu_bk4.elementIterator();
					while(fu_bk5.hasNext()) {
						Element fu_bk6 = (Element) fu_bk5.next();
						fu_bk = fu_bk + fu_bk6.getText() + "\n";
					}
				}
			}
				}
			}
			//System.out.println(fu_bk.trim());
			fulltext = fu_by_div.trim() + "\n" + fu_by_fig.trim() + "\n" + fu_bk.trim();
			xml.setFulltext(fulltext);
			//System.out.println(fulltext);
			/*Iterator xmlTitle = xmlstore.elementIterator("titleStmt");*/
			/*while(xmlTitle.hasNext()) {
				Element child = (Element) xmlTitle.next();
				String titleName = child.getText();
				xml.setTitle(titleName);
			}*/
			xmlList.add(xml);
			/*xml = null;*/
		}catch (DocumentException e) {
			e.printStackTrace();
		}
		return xmlList;
	}
	
	/*
	public void testCreateIndex() throws IOException{
		//1.指定索引库的存放位置Directory对象
		 Directory directory = FSDirectory.open(Paths.get("F:\\CUFE中央财经大学\\大三上\\信息组织与检索\\project1\\index"));
		//2.指定一个标准分析器，对文档内容进行分析
		 Analyzer analyzer = new StandardAnalyzer();
		 // 3. 创建索引写入器
		 IndexWriterConfig config = new IndexWriterConfig(analyzer);
		 config.setOpenMode(OpenMode.CREATE_OR_APPEND); //索引不存在创建，索引存在追加
		 IndexWriter indexWriter = new IndexWriter(directory, config);
		 Field titlefield = new TextField("title",title,store.Yes);
	}
	*/
	public static void main(String[] args) throws IOException{
		File file = new File("D:\\pythonwork\\IRproject1\\xml");
		File[] fileList = file.listFiles();
		for(int i = 0;i < fileList.length;i++) {
			File file1 = fileList[i];
			List<Xml> xmlList = new ReadXMLByDom4j().getXml(file1);
			System.out.println(i+1);
			System.out.println(xmlList);
			/*
			//1.指定索引库的存放位置Directory对象
			 Directory directory = FSDirectory.open(Paths.get("F:\\CUFE中央财经大学\\大三上\\信息组织与检索\\project1\\index"));
			//2.指定一个标准分析器，对文档内容进行分析
			 Analyzer analyzer = new StandardAnalyzer();
			 // 3. 创建索引写入器
			 IndexWriterConfig config = new IndexWriterConfig(analyzer);
			 config.setOpenMode(OpenMode.CREATE_OR_APPEND); //索引不存在创建，索引存在追加
			 IndexWriter indexWriter = new IndexWriter(directory, config);
			 Field titlefield = new TextField("title",title,store.Yes);
			 */
		}
		/*for(Xml xml : xmlList) {
			System.out.println(xml);
		}*/
		
	}
}