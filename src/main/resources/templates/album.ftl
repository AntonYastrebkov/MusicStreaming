<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>

<@common.page>

<div class="card mb-3">
    <div class="row no-gutters">
        <div class="col-md-4">
            <img src="/img/${album.coverPath}" class="card-img" alt="...">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="display-4">${album.name}</h5>
                <p class="lead">by ${album.artist.name}</p>
                <p class="lead">Genre: ${album.genre}</p>
                <h5 class="display-4">${album.averageScore}</h5>
<!--                <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>-->
            </div>
        </div>
    </div>
</div>

<div align="center">
    <div class="lead">
        ${error!}
    </div>
    <#list comments as comment>
        <div class="card mb-3 row no-gutters col-md-8 card-body"  align="left">
            <div style="overflow:hidden">
                <div style="float:left"><p class="lead">by ${comment.user.username}</p></div>
                <div style="float:right"><p class="text-muted">${comment.time}</p></div>
            </div>
            <div style="overflow:hidden">
                <div style="float:left"><p class="lead"></p>${comment.text}</div>
                <div style="float:right"><h5 class="display-4">${comment.mark}</h5></div>
            </div>
            <#if isAdmin || comment.user.id == id>
                <a href="/album/${album.id}/comment/${comment.id}/edit" class="btn btn-primary">Edit</a>
                <a href="/album/${album.id}/comment/${comment.id}/delete" class="btn btn-secondary">Delete</a>
            </#if>
        </div>
    <#else>
        <p class="lead">No comments yet. Be the first!</p>
    </#list>
</div>


<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add comment
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form action="/album/${album.id}/addComment" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="text" class="form-control" placeholder="Enter message:">
            </div>
            <div class="form-group">
                <input type="text" name="mark" class="form-control" placeholder="Put your mark (1-10):">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Comment</button>
            </div>
        </form>
    </div>
</div>

</@common.page>