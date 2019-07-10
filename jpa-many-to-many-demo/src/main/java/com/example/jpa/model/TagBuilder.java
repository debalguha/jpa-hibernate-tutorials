package com.example.jpa.model;

import java.util.HashSet;
import java.util.Set;

public final class TagBuilder {
    private Long id;
    private String name;
    private Set<Post> posts = new HashSet<>();

    private TagBuilder() {
    }

    public static TagBuilder aTag() {
        return new TagBuilder();
    }

    public TagBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public TagBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TagBuilder withPosts(Set<Post> posts) {
        this.posts = posts;
        return this;
    }

    public Tag build() {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        tag.setPosts(posts);
        return tag;
    }
}
