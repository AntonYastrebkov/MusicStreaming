<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Login:</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="Your login" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
    <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Your email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="example@domain.com" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">First name:</label>
            <div class="col-sm-6">
                <input type="text" name="firstName" class="form-control" placeholder="First name" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Last name:</label>
            <div class="col-sm-6">
                <input type="text" name="lastName" class="form-control" placeholder="Last name" />
            </div>
        </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm><a href="/registration">Sign Up</a></#if>
<button type="submit" class="btn btn-primary">
    <#if isRegisterForm>Create<#else>Sign In</#if>
</button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button  class="btn btn-primary" type="submit">Sign Out</button>
</form>
</#macro>