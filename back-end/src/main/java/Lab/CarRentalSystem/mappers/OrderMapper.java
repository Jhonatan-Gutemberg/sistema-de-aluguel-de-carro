package Lab.CarRentalSystem.mappers;

import Lab.CarRentalSystem.dto.CreditContract.CreditContractDTO;
import Lab.CarRentalSystem.dto.Order.RegisterOrderDTO;
import Lab.CarRentalSystem.enums.PlanType;
import Lab.CarRentalSystem.models.Automobile;
import Lab.CarRentalSystem.models.CreditContract;
import Lab.CarRentalSystem.models.Order;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OrderMapper {

    public static Order toEntity(RegisterOrderDTO orderDTO, Automobile automobile) {
        validateOrderDates(orderDTO.deliveryDate());

        Order order = new Order();
        order.setCreationDate(orderDTO.creationDate());
        order.setDeliveryDate(orderDTO.deliveryDate());
        order.setStatus(false);

        long daysBetween = calculateDaysBetween(orderDTO.creationDate(), orderDTO.deliveryDate());
        double value = calculateOrderValue(daysBetween, automobile.getValuePerDay());

        order.setValue(value);
        order.setPlanType(determinePlanType(daysBetween));
        order.setAutomobileId(automobile.getId());

        return order;
    }

    public static CreditContract toEntity(CreditContractDTO creditContractDTO) {
        CreditContract creditContract = new CreditContract();
        creditContract.setAmount(creditContractDTO.amount());
        creditContract.setOrderId(creditContractDTO.orderId());
        creditContract.setBankId(creditContractDTO.bankId());
        creditContract.setCustomerId(creditContractDTO.userId());
        creditContract.setApproved(false);
        return creditContract;
    }

    private static void validateOrderDates(LocalDate deliveryDate) {
        if (deliveryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Delivery date cannot be in the past.");
        }
    }

    private static long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    private static double calculateOrderValue(long daysBetween, double valuePerDay) {
        return daysBetween * valuePerDay;
    }

    private static PlanType determinePlanType(long daysBetween) {
        if (daysBetween > 180) {
            return PlanType.BIANNUAL;
        } else if (daysBetween > 30) {
            return PlanType.MONTHLY;
        } else {
            return PlanType.DAILY;
        }
    }
}
