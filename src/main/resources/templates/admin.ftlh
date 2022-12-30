<#import "blocks/template.ftlh" as t>
<@t.template user>
<table class="table table-bordered table-hover">
    <tr>
        <th>Email</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user>
        <tr>
         <th>${user.email}</th>
        <th><#if user.active>true<#else>false</#if></th>
        <th><#list user.roles as role>${role},<#sep>, </#list></th>
        <th><form action="/admin/ban/user/${user.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" class="btn btn-warning" value="Бан">
            </form> </th>
            <th><a href="/admin/edit/user/${user.id}">Редактировать</a> </th>
    <th><a href="/user/${user.id}">Подробная информация</a> </th>
        </tr>
            <#else>
                <h3>Пользователей нет</h3>
    </#list>
</table>
</@t.template>