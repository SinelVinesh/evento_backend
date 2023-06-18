package custom.springutils.model;

import custom.springutils.exception.CustomException;

public abstract class HasFK<FK> extends HasId {
    public abstract void setFK(FK fk) throws CustomException;
}
