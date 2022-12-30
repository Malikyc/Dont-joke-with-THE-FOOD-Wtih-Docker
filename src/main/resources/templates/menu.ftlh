<#import "blocks/template.ftlh" as t>
<@t.template user>
<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<div class="container-fluid bg-dark text-white"  style="text-align: center; padding-top:20px">
    <form action="/" method="get">
    <h3 >Найти блюдо по названию:</h3>
        <input type="text" name="name" class="form-control"  ><div style="padding-top:30px"> <input type="submit" value="поиск"></div>
    </form>
</div>
<div class="card-columns" id="dish">
<#list menu as dish>
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