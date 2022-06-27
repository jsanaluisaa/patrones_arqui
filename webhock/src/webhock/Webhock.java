package com.atlassian.stash.plugin.webook;

import com.atlassian.stash.event.pull.PullRequestRescopedEvent;
import com.atlassian.stash.hook.repository.AsyncPostReceiveRepositoryHook;
import com.atlassian.stash.hook.repository.RepositoryHookContext;
import com.atlassian.stash.nav.NavBuilder;
import com.atlassian.stash.pull.PullRequestRef;
import com.atlassian.stash.repository.RefChange;
import com.atlassian.stash.repository.RefChangeType;
import com.atlassian.stash.repository.Repository;
import com.atlassian.stash.setting.RepositorySettingsValidator;
import com.atlassian.stash.setting.Settings;
import com.atlassian.stash.setting.SettingsValidationErrors;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;

import com.atlassian.event.api.EventListener;
import com.atlassian.stash.event.pull.PullRequestOpenedEvent;

public class WebHook implements AsyncPostReceiveRepositoryHook, RepositorySettingsValidator {

    private static final Logger log = LoggerFactory.getLogger(WebHook.class);
    private static final String REFS_HEADS = "refs/heads/";

    public WebHook(NavBuilder navBuilder) {
    }

    @Override
    public void postReceive(RepositoryHookContext context, Collection<RefChange> refChanges) {
        // NOTE: Need to pass this down into postChange
        String url = context.getSettings().getString("url");

        Iterable<RefChange> updatedRefs = Iterables.filter(refChanges, new Predicate<RefChange>() {
            @Override
            public boolean apply(RefChange input) {
                // We only care about non-deleted branches
                return input.getType() != RefChangeType.DELETE && input.getRefId().startsWith(REFS_HEADS);
            }
        });

        for (RefChange refChange : updatedRefs) {
            log.info("Ref changed: " + refChange.getRefId());
            String ref = refChange.getRefId().replace(REFS_HEADS, "");

            if (ref.equals("master")) {
                String sha = refChange.getToHash();

                postChange(ref, sha);
            }
        }
    }


    @EventListener
    public void pullRequestOpened(PullRequestOpenedEvent prEvent) {
        // NOTE: How to getSettings from here?
        PullRequestRef fromRef = prEvent.getPullRequest().getFromRef();

        postChange(fromRef.getId(), fromRef.getLatestChangeset());
    }

    @EventListener
    public void pullRequestRescoped(PullRequestRescopedEvent prEvent) {

        PullRequestRef fromRef = prEvent.getPullRequest().getFromRef();

        if (!prEvent.getPreviousFromHash().equals(fromRef.getLatestChangeset())) {
            postChange(fromRef.getId(), fromRef.getLatestChangeset());
        }
    }

    private void postChange(String ref, String sha) {
        // NOTE: This is hard-coded :(
        String baseUrl = "http://localhost:3000/repositories/7/build-ref";
        String urlParams = "ref=" + urlEncode(ref) + "&sha=" + urlEncode(sha);

        post(baseUrl, urlParams);
    }

    private void post(String baseUrl, String urlParams) {
        log.debug("post: " + baseUrl + "?" + urlParams);

        try {

            URLConnection conn = new URL(baseUrl).openConnection();
            conn.setDoOutput(true);  // Triggers POST
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream ());
            wr.writeBytes(urlParams);
            wr.flush();
            wr.close();

            conn.getInputStream().close();

        } catch (Exception e) {
            log.error("Error in post", e);
        }
    }


    private static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void validate(Settings settings, SettingsValidationErrors errors, Repository repository) {
        String url = settings.getString("url");
        if (url == null || url.trim().isEmpty()) {
            errors.addFieldError("url", "URL field is blank, please supply one");
        }
    }
}