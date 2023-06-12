<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<h1>결제 성공</h1>
<input type="text" value="${tid}" id="tid" placeholder="tid">
</body>
<script>
    let tid = document.getElementById("tid");
    let returnObject = {
        status: "OK",
        tid: tid.value,
    }
    window.opener.getReturnValue(JSON.stringify(returnObject));
    // window.returnValue = "OK";
    window.close();
</script>
</html>