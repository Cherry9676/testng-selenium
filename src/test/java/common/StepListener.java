package common;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

public class StepListener implements ConcurrentEventListener {
    public static String stepName;
    public static String featureName;

    public EventHandler<TestStepStarted> stepHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            handleTestStepStarted(event);
        }
    };

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, stepHandler);
    }

    private void handleTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            stepName = testStep.getStep().getText();
            // Extract feature name from URI
            String uri = testStep.getUri().toString();
            featureName = extractFeatureName(uri);
        }
    }

    private String extractFeatureName(String uri) {
        // Extract the feature name from the file path
        if (uri != null && uri.contains("/")) {
            return uri.substring(uri.lastIndexOf('/') + 1).replace(".feature", "");
        } else if (uri != null && uri.contains("\\")) { // For Windows paths
            return uri.substring(uri.lastIndexOf('\\') + 1).replace(".feature", "");
        }
        return "Unknown Feature";
    }
}