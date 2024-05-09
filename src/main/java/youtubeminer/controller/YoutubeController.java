package youtubeminer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import videominer.model.Comment;
import videominer.model.User;
import youtubeminer.model.caption.YoutubeCaption;
import youtubeminer.model.caption.YoutubeCaptionSearch;
import youtubeminer.model.caption.YoutubeCaptionSnippet;
import youtubeminer.model.caption.YoutubeVideoCaptionList;
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
    captionService CaptionService;

    @Autowired
    channelService ChannelService;

    @Autowired
    commentService CommentService;

    @Autowired
    videoSnippetService VideoSnippetService;

    @Autowired
    AuthorChannelId AuthorChannelId;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(String youtubeChannelId) {
        YoutubeChannel channel = ChannelService.getYoutubeChannel(youtubeChannelId);
        YoutubeVideoSnippetList videoList = VideoSnippetService.getYoutubeVideoList(youtubeChannelId, null);
        List<YoutubeVideoSnippet> videos = new ArrayList<>(videoList.getVideos());
        for(int i=2; i<=videoList.getNumPags(); i++){
            videos.addAll(VideoSnippetService.getYoutubeVideoList(youtubeChannelId,i).getVideos());
        }
        for(YoutubeVideoSnippet video:videos){
            YoutubeVideoCaptionList captionList = CaptionService.getYoutubeCaptionList(video.getId(), null);
            video.addTexttracks(captionList.getCaptions());
            for(int i=2; i<=captionList.getNumPags(); i++) {
                video.addTexttracks(CaptionService.getYoutubeCaptionList(video.getId(), i).getCaptions());
            }
            YoutubeVideoCommentList commentList = CommentService.getYoutubeCommentList(video.getId(), null);
            List<YoutubeComment> comments = new ArrayList<>(commentList.getComments());
            for(int i=2; i<=commentList.getNumPags(); i++) {
                comments.addAll(CommentService.getYoutubeCommentList(video.getId(), i).getComments());
            }
            for(YoutubeComment comment:comments) {
                YoutubeCommentSnippet__1 y = CommentService.getYoutubeCommentSnippet(comment.getCommentSnippet(),null);
                User u = new User(y.getAuthorDisplayName(),y.getAuthorChannelUrl(),y.getAuthorProfileImageUrl());
                Comment c = new Comment(y.getTextOriginal(), y.getPublishedAt(), u);
            }
        }
    }
}
