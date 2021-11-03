package com.aie.game.GameComponents.GameScreen.transition;

import com.aie.game.GdxRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(GdxRunner.class)
class TimeTransitionTest {

    @Test
    void testGet_ShouldSucceedWithTimeNull() {
        TimeTransition timeTransition = new TimeTransition();

        float time = timeTransition.get();

        assertThat(time).isEqualTo(1f);
    }

    @Test
    void testGet_ShouldSucceedWithTimeNotZero() {
        float duration = 1f;
        TimeTransition timeTransition = new TimeTransition();
        timeTransition.start(duration);

        float time = timeTransition.get();

        assertThat(time).isZero();
    }
}
