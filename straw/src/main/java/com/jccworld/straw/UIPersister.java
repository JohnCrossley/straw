package com.jccworld.straw;

import android.app.Activity;
import android.app.Fragment;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jccworld.straw.ui.persisters.AutoCompleteTextViewPersister;
import com.jccworld.straw.ui.persisters.ButtonPersister;
import com.jccworld.straw.ui.persisters.CheckBoxPersister;
import com.jccworld.straw.ui.persisters.EditTextPersister;
import com.jccworld.straw.ui.persisters.ImageButtonPersister;
import com.jccworld.straw.ui.persisters.ImageViewPersister;
import com.jccworld.straw.ui.persisters.Persisted;
import com.jccworld.straw.ui.persisters.PersistedDataBean;
import com.jccworld.straw.ui.persisters.ProgressBarPersister;
import com.jccworld.straw.ui.persisters.RadioGroupPersister;
import com.jccworld.straw.ui.persisters.SpinnerPersister;
import com.jccworld.straw.ui.persisters.SwitchPersister;
import com.jccworld.straw.ui.persisters.TextViewPersister;
import com.jccworld.straw.ui.persisters.ToggleButtonPersister;
import com.jccworld.straw.ui.vo.Persister;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Serialise the data from UI elements (i.e. value, enabled, etc) so a new instance can be restored
 * in the same state
 *
 * TODO deconstruct reflection logic from collection.
 * Inject handlers into Constructor -> this may need to be a Application.getXFactory
 * method because of the Activity lifecycle preventing IOC.  Don't want to force a DI framework
 * upon consumers.
 *
 * Created by jcc on 26/10/15.
 */
public class UIPersister {
    private static final String DEHYDRATE_METHOD = "dehydrate";
    private static final String HYDRATE_METHOD = "hydrate";

    private final Map<String, Object> map = new HashMap<>();
    private final Map<Class, Persister> matchers = new HashMap<>();

    public UIPersister() {
        matchers.put(AutoCompleteTextView.class, new AutoCompleteTextViewPersister());
        matchers.put(Button.class, new ButtonPersister());
        matchers.put(CheckBox.class, new CheckBoxPersister());
        matchers.put(EditText.class, new EditTextPersister());
        matchers.put(ImageButton.class, new ImageButtonPersister());
        matchers.put(ImageView.class, new ImageViewPersister());
        matchers.put(RadioGroup.class, new RadioGroupPersister());
        matchers.put(Spinner.class, new SpinnerPersister());
        matchers.put(Switch.class, new SwitchPersister());
        matchers.put(TextView.class, new TextViewPersister());
        matchers.put(ToggleButton.class, new ToggleButtonPersister());
        matchers.put(ProgressBar.class, new ProgressBarPersister());
    }

    public void save(final Activity activity) {
        save((Object)activity);
    }

    public void save(final Fragment fragment) {
        save((Object)fragment);
    }

    private void save(final Object object) {
        final Field[] fields = object.getClass().getDeclaredFields();

        for(final Field field : fields) {
            final Persisted annotation = field.getAnnotation(Persisted.class);

            if (annotation != null) {
                final Class<?> type = field.getType();
                if (!matchers.containsKey(type)) {
                    throw new IllegalArgumentException("Cannot persist objects of type: " + type + ".  Please remove the @Persisted annotation.");
                }

                final Persister persister = matchers.get(type);

                try {
                    field.setAccessible(true);
                    final Object viewToSerialise = field.get(object);

                    final Method method = persister.getClass().getMethod(DEHYDRATE_METHOD, type);
                    final Object serialisedObject = method.invoke(persister, viewToSerialise);

                    map.put(field.getName(), serialisedObject);

                } catch (Exception e) {
                    throw new RuntimeException("Cannot save object: " + e.getMessage(), e);
                }
            }
        }
    }

    public void load(final Activity activity) {
        load((Object)activity);
    }

    public void load(final Fragment fragment) {
        load((Object)fragment);
    }

    private void load(final Object object) {
        final Field[] fields = object.getClass().getDeclaredFields();

        for(final Field field : fields) {
            final Persisted annotation = field.getAnnotation(Persisted.class);

            if (annotation != null) {
                final Class<?> type = field.getType();
                if (!matchers.containsKey(type)) {
                    throw new IllegalArgumentException("Cannot persist objects of type: " + type + ".  Please remove the @Persisted annotation.");
                }

                final Persister persister = matchers.get(type);

                try {
                    field.setAccessible(true);
                    final Object viewToPopulate = field.get(object);

                    final Method method = persister.getClass().getMethod(HYDRATE_METHOD, type, PersistedDataBean.class);
                    method.invoke(persister, viewToPopulate, map.get(field.getName()));

                } catch (Exception e) {
                    throw new RuntimeException("Cannot load object: " + e.getMessage(), e);
                }
            }
        }
    }
}
