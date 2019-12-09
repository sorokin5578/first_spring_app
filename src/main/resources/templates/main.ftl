<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout />
        </br><a href="/user">User list</a>
    </div>
    <div>
        </br>
        <a href="/Add">Add new student</a></br></br>
        <a href="/DeleteById">Delete student by id</a></br></br>
        <a href="/UpdateById">Update student by id</a></br></br>
        <#if message??>
            ${message}
        </#if>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" placeholder="Поиск по возрасту" <#if filter??> value="${filter}"</#if >/></br>
        <button type="submit">Найти</button>
    </form>
    <div>Список группы</div>
    <#list names as name>
    <div>
        <b>${name.id}</b>
        <span>${name.name1}</span>
        <span>${name.sname1}</span>
        <i>${name.age}</i>
        <strong>${name.authorName}</strong>
    </div>
        <#else>
            No student
    </#list>
</@c.page>