<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>

<link href="css/base.css" type="text/css" rel="stylesheet" />



</head>


<body>
<div class="qiandu">
<img src="images/千度.png">
</div>
<div class="search radius6">
	<form name="searchform" method="post" action="">
		<input name='ecmsfrom' type='hidden' value='9'>
		<input type="hidden" name="show" value="title,newstext">
		<select name="classid" id="choose">
			<option value="0">title</option>
			<option value="1">fulltext</option>
			<option value="2">authors</option>
			<option value="3">affiliation</option>
			<option value="4">date</option>
			<option value="5">address</option>
		</select> 
		<input id="text" type="text" class="inp_srh" name="keyboard"  placeholder="输入关键词" >
		<input class="btn_srh" type="submit" name="submit" value="搜索">
		<div id="word" class="word"></div>

	</form>
</div>
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery.select.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/JsonpAjax.js"></script>
<!-- 页脚部分开始 -->
      <footer>
        <div class="footer">
          <div class="row">
            <dl>
              <dt>刘泽辰</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=1344997607&site=qq&menu=yes">
                <img src="D:/信息组织与检索/展示页面/resource/img/刘泽辰.jpg" alt="图标" title="刘泽辰" width="100px" height="100px" />
              </a>
            </dl>
            <dl>
              <dt>罗璨</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=1036610062&site=qq&menu=yes">
                <img src="D:/信息组织与检索/展示页面/resource/img/罗璀.jpg" alt="图标" title="罗璨" width="100px" height="100px" />
              </a>
            </dl>
            <dl>
              <dt>杨鸿瑞</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=1300204310&site=qq&menu=yes">
                <img src="D:/信息组织与检索/展示页面/resource/img/我.jpg" alt="图标" title="杨鸿瑞" width="100px" height="100px" />
              </a>
            </dl>
            <dl>
              <dt>徐源</dt>
              <a href="http://wpa.qq.com/msgrd?v=3&uin=3528551486&site=qq&menu=yes">
                <img src="D:/信息组织与检索/展示页面/resource/img/徐源.jpg" alt="图标" title="徐源" width="100px" height="100px" />
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