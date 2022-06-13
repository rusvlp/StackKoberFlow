<!DOCTYPE html>
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
</html>
