<#import "parts/common.ftl" as common>
<#import "parts/genres-select.ftl" as genreSelect>

<@common.page>
<h1 class="display-4">Music editor</h1>

<p class="lead">${message!}</p>

<div class="form-row">
    <#if album.coverPath??>
        <img src="/img/${album.coverPath}" class="card-img-top" height="400px "/>
    </#if>
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

</@common.page>