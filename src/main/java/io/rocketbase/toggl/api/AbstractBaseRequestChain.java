package io.rocketbase.toggl.api;

import com.google.common.base.Joiner;
import io.rocketbase.toggl.api.model.Billable;
import io.rocketbase.toggl.api.model.DisplayHours;
import org.springframework.core.ParameterizedTypeReference;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by marten on 07.03.17.
 */
public abstract class AbstractBaseRequestChain<T, R> extends ExecutableRequestChain<R> {

    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    protected final String path;

    public AbstractBaseRequestChain(RequestContext context, String path, ParameterizedTypeReference<R> typeReference) {
        super(context, path, typeReference);
        this.path = path;
    }

    public T since(Date since) {
        super.getUriBuilder()
                .addParameter("since", DATE_FORMAT.format(since));
        return (T) this;
    }

    /**
     * not allowed in weekly report use since for 7 days starting from...
     */
    public T until(Date until) {
        super.getUriBuilder()
                .addParameter("until", DATE_FORMAT.format(until));
        return (T) this;
    }

    public T billable(Billable billable) {
        super.getUriBuilder()
                .addParameter("billable",
                        billable.name()
                                .toLowerCase());
        return (T) this;
    }

    public T clientIds(String... ids) {
        return clientIds(Arrays.asList(ids));
    }

    /**
     * 0 if you want to filter out time entries without a client
     */
    public T clientIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("client_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T projectIds(String... ids) {
        return projectIds(Arrays.asList(ids));
    }

    /**
     * 0 if you want to filter out time entries without a project
     */
    public T projectIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("project_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T userIds(String... ids) {
        return userIds(Arrays.asList(ids));
    }

    public T userIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("user_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T membersOfGroupIds(String... ids) {
        return membersOfGroupIds(Arrays.asList(ids));
    }

    /**
     * This limits provided user_ids to the provided group members
     */
    public T membersOfGroupIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("members_of_group_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T orMembersOfGroupIds(String... ids) {
        return orMembersOfGroupIds(Arrays.asList(ids));
    }

    /**
     * This extends provided user_ids with the provided group members
     */
    public T orMembersOfGroupIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("or_members_of_group_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T tagIds(String... ids) {
        return tagIds(Arrays.asList(ids));
    }

    /**
     * 0 if you want to filter out time entries without a tag
     */
    public T tagIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("tag_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T taskIds(String... ids) {
        return tagIds(Arrays.asList(ids));
    }

    /**
     * 0 if you want to filter out time entries without a task
     */
    public T taskIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("task_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T timeEntryIds(String... ids) {
        return tagIds(Arrays.asList(ids));
    }

    public T timeEntryIds(List<String> ids) {
        super.getUriBuilder()
                .addParameter("time_entry_ids",
                        Joiner.on(",")
                                .skipNulls()
                                .join(ids));
        return (T) this;
    }

    public T description(String description) {
        super.getUriBuilder()
                .addParameter("description", description);
        return (T) this;
    }

    public T withoutDescription(boolean withoutDescription) {
        super.getUriBuilder()
                .addParameter("without_description", withoutDescription ? "true" : "false");
        return (T) this;
    }

    /**
     * date/description/duration/user
     */
    public T orderField(String orderField) {
        super.getUriBuilder()
                .addParameter("order_field", orderField);
        return (T) this;
    }

    /**
     * on for descending and off for ascending order
     */
    public T orderDesc(boolean orderDesc) {
        super.getUriBuilder()
                .addParameter("order_desc", orderDesc ? "on" : "off");
        return (T) this;
    }

    /**
     * default off
     */
    public T distinctRates(boolean distinctRates) {
        super.getUriBuilder()
                .addParameter("distinct_rates", distinctRates ? "on" : "off");
        return (T) this;
    }

    /**
     * default off, rounds time according to workspace settings
     */
    public T rounding(boolean rounding) {
        super.getUriBuilder()
                .addParameter("rounding", rounding ? "on" : "off");
        return (T) this;
    }

    /**
     * display hours with minutes or as a decimal number, default minutes
     */
    public T displayHours(DisplayHours displayHours) {
        super.getUriBuilder()
                .addParameter("display_hours",
                        displayHours.name()
                                .toLowerCase());
        return (T) this;
    }
}
