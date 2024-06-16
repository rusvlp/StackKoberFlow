<#import "blocks/template.ftlh" as t>
<@t.template user>

    <div style="height: 20px; width: 600px; margin: 0 auto; text-align: center;"></div>
    <div class="maindiv2">

        <div class="infoaboutq" style="padding-top: 10px">Информация о вопросе</div>
        <div class="kratko">Краткая суть вопроса: ${question.title}</div>
        <div class="samquestion">Сам вопрос: ${question.questionItself}</div>
        <div class="autor">Автор вопроса: <a style="text-decoration: none; color: black" href = "/user/${question.author.id}">${question.author.email}</a></div>
        <div class="samquestion">Категория:
            <#if category??>
                ${category.title}
            <#else>
                вопрос без категории
            </#if>
        </div>
        <div class="rate_flex"><div class="counter_rating" id="counter_rating1">Рейтинг: ${question.rating}</div> </div>
        <form action="/question/${question.id}/increaseRating" method="post">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}">
            <input type="submit" class="updown1" name="rateup1" value="Повысить рейтинг" id="rateup1">
        </form>
        <form action="/question/${question.id}/decreaseRating" method="post">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}">
            <input type="submit" class="updown2" name="ratedown1" value="Понизить рейтинг" id="ratedown1">
        </form>
        <div class="kratko">Изображения к вопросу: <br>
            <#list images as image>
                <div onclick="showImage(${image.id})" class = "imageReveal">
                    <img  src = "/images/${image.id}">
                </div>
            <#else>
                    <h3>Изображений нет</h3>
            </#list>
        </div>
        <!--button type="button" name="searchbtn2" style="margin-bottom: 20px; margin-left: 10px;"><naiti> <a href="deletequestion.html"><aa>Удалить вопрос</aa></a> </naiti></button-->
        <#if user?? && user.email?? && user.id == question.author.id>
            <form action="/question/delete/${question.id}" method="post">
                <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                <input type="submit" name="searchbtn2" class="sbtn2" value="Удалить вопрос">
            </form>
        </#if>
        <button type="button" name="toindex" style="margin-bottom: 20px; margin-left: 10px;"><a href="/"><toi>Вернуться на главную</toi></a></button>
        <!--form action="/ababa" method="post">
            <input type="submit" name="toindex" class="sbtntoi" value="Вернуться на главную">
        </form-->
    </div>

    <#if user?? && user.email??>
        <div class="otvet" style = "width: 1000px">
            <div class="answer">Ответить на вопрос:</div>
            <form action="#" method="dialog" id = "answerForm">
                <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                <ep>Ваш ответ на вопрос:</ep>
                <textarea name="answerItself" class="txtarea"></textarea><br>
                <div class = "numberOfImagesBlock" display = "none">
                    <input type ="hidden" name = "noi" value = "0" id = "noi">
                </div>
            </form>
            <div class="autorization">Прикрепите изображения: </div>
            <div class = "imagesToAdd" style = "display: inline">

            </div>
            <button onclick = "addImage()">+</button>
            <!--button type="button" name="searchbtn2" style="margin-bottom: 20px;"><naiti> <a href="aboutquestion.html"><aa>Ответить</aa></a> </naiti></button-->
            <button class="sbtn2" onclick="addAnswer()">Добавить ответ</button>
        </div>
    <#else>
        <div class = "maindiv2" style="margin-top: 30px; padding: 5px; font-family: 'Trebuchet MS'; width: 990px" >
            <a href = "/login">Войдите</a>, чтобы у вас была возмножность ответить на вопрос
        </div>
    </#if>

    <#if solution??>
        <div class="maindiv2" style="margin-top: 15px;">
            <aa>Этот ответ помечен автором как решение</aa>
            <div class="infoaboutq" style="padding-top: 10px;">Ответ</div>
            <div class="autor">Автор: <a style="text-decoration: none; color: black" href = "/user/${solution.author.id}">${solution.author.email}</a></div>
            <div class="samquestion">Ответ: ${solution.answerItself}</div>

            <div class="rate_flex">Рейтинг: <div class="counter_rating" id="counter_rating2">${solution.rating}</div> </div>
            <form action="/answer/${solution.id}/increaseRating" method="post">
                <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                <input type="submit" class="updown1" name="rateup2" value="Повысить рейтинг" id="rateup2">
            </form>
            <form action="/answer/${solution.id}/decreaseRating" method="post">
                <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                <input type="submit" class="updown2" name="ratedown2" value="Понизить рейтинг" id="ratedown2">
            </form>
            <div class="kratko">Изображения к ответу: </div>
            <#list solution.image as image>
                <div onclick="showImage(${image.id})" class = "imageReveal">
                    <img  src = "/images/${image.id}">
                </div>
            <#else>
                <div class = "kratko">
                    Нет изображений
                </div>
            </#list>


            <#if user?? && user.email?? && user.id == question.author.id>
                <form action="/answer/${solution.id}/demark" method="post">
                    <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                    <input type="submit" name="reshenie" class="sbtntoi" value="Снять пометку решения">
                </form>
             </#if>
             <#if user?? && user.email?? && user.id == solution.author.id>
                <form action="/answer/${solution.id}/delete" method="post">
                    <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                    <input type="submit" name="searchbtn2" class="sbtn2" value="Удалить ответ">
                </form>
            </#if>
            <!--button type="button" name="searchbtn2" style="margin-bottom: 20px; margin-left: 10px;"><naiti> <a href="deletequestion.html"><aa>Удалить ответ</aa></a> </naiti></button-->
        </div>
    </#if>

    <#list answers as answer>
    <div class="maindiv2" style="margin-top: 15px;">
        <div class="infoaboutq" style="padding-top: 10px;">Ответ</div>
        <div class="autor">Автор: <a style="text-decoration: none; color: black" href = "/user/${answer.author.id}">${answer.author.email}</a></div>
        <div class="samquestion">Ответ: ${answer.answerItself}</div>
        <div class="rate_flex">Рейтинг: <div class="counter_rating" id="counter_rating2">${answer.rating}</div> </div>
        <form action="/answer/${answer.id}/increaseRating" method="post">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}">
            <input type="submit" class="updown1" name="rateup2" value="Повысить рейтинг" id="rateup2">
        </form>
        <form action="/answer/${answer.id}/decreaseRating" method="post">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}">
            <input type="submit" class="updown2" name="ratedown2" value="Понизить рейтинг" id="ratedown2">
        </form>
        <div class="kratko">Изображения к ответу: </div>
            <#list answer.image as image>
                <img onclick="showImage(${image.id})" src = "/images/${image.id}">
            <#else>
                <div class = "kratko">
                     Нет изображений
                </div>
            </#list>


        <#if user?? && user.email?? && user.id == question.author.id>
        <form action="/answer/${answer.id}/markAsSolution" method="post">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}">
            <input type="submit" name="reshenie" class="sbtntoi" value="Пометить как решение">
        </form>
        </#if>
        <#if user?? && user.email?? && user.id == answer.author.id>
        <form action="/answer/${answer.id}/delete" method="post">
            <input type="hidden" name = "_csrf" value = "${_csrf.token}">
            <input type="submit" name="searchbtn2" class="sbtn2" value="Удалить ответ">
        </form>
        </#if>
        <!--button type="button" name="searchbtn2" style="margin-bottom: 20px; margin-left: 10px;"><naiti> <a href="deletequestion.html"><aa>Удалить ответ</aa></a> </naiti></button-->
    </div>
    <#else>
        <#if !solution??>
        <div class = "maindiv2" style="margin-top: 30px; padding: 5px; font-family: 'Trebuchet MS'; width: 990px" >
            Ответов пока нет.
        </div>
        </#if>
    </#list>





    <script>
        let imagesContainer = document.querySelector(".imagesToAdd");
        let imagesCounter = 0;



        function addAnswer(){
            let forms = document.forms;
            /*let qTitle = document.getElementById("questionTitle").value;
            let qItself = document.getElementById("questionItself").value;
            let csrf = document.getElementById("csrf").value; */
            let mainFormData = new FormData(document.querySelector("#answerForm"));

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

        function showImage(id){
            document.location.href = "/images/"+id;
        }
    </script>
</@t.template>