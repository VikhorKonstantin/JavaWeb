package by.training.task1oop.bean.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class UpdateSubmissionPublisher implements
        Flow.Publisher<UpdateSubmissionPublisher.UpdateData> {
    /**
     * List of subscribers.
     */
    private List<Flow.Subscriber> subscribers = new ArrayList<>();

    /**
     * @param subscriber new subscriber.
     */
    @Override
    public void subscribe(
            final Flow.Subscriber<? super UpdateData> subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * @param newUpdateData to submit.
     */
    public void submit(final UpdateData newUpdateData) {
        subscribers.forEach(s -> s.onNext(newUpdateData));
    }

    public final class UpdateData {
        /**
         * field name before update.
         */
        private int beforeUpdateValue;
        /**
         * field name after update.
         */
        private int afterUpdateValue;
        /**
         * field name.
         */
        private String fieldName;

        /**
         * @param newBeforeUpdateValue new beforeUpdateValue.
         * @param newAfterUpdateValue new afterUpdateValue
         * @param newFieldName new field name
         */
        public UpdateData(final int newBeforeUpdateValue,
                          final int newAfterUpdateValue,
                          final String newFieldName) {
            beforeUpdateValue = newBeforeUpdateValue;
            afterUpdateValue = newAfterUpdateValue;
            fieldName = newFieldName;
        }

        /**
         * @return beforeUpdateValue.
         */
        public int getBeforeUpdateValue() {
            return beforeUpdateValue;
        }

        /**
         * @return afterUpdateValue
         */
        public int getAfterUpdateValue() {
            return afterUpdateValue;
        }

        /**
         * @return fieldName
         */
        public String getFieldName() {
            return fieldName;
        }
    }
}
