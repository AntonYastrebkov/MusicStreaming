<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>

<@common.page>

<div class="card mb-3">
    <div class="row no-gutters">
        <div class="col-md-4">
            <#if artist.imagePath??>
                <img src="/img/${artist.imagePath}" class="card-img-top" />
            <#else>
                <img src="/img/default.jpg" class="card-img-top" />
            </#if>
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="display-4">${artist.name}</h5>
                <h5 class="lead">Years of activity: ${artist.year!}</h5>
                <p class="lead">${artist.description!}</p>
                <#if isAdmin>
                    <a href="/artist/${artist.id}/edit" class="btn btn-primary">Edit</a>
                    <a href="/artist/${artist.id}/delete" class="btn btn-secondary">Delete</a>
                </#if>
            </div>
        </div>
    </div>
</div>

<div class="card-columns">
    <#list artist.albums as album>
    <div class="card my-3">
        <#if album.coverPath??>
            <img src="/img/${album.coverPath}" class="card-img-top" />
        <#else>
            <img src="/img/default.jpg" class="card-img-top" />
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
        No albums yet
    </#list>
</div>

</@common.page>