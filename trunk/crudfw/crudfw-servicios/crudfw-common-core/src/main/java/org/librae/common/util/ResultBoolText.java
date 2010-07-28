package org.librae.common.util;

public class ResultBoolText {

    public static final String  DEFAULT_MSG_VALUE   = "";
    public static final Boolean DEFAULT_CHECK_VALUE = false;

    public String               msg                 = ResultBoolText.DEFAULT_MSG_VALUE;
    public Boolean              check               = ResultBoolText.DEFAULT_CHECK_VALUE;

    public ResultBoolText(final Boolean check) {
        this.check = check;
    }

    public ResultBoolText(final String msg) {
        this.msg = msg;
    }

    public ResultBoolText(final Boolean check, final String msg) {
        this.check = check;
        this.msg = msg;
    }

    @Override
    public String toString() {
        final String msgTxt = (this.msg == null) ? "''" : this.msg;

        final StringBuilder sb = new StringBuilder();
        sb.append("check=").append(this.check);
        sb.append(" ");
        sb.append("msg=").append(msgTxt);

        return sb.toString();
    }
}