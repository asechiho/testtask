package io.testtask.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
@AllArgsConstructor
public class PackageInfo {
    private String published;
    private String licenses;
    private String module;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageInfo that = (PackageInfo) o;
        return new EqualsBuilder()
                .append(StringUtils.normalizeSpace(published), StringUtils.normalizeSpace(that.published))
                .append(StringUtils.normalizeSpace(licenses), StringUtils.normalizeSpace(that.licenses))
                .append(StringUtils.normalizeSpace(module), StringUtils.normalizeSpace(that.module))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(published)
                .append(licenses)
                .append(module)
                .toHashCode();
    }
}
