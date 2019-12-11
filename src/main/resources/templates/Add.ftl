<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>
    <@l.addAndUp "/Add" />
    <#if messageAdd??>
        ${messageAdd}
    </#if>
</div>
</@c.page>