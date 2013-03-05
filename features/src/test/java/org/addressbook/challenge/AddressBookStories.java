package org.addressbook.challenge;

import java.util.List;

import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.spring.UsingSpring;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.junit.runner.RunWith;

@RunWith(SpringAnnotatedEmbedderRunner.class)
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = false, stepsFactory = true, verboseFailures = true)
@UsingSpring(resources = "classpath:jbehave-spring.xml")
@UsingSteps
public class AddressBookStories extends JUnitStories {

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources"), "**/*.story", "");
    }

    @Override
    public Configuration configuration() {
        return super.configuration().useStoryReporterBuilder(new StoryReporterBuilder()
                                                                    .withFormats(Format.XML, Format.HTML, Format.CONSOLE, Format.TXT)
                                                                    .withRelativeDirectory("../build/jbehave"))
                                    .usePendingStepStrategy(new FailingUponPendingStep());
    }

}
