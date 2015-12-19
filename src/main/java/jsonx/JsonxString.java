package jsonx;

import javax.json.JsonString;

import com.fasterxml.jackson.databind.node.TextNode;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class JsonxString implements JsonString {

    private final TextNode value;

    JsonxString(String value) {
        this(new TextNode(value));
    }

    @Override
    public ValueType getValueType() {
        return ValueType.STRING;
    }

    @Override
    public String getString() {
        return value.asText();
    }

    @Override
    public CharSequence getChars() {
        return getString();
    }
    
    @Override
    public String toString() {
        return value == null ? null : value.asText();
    }

}
