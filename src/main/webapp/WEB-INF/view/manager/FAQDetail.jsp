<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://unpkg.com/mvp.css@1.12/mvp.css">
</head>
<body>
<table>
    <thead>
        <tr>
            <th>제목</th>
            <th>${faq.title}</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>내용</td>
            <td>${faq.content}</td>
        </tr>
    </tbody>
</table>
<button onclick="updateFAQ()">수정하러가기</button>
<button onclick="deleteFAQ()">삭제하기</button>
<script>
    function updateFAQ(){
        location.href = "/manager/faq/update/"+${faq.id}
    }

    function deleteFAQ(){
        if(confirm("삭제하시겠습니까")){
            fetch("/manager/delete/faq?id="+${faq.id},{
                method: "delete",
            }).then(async(res) => {
                let response = await res.json();
                console.log(response)
                switch (response.status_code){
                    case 200:
                        alert("삭제 완료가 되었습니다");
                    case 403:
                        alert("재 로그인 해주세요!")
                }
                location.href = response.redirect_uri;
            })
        }
    }
</script>
