<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
       <h1>StackKoberFlow | <a href = "/login">Войти</a></h1>

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
        <a href = "/question/ask/">Задать вопрос</a>

        <!---script>
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