package youtubeminer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import imports.model.Caption;
import imports.model.Channel;
import imports.model.Comment;
import imports.model.User;
import imports.model.Video;
import org.springframework.web.client.HttpClientErrorException;
import youtubeminer.model.caption.YoutubeCaption;
import youtubeminer.model.caption.YoutubeCaptionSearch;
import youtubeminer.model.channel.*;
import youtubeminer.model.comment.*;
import youtubeminer.model.videoSnippet.*;
import youtubeminer.videoservice.*;
import youtubeminer.youtubeservice.YoutubeCaptionService;
import youtubeminer.youtubeservice.YoutubeChannelService;
import youtubeminer.youtubeservice.YoutubeCommentService;
import youtubeminer.youtubeservice.YoutubeVideoSnippetService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/youtubeminer")
public class YoutubeController {

    @Autowired
    YoutubeCaptionService captionService;

    @Autowired
    YoutubeChannelService channelService;

    @Autowired
    YoutubeCommentService commentService;

    @Autowired
    YoutubeVideoSnippetService videoSnippetService;

    @Autowired
    CaptionService videoCaptionService;

    @Autowired
    ChannelService videoChannelService;

    @Autowired
    CommentService videoCommentService;

    @Autowired
    UserService videoUserService;

    @Autowired
    VideoService videoVideoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{youtubeChannelId}")
    public void create(@PathVariable String youtubeChannelId,
                       @RequestParam(name = "maxVideos", required = false) Integer maxVideos,
                       @RequestParam(name = "maxComments", required = false) Integer maxComments)
            throws HttpClientErrorException {
        YoutubeChannel channel = channelService.getYoutubeChannel(youtubeChannelId);
        System.out.println(channel);
        long numMaxVideos = 10L, numMaxComments = 10L;
        if (maxVideos!=null) {
            numMaxVideos = maxVideos.longValue();
        }
        if (maxComments!=null) {
            numMaxComments = maxComments.longValue();
        }
        Channel canal = videoChannelService.creaCanal(youtubeChannelId, channel.getSnippet().getTitle(),
                channel.getSnippet().getDescription(), channel.getSnippet().getPublishedAt());
        String uploadsPlayListId = channel.getPlayListIdWrapper().getPlayListsIds().getUploadsPlayListId();
        YoutubeVideoSnippetSearch videoList = videoSnippetService.getYoutubeVideoSnippets(uploadsPlayListId);
        List<YoutubeVideoSnippet> videos = new ArrayList<>(videoList.getItems());
        String videosNextPageToken = videoList.getNextPageToken();
        while(videosNextPageToken!=null && videos.size()<=numMaxVideos) {
            videoList = videoSnippetService.getYoutubeVideoSnippets(uploadsPlayListId,videosNextPageToken);
            videos.addAll(videoList.getItems());
            videosNextPageToken = videoList.getNextPageToken();
        }
        for(YoutubeVideoSnippet videoSnippet:videos.stream().limit(numMaxVideos).toList()) {
            Video v = videoVideoService.creaVideo(canal.getId(), videoSnippet.getResourceId().getVideoId(),
                    videoSnippet.getSnippet().getTitle(), videoSnippet.getSnippet().getDescription(),
                    videoSnippet.getSnippet().getPublishedAt());
            YoutubeCaptionSearch captionList = captionService.getYoutubeCaptions(
                    videoSnippet.getResourceId().getVideoId());
            videoSnippet.addCaptions(captionList.getItems());
            for(YoutubeCaption caption:videoSnippet.getCaptions()) {
                Caption subtitulo = videoCaptionService.creaSubtitulo(canal.getId(), v.getId(),caption.getId(),
                        caption.getSnippet().getName(), caption.getSnippet().getLanguage());
            }
            YoutubeCommentSearch commentList = commentService.getYoutubeComments(
                    videoSnippet.getResourceId().getVideoId());
            List<YoutubeComment> comments = new ArrayList<>(commentList.getItems());
            String commentsNextPageToken = commentList.getNextPageToken();
            while(commentsNextPageToken!=null && comments.size()<=numMaxComments) {
                commentList = commentService.getYoutubeComments(videoSnippet.getResourceId().getVideoId(),
                        commentsNextPageToken);
                comments.addAll(commentList.getItems());
                commentsNextPageToken = commentList.getNextPageToken();
            }
            for(YoutubeComment comment:comments.stream().limit(numMaxComments).toList()) {
                User u = videoUserService.creaUsuario(
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName(),
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorChannelUrl(),
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl());
                Comment c = videoCommentService.creaComentario(canal.getId(), v.getId(),
                        comment.getCommentSnippet().getTopLevelComment().getId(),
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getTextOriginal(),
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getPublishedAt(), u);
            }
        }
    }
}
