package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Enhancement;
import com.ynz.jpa.entities.Priority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EnhancementRepositoryTest {

    @Autowired
    private EnhancementRepository enhancementRepository;

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

}