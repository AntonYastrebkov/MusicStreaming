<#import "parts/login.ftl" as login>
<#import "parts/common.ftl" as common>

<@common.page>
${message!}
<@login.login "/login" false />
</@common.page>