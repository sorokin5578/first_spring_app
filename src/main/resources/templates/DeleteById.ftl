<#import "parts/common.ftl" as c>
<@c.page>
<div>
    <i>Delete student by id new</i>
    <form method="post" action="/DeleteById">
        <input type="text" name="text" placeholder="Введите id студента" <#if text??> value="${text}"</#if > /></br>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Удалить</button>
    </form>
    <#if messageDel??>
        ${messageDel}
    </#if>
</div>
</@c.page>