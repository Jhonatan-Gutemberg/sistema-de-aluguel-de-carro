package Lab.CarRentalSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import Lab.CarRentalSystem.dto.Order.RegisterOrderDTO;
import Lab.CarRentalSystem.mappers.OrderMapper;
import Lab.CarRentalSystem.models.Agency;
import Lab.CarRentalSystem.models.Automobile;
import Lab.CarRentalSystem.models.Bank;
import Lab.CarRentalSystem.models.CreditContract;
import Lab.CarRentalSystem.models.Customer;
import Lab.CarRentalSystem.models.Order;
import Lab.CarRentalSystem.models.SystemUser;
import Lab.CarRentalSystem.repository.AgencyRepository;
import Lab.CarRentalSystem.repository.AutomobileRepository;
import Lab.CarRentalSystem.repository.BankRepository;
import Lab.CarRentalSystem.repository.CreditContractRepository;
import Lab.CarRentalSystem.repository.CustomerRepository;
import Lab.CarRentalSystem.repository.OrderRepository;
import Lab.CarRentalSystem.service.interfaces.IOrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AutomobileRepository automobileRepository;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Order register(RegisterOrderDTO orderDTO) {
        Customer customer = customerRepository.findById(orderDTO.customerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Automobile automobile = automobileRepository.findById(orderDTO.automobileId())
                .orElseThrow(() -> new EntityNotFoundException("Automobile not found"));

        Agency agency = agencyRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        // Bank bank = bankRepository.findById(orderDTO.bankId())
        //         .orElseThrow(() -> new EntityNotFoundException("Agency not found"));

        Order order = OrderMapper.toEntity(orderDTO, automobile);
        order.setCustomerId(customer.getId());
        order.setAutomobileId(automobile.getId());
        order.setAgencyId(agency.getId());

        orderRepository.save(order);
        agencyRepository.save(agency);
        customerRepository.save(customer);

        if (customer.getOrders() == null) {
            customer.setOrders(new ArrayList<>());
        }

        // if (bank.getContracts() == null) {
        //     bank.setContracts(new ArrayList<>());
        // }

        if (agency.getOrders() == null) {
            agency.setOrders(new ArrayList<>());
        }

        agency.getOrders().add(order.getId());
        customer.getOrders().add(order.getId());
        // bank.getContracts().add(order.getId());

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    // @Override
    // public Order updateOrder(Long id, Order orderDetails) {
    // Optional<Order> optionalOrder = orderRepository.findById(id);
    // if (optionalOrder.isPresent()) {
    // Order existingOrder = optionalOrder.get();
    // existingOrder.setCreationDate(orderDetails.getCreationDate());
    // existingOrder.setDeliveryDate(orderDetails.getDeliveryDate());
    // existingOrder.setStatus(orderDetails.isStatus());
    // existingOrder.setCustomer(orderDetails.getCustomer().getId());
    // existingOrder.setAutomobile(orderDetails.getAutomobile());
    // existingOrder.setValue(orderDetails.getValue());
    // existingOrder.setPlanType(orderDetails.getPlanType());
    // existingOrder.setAgency(orderDetails.getAgency());

    // return orderRepository.save(existingOrder);
    // } else {
    // return null;
    // }
    // }

    @Override
    public boolean deleteOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Order toggleOrderStatus(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        // Inverte o status do pedido
        order.setStatus(!order.isStatus());

        return orderRepository.save(order); // Salva as alterações
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        Optional<SystemUser> userOptional = systemUserService.getUserById1(userId); // Obter o usuário como Optional
        if (userOptional.isPresent() && userOptional.get() instanceof Customer) {
            Customer customer = (Customer) userOptional.get(); // Cast para Customer
            return orderRepository.findByIdIn(customer.getOrders()); // Buscar ordens
        }
        return new ArrayList<>(); // Retornar uma lista vazia se o usuário não for encontrado ou não for um
                                  // Customer
    }

}
