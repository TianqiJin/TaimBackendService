package com.taim.taimbackendservice.utils;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.util.List;

public class Constants {
    public static final List<BigDecimal> ALLOWED_GST_RATES = ImmutableList.of(BigDecimal.ZERO, new BigDecimal("5"));
    public static final List<BigDecimal> ALLOWED_PST_RATES = ImmutableList.of(BigDecimal.ZERO, new BigDecimal("7"));
}
