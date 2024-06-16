<#import "blocks/template.ftlh" as t>
<@t.template user>

    <div class="sort_flex">

        <div class="sortirovka" style="padding-top: 5px;"><aa>Сортировать по: </aa>
            <button type="button" name="searchbtn_sort"><a href="/?sort=datenew"><naiti>Дате (сначала новые)</naiti></a></button>
            <button type="button" name="searchbtn_sort"><a href="/"><naiti>Дате (сначала старые)</naiti></a></button>
            <button type="button" name="searchbtn_sort"><a href="/?sort=best"><naiti>Рейтингу (сначала лучшие)</naiti></a></button>
            <button type="button" name="searchbtn_sort"><a href="/?sort=worst"><naiti>Рейтингу (сначала худшие)</naiti></a></button>
        </div>
        <div style="display: flex; flex-direction: column">
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
            <div class="voprosov_net">
                Вопросов нет.
            </div>
        </#list>
        </div>

        <div class="sortirovka_kat" style="padding-top: 5px;"><aa>Категории: </aa>
            <#list categories as category>
            <button type="button" name="searchbtn_sort"><a href="/?category=${category.id}"><naiti>${category.title}</naiti></a></button>
            <#else>
                <aa>Категорий нет</aa>
            </#list>
        </div>

    </div>




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