<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
       <h1>StackKoberFlow</h1>
       <form action = "/" method = "get">
           <label for = "qTitle">Поиск по названию вопроса: </label>
           <input type="text" name = "title" id = "qTitle"/>
           <input type = "submit" value = "Найти">
       </form>
        <h4>Вопросы: </h4>
        <#list questions as question>
            <div>
                <p>
                    <b>${question.title}</b>
                    ${question.questionItself}
                    <a href ="/question/${question.id}">Подробнее</a>
                </p>
                <#else>
                    <h3>Вопросов нет</h3>
            </div>
        </#list>
        <h3> Задать вопрос </h3>
        <form action="/question/ask" method="post" enctype="multipart/form-data">
            <label for = "questionTitle">Краткая суть вопроса: </label>
            <input type="text" name = "title" id = "questionTitle"/>
            <label for = "questionItself">Сам вопрос: </label>
            <input type = "text" name="questionItself" id = "questionItself"/>
            <label for = "authorName">Имя автора</label>
            <input type = "text" name = "author" id = "authorName"/>
            <h3>Прикрепите изображения: </h3>
            <div class = "images">
            </div>
            <input type = "submit" value = "Задать">
        </form>
       <button onclick="addPlaceForImage()">+</button>
        <!--script>
            let imageCounter = 0;
            let imageDiv = '<div class = "image"> <label for = "imagePlace' + imageCounter +'"> <input type = "file" name = "images" id = "imagePlace' + imageCounter +'"> </div>'
            let imagePlaceHolder = document.querySelector(".images")


            function addPlaceForImage(){
                console.log("added some image");
                imagePlaceHolder.innerHTML += imageDiv;
                imageCounter++;
            }
        </script-->
    </body>
</html>