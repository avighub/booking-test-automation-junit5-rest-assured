package org.booking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Book {
  private int id;
  private String title;
  private String author;
}
