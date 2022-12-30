<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
<h1 style="text-align: center">Мои рецепеты</h1>
<hr>
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