<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">My Spring</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Group list</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User list</a>
                </li>
            </#if>
            <li class="nav-item">
                <a class="nav-link" href="/Add">Add new student</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="collapse" href="#collapseExample"
                   aria-expanded="false"
                   aria-controls="collapseExample">
                    Find by Age
                </a>
            </li>
        </ul>

        <div class="navbar-text mr-3"> ${nameUser} </div>
        <div>
            <@l.logout />
        </div>
    </div>
</nav>