<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>StackKoberFlow</h1>
    <h4>Информация о вопросе</h4>
    <b>Краткая суть вопроса: </b> ${question.title} <br>
    <b>Сам вопрос: </b> <br>
    ${question.questionItself} <br>
    <b>Рейтинг вопроса: </b>
    ${question.rating} <br>
    <b>Автор вопроса: </b>
    ${question.author.username} <br>
    <div class = "images">
        <#list images as image>
            <div class = "sad">
                <img src = "/images/${image.id}">
            </div>
            <#else>
            <div>
                <h3>Изображений нет</h3>
            </div>
        </#list>
    </div>
    <div class = "answers">
        <#list question.answers as answer>
            <h2>Автор: ${answer.author.username}</h2> <br>
            <h3>Ответ: <br> ${answer.answerItself}</h3>
            <h3>Рейтинг ${answer.rating}</h3>
        <#else>
            <h3>Ответов нет</h3>
        </#list>
    </div>
    <form action = "/question/delete/${question.id}" method = "post">
        <input type = "submit" value = "Удалить вопрос">
        <input type="hidden" name = "_csrf" value = "${_csrf.token}" id = "csrf">
    </form>
    <!--script>
        let imageCount = ${question.numberOfImages()}
        let imagesContainer = document.querySelector(".images");
        for (let i = 0; i<imageCount; i++){
            imagesContainer.innerHTML += '<img src = "/images/"' + i + '>'
        }
    </script-->
</body>
</html>