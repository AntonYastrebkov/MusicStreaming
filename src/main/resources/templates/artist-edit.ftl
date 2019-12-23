<#import "parts/common.ftl" as common>

<@common.page>
<h1 class="display-4">Artist editor</h1>

<p class="lead">${message!}</p>

<div class="form-row">
    <#if artist.imagePath??>
        <img src="/img/${artist.imagePath}" class="img-thumbnail" />
    <#else>
        <img src="/img/default.jpg" class="img-thumbnail" />
    </#if>
<div class="form-group col-md-6">
    <form class="form-group" action="/artist/${artist.id}/edit" method="post" enctype="multipart/form-data">
        <input type="text" class="form-control" name="name" value="${artist.name!}" placeholder="Artist name">
        <input type="text" class="form-control" name="year" value="${artist.year!}" placeholder="Year of activity">
        <div class="input-group">
            <textarea name="description" class="form-control" aria-label="With textarea" placeholder="Artist description">
                ${artist.description}
            </textarea>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file" id="customFile"/>
                <label class="custom-file-label" for="customFile">New image</label>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary ml-3">Save</button>
    </form>
</div>
</div>

</@common.page>