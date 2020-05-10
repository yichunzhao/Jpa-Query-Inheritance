package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Bug;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BugRepositoryTest {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void whenThereTwoSevereBugs_ThenReturnBoth() {
        //when
        Application application = new Application();
        application.setOwner("owner");
        application.setName("application-name");

        Bug bug1 = new Bug();
        bug1.setTitle("bug-title-1");
        bug1.setSeverity(2);

        Bug bug2 = new Bug();
        bug2.setTitle("bug-title-2");
        bug2.setSeverity(1);
        bug2.setRootCause("root-cause-xx");

        Bug bug3 = new Bug();
        bug3.setTitle("bug-title-3");
        bug3.setSeverity(1);
        bug3.setRootCause("root-cause-yy");

        application.addBug(bug1);
        application.addBug(bug2);
        application.addBug(bug3);

        entityManager.persistAndFlush(application);

        //
        List<Bug> severeBugs = bugRepository.findSevereBugs();

        //
        assertThat(severeBugs, hasSize(2));
    }

}