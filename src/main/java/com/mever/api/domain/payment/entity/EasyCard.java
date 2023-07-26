package com.mever.api.domain.payment.entity;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class EasyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false, unique = true)
    private Long seq;
    @Column
    private String payType;
    @Column
    private String amount;
    @Column
    private String vat;
    @Column
    private String approvalNum; //승인번호
    @Column
    private String orderName;
    @Column
    private String cardNum;
    @Column
    private String email;
    @Column
    private String Installment; //할부
    @Column
    private String success;
    @Column
    private String approvedAt; //승인일시
    @Column
    private String orderId; //거래고유번호
    @Column
    private String cardType;


}