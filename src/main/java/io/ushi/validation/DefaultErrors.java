package io.ushi.validation;

public enum DefaultErrors {

    @Text("{io.ushi.validation.DataNotFound.message}")
    DataNotFound,

    @Text("{io.ushi.validation.DuplicateConstraint.message}")
    DuplicateConstraint
}
