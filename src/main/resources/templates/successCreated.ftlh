<!DOCTYPE html>
<html>
    <head>
        <title>Вопрос успешно создан</title>
    </head>
    <body>
        <#if user?? && user.email??>
            <p>Имя пользователя: ${user.email}</p>
        </#if>
        <h1>Вопрос успешно задан</h1> <br>
        <a href = "/">На главную</a>
    </body>
</html>