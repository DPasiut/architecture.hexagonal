package com.example.architecture.hexagonal.domain.port;

import com.example.architecture.hexagonal.domain.Announcement;
import com.example.architecture.hexagonal.domain.Blog;
import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.PublicComment;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryContentRepository {
    private List<Content> contents = new ArrayList<>();

    public InMemoryContentRepository() {
        fillRepository();
    }

    private void fillRepository() {
        Announcement announcement = Announcement.builder()
                .dmsId(new DmsId("announcementDmsId"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishDate(new PublishDate(LocalDate.now().plusDays(2)))
                .title(new Title("announcement title"))
                .slug(null)
                .publishStatus(PublishStatus.DRAFT)
                .description(new Description("announcement description"))
                .build();

        Blog blog = Blog.builder()
                .dmsId(new DmsId("blogDmsId"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishDate(new PublishDate(LocalDate.now().plusDays(2)))
                .title(new Title("blog title"))
                .slug(null)
                .publishStatus(PublishStatus.DRAFT)
                .imageOne(new Image("image one"))
                .textOne(new Text("text one"))
                .build();

        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("pubCommentDmsId"))
                .title(new Title("public comment title"))
                .slug(null)
                .pageDate(new PageDate(LocalDate.now()))
                .upcomingDate(new UpcomingDate(LocalDate.now().plusDays(1)))
                .openForSubmissionDate(new OpenForSubmissionDate(LocalDate.now().plusDays(2)))
                .closeForSubmission(new CloseForSubmissionDate(LocalDate.now().plusDays(3)))
                .publishDate(new PublishDate(LocalDate.now().plusDays(3)))
                .publishStatus(PublishStatus.DRAFT)
                .build();

        contents.add(announcement);
        contents.add(blog);
        contents.add(publicComment);
    }

}
