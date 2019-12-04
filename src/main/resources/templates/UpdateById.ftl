<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div>
    <i>Update student by id</i>
    <@l.addAndUp "/UpdateById" />
    <#if messageUp??>
        ${messageUp}
    </#if>
</div>
</@c.page>