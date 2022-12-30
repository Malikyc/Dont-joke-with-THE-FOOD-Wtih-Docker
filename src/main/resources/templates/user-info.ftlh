<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
<!DOCTYPE html>
<html>
<head>
    <title>Dont joke with food</title>
</head>
<body>
<div>
<<h1 style="text-align: center">Информация о пользователе</h1>
<hr>
    <div style="text-align: center">
        <#if user.hasAvatar==true>
        <img src="/avatar/${user.getAvatar().getId()}"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
    </#if>
    <h2>${user.name}</h2>
</div>
    <br>
</div>
<br>
<hr>
<h1 style="text-align: center">Товары пользователя ${user.name}</h1>
<br>
<div class="card-columns" id="dish">
    <#list dishes as dish>
    <a href="/menu/${dish.ID}" class="product-link">
        <div class="card text-white  my-3 product">
            <img src="/images/${dish.getPreviewImageid()}"
                 class="card-img-top">
            <div class="card-body">
                <h5 class="card-title">${dish.name}</h5>
                <p class="card-text">${dish.calories} кКал</p>
            </div>
            <div class="card-footer">
                Происхождение:${dish.origin}
            </div>
    </a>
</div>
<#else >
<h3>Рецептов нет</h3>
</#list>
</div>
</body>
</html>
</@t.template>