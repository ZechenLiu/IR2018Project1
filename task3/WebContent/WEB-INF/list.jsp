<%@page import="javax.swing.text.Highlighter"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page buffer="32kb"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>首页</title>
<link href="../首页/css/base.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js">
	
</script>-->

<style>






.panel-heading {
	background-color: #FF8C00;
}
.luocui{
 color:#FF8C00;
}



</style>
</head>
<body>
<div class="qiandu">
<img src="../首页/images/千度.png">
</div>


	<br>
		<!--<div class="exp5">
			<a href="http://localhost:8080/Engine/home2.html">Home</a>
		</div>-->
		
			<form name="searchform" method="get" action="/Engine/servlet/Page">
		<select name="field" id="choose">
			<option>title</option>
			<option>fulltext</option>
			<option>authors</option>
			<option>affiliation</option>
			<option>date</option>
			<option>address</option>
		</select> 
		<input id="text" type="text" class="inp_srh" name="keyword"  placeholder="输入关键词" >
		<input class="btn_srh" type="submit" name="submit" value="搜索">
		<div id="word" class="word"></div>

	</form>
		</div>
	


	<div align="center">

		<br> <font color="black"><strong>共
				${requestScope.pageBean.totalCount }条结果
				当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页</strong></font>

		<br /> <br /> <br />
		<c:choose>
			<c:when test="${not empty requestScope.pageBean.pageData}">
				<c:forEach var="emp" items="${requestScope.pageBean.pageData}"
					varStatus="vs">

					<div class="panel panel-primary" style="width: 800px;" align="left">
						<div class="panel-heading">
							<h3 class="panel-title">
								<b>title:</b>${emp.title }
							</h3>
						</div>
						<div class="panel-body">
							<b>date:</b>${emp.date }</div>
						<div class="panel-body">
							<b>authors:</b>${emp.authors }</div>
						<div class="panel-body">
							<b>fulltext:</b>${emp.fulltext}</div>
						<div class="panel-body">
							<b>address:</b>${emp.address}</div>

						<div class="panel-body">
							<b>affiliation:</b>${emp.affiliation }</div>
						<!-- <div class="panel-body">
							<b>fileName:</b>${emp.docName }</div>-->
						<div class="panel-body" align="center">
							<strong>${(requestScope.pageBean.pageCount)*(requestScope.pageBean.currentPage-1)+vs.count}</strong>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="3">对不起，没有你要找的数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
<div class="luocui">
		<strong>当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页</strong>
		<ul class="pagination pagination-lg">
			<li><a
				href="${pageContext.request.contextPath }/servlet/Page?pageNum=1&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">首页</a>
			</li>

			<c:if test="${requestScope.pageBean.currentPage-1>0}">
				<li><a
					href="${pageContext.request.contextPath }/servlet/Page?pageNum=${requestScope.pageBean.currentPage-1}&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">上一页
				</a></li>
			</c:if>
			<li class="active"><a
				href="${pageContext.request.contextPath }/servlet/Page?pageNum=${requestScope.pageBean.currentPage}&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">${requestScope.pageBean.currentPage}
			</a></li>
			<c:if
				test="${requestScope.pageBean.currentPage+1<=requestScope.pageBean.totalPage}">
				<li><a
					href="${pageContext.request.contextPath }/servlet/Page?pageNum=${requestScope.pageBean.currentPage+1}&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">${requestScope.pageBean.currentPage+1}
				</a></li>
			</c:if>
			<c:if
				test="${requestScope.pageBean.currentPage+2<=requestScope.pageBean.totalPage}">
				<li><a
					href="${pageContext.request.contextPath }/servlet/Page?pageNum=${requestScope.pageBean.currentPage+2}&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">${requestScope.pageBean.currentPage+2}
				</a></li>
			</c:if>
			<c:if
				test="${requestScope.pageBean.currentPage+3<=requestScope.pageBean.totalPage}">
				<li><a
					href="${pageContext.request.contextPath }/servlet/Page?pageNum=${requestScope.pageBean.currentPage+3}&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">${requestScope.pageBean.currentPage+3}
				</a></li>
			</c:if>
			<c:if
				test="${requestScope.pageBean.currentPage+1<=requestScope.pageBean.totalPage}">
				<li><a
					href="${pageContext.request.contextPath }/servlet/Page?pageNum=${requestScope.pageBean.currentPage+1}&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">下一页
				</a></li>
			</c:if>
			<li><a
				href="${pageContext.request.contextPath }/servlet/Page?pageNum=${requestScope.pageBean.totalPage}&keyword=${requestScope.pageBean.keyword}&field=${requestScope.pageBean.field}">末页</a>
			</li>
		</ul>
		</div>
		<br>


	</div>
<script type="text/javascript" src="../首页/js/jquery-1.8.3.min.js"></script>
<!--<script type="text/javascript" src="../首页/js/jquery.select.js"></script>-->
<script type="text/javascript" src="../首页/js/jquery.js"></script>
<script type="text/javascript" src="../首页/js/JsonpAjax.js"></script>
<!-- 页脚部分开始 -->
      <footer>
        <div class="footer">
          <div class="row">
            <dl>
              <dt>刘泽辰</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=1344997607&site=qq&menu=yes">
                <img src="../首页/images/刘泽辰.jpg" alt="图标" title="刘泽辰" width="100px" height="100px" />
              </a>
            </dl>
            <dl>
              <dt>罗璨</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=1036610062&site=qq&menu=yes">
                <img src="../首页/images/罗璀.jpg" alt="图标" title="罗璨" width="100px" height="100px" />
              </a>
            </dl>
            <dl>
              <dt>杨鸿瑞</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=1300204310&site=qq&menu=yes">
                <img src="../首页/images/杨导.jpg" alt="图标" title="杨鸿瑞" width="100px" height="100px" />
              </a>
            </dl>
            <dl>
              <dt>徐源</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=3528551486&site=qq&menu=yes">
                <img src="../首页/images/徐源.jpg" alt="图标" title="徐源" width="100px" height="100px" />
              </a>
            </dl>
          </div>

          <div class="row">
            <a title="点此跳转至王焱老师的公众号" href="http://miner.picp.net/Index.html">
              <span class="copyright">在王焱老师的指导下完成</span>
            </a>
          </div>
        </div>
      </footer>
      <!-- 页脚部分结束 -->
</body>
</html>