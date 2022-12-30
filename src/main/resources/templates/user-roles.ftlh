<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
<!DOCTYPE html>
<html>
<head>
    <title>Don't joke with the food</title>
</head>
<body>
<h1>Don't joke with the food</h1>
<h3>Редактирование роли пользователя</h3>
<#list roles as role>
<form action="/admin/edit/user/" method="post">
<label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>${role}</label>
    </#list>
    <input type="hidden" value=${user.id} name="userId">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" >Save</button>
</form>
</body>
</html>
</@t.template>