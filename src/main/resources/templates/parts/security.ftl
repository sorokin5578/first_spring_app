<#assign
known=Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign user=Session.SPRING_SECURITY_CONTEXT.authentication.principal>

    <#assign nameUser=user.getUsername()>

    <#assign isAdmin=user.isAdmin()>


<#else>
    <#assign nameUser = "unknown">
    <#assign isAdmin=false>
</#if>