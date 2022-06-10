<!DOCTYPE html>
<html>
    <head>
        <title>Задать вопрос</title>
    </head>
    <body>
         <h1>StackKoberFlow</h1>
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
         <button onclick="askQuestion()">Задать</button>
        <script>
            let imagesContainer = document.querySelector(".images");
            let imagesCounter = 0;

            function askQuestion(){
                let forms = document.forms;
                let qTitle = document.getElementById("questionTitle").value;
                let qItself = document.getElementById("questionItself").value;
                let csrf = document.getElementById("csrf").value;
                let mainFormData = new FormData(forms[0]);
                console.log(csrf);
                let question = {
                    title: qTitle,
                    questionItself: qItself,
                    _csrf: csrf,
                    noi: imagesCounter
                }



                fetch('/question/add/', {
                    method: 'POST',
                    body: mainFormData
                }).then((data) => {
                    return data.text()
                }).then(id => {
                    let images;

                    for (let i = 1; i<forms.length; i++){
                        let formData = new FormData(forms[i]);
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
    </body>
</html>