<!DOCTYPE html>
<html>
    <head>
        <title></title>
    </head>
    <body>
       <h1>StackKoberFlow</h1><hr>
       <#if user?? && user.email??>
           <h3>Имя пользователя: <i>${user.username}</i></h3>
           <form action = "/logout" method="post">
               <input type="hidden" name = "_csrf" value = "${_csrf.token}">
               <input type="submit" value="Выйти">
           </form>

       <#else>
           <a href = "/login">Войти</a>
       </#if>
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
       <#if user?? && user.email??>
        <a href = "/question/ask/">Задать вопрос</a>
       </#if>
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