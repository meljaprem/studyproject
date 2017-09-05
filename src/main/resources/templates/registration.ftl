<#include "/common/header.ftl">

<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br><br>
        <h1 class="header center orange-text">Registration</h1>
        <#--<div class="row center">-->
            <#--<h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>-->
        <#--</div>-->
        <br><br>

    </div>
</div>

<div class="container">
    <div class="section">
        <div class="row">
            <form class="col s12" method="post" id="form-reg">
                <div class="row">
                    <div class="input-field col s6">
                        <input id="name"  name="name" type="text" class="validate">
                        <label for="name">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="surname" name="surname" type="text" class="validate">
                        <label for="surname">Last Name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s6">
                        <input id="username"  name="username" type="text" class="validate">
                        <label for="username">Username</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="password" name="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="email"  name="email" type="email" class="validate">
                        <label for="email">Email</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="city" name="city" type="text" class="validate">
                        <label for="city">City</label>
                    </div>
                </div>
                <div class="row">
                    <button id='send' class="btn waves-effect waves-light" type="submit" name="action" >Register
                        <i class="material-icons right">send</i>
                    </button>
                </div>
            </form>
        </div>

    </div>
    <br><br>
</div>

<script>
    $(document).ready(function() {
       var ser =  $("#form-reg").serialize();
        $('#post_form').submit(function(){
            $.post("/registration", ser,  function(response) {
            });
        });
    });
</script>

<#include "/common/footer.ftl">