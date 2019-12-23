<#import "parts/common.ftl" as common>
<#import "parts/genres-select.ftl" as genreSelect>

<@common.page>
<h1 class="display-4">Music editor</h1>

<p class="lead">${message!}</p>

<div class="form-row">
    <#if album.coverPath??>
        <img src="/img/${album.coverPath}" class="img-thumbnail mr-4" />
    <#else>
        <img src="/img/default.jpg" class="img-thumbnail m-4" />
    </#if>
    <ul class="list-group">
        <#list album.songs as song>
        <li class="list-group-item">${song.number}. ${song.name}
            <a href="/music-manage/album/${album.id}/song/${song.id}/delete" class="btn btn-secondary btn-sm">Delete</a></li>
        <#else>
            <p class="lead">No songs yet</p>
        </#list>
    </ul>
    <div class="form-group col-md-6">
        <form class="form-group" action="/music-manage/album/${album.id}" method="post"  enctype="multipart/form-data">
            <input type="text" class="form-control" name="artistName" value="${album.artist.name!}" placeholder="Artist name">
            <input type="text" class="form-control" name="name" value="${album.name!}" placeholder="Album name">
            <@genreSelect.enumSelect "genre" genres/>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile"/>
                    <label class="custom-file-label" for="customFile">New cover</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary ml-3">Save</button>
        </form>
    </div>
</div>

<a class="btn btn-primary m-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add song
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form action="/music-manage/album/${album.id}/addSong" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="name" class="form-control" placeholder="Song name:">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add</button>
            </div>
        </form>
    </div>
</div>

</@common.page>