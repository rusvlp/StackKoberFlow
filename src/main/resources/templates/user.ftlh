<#import "blocks/template.ftlh" as t>
<@t.template user>


    <div class="lk1">
        <div class="kartochka">
            <div class="avatar_2">
                <#if subject.avatar??>
                    <img src="/images/${subject.avatar.id}">
                <#else>
                    <img src="/static/images/blank.png">
                </#if>

            </div>
            <#if user.id == subject.id>
            <form action = "/user/addAvatar" enctype="multipart/form-data" method="post">
                <center>
                <label style="font-family: 'Trebuchet MS'" for = "imgToUpload">Загрузите аватар</label><br>
                <input type="file" name = "image" id = "imgToUpload"/><br>
                <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                <input type="submit" value="Загрузить">
                </center>
            </form>
            </#if>
            <div class="kartochka_name">Id пользователя: ${subject.id}</div>
            <div class="kartochka_name">Эл. почта: ${subject.email}</div>
            <div class="blackline"></div>
            <#if user.id == subject.id>
                <div class="kartochka_password_smena">Смена пароля</div>
                <form action="/user/${user.id}/changePassword" name="publish" id ="regform" method="post" style="margin-left: 20px;">
                    <ep>Новый пароль: </ep><input type="password" class="ep" name="newPassword" id="pass1"><br>
                    <ep>Повторите пароль: </ep><input type="password" class="ep" name="password2" id="pass2"><br>
                    <div class="nopass" id="nopass">Пароли не совпадают!</div>
                    <input type="hidden" name = "_csrf" value = "${_csrf.token}">

                </form>
                <#if errorMsg??>${errorMsg}</#if>
                <button style="margin: 15px;" class = "submit" onclick="submitForm()">Сменить пароль</button>
                <div class="blackline"></div>
                <form action="/user/deactivate" method="post">
                    <input type="submit" name="delete_akk" class="del_sbtn2" value="Удалить аккаунт">
                    <input type="hidden" name = "_csrf" value = "${_csrf.token}">
                </form>
            </#if>
        </div>
        <div class="aq_flex">
            <div class="voprosov_net" style="width: 1000px; margin-top: 0px;">
                <#if user.id == subject.id>Мои вопросы:
                <#else>Вопросы пользователя:
                </#if>
            </div>
            <div class="my_questions" style="margin-left: 40px">

                <#list subject.question as question>
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
        </div>
        <script src = "/static/scripts/scripts3.js"></script>
    </div>
</@t.template>