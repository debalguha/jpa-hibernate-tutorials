package com.example.jpa;

import com.example.jpa.model.Post;
import com.example.jpa.model.PostBuilder;
import com.example.jpa.model.Tag;
import com.example.jpa.model.TagBuilder;
import com.example.jpa.repository.PostRepository;
import com.example.jpa.repository.TagRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaManyToManyDemoApplicationTests {
    @Autowired
    PostRepository postRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    EntityManager em;

    @After
    public void cleanup(){
        postRepository.deleteAllInBatch();
    }

    @Test
	public void contextLoads() {}

	@Test
    @Transactional
    public void test_createPostAndTagsTestWithSingleResult(){
        String postTittle = "postTittle";
        String jpqlStr = "select p from Post p inner join fetch p.tags t where title = :title";
        final Set<Tag> tags = IntStream.range(0, 3).mapToObj(i -> TagBuilder.aTag().withName(RandomString.make(5) + i).build()).collect(Collectors.toSet());
        IntStream.range(0, 5).mapToObj(i -> PostBuilder.aPost().withContent(RandomString.make(10)).withDescription(RandomString.make(10)).withLastUpdatedAt(new Date()).withTitle(postTittle+i).withTags(tags).build())
                .forEach(aPost -> postRepository.save(aPost));
        assertEquals(5, postRepository.findAll().size());
        assertEquals(3, tagRepository.findAll().size());
        assertNotNull(em.createQuery(jpqlStr).setParameter("title", "postTittle"+2).getSingleResult());
        assertEquals(3, ((Post)em.createQuery(jpqlStr).setParameter("title", "postTittle"+2).getSingleResult()).getTags().size());
    }

    @Test
    @Transactional
    public void test_createPostAndTagsTestWithResultList(){
        String postTittle = "postTittle";
        String jpqlStr = "select p from Post p inner join fetch p.tags t where title = :title";
        final Set<Tag> tags = IntStream.range(0, 3).mapToObj(i -> TagBuilder.aTag().withName(RandomString.make(5) + i).build()).collect(Collectors.toSet());
        IntStream.range(0, 5).mapToObj(i -> PostBuilder.aPost().withContent(RandomString.make(10)).withDescription(RandomString.make(10)).withLastUpdatedAt(new Date()).withTitle(postTittle+i).withTags(tags).build())
                .forEach(aPost -> postRepository.save(aPost));
        assertEquals(5, postRepository.findAll().size());
        assertEquals(3, tagRepository.findAll().size());
        assertFalse(em.createQuery(jpqlStr).setParameter("title", "postTittle"+2).getResultList().isEmpty());
        assertEquals(1, em.createQuery(jpqlStr).setParameter("title", "postTittle"+2).getResultList().size());
    }
}
