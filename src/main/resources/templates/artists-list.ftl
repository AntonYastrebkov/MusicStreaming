<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>

<@common.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" action="/artists" method="get">
            <input type="text" class="form-control" name="filter" value="${filter!}" placeholder="Search">
            <button type="submit" class="btn btn-primary ml-3">Search by artist name</button>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list artists as artist>
    <div class="card my-3">
        <#if artist.imagePath??>
            <img src="/img/${artist.imagePath}" class="card-img-top rounded" />
        <#else>
            <img src="/img/default.jpg" class="card-img-top rounded" />
        </#if>
        <div class="m-2">
            <span class="lead">${artist.name}</span>
        </div>
        <div class="card-footer text-muted m-1">
            <p>${artist.year!}</p>
        </div>
        <div class="card-footer text-muted text-right">
            <a href="/artist/${artist.id}" class="btn btn-primary">Info</a>
            <#if isAdmin>
                <a href="/artist/${artist.id}/edit" class="btn btn-secondary">Edit</a>
            </#if>
        </div>
    </div>
    <#else>
        <p class="lead">No artists yet</p>
    </#list>
</div>
</@common.page>