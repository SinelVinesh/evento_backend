package custom.springutils.search;

public enum SearchOperator {

    min(">"),
    mineq(">="),
    eq("="),
    max("<"),
    maxeq("<="),
    like("like"),
    noteq("!="),
    ilike("iLike"),
    isnull(" is null"),
    isnotnull(" is not null");


    public final String value;

    SearchOperator(String value) {
        this.value = value;
    }
}
