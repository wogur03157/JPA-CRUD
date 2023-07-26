package com.mever.api.domain.payment.dto;

import com.mever.api.domain.payment.entity.Orders;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EasyCardPaymentDto {
    @ApiModelProperty("지불방법")
    private String payType;
    @ApiModelProperty("지불금액")
    private String amount;
    @ApiModelProperty("부가세")
    private String vat;
    @ApiModelProperty("승인번호")
    private String approvalNum;
    @ApiModelProperty("주문 상품 이름")
    private String orderName;
    @ApiModelProperty("card number")
    private String cardNum;
    @ApiModelProperty("구매자 이메일")
    private String email;
    @ApiModelProperty("할부")
    private String Installment;
    @ApiModelProperty("성공여부")
    private String success;
    @ApiModelProperty("승인일시")
    private String approvedAt;
    @ApiModelProperty("거래고유번호")
    private String orderId;
    @ApiModelProperty("카드유형")
    private String cardType;
    public Orders toOrderBuilder() {
        return Orders.builder()
                .orderId(orderId)
                .orderName(orderName)
                .paymentKey(approvalNum)
                .email(email)
                .approvedAt(approvedAt)
                .method("카드")
                .vat(Long.valueOf(vat))
                .number(cardNum)
                .totalAmount(Long.valueOf(amount))
                .build();
    }

}