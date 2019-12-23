<#import "parts/login.ftl" as login>
<#import "parts/common.ftl" as common>

<@common.page>
<div class="mb-1">Add new user</div>
${message!}
<@login.login "/registration" true />
</@common.page>