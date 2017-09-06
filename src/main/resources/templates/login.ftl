<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style-login.css">
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<#include "/common/header.ftl">

<div class="container">
    <div class="section">
        <div class="message">
        </div>
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
<script>
    $(document).ready(function(){
    <#if error??>
        Materialize.toast('Invalid username or pass', 3000, 'rounded');
    </#if>
    <#if msg?? >
        Materialize.toast('${msg}', 3000, 'rounded');
    </#if>
    <#if logout?? >
        Materialize.toast('u are loged out now', 3000, 'rounded');
    </#if>
    })

</script>



<#include "/common/footer.ftl">
</html>