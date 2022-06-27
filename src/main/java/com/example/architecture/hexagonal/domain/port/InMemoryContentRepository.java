package com.example.architecture.hexagonal.domain.port;

import com.example.architecture.hexagonal.domain.entity.Announcement;
import com.example.architecture.hexagonal.domain.entity.Blog;
import com.example.architecture.hexagonal.domain.entity.Content;
import com.example.architecture.hexagonal.domain.entity.PublicComment;
import com.example.architecture.hexagonal.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryContentRepository {
    private List<Content> contents = new ArrayList<>();

    public InMemoryContentRepository() {
        fillRepository();
    }
    private void fillRepository(){
        contents.add(new Announcement (new DmsId("announcementOne"), new Title("announcementTitleOne"), new PageDate(LocalDate.now()), new Description("announcementDescriptionOne")));
        contents.add(new Blog(new DmsId("blogOne"), new Title("blogTitleOne"), new PageDate(LocalDate.now()), new Text("textOne"), new Image("imageOne")));
        contents.add(new PublicComment(new DmsId("blogOne"), new Title("blogTitleOne"), new PageDate(LocalDate.now()), new UpcomingDate(LocalDate.now())));
    }

}
