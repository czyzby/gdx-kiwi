package com.github.czyzby.kiwi.util.gdx.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.github.czyzby.kiwi.util.gdx.reflection.Reflection;

/** Provides utilities for LibGDX tooltips.
 *
 * @author MJ */
public class Tooltips {
    private Tooltips() {
    }

    /** Since main tooltip manager instance cannot be changed globally with a regular setter, this method modifies it
     * using reflection.
     *
     * @param tooltipManager will be returned on {@link TooltipManager#getInstance()} calls. */
    public static void setDefaultTooltipManager(final TooltipManager tooltipManager) {
        try {
            // We have to set the app field, as it is validated by the static getter.
            Reflection.setFieldValue(ClassReflection.getDeclaredField(TooltipManager.class, "app"), null, Gdx.app);
            Reflection.setFieldValue(ClassReflection.getDeclaredField(TooltipManager.class, "instance"), null,
                    tooltipManager);
        } catch (final ReflectionException exception) {
            throw new GdxRuntimeException("Unable to set default tooltip manager.", exception);
        }
    }
}
