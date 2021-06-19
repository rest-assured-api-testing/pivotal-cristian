package entities.webhooks;

import com.fasterxml.jackson.annotation.JsonInclude;

public class WebHooks {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int project_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String webhook_url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String created_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String updated_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean enabled;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getWebhook_url() {
        return webhook_url;
    }

    public void setWebhook_url(String webhook_url) {
        this.webhook_url = webhook_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
