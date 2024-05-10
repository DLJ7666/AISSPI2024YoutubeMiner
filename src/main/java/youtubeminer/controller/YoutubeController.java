package youtubeminer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import videominer.model.Caption;
import videominer.model.Channel;
import videominer.model.Comment;
import videominer.model.User;
import videominer.model.Video;
import youtubeminer.model.caption.YoutubeCaption;
import youtubeminer.model.caption.YoutubeCaptionSearch;
import youtubeminer.model.caption.YoutubeCaptionSnippet;
import youtubeminer.model.channel.*;
import youtubeminer.model.comment.*;
import youtubeminer.model.videoSnippet.*;
import youtubeminer.service.captionService;
import youtubeminer.service.channelService;
import youtubeminer.service.commentService;
import youtubeminer.service.videoSnippetService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/youtubeminer")
public class YoutubeController {

    @Autowired
    captionService captionService;

    @Autowired
    channelService channelService;

    @Autowired
    commentService commentService;

    @Autowired
    videoSnippetService videoSnippetService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(String youtubeChannelId) {
        YoutubeChannel channel = channelService.getYoutubeChannel(youtubeChannelId);
        Channel canal =new Channel(channel.getSnippet().getTitle(), channel.getSnippet().getDescription(),
                channel.getSnippet().getPublishedAt());
        String uploadsPlayListId = channel.getPlayListIdWrapper().getPlayListsIds().getUploadsPlayListId();
        YoutubeVideoSnippetSearch videoList = videoSnippetService.getYoutubeVideoSnippets(uploadsPlayListId);
        List<YoutubeVideoSnippet> videos = new ArrayList<>(videoList.getItems());
        String videosNextPageToken = videoList.getNextPageToken();
        while(videosNextPageToken!=null){
            videoList = videoSnippetService.getYoutubeVideoSnippets(uploadsPlayListId,videosNextPageToken);
            videos.addAll(videoList.getItems());
            videosNextPageToken = videoList.getNextPageToken();
        }
        for(YoutubeVideoSnippet videoSnippet:videos){
            Video v = new Video();
            v.setName(videoSnippet.getSnippet().getTitle());
            v.setDescription(videoSnippet.getSnippet().getDescription());
            v.setReleaseTime(videoSnippet.getSnippet().getPublishedAt());
            YoutubeCaptionSearch captionList = captionService.getYoutubeCaptions(
                    videoSnippet.getResourceId().getVideoId());
            videoSnippet.addCaptions(captionList.getItems());
            for(YoutubeCaption caption:videoSnippet.getCaptions()) {
                Caption subtitulo = new Caption(caption.getSnippet().getName(), caption.getSnippet().getLanguage());
            }
            YoutubeCommentSearch commentList = commentService.getYoutubeComments(
                    videoSnippet.getResourceId().getVideoId());
            List<YoutubeComment> comments = new ArrayList<>(commentList.getItems());
            String commentsNextPageToken = commentList.getNextPageToken();
            while(commentsNextPageToken!=null) {
                commentList = commentService.getYoutubeComments(videoSnippet.getResourceId().getVideoId(),
                        commentsNextPageToken);
                comments.addAll(commentList.getItems());
                commentsNextPageToken = commentList.getNextPageToken();
            }
            for(YoutubeComment comment:comments) {
                User u = new User(comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorDisplayName(),
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorChannelUrl(),
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getAuthorProfileImageUrl());
                Comment c = new Comment(comment.getCommentSnippet().getTopLevelComment().getSnippet().getTextOriginal(),
                        comment.getCommentSnippet().getTopLevelComment().getSnippet().getPublishedAt(), u);
            }
        }
    }
}
