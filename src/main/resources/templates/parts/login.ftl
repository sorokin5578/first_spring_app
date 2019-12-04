<#macro login path>
    <form action="${path}" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Sign In"/></div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</#macro>

<#macro addAndUp path>
    <form method="post" action=${path}>
        <#if path=="/UpdateById">
            <br/>
            <input type="text" name="text0" placeholder="Введите id" <#if text0??> value="${text0}"</#if >/></br>
        </#if >
        <#if path=="/Add">
            </br>
        </#if >
        <input type="text" name="text1" placeholder="Введите имя" <#if text1??> value="${text1}"</#if >/></br>
        <input type="text" name="text2" placeholder="Введите фамилию" <#if text2??> value="${text2}"</#if >/></br>
        <input type= "text" name="text3" placeholder="Введите возраст" <#if text3??> value="${text3}"</#if >/></br>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if path =="/UpdateById">
            <button type="submit">Обновить</button>
        </#if >
        <#if path =="/Add">
            <button type="submit">Добавить</button>
        </#if >
    </form>
</#macro>
