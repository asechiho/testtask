package io.testtask.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import java.util.Map;

@Slf4j
public class Assert {
    private static final Map<AssertionError, IAssert<?>> ASSERTION_MAPS = Maps.newLinkedHashMap();
    private static final ThreadLocal<Assert> ASSERT_THREAD_LOCAL = new ThreadLocal<>();
    @Getter private final Assertion hardAssert;
    @Getter private final Assertion softAssert;

    private Assert() {
        hardAssert = new MixedAssert(true);
        softAssert = new MixedAssert(false);
    }

    public static Assert getAssert() {
        if (ASSERT_THREAD_LOCAL.get() == null) {
            ASSERT_THREAD_LOCAL.set(new Assert());
        }
        return ASSERT_THREAD_LOCAL.get();
    }

    public static void assertAll() {
        log.info("===============Assert all===============");
        if (!ASSERTION_MAPS.isEmpty()) {
            StringBuilder assertionMessage = new StringBuilder("The following asserts failed: \n");
            for (Map.Entry<AssertionError, IAssert<?>> integerMapEntry : ASSERTION_MAPS.entrySet()) {
                assertionMessage.append("\n\t");
                assertionMessage.append(integerMapEntry.getKey().getMessage());
            }
            ASSERTION_MAPS.clear();
            throw new AssertionError(assertionMessage.toString());
        }
        log.info("Assertion passed!");
    }

    public static void clear() {
        ASSERTION_MAPS.clear();
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class MixedAssert extends Assertion {
        private final boolean isHard;

        @Override
        protected void doAssert(IAssert<?> iAssert) {
            this.onBeforeAssert(iAssert);
            try {
                iAssert.doAssert();
                this.onAssertSuccess(iAssert);
            } catch (AssertionError assertionError) {
                this.onAssertFailure(iAssert, assertionError);
                ASSERTION_MAPS.put(assertionError, iAssert);
                if (this.isHard) {
                    assertAll();
                }
            }
        }
    }
}
