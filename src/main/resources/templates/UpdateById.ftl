<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>
    <@l.addAndUp "/UpdateById" />
    <#if messageUp??>
        ${messageUp}
    </#if>
</div>
</@c.page>