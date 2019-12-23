<#import "parts/common.ftl" as common>

<@common.page>
<h1 class="display-4">Comment editing</h1>

<p class="lead">${message!}</p>

<div class="form-row">
<div class="form-group col-md-6">
    <form class="form-group" action="/album/${comment.album.id}/comment/${comment.id}/edit" method="post">
        <input type="text" class="form-control" name="text" value="${comment.text}" placeholder="Text">
        <input type="text" class="form-control" name="mark" value="${comment.mark}" placeholder="Your mark (1-10)">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary ml-3">Save</button>
    </form>
</div>
</div>

</@common.page>