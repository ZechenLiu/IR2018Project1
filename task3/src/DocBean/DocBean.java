package DocBean;

import java.util.List;


/**
 * 封装分页的参数
 * 
 * @author Jie.Yuan
 * 
 */
public class DocBean<T> {
	private String keyword;
	private String field;
    private int currentPage = 1; // 当前页, 默认显示第一页
    private int pageCount = 10;   // 每页显示的行数(查询返回的行数), 默认每页显示4行
    private int totalCount;      // 总记录数
    private int totalPage;       // 总页数 = 总记录数 / 每页显示的行数  (+ 1)
    private List<T> pageData;       // 分页查询到的数据
    
    // 返回总页数
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    public List<T> getPageData() {
        return pageData;
    }
    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
    public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
    public String getField() {
		return field;
	}
    
    public String getKeyword() {
		return keyword;
	}
    
    public void setField(String field) {
		this.field = field;
	}
    

}