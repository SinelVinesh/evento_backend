package custom.springutils.search;

public enum OrderMethod {
    DESC(" desc"),
    ASC(" asc")
    ;

    public final String value;

    OrderMethod(String value) {
        this.value = value;
    }
}
