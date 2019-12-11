<#import "parts/common.ftl" as c>

<@c.page>

    <form action="/user" method="post">
        <div class="form-group row">
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" value="${user.username}"/>
            </div>
        </div>
        <div class="form-group form-check">
            <#list roles as role>
                <div class="form-group form-check-el">
                    <input type="checkbox" class="form-check-input"
                           name="${role}" ${user.roles?seq_contains(role)?string("checked","")}>
                    <label class="form-check-label">${role}</label>
                </div>
            </#list>
        </div>
        <div class="form-butt">
            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button class="btn btn-primary" type="submit">Save</button>
        </div>

    </form>
</@c.page>