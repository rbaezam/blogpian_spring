$( document ).ready(function() {

    $.ajax({
        url: '/posts',
        dataType: 'json',
        success: function(data) {
            $.each(data, function(index, post) {

                var body = post.body;
                if (post.body.length > 300) {
                    body = post.body.substring(0, 300);
                    body = body + '...';
                }

                var title = '<h4>' + post.title + '</h4>';
                var link = '<a href="/posts/' + post.id + '">' + title + '</a>';
                var bodyText = '<p>' + body + '</p>';
                var li = '<li>' + link + bodyText + '</li>';
                $('#posts').append(li);
            });
        }
    })

});