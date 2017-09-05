<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style-form.css">
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<#include "/common/header.ftl">

<div class="container">
    <div class="section">
        <div class="login">
            <h1>Log in</h1>
            <form method="post" action="/login">
                <div class="input-field col s6">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="login" name="login" type="text" class="validate">
                    <label for="login">Username</label>
                </div>
                <div class="input-field col s6">
                    <i class="material-icons prefix">vpn_key</i>
                    <input id="password" name="password" type="password" class="validate">
                    <label for="password">Password</label>
                </div>
            <#if error??>
                Invalid username or pass
            </#if>
            <#if msg?? >
            ${msg}
            </#if>
            <#if logout?? >
                you successfully logout
            </#if>
                <p class="submit"><input type="submit" name="login" value="Войти"></p>
            </form>
        </div>

        <div class="login-help">
            <a href="/restore">Забыли пароль?</a>
            <a href="/registration">Регистрация</a>
        </div>
    </div>
    <br><br>
</div>


<#include "/common/footer.ftl">
</html>