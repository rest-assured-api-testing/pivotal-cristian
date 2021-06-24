package entities.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Integration {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public String kind;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public int project_id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean can_import;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String base_url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean is_other;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String story_name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean active;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String created_at;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String updated_at;

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

    public boolean isCan_import() {
        return can_import;
    }

    public void setCan_import(boolean can_import) {
        this.can_import = can_import;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public boolean isIs_other() {
        return is_other;
    }

    public void setIs_other(boolean is_other) {
        this.is_other = is_other;
    }

    public String getStory_name() {
        return story_name;
    }

    public void setStory_name(String story_name) {
        this.story_name = story_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

}
