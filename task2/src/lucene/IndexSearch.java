package lucene;
import java.util.Scanner;
import java.io.StringReader;
import java.nio.file.Paths;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
public class IndexSearch {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			String inputSearch = scanner.next();  
			Directory directory = FSDirectory.open(Paths.get("F:\\CUFE中央财经大学\\大三上\\信息组织与检索\\project1\\index"));
			IndexReader indexReader = DirectoryReader.open(directory);
			IndexSearcher searcher = new IndexSearcher(indexReader);
			//检索fulltext域
			QueryParser parser1 = new QueryParser("fulltext",new StandardAnalyzer());
			Query query1 = parser1.parse(inputSearch); //检索字符串
			StandardAnalyzer analyzer = new StandardAnalyzer();
			QueryScorer scorer1=new QueryScorer(query1);
		    Fragmenter fragmenter1=new SimpleSpanFragmenter(scorer1);
		    SimpleHTMLFormatter simpleHTMLFormatter1=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		    Highlighter highlighter1=new Highlighter(simpleHTMLFormatter1, scorer1);
		    highlighter1.setTextFragmenter(fragmenter1);
		    TopDocs topDocs1 = searcher.search(query1, 1); //第二个参数：返回结果 10条信息
		    System.out.println("命中数："+topDocs1.totalHits);
		    ScoreDoc[] docs1 = topDocs1.scoreDocs;
		    for (ScoreDoc scoreDoc : docs1) {
		        System.out.println("title： "+searcher.doc(scoreDoc.doc).get("title"));
		        System.out.println("date: "+searcher.doc(scoreDoc.doc).get("date"));
		        System.out.println("authors: "+searcher.doc(scoreDoc.doc).get("authors"));
		        System.out.println("affiliation: "+searcher.doc(scoreDoc.doc).get("affiliation"));
		        System.out.println("address: "+searcher.doc(scoreDoc.doc).get("address"));
		        String fulltext=searcher.doc(scoreDoc.doc).get("fulltext");
		        if(fulltext!=null){
	                TokenStream tokenStream=analyzer.tokenStream("fulltext", new StringReader(fulltext));
	                /**
	                 * getBestFragment方法用于输出摘要（即权重大的内容）
	                 */
	                System.out.println("fulltext: "+highlighter1.getBestFragment(tokenStream, fulltext));
	            }
		    }
		    //检索title域
		    QueryParser parser2 = new QueryParser("title",new StandardAnalyzer());
		    Query query2 = parser2.parse(inputSearch);
		    QueryScorer scorer2=new QueryScorer(query2);
		    Fragmenter fragmenter2=new SimpleSpanFragmenter(scorer2);
		    SimpleHTMLFormatter simpleHTMLFormatter2=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		    Highlighter highlighter2=new Highlighter(simpleHTMLFormatter2, scorer2);
		    highlighter2.setTextFragmenter(fragmenter2);
		    TopDocs topDocs2 = searcher.search(query2, 1);
		    System.out.println("命中数："+ topDocs2.totalHits);
		    ScoreDoc[] docs2 = topDocs2.scoreDocs;
		    for (ScoreDoc scoreDoc : docs2) {
		    	String title = searcher.doc(scoreDoc.doc).get("title");
		    	if(title!=null){
	                TokenStream tokenStream=analyzer.tokenStream("title", new StringReader(title));
	                /**
	                 * getBestFragment方法用于输出摘要（即权重大的内容）
	                 */
	                System.out.println("title: "+highlighter2.getBestFragment(tokenStream, title));
	            }
		        System.out.println("date: "+searcher.doc(scoreDoc.doc).get("date"));
		        System.out.println("authors: "+searcher.doc(scoreDoc.doc).get("authors"));
		        System.out.println("affiliation: "+searcher.doc(scoreDoc.doc).get("affiliation"));
		        System.out.println("address: "+searcher.doc(scoreDoc.doc).get("address"));
		        System.out.println("fulltext: ");
		    }
		    //检索authors域
		    QueryParser parser3 = new QueryParser("authors",new StandardAnalyzer());
		    Query query3 = parser3.parse(inputSearch);
		    QueryScorer scorer3=new QueryScorer(query3);
		    Fragmenter fragmenter3=new SimpleSpanFragmenter(scorer3);
		    SimpleHTMLFormatter simpleHTMLFormatter3=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		    Highlighter highlighter3=new Highlighter(simpleHTMLFormatter3, scorer3);
		    highlighter3.setTextFragmenter(fragmenter3);
		    TopDocs topDocs3 = searcher.search(query3, 1);
		    System.out.println("命中数："+ topDocs3.totalHits);
		    ScoreDoc[] docs3 = topDocs3.scoreDocs;
		    for (ScoreDoc scoreDoc : docs3) {
		        System.out.println("title: "+searcher.doc(scoreDoc.doc).get("title"));
		        System.out.println("date: "+searcher.doc(scoreDoc.doc).get("date"));
		        String authors = searcher.doc(scoreDoc.doc).get("authors");
		    	if(authors!=null){
	                TokenStream tokenStream=analyzer.tokenStream("authors", new StringReader(authors));
	                /**
	                 * getBestFragment方法用于输出摘要（即权重大的内容）
	                 */
	                System.out.println("authors: "+highlighter3.getBestFragment(tokenStream, authors));
	            }
		    	System.out.println("affiliation: "+searcher.doc(scoreDoc.doc).get("affiliation"));
		    	System.out.println("address: "+searcher.doc(scoreDoc.doc).get("address"));
		    	System.out.println("fulltext: ");
		    }
		    //检索date域
		    QueryParser parser4 = new QueryParser("date",new StandardAnalyzer());
		    Query query4 = parser4.parse(inputSearch);
		    QueryScorer scorer4=new QueryScorer(query4);
		    Fragmenter fragmenter4=new SimpleSpanFragmenter(scorer4);
		    SimpleHTMLFormatter simpleHTMLFormatter4=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		    Highlighter highlighter4=new Highlighter(simpleHTMLFormatter4, scorer4);
		    highlighter4.setTextFragmenter(fragmenter4);
		    TopDocs topDocs4 = searcher.search(query4, 1);
		    System.out.println("命中数："+ topDocs4.totalHits);
		    ScoreDoc[] docs4 = topDocs4.scoreDocs;
		    for (ScoreDoc scoreDoc : docs4) {
		    	 System.out.println("title: "+searcher.doc(scoreDoc.doc).get("title"));
			     String date = searcher.doc(scoreDoc.doc).get("date");
			     if(date!=null){
		              TokenStream tokenStream=analyzer.tokenStream("date", new StringReader(date));
		                /**
		                 * getBestFragment方法用于输出摘要（即权重大的内容）
		                 */
		                System.out.println("date: "+highlighter4.getBestFragment(tokenStream, date));
		            }
			    System.out.println("authors: "+searcher.doc(scoreDoc.doc).get("authors"));
			    System.out.println("affiliation: "+searcher.doc(scoreDoc.doc).get("affiliation"));
			    System.out.println("address: "+searcher.doc(scoreDoc.doc).get("address"));
			    System.out.println("fulltext: ");
		    }
		    //检索affiliation域
		    QueryParser parser5 = new QueryParser("affiliation",new StandardAnalyzer());
		    Query query5 = parser5.parse(inputSearch);
		    QueryScorer scorer5=new QueryScorer(query5);
		    Fragmenter fragmenter5=new SimpleSpanFragmenter(scorer5);
		    SimpleHTMLFormatter simpleHTMLFormatter5=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		    Highlighter highlighter5=new Highlighter(simpleHTMLFormatter5, scorer5);
		    highlighter5.setTextFragmenter(fragmenter5);
		    TopDocs topDocs5 = searcher.search(query5, 1);
		    System.out.println("命中数："+ topDocs5.totalHits);
		    ScoreDoc[] docs5 = topDocs5.scoreDocs;
		    for (ScoreDoc scoreDoc : docs5) {
		    	 System.out.println("title: "+searcher.doc(scoreDoc.doc).get("title"));
		    	 System.out.println("date: "+searcher.doc(scoreDoc.doc).get("date"));
		    	 System.out.println("authors: "+searcher.doc(scoreDoc.doc).get("authors"));
			     String affiliation = searcher.doc(scoreDoc.doc).get("affiliaiton");
			     if(affiliation!=null){
		              TokenStream tokenStream=analyzer.tokenStream("affiliation: ", new StringReader(affiliation));
		                /**
		                 * getBestFragment方法用于输出摘要（即权重大的内容）
		                 */
		                System.out.println("affiliaiton: "+highlighter5.getBestFragment(tokenStream, affiliation));
		            }
			    System.out.println("address: "+searcher.doc(scoreDoc.doc).get("address"));
			    System.out.println("fulltext: ");
		    }
		    //检索address域
		    QueryParser parser6 = new QueryParser("address",new StandardAnalyzer());
		    Query query6 = parser6.parse(inputSearch);
		    QueryScorer scorer6=new QueryScorer(query6);
		    Fragmenter fragmenter6=new SimpleSpanFragmenter(scorer6);
		    SimpleHTMLFormatter simpleHTMLFormatter6=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
		    Highlighter highlighter6=new Highlighter(simpleHTMLFormatter6, scorer6);
		    highlighter6.setTextFragmenter(fragmenter6);
		    TopDocs topDocs6 = searcher.search(query6, 1);
		    System.out.println("命中数："+ topDocs6.totalHits);
		    ScoreDoc[] docs6 = topDocs6.scoreDocs;
		    for (ScoreDoc scoreDoc : docs6) {
		    	 System.out.println("title: "+searcher.doc(scoreDoc.doc).get("title"));
		    	 System.out.println("date: "+searcher.doc(scoreDoc.doc).get("date"));
		    	 System.out.println("authors: "+searcher.doc(scoreDoc.doc).get("authors"));
		    	 System.out.println("affiliation: "+searcher.doc(scoreDoc.doc).get("affiliation"));
			     String address = searcher.doc(scoreDoc.doc).get("address");
			     if(address!=null){
		              TokenStream tokenStream=analyzer.tokenStream("address", new StringReader(address));
		                /**
		                 * getBestFragment方法用于输出摘要（即权重大的内容）
		                 */
		                System.out.println("address: "+highlighter6.getBestFragment(tokenStream, address));
		            }
			     System.out.println("fulltext: ");
		    }
		    // 7. 释放资源
		    indexReader.close();
		    directory.close();
		}catch(Exception e){
		    e.printStackTrace();


			}
	}
}


