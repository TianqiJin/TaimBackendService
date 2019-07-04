package com.taim.taimbackendservice.mapper;

import com.google.common.base.Converter;
import com.taim.taimbackendservice.model.Quotation;
import com.taim.taimbackendservicemodel.QuotationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class QuotationDTOMapper extends Converter<Quotation, QuotationDTO> {

    private final CustomerDTOMapper customerDTOMapper;
    private final TransactionDetailDTOMapper transactionDetailDTOMapper;
    private final StaffDTOMapper staffDTOMapper;

    @Autowired
    public QuotationDTOMapper(CustomerDTOMapper customerDTOMapper,
                              TransactionDetailDTOMapper transactionDetailDTOMapper,
                              StaffDTOMapper staffDTOMapper) {
        this.customerDTOMapper = customerDTOMapper;
        this.transactionDetailDTOMapper = transactionDetailDTOMapper;
        this.staffDTOMapper = staffDTOMapper;
    }

    @Override
    protected QuotationDTO doForward(Quotation quotation) {
        return QuotationDTO.builder()
                .id(quotation.getId())
                .quotationId(quotation.getQuotationId())
                .quotationDate(quotation.getQuotationDate())
                .dueDate(quotation.getDueDate())
                .subtotal(quotation.getSubtotal())
                .total(quotation.getTotal())
                .totalTax(quotation.getTotalTax())
                .customer(this.customerDTOMapper.convert(quotation.getCustomer()))
                .staff(this.staffDTOMapper.convert(quotation.getStaff()))
                .transactionType(quotation.getTransactionType().getValue())
                .refId(quotation.getRefId())
                .status(quotation.getStatus().getValue())
                .note(quotation.getNote())
                .transactionDetails(quotation.getTransactionDetails().stream()
                        .map(this.transactionDetailDTOMapper::convert)
                        .collect(Collectors.toList()))
                .billFromAddress(quotation.getBillFromAddress())
                .billToAddress(quotation.getBillToAddress())
                .build();
    }

    @Override
    protected Quotation doBackward(QuotationDTO quotationDTO) {
        throw new UnsupportedOperationException();
    }
}
