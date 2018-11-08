package DocBean;

import java.util.List;


/**
 * ��װ��ҳ�Ĳ���
 * 
 * @author Jie.Yuan
 * 
 */
public class DocBean<T> {
	private String keyword;
	private String field;
    private int currentPage = 1; // ��ǰҳ, Ĭ����ʾ��һҳ
    private int pageCount = 10;   // ÿҳ��ʾ������(��ѯ���ص�����), Ĭ��ÿҳ��ʾ4��
    private int totalCount;      // �ܼ�¼��
    private int totalPage;       // ��ҳ�� = �ܼ�¼�� / ÿҳ��ʾ������  (+ 1)
    private List<T> pageData;       // ��ҳ��ѯ��������
    
    // ������ҳ��
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