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
    ${question.author} <br>
    <div class = "images">
        <#list images as image>
            <img src = "/images/${image.id}">
        </#list>
    </div>
    <form action = "/question/delete/${question.id}" method = "post">
        <input type = "submit" value = "Удалить вопрос">
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