package com.ynz.jpa.model;

public enum Severity {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);
    private Integer severityScale;

    Severity(Integer severity) {
        this.severityScale = severity;
    }

    public Integer scale() {
        return severityScale;
    }

}
