package io.testtask.base;

import io.testtask.ElementSteps;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BasePageSteps<T extends BasePage> {
    private static final String LOG_MESSAGE_PATTERN = "----%s STEP: %s----";
    protected final ElementSteps steps = ElementSteps.steps();
    private final T page;

    protected void executeMethodsWithLogStep(String message, Consumer<T> consumer) {
        log.info(String.format(LOG_MESSAGE_PATTERN, "START", message));
        consumer.accept(page);
        log.info(String.format(LOG_MESSAGE_PATTERN, "END", message));
    }
}
