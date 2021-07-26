
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="js/jquery-1.11.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="css/bootstrap-theme.css" />

    <script>
        $(function(){
            $("#myForm").submit(function(){
                var flag = validateUserName() && validatePassword();
                if(!flag){
                    alert("有字段未完成！");
                    return false;
                }else {
                    return true;
                }
            });
            $("#userName").keyup(function(){
                validateUserName();
            });
            $("#userName").blur(function(){
                if(validateUserName()){
                    var username = $(this).val();
                    $.ajax({
                        url:"/testusername",
                        data: "username="+ username,
                        type:"get",
                        success: function(rs){
                            console.log(rs);
                            $("#userNameMsg").html(rs);
                        },
                        dataType:"text"
                    });
                }
            });
            $("#userPass").keyup(function(){
                validatePassword();
            });
        });

        function validateNull(value) {
            return !(value == null || value === "");
        }

        //用户名验证规则：必须是超过6位数，仅允许数字、大写字母、小写字母、下划线，不能以下划线开头
        function validateUserName() {
            var userName = $("#userName").val();
            var regex = /[A-Za-z0-9]\w+/;
            var msg = $("#userNameMsg");
            if(regex.test(userName)) {
                msg.css("color", "green");
                msg.html("用户名合法");
                return true;
            } else {
                if(!validateNull(userName)) {
                    msg.html("输入不为空！");
                } else if(userName.match(/_\w*/)) {
                    msg.html("第一个字符不允许为下划线！");
                } else if(userName.length < 2) {
                    msg.html("用户名必须至少2位");
                } else {
                    msg.html("包含非法字符！仅允许数字、字母、下划线");
                }
                msg.css("color", "red");
                return false;
            }
        }

        //密码验证规则：必须是超过6位数，包含数字、大写字母、小写字母
        function validatePassword() {
            var password = $("#userPass").val();
            var regex1 = /[0-9]+[a-z]+[A-Z]+/;
            var regex2 = /[a-z]+[0-9]+[A-Z]+/;
            var regex3 = /[0-9]+[A-Z]+[a-z]+/;
            var regex4 = /[a-z]+[A-Z]+[0-9]+/;
            var regex5 = /[A-Z]+[0-9]+[a-z]+/;
            var regex6 = /[A-Z]+[a-z]+[0-9]+/;

            var msg = $("#passwordMsg");
            if(password.length < 6) {
                msg.html("长度不足6位！");
                msg.css("color", "red");
                return false;
            }
            if(regex1.test(password) || regex2.test(password) || regex3.test(password) || regex4.test(password) || regex5.test(password) || regex6.test(password)) {
                msg.html("密码有效");
                msg.css("color", "green");
                return true;
            } else {
                if(!validateNull(password)) {
                    msg.html("输入为空！");
                } else {
                    msg.html("密码无效，必须包含数字、大小写字母！");
                }
                msg.css("color", "red");
                return false;
            }
        }
    </script>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    String str = "";
    if(cookies != null && cookies.length > 0){
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("uname")){
                str = cookie.getValue();
                break;
            }
        }
    }
    pageContext.setAttribute("mycookie", str);
%>
    <div class="container">
        <h2 class="text-center">用户登录</h2>
        <form id="myForm" role="form" class="form-horizontal" action="test" method="post">
            <div class="form-group">
                <label class="control-label col-sm-1" for="userName">用户名：</label>
                <div class="col-sm-5">
                    <input class="form-control" type="text" name="name" id="userName" value="${mycookie}"/>
                </div>
                <div class="col-sm-6">
                    <label id="userNameMsg" class="control-label text-info">仅允许数字、大小写字母、下划线，第一个字符不能为下划线</label>
                </div>

            </div>
            <div class="form-group">
                <label class="control-label col-sm-1" for="userPass">密码：</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" name="pass" id="userPass"/>
                </div>
                <div class="col-sm-6">
                    <label id="passwordMsg" class="control-label text-info text-left">密码至少6位，需要包含大小写字母和数字</label>
                </div>

            </div>
            <div class="form-group text-center">
                <button type="submit" class="btn btn-info col-sm-3 col-sm-offset-3">登录</button>

                <button type="reset" class="btn btn-normal col-sm-3">重置</button>
            </div>
        </form>
    </div>
</body>
</html>