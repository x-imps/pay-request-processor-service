package com.ximps.payment_service.mapper;

import com.ximps.common.mapper.AbstractMapper;
import com.ximps.pay_request_processor_service.dto.PayRequestDto;
import com.ximps.payment_service.entity.PayRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayRequestMapper extends AbstractMapper<PayRequest, PayRequestDto> {}
