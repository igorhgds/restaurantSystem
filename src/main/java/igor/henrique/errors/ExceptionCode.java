package igor.henrique.errors;

public enum ExceptionCode {
    DUPLICATED_RESOURCE,
    ENTITY_NOT_FOUND;


    public String getExceptionIndex() {
        var enumTotalEntries = values().length;
        var leftPadZerosCount = (String.valueOf(enumTotalEntries).length() + 1);
        var template = "%0" + leftPadZerosCount + "d";
        return String.format(template, this.ordinal());
    }
}
