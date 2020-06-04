package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Priority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


@DataJpaTest
class EnhancementRepositoryTest {

    @Autowired
    private EnhancementRepository enhancementRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenPersistEnhancements_ThenFindAllOrderedByTitle() {
        //when
        String zTitle = "z-title-1";
        String xTitle = "x-title-1";
        String aTitle = "a-title-1";

        Enhancement enhancement1 = new Enhancement();
        enhancement1.setTitle(zTitle);
        enhancement1.setDescription("enhancement-1-description");
        enhancement1.setPriority(Priority.HIGH);
        enhancement1.setDuplicate(false);

        Enhancement enhancement2 = new Enhancement();
        enhancement2.setTitle(xTitle);
        enhancement2.setDescription("enhancement-2-description");
        enhancement2.setPriority(Priority.MEDIUM);
        enhancement2.setDuplicate(false);

        Enhancement enhancement3 = new Enhancement();
        enhancement3.setTitle(aTitle);
        enhancement3.setDescription("enhancement-3-description");
        enhancement3.setPriority(Priority.MEDIUM);
        enhancement3.setDuplicate(false);

        entityManager.persist(enhancement1);
        entityManager.persist(enhancement2);
        entityManager.persist(enhancement3);

        entityManager.flush();

        List<Enhancement> enhancementsFound = enhancementRepository.getAllEnhancementOrderedByTitle();
        assertThat(enhancementsFound, hasSize(3));
        assertThat(enhancementsFound.get(0).getTitle(), is(aTitle));
        assertThat(enhancementsFound.get(1).getTitle(), is(xTitle));
        assertThat(enhancementsFound.get(2).getTitle(), is(zTitle));
    }

    @Test
    public void whenJoinEnhancementWithApplication_ThenGetAppAndItsEnhancements() {
        String zTitle = "z-title-1";
        String xTitle = "x-title-1";
        String aTitle = "a-title-1";

        Enhancement enhancement1 = new Enhancement();
        enhancement1.setTitle(zTitle);
        enhancement1.setDescription("enhancement-1-description");
        enhancement1.setPriority(Priority.HIGH);
        enhancement1.setDuplicate(false);

        Enhancement enhancement2 = new Enhancement();
        enhancement2.setTitle(xTitle);
        enhancement2.setDescription("enhancement-2-description");
        enhancement2.setPriority(Priority.MEDIUM);
        enhancement2.setDuplicate(false);

        Enhancement enhancement3 = new Enhancement();
        enhancement3.setTitle(aTitle);
        enhancement3.setDescription("enhancement-3-description");
        enhancement3.setPriority(Priority.MEDIUM);
        enhancement3.setDuplicate(false);

        Application app1 = new Application();
        app1.setName("app-1");
        app1.setOwner("owner-1");

        Application app2 = new Application();
        app2.setName("app-2");
        app2.setOwner("owner-2");

        Application app3 = new Application();
        app3.setName("app-3");
        app3.setOwner("owner-3");

        app1.addEnhancement(enhancement1);
        app1.addEnhancement(enhancement2);
        app2.addEnhancement(enhancement3);

        entityManager.persist(app1);
        entityManager.persist(app2);
        entityManager.persist(app3);

        entityManager.flush();

        List<Enhancement> enhancements = enhancementRepository.getEnhancementsApp();
        assertThat(enhancements, hasSize(3));

        //app3 is not inside the inner join result.
        long count = enhancements.stream().map(enhancement -> enhancement.getApplication()).filter(application -> application.equals(app3)).count();
        assertThat(count, is(0L));
    }

}