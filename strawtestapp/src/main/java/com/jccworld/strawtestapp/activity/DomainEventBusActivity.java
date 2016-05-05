package com.jccworld.strawtestapp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jccworld.straw.KeyValueCache;
import com.jccworld.straw.UIPersister;
import com.jccworld.straw.activity.BaseActivity;
import com.jccworld.straw.eventbus.EventBus;
import com.jccworld.straw.eventbus.EventBusListener;
import com.jccworld.straw.ui.persisters.Persisted;
import com.jccworld.strawtestapp.R;
import com.jccworld.strawtestapp.di.injection.ProductionInjector;
import com.jccworld.strawtestapp.service.NumberDomainService;
import com.jccworld.strawtestapp.service.SentenceDomainService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jcc on 19/11/15.
 */
public class DomainEventBusActivity extends BaseActivity implements View.OnClickListener {

    private final ProductionInjector injector;

    @Inject @Named("numberDomainConsumableEventBus")
    EventBus<Short> numberConsumableEventBus;

    @Inject @Named("numberDomainStickyEventBus")
    EventBus<Short> numberStickyEventBus;

    @Inject @Named("numberDomainQueuedEventBus")
    EventBus<Short> numberQueuedEventBus;

    private Button numberServiceButton;

    @Inject @Named("wordDomainConsumableEventBus")
    EventBus<String> wordConsumableEventBus;

    @Inject @Named("wordDomainStickyEventBus")
    EventBus<String> wordStickyEventBus;

    @Inject @Named("wordDomainQueuedEventBus")
    EventBus<String> wordQueuedEventBus;

    private Button wordServiceButton;
    private Button getWordButton;

    @Persisted private TextView numberMessage;
    @Persisted private TextView consumableCountTextView;
    @Persisted private TextView stickyCountTextView;
    @Persisted private TextView queuedCountTextView;

    @Persisted private TextView wordMessage;
    @Persisted private TextView consumableWordTextView;
    @Persisted private TextView stickyWordTextView;
    @Persisted private TextView queuedWordTextView;

    private NumberEventListener numberConsumableListener;
    private NumberEventListener numberStickyListener;
    private NumberEventListener numberQueuedListener;

    private WordEventListener wordStickyListener;

    public DomainEventBusActivity() {
        super();
        this.injector = new ProductionInjector();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startNumberService) {
            numberConsumableEventBus.start();
            numberStickyEventBus.start();
            numberQueuedEventBus.start();

            startService(new Intent(this, NumberDomainService.class));
        } else if (v.getId() == R.id.startWordService) {
            System.out.println("start word service....");
            wordConsumableEventBus.start();
            wordStickyEventBus.start();
            wordQueuedEventBus.start();

            startService(new Intent(this, SentenceDomainService.class));
        } else {
            //force null as String
            consumableWordTextView.setText("" + wordConsumableEventBus.get());
            stickyWordTextView.setText("" + wordStickyEventBus.get());
            queuedWordTextView.setText("" + wordQueuedEventBus.get());
        }
    }

    @Override
    public void created() {
        setContentView(R.layout.activity_event_bus);
        injector.inject(this);

        numberServiceButton = (Button) findViewById(R.id.startNumberService);
        numberServiceButton.setOnClickListener(this);

        numberMessage = (TextView) findViewById(R.id.numberMessage);

        consumableCountTextView = (TextView) findViewById(R.id.consumableEventCount);
        numberConsumableListener = new NumberEventListener(numberConsumableEventBus, consumableCountTextView);

        stickyCountTextView = (TextView) findViewById(R.id.stickyEventCount);
        numberStickyListener = new NumberEventListener(numberStickyEventBus, stickyCountTextView);

        queuedCountTextView = (TextView) findViewById(R.id.queuedEventCount);
        numberQueuedListener = new NumberEventListener(numberQueuedEventBus, queuedCountTextView);

        wordServiceButton = (Button) findViewById(R.id.startWordService);
        wordServiceButton.setOnClickListener(this);

        getWordButton = (Button) findViewById(R.id.getWord);
        getWordButton.setOnClickListener(this);

        wordMessage = (TextView) findViewById(R.id.wordMessage);

        consumableWordTextView = (TextView) findViewById(R.id.consumableEventWord);

        stickyWordTextView = (TextView) findViewById(R.id.stickyEventWord);
        wordStickyListener = new WordEventListener(wordMessage);

        queuedWordTextView = (TextView) findViewById(R.id.queuedEventWord);
    }

    @Override
    public void onFocus() {
        numberConsumableEventBus.attachListener(numberConsumableListener);
        numberStickyEventBus.attachListener(numberStickyListener);
        numberQueuedEventBus.attachListener(numberQueuedListener);

        wordStickyEventBus.attachListener(wordStickyListener);
    }

    @Override
    public void onFocusLost() {
        numberConsumableEventBus.detachListener();
        numberStickyEventBus.detachListener();
        numberQueuedEventBus.detachListener();

        wordStickyEventBus.detachListener();
    }

    @Override
    public void onLoad(boolean firstRun, KeyValueCache keyValueCache, UIPersister uiPersister) {
        if (!firstRun) {
            uiPersister.load(this);
        }
    }

    @Override
    public void onSave(KeyValueCache keyValueCache, UIPersister uiPersister) {
        uiPersister.save(this);
    }

    class NumberEventListener implements EventBusListener<Short> {
        private final EventBus eventBus;
        private final TextView textView;

        NumberEventListener(final EventBus eventBus, final TextView textView) {
            this.eventBus = eventBus;
            this.textView = textView;
        }

        @Override
        public void onEvent(final Short s) {
            final String shortString = textView.getText().toString();

            if (textView.getId() == R.id.consumableEventCount) {
                numberMessage.setText("New event in: " + s);
            }

            if (shortString.length() == 0) {
                textView.setText("0");
            } else {
                Short value = Short.parseShort(shortString);
                ++value;
                textView.setText(value + "");
            }

            //end game
            if (s == Short.MAX_VALUE) {
                eventBus.stop();
            }
        }
    }

    class WordEventListener implements EventBusListener<String> {
        private TextView stickyWordTextView;

        public WordEventListener(TextView stickyWordTextView) {
            this.stickyWordTextView = stickyWordTextView;
        }

        @Override
        public void onEvent(final String event) {
            System.out.println("[JCC] WordEventListener.onEvent - event: " + event);
            stickyWordTextView.setText("New event in: " + event);
        }
    }
}
