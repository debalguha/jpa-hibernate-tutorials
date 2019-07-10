package com.example.jpa.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public final class PostBuilder {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Date postedAt = new Date();
    private Date lastUpdatedAt = new Date();
    private Set<Tag> tags = new HashSet<>();

    private PostBuilder() {
    }

    public static PostBuilder aPost() {
        return new PostBuilder();
    }

    public PostBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PostBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PostBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public PostBuilder withPostedAt(Date postedAt) {
        this.postedAt = postedAt;
        return this;
    }

    public PostBuilder withLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }

    public PostBuilder withTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Post build() {
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setDescription(description);
        post.setContent(content);
        post.setPostedAt(postedAt);
        post.setLastUpdatedAt(lastUpdatedAt);
        post.setTags(tags);
        return post;
    }
}
