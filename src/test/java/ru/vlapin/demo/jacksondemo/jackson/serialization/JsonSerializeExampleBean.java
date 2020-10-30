package ru.vlapin.demo.jacksondemo.jackson.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class JsonSerializeExampleBean {

  String name;

  @JsonSerialize(using = CustomDateSerializer.class)
  Date eventDate;
}

class CustomDateSerializer extends StdSerializer<Date> {

  private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

  public CustomDateSerializer() {
    this(null);
  }

  protected CustomDateSerializer(Class<Date> t) {
    super(t);
  }

  @Override
  public void serialize(Date value, JsonGenerator gen, SerializerProvider __) throws IOException {
    gen.writeString(formatter.format(value));
  }
}
