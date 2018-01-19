<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <meta charset="utf-8" />
    <title>脚手架</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="/static/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="/static/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL PLUGINS -->

    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="/static/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="/static/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
    <!-- END THEME GLOBAL STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="/static/assets/pages/css/login-2.min.css" rel="stylesheet" type="text/css" />
    <!-- END PAGE LEVEL STYLES -->

     </head>
<!-- END HEAD -->

<body class=" login">
    <!-- BEGIN LOGO -->
    <div class="logo">
            <h1 class="font-white bold">脚手架</h1>
    </div>
    <!-- END LOGO -->

    <!-- BEGIN LOGIN -->
    <div class="content">
        <!-- BEGIN LOGIN FORM -->
        <form class="login-form" action="/login" method="post">
            <div class="form-title">
                <span class="form-title">欢迎来到脚手架平台.</span>
                <span class="form-subtitle">请登陆.</span>
            </div>
            <div class="alert alert-danger ${message==null? 'display-hide' : ''}">
                <button class="close" data-close="alert"></button>
                <span> ${message==null? "请输入账号或者密码" : message} </span>
            </div>
            <div class="form-group">
                <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                <label class="control-label visible-ie8 visible-ie9">用户名</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username" /> </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">密码</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password" /> </div>
            <c:if test="${isValidateCodeLogin}">
                <div class="form-group">
                    <img id="imgValidateCode" src="/validate/code" style="cursor: pointer;" />
                    <input id="validateCode" name="validateCode" type="text" class="form-control pull-left" placeholder="验证码" style="width: 170px;">
                </div>
            </c:if>
            <div class="form-actions">
                <button type="submit" class="btn red btn-block uppercase">登陆</button>
            </div>
            <div class="form-actions">
                <div class="pull-left">
                    <label class="rememberme mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" name="remember" value="1" />记住我
                        <span></span>
                    </label>
                </div>
                <div class="pull-right forget-password-block">
                    <a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
                </div>
            </div>
        </form>
    </div>
        <!-- END LOGIN FORM -->

    <!-- BEGIN CORE PLUGINS -->
    <script src="/static/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="/static/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/static/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
    <script src="/static/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="/static/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="/static/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
    <!-- END CORE PLUGINS -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <script src="/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="/static/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
    <script src="/static/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL SCRIPTS -->
    <script src="/static/assets/global/scripts/app.min.js" type="text/javascript"></script>
    <!-- END THEME GLOBAL SCRIPTS -->
    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <script src="/static/assets/pages/scripts/login.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL SCRIPTS -->
    <script type="text/javascript">
        // 验证码切换
        $("#imgValidateCode").bind("click", function () {
            $(this).attr("src", "/validate/code?" + Math.random());
        });
    </script>
</body>
</html>
