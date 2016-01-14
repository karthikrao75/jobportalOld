<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Your Jobs</title>
<!-- html 5 jquery -->

<script type="text/javascript">
 document.createElement('header');
 document.createElement('hgroup');
 document.createElement('nav');
 document.createElement('menu');
 document.createElement('section');
 document.createElement('article');
 document.createElement('aside');
 document.createElement('footer');
</script>
<!--[if gte IE 9]>
  <style type="text/css">
    .gradient {
       filter: none;
    }
  </style>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/reset.css" />" />
</head>

<body>
<%@include file="header.jsp" %>
<div class="content-warp">
<div class="container">
  <div class="search-box">
    <div class="left-box">
      <form>
        <input class="left-box-input" type="text" name="Enter keyword(Web Design,New York)" value="Enter keyword(Web Design,New York)">
        <div class="form-block">
          <label><strong>Location</strong></label>
          <br />
          <select>
            <option>-select-</option>
          </select>
        </div>
        <div class="form-block2">
          <label><strong>Experience:</strong></label>
          <br />
          <select>
            <option>-select-</option>
          </select>
        </div>
        <div class="form-block2">
          <label><strong>Salary(min):</strong></label>
          <br />
          <select>
            <option>-select-</option>
          </select>
        </div>
        <div class="form-block2">
          <label><strong>Salary(max):</strong></label>
          <br />
          <select>
            <option>-select-</option>
          </select>
        </div>
        <div class="clear"></div>
        <input type="button" class="red-btn" value="Advance Search" />
      </form>
    </div>
    <div class="right-search"> <a href="#">Search</a> </div>
  </div>
  <div class="bottom-img"><img src="images/sraech-bg.gif" width="1006" height="14" alt="" /></div>
  <div class="clear"></div>
  <section>
    <div class="left-content">
      <div class="browse">
        <h2>Jobs</h2>        
        <div class="clear"></div>
      </div>
      <div class="filter-jobs">
        <div class="coulmn">
          <h2>Software</h2>
          <ul>
            <li><a href="#">PHP Developer<i>(312)</i></a></li>
            <li><a href="#">MySQL Developer<i>(12)</i></a></li>
            <li><a href="#">Articles Writter<i>(312)</i></a></li>
            <li><a href="#">Social Networking<i>(312)</i></a></li>
            <li><a href="#">Technical Writing<i>(312)</i></a></li>
            <li><a href="#">C# Programming<i>(312)</i></a></li>
            <li><a href="#">Joomla & Wordpress<i>(312)</i></a></li>
            <li><a href="#">Java/JRE Developer<i>(312)</i></a></li>
            <li><a href="#">PHP <i></i></a></li>
          </ul>
          <h3><a href="#">See all jobs</a></h3>
          <div class="clear"></div>
        </div>
        <div class="coulmn">
          <ul>
            <li><a href="#">PHP Developer<i>(312)</i></a></li>
            <li><a href="#">MySQL Developer<i>(12)</i></a></li>
            <li><a href="#">Articles Writter<i>(312)</i></a></li>
            <li><a href="#">Social Networking<i>(312)</i></a></li>
          </ul>
          <h2>Hardware/Networking</h2>
          <ul>
            <li><a href="#">PHP Developer<i>(312)</i></a></li>
            <li><a href="#">MySQL Developer<i>(12)</i></a></li>
            <li><a href="#">Articles Writter<i>(312)</i></a></li>
            <li><a href="#">Social Networking<i>(312)</i></a></li>
          </ul>
          <div class="clear"></div>
        </div>
        <div class="coulmn">
          <h2>SEO/Content</h2>
          <ul>
            <li><a href="#"> SEO/Contentr<i>(312)</i></a></li>
            <li><a href="#">Project Manager<i>(12)</i></a></li>
            <li><a href="#">Junior Support Staff<i>(312)</i></a></li>
            <li><a href="#">HR technical<i>(312)</i></a></li>
          </ul>
          <h2>UI/UX/Creative</h2>
          <ul>
            <li><a href="#">Graphic Designer<i>(312)</i></a></li>
            <li><a href="#">Creative Director<i>(12)</i></a></li>
         
          </ul>
          <div class="clear"></div>
        </div>
      </div>
    </div>
    <div class="right-content_2">
      <h1>Recruit the right <strong>Candidate</strong><br />
        for your <strong>Business</strong></h1>
      <ul>
        <li>Efficient and Cost-effective way to hire online</li>
        <li>Job Posting according to your talent needs and complete management system.</li>
        <li>Search the online database with more than millions of searchable resumes. </li>
        <li>Cutting edge Branding Solution fulfils all your advertising needs.</li>
      </ul>
      <div class="space"></div>
     <!-- <div > <a class="btn-big" href="#">Post Jobs Now</a> </div> -->
     </div>
  </section>
  <div class="clear"></div>  
</div>
</div>
<div class="clear"></div>
<%@include file="footer.jsp" %>
</body>
</html>
