<%--
  Created by IntelliJ IDEA.
  User: almehairbi
  Date: 2/17/17
  Time: 4:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<c:url value="/resources/jwplayer/jwplayer.js"/>"></script>
    <script>jwplayer.key="+ywswpU5/MVu+xRl0KvI9tVpMEsbBWHJyIkRkw==";</script>
</head>
<body>


<div style="width: 100%;display: inline-block;">

    <div style="float:left;width:300px;height:240px;">
        <div id="myElement">Loading the player ...</div>
        <script type="text/javascript">
            jwplayer("myElement").setup({
                file: "${url}",
                height: 200,
                width: 300
            });
        </script>
    </div>

</body>
</html>
