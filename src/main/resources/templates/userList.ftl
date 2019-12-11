<#import "parts/common.ftl" as c>

<@c.page>

    <#list users as user>
        <div class="card my-3" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">${user.username}</h5>
                <p class="card-text"><#list user.roles as role>${role}<#sep>, </#list></p>
                <div class="card-href">
                    <a href="/user/${user.id}" class="btn btn-primary">Edit</a>
                </div>
            </div>
        </div>
    </#list>

</@c.page>