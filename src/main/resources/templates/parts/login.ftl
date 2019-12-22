<#include "security.ftl">
<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label for="exampleInputEmail1" class="col-sm-2 col-form-label"> User Name: </label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="exampleInputEmail1" class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit"><#if !isRegisterForm>Sign In <#else>Sign Up</#if></button>
        <div class="mt-2">
            <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>

<#macro addAndUp path>
    <div class="form-group mt-2">
        <form method="post" action="${path}" enctype="multipart/form-data">
            <div class="form-group">
                <#if path=="/UpdateById">
                    Update student
                <#else>
                    Add new student
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type=file name="file" id="customFile"/>
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <div class="form-group">
                <#if path=="/UpdateById">
                    <#if isAdmin>
                        <input type="text" class="form-control" name="text0"
                               placeholder="Id" <#if text0??> value="${text0}"</#if>/>
                    </#if>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="text1"
                       placeholder="Name" <#if text1??> value="${text1}"</#if >/>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="text2"
                       placeholder="Sname" <#if text2??> value="${text2}"</#if >/>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="text3"
                       placeholder="Age" <#if text3??> value="${text3}"</#if >/>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <#if path =="/UpdateById">
                    <button class="btn btn-primary" type="submit">Update student</button>
                <#else>
                    <button class="btn btn-primary" type="submit">Add student</button>
                </#if >
            </div>
        </form>
    </div>
</#macro>
