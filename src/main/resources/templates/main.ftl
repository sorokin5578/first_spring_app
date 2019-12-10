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
<#--        <a href="/DeleteById">Delete student by id</a></br></br>-->
<#--        <a href="/UpdateById">Update student by id</a></br></br>-->
        <#if message??>
            ${message}
        </#if>
    </div>
    <form method="get" action="/main">
        <input type="text" name="filter" placeholder="Поиск по возрасту" <#if filter??> value="${filter}"</#if >/></br>
        <button type="submit">Найти</button>
    </form>
    <div>Список группы</div>
    <table>
        <thead>
        <tr>
            <th>Avatar</th>
            <th>Name</th>
            <th>Sname</th>
            <th>Age</th>
            <th>Author</th>
            <th>Delete</th>
            <th>Update</th>
        </tr>
        </thead>
        <tbody>
        <#list names as name>
            <tr align="center" height="80">
            <div>
                <td>
                <div>
                    <#if name.fileName??>
                        <img src="/img/${name.fileName}" width="50" height="75">
                    </#if>
                </div>
                </td>
                <td><span>${name.name1}</span></td>
                <td><span>${name.sname1}</span></td>
                <td><i>${name.age}</i></td>
                <td><strong>${name.authorName}</strong></td>
                <td><a href="/del/${name.id}">Delete</a></td>
                <td><a href="/up/${name.id}">Edit</a></td>
            </div>
            </tr>
        <#else>
            No student
        </#list>
        </tbody>
    </table>
</@c.page>