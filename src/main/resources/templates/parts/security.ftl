<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getFirstName()
        isAdmin = user.isAdmin()
        id = user.getId()
    >
<#else>
    <#assign
        name = "unknown"
        isAdmin = false
    >
</#if>