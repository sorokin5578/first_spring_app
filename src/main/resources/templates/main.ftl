<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control"
                       placeholder="Find by Age" <#if filter??> value="${filter}"</#if >/></br>
                <button type="submit" class="btn btn-primary ml-2">Find</button>
            </form>
        </div>
    </div>
    <div>
        <#if message??>
            ${message}
        </#if>
    </div>

    <div class="card-columns">
        <#list names as name>
            <div class="card my-3" style="width: 75%; height: 75%;" >
                <#if name.fileName??>
                    <img src="/img/${name.fileName}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <div class="card-title">
                        <h5>
                            <span>${name.name1}</span>
                            <span>${name.sname1}</span>
                        </h5>
                    </div>
                    <i>${name.age}</i>
                </div>
                <div class="card-href m-2">
                    <#if isAdmin>
                        <a href="/del/${name.id}" class="btn btn-primary">Delete</a>
                        <a href="/up/${name.id}" class="btn btn-primary">Edit</a>
                    <#else>
                        <#if name.authorName==nameUser>
                            <a href="/del/${name.id}" class="btn btn-primary">Delete</a>
                            <a href="/up/${name.id}" class="btn btn-primary">Edit</a>
                        </#if>
                    </#if>
                </div>
                <div class="card-footer text-muted">
                    ${name.authorName}
                </div>
            </div>
        <#else>
            No student
        </#list>
    </div>
</@c.page>