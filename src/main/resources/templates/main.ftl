<#import "parts/common.ftl" as common>

<@common.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" action="/main" method="get">
            <input type="text" class="form-control" name="filter" value="${filter!}" placeholder="Tag">
            <button type="submit" class="btn btn-primary ml-3">Search by Tag</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new message
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form action="addEntity" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="text" class="form-control" placeholder="Enter message">
            </div>
            <div class="form-group">
                <input type="text" name="tag" class="form-control" placeholder="Enter tags">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile"/>
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add message</button>
            </div>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list messages as message>
    <div class="card my-3">
        <#if message.filename??>
            <img src="/img/${message.filename}" class="card-img-top" />
        </#if>
    <div class="m-2">
        <span>${message.text}</span>
        <i>${message.tag}</i>
    </div>
        <div class="card-footer text-muted">
            ${message.authorName}
        </div>
    </div>
    <#else>
        No messages yet
    </#list>
</div>
</@common.page>