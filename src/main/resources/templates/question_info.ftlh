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
    <form action="/question/${question.id}/increaseRating" method="post">
        <input type="hidden" name = "_csrf" value = "${_csrf.token}">
        <input type="submit" value = "Увеличить рейтинг">
    </form>
    <form action="/question/${question.id}/decreaseRating" method="post">
        <input type="hidden" name = "_csrf" value = "${_csrf.token}">
        <input type="submit" value = "Уменьшить рейтинг">
    </form>

    <div class = "answers">
        <#list question.answers as answer>
            <h3>Автор: ${answer.author.username}</h3>
            <h4>Ответ: <br> ${answer.answerItself}</h4>
            <h4>Рейтинг: ${answer.rating}</h4>
            <h4>Изображения к ответу: </h4>
            <#list answer.image as img>
                <img src = "/images/${img.id}">
            <#else>
                <h4>Изображений к ответу нет<h4>
            </#list>
        <#else>
            <h3>Ответов нет</h3>
        </#list>
    </div>


    <#if user?? && user.email?? && user.id == question.author.id>
        <form action = "/question/delete/${question.id}" method = "post">
            <input type = "submit" value = "Удалить вопрос">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}" id = "csrf">
        </form>
    </#if>
    <#if user?? && user.email??>


        <p>Ответить на вопрос: </p>
        <form action="#" method="dialog" id = "answerForm">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}" id = "csrf">
            <label for = "answerItself">Ваш ответ на вопрос</label>
            <input type = "text" name = "answerItself" id = "answerItself">
            <div class = "numberOfImagesBlock" display = "none">
                <input type ="hidden" name = "noi" value = "0" id = "noi">
            </div>
        </form>
        <h3>Прикрепите изображения: </h3> <br>
        <div class = "imagesToAdd" style = "display: inline">
        </div>
        <button onclick = "addImage()">+</button>
        <button onclick="addAnswer()">Добавить ответ</button>
    <#else>
        <p>
            <a href = "/login">Войдите</a>, чтобы у вас была возмножность ответить на вопрос
        </p>
    </#if>

    <script>
        let imagesContainer = document.querySelector(".imagesToAdd");
        let imagesCounter = 0;



        function addAnswer(){
            let forms = document.forms;
            /*let qTitle = document.getElementById("questionTitle").value;
            let qItself = document.getElementById("questionItself").value;
            let csrf = document.getElementById("csrf").value; */
            let mainFormData = new FormData(document.querySelector("#answerForm"));
            console.log(csrf);
            /*let question = {
                title: qTitle,
                questionItself: qItself,
                _csrf: csrf,
                noi: imagesCounter
            }*/



            fetch('${question.id}/addAnswer', {
                method: 'POST',
                body: mainFormData
            }).then((data) => {
                return data.text()
            }).then(id => {
                let images;

                for (let i = 0; i<imagesCounter; i++){
                    let formData = new FormData(document.querySelector(".imageform" + i));
                    console.log(id);
                    console.log("senene")
                    fetch("/answer/" + id + "/addImage", {
                        method: 'POST',
                        body: formData
                    })
                }
                document.location.href = "/question/${question.id}"
            })

        }

        function addImage(){
            let newImageBlock = '<div class = "addimage"><form method="dialog" name = "form' + imagesCounter+'" enctype="multipart/form-data" class = "imageform' + imagesCounter+ '"><input type = "file" name = "file" class = "image' + imagesCounter +'"/> <input type="hidden" name = "_csrf" value = "${_csrf.token}"> </form></div>'
            imagesContainer.innerHTML += newImageBlock;
            imagesCounter++;
            document.querySelector(".numberOfImagesBlock").innerHTML = '<input type ="hidden" name = "noi" value = "'+imagesCounter+'" id = "noi">'
        }
    </script>
</body>
</html>