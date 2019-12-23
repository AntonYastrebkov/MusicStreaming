<#import "parts/common.ftl" as common>
<#import "parts/genres-select.ftl" as genreSelect>

<@common.page>
<h1 class="display-4">Music editor</h1>

<p class="lead">${message!}</p>

<div class="accordion" id="accordionExample">
    <div class="card">
        <div class="card-header" id="headingOne">
            <h2 class="mb-0">
                <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    Add new artist
                </button>
            </h2>
        </div>
        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
            <div class="card-body">
                <div class="form-group mt-3">
                    <form action="/music-manage/addArtist" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" placeholder="Name">
                            <textarea name="description" class="form-control" aria-label="With textarea" placeholder="Artist description"></textarea>
                            <input type="text" class="form-control" name="year" placeholder="Year of activity">
                            <div class="custom-file">
                                <input type="file" name="image" id="customFile2" />
                                <label class="custom-file-label" for="customFile2">New image</label>
                            </div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary">Add artist to base</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-header" id="headingTwo">
            <h2 class="mb-0">
                <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    Add new album
                </button>
            </h2>
        </div>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
            <div class="card-body">
                <div class="form-group mt-3">
                    <form action="/music-manage/addAlbum" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" placeholder="Album name">
                        </div>
                        <div class="form-group">
                            <input type="text" name="artistName" class="form-control" placeholder="Artist name">
                        </div>
                        <div class="form-group">
                            <div class="custom-file">
                                <input type="file" name="file" id="customFile"/>
                                <label class="custom-file-label" for="customFile">Choose file</label>
                            </div>
                        </div>
                        <@genreSelect.enumSelect "genre" genres />
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Add message</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</@common.page>
