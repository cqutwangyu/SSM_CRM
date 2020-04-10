package com.cqut.wangyu.crm.system;

/**
 * @author wangyu4017@sefonsoft.com
 * @title: CRMException
 * @projectName SSM_CRM
 * @description: 抛异常类
 * @date 2020/3/31 18:00
 */
public class CRMException extends RuntimeException {

    private final Code code;
    private final String message;

    CRMException(Code code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public static CRMException create(Code code) {
        return new CRMException(code, null);
    }

    public static CRMException create(Code code, String message) {
        return new CRMException(code, message);
    }

    public Code getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message != null && !message.isEmpty()
                ? "MdsErrorCode = " + getCodeMessage(this.code) + " for " + message
                : "MdsErrorCode = " + getCodeMessage(this.code);
    }

    static String getCodeMessage(Code code) {
        switch (code) {
            case NOFIELD:
                return "NoSuchField";
            case BADFIELDTYPE:
                return "BadFieldType";
            case DUPFIELDNAME:
                return "DuplicateFieldName";
            case NOINDEX:
                return "NoIndex";
            case BADINDEXID:
                return "BadIndexID";
            case NOFORMULA:
                return "NoSuchFormula";
            case AGGREGATIONFORMULAERROR:
                return "AggregationFormulaError";
            case COLLECTIONFORMULAERROR:
                return "CollectionFormulaError";
            default:
                return "Unknown error " + code;
        }
    }

    public enum Code {
        NOFIELD(-1),
        BADFIELDTYPE(-2),
        DUPFIELDNAME(-3),
        NOINDEX(-101),
        BADINDEXID(-102),
        NOFORMULA(-201),
        AGGREGATIONFORMULAERROR(-202),
        COLLECTIONFORMULAERROR(-203);

        private int code;

        Code(int code) {
            this.code = code;
        }

        public int intValue() {
            return code;
        }
    }
}
