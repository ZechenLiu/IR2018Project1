package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import DocBean.DocBean;
import Indexsearch.resulter;

/**
 * Servlet implementation class Hello
 */
@WebServlet(name = "Page", urlPatterns = "/servlet/Page")
public class Page extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uri;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String pageNum = request.getParameter("pageNum");
		String field = request.getParameter("field");
		String keyword = request.getParameter("keyword");

		// 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
		DocBean<resulter> pageBean = new DocBean<resulter>();
		if (pageNum != null) {
			pageBean.setCurrentPage(Integer.parseInt(pageNum));
		}

		// 3.给pageBean填充数据

		
		// 4. 保存pageBean对象，到request域中

		if ((keyword != null)&&(!keyword.equals(""))) {
			if (pageNum == null) {
				pageNum = "1";
			}
			Search1(pageNum, field, keyword, pageBean);
			request.setAttribute("pageBean", pageBean);
			uri = "/WEB-INF/list.jsp";
	

		}

		else {
			if (pageNum == null) {
				pageNum = "1";
			}
			Search2(pageNum, pageBean);

			request.setAttribute("pageBean", pageBean);
			uri = "/WEB-INF/list.jsp";
			// return;
		}

		RequestDispatcher rd = request.getRequestDispatcher(uri);
		try {
			rd.forward(request, response);
			return;
		} catch (Exception e) {
		}
		// request.getRequestDispatcher(uri).forward(request, response);
		// return ;
	}

	public static void Search1(String pageId, String field, String keyword, DocBean<resulter> db) {
		try {
			Directory directory = FSDirectory.open(Paths.get("F:\\CUFE中央财经大学\\大三上\\信息组织与检索\\IR2018Project1\\index"));//index文件路径
			IndexReader reader = DirectoryReader.open(directory);
			
			IndexSearcher searcher = new IndexSearcher(reader);
			
			QueryParser parser = new QueryParser(field, new StandardAnalyzer());
			Query query = parser.parse(keyword);
			
			
			TopDocs topdocs = searcher.search(query, 1000);
			
			
			
			ScoreDoc[] sds = topdocs.scoreDocs;

			int totalItemNum = sds.length;
			int curPage = Integer.parseInt(pageId);
			int pageSize = 10;
			int pageCount=pageSize;
			int beginId = (curPage - 1) * pageSize + 1;
			int totalPage = totalItemNum / pageSize + 1;
			int completePageNum = totalPage - 1;

			if (totalItemNum >= beginId) {
				if (curPage > completePageNum) {
					pageSize = totalItemNum % pageSize;
				}
				db.setCurrentPage(curPage);
				db.setPageCount(pageCount);
				db.setTotalPage(totalPage);
				db.setTotalCount(totalItemNum);
				db.setField(field);
				db.setKeyword(keyword);
				if (db.getCurrentPage() <= 0) {
					db.setCurrentPage(1); // 把当前页设置为1
				} else if (db.getCurrentPage() > db.getTotalPage()) {
					db.setCurrentPage(db.getTotalPage()); // 把当前页设置为最大页数
				}

				List<resulter> pageData = new ArrayList<resulter>();

				for (int j = beginId; j < (beginId + pageSize); j++) {

					Document temp = searcher.doc(sds[j - 1].doc);
					resulter aResulter = new resulter();
					aResulter.setAddress(temp.get("address").toString());
					aResulter.setAffiliation(temp.get("affiliation").toString());
					aResulter.setAuthors(temp.get("authors").toString());
					aResulter.setDate(temp.get("date").toString());
					aResulter.setFulltext(temp.get("fulltext").toString());
					aResulter.setTitle(temp.get("title").toString());
					//aResulter.setDocName(temp.get("docName").toString().replaceAll(".xml", ""));
					String text = temp.get(field).toString();
					QueryScorer scorer=new QueryScorer(query);
				    Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
				    SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
				    Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
				    highlighter.setTextFragmenter(fragmenter);
					if (text != null) {
						Analyzer analyzer =  new StandardAnalyzer();
						TokenStream tokenStream =analyzer.tokenStream(field, text);
						String highLightText = highlighter.getBestFragment(tokenStream, text);
						
						if(field.equals("title")) {
							aResulter.setTitle(highLightText);
						}
						else if(field.equals("authors")) {
							aResulter.setAuthors(highLightText);
							}
						else if(field.equals("date")) {
							aResulter.setDate(highLightText);
							}
						else if(field.equals("address")) {
							aResulter.setAddress(highLightText);
							}
						else if(field.equals("affiliation")) {
							aResulter.setAffiliation(highLightText);
							}
						else if(field.equals("fulltext")) {
								aResulter.setFulltext(highLightText);
							}
						analyzer.close();
					}
					
					pageData.add(aResulter);

				}	db.setPageData(pageData);

			}
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// search2函数经测试正常运行。 查询出所有的文件
	public static void Search2(String pageId, DocBean<resulter> db) {
		try {
			String indexPath = "F:\\CUFE中央财经大学\\大三上\\信息组织与检索\\IR2018Project1\\index";
			FSDirectory directory = FSDirectory.open(Paths.get(indexPath));
			IndexReader Reader = DirectoryReader.open(directory);

			int totalItemNum = Reader.numDocs();
			int curPage = Integer.parseInt(pageId);
			int pageSize = 10;
			int pageCount=pageSize;
			int beginId = (curPage - 1) * pageSize + 1;
			int totalPage = totalItemNum / pageSize + 1;
			int completePageNum = totalPage - 1;

			if (totalItemNum >= beginId) {
				if (curPage > completePageNum) {
					pageSize = totalItemNum % pageSize;
				}

				db.setCurrentPage(curPage);
				db.setTotalPage(totalPage);
				db.setPageCount(pageCount);
				db.setTotalCount(totalItemNum);
				/*
				 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！ 如果当前页为末页，再点下一页显示有问题！ 解决： 1. 如果当前页 <= 0;
				 * 当前页设置当前页为1; 2. 如果当前页 > 最大页数； 当前页设置为最大页数
				 */
				// 判断
				if (db.getCurrentPage() <= 0) {
					db.setCurrentPage(1); // 把当前页设置为1
				} else if (db.getCurrentPage() > db.getTotalPage()) {
					db.setCurrentPage(db.getTotalPage()); // 把当前页设置为最大页数
				}

				List<resulter> pageData = new ArrayList<resulter>();

				for (int j = beginId; j < (beginId + pageSize); j++) {
					Document temp = Reader.document(j - 1);
					resulter aResulter = new resulter();
					aResulter.setAddress(temp.get("address").toString());
					aResulter.setAffiliation(temp.get("affiliation").toString());
					aResulter.setAuthors(temp.get("authors").toString());
					aResulter.setDate(temp.get("date").toString());
					aResulter.setFulltext(temp.get("fulltext").toString());
					aResulter.setTitle(temp.get("title").toString());
					//aResulter.setDocName(temp.get("docName").toString().replaceAll(".xml", ""));
					pageData.add(aResulter);
				}
				db.setPageData(pageData);

			} 
			Reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
