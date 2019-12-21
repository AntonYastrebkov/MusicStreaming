<#import "parts/common.ftl" as common>

<@common.page>
User Editor

<form method="post" action="/user">
    <input type="text" value="${user.username}" name="username" />
    <input type="email" value="${user.email}" name="email" />
    <input type="text" value="${user.firstName}" name="firstName" />
    <input type="text" value="${user.lastName}" name="lastName" />
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}/>${role}</label>
    </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId" />
    <input type="hidden" value="${_csrf.token}" name="_csrf" />
    <button type="submit">Save</button>
</form>
</@common.page>