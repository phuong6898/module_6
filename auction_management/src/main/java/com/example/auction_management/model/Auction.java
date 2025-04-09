package com.example.auction_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "auctions")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id", columnDefinition = "INT")
    private Integer auctionId;

    @NotNull(message = "Sản phẩm không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Future(message = "Thời gian bắt đầu phải trong tương lai")
    @Column(name = "auction_start_time", nullable = false)
    private LocalDateTime auctionStartTime;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    @Future(message = "Thời gian kết thúc phải trong tương lai")
    @Column(name = "auction_end_time", nullable = false)
    private LocalDateTime auctionEndTime;

    @NotNull(message = "Giá hiện tại không được để trống")
    @DecimalMin(value = "0.00", inclusive = false, message = "Giá hiện tại phải lớn hơn 0")
    @Column(name = "current_price", precision = 12, scale = 2, nullable = false)
    private BigDecimal currentPrice;

    @NotNull(message = "Bước giá không được để trống")
    @DecimalMin(value = "0.01", message = "Bước giá phải lớn hơn 0")
    @Column(name = "bid_step", precision = 12, scale = 2, nullable = false)
    private BigDecimal bidStep;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('pending', 'active', 'completed', 'canceled') DEFAULT 'pending'")
    private AuctionStatus status = AuctionStatus.pending;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum AuctionStatus {
        pending, active, completed, canceled
    }
}
