package jsonx;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.json.JsonNumber;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
class JsonxNumber implements JsonNumber {

    private final BigDecimal value;

    JsonxNumber(BigInteger value) {
        this.value = new BigDecimal(value);
    }

    JsonxNumber(long value) {
        this.value = BigDecimal.valueOf(value);
    }

    JsonxNumber(int value) {
        this.value = BigDecimal.valueOf(value);
    }

    JsonxNumber(double value) {
        this.value = BigDecimal.valueOf(value);
    }

    @Override
    public ValueType getValueType() {
        return ValueType.NUMBER;
    }

    @Override
    public boolean isIntegral() {
        try {
            bigIntegerValueExact();
            return true;
        } catch (ArithmeticException e) {
            return false;
        }
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public int intValueExact() {
        return value.intValueExact();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }

    @Override
    public long longValueExact() {
        return value.longValueExact();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return value.toBigInteger();
    }

    @Override
    public BigInteger bigIntegerValueExact() {
        return value.toBigIntegerExact();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return value;
    }

}
