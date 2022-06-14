<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/styles.css">
    <title>SOF2</title>
</head>
<body>
<div class="info">

    <button type="button" name="signup"><a href="/login" class="signup"> <su>Войти</su> </a></button>


    <button type="button" name="searchbtn" onclick="searchexec()"><naiti>Найти</naiti></button>
    <form id="search" role="search" action="/" method="get" class="searchline" autocomplete="off">
        <div class="searchbar">
            <input  type="text" placeholder="Поиск..." value="" autocomplete="off" maxlength="240" class="inputfield" aria-label="Поиск"
                    aria-controls="top-search" data-controller="s-popover" data-action="focus->s-popover#show" data-s-popover-placement="bottom-start"
                    aria-expanded="false" name = "title">
            <svg aria-hidden="true" class="svgsearch" width="18" height="18" viewBox="0 0 18 18">
                <path d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z"></path>
            </svg>
        </div>
    </form>
    <div class="headerofsite"><a style="text-decoration: none; color: black" href = "/"> StackKoberFlow </a></div>
    <div class="logo"> <img src="/static/images/soflogo.jpg"> </div>




</div>

<div style="height: 150px; width: 600px; margin: 0 auto; text-align: center;"></div>
<div class="maindiv">

    <div class="autorization">Регистрация</div>
    <form action="/registration" name="publish" method="post" id = "regform">
        <ep>Имя пользователя: </ep><input type="text" class="ep" name="username"><br>
        <ep>Электронная почта: </ep><input type="email" class="ep" name="email"><br>
        <ep>Пароль: </ep><input type="password" class="ep" name="password" id="pass1"><br>
        <div class = "passConf">
            <ep>Повторите пароль: </ep><input type="password" class="ep" name="password2" id="pass2"><br>
        </div>
        <div class="nopass" id="nopass">Пароли не совпадают!</div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">

    </form>
    <button onclick="submitForm()">Регистрация</button>
    <div class="blackline"></div>
    <div class="registration">Есть аккаунт? <a href="/" class="registration"> <reg>Войдите!</reg> </a></div>
    <div class="blackline"></div>
    <div class="toindex" style="padding-bottom: 15px;">
        <button type="button" name="toindex3"><a href="index.html"><toi>Вернуться на главную</toi></a></button>
    </div>
    <#if errorMessage??>
        <p style="font-family: 'Trebuchet MS';color: red">${errorMessage}</p>
    </#if>
</div>


<script src="/static/scripts/scripts3.js"></script>
</body>
</html>

<!--DOCTYPE html>
<html>
    <head>
        <title>Зарегистрироваться</title>
    </head>
    <body>
        <h1>StackKoberFlow</h1>
        <h4>Регистрация</h4>
        <form action = "/registration" method="post">
            Имя пользователя: <input type = "text" name = "username"><br>
            Электронная почта: <input type = "email" name = "email"><br>
            Пароль: <input type = "password" name = "password"><br>
            <input type = "hidden" name = "_csrf" value="${_csrf.token}">
            <input type = "submit" value="Регистрация">
            <#if errorMessage??>
                <h2 style="color: red">${errorMessage}</h2>
            </#if>
        </form>
    </body>
</html-->
