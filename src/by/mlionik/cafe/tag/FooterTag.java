package by.mlionik.cafe.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class FooterTag extends TagSupport {
    private String locale;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String footerMsg;
            if ("rus_RU".equalsIgnoreCase(locale)) {
                footerMsg = "© Кафе | Ольга Млёник | Epam";
            } else  {
                footerMsg = "© Cafe | Volha Mlionik | Epam";
            }
            JspWriter out = pageContext.getOut();
            out.write("<footer>");
            out.write(" <div class=\"footer-copyright text-center py-3\">");
            out.write(" <p class=\"text-muted\">" + footerMsg + "</p>");
            out.write("</div>");
            out.write("</footer>");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
