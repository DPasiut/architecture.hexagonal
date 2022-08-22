package com.example.architecture.hexagonal.infrastructure;

import com.example.architecture.hexagonal.domain.Announcement;
import com.example.architecture.hexagonal.domain.Blog;
import com.example.architecture.hexagonal.domain.Content;
import com.example.architecture.hexagonal.domain.PublicComment;
import com.example.architecture.hexagonal.domain.port.ContentRepository;
import com.example.architecture.hexagonal.domain.types.PublishStatus;
import com.example.architecture.hexagonal.domain.valueobjects.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("test")
public class InMemoryContentRepository implements ContentRepository {
    private List<Content> contents = new ArrayList<>();

    public InMemoryContentRepository() {
        fillRepository();
    }

    @Override
    public Optional<Content> getById(DmsId id) {
        return contents.stream().filter(content1 -> content1.getDmsId().equals(id)).findFirst();
    }

    @Override
    public void save(Content content) {
        getIndexByDmsId(content.getDmsId()).ifPresentOrElse(
                integer -> contents.set(integer, content),
                () -> contents.add(content));
    }

    @Override
    public void delete(DmsId id) {
        getIndexByDmsId(id).ifPresent(integer -> contents.remove(integer.intValue()));
    }

    public void clearRepository(){
        contents.clear();
    }

    public int size(){
        return contents.size();
    }
    private Optional<Integer> getIndexByDmsId(DmsId id) {
        return contents.stream().filter(content -> content.getDmsId().equals(id)).findFirst().map(content -> contents.indexOf(content));
    }

    private void fillRepository() {
        Announcement announcement = Announcement.builder()
                .dmsId(new DmsId("announcementDmsId"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishDate(new PublishDate(LocalDate.now().plusDays(2)))
                .title(new Title("announcement title"))
                .publishStatus(PublishStatus.DRAFT)
                .description(new Description("announcement description"))
                .build();

        Blog blog = Blog.builder()
                .dmsId(new DmsId("blogDmsId"))
                .pageDate(new PageDate(LocalDate.now()))
                .publishDate(new PublishDate(LocalDate.now().plusDays(2)))
                .title(new Title("blog title"))
                .publishStatus(PublishStatus.DRAFT)
                .imageOne(new Image("image one"))
                .textOne(new Text("text one"))
                .build();

        PublicComment publicComment = PublicComment
                .builder()
                .dmsId(new DmsId("pubCommentDmsId"))
                .title(new Title("public comment title"))
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
