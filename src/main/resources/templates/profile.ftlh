<#import "blocks/template.ftlh" as t>
<@t.template user>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Profile</title>
</head>
<body>
<h1 style="text-align: center">Личный кабинет</h1>
<hr>
<br>
<div style="text-align: center">
    <#if user.hasAvatar==true>
    <img src="/avatar/${user.getAvatar().getId()}"
         style="border:2px solid black; border-radius: 20px" height="120">
    <br>
    <br>
    </#if>
    <h2>${user.name}</h2>
    <br>
    Электоронная почта: <b>${user.email}</b>
    <br>
    Номер телефона: <b>${user.phoneNumber}</b>
    <br>
    <br>
    <#if user.isAdmin()>
    <a class="btn btn-dark mb-3" style="text-align: center; width: 50%"
       href="/admin">
        Панель администратора
    </a><br>
</#if>
<a class="btn btn-dark mb-3" style="text-align: center; width: 50%"
   href="/my/dishes">
    Мои рецепты
</a>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit" style="text-align: center; width: 50%" class="btn btn-danger mb-2">
        Выйти из аккаунта
    </button>
</form>
<div class="container">
    <details>
        <summary><h4>Добавить блюдо</h4></summary>
        <form action="/menu/addDish" method="post" enctype="multipart/form-data">
            Название блюда:<input type="text" name="name"/><br><br>
            Количество калории:<input type="text" name="calories"/><br><br>
            Описание блюда:<textarea name="description" rows="4" cols="50"></textarea><br><br>
            Происхождение :<input type="text" name="origin"/><br><br>
            Первая фотография :<input type="file" name="file1"/><br><br>
            Вторая фотография :<input type="file" name="file2"/><br><br>
            Третья фотография :<input type="file" name="file3"/><br><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Добавить блюдо"/>
        </form>
    </details>
</div>
</div>
</body>
</html>
</@t.template>