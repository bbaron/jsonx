package jsonx;

import javax.json.JsonString;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class JsonxString implements JsonString {

    private final String value;
    
    @Override
    public ValueType getValueType() {
        return ValueType.STRING;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public CharSequence getChars() {
        return getString();
    }

}
