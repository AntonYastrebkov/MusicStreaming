<#include "parts/security.ftl">
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

<div class="card-columns">
    <#list albums as album>
    <div class="card my-3">
        <#if album.coverPath??>
            <img src="/img/${album.coverPath}" class="card-img-top" />
        </#if>
    <div class="m-2">
        <span>${album.name}</span>
<!--        <i>${album.artist.name}</i>-->
    </div>
        <div class="card-footer text-muted">
            ${album.artist.name}
        </div>
        <div class="card-footer text-muted text-right">
            <a href="/album/${album.id}" class="btn btn-primary">Comment</a>
            <#if isAdmin>
                <a href="/music-manage/album/${album.id}" class="btn btn-secondary">Edit</a>
            </#if>
        </div>
    </div>
    <#else>
        No messages yet
    </#list>
</div>
</@common.page>