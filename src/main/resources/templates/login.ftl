<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <@l.login "/login" false/>
    <#if message??>
        ${message}
    </#if>
</@c.page>
