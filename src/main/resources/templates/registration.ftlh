<#import "blocks/template.ftlh" as t>
<@t.template user>
<h2>Регистрация</h2>
<hr>
<form action="/registration" method="post" enctype="multipart/form-data">
    Имя пользователя: <input type="text" name="name"><br><br>
    Эл. почта: <input type="email" name="email"><br><br>
    Номер телефона: <input type="text" name="phoneNumber"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    Добавьте аватарку:<input type="file" name="file1"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>
<#if errorMess??>
    <h2 style="color: red"> ${errorMess}</h2>
</#if>
</@t.template>