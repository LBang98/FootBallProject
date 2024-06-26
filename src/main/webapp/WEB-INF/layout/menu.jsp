<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@400..700&family=Dancing+Script:wght@400..700&family=East+Sea+Dokdo&family=Jua&family=Gaegu&family=Gamja+Flower&family=Pacifico&family=Single+Day&display=swap"
          rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <style>
        body * {
            font-family: 'Jua';
        }
        div.menu {
            background-color: #4071e3;
            text-align: center;
        }
        div.bt button {
            width: 120px;
            height: 50px;
            line-height: 30px;
            background-color: #ffffff;
            border: 2px solid #4071e3;
            border-radius: 20px;
            padding: 10px 20px;
            margin: 0 10px;
            font-size: 18px;
            color: #4071e3;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }
        div.bt button:hover {
            background-color: #a0b4e5;
            color: #ffffff;
            transform: translateY(-10px); /* 위로 5px 이동 */
            transition: transform 0.2s; /* 부드러운 이동 효과 */
        }
    </style>
</head>
<c:set  var="root" value="<%=request.getContextPath()%>"/>
<script type="text/javascript">
    $(function (){
        $("#loginfrm").submit(function (e){
            //기본 이벤트 무효화
            e.preventDefault();
            //폼안의 입력값 읽기
            let fdata = $(this).serialize();
            //alert(fdata);
            $.ajax({
                type:"get",
                dataType:"json",
                url:`${root}/member/login`,
                data:fdata,
                success:function (data){
                    if(data.status == 'success'){
                        //페이지 새로고침
                        location.reload();
                    }
                    else {
                        alert("아이디 또는 비밀번호가 맞지않습니다")
                    }
                }
            });
        });
        //로그아웃 버튼
        $("#btnlogout").click(function (){
            $.ajax({
                type:"get",
                dataType: "text",
                url: `${root}/member/logout`,
                success:function (){
                    //전체 페이지 새로고침
                    location.reload();
                }
            })
        });

    }); //close function
</script>

<body>

<!-- 로그인 모달 다이얼로그 -->
<div class="modal" id="myLoginModal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">회원 로그인</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <form id="loginfrm">
                <!-- Modal body -->
                <div class="modal-body">
                    <table class="table table-bordered">
                        <caption align="top">
                            <label>
                                <input type="checkbox" name="saveid" ${sessionScope.saveid == null or sessionScope.saveid == 'no'?"":"checked"}>
                                &nbsp;아이디저장
                            </label>
                        </caption>
                        <tr align="center">
                            <th class="table-success" width="80" style="background-color: #a0b4e5">아이디</th>
                            <td>
                                <input type="text" name="myid" class="form-control" required="required"
                                       value="${sessionScope.saveid != null and sessionScope.saveid =='yes'?sessionScope.loginid:''}">
                            </td>
                        </tr>
                        <tr align="center">
                            <th class="table-success" width="80" style="background-color: #a0b4e5">비밀번호</th>
                            <td>
                                <input type="password" name="pass" id="pass" class="form-control"
                                       required="required">
                            </td>
                        </tr>
                    </table>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-sm btn-danger" data-bs-dismiss="modal" style="background-color: #a0b4e5; border: 0px solid black; height: 30px;"
                            id="btnmemberlogin">로그인</button>
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal" style="background-color: #a0b4e5; border: 0px solid black; height: 30px; line-height: 20px;"
                            id="btnclose">닫기</button>
                </div>
            </form>
        </div>
    </div>
</div>


<div class="menu">
    <div class="bt" style="margin-left: 500px;">
        <button type="button" onclick="location.href='${root}/'">Home</button>
        <button type="button" onclick="location.href='${root}/schedule/pl?month=5'">일정</button>
    <button type="button" onclick="location.href='${root}/rank/pl'">순위</button>
    <button type="button" onclick="location.href='${root}/board/list'">게시판</button>
        <c:if test="${sessionScope.loginok==null}">
            <button type="button" id="btnlogin" data-bs-toggle="modal" data-bs-target="#myLoginModal" style=" margin-left: 200px;">로그인</button>
            <button type="button" onclick="location.href='${root}/login/form'">회원가입</button>
        </c:if>
        <c:if test="${sessionScope.loginok!=null}">
            <c:set var="stpath" value="https://kr.object.ncloudstorage.com/bitcamp-bh-98/football"/>


            <input type="hidden" value="${dto.num}">
            <!-- dto.num을 가져오기 위해 DTO 객체를 Model로부터 전달 -->
<%--            <c:set var="num" value="${sessionScope.dto.num}" /> <!-- sessionScope에서 num 값을 가져옵니다 -->--%>
            <a href="${root}/member/updateform?id=${sessionScope.loginid}">
                <b style="color: white; font-size: 20px; margin-left: 200px;">${sessionScope.loginid} 님</b>
            </a>

            <button type="button" id="btnlogout" style="margin-left: 20px;">로그아웃</button>
        </c:if>



    </div>
</div>


</body>
</html>
