package com.wexinc.purchase.api.shared.constant;

import tools.jackson.databind.json.JsonMapper;

public final class PresentationTestConstantes {

  public static final JsonMapper JSON_MAPPER = JsonMapper.builder().build();

  private PresentationTestConstantes() {
    throw ConstantsCore.UTILITY_CLASS_EXCEPTION;
  }
}
