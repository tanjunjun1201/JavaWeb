<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">

        $(function () {


            $("#is_img").click(function () {
                this.src = "${basePath}Kaptcha.jpg?d=" + new Date();
            });
            $("#sub_btn").click(function () {
                var usernameval = $("#username").val();
                var usernamepatt = /^\w{5,12}$/;
                if (!usernamepatt.test(usernameval)) {
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                }
                var passwordval = $("#password").val();
                var passwordpatt = /^\w{5,12}$/;
                if (!passwordpatt.test(passwordval)) {
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }

                var repwdval = $("#repwd").val();
                if (repwdval != passwordval) {
                    $("span.errorMsg").text("密码不一致");
                    return false;
                }

                var emailval = $("#email").val();
                var emailpatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailpatt.test(emailval)) {
                    $("span.errorMsg").text("邮箱格式不正确");
                    return false;
                }

                var codeval = $("#code").val();
                codeval = $.trim(codeval);
                if (codeval == null || codeval == "") {
                    $("span.errorMsg").text("验证码不能为空");
                    return false;
                }


                $("span.errorMsg").text("");


            });


        })


    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
                        ${requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1" name="username" id="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               value="${requestScope.email}"
                               autocomplete="off" tabindex="1" name="email" id="email"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" name="code" style="width: 80px" id="code"/>
                        <img id="is_img" alt="" src="Kaptcha.jpg"
                             style="float: right; margin-right: 40px;width: 110px;height: 30px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>