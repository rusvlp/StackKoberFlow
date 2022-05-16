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
             <input type = "text" name="questionItself" id = "questionItself"/> <br>
             <label for = "authorName">Имя автора</label>
             <input type = "text" name = "author" id = "authorName"/>
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
                let qTitle = document.getElementById("questionTitle").value;
                let qItself = document.getElementById("questionItself").value;
                let authorName = document.getElementById("authorName").value;
                let question = {
                    title: qTitle,
                    questionItself: qItself,
                    author: authorName,
                    noi: imagesCounter
                }



                fetch('/question/add/', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(question)
                }).then((data) => {
                    return data.text()
                }).then(id => {
                    let images;
                    let forms = document.forms;
                    for (let i = 1; i<forms.length; i++){
                        let formData = new FormData(forms[i]);
                        console.log("senene")
                        fetch("/question/" + id + "/addImage", {
                            method: 'POST',
                            body: formData
                        })
                    }
                })
            }
            function addImage(){
                let newImageBlock = '<div class = "addimage"><form method="dialog" name = "form' + imagesCounter+'" enctype="multipart/form-data" class = "imageform' + imagesCounter+ '"><input type = "file" name = "file" class = "image' + imagesCounter +'"/></form></div>'
                imagesContainer.innerHTML += newImageBlock;
                imagesCounter++;
            }

        </script>
    </body>
</html>