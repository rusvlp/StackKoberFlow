<#import "blocks/template.ftlh" as t>
    <@t.template user>

        <div style="height: 150px; width: 600px; margin: 0 auto; text-align: center;"></div>
        <div class="maindiv">


            <div class="autorization">Задать вопрос</div>
            <form action="/ask" id ="askForm" method="dialog">
                <ep>Краткая суть вопроса:</ep> <input type="text" class="ep" name="title"><br>
                <ep>Сам вопрос:</ep> <textarea name="questionItself" class="txtarea"></textarea><br>
                <input type = "hidden" name = "_csrf" value="${_csrf.token}">
                <ep>Укажите категорию вопроса:</ep>
                    <select name = "cid">
                        <#list categories as category>
                            <option value = "${category.id}">${category.title}</option>
                        <#else>
                            <option value="-1">Нет категорий</option>
                        </#list>
                    </select>

                <div class = "numberOfImagesBlock" display = "none">
                    <input type ="hidden" name = "noi" value = "0" id = "noi">
                </div>
            </form>
            <div class="autorization">Прикрепите изображения: </div>
            <div class="page-block">
                <!--button type="button" name="searchbtn2"><su2>Выберите файл</su2></button> <su2>Файл не выбран</su2-->
            </div>
            <button type="button" onclick = "addImage()" class="addBtn">+</button> <br>
            <button type="button" onclick = "askQuestion()" name="searchbtn2" style="margin-bottom: 20px;"><naiti> <aa>Задать</aa></naiti></button>
        </div>




         <!--h1>StackKoberFlow</h1>
         <h3> Задать вопрос </h3>
         <form action="#" method="dialog" onsubmit = "askQuestion()">
             <label for = "questionTitle">Краткая суть вопроса: </label>
             <input type="text" name = "title" id = "questionTitle"/> <br>
             <label for = "questionItself">Сам вопрос: </label> <br>
             <input type = "text" name="questionItself" id = "questionItself"/>
             <input type="hidden" name = "_csrf" value = "${_csrf.token}" id = "csrf">
             <div class = "numberOfImagesBlock" display = "none">
                 <input type ="hidden" name = "noi" value = "0" id = "noi">
             </div>
         </form>
         <h3>Прикрепите изображения: </h3> <br>
         <div class = "images" style = "display: inline">
         </div>
         <button onclick = "addImage()">+</button>
         <button-- onclick="askQuestion()">Задать</button-->
        <script>
            let imagesContainer = document.querySelector(".page-block");
            let imagesCounter = 0;

            function askQuestion(){
                let forms = document.forms;
                let mainFormData = new FormData(document.querySelector("#askForm"));





                fetch('/question/add/', {
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
                        fetch("/question/" + id + "/addImage", {
                            method: 'POST',
                            body: formData
                        })
                    }
                    document.location.href = "/successCreated"
                })

            }
            function addImage(){
                let newImageBlock = '<div class = "addimage"><form method="dialog" name = "form' + imagesCounter+'" enctype="multipart/form-data" class = "imageform' + imagesCounter+ '"><input type = "file" name = "file" class = "image' + imagesCounter +'"/> <input type="hidden" name = "_csrf" value = "${_csrf.token}"> </form></div>'
                imagesContainer.innerHTML += newImageBlock;
                imagesCounter++;
                document.querySelector(".numberOfImagesBlock").innerHTML = '<input type ="hidden" name = "noi" value = "'+imagesCounter+'" id = "noi">'
            }

        </script>
    </@t.template>
