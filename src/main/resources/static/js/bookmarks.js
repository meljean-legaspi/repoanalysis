function showBookmarks(){
    $.getJSON( "/bookmarks", function( data ) {
        let items = [];
        $.each( data, function( key, val ) {
            items.push(`<li id='${ key }'><a href="/search?query=${ val['query'] }&page=${ val['page'] }">${ val['query'] }</a></li>`);
        });
        if (items.length > 0) {
            $("<p />", {
                html: "Bookmarked Searches"
            }).appendTo( "#bookmarks-section" );

            $( "<ul/>", {
                "id": "bookmarks-list",
                html: items.join( "" )
            }).appendTo( "#bookmarks-section" );
        }
    });
}

$( "#bookmark-btn" ).click(function() {
    let query = $("#query").val();
    let page = parseInt($("#current-page").val());
    let requestBody = ` { "query" : "${ query }", "page": ${ page } }`;
    $.ajax({
        url: "/bookmarks/add",
        method: 'POST',
        contentType: "application/json",
        data: requestBody
    })
    .done(function() {
        $(".alert-success").show();
    })
    .fail(function() {
        $(".alert-danger").show();
    });
});
$(".alert-success").hide();
$(".alert-danger").hide();
showBookmarks();