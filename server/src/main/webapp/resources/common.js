/**
 * Created by Francis on 2015-06-07.
 */
$(function(){
    
    $('#bonacell_search_btn').on('click', function(){
        var url = contextPath+'/request/search';
        var json = {
            artist : $('#search_artist').val(),
            title : $('#search_title').val(),
            start : $('#search_start').val(),
            count : $('#search_count').val()
        };

        $.postJSON(url, json, function(result){

            var obj = JSON.parse(result.line);
            var tracks = obj.tracks;
            var ul = $('#bonacell_search_result');
            
            ul.html('');
            for(var i=0; i<tracks.length; i++){
                var li = $('<li>'), track = tracks[i];
                li.html(track.title + ' ' + track.track_id + ' <a href="'+track.url+'">YouTube</a>');
                ul.append(li);
            }
        });
    });

    $('#bonacell_recommend_btn').on('click', function(){
        var url = contextPath + '/request/recommend'
        var json = {
            trackId : $('#recommend_trackId').val(),
            count : $('#recommend_count').val()
        };

        $.postJSON(url,json, function(result){
            var obj = JSON.parse(result.line);
            var tracks = obj.tracks;
            var ul = $('#bonacell_recommend_result');

            ul.html('');
            for(var i=0; i<tracks.length; i++){
                var li = $('<li>'), track = tracks[i];
                li.html(track.title + ' ' + track.track_id + ' '+track.score +' <a href="'+track.url+'">YouTube</a>');
                ul.append(li);
            }
        });
    })

    $('#bonalcell_search_recommend_btn').on('click', function(){
        var url = contextPath+'/recommendList';
        var json = {
            artist : $('#search_recommend_artist').val(),
            title : $('#search_recommend_title').val()
        };

        $.postJSON(url, json, function(result){
            var tracks = result.tracks;
            var track = result.track;
            var ul = $('#bonacell_search_recommend_result');

            ul.html('');
            var trackLi = $('<li>');
            trackLi.html(track.title + ' ' + track.trackId +' <a href="'+track.url+'">YouTube</a>');
            trackLi.addClass('highlight');
            ul.append(trackLi);
            
            for(var i=0; i<tracks.length; i++){
                var li = $('<li>');
                track = tracks[i];
                
                li.html(track.title + ' ' + track.trackId + ' '+track.score +' <a href="'+track.url+'">YouTube</a>');
                ul.append(li);
            }
        });
    });

    $('#bonalcell_recommend_weather_btn').on('click', function(){
        var url = contextPath+'/recommend/time/weather';
        var json = {
            id : $('#recommend_id').val(),
            weatherMain : $('#recommend_id').val()
        };

        $.postJSON(url, json, function(result){
            var tracks = result.tracks;
            var track = result.track;
            var ul = $('#bonacell_recommend_weather_result');

            ul.html('');
            var trackLi = $('<li>');
            trackLi.html(track.title + ' ' + track.trackId +' <a href="'+track.url+'">YouTube</a>');
            trackLi.addClass('highlight');
            ul.append(trackLi);

            for(var i=0; i<tracks.length; i++){
                var li = $('<li>');
                track = tracks[i];

                li.html(track.title + ' ' + track.trackId + ' '+track.score +' <a href="'+track.url+'">YouTube</a>');
                ul.append(li);
            }
        });
    });
    
    $('#auth_table>tbody>tr').on('click', function(){
        var authId = $(this).attr('data-id');
        location.href = contextPath + '/manage/auth/'+authId;
    });
    
});