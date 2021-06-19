package entities.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

public class IntegrationCreate {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public boolean active;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String base_url;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String type;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
