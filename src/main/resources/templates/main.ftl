<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as p>

<@common.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" action="/main" method="get">
            <input type="text" class="form-control" name="filter" value="${filter!}" placeholder="Search">
            <button type="submit" class="btn btn-primary ml-3">Search by album name</button>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list albums.content as album>
    <div class="card my-3">
        <#if album.coverPath??>
            <img src="/img/${album.coverPath}" class="card-img-top rounded" />
        <#else>
            <img src="/img/default.jpg" class="card-img-top rounded" />
        </#if>
    <div class="m-2">
        <span>${album.name}</span>
    </div>
        <div class="card-footer text-muted">
            <a href="/artist/${album.artist.id}">${album.artist.name}</a>
        </div>
        <div class="card-footer text-muted text-right">
            <a href="/album/${album.id}" class="btn btn-primary">Comment</a>
            <#if isAdmin>
                <a href="/music-manage/album/${album.id}" class="btn btn-secondary">Edit</a>
            </#if>
        </div>
    </div>
    <#else>
        <p class="lead">No albums yet</p>
    </#list>
</div>
<@p.pager url albums />

</@common.page>