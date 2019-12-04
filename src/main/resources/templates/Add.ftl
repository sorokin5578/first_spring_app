<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>
    <i>Add new student</i>
    <@l.addAndUp "/Add" />
    <#if messageAdd??>
        ${messageAdd}
    </#if>
</div>
</@c.page>