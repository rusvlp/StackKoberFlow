<#import "blocks/template.ftlh" as t>
<@t.template user>


    <#list questions as question>
        <div class="questioninspisok">
            <div class="flexq">
                <div class="essenceofq">
                    <div class="brief_essence">${question.title}</div>
                    <div class="essence">
                        ${question.questionItself}
                    </div>
                </div>
                <div class="rating">
                    <div class="rating2
                    <#if question.rating gt 0>better</#if>
                    <#if question.rating < 0>worse</#if>"
                     id="rate">${question.rating}</div>
                </div>
            </div>
            <div class="checkq">
                <button type="button" name="searchbtn"> <a href="/question/${question.id}"><naiti>Посмотреть</naiti></a> </button>
            </div>
        </div>
    <#else>
        Вопросов нет
    </#list>




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
    <script src = "/static/scripts/questionRating.js"></script>
</@t.template>