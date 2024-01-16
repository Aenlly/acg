/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package top.aenlly.acg.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;

/**
 * Validate that the character sequence (e.g. string) is a valid URL using the {@code java.net.URL} constructor.
 *
 * @author Hardy Ferentschik
 */
public class URLValidator implements ConstraintValidator<URL, CharSequence> {
    private String protocol;
    private String host;
    private int port;

    @Override
    public void initialize(URL url) {
        this.protocol = url.protocol();
        this.host = url.host();
        this.port = url.port();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.length() == 0) {
            return true;
        }

        java.net.URL url;
        try {
            url = new java.net.URL(value.toString());
        } catch (MalformedURLException e) {
            return false;
        }

        if (this.protocol != null && this.protocol.length() > 0 && !url.getProtocol().equals(this.protocol)) {
            return false;
        }

        if (this.host != null && this.host.length() > 0 && !url.getHost().equals(this.host)) {
            return false;
        }

        if (this.port != -1 && url.getPort() != this.port) {
            return false;
        }

        return true;
    }
}
