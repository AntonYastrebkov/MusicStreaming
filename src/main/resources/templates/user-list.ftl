<#import "parts/common.ftl" as common>

<@common.page>
List of users

<table>
    <thead>
    <tr>
        <th>Username</th>
        <th>Role</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
        <th>Is active</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>${user.active?string('yes', 'no')}</td>
        <td><a href="/user/${user.id}">Edit</a> </td>
    </tr>
    </#list>
    </tbody>
</table>
</@common.page>